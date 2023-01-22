package com.promineotech.mwa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.mwa.controller.DefaultCharacterController;
import com.promineotech.mwa.entity.Character;
import com.promineotech.mwa.service.CharacterService;

@RestController
public class DefaultCharacterController implements CharacterController {

  @Autowired
  private CharacterService characterService;

  public List<Character> fetchCharacters() {
	return characterService.fetchCharacters();  
  }
  
  @Override
  public Character createNewCharacter(String name) {
	return characterService.createNewCharacter(name);
  }

  @Override
  public Character fetchCharacterById(int character_id) {
	return characterService.fetchCharacterById(character_id);
}

@Override
public Character deleteCharacter(String name) {
	// TODO Auto-generated method stub
	return null;
}

}
