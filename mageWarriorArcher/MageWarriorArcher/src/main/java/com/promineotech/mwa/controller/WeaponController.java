package com.promineotech.mwa.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotech.mwa.entity.Weapon;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RequestMapping("/weapons")

@OpenAPIDefinition(info = @Info(title = "Weapon List"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})

public interface WeaponController {
  //@formatter:off
@Operation(
      summary = "Returns a list of all Weapons",
      description = "Returns a list of all Weapons able to be equipped by Characters",
      responses = {
          @ApiResponse(responseCode = "200", 
                       description = "A list of Weapons is returned", 
                       content = @Content(mediaType = "application/json", 
                       schema = @Schema(implementation = Weapon.class))),
          @ApiResponse(responseCode = "400", 
                       description = "The request parameters are invalid", 
                       content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404", 
                       description = "No Weapons were found", 
                       content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
                       description = "An unplanned error ocurred.", 
                       content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(name = "weapon_id", 
                     allowEmptyValue = false, 
                     required = false, 
                     description = "The weapon id"),
      }
  )

  @GetMapping()
  @ResponseStatus(code = HttpStatus.OK)
  public List<Weapon> fetchWeapons();

}
