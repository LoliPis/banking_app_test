package com.epifanova.banking_app_test.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Schema(description = "Used to make a deposit")
@AllArgsConstructor
@NoArgsConstructor
@Data
public final class DepositRequest {

  @Positive
  private Long toAccountNumber;

  @Positive
  private BigDecimal amount;
}
