package com.amal.institutionmanagement.shared.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

/**
 * The type App config.
 */
@Configuration
@ConfigurationProperties(prefix = "app.institution-management")
@Getter
@Setter
@Validated
public class AppConfig {
  
  private String secretKey;
  private long expirationTime;
}
