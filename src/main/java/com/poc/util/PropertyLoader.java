package com.poc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
	
	private PropertyLoader(){}
	
	private static Properties prop = new Properties();
	InputStream input = null;
	private static final String propertyPath = "src/main/resources/project.properties";
	
	public static Properties loadprops(){
		try {
			prop.load(new FileInputStream(propertyPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
}
