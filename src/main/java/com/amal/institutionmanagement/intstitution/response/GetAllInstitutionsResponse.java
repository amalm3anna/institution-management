package com.amal.institutionmanagement.intstitution.response;

import com.amal.institutionmanagement.intstitution.dto.InstitutionDto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Get all institutions response.
 */
@Getter
@Setter
public class GetAllInstitutionsResponse {
  
  private List<InstitutionDto> institutions;
}
