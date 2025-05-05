package com.amal.institutionmanagement.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Login failed exception.
 */
@Getter
@Setter
@AllArgsConstructor
public class LoginFailedException extends RuntimeException {
  
  private String username;
}
