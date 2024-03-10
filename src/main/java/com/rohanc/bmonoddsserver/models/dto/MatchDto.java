/* (C)2024 */
package com.rohanc.bmonoddsserver.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * MatchDto
 */
@JsonTypeName("match-dto")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-02-23T11:29:00.797394723Z[Etc/UTC]")
public class MatchDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime createdAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime updatedAt;

  private String name;

  private Boolean live;

  private LeagueDto league;

  private SportDto sport;

  private MatchStateDto matchState;

  @Valid private List<@Valid MatchStateDto> matchStates;

  public MatchDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public MatchDto(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public MatchDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @NotNull
  @Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public MatchDto createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
   */
  @Valid
  @Schema(name = "created_at", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("created_at")
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public MatchDto updatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  /**
   * Get updatedAt
   * @return updatedAt
   */
  @Valid
  @Schema(name = "updated_at", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("updated_at")
  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public MatchDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @NotNull
  @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MatchDto live(Boolean live) {
    this.live = live;
    return this;
  }

  /**
   * Get live
   * @return live
   */
  @Schema(name = "live", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("live")
  public Boolean getLive() {
    return live;
  }

  public void setLive(Boolean live) {
    this.live = live;
  }

  public MatchDto league(LeagueDto league) {
    this.league = league;
    return this;
  }

  /**
   * Get league
   * @return league
   */
  @Valid
  @Schema(name = "league", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("league")
  public LeagueDto getLeague() {
    return league;
  }

  public void setLeague(LeagueDto league) {
    this.league = league;
  }

  public MatchDto sport(SportDto sport) {
    this.sport = sport;
    return this;
  }

  /**
   * Get sport
   * @return sport
   */
  @Valid
  @Schema(name = "sport", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("sport")
  public SportDto getSport() {
    return sport;
  }

  public void setSport(SportDto sport) {
    this.sport = sport;
  }

  public MatchDto matchState(MatchStateDto matchState) {
    this.matchState = matchState;
    return this;
  }

  /**
   * Get matchState
   * @return matchState
   */
  @Valid
  @Schema(name = "matchState", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("matchState")
  public MatchStateDto getMatchState() {
    return matchState;
  }

  public void setMatchState(MatchStateDto matchState) {
    this.matchState = matchState;
  }

  public MatchDto matchStates(List<@Valid MatchStateDto> matchStates) {
    this.matchStates = matchStates;
    return this;
  }

  public MatchDto addMatchStatesItem(MatchStateDto matchStatesItem) {
    if (this.matchStates == null) {
      this.matchStates = new ArrayList<>();
    }
    this.matchStates.add(matchStatesItem);
    return this;
  }

  /**
   * Get matchStates
   * @return matchStates
   */
  @Valid
  @Schema(name = "matchStates", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("matchStates")
  public List<@Valid MatchStateDto> getMatchStates() {
    return matchStates;
  }

  public void setMatchStates(List<@Valid MatchStateDto> matchStates) {
    this.matchStates = matchStates;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MatchDto matchDto = (MatchDto) o;
    return Objects.equals(this.id, matchDto.id)
        && Objects.equals(this.createdAt, matchDto.createdAt)
        && Objects.equals(this.updatedAt, matchDto.updatedAt)
        && Objects.equals(this.name, matchDto.name)
        && Objects.equals(this.live, matchDto.live)
        && Objects.equals(this.league, matchDto.league)
        && Objects.equals(this.sport, matchDto.sport)
        && Objects.equals(this.matchState, matchDto.matchState)
        && Objects.equals(this.matchStates, matchDto.matchStates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, createdAt, updatedAt, name, live, league, sport, matchState, matchStates);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MatchDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    live: ").append(toIndentedString(live)).append("\n");
    sb.append("    league: ").append(toIndentedString(league)).append("\n");
    sb.append("    sport: ").append(toIndentedString(sport)).append("\n");
    sb.append("    matchState: ").append(toIndentedString(matchState)).append("\n");
    sb.append("    matchStates: ").append(toIndentedString(matchStates)).append("\n");
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
