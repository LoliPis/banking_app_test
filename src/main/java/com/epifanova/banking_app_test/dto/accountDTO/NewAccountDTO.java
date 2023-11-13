package com.epifanova.banking_app_test.dto.accountDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Used to create accounts")
public class NewAccountDTO {

  @Schema(description = "Must contains 4 digits", example = "1234")
  @Pattern(regexp = "^\\d{4}$", message = "Must contain exactly 4 digits")
  private String pinCode;

  @Schema(description = "Must be unique!", minLength = 2, maxLength = 50, example = "my_name")
  @NotBlank
  @Size(min =  2,  max = 50)
  private String recipientName;
}
