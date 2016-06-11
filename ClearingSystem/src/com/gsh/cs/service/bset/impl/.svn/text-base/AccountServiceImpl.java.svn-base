package com.gsh.cs.service.bset.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsh.cs.dao.BaseDaoI;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.Account;
import com.gsh.cs.model.base.SettlementExchangeRate;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.AccountPRMT;
import com.gsh.cs.model.parameter.ReceivablePRMT;
import com.gsh.cs.service.bset.AccountServiceI;
import com.gsh.cs.tools.LongTool;
import com.gsh.cs.tools.StringTool;

@Transactional
@Service("accountService")
public class AccountServiceImpl implements AccountServiceI {
	private BaseDaoI<Account> accountDao;
	
	public BaseDaoI<Account> getAccountDao() {
		return accountDao;
	}
	@Autowired
	public void setAccountDao(BaseDaoI<Account> accountDao) {
		this.accountDao = accountDao;
	}
	
	/**
	 * 查询所有，多条件查询
	 * @param p
	 * @return
	 */
	public Datagrid findAll(AccountPRMT p,User user) {
		Datagrid dg = new Datagrid();
		String hql="from Account a where 1=1 ", countHql = "select count(*) ";
		Map<String, Object> params = new HashMap<String, Object>(0);
		if(!StringTool.isEmptyOrNull(p.getAccountCode())) {
			hql+="and a.accountCode like :accountCode ";
			params.put("accountCode", "%"+p.getAccountCode()+"%");
		}if(p.getAccountCurrency()!=null&&!p.getAccountCurrency().equals("")){
			hql+="and a.accountCurrency = :accountCurrency ";
			params.put("accountCurrency", p.getAccountCurrency());
		}
		if(p.getAccountBank()!=null&&!p.getAccountBank().equals("")){
			hql+="and a.accountBank like :accountBank ";
			params.put("accountBank", "%"+p.getAccountBank()+"%");
		}
		if(p.getAccountType()!=null&&!p.getAccountType().equals("")){
			hql+="and a.accountType = :accountType ";
			params.put("accountType", p.getAccountType());
		}
		hql+="and a.userId="+user.getCid();
		countHql += hql;
		hql = setOrder(p, hql);
		hql +=" group by a.accountCurrency";
		dg.setRows(this.accountDao.find(hql, params, p.getPage(), p.getRows()));
		dg.setTotal(this.accountDao.count(countHql, params));
		return dg;
	}
	
	public String setOrder(AccountPRMT p, String hql) {
		if (!StringTool.isEmptyOrNull(p.getSort()) && null != p.getOrder()) {
			hql += " order by " + p.getSort() + " " + p.getOrder();
		}else{
			hql += " order by establishDate asc ";
		}
		return hql;
	}

	/**
	 * 新增
	 * @param account
	 */
	public Account save(AccountPRMT p) {
		try {
			Account a=new Account();
			BeanUtils.copyProperties(p, a);
			if(a.getAccountCode()==null||a.getAccountCode().equals("")){
				if(a.getAccountType().equals("C")){//公司
					a.setAccountCode("CO"+this.code());
				}else if(a.getAccountType().equals("P")){//个人
					a.setAccountCode("PC"+this.code());
				}else if(a.getAccountType().equals("M")){//现金
					a.setAccountCode("CA"+this.code());
				}else if(a.getAccountType().equals("X")){//信用卡
					a.setAccountCode("CC"+this.code());
				}else if(a.getAccountType().equals("O")){//第三方支付
					a.setAccountCode("TP"+this.code());
				}
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			a.setEstablishDate(LongTool.stringTosqlDate(df.format(new Date())));
			a.setId((Long) this.accountDao.save(a));
			return a;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 修改
	 * @param account
	 */
	public Account update(AccountPRMT p) {
		try {
			Account a=new Account();
			BeanUtils.copyProperties(p, a);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			a.setEstablishDate(LongTool.stringTosqlDate(df.format(new Date())));
			this.accountDao.update(a);
			return a;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 删除
	 * @param account
	 */
	public void delete(AccountPRMT p) {
		Account a=new Account();
		BeanUtils.copyProperties(p, a);
		this.accountDao.delete(a);
	}
	
	/**
	 * 修改激活、挂起、解挂、注销状态
	 */
	public void updateByStatus(String id, String status) {
		String hql="update Account set accountStatus = "+status+", accountLock = 0 where id = "+id;
		accountDao.executeHql(hql);
	}
	/**
	 * 查询LIST集合
	 */
	public List<Account> findAllList(User user) {
		String hql="from Account where accountStatus =1 and userId = "+user.getCid();
		List<Account> li = accountDao.find(hql);
		return li;
	}
	
	
	/**
	 * 修改锁定状态
	 */
	public void updateByaccountLock(String id,String accountLock) {
		String hql="update Account set accountLock = "+accountLock+" where id = "+id;
		accountDao.executeHql(hql);
	}
	
	/**
	 * 编辑判断账户代码唯一
	 */
	public Account findBianli(String code) {
		String hql="from Account where accountCode = '"+code+"'";
		Account a= accountDao.get(hql);
		return a;
	}
	
	public String code(){
		Random r = new Random();
		Double d = r.nextDouble();
		System.out.println(d);
		String s = d + "";
		s=s.substring(3,3+6);
		return s;
	}
	
	public List<Account> findAccount(String code,String accountCurrency) {
		String hql = "from Account where accountCode != '"+code+"' and accountCurrency = '"+accountCurrency+"' and accountStatus = 1";
		List<Account> li = accountDao.find(hql);
		return li;
	}
	
	public List<Account> findByDefaultIncAccount(){
		String hql="from Account where defaultIncAccount = 1";
		return accountDao.find(hql);
	}

}
