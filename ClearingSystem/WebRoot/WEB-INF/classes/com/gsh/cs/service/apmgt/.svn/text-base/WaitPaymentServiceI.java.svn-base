package com.gsh.cs.service.apmgt;

import java.util.List;

import com.gsh.cs.base.MessageException;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.Interface.AccountsPayable;
import com.gsh.cs.model.base.Payable;
import com.gsh.cs.model.base.PayableDetail;
import com.gsh.cs.model.base.SupplierPayment;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.PayablePRMT;

public interface WaitPaymentServiceI {

	/**
	 * 查询
	 * 
	 * @param p
	 * @return
	 */
	public Datagrid findPayable(PayablePRMT p,User user);

	/**
	 * 撤销
	 * 
	 * @param id
	 * @return
	 */
	public Payable revocation(Long id) throws MessageException;

	/**
	 * 付款
	 * 
	 * @param t
	 * @param pl
	 */
	public List<PayableDetail> pay(SupplierPayment t, List<PayableDetail> pl,User user) throws MessageException;

	/**
	 * 收入
	 * 
	 * @param t
	 * @param pl
	 */
	public List<PayableDetail> income(SupplierPayment t, List<PayableDetail> pl,User user) throws MessageException;

	/**
	 * 付款申请
	 * 
	 * @param t
	 * @param pl
	 * @throws MessageException
	 */
	public List<PayableDetail> apply(SupplierPayment t, List<PayableDetail> pl) throws MessageException;
	
	/**
	 * 查询归属人
	 */
	public List<Payable> findAllList(User user);
	
	/**
	 * 查询客户编号
	 * @return
	 */
	public List<Payable> findAllSupplierNo(String code);
	
	/**
	 * 添加应收单据信息
	 */
	public void save(Payable p);
	
	/**
	 * 订单中心作废单据，需要调用接口，改变申请作废状态
	 */
	public void UpdateCancelStatus(String exchangeNo);
	
	/**
	 * 同意作废的话，反向生成一条记录
	 * @param p
	 */
	public Payable tongyi(String id);
	
	/**
	 * 不同意作废
	 * @param p
	 */
	public void butongyi(String id);
	
	/**
	 * 新建
	 * @param p
	 * @return
	 */
	public Payable add(Payable p);
	
	/**
	 * 编码规则
	 * 13位数：AP+8位年月日+“-”+2位顺序号
	 * Eg：AP20140721-01
	 */
	public String addCode();
	
	/**
	 * 修改通知单状态接口
	 * @param noticeNo
	 */
	public void UpdateNoticeStatus(String exchangeNo);
	
	/**
	 * 修改产品单状态接口
	 * @param noticeNo
	 */
	public void UpdateProductStatus(String exchangeNo);
	
	public String jiesuanCode(String ar);
	
	public Payable findPayable(String pablid);
	
	/**
	 * 修改付款交换单的异常状态
	 * @param noticeNo
	 */
	public void UpdateAbnormal(String exchangeNo,String abnormalStatus);
	
	//订单中心新增作废
	public Payable updateWaitPaymentCS(String exchangeNo);
	
	public List<AccountsPayable> accountsPayable(PayablePRMT p,String xianshi,String xianshi1,String xianshi2);
	
	//订单中心修改确认号
	public void updateQrh(String exchangeNo,String productNo);
	
	public Payable findAllexNo(String exchangeNo);
	
}
