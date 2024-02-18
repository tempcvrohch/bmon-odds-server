/* (C)2024 */
package com.rohanc.bmonoddsserver.models.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
    value = {"createdAt", "updatedAt"},
    allowGetters = true)
@Data
public abstract class BaseEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  // TODO: figure out why hibernate is not converting thsese correctly
  // @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", updatable = false)
  // @CreatedDate
  private OffsetDateTime createdAt;

  // @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_at")
  // @LastModifiedDate
  private OffsetDateTime updatedAt;
}
