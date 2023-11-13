package com.epifanova.banking_app_test.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
public class Account {

  @Id
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "account_numbers_auto_increment")
  @SequenceGenerator(
      name = "account_numbers_auto_increment",
      sequenceName = "account_numbers_auto_increment",
      allocationSize = 1
  )
  @Column(name = "account_number", updatable = false, nullable = false)
  private Long accountNumber;

  @Column(name = "recipient_name", nullable = false)
  private String recipientName;

  @Column(name = "pin_code", nullable = false)
  private String pinCode;

  @Column(name = "balance", nullable = false)
  private BigDecimal balance;

  public void deposit(BigDecimal amount) {
    balance = balance.add(amount);
  }

  public void withdraw(BigDecimal amount) {
    balance = balance.subtract(amount);
  }

}
