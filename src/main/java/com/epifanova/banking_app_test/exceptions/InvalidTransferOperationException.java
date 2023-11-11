package com.epifanova.banking_app_test.exceptions;

public class InvalidTransferOperationException extends RuntimeException{
  public InvalidTransferOperationException(String message){
    super(message);
  }
}
