package com.gsh.cs.service.bset;

import java.util.List;

import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.Account; 
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.AccountPRMT;

public interface AccountServiceI {
	/**
	 * 查询所有，多条件查询
	 * @param p
	 * @return
	 */
	public Datagrid findAll(AccountPRMT p,User user);
	/**
	 * 新增
	 * @param account
	 */
	public Account save(AccountPRMT p);
	/**
	 * 修改
	 * @param account
	 */
	public Account update(AccountPRMT p);
	/**
	 * 删除
	 * @param account
	 */
	public void delete(AccountPRMT p);
	
	/**
	 * 账户状态改变
	 * @param id
	 * @param status
	 */
	public void updateByStatus(String id,String status);
	
	/**
	 * 查询List集合
	 * @return
	 */
	public List<Account> findAllList(User user);
	
	/**
	 * 判断是否唯一
	 */
	public Account findBianli(String code);
	
	/**
	 * 解锁
	 */
	public void updateByaccountLock(String id,String accountLock);
	
	/**
	 * 查询其他账户
	 * @return
	 */
	public List<Account> findAccount(String code,String accountCurrency);
	
	/**
	 * 查询默认收款账户
	 * @return
	 */
	public List<Account> findByDefaultIncAccount();
	
	
}
