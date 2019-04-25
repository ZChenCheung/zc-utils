package com.zc.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Properties;

public abstract class ReadProperties {

	public ReadProperties() {}
	
	/**
	 * 回调函数，由调用者处理
	 * @param key
	 * @param value
	 */
	public abstract void dealKeyAndValue(String key, String value);

	/**
	 * 根据文件的绝对路径解析
	 * @param absolutePath
	 * @throws IOException
	 */
	public void readFile(String absolutePath) throws IOException {
		read(new File(absolutePath));
	}

	/**
	 * 根据包路径解析
	 * @param packagePath
	 * @throws IOException
	 */
	public void read(String packagePath) throws IOException {
		InputStream is = this.getClass().getResourceAsStream(packagePath);
		read(is);
	}
	
	/**
	 * 根据{@link File}解析
	 * @param file
	 * @throws IOException
	 */
	public void read(File file) throws IOException {
		read(new FileInputStream(file));
	}
	
	/**
	 * 根据{@link InputStream}解析
	 * @param is
	 * @throws IOException
	 */
	public void read(InputStream is) throws IOException {
		Properties properties = new Properties();
		try {
			// Properties文件会出现乱码问题，转换成UTF-8打开
			properties.load(new InputStreamReader(is, "UTF-8"));
			Enumeration<Object> keys = properties.keys();
			
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				String value = properties.getProperty(key);
				properties.get(key);
				
				dealKeyAndValue(key, value);
			}
		} finally {
			is.close();
		}
	}
		
}
