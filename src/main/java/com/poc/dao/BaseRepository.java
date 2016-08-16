package com.poc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


/**
 * 
 * @author joliveros
 *
 * @param <T>
 * 
 * base repo for BASIC CRUD, all concrete repository will implement the basic CRUD methods
 * 
 * added method for closing Resultset,Connection,statement
 * 
 */
public abstract class BaseRepository<T> {
	
	public abstract List<T> find(String key);
	public abstract boolean save(T record);
	public abstract boolean update(T record);
	public abstract boolean delete(T record);
	
	public static void closeStatement(Statement statement) {
		if(statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeConnection(Connection connection) {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
		
	public static void closeResultSet(ResultSet resultSet) {
		if(resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
