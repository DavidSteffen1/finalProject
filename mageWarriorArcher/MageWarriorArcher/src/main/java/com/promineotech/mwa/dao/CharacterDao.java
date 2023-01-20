package com.promineotech.mwa.dao;

import java.util.List;
import com.promineotech.mwa.entity.Character;

public interface CharacterDao {
	
	List<Character> fetchCharacters();

}
