package com.epifanova.banking_app_test.service;

import com.epifanova.banking_app_test.dto.accountDTO.AccountBalanceAfterOperationDTO;
import com.epifanova.banking_app_test.dto.accountDTO.AccountDTO;
import com.epifanova.banking_app_test.dto.accountDTO.AccountsDTO;
import com.epifanova.banking_app_test.dto.accountDTO.NewAccountDTO;
import com.epifanova.banking_app_test.dto.request.DepositRequest;
import com.epifanova.banking_app_test.dto.request.TransferRequest;
import com.epifanova.banking_app_test.dto.request.WithdrawRequest;
import com.epifanova.banking_app_test.exceptions.AccountNotFoundException;
import com.epifanova.banking_app_test.exceptions.InvalidTransferOperationException;
import com.epifanova.banking_app_test.exceptions.NotEnoughMoneyException;
import com.epifanova.banking_app_test.model.Account;
import com.epifanova.banking_app_test.repository.AccountRepository;
import com.epifanova.banking_app_test.service.Impl.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class AccountServiceTest {
  @Mock
  private AccountRepository accountRepository;
  @Mock
  private SecurityService securityService;
  @Mock
  private TransactionService transactionService;
  @InjectMocks
  private AccountServiceImpl accountService;


  @Test
  public void createAccountTest() {
    NewAccountDTO newAccountDTO = new NewAccountDTO("1234", "Recipient");
    Account createdAccount = new Account();
    when(accountRepository.save(any(Account.class))).thenReturn(createdAccount);

    AccountDTO result = accountService.createAccount(newAccountDTO);

    assertNotNull(result);
    assertEquals(createdAccount.getAccountNumber(), result.getAccountNumber());
    assertEquals(newAccountDTO.getRecipientName(), result.getRecipientName());
    assertEquals(BigDecimal.ZERO, result.getBalance());
    verify(accountRepository, times(1)).save(any(Account.class));
  }

  @Test
  public void getAllAccountsTest() {
    String recipientName = "Recipient";
    List<Account> accounts = Arrays.asList(new Account(), new Account());
    when(accountRepository.findAllByRecipientName(recipientName)).thenReturn(accounts);

    AccountsDTO result = accountService.getAllAccountsByRecipientName(recipientName);

    assertNotNull(result);
    assertEquals(accounts.size(), result.getCount());
    verify(accountRepository, times(1)).findAllByRecipientName(recipientName);
  }

  @Test
 public void depositTest() {
    DepositRequest depositRequest = new DepositRequest(1L, BigDecimal.TEN);
    DepositRequest incorrectAccountNumberDepositRequest = new DepositRequest(2L, BigDecimal.TEN);
    Account account = new Account();
    account.setBalance(BigDecimal.ZERO);
    when(accountRepository.findById(depositRequest.getToAccountNumber())).thenReturn(Optional.of(account));

    AccountBalanceAfterOperationDTO result = accountService.deposit(depositRequest);

    assertNotNull(result);
    assertEquals(account.getAccountNumber(), result.getAccountNumber());
    assertEquals(account.getRecipientName(), result.getRecipientName());
    assertEquals(BigDecimal.TEN, result.getBalance());

    assertThrows(AccountNotFoundException.class, () -> accountService.deposit(incorrectAccountNumberDepositRequest));

    verify(accountRepository, times(1)).findById(depositRequest.getToAccountNumber());
    verify(accountRepository, times(1)).save(account);
    verify(transactionService, times(1)).depositTransaction(account, depositRequest.getAmount());
  }

  @Test
  public void withdrawTest() {
    WithdrawRequest withdrawRequest = new WithdrawRequest("encodedPinCode",1L, BigDecimal.TEN);
    WithdrawRequest incorrectAccountNumberWithdrawRequest = new WithdrawRequest("encodedPinCode",2L, BigDecimal.TEN);
    Account accountWithPositiveBalance = new Account();
    accountWithPositiveBalance.setAccountNumber(1L);
    accountWithPositiveBalance.setBalance(BigDecimal.TEN);
    accountWithPositiveBalance.setPinCode("encodedPinCode");

    when(accountRepository.findById(withdrawRequest.getFromAccountNumber())).thenReturn(Optional.of(accountWithPositiveBalance));

    AccountBalanceAfterOperationDTO resultPositive = accountService.withdraw(withdrawRequest);

    assertNotNull(resultPositive);
    assertEquals(accountWithPositiveBalance.getAccountNumber(), resultPositive.getAccountNumber());
    assertEquals(accountWithPositiveBalance.getRecipientName(), resultPositive.getRecipientName());
    assertEquals(BigDecimal.ZERO, resultPositive.getBalance());

    assertThrows(AccountNotFoundException.class, () -> accountService.withdraw(incorrectAccountNumberWithdrawRequest));

    verify(accountRepository, times(1)).findById(withdrawRequest.getFromAccountNumber());
    verify(securityService, times(1)).verifyPinCode(withdrawRequest.getPinCode(), accountWithPositiveBalance.getPinCode());
    verify(accountRepository, times(1)).save(accountWithPositiveBalance);
    verify(transactionService, times(1)).withdrawTransaction(accountWithPositiveBalance, withdrawRequest.getAmount());
  }

  @Test
  public void transferTest() {
    TransferRequest transferRequest = new TransferRequest(1L, 2L, "encodedPinCode", BigDecimal.TEN);
    Account fromAccount = new Account();
    fromAccount.setPinCode("encodedPinCode");
    fromAccount.setBalance(BigDecimal.TEN);
    Account toAccount = new Account();
    toAccount.setBalance(BigDecimal.ZERO);

    when(accountRepository.findById(transferRequest.getFromAccountNumber())).thenReturn(Optional.of(fromAccount));
    when(accountRepository.findById(transferRequest.getToAccountNumber())).thenReturn(Optional.of(toAccount));

    accountService.transfer(transferRequest);

    assertEquals(BigDecimal.ZERO, fromAccount.getBalance());
    assertEquals(BigDecimal.TEN, toAccount.getBalance());

    verify(accountRepository, times(1)).findById(transferRequest.getFromAccountNumber());
    verify(accountRepository, times(1)).findById(transferRequest.getToAccountNumber());
    verify(securityService, times(1)).verifyPinCode(transferRequest.getPinCode(), fromAccount.getPinCode());
    verify(accountRepository, times(1)).save(fromAccount);
    verify(accountRepository, times(1)).save(toAccount);
    verify(transactionService, times(1)).withdrawTransaction(fromAccount, transferRequest.getAmount());
    verify(transactionService, times(1)).depositTransaction(toAccount, transferRequest.getAmount());
  }

  @Test
  public void notEnoughMoneyExceptionTest() {
    WithdrawRequest withdrawRequest = new WithdrawRequest("encodedPinCode",1L, BigDecimal.TEN);
    Account accountWithNegativeBalance = new Account();
    accountWithNegativeBalance.setAccountNumber(1L);
    accountWithNegativeBalance.setBalance(BigDecimal.ZERO);
    when(accountRepository.findById(accountWithNegativeBalance.getAccountNumber()))
        .thenReturn(Optional.of(accountWithNegativeBalance));
    assertThrows(NotEnoughMoneyException.class, () -> accountService.withdraw(withdrawRequest));
  }

  @Test
  public void invalidTransferOperationExceptionTest() {
    assertThrows(InvalidTransferOperationException.class,
        () -> accountService.transfer(new TransferRequest(
            1L,
            1L,
            "1234",
            BigDecimal.TEN)));
  }
}
