package com.promineotech.mwa.dao;

import java.util.List;
import com.promineotech.mwa.entity.Character;

public interface CharacterDao {
	
	List<Character> fetchCharacters();
	
	Character fetchCharacter(String name);

	Character createNewCharacter(String name);

	Character fetchCharacterById(int character_id);

	String deleteCharacter(String name);

	Character updateCharacter(String name, String newName);

}
