package com.promineotech.mwa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.mwa.controller.DefaultArmorController;
import com.promineotech.mwa.entity.Armor;
import com.promineotech.mwa.service.ArmorService;

@RestController
public class DefaultArmorController implements ArmorController {

  @Autowired
  private ArmorService armorService;

  public List<Armor> fetchArmor() {
	return armorService.fetchArmor();  
  }

}
