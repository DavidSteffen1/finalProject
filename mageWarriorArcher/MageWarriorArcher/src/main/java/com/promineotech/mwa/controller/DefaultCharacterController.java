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
public String deleteCharacter(String name) {
	return characterService.deleteCharacter(name); 
}

@Override
public Character updateCharacter(String name, String newName) {
	return characterService.updateCharacter(name, newName);
}

@Override
public Character fetchCharacter(String name) {
	return characterService.fetchCharacter(name);
}

@Override
public List<Character> fetchCharactersWithWeapons() {
	// Build out list from return statement
	return null;
}

}
