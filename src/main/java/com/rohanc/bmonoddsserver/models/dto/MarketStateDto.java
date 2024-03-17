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
import java.util.Objects;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * MarketStateDto
 */
@JsonTypeName("market-state-dto")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-03-17T12:07:05.351636620Z[Etc/UTC]")
public class MarketStateDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime createdAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime updatedAt;

  private Float odd;

  private Boolean suspended;

  private Integer stakeLimit;

  private MarketDto market;

  private PlayerDto player;

  public MarketStateDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public MarketStateDto(Long id, Float odd, Boolean suspended) {
    this.id = id;
    this.odd = odd;
    this.suspended = suspended;
  }

  public MarketStateDto id(Long id) {
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

  public MarketStateDto createdAt(OffsetDateTime createdAt) {
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

  public MarketStateDto updatedAt(OffsetDateTime updatedAt) {
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

  public MarketStateDto odd(Float odd) {
    this.odd = odd;
    return this;
  }

  /**
   * Get odd
   * minimum: 1
   * maximum: 100
   * @return odd
   */
  @NotNull
  @DecimalMin("1")
  @DecimalMax("100")
  @Schema(name = "odd", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("odd")
  public Float getOdd() {
    return odd;
  }

  public void setOdd(Float odd) {
    this.odd = odd;
  }

  public MarketStateDto suspended(Boolean suspended) {
    this.suspended = suspended;
    return this;
  }

  /**
   * Get suspended
   * @return suspended
   */
  @NotNull
  @Schema(name = "suspended", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("suspended")
  public Boolean getSuspended() {
    return suspended;
  }

  public void setSuspended(Boolean suspended) {
    this.suspended = suspended;
  }

  public MarketStateDto stakeLimit(Integer stakeLimit) {
    this.stakeLimit = stakeLimit;
    return this;
  }

  /**
   * Get stakeLimit
   * minimum: 0
   * @return stakeLimit
   */
  @Min(0)
  @Schema(name = "stakeLimit", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("stakeLimit")
  public Integer getStakeLimit() {
    return stakeLimit;
  }

  public void setStakeLimit(Integer stakeLimit) {
    this.stakeLimit = stakeLimit;
  }

  public MarketStateDto market(MarketDto market) {
    this.market = market;
    return this;
  }

  /**
   * Get market
   * @return market
   */
  @Valid
  @Schema(name = "market", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("market")
  public MarketDto getMarket() {
    return market;
  }

  public void setMarket(MarketDto market) {
    this.market = market;
  }

  public MarketStateDto player(PlayerDto player) {
    this.player = player;
    return this;
  }

  /**
   * Get player
   * @return player
   */
  @Valid
  @Schema(name = "player", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("player")
  public PlayerDto getPlayer() {
    return player;
  }

  public void setPlayer(PlayerDto player) {
    this.player = player;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MarketStateDto marketStateDto = (MarketStateDto) o;
    return Objects.equals(this.id, marketStateDto.id)
        && Objects.equals(this.createdAt, marketStateDto.createdAt)
        && Objects.equals(this.updatedAt, marketStateDto.updatedAt)
        && Objects.equals(this.odd, marketStateDto.odd)
        && Objects.equals(this.suspended, marketStateDto.suspended)
        && Objects.equals(this.stakeLimit, marketStateDto.stakeLimit)
        && Objects.equals(this.market, marketStateDto.market)
        && Objects.equals(this.player, marketStateDto.player);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdAt, updatedAt, odd, suspended, stakeLimit, market, player);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MarketStateDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("    odd: ").append(toIndentedString(odd)).append("\n");
    sb.append("    suspended: ").append(toIndentedString(suspended)).append("\n");
    sb.append("    stakeLimit: ").append(toIndentedString(stakeLimit)).append("\n");
    sb.append("    market: ").append(toIndentedString(market)).append("\n");
    sb.append("    player: ").append(toIndentedString(player)).append("\n");
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
