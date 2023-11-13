package com.epifanova.banking_app_test.exceptions;

public class NotEnoughMoneyException extends RuntimeException {

  public NotEnoughMoneyException(String message) {
    super(message);
  }
}
