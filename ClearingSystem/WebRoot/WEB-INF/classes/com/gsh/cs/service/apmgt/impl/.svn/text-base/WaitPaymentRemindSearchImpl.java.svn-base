package com.gsh.cs.service.apmgt.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsh.cs.dao.BaseDaoI;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.Payable;
import com.gsh.cs.model.base.Receivable;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.PayablePRMT;
import com.gsh.cs.service.apmgt.WaitPaymentRemindSearchI;
import com.gsh.cs.service.bset.SettlementExchangeRateServiceI;
import com.gsh.cs.service.bsp.DataPermissionServiceI;
@Service("waitPaymentRemindSearch")
public class WaitPaymentRemindSearchImpl implements WaitPaymentRemindSearchI {
	private BaseDaoI<Payable> payableDao;
	private SettlementExchangeRateServiceI settlementExchangeRateService;
	@Resource DataPermissionServiceI dataPermissionService;
	public BaseDaoI<Payable> getPayableDao() {
		return payableDao;
	}
	@Autowired
	public void setPayableDao(BaseDaoI<Payable> payableDao) {
		this.payableDao = payableDao;
	}

	public SettlementExchangeRateServiceI getSettlementExchangeRateService() {
		return settlementExchangeRateService;
	}

	@Autowired
	public void setSettlementExchangeRateService(SettlementExchangeRateServiceI settlementExchangeRateService) {
		this.settlementExchangeRateService = settlementExchangeRateService;
	}

	public Datagrid findAll(PayablePRMT p,User user) {
		Datagrid dg = new Datagrid();
		Date date = new Date();
		date.setDate(date.getDate() + 3);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(date);
		List<Payable> rbli=new ArrayList<Payable>();
		String hql="from Payable r1 where (r1.deadline <= '"+str+"') and r1.balance!=0 ",countHql = "select count(*) ";
		Map<String, Object> args = new HashMap<String, Object>();
		String str1 = dataPermissionService.findAllList(user);
		if(str1!=null&&!str1.equals("")){
			if (p.getAffiliationNo() != null && !p.getAffiliationNo().equals("")) {
				hql += "and r1.affiliationNo = :affiliationNo ";
				args.put("affiliationNo", p.getAffiliationNo());
			}else{
				hql += "and r1.affiliationNo in ("+str1+")";
			}
		}else{
			hql +="and r1.createrNo = "+user.getCid();
		}
		hql+=" group by r1.supplierNo,r1.deadline";
		List<Payable> li  =payableDao.find(hql,args, p.getPage(), p.getRows());
		for(Payable rb:li){
			Payable payable=new Payable();
			String hql1="from Payable where supplierNo = '"+rb.getSupplierNo()+"' and deadline = '"+rb.getDeadline()+"' and currencyType = '"+rb.getCurrencyType()+"'";
			List<Payable> li1  =payableDao.find(hql1);
			BigDecimal yingfu=new BigDecimal(0);
			BigDecimal yifu=new BigDecimal(0);
			BigDecimal yue=new BigDecimal(0);
			for(Payable r:li1){
				if(!r.getCurrencyType().equals("CNY")){
					yingfu=yingfu.add(new BigDecimal(settlementExchangeRateService.findCurrency(r.getPayBeAmount(), r.getCurrencyType())));
					yifu=yifu.add(new BigDecimal(settlementExchangeRateService.findCurrency(r.getPayAmount(), r.getCurrencyType())));
					yue=yue.add(new BigDecimal(settlementExchangeRateService.findCurrency(r.getBalance(), r.getCurrencyType())));
				}else{
					yingfu=yingfu.add(r.getPayBeAmount());
					yifu=yifu.add(r.getPayAmount());
					yue=yue.add(r.getBalance());
				}
			}
			payable.setPayBeAmount(yingfu);
			payable.setPayAmount(yifu);
			payable.setBalance(yue);
			payable.setSupplierNo(rb.getSupplierNo());
			payable.setDeadline(rb.getDeadline());
			payable.setCurrencyType(rb.getCurrencyType());
			rbli.add(payable);
		}
		countHql+=hql;
		dg.setRows(rbli);
		dg.setTotal((long)rbli.size());
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
			foolter.put("payAmount", (BigDecimal) sum[1]);
			foolter.put("balance", (BigDecimal) sum[2]);
			List l = new ArrayList();
			l.add(foolter);
			dg.getSumfooter().put(sum[3], l);
			System.out.println(sum[3]);
		}
		for (Object[] sum : xj) {
			Map foolter = new HashMap();
			foolter.put("payBeAmount", (BigDecimal) sum[0]);
			foolter.put("payAmount", (BigDecimal) sum[1]);
			foolter.put("balance", (BigDecimal) sum[2]);
			((List) dg.getSumfooter().get(sum[3])).add(foolter);
		}
	}
}
