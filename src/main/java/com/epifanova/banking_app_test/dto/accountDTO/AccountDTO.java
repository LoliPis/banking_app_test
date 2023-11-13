package com.epifanova.banking_app_test.dto.accountDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Contains recipient name and account number of created account. Sent by server")
public class AccountDTO {
  private Long accountNumber;
  private String recipientName;
  private BigDecimal balance;
}
