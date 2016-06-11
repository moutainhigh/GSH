package com.gsh.cs.service.bset.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsh.cs.dao.BaseDaoI;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.CurrencyUnit;
import com.gsh.cs.model.base.SettlementExchangeRate;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.CollectpayAccountPRMT;
import com.gsh.cs.model.parameter.SettlementExchangeRatePRMT;
import com.gsh.cs.service.bset.SettlementExchangeRateServiceI;
import com.gsh.cs.tools.LongTool;
import com.gsh.cs.tools.StringTool;

@Transactional
@Service("settlementExchangeRateService")
public class SettlementExchangeRateServiceImpl implements SettlementExchangeRateServiceI {

	private BaseDaoI<SettlementExchangeRate> settlementExchangeRateDao;
	private BaseDaoI<CurrencyUnit> currencyUnitDao;

	public BaseDaoI<SettlementExchangeRate> getSettlementExchangeRateDao() {
		return settlementExchangeRateDao;
	}

	@Autowired
	public void setSettlementExchangeRateDao(BaseDaoI<SettlementExchangeRate> settlementExchangeRateDao) {
		this.settlementExchangeRateDao = settlementExchangeRateDao;
	}
	
	public BaseDaoI<CurrencyUnit> getCurrencyUnitDao() {
		return currencyUnitDao;
	}
	@Autowired
	public void setCurrencyUnitDao(BaseDaoI<CurrencyUnit> currencyUnitDao) {
		this.currencyUnitDao = currencyUnitDao;
	}

	/**
	 * 添加
	 */
	public SettlementExchangeRate save(SettlementExchangeRatePRMT t) {
		try {
			SettlementExchangeRate b = new SettlementExchangeRate();
			BeanUtils.copyProperties(t, b, new String[] { "id" });
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			b.setEstablishDate(LongTool.stringTosqlDate(df.format(new Date())));
			b.setSettlementexchangerateLock(0);
			b.setDeptId("1");
			b.setAdminId((long)10);
			b.setId((Integer) this.settlementExchangeRateDao.save(b));
			return b;
		} catch (ParseException e) {
			e.printStackTrace();
		}
			return null;
	}
	
	
	
	/**
	 * 删除
	 */
	public void del(Integer id) {
		this.settlementExchangeRateDao.delete(this.settlementExchangeRateDao.get(SettlementExchangeRate.class, id));
	}
	
	/**
	 * 修改
	 */
	public SettlementExchangeRate edit(SettlementExchangeRatePRMT t) {
		try {
			SettlementExchangeRate b = new SettlementExchangeRate();
			BeanUtils.copyProperties(t, b);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			b.setEstablishDate(LongTool.stringTosqlDate(df.format(new Date())));
			b.setSettlementexchangerateLock(0);
			this.settlementExchangeRateDao.update(b);
			return b;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询
	 */
	public Datagrid find(SettlementExchangeRatePRMT p,User user) {
		Datagrid dg = new Datagrid(); 
		String hql = "from SettlementExchangeRate t where 1=1 ", countHql = "select count(*) ";
		Map<String, Object> args = new HashMap<String, Object>();
		if (!StringTool.isEmptyOrNull(p.getCode())) {
			hql += "and t.code = :code";
			args.put("code", p.getCode());
		}
		hql+=" and t.userId = "+user.getCid();
		countHql += hql;
		hql = setOrder(p, hql);
		dg.setRows(this.settlementExchangeRateDao.find(hql, args, p.getPage(), p.getRows()));
		dg.setTotal(this.settlementExchangeRateDao.count(countHql, args));
		return dg;
	}
	
	/**
	 * 排序条件 处理
	 * 
	 * @param SettlementExchangeRatePRMT
	 * @param hql
	 */
	public String setOrder(SettlementExchangeRatePRMT p, String hql) {
		if (!StringTool.isEmptyOrNull(p.getSort()) && null != p.getOrder()) {
			hql += " order by " + p.getSort() + " " + p.getOrder();
		}else{
			hql += " order by t.establishDate asc ";
		}
		return hql;
	}
	
	/**
	 * 判断是否唯一
	 */
	public SettlementExchangeRate findBianli(String code,User user) {
		String hql="from SettlementExchangeRate where code = '"+code+"' and userId = "+user.getCid();
		SettlementExchangeRate s= settlementExchangeRateDao.get(hql);
		return s;
	}
	/**
	 * 修改解锁
	 */
	public void updateByLock(String id, String settlementexchangerateLock) {
		String hql="update SettlementExchangeRate set settlementexchangerateLock = "+settlementexchangerateLock+" where id = "+id;
		settlementExchangeRateDao.executeHql(hql);
	}
	
	public List<SettlementExchangeRate> findList(String cid){
		String hql = "from SettlementExchangeRate where userId = "+cid;
		return this.settlementExchangeRateDao.find(hql);
	}

	public Double findCurrency(BigDecimal money,String currency){
		String hql="from SettlementExchangeRate where code = '"+currency+"'";
		SettlementExchangeRate ser = settlementExchangeRateDao.get(hql);
		if(ser!=null){
			money = money.multiply(ser.getSelling());
			return money.doubleValue();
		}
		return 0.0;
	}
	
	public void saveSoa(String cid){
		String hql="from CurrencyUnit";
		List<CurrencyUnit> culi=currencyUnitDao.find(hql);
		for(CurrencyUnit cu:culi){
			try {
				SettlementExchangeRate ser=new SettlementExchangeRate();
				ser.setBuying(new BigDecimal(1));
				ser.setSelling(new BigDecimal(1));
				ser.setCode(cu.getId());
				ser.setName(cu.getText());
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
				ser.setEstablishDate(LongTool.stringTosqlDate(df.format(new Date())));
				GregorianCalendar gc=new GregorianCalendar();
				gc.setTime(new Date());
				gc.add(1,+1);
				gc.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DATE));
				System.out.println(df.format(gc.getTime()));
				ser.setValidity(LongTool.stringTosqlDate(df.format(gc.getTime())));
				ser.setSettlementexchangerateLock(1);
				ser.setDeptId("1");
				ser.setAdminId((long)10);
				ser.setUserId(Long.parseLong(cid));
				settlementExchangeRateDao.save(ser);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}


}
