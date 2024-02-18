/* (C)2024 */
package com.rohanc.bmonoddsserver.repositories;

import com.rohanc.bmonoddsserver.models.db.Bet;
import com.rohanc.bmonoddsserver.models.db.Bet.BetStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// TODO: implement modelmapper someday
public interface BetRepository extends JpaRepository<Bet, Long> {
  @Query(
      value =
          "SELECT * FROM bets b WHERE b.market_state_id = :marketStateId AND b.user_id ="
              + " :userId",
      nativeQuery = true)
  Bet findByMarketStateIdAndUserId(
      @Param("marketStateId") Long marketStateId, @Param("userId") Long userId);

  @Query(
      value =
          "SELECT * FROM bets b WHERE b.user_id = :userId ORDER BY b.created_at DESC" + " LIMIT 25",
      nativeQuery = true)
  List<Bet> findByUserIdLimit(@Param("userId") Long userId);

  @Query(
      value = "SELECT COUNT(id) FROM bets b WHERE b.user_id = :userId AND b.status = :status",
      nativeQuery = true)
  Integer countByStatusAndUserId(@Param("userId") Long userId, @Param("status") String status);

  @Query(
      value =
          "SELECT mts.* FROM matches m "
              + "JOIN matches_states ms ON m.id = ms.match_id "
              + "JOIN market_states mts ON ms.id = mts.match_state_id "
              + "JOIN bets bts ON mts.id = bts.market_state_id "
              + "WHERE m.id = :matchId",
      nativeQuery = true)
  List<Bet> findByMatchId(@Param("matchId") Long matchId);

  @Modifying
  @Query(value = "UPDATE bets SET status = :status WHERE id = :id", nativeQuery = true)
  void updateOnBetStatusById(@Param("id") Long id, @Param("status") BetStatus status);
}
