package com.zc.util.reader;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;

public abstract class PackageScanner {

	/**
	 * 拦截器，需要用户根据需求手动添加
	 */
	private FilterCondition condition;

	public PackageScanner() {
	}

	public PackageScanner(FilterCondition condition) {
		addFilterCondition(condition);
	}

	public PackageScanner addFilterCondition(FilterCondition condition) {
		this.condition = condition;

		return this;
	}

	public void scanPackage(Object object) {
		scanPackage(object.getClass().getPackage().getName());
	}

	public void scanPackage(Class<?> clazz) {
		scanPackage(clazz.getPackage().getName());
	}
	
	public void scanPackage(String packageName) {
		String packagePath = packageName.replace(".", "/");
		
		try {
			Enumeration<URL> resources = Thread.currentThread()
				.getContextClassLoader()
				.getResources(packagePath);
			
			while (resources.hasMoreElements()) {
				URL url = resources.nextElement();
				
				if (url.getProtocol().equals("jar")) {
					parse(url);
				} else {
					File file = new File(url.toURI());
					
					if (file.exists()) {
						parse(file, packageName);
					}
				}
			}
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
    }

	/**
	 * 回调函数
	 * @param clazz
	 */
	public abstract void dealClass(Class<?> clazz);
	
	private void parse(URL url) throws IOException {
		Enumeration<JarEntry> jarEntries = ((JarURLConnection) url.openConnection())
			.getJarFile().entries();
		
		while (jarEntries.hasMoreElements()) {
			JarEntry jarEntry = jarEntries.nextElement();
			String jarName = jarEntry.getName();
			
			if (!jarEntry.isDirectory() && jarName.endsWith(".class")) {
				dealClassName(jarName.replace("/", ".").replace(".class", ""));
			}
		}
	}
	
	private void parse(File curFile, String packageName) {
		File[] fileList = curFile.listFiles(pathname ->
                pathname.isDirectory()
                        || pathname.getName().endsWith(".class"));
		
		for (File file : fileList) {
			String fileName = file.getName();
			if (file.isDirectory()) {
				parse(file, packageName + "." + fileName);
			} else {
				String className = packageName + "." + fileName.replace(".class", "");
				dealClassName(className);
			}
		}
	}
	
	private void dealClassName(String className) {
		try {
			Class<?> clazz = Class.forName(className);
			
			if (clazz.isPrimitive()
					|| (this.condition != null
					&& this.condition.filter(clazz))) {
				return;
			}

			dealClass(clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public interface FilterCondition {

		/**
		 * 拦截条件
		 * @param clazz
		 * @return
		 */
		boolean filter(Class<?> clazz);

	}

}
