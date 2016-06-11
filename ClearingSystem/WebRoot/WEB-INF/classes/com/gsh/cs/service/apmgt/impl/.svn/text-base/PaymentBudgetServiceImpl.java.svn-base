package com.gsh.cs.service.apmgt.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsh.cs.dao.BaseDaoI;
import com.gsh.cs.model.base.PayableView;
import com.gsh.cs.model.base.PaymentBudget;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.PaymentBudgetPRMT;
import com.gsh.cs.service.apmgt.PaymentBudgetServiceI;
@Transactional
@Service("paymentBudgetService")
public class PaymentBudgetServiceImpl implements PaymentBudgetServiceI {
	private BaseDaoI<PaymentBudget> PaymentBudgetDao;
	
	public BaseDaoI<PaymentBudget> getPaymentBudgetDao() {
		return PaymentBudgetDao;
	}
	@Autowired
	public void setPaymentBudgetDao(BaseDaoI<PaymentBudget> paymentBudgetDao) {
		PaymentBudgetDao = paymentBudgetDao;
	}

	public Datagrid findAll(PaymentBudgetPRMT p) {
		Datagrid dg=new Datagrid();
		String hql="from PaymentBudget p where p.status = 0 ", countHql = "select count(*) ";
		Map<String, Object> args = new HashMap<String, Object>();
		
		countHql += hql;
		hql += " group by p.currencyType ";
		dg.setRows(this.PaymentBudgetDao.find(hql, args, p.getPage(), p.getRows()));
		dg.setTotal(this.PaymentBudgetDao.count(countHql, args));
		
		//小计
		List<PaymentBudget> rbli=this.PaymentBudgetDao.find(hql, args, p.getPage(), p.getRows());
		List list=new ArrayList();
		Object[] ob=new Object[2];
		Object[] ob2=new Object[2];
		BigDecimal payBeAmount=new BigDecimal(0);
		BigDecimal payBeAmountC=new BigDecimal(0);
		for(PaymentBudget r:rbli){
			if(r.getCurrencyType().equals("CNY")){
				payBeAmountC=payBeAmountC.add(r.getPayBeAmount());
			}else{
				payBeAmount=payBeAmount.add(r.getPayBeAmount());
			}
			
		}
		if(!payBeAmount.equals(BigDecimal.ZERO)){
			ob[0]=payBeAmount;
			ob[1]="USD";
			list.add(ob);
		}
		if(!payBeAmountC.equals(BigDecimal.ZERO)){
			ob2[0]=payBeAmountC;
			ob2[1]="CNY";
			list.add(ob2);
		}
		

		//总计
		
		Object[] ob1=new Object[2];
		Object[] ob3=new Object[2];
		List<PaymentBudget> rbli1  =PaymentBudgetDao.find(hql,args);
		
		List list1=new ArrayList();
		BigDecimal payBeAmount1=new BigDecimal(0);
		
		BigDecimal payBeAmount1C=new BigDecimal(0);
		
		for(PaymentBudget r:rbli1){
			if(r.getCurrencyType().equals("CNY")){
				payBeAmount1C=payBeAmount1C.add(r.getPayBeAmount());
			}else{
				payBeAmount1=payBeAmount1.add(r.getPayBeAmount());
			}
			
		}
		
		if(!payBeAmount1.equals(BigDecimal.ZERO)){
			ob1[0]=payBeAmount1;
			ob1[1]="USD";
			list1.add(ob1);
		}
		if(!payBeAmount1C.equals(BigDecimal.ZERO)){
			ob3[0]=payBeAmount1C;
			ob3[1]="CNY";
			list1.add(ob3);
			
		}
		setFoolter(dg, list1,list);
		
		return dg;
	}

	/**
	 * 统计数据处理
	 * 
	 * @param dg
	 * @param zj
	 * @param xj
	 */
	private void setFoolter(Datagrid dg, List<Object[]> zj, List<Object[]> xj) {
		for (Object[] sum : zj) {
			Map foolter = new HashMap();
			foolter.put("payBeAmount", (BigDecimal) sum[0]);
			List l = new ArrayList();
			l.add(foolter);
			dg.getSumfooter().put(sum[1], l);
		}
		for (Object[] sum : xj) {
			Map foolter = new HashMap();
			foolter.put("payBeAmount", (BigDecimal) sum[0]);
			((List) dg.getSumfooter().get(sum[1])).add(foolter);
		}
	}

}
