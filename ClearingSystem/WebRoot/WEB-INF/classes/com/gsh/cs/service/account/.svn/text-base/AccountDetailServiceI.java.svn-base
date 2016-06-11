package com.gsh.cs.service.account;

import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.AccountDetailPRMT;

public interface AccountDetailServiceI {
	/**
	 * 查询应收应付记录
	 * @return
	 */
	public Datagrid findCollectPay(AccountDetailPRMT P,User user);
	
	/**
	 * 查询账目需要复合的记录
	 * @return
	 */
	public Datagrid findToReview(AccountDetailPRMT P,User user);
	
	/**
	 * 根据主键ID，确认复核
	 * @param settlementNo
	 */
	public void AccountDetailToreview(String id);
	
	/**
	 * 应付账目明细查询
	 * @param id
	 */
	public Datagrid payableMingxi(String id);
	
	/**
	 * 应收明细
	 * @param id
	 * @return
	 */
	public Datagrid receivableMingxi(String id);
	
	/**
	 * 佣金明细
	 * @param id
	 * @return
	 */
	public Datagrid commissionMingxi(String id);
	
	/**
	 * 应付驳回
	 */
	public void payableReject(String id);
	
	/**
	 * 应收驳回
	 */
	public void receivableReject(String id);
	
	/**
	 * 佣金驳回
	 */
	public void commissionReject(String id);
	
	/**
	 * 转账汇款驳回
	 * @param id
	 */
	public void zhuanZhangReject(String id);
	
	/**
	 * 转账汇款
	 * @param p
	 */
	public void zhuanZhang(AccountDetailPRMT p,User user);
	
	public String jiesuanCode();
}
