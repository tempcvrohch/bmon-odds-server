/* (C)2024 */
package com.rohanc.bmonoddsserver.api;

import com.rohanc.bmonoddsserver.models.dto.MatchDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-03-17T12:07:05.351636620Z[Etc/UTC]")
@Validated
@Tag(name = "matches", description = "the matches API")
@RequestMapping("${openapi.betMonitorForGeneratingMatchesAndOdds.base-path:/v2}")
public interface MatchesApi {

  default Optional<NativeWebRequest> getRequest() {
    return Optional.empty();
  }

  /**
   * GET /matches : Get all matches or matches between dates.
   *
   * @param from Starting date inclusive. (optional)
   * @param to Ending date exclusive. (optional)
   * @return a list of matches. (status code 200)
   */
  @Operation(
      operationId = "getMatches",
      summary = "Get all matches or matches between dates.",
      tags = {"matches"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "a list of matches.",
            content = {
              @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = MatchDto.class)))
            })
      })
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/matches",
      produces = {"application/json"})
  @ResponseStatus(HttpStatus.OK)
  default List<MatchDto> getMatches(
      @Parameter(name = "from", description = "Starting date inclusive.", in = ParameterIn.QUERY)
          @Valid
          @RequestParam(value = "from", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
          OffsetDateTime from,
      @Parameter(name = "to", description = "Ending date exclusive.", in = ParameterIn.QUERY)
          @Valid
          @RequestParam(value = "to", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
          OffsetDateTime to)
      throws Exception {
    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString = "[ null, null ]";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    throw new IllegalArgumentException("Not implemented");
  }

  /**
   * GET /matches/recent : Get recent matches.
   *
   * @return a list of recent matches. (status code 200)
   */
  @Operation(
      operationId = "getRecentMatches",
      summary = "Get recent matches.",
      tags = {"matches"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "a list of recent matches.",
            content = {
              @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = MatchDto.class)))
            })
      })
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/matches/recent",
      produces = {"application/json"})
  @ResponseStatus(HttpStatus.OK)
  default List<MatchDto> getRecentMatches() throws Exception {

    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString = "[ null, null ]";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    throw new IllegalArgumentException("Not implemented");
  }
}
