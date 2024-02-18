/* (C)2024 */
package com.rohanc.bmonoddsserver.repositories;

import com.rohanc.bmonoddsserver.models.db.Match;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
  @Query(value = "SELECT * FROM matches m WHERE m.created_at >= :timestamp", nativeQuery = true)
  List<Match> findAfterTimestamp(@Param("timestamp") Timestamp timestamp);

  @Query(
      value =
          "SELECT * FROM matches m WHERE m.created_at >= :startTimestamp AND m.created_at"
              + " <= :endTimestamp",
      nativeQuery = true)
  List<Match> findBetweenTimestamps(
      @Param("startTimestamp") Timestamp startTimestamp,
      @Param("endTimestamp") Timestamp endTimestamp);
}
