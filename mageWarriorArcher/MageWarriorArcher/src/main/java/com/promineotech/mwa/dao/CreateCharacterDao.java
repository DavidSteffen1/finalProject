package com.promineotech.mwa.dao;

import com.promineotech.mwa.entity.Character;

public interface CreateCharacterDao {

	Character fetchCharacter(String name, String fightingStyle);

	Character saveCharacter(String name, String fightingStyle);

}
