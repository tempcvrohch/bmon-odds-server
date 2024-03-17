/* (C)2024 */
package com.rohanc.bmonoddsserver.api;

import com.rohanc.bmonoddsserver.models.dto.SportDto;
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
    date = "2024-03-17T12:07:05.351636620Z[Etc/UTC]")
@Validated
@Tag(name = "sports", description = "the sports API")
@RequestMapping("${openapi.betMonitorForGeneratingMatchesAndOdds.base-path:/v2}")
public interface SportsApi {

  default Optional<NativeWebRequest> getRequest() {
    return Optional.empty();
  }

  /**
   * GET /sports : Get all sports.
   *
   * @return A list of sports. (status code 200)
   */
  @Operation(
      operationId = "getAllSports",
      summary = "Get all sports.",
      tags = {"sports"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "A list of sports.",
            content = {
              @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = SportDto.class)))
            })
      })
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/sports",
      produces = {"application/json"})
  @ResponseStatus(HttpStatus.OK)
  default List<SportDto> getAllSports() throws Exception {

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
