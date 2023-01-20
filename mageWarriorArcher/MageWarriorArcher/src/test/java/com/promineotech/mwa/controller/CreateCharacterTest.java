package com.promineotech.mwa.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import com.promineotech.mwa.MageWarriorArcherApplication;
import com.promineotech.mwa.controller.support.CreateCharacterTestSupport;
import com.promineotech.mwa.entity.Character;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MageWarriorArcherApplication.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) 
@ActiveProfiles("test")
@Sql(scripts = {
	 "classpath:flyway/migrations/Schema_Data.sql",
	 "classpath:flyway/migrations/Table_Data.sql"},
    config = @SqlConfig(encoding = "utf-8"))

class CreateCharacterTest extends CreateCharacterTestSupport{

	@Test
	void testCreateCharacterReturnsSuccess201() {
		//Given: a Character as JSON
		
		// @formatter:off
		String body = "{\n"
				+ "  \"name\":\"Katniss_Everdeen\",\n"
				+ "  \"fightingStyle\":\"ARCHERY\"\n"
				+ "}";
		// @formatter:on
		
		String uri = getBaseUriForCharacterCreation();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> bodyEntity= new HttpEntity<>(body, headers);
		
		//When: the Character is sent
		ResponseEntity<Character> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity, Character.class);
		
		//Then: a 201 status is returned
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		
		//And: the returned Character is correct
		assertThat(response.getBody()).isNotNull();
		
		Character character = response.getBody();
		assertThat(character.getName()).isEqualTo("Katniss_Everdeen");
		assertThat(character.getFightingStyle()).isEqualTo("ARCHERY");		
		
		
	}

}
