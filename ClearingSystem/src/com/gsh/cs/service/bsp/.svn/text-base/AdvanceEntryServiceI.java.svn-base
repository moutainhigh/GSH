package com.gsh.cs.service.bsp;

import com.gsh.cs.model.base.AdvanceEntry;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.AdvanceEntryPRMT;

public interface AdvanceEntryServiceI {
	/**
	 * 查询所有、分页查询
	 * @param P
	 * @return
	 */
	public Datagrid findAll(AdvanceEntryPRMT P);
	/**
	 * 录入
	 * @param P
	 * @return
	 */
	public AdvanceEntry save(AdvanceEntryPRMT P);
	
	/**
	 * 判断发票号是否唯一
	 * @param invoiceNo
	 */
	public AdvanceEntry findBianli(String invoiceNo);
}
