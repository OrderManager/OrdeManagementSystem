package com.orderManagement.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

	private static PropertyManager pm;
	private static Properties properties = new Properties();

	private PropertyManager() {
		
	}

	public static synchronized PropertyManager getInstance() {
		if (pm == null) {
			pm = new PropertyManager();
		}
		return pm;
	}
	
	public String getValue(String key){
		readConfig();
		return properties.getProperty(key);
	}
	
	private void readConfig(){
		try {
			InputStream is = PropertyManager.class.getClassLoader().getResourceAsStream("dbinfo.properties");
			properties.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}