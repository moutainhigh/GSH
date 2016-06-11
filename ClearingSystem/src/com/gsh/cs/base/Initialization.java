package com.gsh.cs.base;

import java.util.Properties;

import com.gsh.cs.tools.PropertiesTool;

public class Initialization {

	public static final String CURRENCY_UNIT; // 结算本币

	public static final boolean APPLY_POWWER; // 付款申请开关
	
	public static final String SETTLEMENT_DAY; // 结算日期

	static {
		Properties system = PropertiesTool.readProperties("/system.properties");
		CURRENCY_UNIT = system.getProperty("currency_unit");
		APPLY_POWWER = false;
		SETTLEMENT_DAY = system.getProperty("settlement_day");
	}

}
