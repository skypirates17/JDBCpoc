package com.poc.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

import com.poc.util.PropertyLoader;


/**
 * class for initializing connection
 * @author joliveros
 *
 */
public class ConnectionClass {
	
	private static final String DRIVER="db.driver";
	private static final String URL="db.url";
	private static final String USERNAME="db.user";
	private static final String PASSWORD="db.password";
	
	private BasicDataSource datasource;
	
	private ConnectionClass(){
		prop = PropertyLoader.loadprops();
		datasource = new BasicDataSource();
		datasource.setDriverClassName(prop.getProperty(DRIVER));
		datasource.setUrl(prop.getProperty(URL));
		datasource.setUsername(prop.getProperty(USERNAME));
		datasource.setPassword(prop.getProperty(PASSWORD));
	}
	
	Properties prop = new Properties();

	private static class InitConnection{
		static final ConnectionClass connectionInstance = new ConnectionClass();
	}
	
	public static Connection getConnection() throws SQLException{
		return InitConnection.connectionInstance.datasource.getConnection();
	}
}

