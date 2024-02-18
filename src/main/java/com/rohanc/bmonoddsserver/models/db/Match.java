/* (C)2024 */
package com.rohanc.bmonoddsserver.models.db;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "matches", schema = "bmon_schema")
public class Match extends BaseEntity {
  @NonNull
  @Column(nullable = false)
  private String name;

  @OneToOne() @PrimaryKeyJoinColumn private Sport sport;

  @OneToOne() @PrimaryKeyJoinColumn private League league;
}
