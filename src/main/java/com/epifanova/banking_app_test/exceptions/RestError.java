package com.epifanova.banking_app_test.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestError {
  private String httpStatus;
  private String message;
}
