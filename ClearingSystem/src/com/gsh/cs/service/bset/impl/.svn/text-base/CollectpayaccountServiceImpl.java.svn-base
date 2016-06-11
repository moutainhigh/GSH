package com.gsh.cs.service.bset.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsh.cs.dao.BaseDaoI;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.CollectpayAccount;
import com.gsh.cs.model.base.Receivable;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.AccountPRMT;
import com.gsh.cs.model.parameter.CollectpayAccountPRMT;
import com.gsh.cs.service.bset.CollectpayaccountServiceI;
import com.gsh.cs.tools.StringTool;
@Transactional
@Service("collectpayaccountService")
public class CollectpayaccountServiceImpl implements CollectpayaccountServiceI {
	private BaseDaoI<CollectpayAccount> collectpayaccountDao;
	
	public BaseDaoI<CollectpayAccount> getCollectpayaccountDao() {
		return collectpayaccountDao;
	}
	@Autowired
	public void setCollectpayaccountDao(
			BaseDaoI<CollectpayAccount> collectpayaccountDao) {
		this.collectpayaccountDao = collectpayaccountDao;
	}

	public Datagrid findAll(CollectpayAccountPRMT p,User user) {
		Datagrid dg = new Datagrid();
		String hql="from CollectpayAccount c where 1=1 ", countHql = "select count(*) ";
		Map<String, Object> params = new HashMap<String, Object>(0);
		if((p.getAccountSource()!=null)&&(p.getAccountSource()!=0)) {
			hql+=" and c.accountSource = :accountSource ";
			params.put("accountSource", p.getAccountSource());
		}
		if(p.getAccountCode()!=null&&!p.getAccountCode().equals("")){
			hql+=" and c.accountCode like :accountCode ";
			params.put("accountCode", "%"+p.getAccountCode()+"%");
		}
		if(p.getAccountName()!=null&&!p.getAccountName().equals("")){
			hql+=" and c.accountName like :accountName ";
			params.put("accountName", "%"+p.getAccountName()+"%");
		}
		if(p.getAccountType()!=null&&!p.getAccountType().equals("")){
			hql+=" and c.accountType = :accountType ";
			params.put("accountType", p.getAccountType());
		}
		hql+="and c.cid = "+user.getCid();
		countHql += hql;
		hql = setOrder(p, hql);
		hql +=" group by c.accountType";
		dg.setRows(this.collectpayaccountDao.find(hql, params, p.getPage(), p.getRows()));
		dg.setTotal(this.collectpayaccountDao.count(countHql, params));
		return dg;
	}
	
	
	public String setOrder(CollectpayAccountPRMT p, String hql) {
		if (!StringTool.isEmptyOrNull(p.getSort()) && null != p.getOrder()) {
			hql += " order by " + p.getSort() + " " + p.getOrder();
		}else{
			hql += " order by c.accountDate asc ";
		}
		return hql;
	}
	
	//新增
	public CollectpayAccount save(CollectpayAccount c) {
		String hql="from CollectpayAccount where accountCode = '"+c.getAccountCode()+"'";
		List<CollectpayAccount> li = collectpayaccountDao.find(hql);
		if(li.size()==0){
			c.setId((Long) this.collectpayaccountDao.save(c));
		}
		return c;
	}
	
	//修改
	public CollectpayAccount update(CollectpayAccountPRMT p) {
		CollectpayAccount c=new CollectpayAccount();
		BeanUtils.copyProperties(p, c);
		this.collectpayaccountDao.update(c);
		return c;
	}
	
	public void updateZH(CollectpayAccountPRMT p){
		String hql="update CollectpayAccount set accountCode='"+p.getAccountCode()+"',accountType='"+p.getAccountType()+"',accountName='"+p.getAccountName()+"' where accountCode='"+p.getAccountCode()+"'";
		collectpayaccountDao.executeHql(hql);
	}
	
	//查询所有List
	public List<CollectpayAccount> findAllList(String accountSource,String code,User user) {
		String hql="from CollectpayAccount c where c.cid = "+user.getCid()+" and c.accountSource = "+accountSource+" and c.accountStatus = 1";
		Map<String, Object> params = new HashMap<String, Object>(0);
		if(code!=null&&!code.equals("")){
			hql+=" and c.accountCode like :accountCode ";
			params.put("accountCode", "%"+code+"%");
		}
		return collectpayaccountDao.find(hql,params);
	}
	
	/**
	 * 查询应收应付账户类型
	 * not exists (select 1 from CollectpayAccount c2 where c2.accountType=c1.accountType and c2.id<c1.id)  and
	 */
	public List<CollectpayAccount> findAllType(String accountSource,String cid) {
		String hql = "from CollectpayAccount where cid = "+cid+" and accountSource = "+accountSource+" and accountStatus = 1";
		return collectpayaccountDao.find(hql);
	}
	
	

}
