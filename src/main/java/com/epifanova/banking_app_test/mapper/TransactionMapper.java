package com.epifanova.banking_app_test.mapper;

import com.epifanova.banking_app_test.dto.transactionDTO.TransactionDTO;
import com.epifanova.banking_app_test.dto.transactionDTO.TransactionsDTO;
import com.epifanova.banking_app_test.model.Account;
import com.epifanova.banking_app_test.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper
public interface TransactionMapper {

  TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

  @Mapping(source = "account", target = "account")
  @Mapping(source = "operationType", target = "operationType")
  @Mapping(source = "amount", target = "amount")
  @Mapping(target = "createdDate", source = "transaction.createdDate", qualifiedByName = "localDateTimeToString")
  TransactionDTO toTransactionDTO(Transaction transaction);

  @Named("localDateTimeToString")
  default String localDateTimeToString(LocalDateTime localDateTime) {
    if (localDateTime == null) {
      return null;
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return localDateTime.format(formatter);
  }


  default Long map(Account account) {
    if (account != null) {
      return account.getAccountNumber();
    } else {
      return null;
    }
  }

  List<TransactionDTO> toTransactionDTOList(List<Transaction> transactions);

  default TransactionsDTO toTransactionsDTO(List<Transaction> transactions) {
    TransactionsDTO transactionsDTO = new TransactionsDTO();
    transactionsDTO.setResult(toTransactionDTOList(transactions));
    transactionsDTO.setCount((long) transactions.size());
    return transactionsDTO;
  }
}
