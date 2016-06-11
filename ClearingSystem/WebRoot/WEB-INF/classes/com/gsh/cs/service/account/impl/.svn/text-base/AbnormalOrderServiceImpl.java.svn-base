package com.gsh.cs.service.account.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsh.cs.dao.BaseDaoI;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.AbnormalOrder;
import com.gsh.cs.model.base.Payable;
import com.gsh.cs.model.base.PayableView;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.AbnormalOrderPRMT;
import com.gsh.cs.service.account.AbnormalOrderServiceI;
import com.gsh.cs.service.bset.SettlementExchangeRateServiceI;

@Transactional
@Service("abnormalOrderService")
public class AbnormalOrderServiceImpl implements AbnormalOrderServiceI {
	private BaseDaoI<AbnormalOrder> abnormalOrderDao;
	@Resource SettlementExchangeRateServiceI settlementExchangeRateService;
	public BaseDaoI<AbnormalOrder> getAbnormalOrderDao() {
		return abnormalOrderDao;
	}
	@Autowired
	public void setAbnormalOrderDao(BaseDaoI<AbnormalOrder> abnormalOrderDao) {
		this.abnormalOrderDao = abnormalOrderDao;
	}

	public Datagrid findAll(AbnormalOrderPRMT p,User user) {
		Datagrid dg=new Datagrid();
		String hql="from AbnormalOrder a", countHql = "select count(*) ", footerHql = "select sum(a.balance) ,a.currencyType ";
		Map<String, Object> args = new HashMap<String, Object>();
		hql+=" where a.createrNo = '"+user.getCid()+"'";
		countHql += hql;
		footerHql +=" group by a.currencyType";
		dg.setRows(this.abnormalOrderDao.find(hql, args, p.getPage(), p.getRows()));
		dg.setTotal(this.abnormalOrderDao.count(countHql, args));
		
		//小计
		List<AbnormalOrder> rbli=this.abnormalOrderDao.find(hql, args, p.getPage(), p.getRows());
		//总计
		List<AbnormalOrder> rbli1  =abnormalOrderDao.find(hql,args);
		dg.getFooter().add(formatterSum("小计：", sumCurrencySum(rbli)));
		dg.getFooter().add(formatterSum("总计：", sumCurrencySum(rbli1)));
//		setFoolter(dg, list1, list);
		return dg;
	}
	public Object[] sumCurrencySum(List<AbnormalOrder> li){
		Object[] paySum=new Object[6];//小计
		BigDecimal pay1=new BigDecimal(0);
		for (AbnormalOrder pab : li) {
				if(!pab.getCurrencyType().equals("CNY")){
					pay1 = pay1.add(new BigDecimal(settlementExchangeRateService.findCurrency(pab.getBalance(), pab.getCurrencyType())));
				}else{
					pay1 = pay1.add(pab.getBalance());
				}
		}
		paySum[0]=pay1;
		return paySum;
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
		foolter.put("exchangeNo", title);
		foolter.put("balance", (BigDecimal) sum[0]);
		return foolter;
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
			foolter.put("balance", (BigDecimal) sum[0]);
			List l = new ArrayList();
			l.add(foolter);
			dg.getSumfooter().put(sum[1], l);
		}
		for (Object[] sum : xj) {
			Map foolter = new HashMap();
			foolter.put("balance", (BigDecimal) sum[0]);
			((List) dg.getSumfooter().get(sum[1])).add(foolter);
		}
	}
	
	public void save(AbnormalOrder ao){
		String hql="from AbnormalOrder where exchangeNo = '"+ao.getExchangeNo()+"'";
		List<AbnormalOrder> li = abnormalOrderDao.find(hql);
		if(li.size()==0){
			abnormalOrderDao.save(ao);
		}
	}
	
	public void dalete(String exchangeNo){
		String hql="delete from AbnormalOrder where exchangeNo = '"+exchangeNo+"'";
		abnormalOrderDao.executeHql(hql);
	}

	
}	
