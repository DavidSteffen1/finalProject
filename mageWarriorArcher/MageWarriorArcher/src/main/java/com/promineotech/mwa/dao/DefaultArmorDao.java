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
import com.promineotech.mwa.entity.Armor;

@Component
@Repository

public class DefaultArmorDao extends DaoBase implements ArmorDao {
	  
	  @Autowired
	  private NamedParameterJdbcTemplate jdbcTemplate;
	
	  @Override
	  public List<Armor> fetchArmor() {
			String sql = "SELECT * FROM armor ORDER BY armor_id";
			
		    Map<String,Object> parameters = new HashMap<>();

		    List<Armor> armor = jdbcTemplate.query(sql, parameters, new RowMapper<>() {

		        @Override
		        public Armor mapRow(ResultSet rs, int rowNum) throws SQLException {
		          // @formatter:off
		          return Armor.builder()
		              .armorId((rs.getInt("armor_id")))
		              .name(rs.getString("name"))
		              .effectiveness(rs.getInt("effectiveness"))
		              .location(rs.getString("location"))
		              .build();
		          // @formatter:on
		        	}
		        });
			return armor;
		    }

	}