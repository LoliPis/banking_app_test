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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "account_number", nullable = false)
  private String accountNumber;

  @Column(name = "recipient_name", nullable = false)
  private String recipientName;

  @Column(name = "pin_code", nullable = false)
  private int pinCode;

  @Column(name = "balance", nullable = false)
  private BigDecimal balance;

}
