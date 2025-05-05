package com.amal.institutionmanagement.shared.filter;

import com.amal.institutionmanagement.shared.Constants;
import com.amal.institutionmanagement.shared.util.JwtUtils;
import io.jsonwebtoken.Claims;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * The type Jwt authentication filter.
 */
@Component
@Log4j2
@AllArgsConstructor
public class JwtAuthenticationFilter implements Filter {
  
  private final JwtUtils jwtUtils;
  
  @Override
  public void doFilter(
          ServletRequest servletRequest,
          ServletResponse servletResponse,
          FilterChain filterChain)
          throws IOException, ServletException {
    
    /*
    Get the request and the response of the http servlet
     */
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    /*
    Avoid authorization checking if the user entered one of the excluded paths
     */
    String path = request.getRequestURI();
    if (Constants.EXCLUDED_PATHS
            .parallelStream()
            .anyMatch(path::startsWith)) {
      log.warn("Bypassing JWT filter for path: {}", path);
      filterChain.doFilter(request, response);
      return;
    }
    /*
    Get the authorization header from the request
     */
    String authHeader = request.getHeader("Authorization");
    log.info("Auth header: {}", authHeader);
    /*
    Handle the case where the authorization header is invalid
     */
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      sendUnauthorizedResponse(
              response,
              "Missing or invalid Authorization header");
      log.warn("Invalid Authorization header: {}", authHeader);
      return;
    }
    /*
    Get the token
     */
    String token = authHeader.substring(7);
    log.info("Token: {}", token);
    try {
      /*
      Proceed with the request
       */
      Claims claims = jwtUtils.validateToken(token);
      String subject = claims.getSubject();
      log.info("Authenticated user: {}", subject);
      filterChain.doFilter(request, response);
    } catch (Exception ex) {
      log.warn("Invalid or expired token: {}", token, ex);
      sendUnauthorizedResponse(
              response,
              "Invalid or expired token");
    }
  }
  
  /*
  This method is responsible to build the response status and message in case the user
  is unauthorized
   */
  private void sendUnauthorizedResponse(
          HttpServletResponse response,
          String message)
          throws IOException {
    
    /*
    Setup the unauthorized response
     */
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getWriter().write(message);
  }
}
