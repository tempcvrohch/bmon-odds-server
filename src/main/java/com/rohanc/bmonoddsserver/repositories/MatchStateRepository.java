/* (C)2024 */
package com.rohanc.bmonoddsserver.repositories;

import com.rohanc.bmonoddsserver.models.db.MatchState;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchStateRepository extends JpaRepository<MatchState, Long> {
  @Query(value = "SELECT * FROM matches_states m WHERE m.match_id = :matchId", nativeQuery = true)
  List<MatchState> findAllByMatchId(@Param("matchId") Long matchId);

  @Query(
      value =
          "SELECT * FROM matches_states m WHERE m.match_id = :matchId ORDER BY m.id DESC"
              + " LIMIT 1",
      nativeQuery = true)
  MatchState findLatestByMatchId(@Param("matchId") Long matchId);
}
