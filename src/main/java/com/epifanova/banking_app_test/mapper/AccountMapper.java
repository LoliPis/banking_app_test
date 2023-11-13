package com.epifanova.banking_app_test.mapper;

import com.epifanova.banking_app_test.dto.accountDTO.AccountBalanceAfterOperationDTO;
import com.epifanova.banking_app_test.dto.accountDTO.AccountDTO;
import com.epifanova.banking_app_test.dto.accountDTO.AccountsDTO;
import com.epifanova.banking_app_test.dto.accountDTO.NewAccountDTO;
import com.epifanova.banking_app_test.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountMapper {
  AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

  Account toAccount(NewAccountDTO newAccountDTO);

  AccountDTO toAccountDTOFromAccount(Account account);

  List<AccountDTO> toAccountDTOList(List<Account> accounts);

  default AccountsDTO toAccountsDTO(List<Account> accounts) {
    AccountsDTO accountsDTO = new AccountsDTO();
    accountsDTO.setResult(toAccountDTOList(accounts));
    accountsDTO.setCount((long) accounts.size());
    return accountsDTO;
  }

  AccountBalanceAfterOperationDTO balanceAfterOperationFromAccount(Account account);
}
