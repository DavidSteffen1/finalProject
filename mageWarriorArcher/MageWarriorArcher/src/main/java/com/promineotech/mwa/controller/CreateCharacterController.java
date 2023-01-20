package com.promineotech.mwa.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotech.mwa.entity.Character;
import com.promineotech.mwa.entity.CharacterRequest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;


@RequestMapping("/create")

@OpenAPIDefinition(info = @Info(title = "Character Creater"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})

public interface CreateCharacterController {

	  /**
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

	  @PostMapping
	  @ResponseStatus(code = HttpStatus.CREATED)
	  public Character createNewCharacter(@Valid @RequestBody CharacterRequest characterRequest);
	  //@formatter:on

}
