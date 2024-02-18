/* (C)2024 */
package com.rohanc.bmonoddsserver.repositories;

import com.rohanc.bmonoddsserver.models.db.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {}
