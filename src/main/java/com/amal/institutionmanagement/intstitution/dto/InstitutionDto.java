package com.amal.institutionmanagement.intstitution.dto;

import com.amal.institutionmanagement.shared.annotation.code.CodeValidate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Get institution response.
 */
@Getter
@Setter
public class InstitutionDto {
  
  @Positive
  private Long id;
  @CodeValidate
  private Integer code;
  @NotBlank
  private String name;
  @Positive
  private Integer status;
}
