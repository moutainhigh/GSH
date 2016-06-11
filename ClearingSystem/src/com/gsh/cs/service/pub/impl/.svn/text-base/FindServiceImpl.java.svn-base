package com.gsh.cs.service.pub.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsh.cs.dao.BaseDaoI;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.Account;
import com.gsh.cs.model.base.SettlementExchangeRate;
import com.gsh.cs.service.pub.FindServiceI;

@Transactional
@Service("findService")
public class FindServiceImpl implements FindServiceI {
	private BaseDaoI<Account> accountDao;
	private BaseDaoI<SettlementExchangeRate> settlementExchangeRateDao;

	@Autowired
	public void setSettlementExchangeRateDao(BaseDaoI<SettlementExchangeRate> settlementExchangeRateDao) {
		this.settlementExchangeRateDao = settlementExchangeRateDao;
	}

	@Autowired
	public void setAccountDao(BaseDaoI<Account> accountDao) {
		this.accountDao = accountDao;
	}

	public List<Account> findAll(String paymentMethod,User user) {
		String hql = "from Account t where t.accountStatus = 1";
		if(paymentMethod!=null&&!paymentMethod.equals("")){
			if(paymentMethod.equals("1")){//现金
				hql=hql+" and t.accountType in ('M','P')";
			}else if(paymentMethod.equals("2")){//信用卡
				hql=hql+" and t.accountType in ('C')";
			}else if(paymentMethod.equals("3")){//支票
				hql=hql+" and t.accountType in ('C','P')";
			}else if(paymentMethod.equals("4")){//转账汇款
				hql=hql+" and t.accountType in ('C','P')";
			}else if(paymentMethod.equals("5")){//内转
				hql=hql+" and t.accountType in ('C')";
			}else if(paymentMethod.equals("6")){//第三方支付
				hql=hql+" and t.accountType in ('O')";
			}
			hql=hql+" and t.accountLock = 0 and t.userId = "+user.getCid();
			
		}
		return this.accountDao.find(hql);
	}
	
	public List<Account> findAllType(String paymentMethod,User user) {
		String hql = "from Account t where t.accountStatus = 1 and t.accountLock = 0 and t.userId = "+user.getCid();
		if(paymentMethod!=null&&!paymentMethod.equals("")){
			if(paymentMethod.equals("6")){
				hql=hql+" and t.accountType in ('O')";
			}else{
				hql=hql+" and t.accountType in ('C')";
			}
			
		}
		List<Account> ali = this.accountDao.find(hql);
		List<Account> aali=new ArrayList<Account>();
		for(Account a:ali){
			if(a.getAccountType().equals("O")){
				Account aa=new Account();
				BeanUtils.copyProperties(a, aa);
				aa.setAccountBank(a.getAccountOwner());
				aa.setAccountNumber(a.getAccountBank());
				aali.add(aa);
			}else{
				aali.add(a);
			}
		}
		return aali;
	}

	public BigDecimal findRate(String currency,User user) {
		String hql = "from SettlementExchangeRate t where t.code = :code and t.userId = "+user.getCid();
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("code", currency);
		return this.settlementExchangeRateDao.find(hql, args).get(0).getSelling();
	}
}
