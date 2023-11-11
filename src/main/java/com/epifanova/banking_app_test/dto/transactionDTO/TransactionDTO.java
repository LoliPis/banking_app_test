package com.epifanova.banking_app_test.dto.transactionDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
