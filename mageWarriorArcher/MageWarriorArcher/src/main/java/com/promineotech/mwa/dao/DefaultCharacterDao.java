package com.promineotech.mwa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.promineotech.mwa.entity.Character;
import com.promineotech.mwa.entity.CharacterWithWeapons;

@Component
@Repository
public class DefaultCharacterDao extends DaoBase implements CharacterDao {
	  
	  @Autowired
	  private NamedParameterJdbcTemplate jdbcTemplate;
	  
	  	@Override
		public Character createNewCharacter(String name) {
			SqlParams params = generateInsertSql(name);
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(params.sql, params.source, keyHolder);
			
			int characterId = keyHolder.getKey().intValue();
			
			// @formatter:off
			return Character.builder()
					.character_id(characterId)
					.name(name)
					.build();
			// @formatter:on
		}

		private SqlParams generateInsertSql(String name) {
			// @formatter:off
			String sql = ""
					+ "INSERT INTO `character` ("
					+ "name"
					+ ") VALUES ("
					+ ":name"
					+ ")";
			// @formatter:on
			
			SqlParams params = new SqlParams();
			
			params.sql = sql;
			params.source.addValue("name", name);
			
			return params;
		}		
		
		 
		  /**
		 *Fetches all characters
		 */
		@Override
		  public List<Character> fetchCharacters() {
				String sql = "SELECT * FROM `character` ORDER BY character_id";
				
			    Map<String,Object> parameters = new HashMap<>();

			    List<Character> characters = jdbcTemplate.query(sql, parameters, new RowMapper<>() {

			        @Override
			        public Character mapRow(ResultSet rs, int rowNum) throws SQLException {
			          // @formatter:off
			          return Character.builder()
			              .character_id((rs.getInt("character_id")))
			              .name(rs.getString("name"))
			              .build();
			          // @formatter:on
			        	}
			        });
				return characters;
			    }
		
		/**
		 *Fetches Character by name
		 *CharacterResultsSetExtractor?
		 *Builder?
		 */
		@Override
		public List<Character> fetchCharacterByName(String name) {
			String sql = "SELECT * FROM `character` WHERE name = :name";

		    Map<String,Object> parameters = new HashMap<>();
		    parameters.put("name", name);

		    List<Character> characters = jdbcTemplate.query(sql, parameters, new RowMapper<>() {

		        @Override
		        public Character mapRow(ResultSet rs, int rowNum) throws SQLException {
		          // @formatter:off
		          return Character.builder()
		              .character_id((rs.getInt("character_id")))
		              .name(rs.getString("name"))
		              .build();
		          // @formatter:on
		        	}
		        });
			return characters;
		    }

		  /**
		 *Fetches Character by Id
		 *Returns SQL syntax error
		 */
		@Override
		  public List<Character> fetchCharacterById(int character_id) {
				String sql = "SELECT * FROM `character` WHERE character_id = :character_id";		
				
			    Map<String,Object> parameters = new HashMap<>();
			    parameters.put("character_id", character_id);

			    List<Character> characters = jdbcTemplate.query(sql, parameters, new RowMapper<>() {

			        @Override
			        public Character mapRow(ResultSet rs, int rowNum) throws SQLException {
			          // @formatter:off
			          return Character.builder()
			              .character_id((rs.getInt("character_id")))
			              .name(rs.getString("name"))
			              .build();
			          // @formatter:on
			        	}
			        });
				return characters;
			    }


		/**
		 *Updates a character by name
		 */
		@Override
		public String updateCharacterName(String name, String newName) {
			//Character characterToUpdate = fetchCharacterByName(name).get(0);
			
			// @formatter:off
			String sql = "UPDATE `character` "
					+ "SET name = :newName "
					+ "WHERE name = :name";
			// @formatter:on	
			
			SqlParams params = new SqlParams();
			
			params.sql = sql;
			params.source.addValue("newName", newName);
			params.source.addValue("name", name);
			
			jdbcTemplate.update(sql, params.source);
			
			return "Changed character name from " + name + " to " + newName;
					
		}
		
		/**
		 *Deletes a character by name
		 * @return 
		 */
		@Override
		public String deleteCharacter(String name) {
			String sql = "DELETE FROM `character` WHERE name = :name";
						
						SqlParams params = new SqlParams();
						
						params.sql = sql;
						params.source.addValue("name", name);
						
						return "Deleted " + jdbcTemplate.update(sql, params.source) + " character(s) named " + name;
			
		}
		
		/**
		 *Read operation on many-to-many relationship table
		 * @return 
		 */
		@Override
		public List<CharacterWithWeapons> fetchCharactersWithWeapons() {
			String sql = "SELECT * FROM character_weapon ORDER BY character_id";
			
		    Map<String,Object> parameters = new HashMap<>();
		    
		    List<CharacterWithWeapons> charactersWithWeapons = jdbcTemplate.query(sql, parameters, new RowMapper<CharacterWithWeapons>() {

		        @Override
		        public CharacterWithWeapons mapRow(ResultSet rs, int rowNum) throws SQLException {
		          // @formatter:off
		          return CharacterWithWeapons.builder()
		              .character_id((rs.getInt("character_id")))
		              .weapon_id(rs.getInt("weapon_id"))
		              .build();
		          // @formatter:on
		        	}
		        });
			return charactersWithWeapons;

		}
		
		class CharacterResultSetExtractor implements ResultSetExtractor<Character> {

			@Override
			public Character extractData(ResultSet rs) 
				throws SQLException, DataAccessException {
				rs.next();
			
				// @formatter:off
				return Character.builder()
						.character_id(rs.getInt("character_id"))
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