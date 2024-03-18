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
@Table(name = "market_states")
public class MarketState extends BaseEntity {
  @Column(nullable = false)
  private boolean suspended;

  @NonNull @Column(nullable = false)
  private Float odd;

  @Column(nullable = false)
  private Float stakeLimit;

  @ManyToOne()
  @JoinColumn(name = "markets_id")
  private Market market;

  @ManyToOne()
  @JoinColumn(name = "players_id")
  private Player player;

  @ManyToOne()
  @JoinColumn(name = "match_states_id")
  private MatchState matchState;
}
