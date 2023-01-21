package com.promineotech.mwa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.mwa.controller.DefaultWeaponController;
import com.promineotech.mwa.entity.Weapon;
import com.promineotech.mwa.service.WeaponService;

@RestController
public class DefaultWeaponController implements WeaponController {

  @Autowired
  private WeaponService weaponService;

  public List<Weapon> fetchWeapons() {
	return weaponService.fetchWeapons();  
  }

}
