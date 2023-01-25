package com.promineotech.mwa.dao;

import java.util.List;
import com.promineotech.mwa.entity.Character;
import com.promineotech.mwa.entity.CharacterWithWeapons;

public interface CharacterDao {
	
	List<Character> fetchCharacters();
	
	List<Character> fetchCharacterByName(String name);

	Character createNewCharacter(String name);

	List<Character> fetchCharacterById(int character_id);

	String deleteCharacter(String name);

	String updateCharacterName(String name, String newName);

	List<CharacterWithWeapons> fetchCharactersWithWeapons();

}
