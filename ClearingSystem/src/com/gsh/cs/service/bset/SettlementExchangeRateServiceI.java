package com.gsh.cs.service.bset;

import java.math.BigDecimal;
import java.util.List;

import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.SettlementExchangeRate;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.SettlementExchangeRatePRMT;

public interface SettlementExchangeRateServiceI {

	public SettlementExchangeRate save(SettlementExchangeRatePRMT t);

	public void del(Integer id);

	public SettlementExchangeRate edit(SettlementExchangeRatePRMT t);

	public Datagrid find(SettlementExchangeRatePRMT p,User user);
	
	public SettlementExchangeRate findBianli(String code,User user);
	
	/**
	 * 解锁/申请解锁
	 * @param id
	 * @param creditcardfeeLock
	 */
	public void updateByLock(String id,String settlementexchangerateLock);
	
	public List<SettlementExchangeRate> findList(String cid);
	
	/**
	 * 货币换算
	 */
	public Double findCurrency(BigDecimal money,String urrency);
	
	public void saveSoa(String cid);
}
