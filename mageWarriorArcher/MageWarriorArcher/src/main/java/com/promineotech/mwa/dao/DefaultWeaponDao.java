package com.promineotech.mwa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.promineotech.mwa.entity.Weapon;
import com.promineotech.mwa.exception.DbException;

@Component
@Service

public class DefaultWeaponDao extends DaoBase implements WeaponDao {
	  
	  @Override
	  public List<Weapon> fetchWeapons() {
			String sql = "SELECT * FROM weapons ORDER BY weapon_id";
			
			try(Connection conn = DbConnection.getConnection()) {
				startTransaction(conn);
				
				try(PreparedStatement stmt = conn.prepareStatement(sql)) {
					try(ResultSet rs = stmt.executeQuery()) {
						List<Weapon> weapons = new LinkedList<>();
						
						while(rs.next()) {
							weapons.add(extract(rs, Weapon.class));
							}
					
					return weapons;	
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