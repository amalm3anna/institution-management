package com.amal.institutionmanagement.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * The type User entity.
 */
@Entity
@Table(name = "APP_USERS")
@Getter
@Setter
public class UserEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  
  @Column(name = "username", nullable = false)
  private String username;
  
  @Column(name = "password", nullable = false)
  private String password;
}
