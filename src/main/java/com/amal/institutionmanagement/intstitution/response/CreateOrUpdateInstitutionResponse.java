package com.amal.institutionmanagement.intstitution.response;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Create or update institution response.
 */
@Getter
@Setter
public class CreateOrUpdateInstitutionResponse {
  
  private Long institutionId;
  private boolean success;
  private String message;
}
