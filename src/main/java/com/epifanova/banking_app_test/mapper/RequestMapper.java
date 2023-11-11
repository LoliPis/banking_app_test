package com.epifanova.banking_app_test.mapper;

import com.epifanova.banking_app_test.dto.request.DepositRequest;
import com.epifanova.banking_app_test.dto.request.TransferRequest;
import com.epifanova.banking_app_test.dto.request.WithdrawRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RequestMapper {
  RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

  DepositRequest fromTransferToDeposit(TransferRequest transferRequest);

  WithdrawRequest fromTransferToWithdraw(TransferRequest transferRequest);
}
