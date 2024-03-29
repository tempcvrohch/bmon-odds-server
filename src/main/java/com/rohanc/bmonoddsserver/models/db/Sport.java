/* (C)2024 */
package com.rohanc.bmonoddsserver.models.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "sports")
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Sport extends BaseEntity {
  @NonNull @Column(nullable = false)
  private String name;
}
