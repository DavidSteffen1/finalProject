package com.promineotech.mwa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.promineotech.mwa.entity.Weapon;

@Component
@Repository

public class DefaultWeaponDao extends DaoBase implements WeaponDao {

	  @Autowired
	  private NamedParameterJdbcTemplate jdbcTemplate;
	
	  @Override
	  public List<Weapon> fetchWeapons() {
			String sql = "SELECT * FROM weapons ORDER BY weapon_id";
			
		    Map<String,Object> parameters = new HashMap<>();

		    List<Weapon> weapons = jdbcTemplate.query(sql, parameters, new RowMapper<>() {

		        @Override
		        public Weapon mapRow(ResultSet rs, int rowNum) throws SQLException {
		          // @formatter:off
		          return Weapon.builder()
		              .weaponId((rs.getInt("weapon_id")))
		              .name(rs.getString("name"))
		              .effectiveness(rs.getInt("effectiveness"))
		              .build();
		          // @formatter:on
		        	}
		        });
			return weapons;
		    }

	}