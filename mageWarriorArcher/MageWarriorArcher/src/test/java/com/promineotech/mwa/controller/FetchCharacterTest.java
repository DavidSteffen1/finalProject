package com.promineotech.mwa.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.promineotech.mwa.controller.support.BaseTest;
import com.promineotech.mwa.entity.Character;
import org.springframework.http.HttpMethod;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) 
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:flyway/migrations/Schema_Data.sql",
    "classpath:flyway/migrations/Table_Data.sql"},
    config = @SqlConfig(encoding = "utf-8"))

class FetchCharacterTest extends BaseTest {

@Test
  void testThatCharacterIsReturnedWhenAValidCharacterIdIsSupplied() {
    //Given: a valid character_id
	Integer characterId = 1;
	String uri = String.format("%s?characterId=%s", getBaseUri(), characterId);
    
    //When: a connection is made to the URI
    ResponseEntity<List<Character>> response = getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
    
    //Then: the character is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    
    //And: the actual character returned is the same as the expected character
    List<Character> actual = response.getBody();
    List<Character> expected = buildExpected();
    
    assertThat(actual).isEqualTo(expected);
  }

  protected List<Character> buildExpected() {
    List<Character> list = new LinkedList<>();
    
    //@formatter:off
    list.add(Character.builder()
        .characterId(1)
        .name("Gandalf")
        .fightingStyle("MAGIC")
        .build());
    
    //@formatter:on
    
    Collections.sort(list);
    
    return list;
  }

}
