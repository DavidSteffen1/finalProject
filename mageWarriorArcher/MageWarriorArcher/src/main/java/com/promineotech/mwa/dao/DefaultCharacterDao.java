package com.promineotech.mwa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.promineotech.mwa.entity.Character;
import com.promineotech.mwa.exception.DbException;

@Component
@Service

public class DefaultCharacterDao extends DaoBase implements CharacterDao {
	  
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

	}