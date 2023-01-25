package com.promineotech.mwa.service;

import java.util.List;
import com.promineotech.mwa.entity.Character;
import com.promineotech.mwa.entity.CharacterWithWeapons;

public interface CharacterService {
	
	List<Character> fetchCharacters();
	
	Character createNewCharacter(String name);

	List<Character> fetchCharacterById(Integer characterId);

	String deleteCharacter(String name);

	String updateCharacterName(String name, String newName);

	List<Character> fetchCharacterByName(String name);

	List<CharacterWithWeapons> fetchCharactersWithWeapons();

}
