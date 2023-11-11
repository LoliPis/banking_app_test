package com.epifanova.banking_app_test.dto.request;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public final class DepositRequest {

  @Positive
  private Long toAccountNumber;

  @Positive
  private BigDecimal amount;
}
