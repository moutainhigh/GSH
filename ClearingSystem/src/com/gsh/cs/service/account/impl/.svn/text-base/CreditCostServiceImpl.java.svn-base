package com.gsh.cs.service.account.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsh.cs.dao.BaseDaoI;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.CreditCost;
import com.gsh.cs.model.base.Receivable;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.CreditCostPRMT;
import com.gsh.cs.model.parameter.ReceivablePRMT;
import com.gsh.cs.service.account.CreditCostServiceI;
import com.gsh.cs.tools.StringTool;
@Transactional
@Service("creditCostService")
public class CreditCostServiceImpl implements CreditCostServiceI {
	private BaseDaoI<CreditCost> creditCostDao;
	
	public BaseDaoI<CreditCost> getCreditCostDao() {
		return creditCostDao;
	}
	@Autowired
	public void setCreditCostDao(BaseDaoI<CreditCost> creditCostDao) {
		this.creditCostDao = creditCostDao;
	}

	public Datagrid findAll(CreditCostPRMT P,User user) {
		Datagrid dg=new Datagrid();
		String hql="from CreditCost c where 1=1 ", countHql = "select count(*) ", footerHql = "select sum(c.saleMoney),sum(c.costAmount),sum(c.grossProfit) ";
		Map<String, Object> params = new HashMap<String, Object>(0);
		if(P.getQiDate()!=null) {
			hql+="and c.creditDate >= :qiDate ";
			params.put("qiDate", P.getQiDate());
		}
		if(P.getShiDate()!=null) {
			hql+="and c.creditDate <= :shiDate ";
			params.put("shiDate", P.getShiDate());
		}
		hql+=" and c.userId = "+user.getCid();
		countHql += hql;
		hql = setOrder(P, hql);
		footerHql += hql;
		dg.setRows(this.creditCostDao.find(hql, params, P.getPage(), P.getRows()));
		dg.setTotal(this.creditCostDao.count(countHql, params));
		
		List<CreditCost> rli=this.creditCostDao.find(hql, params, P.getPage(), P.getRows());
		List li=new ArrayList();
		BigDecimal saleMoney=new BigDecimal(0);
		BigDecimal costAmount=new BigDecimal(0);
		BigDecimal grossProfit=new BigDecimal(0);
		for(CreditCost r:rli){
			saleMoney=saleMoney.add(r.getSaleMoney());
			costAmount=costAmount.add(r.getCostAmount());
			grossProfit=grossProfit.add(r.getGrossProfit());
		}
		li.add(saleMoney);
		li.add(costAmount);
		li.add(grossProfit);
		dg.getFooter().add(formatterSum("小计：", (Object[])li.toArray()));
		dg.getFooter().add(formatterSum("总计：", (Object[]) this.creditCostDao.sum(footerHql, params).get(0)));
		
		return dg;
	}
	
	/**
	 * 统计数据处理
	 * 
	 * @param dg
	 * @param zj
	 * @param xj
	 */
	private Map formatterSum(String title, Object[] sum) {
		Map foolter = new HashMap();
		foolter.put("tradeNo", title);
		foolter.put("saleMoney", (BigDecimal) sum[0]);
		foolter.put("costAmount", (BigDecimal) sum[1]);
		foolter.put("grossProfit", (BigDecimal) sum[2]);
		return foolter;
	}

	/**
	 * 排序条件 处理
	 * 
	 * @param ReceivablePRMT
	 * @param hql
	 */
	public String setOrder(CreditCostPRMT p, String hql) {
		if (!StringTool.isEmptyOrNull(p.getSort()) && null != p.getOrder()) {
			hql += " order by " + p.getSort() + " " + p.getOrder();
		}else{
			hql += " order by creditDate asc ";
		}
		return hql;
	}

}
