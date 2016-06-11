package com.gsh.cs.service.bset;

import java.util.List;

import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.CollectpayAccount;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.CollectpayAccountPRMT;

public interface CollectpayaccountServiceI {
	/**
	 * 查询所有，条件查询
	 * @param p
	 * @return
	 */
	public Datagrid findAll(CollectpayAccountPRMT p,User user);
	/**
	 * 添加
	 * @param c
	 * @return
	 */
	public CollectpayAccount save(CollectpayAccount c);
	/**
	 * 修改激活
	 * @param p
	 * @return
	 */
	public CollectpayAccount update(CollectpayAccountPRMT p); 
	
	public void updateZH(CollectpayAccountPRMT p);
	/**
	 * 查询所有，List集合
	 * @return
	 */
	public List<CollectpayAccount> findAllList(String accountSource,String code,User user);
	
	/**
	 * 查询应收账户类型、应付账户类型
	 */
	public List<CollectpayAccount> findAllType(String accountSource,String cid);
}
