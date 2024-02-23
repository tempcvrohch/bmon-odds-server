/* (C)2024 */
package com.rohanc.bmonoddsserver.services.aggregate;

import com.rohanc.bmonoddsserver.models.db.Market;
import com.rohanc.bmonoddsserver.models.db.MarketState;
import com.rohanc.bmonoddsserver.models.db.Match;
import com.rohanc.bmonoddsserver.models.db.MatchState;
import com.rohanc.bmonoddsserver.models.db.Player;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MatchChildrenAggregate {
  @NonNull public Match match;

  @NonNull public Market market;

  @NonNull public List<MarketState> marketStates;

  @NonNull public MatchState matchState;

  @NonNull public ArrayList<Player> players;
}
