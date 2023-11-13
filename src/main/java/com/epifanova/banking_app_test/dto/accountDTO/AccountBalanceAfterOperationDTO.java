package com.epifanova.banking_app_test.dto.accountDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Contains actual information about account number, recipient name and balance " +
    "of account after operation. Sent by server")
public class AccountBalanceAfterOperationDTO {
  private Long accountNumber;
  private String recipientName;
  private BigDecimal balance;
}
