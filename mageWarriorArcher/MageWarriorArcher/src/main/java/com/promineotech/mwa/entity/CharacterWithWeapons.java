package com.promineotech.mwa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder

@NoArgsConstructor
@AllArgsConstructor

public class CharacterWithWeapons {

	private int character_id;
	private int weapon_id;
	
}
