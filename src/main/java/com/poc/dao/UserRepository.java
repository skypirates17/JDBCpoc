package com.poc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poc.model.User;
import com.poc.util.ConnectionClass;

public class UserRepository extends BaseRepository<User>{
	
	private static final String INSERT_USER = "INSERT INTO student_info (student_name,student_age,student_address) " +
				"VALUES (?, ? ,?)";
	private static final String UPDATE_USER = "UPDATE student_info SET student_name = ?,student_age = ?,student_address = ? WHERE student_id = ?";
	private static final String DELETE_USER = "delete from student_info WHERE student_id = ?";
			
	@Override
	public boolean save(User record) {
		// TODO Auto-generated method stub
		boolean isSuccessful;
		Connection connection = null;
		PreparedStatement insertStatement = null;		
		try {
			connection = ConnectionClass.getConnection();
			insertStatement = connection.prepareStatement(INSERT_USER);
			insertStatement.setString(1, record.getName());
			insertStatement.setString(2, record.getAge());
			insertStatement.setString(3, record.getAddress());
			isSuccessful = insertStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			isSuccessful = false;
			e.printStackTrace();
		} finally {
			closeStatement(insertStatement);
			closeConnection(connection);
		}
		return isSuccessful;
	}

	@Override
	public boolean update(User record) {
		boolean isSuccessful;
		Connection connection = null;
		PreparedStatement updateStatement = null;
		try {
			connection = ConnectionClass.getConnection();
			updateStatement = connection.prepareStatement(UPDATE_USER);
			updateStatement.setString(1, record.getName());
			updateStatement.setString(2, record.getAge());
			updateStatement.setString(3, record.getAddress());
			updateStatement.setString(4, record.getId());
			isSuccessful = updateStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			isSuccessful = false;
			e.printStackTrace();
		} finally {
			closeStatement(updateStatement);
			closeConnection(connection);
		}
		return isSuccessful;
	}

	@Override
	public boolean delete(User record) {
		boolean isSuccessful;
		Connection connection = null;
		PreparedStatement updateStatement = null;
		try {
			connection = ConnectionClass.getConnection();			
			updateStatement = connection.prepareStatement(DELETE_USER);
			updateStatement.setString(1, record.getId());	
			isSuccessful = updateStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			isSuccessful = false;
			e.printStackTrace();
		} finally {
			closeStatement(updateStatement);
			closeConnection(connection);
		}
		return isSuccessful;
	}

	@Override
	public List<User> find(String key) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;		
		
		List<User> users = new ArrayList<User>();
		try {
			connection = ConnectionClass.getConnection();
			final boolean hasParameter = (key != null && key.length() > 0);
			String query = String.format("SELECT student_id,student_name,student_age,student_address FROM student_info %s", 
					hasParameter ? "WHERE student_id = ?" : "");
			statement = connection.prepareStatement(query);
			
			if(key != null)
			statement.setInt(1, Integer.parseInt(key));
			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getString("student_id"));
				user.setAge(resultSet.getString("student_age"));
				user.setName(resultSet.getString("student_name"));
				user.setAddress(resultSet.getString("student_address"));
				users.add(user);
			}
			
		} catch (SQLException e) {
			users = null;
			e.printStackTrace();
		} finally {
			closeResultSet(resultSet);
			closeStatement(statement);
			closeConnection(connection);
		}
		
		return users;
	}

}
