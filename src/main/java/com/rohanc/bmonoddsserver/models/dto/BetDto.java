package com.rohanc.bmonoddsserver.models.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.rohanc.bmonoddsserver.models.dto.BetStatusDto;
import com.rohanc.bmonoddsserver.models.dto.MarketStateDto;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * BetDto
 */

@JsonTypeName("bet-dto")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-23T11:29:00.797394723Z[Etc/UTC]")
public class BetDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime createdAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime updatedAt;

  private Integer stake;

  private BetStatusDto status;

  private Float toReturn;

  private MarketStateDto marketState;

  public BetDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public BetDto(Long id, Integer stake, BetStatusDto status) {
    this.id = id;
    this.stake = stake;
    this.status = status;
  }

  public BetDto id(Long id) {
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

  public BetDto createdAt(OffsetDateTime createdAt) {
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

  public BetDto updatedAt(OffsetDateTime updatedAt) {
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

  public BetDto stake(Integer stake) {
    this.stake = stake;
    return this;
  }

  /**
   * Get stake
   * minimum: 0
   * @return stake
  */
  @NotNull @Min(0) 
  @Schema(name = "stake", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("stake")
  public Integer getStake() {
    return stake;
  }

  public void setStake(Integer stake) {
    this.stake = stake;
  }

  public BetDto status(BetStatusDto status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @NotNull @Valid 
  @Schema(name = "status", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("status")
  public BetStatusDto getStatus() {
    return status;
  }

  public void setStatus(BetStatusDto status) {
    this.status = status;
  }

  public BetDto toReturn(Float toReturn) {
    this.toReturn = toReturn;
    return this;
  }

  /**
   * Get toReturn
   * @return toReturn
  */
  
  @Schema(name = "toReturn", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("toReturn")
  public Float getToReturn() {
    return toReturn;
  }

  public void setToReturn(Float toReturn) {
    this.toReturn = toReturn;
  }

  public BetDto marketState(MarketStateDto marketState) {
    this.marketState = marketState;
    return this;
  }

  /**
   * Get marketState
   * @return marketState
  */
  @Valid 
  @Schema(name = "marketState", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("marketState")
  public MarketStateDto getMarketState() {
    return marketState;
  }

  public void setMarketState(MarketStateDto marketState) {
    this.marketState = marketState;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BetDto betDto = (BetDto) o;
    return Objects.equals(this.id, betDto.id) &&
        Objects.equals(this.createdAt, betDto.createdAt) &&
        Objects.equals(this.updatedAt, betDto.updatedAt) &&
        Objects.equals(this.stake, betDto.stake) &&
        Objects.equals(this.status, betDto.status) &&
        Objects.equals(this.toReturn, betDto.toReturn) &&
        Objects.equals(this.marketState, betDto.marketState);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdAt, updatedAt, stake, status, toReturn, marketState);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BetDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("    stake: ").append(toIndentedString(stake)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    toReturn: ").append(toIndentedString(toReturn)).append("\n");
    sb.append("    marketState: ").append(toIndentedString(marketState)).append("\n");
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

