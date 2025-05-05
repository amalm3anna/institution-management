package com.amal.institutionmanagement.intstitution;

import com.amal.institutionmanagement.intstitution.dto.InstitutionDto;
import com.amal.institutionmanagement.intstitution.exception.AllInstitutionsNotFoundException;
import com.amal.institutionmanagement.intstitution.exception.InstitutionNotFoundException;
import com.amal.institutionmanagement.intstitution.response.CreateOrUpdateInstitutionResponse;
import com.amal.institutionmanagement.intstitution.response.DeleteInstitutionResponse;
import com.amal.institutionmanagement.intstitution.response.GetAllInstitutionsResponse;
import com.amal.institutionmanagement.intstitution.response.GetInstitutionResponse;
import com.amal.institutionmanagement.shared.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * The type Institution service.
 */
@Service
@Log4j2
@AllArgsConstructor
public class InstitutionService {
  
  private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
  private static final ModelMapper modelMapper = new ModelMapper();
  
  private final InstitutionRepository institutionRepository;
  
  /**
   * Gets all institutions.
   *
   * @return the all institutions
   */
  public GetAllInstitutionsResponse getAllInstitutions() {
    
    log.info("Getting all institutions from the database");
    /*
    Finding and retrieving back all the institutions found in the db
     */
    List<InstitutionEntity> institutionEntityList = institutionRepository.findAll();
    /*
    Handle the case where the list has returned empty from the database
     */
    if (institutionEntityList.isEmpty()) {
      log.warn("Institutions list is empty");
      throw new AllInstitutionsNotFoundException();
    }
    /*
    build and return the response
     */
    return buildGetAllInstitutionsResponse(institutionEntityList);
  }
  
  
  /**
   * Gets institution by id.
   *
   * @param id the id
   * @return the institution by id
   */
  public GetInstitutionResponse getInstitutionById(Long id) {
    
    log.info("Getting the institution of id {}", id);
    /*
    Get the requested institution entity by its id
     */
    InstitutionEntity institutionEntity = getInstitutionEntityById(id);
    /*
    Map the entity to the institution response
     */
    GetInstitutionResponse response = modelMapper.map(institutionEntity, GetInstitutionResponse.class);
    log.info("Successfully retrieved Institution response: {}", gson.toJson(response));
    /*
    Return the final response
     */
    return response;
  }
  
  /**
   * Gets active institutions.
   *
   * @return the active institutions
   */
  public GetAllInstitutionsResponse getActiveInstitutions() {
    
    log.info("Getting the active institutions from the database");
    /*
    Find and retrieved all the active institutions of status = 1 from the database
     */
    List<InstitutionEntity> institutionEntityList = institutionRepository
            .findAllByStatus(Constants.ACTIVE_STATUS);
    /*
    Handle the case where the list returned is empty
     */
    if (institutionEntityList.isEmpty()) {
      log.warn("Active Institutions list is empty");
      throw new AllInstitutionsNotFoundException();
    }
    /*
    build and return the response
     */
    return buildGetAllInstitutionsResponse(institutionEntityList);
  }
  
  /**
   * Create update institution create or update institution response.
   *
   * @param institutionId   the institution id
   * @param institutionCode the institution code
   * @param institutionName the institution name
   * @param status          the status
   * @return the create or update institution response
   */
  public CreateOrUpdateInstitutionResponse createUpdateInstitution(
          long institutionId,
          int institutionCode,
          String institutionName,
          int status) {
    
    log.info("Creating and updating the requested institution {}", institutionName);
    /*
    Initializing the institution entity
     */
    InstitutionEntity institutionEntity;
    /*
    Create the institution when the passed if is 0, else update the existing institution
     */
    if (institutionId == 0) {
      institutionEntity = new InstitutionEntity();
      institutionEntity = buildAndSaveInstitutionEntity(
              institutionEntity,
              institutionCode,
              institutionName,
              status);
      log.info("Institution {} successfully created", institutionEntity.getId());
    } else {
      
      institutionEntity = getInstitutionEntityById(institutionId);
      institutionEntity = buildAndSaveInstitutionEntity(
              institutionEntity,
              institutionCode,
              institutionName,
              status);
      log.info("Institution {} successfully updated", institutionEntity.getId());
    }
    /*
    Determine if it was a creation or update
     */
    boolean isUpdate = (institutionId != 0);
    /*
    Prepare the response
     */
    CreateOrUpdateInstitutionResponse response = new CreateOrUpdateInstitutionResponse();
    response.setInstitutionId(institutionEntity.getId());
    response.setSuccess(true);
    response.setMessage(isUpdate ? "Institution Updated successfully"
            : "Institution Created successfully");
    /*
    Return the final response
     */
    return response;
  }
  
  /**
   * Delete institution by id delete institution response.
   *
   * @param id the id
   * @return the delete institution response
   */
  public DeleteInstitutionResponse deleteInstitutionById(Long id) {
    
    log.info("Deleting the institution of id {}", id);
    /*
    Get the requested institution entity by its id
     */
    InstitutionEntity institutionEntity = getInstitutionEntityById(id);
    /*
    Delete the found and retrieved entity from the database
     */
    institutionRepository.delete(institutionEntity);
    /*
    Check if it still exists in the database
     */
    boolean exists = institutionRepository.existsById(id);
    /*
    Prepare and Set up the response
     */
    DeleteInstitutionResponse response = new DeleteInstitutionResponse();
    response.setSuccess(!exists);
    /*
    Return the final response
     */
    return response;
  }
  
  /*
  This common method is responsible to build and set up the GetAllInstitutionsResponse from the entities
  retrieved back from the database
   */
  private GetAllInstitutionsResponse buildGetAllInstitutionsResponse(
          List<InstitutionEntity> institutionEntityList) {
    
    /*
    Loop over each institution retrieved from the db and map it to the institution response
    using modelmapper since they have the same params
     */
    List<InstitutionDto> institutionDtoList = institutionEntityList
            .parallelStream()
            .map(institutionEntity -> modelMapper.map(
                    institutionEntity,
                    InstitutionDto.class))
            .collect(Collectors.toList());
    /*
    Create and Set up the response
     */
    GetAllInstitutionsResponse response = new GetAllInstitutionsResponse();
    response.setInstitutions(institutionDtoList);
    log.info("Successfully retrieved all institutions. Response: {}", gson.toJson(response));
    /*
    return the final response
     */
    return response;
  }
  
  /*
  This common method is responsible to check and find the requested entity from the database
   */
  private InstitutionEntity getInstitutionEntityById(Long id) {
    
    /*
    Find and retrieve back the requested institution by its id
     */
    Optional<InstitutionEntity> optionalInstitution = institutionRepository.findById(id);
    /*
    Handle the case where the requested institution is empty and not found
     */
    if (optionalInstitution.isEmpty()) {
      log.warn("Institution id {} not found", id);
      throw new InstitutionNotFoundException(id);
    }
    /*
    Return the entity
     */
    return optionalInstitution.get();
  }
  
  /*
  This method is responsible to build, set up and save the institution entity in the database
   */
  private InstitutionEntity buildAndSaveInstitutionEntity(
          InstitutionEntity institutionEntity,
          int code,
          String name,
          int status) {
    
    /*
    Set up the institution entity by the given values
     */
    institutionEntity.setCode(code);
    institutionEntity.setName(name);
    institutionEntity.setStatus(status);
    /*
    Save the institution in the database
     */
    institutionEntity = institutionRepository.save(institutionEntity);
    /*
    Return the entity
     */
    return institutionEntity;
  }
}
