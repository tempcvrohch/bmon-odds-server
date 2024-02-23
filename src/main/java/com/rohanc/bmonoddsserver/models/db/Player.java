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
@Table(name = "players")
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Player extends BaseEntity {
  @NonNull
  @Column(nullable = false)
  private String firstname;

  @NonNull
  @Column(nullable = false)
  private String lastname;

  @NonNull
  @Column(nullable = false)
  private String slug;

  @NonNull
  @Column(nullable = false)
  private String countryCode;
}
