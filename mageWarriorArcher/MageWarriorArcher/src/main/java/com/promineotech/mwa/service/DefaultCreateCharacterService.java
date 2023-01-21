package com.promineotech.mwa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promineotech.mwa.dao.CreateCharacterDao;
import com.promineotech.mwa.entity.Character;
import com.promineotech.mwa.entity.CharacterRequest;

@Service
public class DefaultCreateCharacterService implements CreateCharacterService {

	@Autowired
	private CreateCharacterDao createCharacterDao;
	
	@Transactional
	@Override
	public Character createNewCharacter(CharacterRequest characterRequest) {
		
		return createCharacterDao.saveCharacter(characterRequest.getName(), characterRequest.getFightingStyle());
	}

}
