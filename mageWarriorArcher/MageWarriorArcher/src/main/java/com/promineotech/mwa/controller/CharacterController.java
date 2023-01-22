package com.promineotech.mwa.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotech.mwa.entity.Character;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RequestMapping("/characters")

@OpenAPIDefinition(info = @Info(title = "Character List"), servers = {
		@Server(url = "http://localhost:8080", description = "Local server.") })

public interface CharacterController {
	//@formatter:off
@Operation(
      summary = "Returns a list of all Characters",
      description = "Returns a list of all Characters",
      responses = {
          @ApiResponse(responseCode = "200", 
                       description = "A list of Characters is returned", 
                       content = @Content(mediaType = "application/json", 
                       schema = @Schema(implementation = Character.class))),
          @ApiResponse(responseCode = "400", 
                       description = "The request parameters are invalid", 
                       content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404", 
                       description = "No Characters were found", 
                       content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
                       description = "An unplanned error ocurred.", 
                       content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(name = "character_id", 
                     allowEmptyValue = false, 
                     required = false, 
                     description = "The character id"),
      }
  )

  @GetMapping(path="/all")
  @ResponseStatus(code = HttpStatus.OK)
  public List<Character> fetchCharacters();


  @GetMapping(value = "/{character_id}")
  @ResponseStatus(code = HttpStatus.OK)
  public Character fetchCharacterById(@PathVariable("character_id") int character_id);


/**
 * @return 
 * @PostMapping for characters
 */
@Operation(
		summary = "Create a new Character",
		description = "Returns the created Character",
		responses = {
				@ApiResponse(responseCode = "201", 
						description = "A newly created Character is returned", 
						content = @Content(mediaType = "application/json", 
						schema = @Schema(implementation = Character.class))),
				@ApiResponse(responseCode = "400", 
				description = "The Character is invalid", 
				content = @Content(mediaType = "application/json")),
				@ApiResponse(responseCode = "404", 
				description = "No Characters were found", 
				content = @Content(mediaType = "application/json")),
				@ApiResponse(responseCode = "500", 
				description = "An unplanned error ocurred.", 
				content = @Content(mediaType = "application/json"))
		},
		parameters = {
				@Parameter(name = "name", 
						allowEmptyValue = false, 
						required = true, 
						description = "The Character name"),

				@Parameter(name = "fightingStyle", 
				allowEmptyValue = false, 
				required = false, 
				description = "The Characters fighting style")
		}
		)

@PostMapping(path = "/new")
@ResponseStatus(code = HttpStatus.CREATED)
  public Character createNewCharacter(@RequestBody String name);

/**
 * @return 
 * @DeleteMapping for characters
 */
@Operation(
		summary = "Delete a Character",
		description = "Deletes the selected Character",
		responses = {
				@ApiResponse(responseCode = "201", 
						description = "A selected Character is deleted", 
						content = @Content(mediaType = "application/json", 
						schema = @Schema(implementation = Character.class))),
				@ApiResponse(responseCode = "400", 
				description = "The Character is invalid", 
				content = @Content(mediaType = "application/json")),
				@ApiResponse(responseCode = "404", 
				description = "No Characters were found", 
				content = @Content(mediaType = "application/json")),
				@ApiResponse(responseCode = "500", 
				description = "An unplanned error ocurred.", 
				content = @Content(mediaType = "application/json"))
		},
		parameters = {
				@Parameter(name = "name", 
						allowEmptyValue = false, 
						required = true, 
						description = "The Character name"),

		}
		)

@PostMapping(path = "/delete")
@ResponseStatus(code = HttpStatus.CREATED)
  public Character deleteCharacter(@RequestBody String name);
}
