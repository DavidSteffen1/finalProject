package com.promineotech.mwa.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotech.mwa.entity.Armor;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RequestMapping("/armor")

@OpenAPIDefinition(info = @Info(title = "Armor List"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})

public interface ArmorController {
  //@formatter:off
@Operation(
      summary = "Returns a list of all armor pieces",
      description = "Returns a list of all armor able to be equipped to Characters",
      responses = {
          @ApiResponse(responseCode = "200", 
                       description = "A list of Armor is returned", 
                       content = @Content(mediaType = "application/json", 
                       schema = @Schema(implementation = Armor.class))),
          @ApiResponse(responseCode = "400", 
                       description = "The request parameters are invalid", 
                       content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404", 
                       description = "No Armor was found", 
                       content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
                       description = "An unplanned error ocurred.", 
                       content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(name = "armor_id", 
                     allowEmptyValue = false, 
                     required = false, 
                     description = "The armor id"),
      }
  )

  @GetMapping()
  @ResponseStatus(code = HttpStatus.OK)
  public List<Armor> fetchArmor();

}
