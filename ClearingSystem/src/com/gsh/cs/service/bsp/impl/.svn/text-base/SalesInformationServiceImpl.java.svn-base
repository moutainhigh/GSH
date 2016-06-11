package com.gsh.cs.service.bsp.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsh.cs.dao.BaseDaoI;
import com.gsh.cs.model.Interface.CreateByHand;
import com.gsh.cs.model.base.SalesInformation;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.SalesInformationPRMT;
import com.gsh.cs.service.bsp.SalesInformationServiceI;
import com.gsh.cs.tools.DateTool;
@Transactional
@Service("salesInformationService")
public class SalesInformationServiceImpl implements SalesInformationServiceI {
	@Resource BaseDaoI<SalesInformation> salesInformationDao;
	
	public Datagrid findAll(SalesInformationPRMT p) {
		Datagrid dg=new Datagrid();
		Map<String, Object> params = new HashMap<String, Object>(0);
		String hql="from SalesInformation where 1=1 ",countHql = "select count(*) ";
		countHql += hql;
		dg.setRows(this.salesInformationDao.find(hql, params, p.getPage(), p.getRows()));
		dg.setTotal(this.salesInformationDao.count(countHql, params));
		return dg;
	}

	public void saveCreateByHand(CreateByHand cb) {
		SalesInformation sf=new SalesInformation();
		BeanUtils.copyProperties(cb, sf);
		sf.setStatus(1);
		sf.setCurrencyType("CNY");
		sf.setCreateDate(DateTool.getSystemTimestamp());
		salesInformationDao.save(sf);
	}

	public SalesInformation update(SalesInformationPRMT p) {
		SalesInformation sf=new SalesInformation();
		BeanUtils.copyProperties(p, sf);
		sf.setStatus(2);
		salesInformationDao.update(sf);
		return sf;
	}

}
