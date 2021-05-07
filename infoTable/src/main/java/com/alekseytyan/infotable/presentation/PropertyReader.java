package com.alekseytyan.infotable.presentation;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

	public static final String propFileName = "config.properties";

	public static String getPropValue(String key) {

		String result = "";

		try(InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(propFileName);) {
			Properties prop = new Properties();

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			return prop.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}