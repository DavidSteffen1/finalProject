package com.promineotech.mwa.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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

@OpenAPIDefinition(info = @Info(title = "MageWarriorArcher project"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})

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
  List<Character> fetchCharacters();
  //@formatter:on
}
