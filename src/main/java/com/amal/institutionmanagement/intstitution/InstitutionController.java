package com.amal.institutionmanagement.intstitution;

import com.amal.institutionmanagement.intstitution.request.CreateOrUpdateInstitutionRequest;
import com.amal.institutionmanagement.intstitution.response.CreateOrUpdateInstitutionResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Institution controller.
 */
@RestController
@RequestMapping(value = "institution")
@Tag(name = "Institution Controller")
@Validated
@AllArgsConstructor
@Log4j2
public class InstitutionController {
  
  private final InstitutionService institutionService;
  
  /**
   * Gets all institutions.
   *
   * @return the all institutions
   */
  @GetMapping("all")
  public ResponseEntity<?> getAllInstitutions() {
    
    log.info("Invoke getAllInstitutions controller method");
    /*
    Return the success response
     */
    return ResponseEntity.ok(institutionService.getAllInstitutions());
  }
  
  /**
   * Gets institution by id.
   *
   * @param id the id
   * @return the institution by id
   */
  @GetMapping("{id}")
  public ResponseEntity<?> getInstitutionById(@PathVariable("id") Long id) {
    
    log.info("Invoke getInstitutionById controller method with id {}", id);
    /*
    Return the success response
     */
    return ResponseEntity.ok(institutionService.getInstitutionById(id));
  }
  
  /**
   * Gets all active institutions.
   *
   * @return the all active institutions
   */
  @GetMapping("active")
  public ResponseEntity<?> getAllActiveInstitutions() {
    
    log.info("Invoke getAllActiveInstitutions controller method");
    /*
    Return the success response
     */
    return ResponseEntity.ok(institutionService.getActiveInstitutions());
  }
  
  @PostMapping("create-update")
  public ResponseEntity<?> createOrUpdateInstitution(
          @Valid @RequestBody CreateOrUpdateInstitutionRequest request) {
    
    log.info("Invoke createOrUpdateInstitution controller method");
    /*
    Send a request to either create or update the institution
     */
    CreateOrUpdateInstitutionResponse response = institutionService.createUpdateInstitution(
            request.getId(),
            request.getCode(),
            request.getName(),
            request.getStatus());
    /*
    Return the success response
     */
    return ResponseEntity.ok(response);
  }
  
  /**
   * Delete institution by id response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping("{id}")
  public ResponseEntity<?> deleteInstitutionById(@PathVariable("id") Long id) {
    
    log.info("Invoke deleteInstitutionById controller method with id {}", id);
    /*
    Return the success response
     */
    return ResponseEntity.ok(institutionService.deleteInstitutionById(id));
  }
}
