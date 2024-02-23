package com.rohanc.bmonoddsserver.models.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets bet-status-dto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-23T11:29:00.797394723Z[Etc/UTC]")
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

