/* (C)2024 */
package com.rohanc.bmonoddsserver.models.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.*;
import java.util.*;

/**
 * Gets or Sets bet-status-dto
 */
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-02-23T11:29:00.797394723Z[Etc/UTC]")
public enum BetStatusDto {
  WIN("WIN"),

  LOSS("LOSS"),

  PENDING("PENDING"),

  VOID("VOID");

  private String value;

  BetStatusDto(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BetStatusDto fromValue(String value) {
    for (BetStatusDto b : BetStatusDto.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}
