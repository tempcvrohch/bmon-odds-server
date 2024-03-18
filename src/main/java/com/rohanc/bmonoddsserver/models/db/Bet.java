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
@Table(name = "bets")
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Bet extends BaseEntity {
  @NonNull @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private BetStatus status;

  @NonNull @Column(nullable = false)
  private Float stake;

  @NonNull @Column(nullable = false)
  private Float toReturn;

  @ManyToOne()
  @JoinColumn(name = "market_states_id")
  private MarketState marketState;

  @ManyToOne()
  @JoinColumn(name = "users_id")
  private User user;

  public enum BetStatus {
    WIN,
    LOSS,
    PENDING,
    VOID
  }
}
