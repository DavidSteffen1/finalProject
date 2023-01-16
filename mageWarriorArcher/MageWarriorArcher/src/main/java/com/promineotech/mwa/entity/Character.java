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

public class Character implements Comparable<Character> {
  private int characterId;  
  private String name;  
  private FightingStyle fightingStyle;

@Override
public int compareTo(Character that) {
    // @formatter:off
    return Comparator
        .comparing(Character::getCharacterId)
        .thenComparing(Character::getName)
        .thenComparing(Character::getFightingStyle)
        .compare(this, that);      
    // @formatter:on
  }
}