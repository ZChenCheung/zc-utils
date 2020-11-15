package com.zc.util.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PropertiesReader {

	private final Map<String, String> propertiesMap = new HashMap<String, String>();

	public PropertiesReader() {
	}

	public Map<String, String> getPropertiesMap() {
		return new HashMap<>(propertiesMap);
	}
	
	public List<String> getKeyList() {
		List<String> result = new ArrayList<String>();
		result.addAll(propertiesMap.keySet());
		
		return result;
	}
	
	public String getValue(String key) {
		return propertiesMap.get(key);
	}
	
	public void readPropertiesFile(File file) {
		try {
			readPropertiesFile(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void readPropertiesFile(String path) throws FileNotFoundException {
		InputStream is = Properties.class.getResourceAsStream(path);
		if (is == null) {
			throw new FileNotFoundException("File[" + path + "] not found!");
		} else {
			readPropertiesFile(is);
		}
	}
	
	public void readPropertiesFile(InputStream is) {
		try {
			Properties properties = new Properties();
			properties.load(new InputStreamReader(is, "UTF-8"));
			Enumeration<Object> keys = properties.keys();

			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				String value = properties.getProperty(key);
				
				propertiesMap.put(key, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
