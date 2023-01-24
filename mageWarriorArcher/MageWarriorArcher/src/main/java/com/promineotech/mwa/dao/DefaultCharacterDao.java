package com.promineotech.mwa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
import com.promineotech.mwa.exception.DbException;

@Component

public class DefaultCharacterDao extends DaoBase implements CharacterDao {
	  
	  @Autowired
	  private NamedParameterJdbcTemplate jdbcTemplate;
	  
		@Override
		public Character createNewCharacter(String name) {
			SqlParams params = generateInsertSql(name);
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(params.sql, params.source, keyHolder);
			
			int characterId = keyHolder.getKey().intValue();
			
			//try separate method to run generated sql query against database
			//runInsertSql(name);
			
			// @formatter:off
			return Character.builder()
					.character_id(characterId)
					.name(name)
					.build();
			// @formatter:on
		}
		
		/**
		 * saveCharacter is not connected to database?
		 */
//		private void runInsertSql(String name) {
//		
//			String sql = generateInsertSql(name).sql;
//			
//			try(Connection conn = DbConnection.getConnection()) {
//				startTransaction(conn);
//				
//				try(PreparedStatement stmt = conn.prepareStatement(sql)) {
//					
//						SqlParams params = new SqlParams();
//						
//						params.sql = sql;
//						params.source.addValue("name", name);
//						
//						jdbcTemplate.update(sql, params.source);
//
//					}
//				}
//			
//			catch(SQLException e) {
//				throw new DbException(e);
//				}
//			
//		}

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
				
				try(Connection conn = DbConnection.getConnection()) {
					startTransaction(conn);
					
					try(PreparedStatement stmt = conn.prepareStatement(sql)) {
						try(ResultSet rs = stmt.executeQuery()) {
							List<Character> characters = new LinkedList<>();
							
							while(rs.next()) {
								characters.add(extract(rs, Character.class));
								}
						
						return characters;	
						}
					}
				
				catch(Exception e) {
					rollbackTransaction(conn);
					throw new DbException(e);
					}			
				}
				
				catch(SQLException e) {
					throw new DbException(e);
					}
				
			}
		
		/**
		 *Fetches Character by name
		 *CharacterResultsSetExtractor?
		 *Builder?
		 */
		@Override
		public Character fetchCharacter(String name) {
			String sql = "SELECT * FROM `character` WHERE name = :name";
			
			try(Connection conn = DbConnection.getConnection()) {
				startTransaction(conn);
				
				try(PreparedStatement stmt = conn.prepareStatement(sql)) {
					try(ResultSet rs = stmt.executeQuery()) {
						
						Map<String, Object> params = new HashMap<>();
						params.put("name", name);
					
					    return jdbcTemplate.query(sql, params, new CharacterResultSetExtractor());
					}
				}
			
			catch(Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
				}			
			}
			
			catch(SQLException e) {
				throw new DbException(e);
				}
		}

		  /**
		 *Fetches Character by Id
		 *Returns SQL syntax error
		 */
		@Override
		  public Character fetchCharacterById(int character_id) {
				String sql = "SELECT * FROM `character` WHERE character_id = :character_id";		
				
				try(Connection conn = DbConnection.getConnection()) {
					startTransaction(conn);
					
					try(PreparedStatement stmt = conn.prepareStatement(sql)) {
						try(ResultSet rs = stmt.executeQuery()) {
							
							Map<String, Object> params = new HashMap<>();
							params.put("character_id", character_id);
						
							return jdbcTemplate.query(sql, params, new CharacterResultSetExtractor());
						}
					}
				
				catch(Exception e) {
					rollbackTransaction(conn);
					throw new DbException(e);
					}			
				}
				
				catch(SQLException e) {
					throw new DbException(e);
					}
				
			}

		/**
		 *Deletes a character by name
		 * @return 
		 */
		@Override
		public String deleteCharacter(String name) {
			String sql = "DELETE FROM `character` WHERE name = :name";
			//String deleteMessage = 
			
			try(Connection conn = DbConnection.getConnection()) {
				startTransaction(conn);
				
				try(PreparedStatement stmt = conn.prepareStatement(sql)) {
						
						SqlParams params = new SqlParams();
						
						params.sql = sql;
						params.source.addValue("name", name);
						
						return "Deleted " + jdbcTemplate.update(sql, params.source) + " character(s) named " + name;

					}
				}
			
			catch(SQLException e) {
				throw new DbException(e);
				}
			
		}
		

		@Override
		public Character updateCharacter(String name, String newName) {
			// Need fetchCharacter method
			return null;
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