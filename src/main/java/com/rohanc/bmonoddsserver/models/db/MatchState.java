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
@Table(name = "match_states")
public class MatchState extends BaseEntity {
  @NonNull
  @Column(nullable = false)
  private String setScore;

  @NonNull
  @Column(nullable = false)
  private String pointScore;

  @NonNull
  @Column(nullable = false)
  private Integer servingIndex;

  @ManyToOne()
  @JoinColumn(name = "matches_id")
  private Match match;
}
