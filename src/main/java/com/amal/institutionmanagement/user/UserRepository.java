package com.amal.institutionmanagement.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  
  /**
   * Find by username optional.
   *
   * @param username the username
   * @return the optional
   */
  Optional<UserEntity> findByUsername(String username);
}
