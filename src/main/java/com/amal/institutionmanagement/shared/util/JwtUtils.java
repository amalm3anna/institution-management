package com.amal.institutionmanagement.shared.util;

import com.amal.institutionmanagement.shared.config.AppConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * The type Jwt utils.
 */
@Service
@AllArgsConstructor
@Log4j2
public class JwtUtils {
  
  private final AppConfig appConfig;
  
  /**
   * Generate token string.
   *
   * @param username the username
   * @return the string
   */
  public String generateToken(String username) {
    
    /*
    build the jwt token using the key, and set the expiry date to 1 hour
     */
    return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + appConfig.getExpirationTime() * 60 * 60))
            .signWith(Keys.hmacShaKeyFor(appConfig.getSecretKey().getBytes()), SignatureAlgorithm.HS256)
            .compact();
  }
  
  /**
   * Validate token claims.
   *
   * @param token the token
   * @return the claims
   */
  public Claims validateToken(String token) {
    
    try {
      
      return Jwts.parserBuilder()
              .setSigningKey(appConfig.getSecretKey().getBytes())
              .build()
              .parseClaimsJws(token)
              .getBody();
      
    } catch (ExpiredJwtException ex) {
      log.warn("Token expired");
      throw ex;
    } catch (JwtException ex) {
      log.warn("Token invalid: {}", ex.getMessage());
      throw ex;
    }
  }
}
