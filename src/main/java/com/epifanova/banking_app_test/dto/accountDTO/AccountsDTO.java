package com.epifanova.banking_app_test.dto.accountDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Contains total amount of accounts and list of recipient's accounts consists of " +
    "recipient name, account numbers and accounts balances")
public class AccountsDTO {
  @Schema(description = "Total count")
  private Long count;
  @Schema(description = "Accounts list")
  private List<AccountDTO> result;
}
