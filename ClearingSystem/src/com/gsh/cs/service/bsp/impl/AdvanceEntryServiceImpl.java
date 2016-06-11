package com.gsh.cs.service.bsp.impl;

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
import com.gsh.cs.model.base.Account;
import com.gsh.cs.model.base.AdvanceEntry;
import com.gsh.cs.model.base.BspTicket;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.AdvanceEntryPRMT;
import com.gsh.cs.service.bsp.AdvanceEntryServiceI;
@Transactional
@Service("advanceEntryService")
public class AdvanceEntryServiceImpl implements AdvanceEntryServiceI {
	private BaseDaoI<AdvanceEntry> advanceEntryDao;
	
	public BaseDaoI<AdvanceEntry> getAdvanceEntryDao() {
		return advanceEntryDao;
	}
	@Autowired
	public void setAdvanceEntryDao(BaseDaoI<AdvanceEntry> advanceEntryDao) {
		this.advanceEntryDao = advanceEntryDao;
	}

	public Datagrid findAll(AdvanceEntryPRMT P) {
		Datagrid dg=new Datagrid();
		String hql="from AdvanceEntry a where 1=1", countHql = "select count(*) ", footerHql = "select sum(a.amountMoney) ,sum(a.invoiceMoney) ,a.currencyType ";
		Map<String, Object> params = new HashMap<String, Object>(0);
		if(P.getStatus()!=null) {
			hql+="and a.status = :status ";
			params.put("status", P.getStatus());
		}
		countHql += hql;
//		footerHql += hql + " group by a.currencyType";
		dg.setRows(this.advanceEntryDao.find(hql, params, P.getPage(), P.getRows()));
		dg.setTotal(this.advanceEntryDao.count(countHql, params));
//		setFoolter(dg, this.advanceEntryDao.sum(footerHql, params), this.advanceEntryDao.sum(footerHql, params, P.getPage(), P.getRows()));
		return dg;
	}
	
	/**
	 * 统计数据处理
	 * 
	 * @param dg
	 * @param zj
	 * @param xj
	 */
//	private void setFoolter(Datagrid dg, List<Object[]> zj, List<Object[]> xj) {
//		for (Object[] sum : zj) {
//			Map foolter = new HashMap();
//			foolter.put("amountMoney", (BigDecimal) sum[0]);
//			foolter.put("invoiceMoney", (BigDecimal) sum[1]);
//			List l = new ArrayList();
//			l.add(foolter);
//			dg.getSumfooter().put(sum[3], l);
//		}
//		for (Object[] sum : xj) {
//			Map foolter = new HashMap();
//			foolter.put("amountMoney", (BigDecimal) sum[0]);
//			foolter.put("invoiceMoney", (BigDecimal) sum[1]);
//			((List) dg.getSumfooter().get(sum[3])).add(foolter);
//		}
//	}
	
	public AdvanceEntry save(AdvanceEntryPRMT P) {
		try {
			AdvanceEntry a=new AdvanceEntry();
			BeanUtils.copyProperties(P, a);
			a.setId((Long) this.advanceEntryDao.save(a));
			return a;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public AdvanceEntry findBianli(String invoiceNo) {
		String hql="from AdvanceEntry";
		List<AdvanceEntry> ali= advanceEntryDao.find(hql);
		for(AdvanceEntry ae:ali){
			if(ae.getInvoiceNo().indexOf(",")!=-1){
				String [] str = ae.getInvoiceNo().split(",");
				for(int i=0;i<str.length;i++){
					if(str[i].equals(invoiceNo)){
						return ae;
					}
				}
			}else{
				if(ae.getInvoiceNo().trim().equals(invoiceNo.trim())){
					return ae;
				}
			}
		}
		return null;
	}

}
