package com.epifanova.banking_app_test.controller;

import com.epifanova.banking_app_test.dto.accountDTO.AccountBalanceAfterOperationDTO;
import com.epifanova.banking_app_test.dto.accountDTO.AccountDTO;
import com.epifanova.banking_app_test.dto.accountDTO.AccountsDTO;
import com.epifanova.banking_app_test.dto.accountDTO.NewAccountDTO;
import com.epifanova.banking_app_test.dto.request.DepositRequest;
import com.epifanova.banking_app_test.dto.request.TransferRequest;
import com.epifanova.banking_app_test.dto.request.WithdrawRequest;
import com.epifanova.banking_app_test.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Tag(name = "Accounts")
public class AccountController {
  private final AccountService accountService;

  @Operation(summary = "Create new account")
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
  @RequestMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.POST)
  public AccountDTO createAccount(@Valid @RequestBody NewAccountDTO newAccountDTO){
    return accountService.createAccount(newAccountDTO);
  }

  @Operation(summary = "Get holder's numbers of accounts")
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
  @RequestMapping(value = "/{recipientName}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.GET)
  public AccountsDTO getAllAccounts(@Valid @PathVariable String recipientName){
      return accountService.getAllAccountsByRecipientName(recipientName);
  }

  @Operation(summary = "Deposit to account by account number")
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
  @RequestMapping(value = "/deposit",
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.POST)
  public AccountBalanceAfterOperationDTO deposit(@Valid @RequestBody DepositRequest depositRequest){
    return accountService.deposit(depositRequest);
  }

  @Operation(summary = "Withdraw from account by account number")
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
  @RequestMapping(value = "/withdraw",
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.POST)
  public AccountBalanceAfterOperationDTO withdraw(@Valid @RequestBody WithdrawRequest withdrawRequest){
    return accountService.withdraw(withdrawRequest);
  }

  @Operation(summary = "Transfer from account by account number to other account by account number")
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
  @RequestMapping(value = "/transfer",
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.POST)
  public void transfer(@Valid @RequestBody TransferRequest transferRequest){
    accountService.transfer(transferRequest);
  }
}
