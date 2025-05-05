package com.amal.institutionmanagement.intstitution.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Institution not found exception.
 */
@Getter
@Setter
@AllArgsConstructor
public class InstitutionNotFoundException extends RuntimeException {
  
  private Long id;
}
