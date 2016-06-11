package com.gsh.cs.service.account.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gsh.cs.base.MessageException;
import com.gsh.cs.dao.BaseDaoI;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.Interface.BaseJsonResponse;
import com.gsh.cs.model.Interface.RouteProducts;
import com.gsh.cs.model.base.Account;
import com.gsh.cs.model.base.AccountDetail;
import com.gsh.cs.model.base.Commission;
import com.gsh.cs.model.base.CommissionDetail;
import com.gsh.cs.model.base.CommissionPayment;
import com.gsh.cs.model.base.CustomerPayment;
import com.gsh.cs.model.base.Payable;
import com.gsh.cs.model.base.PayableDetail;
import com.gsh.cs.model.base.Receivable;
import com.gsh.cs.model.base.SupplierPayment;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.CommissionPRMT;
import com.gsh.cs.service.account.CommissionServiceI;
import com.gsh.cs.service.bset.SettlementExchangeRateServiceI;
import com.gsh.cs.service.bsp.DataPermissionServiceI;
import com.gsh.cs.tools.SoaPropertiesUtil;
import com.gsh.cs.tools.httpClient;
@Transactional
@Service("commissionService")
public class CommissionServiceImpl implements CommissionServiceI{
	private BaseDaoI<Commission> commissionDao;
	private BaseDaoI<SupplierPayment> supplierPaymentDao;
	private BaseDaoI<CustomerPayment> customerPaymentDao;
	@Resource DataPermissionServiceI dataPermissionService;
	@Resource SettlementExchangeRateServiceI settlementExchangeRateService;
	@Autowired
	public void setSupplierPaymentDao(BaseDaoI<SupplierPayment> supplierPaymentDao) {
		this.supplierPaymentDao = supplierPaymentDao;
	}
	@Autowired
	public void setCustomerPaymentDao(BaseDaoI<CustomerPayment> customerPaymentDao) {
		this.customerPaymentDao = customerPaymentDao;
	}
	public BaseDaoI<Commission> getCommissionDao() {
		return commissionDao;
	}
	@Autowired
	public void setCommissionDao(BaseDaoI<Commission> commissionDao) {
		this.commissionDao = commissionDao;
	}
	
	private BaseDaoI<CommissionPayment> commissionPaymentDao;

	public BaseDaoI<CommissionPayment> getCommissionPaymentDao() {
		return commissionPaymentDao;
	}
	@Autowired
	public void setCommissionPaymentDao(
			BaseDaoI<CommissionPayment> commissionPaymentDao) {
		this.commissionPaymentDao = commissionPaymentDao;
	}
	private BaseDaoI<CommissionDetail> commissionDetailDao;
	
	public BaseDaoI<CommissionDetail> getCommissionDetailDao() {
		return commissionDetailDao;
	}
	@Autowired
	public void setCommissionDetailDao(
			BaseDaoI<CommissionDetail> commissionDetailDao) {
		this.commissionDetailDao = commissionDetailDao;
	}
	private BaseDaoI<Account> accountDao;
	
	public BaseDaoI<Account> getAccountDao() {
		return accountDao;
	}
	@Autowired
	public void setAccountDao(BaseDaoI<Account> accountDao) {
		this.accountDao = accountDao;
	}
	private BaseDaoI<AccountDetail> accountDetailDao;
	
	
	public BaseDaoI<AccountDetail> getAccountDetailDao() {
		return accountDetailDao;
	}
	@Autowired
	public void setAccountDetailDao(BaseDaoI<AccountDetail> accountDetailDao) {
		this.accountDetailDao = accountDetailDao;
	}
	public Datagrid findAll(CommissionPRMT P,User user) {
		Datagrid dg=new Datagrid();
		String footerHql = "select sum(c.incomeBeAmount) ,sum(c.incomeAmount) ,sum(c.balance) ,c.currencyType from Commission c ";
		String footerHql2_center="select * from commission c ";
		String hql = " where c.balance != 0 ", countHql = "select count(*) ";
//		String hql="from Commission c where c.balance != 0 ",countHql = "select count(*) ", footerHql = "select sum(c.incomeBeAmount) ,sum(c.incomeAmount) ,sum(c.balance) ,c.currencyType ";
		Map<String, Object> params = new HashMap<String, Object>(0);
		if(P.getProductRoute()!=null&&!P.getProductRoute().equals("")) {
			hql+="and c.productRoute = :productRoute ";
			params.put("productRoute", P.getProductRoute());
		}
		if(P.getSupplierNo()!=null&&!P.getSupplierNo().equals("")) {
			hql+="and c.supplierNo = :supplierNo ";
			params.put("supplierNo", P.getSupplierNo());
		}
		if(P.getCarrier()!=null&&!P.getCarrier().equals("")) {
			hql+="and c.carrier = :carrier ";
			params.put("carrier", P.getCarrier());
		}
		if(P.getQiDate()!=null) {
			hql+="and c.tradeDate >= :qiDate ";
			params.put("qiDate", P.getQiDate());
		}
		if(P.getShiDate()!=null) {
			hql+="and c.tradeDate <= :shiDate ";
			params.put("shiDate", P.getShiDate());
		}
		String str = dataPermissionService.findAllList(user);
		if(str!=null&&!str.equals("")){
			hql += "and c.affiliationNo in ("+str+")";
		}else{
			hql +="and c.createrNo = "+user.getCid();
		}
		footerHql2_center += hql;
		//小计
		String footerHql2="select sum(c.incomeBeAmount) ,sum(c.incomeAmount) ,sum(c.balance) ,c.currencyType from ("+footerHql2_center+" limit "+((P.getPage()-1)*P.getRows())+","+P.getRows()+") c ";
		footerHql += hql+" group by c.currencyType";
		footerHql2 += hql+" group by c.currencyType";
		hql="from Commission c "+hql;
		countHql += hql;
		System.out.println(footerHql);
		System.out.println(footerHql2);
//		List<Commission> li = this.commissionDao.find(hql, params, P.getPage(), P.getRows());
		dg.setRows(this.commissionDao.find(hql, params, P.getPage(), P.getRows()));
		dg.setTotal(this.commissionDao.count(countHql, params));
		List<Commission> li = this.commissionDao.find(hql, params, P.getPage(), P.getRows());
		List<Commission> li1 = this.commissionDao.find(hql, params);
		dg.getFooter().add(formatterSum("小计：", sumCurrencySum(li)));
		dg.getFooter().add(formatterSum("总计：", sumCurrencySum(li1)));
//		setFoolter(dg, this.commissionDao.sum(footerHql, params), this.commissionDao.sum2(footerHql2, params));
		return dg;
	}
	
	public Object[] sumCurrencySum(List<Commission> li){
		Object[] paySum=new Object[6];//小计
		BigDecimal pay1=new BigDecimal(0);
		BigDecimal pay2=new BigDecimal(0);
		BigDecimal pay3=new BigDecimal(0);
		for (Commission pab : li) {
			if(!pab.getCurrencyType().equals("CNY")){
				pay1 = pay1.add(new BigDecimal(settlementExchangeRateService.findCurrency(pab.getIncomeBeAmount(), pab.getCurrencyType())));
				pay2=pay2.add(new BigDecimal(settlementExchangeRateService.findCurrency(pab.getIncomeAmount(), pab.getCurrencyType())));
				pay3=pay3.add(new BigDecimal(settlementExchangeRateService.findCurrency(pab.getBalance(), pab.getCurrencyType())));
			}else{
				pay1 = pay1.add(pab.getIncomeBeAmount());
				pay2=pay2.add(pab.getIncomeAmount());
				pay3=pay3.add(pab.getBalance());
			}
			
		}
		paySum[0]=pay1;
		paySum[1]=pay2;
		paySum[2]=pay3;
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
		foolter.put("supplierNo", title);
		foolter.put("incomeBeAmount", (BigDecimal) sum[0]);
		foolter.put("incomeAmount", (BigDecimal) sum[1]);
		foolter.put("balance", (BigDecimal) sum[2]);
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
			foolter.put("incomeBeAmount", (BigDecimal) sum[0]);
			foolter.put("incomeAmount", (BigDecimal) sum[1]);
			foolter.put("balance", (BigDecimal) sum[2]);
			List l = new ArrayList();
			l.add(foolter);
			dg.getSumfooter().put(sum[3], l);
		}
		for (Object[] sum : xj) {
			Map foolter = new HashMap();
			foolter.put("incomeBeAmount", (BigDecimal) sum[0]);
			foolter.put("incomeAmount", (BigDecimal) sum[1]);
			foolter.put("balance", (BigDecimal) sum[2]);
			((List) dg.getSumfooter().get(sum[3])).add(foolter);
		}
	}
	
	public void payment(CommissionPayment sp, List<CommissionDetail> cdl) throws MessageException {
		try {
			//添加一条明细
			sp.setCreateDate(new Date());
			sp.setSettlementNo(UUID.randomUUID().toString());
			sp.setStatus(1);
			sp.setId((Long)this.commissionPaymentDao.save(sp));
			//添加多条或者一条commission_detail
			for(CommissionDetail cd:cdl){
				//修改一条或者多条佣金信息
				Commission c=this.commissionDao.get(Commission.class, cd.getCmsnid());
				c.setBalance(c.getBalance().subtract(cd.getIncomeAmount()));
				c.setIncomeAmount(c.getIncomeAmount().add(cd.getIncomeAmount()));
				cd.setCmpmid(sp.getId());
				commissionDetailDao.save(cd);
				commissionDao.update(c);
			}
			//账户余额加上已收入的佣金
			String hql = "from Account t where t.accountCode = :accountCode";
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("accountCode", sp.getFundAccount());
			Account at = this.accountDao.find(hql, args).get(0);
			at.setAccountBalance(at.getAccountBalance().add(sp.getIncomeAmount()));
			// 记录账户明细
			AccountDetail ad = new AccountDetail();
			ad.setAccountCode(at.getAccountCode());
			ad.setAccountBank(at.getAccountBank());
			ad.setTradeDate(sp.getTradeDate());
			ad.setTradeObject(sp.getCarrier());
			ad.setIncome(sp.getIncomeAmount());
			ad.setExpenditure(BigDecimal.ZERO);
			ad.setBalance(at.getAccountBalance().abs() );
			ad.setVoucherNo(sp.getVoucherNo());
			ad.setRemark(sp.getRemark());
			ad.setStatus(1);
			ad.setType(3);
			ad.setSettlementNo(jiesuanCode("RCV"));
			ad.setSubjects("佣金收入");
			ad.setCurrencyType(at.getAccountCurrency());
			// 保存业务明细
			this.accountDetailDao.save(ad);
			// 记录手续费
			if (sp.getFactorage().compareTo(BigDecimal.ZERO) != 0) {
				// 计算账户余额
				at.setAccountBalance(at.getAccountBalance().subtract(sp.getFactorage()));
				AccountDetail adfee = new AccountDetail();
				BeanUtils.copyProperties(ad, adfee);
				adfee.setIncome(BigDecimal.ZERO);
				adfee.setExpenditure(sp.getFactorage().abs());
				adfee.setBalance(at.getAccountBalance());
				adfee.setSubjects("手续费");
				this.accountDetailDao.save(adfee);
			}
			this.accountDao.update(at);
			this.commissionPaymentDao.update(sp);
		} catch (BeansException e) {
			e.printStackTrace();
		}
		
	}
	public List<Commission> findsupplierNo() {
		String hql="from Commission c1 where not exists (select 1 from Commission c2 where c2.supplierNo=c1.supplierNo and c2.id<c1.id)";
		return commissionDao.find(hql);
	}
	public List<CommissionPRMT> findProductRoute() {
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		List<CommissionPRMT> cpli=new ArrayList<CommissionPRMT>();
		String hql="from Commission c1 where not exists (select 1 from Commission c2 where c2.productRoute=c1.productRoute and c2.id<c1.id)";
		List<Commission> cli=commissionDao.find(hql);
		for(Commission c:cli){
			CommissionPRMT cp=new CommissionPRMT();
			BeanUtils.copyProperties(c, cp);
			Map<String, String> params = new HashMap<String, String>(0);
			params.put("id", c.getProductRoute());
			String url=SoaPropertiesUtil.soaUrl.getProperty("routeProductsById");
			String json = httpClient.getDoPostResponseDataByURL(url, params,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			ArrayList<RouteProducts> rplist = (ArrayList<RouteProducts>) JSON
					.parseArray("["+bjr.getInfo()+"]", RouteProducts.class);
			cp.setProductRouteName(rplist.get(0).getText());
			cpli.add(cp);
		}
		return cpli;
	}
	public List<Commission> findcarrier() {
		String hql="from Commission c1 where not exists (select 1 from Commission c2 where c2.carrier=c1.carrier and c2.id<c1.id)";
		return commissionDao.find(hql);
	}
	
	public void save(Commission c) {
		String hql="from Commission where productOrderNo = '"+c.getProductOrderNo()+"'";
		List<Commission> li = commissionDao.find(hql);
		if(li.size()==0&&c.getBalance().compareTo(BigDecimal.ZERO)==0){
			commissionDao.save(c);
		}
	}
	
	
	/**
	 * 13位数：3位编码+8位年月日+2位
	 *	Eg：PMT2014072110
	 * @return
	 */
	public String jiesuanCode(String ar){
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		int month = cal.get(Calendar.MONTH) + 1;
		String year = cal.get(Calendar.YEAR)+"";
		String y = year+month+day;
		String xuhao="";
		String hql="select count(*) from SupplierPayment where settlementNo like  '%"+y+"%'";
		Long count = supplierPaymentDao.count(hql);
		
		String hql1="select count(*) from CustomerPayment where settlementNo like  '%"+y+"%'";
		Long count1 = customerPaymentDao.count(hql1);
	
		String hql2="select count(*) from CommissionPayment where settlementNo like  '%"+y+"%'";
		Long count2 =commissionPaymentDao.count(hql2);
		
		count=count+count1+count2;
		if(count!=0){
			if(count>=9){
				count=count+1;
				xuhao=count.toString();	
			}else{
				count=count+1;
				xuhao=0+count.toString();
			}
		}else{
			xuhao="01";
		}
		return ar+y+xuhao;
	}
	


}
