package com.amal.institutionmanagement.shared;

import java.util.List;

/**
 * The type Constants.
 */
public class Constants {
  
  /**
   * The constant ACTIVE_STATUS.
   */
  public static final Integer ACTIVE_STATUS = 1;
  /**
   * The constant EXCLUDED_PATHS.
   */
  public static final List<String> EXCLUDED_PATHS = List.of(
          "/h2-console",
          "/swagger-ui",
          "/v3/api-docs",
          "/swagger-resources",
          "/webjars",
          "/user"
  );
  /**
   * The constant ALL_INSTITUTIONS_NOT_FOUND_ERROR_CODE.
   */
  public static final String ALL_INSTITUTIONS_NOT_FOUND_ERROR_CODE = "IM_1000";
  /**
   * The constant ALL_INSTITUTIONS_NOT_FOUND_ERROR_MESSAGE.
   */
  public static final String ALL_INSTITUTIONS_NOT_FOUND_ERROR_MESSAGE = "No institutions found";
  /**
   * The constant INSTITUTION_NOT_FOUND_ERROR_CODE.
   */
  public static final String INSTITUTION_NOT_FOUND_ERROR_CODE = "IM_1001";
  /**
   * The constant INSTITUTION_NOT_FOUND_ERROR_MESSAGE.
   */
  public static final String INSTITUTION_NOT_FOUND_ERROR_MESSAGE = "Institution with id found";
  /**
   * The constant INSTITUTION_NOT_FOUND_ERROR_CODE.
   */
  public static final String LOGIN_FAILED_ERROR_CODE = "IM_1002";
  /**
   * The constant INSTITUTION_NOT_FOUND_ERROR_MESSAGE.
   */
  public static final String LOGIN_FAILED_ERROR_MESSAGE = "invalid credentials";
}
