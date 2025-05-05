package com.amal.institutionmanagement.user;

import com.amal.institutionmanagement.user.request.LoginRequest;
import com.amal.institutionmanagement.user.response.LoginResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type User controller.
 */
@RestController
@RequestMapping(value = "user")
@Tag(name = "User Controller")
@AllArgsConstructor
@Log4j2
@Validated
public class UserController {

  private final UserService userService;
  
  /**
   * Login response entity.
   *
   * @param request the request
   * @return the response entity
   */
  @PostMapping("login")
  public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {

    log.info("Invoke login controller method for username: {}", request.getUsername());
    /*
    Send a request for the user to get the token and login
     */
    LoginResponse response = userService.login(
            request.getUsername(),
            request.getPassword());
    /*
    Return the response
     */
    return ResponseEntity.ok(response);
  }
}
