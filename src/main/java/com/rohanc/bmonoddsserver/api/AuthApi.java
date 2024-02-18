/* (C)2024 */
package com.rohanc.bmonoddsserver.api;

import com.rohanc.bmonoddsserver.models.dto.UserDto;
import com.rohanc.bmonoddsserver.models.dto.UserRegisterDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    date = "2024-02-18T11:33:56.064195726Z[Etc/UTC]")
@Validated
@Tag(name = "user", description = "A registered user")
@RequestMapping("${openapi.betMonitorForGeneratingMatchesAndOdds.base-path:/v2}")
public interface AuthApi {

  default Optional<NativeWebRequest> getRequest() {
    return Optional.empty();
  }

  /**
   * GET /auth/session : Get current logged-in user.
   *
   * @return a user is logged in and was returned. (status code 200)
   *         or the user was not logged in (status code 401)
   *         or the user account has been closed (status code 403)
   */
  @Operation(
      operationId = "getUserSession",
      summary = "Get current logged-in user.",
      tags = {"user", "auth"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "a user is logged in and was returned.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = UserDto.class))
            }),
        @ApiResponse(responseCode = "401", description = "the user was not logged in"),
        @ApiResponse(responseCode = "403", description = "the user account has been closed")
      })
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/auth/session",
      produces = {"application/json"})
  @ResponseStatus(HttpStatus.OK)
  default UserDto getUserSession() throws Exception {

    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"password\" : \"password\", \"updated_at\" : \"2000-01-23T04:56:07.000+00:00\", \"balance\" : 6, \"created_at\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 0, \"username\" : \"username\" }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    throw new IllegalArgumentException("Not implemented");
  }

  /**
   * POST /auth/register : register a new user.
   *
   * @param userRegisterDto  (required)
   * @return a user is logged in and was returned. (status code 200)
   *         or the username/password has invalid length. (status code 400)
   *         or username was taken (status code 409)
   */
  @Operation(
      operationId = "register",
      summary = "register a new user.",
      tags = {"user", "auth"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "a user is logged in and was returned.",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = UserDto.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description = "the username/password has invalid length."),
        @ApiResponse(responseCode = "409", description = "username was taken")
      })
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/auth/register",
      produces = {"application/json"},
      consumes = {"application/json"})
  @ResponseStatus(HttpStatus.OK)
  default UserDto register(
      @Parameter(name = "UserRegisterDto", description = "", required = true) @Valid @RequestBody
          UserRegisterDto userRegisterDto)
      throws Exception {
    getRequest()
        .ifPresent(
            request -> {
              for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                  String exampleString =
                      "{ \"password\" : \"password\", \"updated_at\" : \"2000-01-23T04:56:07.000+00:00\", \"balance\" : 6, \"created_at\" : \"2000-01-23T04:56:07.000+00:00\", \"id\" : 0, \"username\" : \"username\" }";
                  ApiUtil.setExampleResponse(request, "application/json", exampleString);
                  break;
                }
              }
            });
    throw new IllegalArgumentException("Not implemented");
  }
}