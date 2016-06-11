package com.gsh.cs.tools;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTool {

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param fmt
	 * @return
	 */
	public String formatter(Date date, String fmt) {
		SimpleDateFormat df = new SimpleDateFormat(fmt);
		return df.format(date);
	}

	/**
	 * 格式化日期 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public String formatterDateTime(Date date) {
		return formatter(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 格式化日期 yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public String formatterDate(Date date) {
		return formatter(date, "yyyy-MM-dd");
	}

	/**
	 * 获取系统日期 yyyy-MM-dd
	 * 
	 * @return
	 */
	public String getSystemDate() {
		return formatter(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 获取系统日期时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public String getSystemDateTime() {
		return formatter(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 获取系统时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return Timestamp
	 */
	public static Timestamp getSystemTimestamp() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return Timestamp.valueOf(sd.format(Calendar.getInstance().getTime()));
	}
}
