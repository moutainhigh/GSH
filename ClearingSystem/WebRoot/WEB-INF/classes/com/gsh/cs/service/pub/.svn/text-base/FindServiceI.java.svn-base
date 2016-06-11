package com.gsh.cs.service.pub;

import java.math.BigDecimal;
import java.util.List;

import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.Account;

public interface FindServiceI {

	/**
	 * 查询所有 account
	 * 
	 * @return
	 */
	public List<Account> findAll(String paymentMethod,User user);
	public List<Account> findAllType(String paymentMethod,User user);
	/**
	 * 获取员工业务汇率
	 * 
	 * @param currency
	 * @return
	 */
	public BigDecimal findRate(String currency,User user);

}
