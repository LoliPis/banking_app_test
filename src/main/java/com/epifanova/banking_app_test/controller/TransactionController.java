package com.epifanova.banking_app_test.controller;

import com.epifanova.banking_app_test.dto.transactionDTO.TransactionsDTO;
import com.epifanova.banking_app_test.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
@Tag(name = "Transactions")
public class TransactionController {
  private final TransactionService transactionService;

  @Operation(summary = "Get transactions for a account number")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "OK"
      ),
      @ApiResponse(
          responseCode = "401",
          description = "Unauthorized"
      ),
      @ApiResponse(
          responseCode = "403",
          description = "Forbidden"
      ),
      @ApiResponse(
          responseCode = "404",
          description = "Not Found"
      )
  })
  @RequestMapping(value = "/{accountNumber}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.GET)
  public TransactionsDTO getAllAccountNumberTransactions(@PathVariable(name = "accountNumber") Long accountNumber) {
    return transactionService.getAllAccountNumberTransactions(accountNumber);
  }
}
