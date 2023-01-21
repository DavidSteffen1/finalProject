package com.promineotech.mwa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.promineotech.mwa.entity.Armor;
import com.promineotech.mwa.exception.DbException;

@Component
@Service

public class DefaultArmorDao extends DaoBase implements ArmorDao {
	  
	  @Override
	  public List<Armor> fetchArmor() {
			String sql = "SELECT * FROM armor ORDER BY armor_id";
			
			try(Connection conn = DbConnection.getConnection()) {
				startTransaction(conn);
				
				try(PreparedStatement stmt = conn.prepareStatement(sql)) {
					try(ResultSet rs = stmt.executeQuery()) {
						List<Armor> armor = new LinkedList<>();
						
						while(rs.next()) {
							armor.add(extract(rs, Armor.class));
							}
					
					return armor;	
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