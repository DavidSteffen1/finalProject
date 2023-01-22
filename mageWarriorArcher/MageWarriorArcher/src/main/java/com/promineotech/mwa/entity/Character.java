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
  
  private int character_id;  
  private String name;  
  private String fightingStyle;

  
  @Override
public int compareTo(Character that) {
    // @formatter:off
    return Comparator
        .comparing(Character::getCharacter_id)
        .thenComparing(Character::getName)
        .thenComparing(Character::getFightingStyle)
        .compare(this, that);      
    // @formatter:on
  }
  
  public Character(String name) {}
  
  public Character(String name, String fightingStyle) {}
  
}