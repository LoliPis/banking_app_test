package com.epifanova.banking_app_test.service;

import com.epifanova.banking_app_test.dto.transactionDTO.TransactionsDTO;
import com.epifanova.banking_app_test.exceptions.AccountNotFoundException;
import com.epifanova.banking_app_test.model.Account;
import com.epifanova.banking_app_test.model.Transaction;
import com.epifanova.banking_app_test.repository.AccountRepository;
import com.epifanova.banking_app_test.repository.TransactionRepository;
import com.epifanova.banking_app_test.service.Impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

  @Mock
  private TransactionRepository transactionRepository;
  @Mock
  private AccountRepository accountRepository;

  @InjectMocks
  private TransactionServiceImpl transactionService;

  @Test
  public void withdrawTransactionTest(){
    Account account = new Account();
    BigDecimal amount = new BigDecimal("100.00");

    transactionService.withdrawTransaction(account, amount);
    verify(transactionRepository,times(1)).save(any(Transaction.class));
  }

  @Test
  public void depositTransactionTest(){
    Account account = new Account();
    BigDecimal amount = new BigDecimal("100.00");

    transactionService.depositTransaction(account, amount);
    verify(transactionRepository,times(1)).save(any(Transaction.class));
  }

  @Test
  public void getAllAccountNumberTransactionsTest() {
    Long accountNumber = 123L;
    Account account = new Account();
    account.setAccountNumber(accountNumber);

    when(accountRepository.existsById(accountNumber)).thenReturn(true);
    when(transactionRepository.findAllByAccount_AccountNumber(accountNumber))
        .thenReturn(Collections.singletonList(new Transaction()));

    TransactionsDTO transactionsDTO = transactionService.getAllAccountNumberTransactions(accountNumber);

    verify(accountRepository, times(1)).existsById(accountNumber);
    verify(transactionRepository, times(1)).findAllByAccount_AccountNumber(accountNumber);

    assertEquals(1, transactionsDTO.getResult().size());
  }

  @Test
  void getAllAccountNumberTransactionsAccountNotFoundTest() {
    Long accountNumber = 123L;

    when(accountRepository.existsById(accountNumber)).thenReturn(false);

    assertThrows(AccountNotFoundException.class,
        () -> transactionService.getAllAccountNumberTransactions(accountNumber));

    verify(accountRepository, times(1)).existsById(accountNumber);
    verify(transactionRepository, never()).findAllByAccount_AccountNumber(anyLong());
  }
}
