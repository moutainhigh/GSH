package com.gsh.cs.service.account;

import java.util.List;

import com.gsh.cs.base.MessageException;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.Commission;
import com.gsh.cs.model.base.CommissionDetail;
import com.gsh.cs.model.base.CommissionPayment;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.CommissionPRMT;

public interface CommissionServiceI {
	public Datagrid findAll(CommissionPRMT P,User user);
	
	/**
	 * 收入
	 * 
	 * @param t
	 * @throws MessageException
	 */
	public void payment(CommissionPayment cp, List<CommissionDetail> cdl) throws MessageException;

	/**
	 * 查询客户编号
	 * @return
	 */
	public  List<Commission>  findsupplierNo();
	
	/**
	 * 查询供应商编号
	 * @return
	 */
	public  List<CommissionPRMT>  findProductRoute();
	
	/**
	 * 查询承运商
	 */
	public  List<Commission>  findcarrier();
	
	/**
	 * 调用接口 添加记录
	 */
	public void save(Commission c);
	
	public String jiesuanCode(String ar);
}
