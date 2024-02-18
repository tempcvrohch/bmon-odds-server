/* (C)2024 */
package com.rohanc.bmonoddsserver.api;

import com.rohanc.bmonoddsserver.models.dto.LeagueDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Generated;
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
    date = "2024-02-18T11:33:56.064195726Z[Etc/UTC]")
@Validated
@Tag(name = "leagues", description = "the leagues API")
@RequestMapping("${openapi.betMonitorForGeneratingMatchesAndOdds.base-path:/v2}")
public interface LeaguesApi {

  default Optional<NativeWebRequest> getRequest() {
    return Optional.empty();
  }

  /**
   * GET /leagues : Get all leagues.
   *
   * @return a list of leagues (status code 200)
   */
  @Operation(
      operationId = "getAllLeagues",
      summary = "Get all leagues.",
      tags = {"leagues"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "a list of leagues",
            content = {
              @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = LeagueDto.class)))
            })
      })
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/leagues",
      produces = {"application/json"})
  @ResponseStatus(HttpStatus.OK)
  default List<LeagueDto> getAllLeagues() throws Exception {

    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "[ { \"updated_at\" : \"2000-01-23T04:56:07.000+00:00\", \"name\" : \"name\", \"created_at\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 6 }, { \"updated_at\" : \"2000-01-23T04:56:07.000+00:00\", \"name\" : \"name\", \"created_at\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 6 } ]";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    throw new IllegalArgumentException("Not implemented");
  }
}
