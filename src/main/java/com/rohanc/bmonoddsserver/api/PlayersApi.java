/* (C)2024 */
package com.rohanc.bmonoddsserver.api;

import com.rohanc.bmonoddsserver.models.dto.PlayerDto;
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
    date = "2024-02-23T11:29:00.797394723Z[Etc/UTC]")
@Validated
@Tag(name = "players", description = "the players API")
@RequestMapping("${openapi.betMonitorForGeneratingMatchesAndOdds.base-path:/v2}")
public interface PlayersApi {

  default Optional<NativeWebRequest> getRequest() {
    return Optional.empty();
  }

  /**
   * GET /players : Get all players.
   *
   * @return a list of players. (status code 200)
   */
  @Operation(
      operationId = "getAllPlayers",
      summary = "Get all players.",
      tags = {"players"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "a list of players.",
            content = {
              @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = PlayerDto.class)))
            })
      })
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/api/players",
      produces = {"application/json"})
  @ResponseStatus(HttpStatus.OK)
  default List<PlayerDto> getAllPlayers() throws Exception {

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
