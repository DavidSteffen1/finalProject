package com.promineotech.mwa.service;

import java.util.List;
import com.promineotech.mwa.entity.Character;

public interface CharacterService {
	
	List<Character> fetchCharacters();
	
	Character createNewCharacter(String name);

	Character fetchCharacterById(Integer characterId);

}
