/* (C)2024 */
package com.rohanc.bmonoddsserver.services;

import com.rohanc.bmonoddsserver.models.db.Market;
import com.rohanc.bmonoddsserver.repositories.MarketRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketService {
  @Autowired private MarketRepository marketRepository;

  public List<Market> getAllMarkets() {
    return marketRepository.findAll();
  }
}
