package com.epifanova.banking_app_test.service.Impl;

import com.epifanova.banking_app_test.dto.accountDTO.AccountBalanceAfterOperationDTO;
import com.epifanova.banking_app_test.exceptions.AccountNotFoundException;
import com.epifanova.banking_app_test.exceptions.InvalidTransferOperationException;
import com.epifanova.banking_app_test.exceptions.NotEnoughMoneyException;
import com.epifanova.banking_app_test.mapper.RequestMapper;
import com.epifanova.banking_app_test.repository.AccountRepository;
import com.epifanova.banking_app_test.dto.accountDTO.AccountDTO;
import com.epifanova.banking_app_test.dto.accountDTO.AccountsDTO;
import com.epifanova.banking_app_test.dto.accountDTO.NewAccountDTO;
import com.epifanova.banking_app_test.dto.request.DepositRequest;
import com.epifanova.banking_app_test.dto.request.TransferRequest;
import com.epifanova.banking_app_test.dto.request.WithdrawRequest;
import com.epifanova.banking_app_test.mapper.AccountMapper;
import com.epifanova.banking_app_test.model.Account;
import com.epifanova.banking_app_test.service.AccountService;
import com.epifanova.banking_app_test.service.SecurityService;
import com.epifanova.banking_app_test.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
  private final AccountRepository accountRepository;
  private final SecurityService securityService;
  private final TransactionService transactionService;

  @Override
  public AccountDTO createAccount(NewAccountDTO newAccountDTO) {
    String pinCode = newAccountDTO.getPinCode();
    newAccountDTO.setPinCode(securityService.encodePinCode(pinCode));
    Account account = AccountMapper.INSTANCE.toAccount(newAccountDTO);
    account.setBalance(BigDecimal.ZERO);
    accountRepository.save(account);
    return AccountMapper.INSTANCE.toAccountDTOFromAccount(account);
  }

  @Override
  public AccountsDTO getAllAccountsByRecipientName(String recipientName) {
    List<Account> accounts = accountRepository.findAllByRecipientName(recipientName);
    return AccountMapper.INSTANCE.toAccountsDTO(accounts);
  }

  @Override
  public AccountBalanceAfterOperationDTO deposit(DepositRequest depositRequest) {
    Long accountNumber = depositRequest.getToAccountNumber();
    BigDecimal amount = depositRequest.getAmount();
    Account account = accountRepository
        .findById(accountNumber)
        .orElseThrow(() -> new AccountNotFoundException("Account with " + accountNumber + " not found"));
    account.deposit(amount);
    accountRepository.save(account);
    transactionService.depositTransaction(account, amount);
    return AccountMapper.INSTANCE.balanceAfterOperationFromAccount(account);
  }

  @Override
  public AccountBalanceAfterOperationDTO withdraw(WithdrawRequest withdrawRequest) {
    Long accountNumber = withdrawRequest.getFromAccountNumber();
    BigDecimal amount = withdrawRequest.getAmount();
    Account account = accountRepository
        .findById(accountNumber)
        .orElseThrow(() -> new AccountNotFoundException("Account with " + accountNumber + " not found"));
    securityService.verifyPinCode(withdrawRequest.getPinCode(), account.getPinCode());
    BigDecimal balance = account.getBalance();
    if (balance.compareTo(amount) < 0) {
      throw new NotEnoughMoneyException("Not enough money");
    }
    account.withdraw(amount);
    accountRepository.save(account);
    transactionService.withdrawTransaction(account, amount);
    return AccountMapper.INSTANCE.balanceAfterOperationFromAccount(account);
  }

  @Override
  public void transfer(TransferRequest transferRequest) {
      if (transferRequest.getToAccountNumber().equals(transferRequest.getFromAccountNumber())) {
        throw new InvalidTransferOperationException("Invalid transfer");
      }
      withdraw(RequestMapper.INSTANCE.fromTransferToWithdraw(transferRequest));
      deposit(RequestMapper.INSTANCE.fromTransferToDeposit(transferRequest));

  }
}
