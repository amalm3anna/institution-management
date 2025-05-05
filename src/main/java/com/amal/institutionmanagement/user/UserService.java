package com.amal.institutionmanagement.user;

import com.amal.institutionmanagement.shared.util.JwtUtils;
import com.amal.institutionmanagement.user.exception.LoginFailedException;
import com.amal.institutionmanagement.user.response.LoginResponse;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * The type Authenticate service.
 */
@Service
@Log4j2
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final JwtUtils jwtUtils;

  /**
   * Login login response.
   *
   * @param username the username
   * @param password the password
   * @return the login response
   */
  public LoginResponse login(
          String username,
          String password) {

    /*
    Find the user entity in the database based on the requested username
     */
    Optional<UserEntity> userEntity = userRepository.findByUsername(username);
    /*
    Check if the user is authorized to login to generate the token
    Then handle the case were the user is not authorized the login failed
     */
    if (userEntity.isPresent()
            && password.equals(userEntity.get().getPassword())) {
      
      log.info("User {} logged in", username);
      /*
      Generate the token of the user
       */
      String token = jwtUtils.generateToken(userEntity.get().getUsername());
      log.info("Generated token: {}", token);
      /*
      Prepare the login response
       */
      LoginResponse response = new LoginResponse();
      response.setToken(token);
      /*
      Return the final response
       */
      return response;
    } else {
      log.warn("Failed to login with user: {}", username);
      throw new LoginFailedException(username);
    }
  }
}
