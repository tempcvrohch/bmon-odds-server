/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.3.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.rohanc.bmonoddsserver.api;

import com.rohanc.bmonoddsserver.models.dto.BetDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-23T11:29:00.797394723Z[Etc/UTC]")
@Validated
@Tag(name = "user", description = "A registered user")
@RequestMapping("${openapi.betMonitorForGeneratingMatchesAndOdds.base-path:/v2}")
public interface UserApi {

	default Optional<NativeWebRequest> getRequest() {
		return Optional.empty();
	}

	/**
	 * GET /user/bets/pending : Get user pending bets.
	 *
	 * @return The list of bets that are pending. (status code 200)
	 *         or The user was not logged in. (status code 401)
	 */
	@Operation(operationId = "getUserBetsPending", summary = "Get user pending bets.", tags = { "user",
			"bets" }, responses = {
					@ApiResponse(responseCode = "200", description = "The list of bets that are pending.", content = {
							@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BetDto.class)))
					}),
					@ApiResponse(responseCode = "401", description = "The user was not logged in.")
			})
	@RequestMapping(method = RequestMethod.GET, value = "/api/user/bets/pending", produces = { "application/json" })
	@ResponseStatus(HttpStatus.OK)

	default List<BetDto> getUserBetsPending(

	) throws Exception {
		getRequest().ifPresent(request -> {
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
