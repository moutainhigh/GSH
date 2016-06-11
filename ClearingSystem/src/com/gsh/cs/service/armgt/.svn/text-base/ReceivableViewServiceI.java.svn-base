package com.gsh.cs.service.armgt;

import java.util.List;

import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.Interface.ReceivableCustmerPaymentView;
import com.gsh.cs.model.base.Receivable;
import com.gsh.cs.model.base.ReceivableView;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.ReceivableViewPRMT;

public interface ReceivableViewServiceI {
	/**
	 * 查询应付销账明细
	 * 
	 * @return
	 */
	public Datagrid findAll(ReceivableViewPRMT p,User user);

	/**
	 * 查询归属人 唯一
	 * @return
	 */
	public List<Receivable> findAllList(User user);
	
	/**
	 * 查询客户编号   唯一
	 * @return
	 */
	public List<Receivable> findAllCustomerNo(String code);

	/**
	 * 查询
	 * 
	 * @return List
	 */
	public List<ReceivableView> list(ReceivableViewPRMT p);
	
	public com.gsh.cs.model.Interface.ReceivableView findId(Long id);
	
	public List<ReceivableCustmerPaymentView> findReceivableCustmerPaymentView();
}
