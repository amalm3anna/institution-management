package com.amal.institutionmanagement.intstitution;

import com.amal.institutionmanagement.intstitution.exception.AllInstitutionsNotFoundException;
import com.amal.institutionmanagement.intstitution.exception.InstitutionNotFoundException;
import com.amal.institutionmanagement.shared.Constants;
import com.amal.institutionmanagement.shared.util.Utils;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type Institution exception handler.
 */
@ControllerAdvice
@Log4j2
public class InstitutionExceptionHandler {
  
  /**
   * Handle all institutions not found exception response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler(AllInstitutionsNotFoundException.class)
  public ResponseEntity<?> handleAllInstitutionsNotFoundException(
          AllInstitutionsNotFoundException ex) {
    
    log.warn("Handling AllInstitutionsNotFoundException. Message: {}", ex.getMessage());
    /*
    Build the error body
     */
    Map<String, Object> errorBody = Utils.buildErrorBody(
            Constants.ALL_INSTITUTIONS_NOT_FOUND_ERROR_CODE,
            Constants.ALL_INSTITUTIONS_NOT_FOUND_ERROR_MESSAGE);
    /*
    return the error response
     */
    return new ResponseEntity<>(errorBody, HttpStatus.UNPROCESSABLE_ENTITY);
  }
  
  /**
   * Handle institution not found exception response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler(InstitutionNotFoundException.class)
  public ResponseEntity<?> handleInstitutionNotFoundException(
          InstitutionNotFoundException ex) {
    
    log.warn("Handling InstitutionNotFoundException. Message: {}", ex.getMessage());
    /*
    Build the error body
     */
    Map<String, Object> errorBody = Utils.buildErrorBody(
            Constants.INSTITUTION_NOT_FOUND_ERROR_CODE,
            Constants.INSTITUTION_NOT_FOUND_ERROR_MESSAGE);
    /*
    return the error response
     */
    return new ResponseEntity<>(errorBody, HttpStatus.UNPROCESSABLE_ENTITY);
  }
}
