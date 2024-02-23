/* (C)2024 */
package com.rohanc.bmonoddsserver.repositories;

import com.rohanc.bmonoddsserver.models.db.MarketState;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketStateRepository extends JpaRepository<MarketState, Long> {
  // TODO: doublecheck index
  @Query(
      value =
          "SELECT mts.* FROM matches m "
              + "JOIN match_states ms ON m.id = ms.matches_id "
              + "JOIN market_states mts ON ms.id = mts.match_states_id "
              + "WHERE m.id = :matchId ORDER BY mts.id DESC LIMIT :limit",
      nativeQuery = true)
  List<MarketState> findLatestByMatchIdOrderByIdDescLimit(
      @Param("matchId") Long matchId, @Param("limit") Integer limit);
}
