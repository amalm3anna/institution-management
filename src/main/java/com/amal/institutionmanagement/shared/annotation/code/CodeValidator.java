package com.amal.institutionmanagement.shared.annotation.code;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CodeValidator implements ConstraintValidator<CodeValidate, Object> {
  
  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    boolean result;
    
    Integer code = Integer.parseInt(String.valueOf(value));
    
    if (code == 0) {
      log.warn("code is 0 or not sent");
      result = false;
    } else if (code < 0) {
      log.warn("code must be greater than 0");
      result = false;
    } else if (String.valueOf(code).length() > 5) {
      log.warn("code length must be max 5");
      result = false;
    } else {
      result = true;
    }
    return result;
  }
}

