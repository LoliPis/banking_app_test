package com.epifanova.banking_app_test.repository;

import com.epifanova.banking_app_test.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  List<Transaction> findAllByAccount_AccountNumber(Long accountNumber);

}
