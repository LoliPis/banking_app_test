package com.epifanova.banking_app_test.exceptions;

public class WrongPinCodeException extends RuntimeException{
    public WrongPinCodeException(String message) {
      super(message);
    }
}
