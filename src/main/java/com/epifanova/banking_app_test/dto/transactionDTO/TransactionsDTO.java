package com.epifanova.banking_app_test.dto.transactionDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsDTO {
  private Long count;
  private List<TransactionDTO> result;

}
