package com.amal.institutionmanagement.user.request;

import com.amal.institutionmanagement.shared.annotation.password.PasswordValidate;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Login request.
 */
@Getter
@Setter
public class LoginRequest {
  
  private String username;
  @PasswordValidate
  private String password;
}
