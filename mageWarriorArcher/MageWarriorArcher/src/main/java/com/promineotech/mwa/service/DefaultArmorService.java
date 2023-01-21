package com.promineotech.mwa.service;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.mwa.dao.ArmorDao;
import com.promineotech.mwa.entity.Armor;

@Service
public class DefaultArmorService implements ArmorService {

	  @Autowired
	  private ArmorDao armorDao;
	  
	  @Override
	  public List<Armor> fetchArmor() {
	    List<Armor> armor = armorDao.fetchArmor();
	    
	    Collections.sort(armor);
	    return armor;
	  }

}
