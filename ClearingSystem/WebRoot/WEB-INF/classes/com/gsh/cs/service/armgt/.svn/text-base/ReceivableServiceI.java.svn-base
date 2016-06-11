package com.gsh.cs.service.armgt;

import java.math.BigDecimal;
import java.util.List;

import com.gsh.cs.base.MessageException;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.Interface.AccountsReceivable;
import com.gsh.cs.model.Interface.CustomersOntheBill;
import com.gsh.cs.model.Interface.SalesmanArrearsReport;
import com.gsh.cs.model.base.CustomerPayment;
import com.gsh.cs.model.base.DeadlineChg;
import com.gsh.cs.model.base.Receivable;
import com.gsh.cs.model.base.ReceivableDetail;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.ReceivablePRMT;

public interface ReceivableServiceI {

	/**
	 * 查询
	 * 
	 * @param p
	 * @return
	 */
	public Datagrid find(ReceivablePRMT p,User user);

	/**
	 * 添加
	 * 
	 * @param p
	 * @return
	 */
	public Receivable add(Receivable t);

	/**
	 * 编码规则
	 * 10位数：AR+6位年月日+2位顺序号
	 *	Eg：AR14072101
	 */
	public String addCode();
	
	/**
	 * 撤销
	 * 
	 * @param id
	 * @return
	 */
	public Receivable revocation(Long id) throws MessageException;

	/**
	 * 修改结算期限
	 * 
	 * @param d
	 * @return
	 */
	public Receivable deadline(DeadlineChg d);

	/**
	 * 销账
	 * 
	 * @param t
	 * @param rdl
	 */
	public List<ReceivableDetail> income(CustomerPayment t, List<ReceivableDetail> rdl,User user) throws MessageException;

	/**
	 * 销账 -退款
	 * 
	 * @param t
	 * @param rdl
	 */
	public List<ReceivableDetail> refund(CustomerPayment t, List<ReceivableDetail> rdl,User user) throws MessageException;

	/**
	 * 退款申请
	 * 
	 * @param t
	 * @param rdl
	 */
	public void apply(CustomerPayment t, List<ReceivableDetail> rdl) throws MessageException;
	
	/**
	 * 调用接口添加单据
	 * @param t
	 */
	public void save(Receivable t);
	
	/**
	 * 订单中心作废应收单据，调用接口
	 */
	public Receivable OrderZuofei(String noticeNo);
	
	/**
	 * 13位数：3位编码+8位年月日+2位
	 *	Eg：PMT2014072110
	 * @return
	 */
	public String jiesuanCode(String ar);
	
	/**
	 * 查询应收账款
	 * @param rcvbid
	 * @return
	 */
	public Receivable findReceivable(String rcvbid);
	
	/**
	 * 修改付款通知单的异常状态
	 */
	public void UpdateAbnormal(String noticeNo,String abnormalStatus);
	
	/**
	 * 订单中心需要的接口
	 * 根据客户编号查询所有未销账的数据
	 */
	public double findReceivableSearch(String customerNo,String startDate,String endDate);
	
	//报表应收账款借口
	public List<AccountsReceivable> accountsReceivable(ReceivablePRMT p,String xianshi,String xianshi1,String xianshi2);
	//报表客户对账单接口
	public List<CustomersOntheBill> customersOntheBill(ReceivablePRMT p);
	//业务员欠款报表
	public List<SalesmanArrearsReport> salesmanArrearsReport(ReceivablePRMT p,String accountType,String affiliationNo);
	//根据通知单号查询未销账金额
	public double findMoney(String noticeNo);
	
	public List<Receivable> findReceivableReport();
	
	public Receivable tongyi(String id);
	public void butongyi(String id);
	
	/**
	 * 订单中心作废单据，需要调用接口，改变申请作废状态
	 */
	public void UpdateCancelStatus(String noticeNo);
}
