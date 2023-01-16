package com.promineotech.mwa.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Armor {
	private int armorId;
	private String name;
	private int effectiveness;
	private Location location;

}
