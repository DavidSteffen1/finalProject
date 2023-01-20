package com.promineotech.mwa.controller.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import lombok.Getter;

@SuppressWarnings("deprecation")
public class BaseTest {
  @LocalServerPort
  private int serverPort;
  
  @Autowired
  @Getter
  protected TestRestTemplate restTemplate;
  
  protected String getBaseUriForCharacters() {
    return String.format("http://localhost:%d/characters", serverPort);
  }
  
  protected String getBaseUriForCharacterCreation() {
	    return String.format("http://localhost:%d/create", serverPort);
	  }
}
