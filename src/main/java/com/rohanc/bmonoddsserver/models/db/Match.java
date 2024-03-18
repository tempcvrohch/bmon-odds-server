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
@Table(name = "matches")
public class Match extends BaseEntity {
  @NonNull @Column(nullable = false)
  private String name;

  @NonNull @Column(nullable = false)
  private Boolean live;

  @OneToOne()
  // @PrimaryKeyJoinColumn
  @JoinColumn(name = "sports_id")
  private Sport sport;

  @OneToOne()
  // @PrimaryKeyJoinColumn
  @JoinColumn(name = "leagues_id")
  private League league;
}
