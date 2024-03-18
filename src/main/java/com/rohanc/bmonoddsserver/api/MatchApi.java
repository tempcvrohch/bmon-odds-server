/* (C)2024 */
package com.rohanc.bmonoddsserver.api;

import com.rohanc.bmonoddsserver.models.dto.MarketStateDto;
import com.rohanc.bmonoddsserver.models.dto.MatchDto;
import com.rohanc.bmonoddsserver.models.dto.MatchUpsertDto;
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
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2024-03-17T12:07:05.351636620Z[Etc/UTC]")
@Validated
@Tag(name = "match", description = "the match API")
@RequestMapping("${openapi.betMonitorForGeneratingMatchesAndOdds.base-path:/v2}")
public interface MatchApi {

  default Optional<NativeWebRequest> getRequest() {
    return Optional.empty();
  }

  /**
   * POST /match : Create a new match
   *
   * @param matchUpsertDto (required)
   * @return A match, matchState and marketStates were successfully created.
   *         (status code 200)
   */
  @Operation(
      operationId = "createMatch",
      summary = "Create a new match",
      tags = {"match"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "A match, matchState and marketStates were successfully created.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = MatchDto.class))
            })
      })
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/match",
      produces = {"application/json"},
      consumes = {"application/json"})
  @ResponseStatus(HttpStatus.OK)
  default MatchDto createMatch(
      @Parameter(name = "MatchUpsertDto", description = "", required = true) @Valid @RequestBody
          MatchUpsertDto matchUpsertDto)
      throws Exception {
    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"matchStates\" : [ { \"updated_at\" : \"2000-01-23T04:56:07.000+00:00\", \"marketStates\" : [ null, null ], \"created_at\" : \"2000-01-23T04:56:07.000+00:00\", \"pointScore\" : \"pointScore\", \"id\" : 1, \"servingIndex\" : 0, \"setScore\" : \"setScore\" }, { \"updated_at\" : \"2000-01-23T04:56:07.000+00:00\", \"marketStates\" : [ null, null ], \"created_at\" : \"2000-01-23T04:56:07.000+00:00\", \"pointScore\" : \"pointScore\", \"id\" : 1, \"servingIndex\" : 0, \"setScore\" : \"setScore\" } ], \"updated_at\" : \"2000-01-23T04:56:07.000+00:00\", \"league\" : { \"updated_at\" : \"2000-01-23T04:56:07.000+00:00\", \"name\" : \"name\", \"created_at\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 6 }, \"name\" : \"name\", \"matchState\" : { \"updated_at\" : \"2000-01-23T04:56:07.000+00:00\", \"marketStates\" : [ null, null ], \"created_at\" : \"2000-01-23T04:56:07.000+00:00\", \"pointScore\" : \"pointScore\", \"id\" : 1, \"servingIndex\" : 0, \"setScore\" : \"setScore\" }, \"created_at\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 0, \"live\" : true }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    throw new IllegalArgumentException("Not implemented");
  }

  /**
   * GET /match/{id}/markets/latest : Get the latest market states on match.
   *
   * @param id id of the match (required)
   * @return the latest markets (status code 200)
   */
  @Operation(
      operationId = "getLatestMarketsByMatchId",
      summary = "Get the latest market states on match.",
      tags = {"markets"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "the latest markets",
            content = {
              @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = MarketStateDto.class)))
            })
      })
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/match/{id}/markets/latest",
      produces = {"application/json"})
  @ResponseStatus(HttpStatus.OK)
  default List<MarketStateDto> getLatestMarketsByMatchId(
      @Parameter(
              name = "id",
              description = "id of the match",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("id")
          Long id)
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
   * GET /match/{id} : Get match on id.
   *
   * @param id Id of match to return. (required)
   * @return the found match (status code 200)
   */
  @Operation(
      operationId = "getMatchById",
      summary = "Get match on id.",
      tags = {"match"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "the found match",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = MatchDto.class))
            })
      })
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/match/{id}",
      produces = {"application/json"})
  @ResponseStatus(HttpStatus.OK)
  default MatchDto getMatchById(
      @Parameter(
              name = "id",
              description = "Id of match to return.",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("id")
          Long id)
      throws Exception {
    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"matchStates\" : [ { \"updated_at\" : \"2000-01-23T04:56:07.000+00:00\", \"marketStates\" : [ null, null ], \"created_at\" : \"2000-01-23T04:56:07.000+00:00\", \"pointScore\" : \"pointScore\", \"id\" : 1, \"servingIndex\" : 0, \"setScore\" : \"setScore\" }, { \"updated_at\" : \"2000-01-23T04:56:07.000+00:00\", \"marketStates\" : [ null, null ], \"created_at\" : \"2000-01-23T04:56:07.000+00:00\", \"pointScore\" : \"pointScore\", \"id\" : 1, \"servingIndex\" : 0, \"setScore\" : \"setScore\" } ], \"updated_at\" : \"2000-01-23T04:56:07.000+00:00\", \"league\" : { \"updated_at\" : \"2000-01-23T04:56:07.000+00:00\", \"name\" : \"name\", \"created_at\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 6 }, \"name\" : \"name\", \"matchState\" : { \"updated_at\" : \"2000-01-23T04:56:07.000+00:00\", \"marketStates\" : [ null, null ], \"created_at\" : \"2000-01-23T04:56:07.000+00:00\", \"pointScore\" : \"pointScore\", \"id\" : 1, \"servingIndex\" : 0, \"setScore\" : \"setScore\" }, \"created_at\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 0, \"live\" : true }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    throw new IllegalArgumentException("Not implemented");
  }

  /**
   * PUT /match/{id} : Update a live match with a new matchState
   *
   * @param id             Id of match to update. (required)
   * @param matchUpsertDto (required)
   * @return The match was updated and new matchstate and marketstates inserted.
   *         (status code 200)
   */
  @Operation(
      operationId = "updateMatchAndStates",
      summary = "Update a live match with a new matchState",
      tags = {"match"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "The match was updated and new matchstate and marketstates inserted.")
      })
  @RequestMapping(
      method = RequestMethod.PUT,
      value = "/match/{id}",
      consumes = {"application/json"})
  @ResponseStatus(HttpStatus.OK)
  default void updateMatchAndStates(
      @Parameter(
              name = "id",
              description = "Id of match to update.",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("id")
          Long id,
      @Parameter(name = "MatchUpsertDto", description = "", required = true) @Valid @RequestBody
          MatchUpsertDto matchUpsertDto)
      throws Exception {
    throw new IllegalArgumentException("Not implemented");
  }
}
