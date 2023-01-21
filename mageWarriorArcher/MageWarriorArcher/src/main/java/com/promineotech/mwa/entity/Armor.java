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

public class Armor implements Comparable<Armor>  {
	private int armorId;
	private String name;
	private int effectiveness;
	private String location;
	
	@Override
	public int compareTo(Armor that) {
	    // @formatter:off
	    return Comparator
	        .comparing(Armor::getArmorId)
	        .thenComparing(Armor::getName)
	        .thenComparing(Armor::getEffectiveness)
	        .thenComparing(Armor::getLocation)	        
	        .compare(this, that);      
	    // @formatter:on
	  }	

}
