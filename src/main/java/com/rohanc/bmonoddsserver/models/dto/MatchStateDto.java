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
 * MatchStateDto
 */
@JsonTypeName("match-state-dto")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-02-23T11:29:00.797394723Z[Etc/UTC]")
public class MatchStateDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime createdAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime updatedAt;

  private String pointScore;

  private Integer servingIndex;

  private String setScore;

  @Valid private List<@Valid MarketStateDto> marketStates;

  public MatchStateDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public MatchStateDto(Long id) {
    this.id = id;
  }

  public MatchStateDto id(Long id) {
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

  public MatchStateDto createdAt(OffsetDateTime createdAt) {
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

  public MatchStateDto updatedAt(OffsetDateTime updatedAt) {
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

  public MatchStateDto pointScore(String pointScore) {
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

  public MatchStateDto servingIndex(Integer servingIndex) {
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

  public MatchStateDto setScore(String setScore) {
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

  public MatchStateDto marketStates(List<@Valid MarketStateDto> marketStates) {
    this.marketStates = marketStates;
    return this;
  }

  public MatchStateDto addMarketStatesItem(MarketStateDto marketStatesItem) {
    if (this.marketStates == null) {
      this.marketStates = new ArrayList<>();
    }
    this.marketStates.add(marketStatesItem);
    return this;
  }

  /**
   * Get marketStates
   * @return marketStates
   */
  @Valid
  @Schema(name = "marketStates", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("marketStates")
  public List<@Valid MarketStateDto> getMarketStates() {
    return marketStates;
  }

  public void setMarketStates(List<@Valid MarketStateDto> marketStates) {
    this.marketStates = marketStates;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MatchStateDto matchStateDto = (MatchStateDto) o;
    return Objects.equals(this.id, matchStateDto.id)
        && Objects.equals(this.createdAt, matchStateDto.createdAt)
        && Objects.equals(this.updatedAt, matchStateDto.updatedAt)
        && Objects.equals(this.pointScore, matchStateDto.pointScore)
        && Objects.equals(this.servingIndex, matchStateDto.servingIndex)
        && Objects.equals(this.setScore, matchStateDto.setScore)
        && Objects.equals(this.marketStates, matchStateDto.marketStates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdAt, updatedAt, pointScore, servingIndex, setScore, marketStates);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MatchStateDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("    pointScore: ").append(toIndentedString(pointScore)).append("\n");
    sb.append("    servingIndex: ").append(toIndentedString(servingIndex)).append("\n");
    sb.append("    setScore: ").append(toIndentedString(setScore)).append("\n");
    sb.append("    marketStates: ").append(toIndentedString(marketStates)).append("\n");
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
