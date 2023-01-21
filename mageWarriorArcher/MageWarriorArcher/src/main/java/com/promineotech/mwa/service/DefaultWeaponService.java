package com.promineotech.mwa.service;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.mwa.dao.WeaponDao;
import com.promineotech.mwa.entity.Weapon;

@Service
public class DefaultWeaponService implements WeaponService {

	  @Autowired
	  private WeaponDao weaponDao;
	  
	  @Override
	  public List<Weapon> fetchWeapons() {
	    List<Weapon> weapons = weaponDao.fetchWeapons();
	    
	    Collections.sort(weapons);
	    return weapons;
	  }

}
