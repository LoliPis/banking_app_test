package com.epifanova.banking_app_test.dto.accountDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewAccountDTO {

  @Pattern(regexp = "^\\d{4}$", message = "Must contain exactly 4 digits")
  private String pinCode;

  @NotBlank
  @Size(min =  2,  max = 50)
  private String recipientName;
}
