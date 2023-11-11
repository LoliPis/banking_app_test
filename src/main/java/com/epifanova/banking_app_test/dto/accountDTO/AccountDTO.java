package com.epifanova.banking_app_test.dto.accountDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
  private Long accountNumber;
  private String recipientName;
  private BigDecimal balance;
}
