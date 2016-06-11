package com.gsh.cs.service.apmgt;

import java.util.List;

import com.gsh.cs.base.MessageException;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.PayableDetail;
import com.gsh.cs.model.base.SupplierPayment;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.PayableViewPRMT;
import com.gsh.cs.model.parameter.SupplierPaymentPRMT;

public interface ApplyPaymentServiceI {
	/**
	 * 查询
	 * 
	 * @param p
	 * @return
	 */
	public Datagrid find(SupplierPaymentPRMT p,User user);

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
	
	//驳回接口
	public void bohuiSubmit(Long id,String name,String date) throws MessageException;

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
	 * @throws MessageException
	 */
	public  List<PayableDetail> payment(SupplierPayment sp, List<PayableDetail> pl,User user) throws MessageException;

	/**
	 * 修改状态
	 * 
	 * @param status
	 */
	public void updateStatus(String status, String settlementNo);
	
	/**
	 * payable_detail修改录入状态
	 * @param pablid
	 * @param supmid
	 */
	public void updatePayableDdetail(Integer pablid,Integer supmid);
	
	/**
	 * 查询录入状态
	 * @return
	 */
	public Integer findstatusZanzhi(Integer supmid);

}
