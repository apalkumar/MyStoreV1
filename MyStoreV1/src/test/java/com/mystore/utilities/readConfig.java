package com.mystore.utilities;

import java.io.FileInputStream;
import java.util.Properties;

import javax.management.RuntimeErrorException;

public class readConfig {
	
	Properties prop;
	
	String path = "C:\\Users\\ADMIN\\Desktop\\JavaSelenium\\MyStoreV1\\Configuration\\config.properties";
	
	public readConfig() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);
		}
		catch(Exception	 e) {
			e.printStackTrace();
		}
	}
	
	public String getBaseUrl() {
		String baseURl = prop.getProperty("baseUrl");
		if (baseURl!=null)
			return baseURl;
		else
			throw new RuntimeException("url is not specified in config file");
	}

	
	public String getBrowser() {
		String browserValue = prop.getProperty("browser");
		if (browserValue!=null)
			return browserValue;
		else
			throw new RuntimeException("url is not specified in config file");
	}
}
