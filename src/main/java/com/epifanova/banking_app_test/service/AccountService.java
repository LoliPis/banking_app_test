package com.epifanova.banking_app_test.service;

import com.epifanova.banking_app_test.dto.accountDTO.AccountBalanceAfterOperationDTO;
import com.epifanova.banking_app_test.dto.accountDTO.AccountDTO;
import com.epifanova.banking_app_test.dto.accountDTO.AccountsDTO;
import com.epifanova.banking_app_test.dto.request.DepositRequest;
import com.epifanova.banking_app_test.dto.accountDTO.NewAccountDTO;
import com.epifanova.banking_app_test.dto.request.TransferRequest;
import com.epifanova.banking_app_test.dto.request.WithdrawRequest;

public interface AccountService {

  AccountsDTO getAllAccounts(String recipientName);

  AccountDTO createAccount(NewAccountDTO newAccountDTO);

  AccountBalanceAfterOperationDTO deposit(DepositRequest depositRequest);

  AccountBalanceAfterOperationDTO withdraw(WithdrawRequest withdrawRequest);

  void transfer(TransferRequest transferRequest);

}
