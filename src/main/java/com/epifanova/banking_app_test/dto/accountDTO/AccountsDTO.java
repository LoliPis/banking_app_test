package com.epifanova.banking_app_test.dto.accountDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDTO {
  private Long count;
  private List<AccountDTO> result;
}
