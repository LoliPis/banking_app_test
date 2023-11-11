package com.epifanova.banking_app_test.model;

import com.epifanova.banking_app_test.model.enums.OperationType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "account_number")
  private Account account;

  @Column(name = "operation_type", nullable = false)
  @Enumerated(value = EnumType.STRING)
  private OperationType operationType;

  @Column(name = "amount", nullable = false)
  private BigDecimal amount;

  @CreationTimestamp(source = SourceType.DB)
  @Column(name = "created_date", nullable = false)
  private LocalDateTime createdDate;

  private Transaction(Account account, OperationType operationType, BigDecimal amount) {
    this.account = account;
    this.operationType = operationType;
    this.amount = amount;
  }

  public static Transaction newBalanceAfterOperation(Account account, BigDecimal amount, OperationType operationType) {
    return new Transaction(account, operationType, amount);
  }
}
