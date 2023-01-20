package com.promineotech.mwa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.mwa.entity.Character;
import com.promineotech.mwa.entity.CharacterRequest;
import com.promineotech.mwa.service.CreateCharacterService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultCreateCharacterController implements CreateCharacterController {

	@Autowired
	private CreateCharacterService createCharacterService;
	
	@Override
	public Character createNewCharacter(CharacterRequest characterRequest) {
		log.debug("Character={}", characterRequest);
		return createCharacterService.createNewCharacter(characterRequest);
	}

}
