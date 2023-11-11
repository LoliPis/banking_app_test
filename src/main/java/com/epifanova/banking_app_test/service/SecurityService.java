package com.epifanova.banking_app_test.service;

import com.epifanova.banking_app_test.exceptions.WrongPinCodeException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityService{
  private PasswordEncoder passwordEncoder;

  public void verifyPinCode(String pinCode, String correctPinCode) {
    if (!passwordEncoder.matches(pinCode,correctPinCode)){
      throw new WrongPinCodeException("Invalid pinCode! The operation cannot be performed.");
    }
  }
  public String encodePinCode(String pinCode) {
    return passwordEncoder.encode(pinCode);
  }
}
