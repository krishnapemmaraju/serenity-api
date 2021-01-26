package com.test.utils;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Utils {
	
	public static final String CUR_DIR = System.getProperty("user.dir");
	public static final String FILE_SEPERATOR = System.getProperty("file.separator");
	private static final String ENV = CUR_DIR+FILE_SEPERATOR+"src"+FILE_SEPERATOR+"test"+FILE_SEPERATOR+"resources"+FILE_SEPERATOR+"apikey.properties";
	private static File file;

	public String getEnvironment(String key) {
		file = new File(ENV);
		Logger.getRootLogger().setLevel(Level.OFF);
		PropertiesConfiguration config = null;
		try {
			config = new PropertiesConfiguration(file);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return config.getString(key);
	}
	
	public static String getApiKey(String key) {
		System.out.println(ENV);
		file = new File(ENV);
		Logger.getRootLogger().setLevel(Level.OFF);
		PropertiesConfiguration config = null;
		try {
			config = new PropertiesConfiguration(file);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return config.getString(key);
	}
	
	public static String[] splitString(String  stringParam,String delimter) {
		String strPalit[] = stringParam.split(delimter);
		return strPalit;
	}

}
