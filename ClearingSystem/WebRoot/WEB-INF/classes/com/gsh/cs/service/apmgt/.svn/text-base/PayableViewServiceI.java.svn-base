package com.gsh.cs.service.apmgt;

import java.util.List;

import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.Payable;
import com.gsh.cs.model.base.PayableView;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.PayableViewPRMT;

public interface PayableViewServiceI {
	/**
	 * 查询应收销账明细
	 * 
	 * @param p
	 * @return
	 */
	public Datagrid findAll(PayableViewPRMT p,User user);

	/**
	 * 查询
	 * 
	 * @param p
	 * @return List
	 */
	public List<PayableView> list(PayableViewPRMT p);
	
	public Datagrid findZhanzhi(PayableViewPRMT p,User user,String status);
	
	public com.gsh.cs.model.Interface.PayableView findId(Long id);
	
	public List<Payable> findSupmid(String supmid);
	
}
