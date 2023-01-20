package com.promineotech.mwa.service;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
