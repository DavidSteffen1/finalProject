package com.promineotech.mwa.service;

import com.promineotech.mwa.entity.Character;
import com.promineotech.mwa.entity.CharacterRequest;

public interface CreateCharacterService {

	Character createNewCharacter(CharacterRequest characterRequest);

}
