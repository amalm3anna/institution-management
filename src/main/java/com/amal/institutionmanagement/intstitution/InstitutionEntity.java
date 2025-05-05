package com.amal.institutionmanagement.intstitution;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Institution entity.
 */
@Entity
@Table(name = "INSTITUTION")
@Getter
@Setter
public class InstitutionEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  
  @Column(name = "code", nullable = false, length = 5)
  private Integer code;
  
  @Column(name = "name", nullable = false, length = 50)
  private String name;
  
  @Column(name = "status", nullable = false)
  @Min(0)
  @Max(1)
  private Integer status;
}
