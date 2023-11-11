package com.epifanova.banking_app_test.service;

import com.epifanova.banking_app_test.dto.transactionDTO.TransactionsDTO;
import com.epifanova.banking_app_test.model.Account;

import java.math.BigDecimal;

public interface TransactionService {

  void withdrawTransaction(Account account, BigDecimal amount);

  void depositTransaction(Account account, BigDecimal amount);

  TransactionsDTO getAllAccountNumberTransactions(Long accountNumber);

}
