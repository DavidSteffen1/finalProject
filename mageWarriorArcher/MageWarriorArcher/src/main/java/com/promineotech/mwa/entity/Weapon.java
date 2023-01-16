package com.promineotech.mwa.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Weapon {
	private int weaponId;
	private String name;
	private int effectiveness;

}
