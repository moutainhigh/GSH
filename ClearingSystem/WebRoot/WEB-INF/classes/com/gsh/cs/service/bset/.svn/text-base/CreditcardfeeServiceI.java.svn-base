package com.gsh.cs.service.bset;

import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.CreditcardFee;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.CreditcardFeePRMT;

public interface CreditcardfeeServiceI {
	/**
	 * 查询所有，多条件查询
	 * @param P
	 * @return
	 */
	public Datagrid findAll(CreditcardFeePRMT p,User user);
	/**
	 * 新建
	 * @param P
	 * @return
	 */
	public CreditcardFee save(CreditcardFeePRMT p);
	/**
	 * 修改
	 * @param P
	 * @return
	 */
	public CreditcardFee update(CreditcardFeePRMT p);
	/**
	 * 删除
	 * @param P
	 */
	public void delete(CreditcardFeePRMT p);
	/**
	 * 解锁
	 * @param id
	 * @param creditcardfeeLock
	 */
	public void updateByCreditcardfeeLock(String id,String creditcardfeeLock);
	
	/**
	 * 判断是否唯一
	 * @param code
	 * @return
	 */
	public CreditcardFee findBianli(String code,User user);
}
