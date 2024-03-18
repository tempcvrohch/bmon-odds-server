/* (C)2024 */
package com.rohanc.bmonoddsserver.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Match between players with markets.
 */
@Schema(name = "match-upsert-dto", description = "Match between players with markets.")
@JsonTypeName("match-upsert-dto")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-03-17T12:07:05.351636620Z[Etc/UTC]")
public class MatchUpsertDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String name;

  private Boolean live;

  private LeagueDto league;

  private SportDto sport;

  @Valid private List<Long> playerIds = new ArrayList<>();

  private MatchUpsertDtoMatchState matchState;

  public MatchUpsertDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public MatchUpsertDto(
      String name,
      Boolean live,
      LeagueDto league,
      SportDto sport,
      List<Long> playerIds,
      MatchUpsertDtoMatchState matchState) {
    this.name = name;
    this.live = live;
    this.league = league;
    this.sport = sport;
    this.playerIds = playerIds;
    this.matchState = matchState;
  }

  public MatchUpsertDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public MatchUpsertDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @NotNull @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MatchUpsertDto live(Boolean live) {
    this.live = live;
    return this;
  }

  /**
   * Get live
   * @return live
   */
  @NotNull @Schema(name = "live", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("live")
  public Boolean getLive() {
    return live;
  }

  public void setLive(Boolean live) {
    this.live = live;
  }

  public MatchUpsertDto league(LeagueDto league) {
    this.league = league;
    return this;
  }

  /**
   * Get league
   * @return league
   */
  @NotNull @Valid
  @Schema(name = "league", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("league")
  public LeagueDto getLeague() {
    return league;
  }

  public void setLeague(LeagueDto league) {
    this.league = league;
  }

  public MatchUpsertDto sport(SportDto sport) {
    this.sport = sport;
    return this;
  }

  /**
   * Get sport
   * @return sport
   */
  @NotNull @Valid
  @Schema(name = "sport", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("sport")
  public SportDto getSport() {
    return sport;
  }

  public void setSport(SportDto sport) {
    this.sport = sport;
  }

  public MatchUpsertDto playerIds(List<Long> playerIds) {
    this.playerIds = playerIds;
    return this;
  }

  public MatchUpsertDto addPlayerIdsItem(Long playerIdsItem) {
    if (this.playerIds == null) {
      this.playerIds = new ArrayList<>();
    }
    this.playerIds.add(playerIdsItem);
    return this;
  }

  /**
   * Get playerIds
   * @return playerIds
   */
  @NotNull @Schema(name = "playerIds", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("playerIds")
  public List<Long> getPlayerIds() {
    return playerIds;
  }

  public void setPlayerIds(List<Long> playerIds) {
    this.playerIds = playerIds;
  }

  public MatchUpsertDto matchState(MatchUpsertDtoMatchState matchState) {
    this.matchState = matchState;
    return this;
  }

  /**
   * Get matchState
   * @return matchState
   */
  @NotNull @Valid
  @Schema(name = "matchState", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("matchState")
  public MatchUpsertDtoMatchState getMatchState() {
    return matchState;
  }

  public void setMatchState(MatchUpsertDtoMatchState matchState) {
    this.matchState = matchState;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MatchUpsertDto matchUpsertDto = (MatchUpsertDto) o;
    return Objects.equals(this.id, matchUpsertDto.id)
        && Objects.equals(this.name, matchUpsertDto.name)
        && Objects.equals(this.live, matchUpsertDto.live)
        && Objects.equals(this.league, matchUpsertDto.league)
        && Objects.equals(this.sport, matchUpsertDto.sport)
        && Objects.equals(this.playerIds, matchUpsertDto.playerIds)
        && Objects.equals(this.matchState, matchUpsertDto.matchState);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, live, league, sport, playerIds, matchState);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MatchUpsertDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    live: ").append(toIndentedString(live)).append("\n");
    sb.append("    league: ").append(toIndentedString(league)).append("\n");
    sb.append("    sport: ").append(toIndentedString(sport)).append("\n");
    sb.append("    playerIds: ").append(toIndentedString(playerIds)).append("\n");
    sb.append("    matchState: ").append(toIndentedString(matchState)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
