/* (C)2024 */
package com.rohanc.bmonoddsserver.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.*;
import java.util.Objects;

/**
 * A change during a match, most likely in points.
 */
@Schema(
    name = "match_upsert_dto_matchState",
    description = "A change during a match, most likely in points.")
@JsonTypeName("match_upsert_dto_matchState")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-03-17T12:07:05.351636620Z[Etc/UTC]")
public class MatchUpsertDtoMatchState implements Serializable {

  private static final long serialVersionUID = 1L;

  private String pointScore;

  private Integer servingIndex;

  private String setScore;

  public MatchUpsertDtoMatchState pointScore(String pointScore) {
    this.pointScore = pointScore;
    return this;
  }

  /**
   * Get pointScore
   * @return pointScore
   */
  @Schema(name = "pointScore", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pointScore")
  public String getPointScore() {
    return pointScore;
  }

  public void setPointScore(String pointScore) {
    this.pointScore = pointScore;
  }

  public MatchUpsertDtoMatchState servingIndex(Integer servingIndex) {
    this.servingIndex = servingIndex;
    return this;
  }

  /**
   * Get servingIndex
   * minimum: 0
   * maximum: 1
   * @return servingIndex
   */
  @Min(0)
  @Max(1)
  @Schema(name = "servingIndex", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("servingIndex")
  public Integer getServingIndex() {
    return servingIndex;
  }

  public void setServingIndex(Integer servingIndex) {
    this.servingIndex = servingIndex;
  }

  public MatchUpsertDtoMatchState setScore(String setScore) {
    this.setScore = setScore;
    return this;
  }

  /**
   * Get setScore
   * @return setScore
   */
  @Schema(name = "setScore", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("setScore")
  public String getSetScore() {
    return setScore;
  }

  public void setSetScore(String setScore) {
    this.setScore = setScore;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MatchUpsertDtoMatchState matchUpsertDtoMatchState = (MatchUpsertDtoMatchState) o;
    return Objects.equals(this.pointScore, matchUpsertDtoMatchState.pointScore)
        && Objects.equals(this.servingIndex, matchUpsertDtoMatchState.servingIndex)
        && Objects.equals(this.setScore, matchUpsertDtoMatchState.setScore);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pointScore, servingIndex, setScore);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MatchUpsertDtoMatchState {\n");
    sb.append("    pointScore: ").append(toIndentedString(pointScore)).append("\n");
    sb.append("    servingIndex: ").append(toIndentedString(servingIndex)).append("\n");
    sb.append("    setScore: ").append(toIndentedString(setScore)).append("\n");
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
