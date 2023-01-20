package com.promineotech.mwa.dao;

import com.promineotech.mwa.entity.Character;

public interface CreateCharacterDao {

	Character fetchCharacter(String name);

	Character saveCharacter(String name, String fightingStyle);

}
