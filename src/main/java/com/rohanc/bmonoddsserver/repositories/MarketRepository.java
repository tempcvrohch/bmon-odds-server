/* (C)2024 */
package com.rohanc.bmonoddsserver.repositories;

import com.rohanc.bmonoddsserver.models.db.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market, Long> {}
