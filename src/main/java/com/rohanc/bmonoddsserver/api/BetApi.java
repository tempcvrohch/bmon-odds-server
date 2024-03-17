/* (C)2024 */
package com.rohanc.bmonoddsserver.api;

import com.rohanc.bmonoddsserver.models.dto.BetDto;
import com.rohanc.bmonoddsserver.models.dto.BetPlaceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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
@Tag(name = "bet", description = "the bet API")
@RequestMapping("${openapi.betMonitorForGeneratingMatchesAndOdds.base-path:/v2}")
public interface BetApi {

  default Optional<NativeWebRequest> getRequest() {
    return Optional.empty();
  }

  /**
   * POST /bet/place/{marketStateId} : Place a wager.
   *
   * @param marketStateId Id of the market state to place the bet on. (required)
   * @param betPlaceDto  (required)
   * @return A bet was successfully placed and balance was substracted. (status code 200)
   *         or The bet was already placed or insufficient funds or stake out of bounds or unknown market. (status code 400)
   */
  @Operation(
      operationId = "placeBet",
      summary = "Place a wager.",
      tags = {"bet"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "A bet was successfully placed and balance was substracted.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = BetDto.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description =
                "The bet was already placed or insufficient funds or stake out of bounds or unknown market.")
      })
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/bet/place/{marketStateId}",
      produces = {"application/json"},
      consumes = {"application/json"})
  @ResponseStatus(HttpStatus.OK)
  default BetDto placeBet(
      @Parameter(
              name = "marketStateId",
              description = "Id of the market state to place the bet on.",
              required = true,
              in = ParameterIn.PATH)
          @PathVariable("marketStateId")
          Long marketStateId,
      @Parameter(name = "BetPlaceDto", description = "", required = true) @Valid @RequestBody
          BetPlaceDto betPlaceDto)
      throws Exception {
    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"stake\" : 0, \"updated_at\" : \"2000-01-23T04:56:07.000+00:00\", \"toReturn\" : 1.4658129, \"created_at\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 0 }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    throw new IllegalArgumentException("Not implemented");
  }
}
