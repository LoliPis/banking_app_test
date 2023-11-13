package com.epifanova.banking_app_test.service.Impl;

import com.epifanova.banking_app_test.dto.transactionDTO.TransactionsDTO;
import com.epifanova.banking_app_test.exceptions.AccountNotFoundException;
import com.epifanova.banking_app_test.mapper.TransactionMapper;
import com.epifanova.banking_app_test.model.Account;
import com.epifanova.banking_app_test.model.Transaction;
import com.epifanova.banking_app_test.model.enums.OperationType;
import com.epifanova.banking_app_test.repository.AccountRepository;
import com.epifanova.banking_app_test.repository.TransactionRepository;
import com.epifanova.banking_app_test.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
  private final TransactionRepository transactionRepository;
  private final AccountRepository accountRepository;

  public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
    this.transactionRepository = transactionRepository;
    this.accountRepository = accountRepository;
  }

  @Override
  public void withdrawTransaction(Account account, BigDecimal amount) {
    Transaction withdraw = Transaction.newBalanceAfterOperation(account, amount, OperationType.WITHDRAW);
    transactionRepository.save(withdraw);
  }

  @Override
  public void depositTransaction(Account account, BigDecimal amount) {
    Transaction deposit = Transaction.newBalanceAfterOperation(account, amount, OperationType.DEPOSIT);
    transactionRepository.save(deposit);
  }

  @Override
  public TransactionsDTO getAllAccountNumberTransactions(Long accountNumber) {
    if (!accountRepository.existsById(accountNumber)) {
      throw new AccountNotFoundException("Account with " + accountNumber + " not found");
    } else {
      return TransactionMapper.INSTANCE.toTransactionsDTO(transactionRepository
          .findAllByAccount_AccountNumber(accountNumber));
    }
  }
}
