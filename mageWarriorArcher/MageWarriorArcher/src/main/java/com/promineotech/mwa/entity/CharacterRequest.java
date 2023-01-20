package com.promineotech.mwa.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data

public class CharacterRequest {
	
	@NotNull
	private Integer characterId; 
	@NotNull
	@Pattern(regexp = "[\\w\\s]*")
	private String name;
	private String fightingStyle;
}
