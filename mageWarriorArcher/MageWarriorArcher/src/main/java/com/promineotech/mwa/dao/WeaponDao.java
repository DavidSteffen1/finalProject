package com.promineotech.mwa.dao;

import java.util.List;
import com.promineotech.mwa.entity.Weapon;

public interface WeaponDao {
	
	List<Weapon> fetchWeapons();

}
