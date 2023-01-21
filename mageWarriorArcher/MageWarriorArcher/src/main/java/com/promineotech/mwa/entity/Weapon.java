package com.promineotech.mwa.entity;

import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor

public class Weapon implements Comparable<Weapon> {
	private int weaponId;
	private String name;
	private int effectiveness;
	
	@Override
	public int compareTo(Weapon that) {
	    // @formatter:off
	    return Comparator
	        .comparing(Weapon::getWeaponId)
	        .thenComparing(Weapon::getName)
	        .thenComparing(Weapon::getEffectiveness)        
	        .compare(this, that);      
	    // @formatter:on
	  }	

}
