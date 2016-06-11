package com.gsh.cs.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SoaPropertiesUtil {
	public static Properties soaUrl = System.getProperties();

	static {
		SoaPropertiesUtil.readProperties("soaUrl", soaUrl);
	}

	public static void readProperties(String propName, Properties p) {
		try {
			InputStream inputStream = SoaPropertiesUtil.class
					.getResourceAsStream("/" + propName + ".properties");
			if (null == inputStream) {
				System.out.println("readProperties(String, Properties) - Read "
						+ propName + ".properties is error!");
			} else {
				p.load(inputStream);
				inputStream.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("readProperties(String, Properties)" + e);
		} catch (IOException e) {
			System.out.println("readProperties(String, Properties)" + e);
		}
	}
}
