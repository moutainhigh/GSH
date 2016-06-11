package com.gsh.cs.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LongTool {

	/**
	 * 是不是NULL 或者为 0
	 * 
	 * @param s
	 * @return boolean
	 */
	public static boolean isEmptyOrNull(Long l) {
		return (l == null || l.equals(0l));
	}
	
	/**
	 * 系统当前时间
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static java.sql.Date stringTosqlDate(String date) throws ParseException{
		if(date==null||date.trim().equals("")){
			return null;
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Date time=sd.parse(date);
		return new java.sql.Date(time.getTime());
	}
}
