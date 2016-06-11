package com.gsh.cs.model.easyui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Easyui Datagrid 模型
 * 
 * @author alan
 * 
 */
public class Datagrid {

	private Long total = 0L;

	private List rows = new ArrayList();

	private List footer = new ArrayList();

	private Map sumfooter = new HashMap();

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public List getFooter() {
		return footer;
	}

	public void setFooter(List footer) {
		this.footer = footer;
	}

	public Map getSumfooter() {
		return sumfooter;
	}

	public void setSumfooter(Map sumfooter) {
		this.sumfooter = sumfooter;
	}

}
