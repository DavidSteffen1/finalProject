package com.promineotech.mwa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.mwa.controller.DefaultCharacterController;
import com.promineotech.mwa.entity.Character;
import com.promineotech.mwa.service.CharacterService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultCharacterController implements CharacterController {

  @Autowired
  private CharacterService characterService;

  public List<Character> fetchCharacters() {
	return characterService.fetchCharacters();  
  }
}
