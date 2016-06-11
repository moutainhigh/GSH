package com.gsh.cs.service.bset.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsh.cs.dao.BaseDaoI;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.Account;
import com.gsh.cs.model.base.CreditcardFee;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.CollectpayAccountPRMT;
import com.gsh.cs.model.parameter.CreditcardFeePRMT;
import com.gsh.cs.service.bset.CreditcardfeeServiceI;
import com.gsh.cs.tools.LongTool;
import com.gsh.cs.tools.StringTool;
@Transactional
@Service("creditcardfeeService")
public class CreditcardfeeServiceImpl implements CreditcardfeeServiceI {
	private BaseDaoI<CreditcardFee> creditcardfeeDao;
	
	public BaseDaoI<CreditcardFee> getCreditcardfeeDao() {
		return creditcardfeeDao;
	}
	@Autowired
	public void setCreditcardfeeDao(BaseDaoI<CreditcardFee> creditcardfeeDao) {
		this.creditcardfeeDao = creditcardfeeDao;
	}
	/**
	 * 查询所有，多条件查询
	 * @param P
	 * @return
	 */
	public Datagrid findAll(CreditcardFeePRMT p,User user) {
		Datagrid dg = new Datagrid();
		String hql="from CreditcardFee c where 1=1 ", countHql = "select count(*) ";
		Map<String, Object> params = new HashMap<String, Object>(0);
		if(!StringTool.isEmptyOrNull(p.getCreditCode())) {
			hql+="and c.creditCode = :creditCode ";
			params.put("creditCode", p.getCreditCode());
		}
		hql+=" and c.userId="+user.getCid();
		countHql += hql;
		hql = setOrder(p, hql);
		dg.setRows(this.creditcardfeeDao.find(hql, params, p.getPage(), p.getRows()));
		dg.setTotal(this.creditcardfeeDao.count(countHql, params));
		return dg;
	}
	
	public String setOrder(CreditcardFeePRMT p, String hql) {
		if (!StringTool.isEmptyOrNull(p.getSort()) && null != p.getOrder()) {
			hql += " order by " + p.getSort() + " " + p.getOrder();
		}else{
			hql += " order by c.establishDate asc ";
		}
		return hql;
	}
	
	/**
	 * 新建
	 * @param P
	 * @return
	 */
	public CreditcardFee save(CreditcardFeePRMT p) {
		try {
			CreditcardFee c=new CreditcardFee();
			BeanUtils.copyProperties(p, c);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			c.setEstablishDate(LongTool.stringTosqlDate(df.format(new Date())));
			c.setValidtime(new Date());
			c.setCreditcardfeeLock(0);
			c.setDeptId("1");
			c.setAdminId((long)10);
			c.setId((Long) this.creditcardfeeDao.save(c));
			return c;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 修改
	 * @param P
	 * @return
	 */
	public CreditcardFee update(CreditcardFeePRMT p) {
		try {
			CreditcardFee c=new CreditcardFee();
			BeanUtils.copyProperties(p, c);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			c.setEstablishDate(LongTool.stringTosqlDate(df.format(new Date())));
			c.setValidtime(LongTool.stringTosqlDate(df.format(new Date())));
			c.setCreditcardfeeLock(0);
			this.creditcardfeeDao.update(c);
			return c;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 删除
	 * @param P
	 */
	public void delete(CreditcardFeePRMT p) {
		CreditcardFee c=new CreditcardFee();
		BeanUtils.copyProperties(p, c);
		this.creditcardfeeDao.delete(c);
	}
	/**
	 * 解锁
	 * @param id
	 * @param creditcardfeeLock
	 */
	public void updateByCreditcardfeeLock(String id,String creditcardfeeLock) {
		String hql="update CreditcardFee set creditcardfeeLock = "+creditcardfeeLock+" where id = "+id;
		creditcardfeeDao.executeHql(hql);
	}
	
	/**
	 * 判断是否唯一
	 */
	public CreditcardFee findBianli(String code,User user) {
		String hql="from CreditcardFee where creditCode = '"+code+"' and userId = "+user.getCid();
		CreditcardFee c= creditcardfeeDao.get(hql);
		return c;
	}

}
