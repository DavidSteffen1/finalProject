package com.promineotech.mwa.dao;

import java.util.List;
import com.promineotech.mwa.entity.Character;

public interface CharacterDao {
	
	public static final String CHARACTER_TABLE = "`character`";
	
	List<Character> fetchCharacters();

}
