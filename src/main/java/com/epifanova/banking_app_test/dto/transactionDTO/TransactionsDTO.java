package com.epifanova.banking_app_test.dto.transactionDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Contains total amount of transactions and list of account's transactions consists of " +
    "id, account (namely: account number) operation type, amount and created date")
public class TransactionsDTO {
  @Schema(description = "Total count")
  private Long count;
  @Schema(description = "Transaction list")
  private List<TransactionDTO> result;

}
