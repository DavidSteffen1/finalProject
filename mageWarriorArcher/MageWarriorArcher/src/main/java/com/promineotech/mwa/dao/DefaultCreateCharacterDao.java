package com.promineotech.mwa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.promineotech.mwa.entity.Character;

@Component
public class DefaultCreateCharacterDao implements CreateCharacterDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public Character saveCharacter(String name, String fightingStyle) {
		SqlParams params = generateInsertSql(name, fightingStyle);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(params.sql, params.source, keyHolder);
		
		int characterId = keyHolder.getKey().intValue();
		
		// @formatter:off
		return Character.builder()
				.characterId(characterId)
				.name(name)
				.fightingStyle(fightingStyle)
				.build();
		// @formatter:on
	}
	
	private SqlParams generateInsertSql(String name, String fightingStyle) {
		// @formatter:off
		String sql = ""
				+ "INSERT INTO `character` ("
				+ "name, fightingStyle"
				+ ") VALUES ("
				+ ":name, :fightingStyle"
				+ ")";
		// @formatter:on
		
		SqlParams params = new SqlParams();
		
		params.sql = sql;
		params.source.addValue("name", name);
		params.source.addValue("fightingStyle", fightingStyle);
		
		return params;
	}

	@Override
	public Character fetchCharacter(String name) {
		String sql = "SELECT * FROM `character` WHERE name = :name";
		
		Map<String, Object> params = new HashMap<>();
		params.put("name", name);
	
		return jdbcTemplate.query(sql, params, new CharacterResultSetExtractor());
	}
	
	class CharacterResultSetExtractor implements ResultSetExtractor<Character> {

		@Override
		public Character extractData(ResultSet rs) 
			throws SQLException, DataAccessException {
			rs.next();
		
			// @formatter:off
			return Character.builder()
					.name(rs.getString("name"))
					.fightingStyle(rs.getString("fightingStyle"))
					.build();
			
			// @formatter:on
		}
		
	}
	
	class SqlParams {
		String sql;
		MapSqlParameterSource source = new MapSqlParameterSource();
	}

}
