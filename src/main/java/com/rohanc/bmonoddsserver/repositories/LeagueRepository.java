/* (C)2024 */
package com.rohanc.bmonoddsserver.repositories;

import com.rohanc.bmonoddsserver.models.db.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<League, Long> {}
