package com.epifanova.banking_app_test.dto.transactionDTO;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Contains id, account (namely: account number) operation type, amount and created date")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
  private Long id;
  private Long account;
  private String operationType;
  private String amount;
  private String createdDate;

}
