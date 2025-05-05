package com.amal.institutionmanagement.shared.util;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

/**
 * The type Utils.
 */
@Log4j2
public class Utils {
  
  /**
   * Build error body map.
   *
   * @param errorCode    the error code
   * @param errorMessage the error message
   * @return the map
   */
  public static Map<String, Object> buildErrorBody(
          String errorCode,
          String errorMessage) {
    
    /*
    Create the error body map and insert values with the data related to the error
     */
    Map<String, Object> errorBody = new HashMap<>();
    errorBody.put("timestamp", LocalDateTime.now());
    errorBody.put("status", HttpStatus.UNPROCESSABLE_ENTITY.value());
    errorBody.put("errorCode", errorCode);
    errorBody.put("errorMessage", errorMessage);
    log.warn("Error Body: {}", errorBody);
    /*
    Return the built error body
     */
    return errorBody;
  }
}
