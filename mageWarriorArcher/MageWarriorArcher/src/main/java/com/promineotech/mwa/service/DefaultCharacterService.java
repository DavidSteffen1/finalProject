package com.promineotech.mwa.service;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promineotech.mwa.dao.CharacterDao;
import com.promineotech.mwa.entity.Character;

@Service
public class DefaultCharacterService implements CharacterService {

	  @Autowired
	  private CharacterDao characterDao;
	  
	  @Override
	  public List<Character> fetchCharacters() {
	    List<Character> characters = characterDao.fetchCharacters();
	    
	    Collections.sort(characters);
	    return characters;
	  }
	  
		
		@Transactional
		@Override
		public Character createNewCharacter(String name) {
			return characterDao.createNewCharacter(name);
		}


		@Override
		public Character fetchCharacterById(Integer characterId) {
			return characterDao.fetchCharacterById(characterId);
		}


		@Override
		public void deleteCharacter(String name) {
			characterDao.deleteCharacter(name);
		}

}
