package com.amal.institutionmanagement.shared.annotation.password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.log4j.Log4j2;

/**
 * The type Password validator.
 */
@Log4j2
public class PasswordValidator implements ConstraintValidator<PasswordValidate, Object> {
  
  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    boolean result;
    
    String password = String.valueOf(value);
    
    if (password == null || password.isEmpty()) {
      log.warn("Password is null or empty");
      result = false;
    } else if (password.length() < 8) {
      log.warn("Password must be at least 8 characters");
      result = false;
    } else if (!password.matches(".*[A-Z].*")) {
      log.warn("Password must contain at least one uppercase letter");
      result = false;
    } else if (!password.matches(".*\\d.*")) {
      log.warn("Password must contain at least one digit");
      result = false;
    } else {
      result = true;
    }
    return result;
  }
}
