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
 * A request body of a wager placed by a user on a marketState.
 */
@Schema(
    name = "bet-place-dto",
    description = "A request body of a wager placed by a user on a marketState.")
@JsonTypeName("bet-place-dto")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-03-17T12:07:05.351636620Z[Etc/UTC]")
public class BetPlaceDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Float stake;

  private Long marketStateId;

  public BetPlaceDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public BetPlaceDto(Float stake, Long marketStateId) {
    this.stake = stake;
    this.marketStateId = marketStateId;
  }

  public BetPlaceDto stake(Float stake) {
    this.stake = stake;
    return this;
  }

  /**
   * Get stake
   * minimum: 0
   * @return stake
   */
  @NotNull
  @DecimalMin("0")
  @Schema(name = "stake", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("stake")
  public Float getStake() {
    return stake;
  }

  public void setStake(Float stake) {
    this.stake = stake;
  }

  public BetPlaceDto marketStateId(Long marketStateId) {
    this.marketStateId = marketStateId;
    return this;
  }

  /**
   * Get marketStateId
   * @return marketStateId
   */
  @NotNull
  @Schema(name = "marketStateId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("marketStateId")
  public Long getMarketStateId() {
    return marketStateId;
  }

  public void setMarketStateId(Long marketStateId) {
    this.marketStateId = marketStateId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BetPlaceDto betPlaceDto = (BetPlaceDto) o;
    return Objects.equals(this.stake, betPlaceDto.stake)
        && Objects.equals(this.marketStateId, betPlaceDto.marketStateId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stake, marketStateId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BetPlaceDto {\n");
    sb.append("    stake: ").append(toIndentedString(stake)).append("\n");
    sb.append("    marketStateId: ").append(toIndentedString(marketStateId)).append("\n");
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
