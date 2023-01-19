package com.promineotech.mwa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.promineotech.mwa.entity.Character;
import com.promineotech.mwa.exception.DbException;

import lombok.extern.slf4j.Slf4j;

@Component
@Service
@Slf4j

public class DefaultCharacterDao extends DaoBase implements CharacterDao {

	  @Autowired
	  private NamedParameterJdbcTemplate jdbcTemplate; 
	  
	  @Override
	  public List<Character> fetchCharacters() {
			String sql = "SELECT * FROM " + CHARACTER_TABLE + " ORDER BY character_id";
			
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

	}