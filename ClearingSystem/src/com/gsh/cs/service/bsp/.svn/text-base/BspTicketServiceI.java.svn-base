package com.gsh.cs.service.bsp;

import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.BspTicket;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.BspTicketPRMT;

public interface BspTicketServiceI {
	/**
	 * 查询
	 * @param P
	 * @return
	 */
	public Datagrid findAll(BspTicketPRMT P,User user); 
	/**
	 * 新建
	 * @param p
	 * @return
	 */
	public BspTicket save(BspTicketPRMT p);
	
	/**
	 * 取票
	 * @param type
	 */
	public String quPiao(String type,String piaohao,String cid);
	
	/**
	 * 检索票号的唯一性
	 */
	public boolean tickedWeiyi(String piaohao,User user);
}
