package com.amal.institutionmanagement.user;

import com.amal.institutionmanagement.shared.Constants;
import com.amal.institutionmanagement.shared.util.Utils;
import com.amal.institutionmanagement.user.exception.LoginFailedException;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type User exception handler.
 */
@ControllerAdvice
@Log4j2
public class UserExceptionHandler {
  
  /**
   * Handle login failed exception response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler(LoginFailedException.class)
  public ResponseEntity<?> handleLoginFailedException(
          LoginFailedException ex) {
    
    log.warn("Handling LoginFailedException. Username: {}", ex.getUsername());
    /*
    Build the error body
     */
    Map<String, Object> errorBody = Utils.buildErrorBody(
            Constants.LOGIN_FAILED_ERROR_CODE,
            Constants.LOGIN_FAILED_ERROR_MESSAGE);
    /*
    return the error response
     */
    return new ResponseEntity<>(errorBody, HttpStatus.UNAUTHORIZED);
  }
}
