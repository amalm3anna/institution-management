package com.amal.institutionmanagement.intstitution;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Institution repository.
 */
@Repository
public interface InstitutionRepository extends JpaRepository<InstitutionEntity, Long> {
  
  /**
   * Find all by status list.
   *
   * @param status the status
   * @return the list
   */
  List<InstitutionEntity> findAllByStatus(Integer status);
}
