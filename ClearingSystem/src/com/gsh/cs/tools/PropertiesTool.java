package com.gsh.cs.tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesTool {

	/**
	 * 根据key读取
	 * 
	 * @param filePath
	 * @param key
	 * @return
	 */
	public static String readValue(String filePath, String key) {
		Properties props = readProperties(filePath);
		return props.getProperty(key);
	}

	/**
	 * 读取Properties文件
	 * 
	 * @param filePath
	 * @return
	 */
	public static Properties readProperties(String filePath) {
		Properties props = new Properties();
		try {
			InputStream in = PropertiesTool.class.getClassLoader().getResourceAsStream(filePath);
			props.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return props;
	}

	/**
	 * 写入Properties 文件
	 * 
	 * @param filePath
	 * @param parameterName
	 * @param parameterValue
	 */
	public static void writeProperties(String filePath, String parameterName, String parameterValue) {
		Properties prop = new Properties();
		try {
			InputStream fis = new FileInputStream(filePath);
			// 从输入流中读取属性列表（键和元素对）
			prop.load(fis);
			// 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
			// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
			OutputStream fos = new FileOutputStream(filePath);
			prop.setProperty(parameterName, parameterValue);
			// 以适合使用 load 方法加载到 Properties 表中的格式，
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			prop.store(fos, "Update '" + parameterName + "' value");
		} catch (IOException e) {
			System.err.println("Visit " + filePath + " for updating " + parameterName + " value error");
		}
	}

}
