package com.epifanova.banking_app_test.exceptions.handler;

import com.epifanova.banking_app_test.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

  @ExceptionHandler(WrongPinCodeException.class)
  public ResponseEntity<RestError> handleWrongPinCodeException(
      WrongPinCodeException ex) {
    return configureResponse(HttpStatus.UNAUTHORIZED, ex);
  }

  @ExceptionHandler(AccountNotFoundException.class)
  public ResponseEntity<RestError> handleAccountNotFoundException(
      WrongPinCodeException ex) {
    return configureResponse(HttpStatus.NOT_FOUND, ex);
  }

  @ExceptionHandler(NotEnoughMoneyException.class)
  public ResponseEntity<RestError> handleNotEnoughMoneyException(
      NotEnoughMoneyException ex) {
    return configureResponse(HttpStatus.BAD_REQUEST, ex);
  }

  @ExceptionHandler(InvalidTransferOperationException .class)
  public ResponseEntity<RestError> handleInvalidTransferOperationException(
      InvalidTransferOperationException ex) {
    return configureResponse(HttpStatus.BAD_REQUEST, ex);
  }
  private ResponseEntity<RestError> configureResponse(
      HttpStatus httpStatus,
      Throwable ex) {
      RestError restError = new RestError(httpStatus.toString(), ex.getMessage());
      return ResponseEntity
          .status(httpStatus)
          .body(restError);
  }
}
