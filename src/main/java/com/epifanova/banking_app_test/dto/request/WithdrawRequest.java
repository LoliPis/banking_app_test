package com.epifanova.banking_app_test.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class WithdrawRequest {

  @Pattern(regexp = "^\\d{4}$", message = "Must contain exactly 4 digits")
  private String pinCode;

  @Positive
  private Long fromAccountNumber;

  @Positive
  private BigDecimal amount;
}
