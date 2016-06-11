package com.gsh.cs.service.armgt;

import java.util.List;

import com.gsh.cs.base.MessageException;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.CustomerPayment;
import com.gsh.cs.model.base.ReceivableDetail;
import com.gsh.cs.model.base.SupplierPayment;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.CustomerPaymentPRMT;

public interface CustomerPaymentServiceI {

	/**
	 * 查询
	 * 
	 * @param p
	 * @return
	 */
	public Datagrid find(CustomerPaymentPRMT p,User user);

	/**
	 * 提交申请
	 * 
	 * @param id
	 * @throws MessageException
	 */
	public void submit(Long id,String status) throws MessageException;

	/**
	 * 审核通过回调接口
	 * @param id
	 * @throws MessageException
	 */
	public void huidiaoSubmit(Long id,String name,String date) throws MessageException;
	
	/**
	 * 审核驳回回调接口
	 * @param id
	 * @throws MessageException
	 */
	public void boHuiSubmit(Long id,String name,String date) throws MessageException;
	
	/**
	 * 撤销
	 * 
	 * @param id
	 * @throws MessageException
	 */
	public void revoke(Long id) throws MessageException;

	/**
	 * 销账
	 * 
	 * @param t
	 * @param rdl
	 * @throws MessageException
	 */
	public List<ReceivableDetail> payment(CustomerPayment t, List<ReceivableDetail> rdl,User user) throws MessageException;

	/**
	 * 确认到账
	 * 
	 * @param t
	 * @throws MessageException
	 */
	public void sure(CustomerPaymentPRMT t,User user) throws MessageException;
	
	/**
	 * 应收账款销账明细报表
	 */
	public List<CustomerPayment> findYsZhangkuan();
	/*
	 * 付款明细
	 */
	public List<SupplierPayment> PassengerReport();
}
