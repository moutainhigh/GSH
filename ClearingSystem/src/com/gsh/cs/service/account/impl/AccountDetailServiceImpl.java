package com.gsh.cs.service.account.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsh.cs.base.MessageException;
import com.gsh.cs.dao.BaseDaoI;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.Account;
import com.gsh.cs.model.base.AccountDetail;
import com.gsh.cs.model.base.Commission;
import com.gsh.cs.model.base.CommissionDetail;
import com.gsh.cs.model.base.CommissionPayment;
import com.gsh.cs.model.base.CustomerPayment;
import com.gsh.cs.model.base.Payable;
import com.gsh.cs.model.base.PayableDetail;
import com.gsh.cs.model.base.PayableView;
import com.gsh.cs.model.base.Receivable;
import com.gsh.cs.model.base.ReceivableDetail;
import com.gsh.cs.model.base.ReceivableView;
import com.gsh.cs.model.base.SupplierPayment;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.AccountDetailPRMT;
import com.gsh.cs.model.parameter.DetailedPRMT;
import com.gsh.cs.model.parameter.PayablePRMT;
import com.gsh.cs.service.account.AccountDetailServiceI;
import com.gsh.cs.service.bset.SettlementExchangeRateServiceI;
import com.gsh.cs.tools.StringTool;
@Transactional
@Service("accountDetailService")
public class AccountDetailServiceImpl implements AccountDetailServiceI {
	private BaseDaoI<AccountDetail> accountDetailDao;
	private BaseDaoI<ReceivableView> receivableViewDao;
	private BaseDaoI<PayableView> payableViewDao;
	@Resource SettlementExchangeRateServiceI settlementExchangeRateService;
	public BaseDaoI<AccountDetail> getAccountDetailDao() {
		return accountDetailDao;
	}
	@Autowired
	public void setAccountDetailDao(BaseDaoI<AccountDetail> accountDetailDao) {
		this.accountDetailDao = accountDetailDao;
	}
	private BaseDaoI<SupplierPayment> supplierPaymentDao;
	
	public BaseDaoI<SupplierPayment> getSupplierPaymentDao() {
		return supplierPaymentDao;
	}
	@Autowired
	public void setSupplierPaymentDao(BaseDaoI<SupplierPayment> supplierPaymentDao) {
		this.supplierPaymentDao = supplierPaymentDao;
	}
	
	private BaseDaoI<PayableDetail> payableDetailDao;
	
	public BaseDaoI<PayableDetail> getPayableDetailDao() {
		return payableDetailDao;
	}
	@Autowired
	public void setPayableDetailDao(BaseDaoI<PayableDetail> payableDetailDao) {
		this.payableDetailDao = payableDetailDao;
	}
	
	private BaseDaoI<Payable> payableDao;

	public BaseDaoI<Payable> getPayableDao() {
		return payableDao;
	}
	@Autowired
	public void setPayableDao(BaseDaoI<Payable> payableDao) {
		this.payableDao = payableDao;
	}
	
	private BaseDaoI<Account> accountDao;
	
	public BaseDaoI<Account> getAccountDao() {
		return accountDao;
	}
	@Autowired
	public void setAccountDao(BaseDaoI<Account> accountDao) {
		this.accountDao = accountDao;
	}
	
	private BaseDaoI<CustomerPayment> customerPaymentDao;
	
	
	public BaseDaoI<CustomerPayment> getCustomerPaymentDao() {
		return customerPaymentDao;
	}
	@Autowired
	public void setCustomerPaymentDao(BaseDaoI<CustomerPayment> customerPaymentDao) {
		this.customerPaymentDao = customerPaymentDao;
	}
	private BaseDaoI<ReceivableDetail> receivableDetailDao;
	
	public BaseDaoI<ReceivableDetail> getReceivableDetailDao() {
		return receivableDetailDao;
	}
	@Autowired
	public void setReceivableDetailDao(
			BaseDaoI<ReceivableDetail> receivableDetailDao) {
		this.receivableDetailDao = receivableDetailDao;
	}
	
	private BaseDaoI<Receivable> receivableDao;
	public BaseDaoI<Receivable> getReceivableDao() {
		return receivableDao;
	}
	@Autowired
	public void setReceivableDao(BaseDaoI<Receivable> receivableDao) {
		this.receivableDao = receivableDao;
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
	
	private BaseDaoI<Commission> commissionDao;
	
	public BaseDaoI<Commission> getCommissionDao() {
		return commissionDao;
	}
	@Autowired
	public void setCommissionDao(BaseDaoI<Commission> commissionDao) {
		this.commissionDao = commissionDao;
	}
	
	public BaseDaoI<ReceivableView> getReceivableViewDao() {
		return receivableViewDao;
	}
	@Autowired
	public void setReceivableViewDao(BaseDaoI<ReceivableView> receivableViewDao) {
		this.receivableViewDao = receivableViewDao;
	}
	
	public BaseDaoI<PayableView> getPayableViewDao() {
		return payableViewDao;
	}
	@Autowired
	public void setPayableViewDao(BaseDaoI<PayableView> payableViewDao) {
		this.payableViewDao = payableViewDao;
	}
	/**
	 * 查询应收应付记录
	 * @return
	 */
	public Datagrid findCollectPay(AccountDetailPRMT P,User user) {
		Datagrid dg=new Datagrid();
		String footerHql = "select sum(c.income) ,sum(c.expenditure) ,sum(c.balance) ,c.currencyType from AccountDetail c ";
		String footerHql2_center="select * from account_detail c ";
		String hql = " where c.status != 3 ", countHql = "select count(*) ";
//		String hql="from AccountDetail c where status != 3 ", countHql = "select count(*) ";
		Map<String, Object> params = new HashMap<String, Object>(0);
		if(P.getType()!=null&&P.getType()!=0) {
			hql+="and c.type = :type ";
			params.put("type", P.getType());
		}
		if(P.getTradeObject()!=null&&!P.getTradeObject().equals("")) {
			hql+="and c.tradeObject = :tradeObject ";
			params.put("tradeObject", P.getTradeObject());
		}
		if(P.getQiDate()!=null) {
			hql+="and c.tradeDate >= :qiDate ";
			params.put("qiDate", P.getQiDate());
		}
		if(P.getShiDate()!=null) {
			hql+="and c.tradeDate <= :shiDate ";
			params.put("shiDate", P.getShiDate());
		}
		footerHql2_center += hql;
		//小计
		String footerHql2="select sum(c.income) ,sum(c.expenditure) ,sum(c.balance) ,c.currencyType from ("+footerHql2_center+" limit "+((P.getPage()-1)*P.getRows())+","+P.getRows()+") c ";
		footerHql += hql+" group by c.currencyType";
		footerHql2 += hql+" group by c.currencyType";
		hql="from AccountDetail c "+hql;
		hql+=" and c.cid = "+user.getCid();
		countHql += hql;
		dg.setRows(this.accountDetailDao.find(hql, params, P.getPage(), P.getRows()));
		dg.setTotal(this.accountDetailDao.count(countHql, params));
		List<AccountDetail> li = this.accountDetailDao.find(hql, params, P.getPage(), P.getRows());
		List<AccountDetail> li1 = this.accountDetailDao.find(hql, params);
		dg.getFooter().add(formatterSum("小计：", sumCurrencySum(li)));
		dg.getFooter().add(formatterSum("总计：", sumCurrencySum(li1)));
//		setFoolter(dg, this.accountDetailDao.sum(footerHql, params), this.accountDetailDao.sum2(footerHql2, params));
		return dg;
	}
	
	public Object[] sumCurrencySum(List<AccountDetail> li){
		Object[] paySum=new Object[6];//小计
		BigDecimal pay1=new BigDecimal(0);
		BigDecimal pay2=new BigDecimal(0);
		BigDecimal pay3=new BigDecimal(0);
		for (AccountDetail pab : li) {
			if(!pab.getCurrencyType().equals("CNY")){
				pay1 = pay1.add(new BigDecimal(settlementExchangeRateService.findCurrency(pab.getIncome(), pab.getCurrencyType())));
				pay2=pay2.add(new BigDecimal(settlementExchangeRateService.findCurrency(pab.getExpenditure(), pab.getCurrencyType())));
				pay3=pay3.add(new BigDecimal(settlementExchangeRateService.findCurrency(pab.getBalance(), pab.getCurrencyType())));
			}else{
				pay1 = pay1.add(pab.getIncome());
				pay2=pay2.add(pab.getExpenditure());
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
		foolter.put("tradeDate", title);
		foolter.put("income", (BigDecimal) sum[0]);
		foolter.put("expenditure", (BigDecimal) sum[1]);
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
			foolter.put("income", (BigDecimal) sum[0]);
			foolter.put("expenditure", (BigDecimal) sum[1]);
			foolter.put("balance", (BigDecimal) sum[2]);
			List l = new ArrayList();
			l.add(foolter);
			dg.getSumfooter().put(sum[3], l);
		}
		for (Object[] sum : xj) {
			Map foolter = new HashMap();
			foolter.put("income", (BigDecimal) sum[0]);
			foolter.put("expenditure", (BigDecimal) sum[1]);
			foolter.put("balance", (BigDecimal) sum[2]);
			((List) dg.getSumfooter().get(sum[3])).add(foolter);
		}
	}
	
	/**
	 * 排序条件 处理
	 * 
	 * @param PayablePRMT
	 * @param hql
	 */
	public String setOrder(AccountDetailPRMT p, String hql) {
		if (!StringTool.isEmptyOrNull(p.getSort()) && null != p.getOrder()) {
			hql += " order by " + p.getSort() + " " + p.getOrder();
		}else{
			hql += " order by tradeDate asc ";
		}
		return hql;
	}
	
	/**
	 * 查询账目需要复合的记录
	 * @return
	 */
	public Datagrid findToReview(AccountDetailPRMT P,User user) {
		Datagrid dg=new Datagrid();
		String footerHql = "select sum(c.income) ,sum(c.expenditure) ,sum(c.balance) ,c.currencyType from AccountDetail c ";
		String footerHql2_center="select * from account_detail c ";
		String hql = " where status = 1 ", countHql = "select count(*) ";
//		String hql="from AccountDetail c where status != 3 ", countHql = "select count(*) ";
		Map<String, Object> params = new HashMap<String, Object>(0);
		if(P.getAccountCode()!=null&&!P.getAccountCode().equals("")) {
			hql+="and c.accountCode = :accountCode ";
			params.put("accountCode", P.getAccountCode());
		}
		if(P.getVoucherNo()!=null&&!P.getVoucherNo().equals("")) {
			hql+="and c.voucherNo = :voucherNo ";
			params.put("voucherNo", P.getVoucherNo());
		}
		if(P.getQiDate()!=null) {
			hql+="and c.tradeDate >= :qiDate ";
			params.put("qiDate", P.getQiDate());
		}
		if(P.getShiDate()!=null) {
			hql+="and c.tradeDate <= :shiDate ";
			params.put("shiDate", P.getShiDate());
		}
		hql+=" and c.cid = "+user.getCid();
		footerHql2_center += hql;
		//小计
		String footerHql2="select sum(c.income) ,sum(c.expenditure) ,sum(c.balance) ,c.currencyType from ("+footerHql2_center+" limit "+((P.getPage()-1)*P.getRows())+","+P.getRows()+") c ";
		footerHql += hql+" group by c.currencyType";
		footerHql2 += hql+" group by c.currencyType";
		hql="from AccountDetail c "+hql;
		countHql += hql;
		System.out.println(footerHql);
		System.out.println(footerHql2);
//		List<AccountDetail> li = this.accountDetailDao.find(hql, params, P.getPage(), P.getRows());
		dg.setRows(this.accountDetailDao.find(hql, params, P.getPage(), P.getRows()));
		dg.setTotal(this.accountDetailDao.count(countHql, params));
		List<AccountDetail> li = this.accountDetailDao.find(hql, params, P.getPage(), P.getRows());
		List<AccountDetail> li1 = this.accountDetailDao.find(hql, params);
		dg.getFooter().add(formatterSum("小计：", sumCurrencySum(li)));
		dg.getFooter().add(formatterSum("总计：", sumCurrencySum(li1)));
//		setFoolter(dg, this.accountDetailDao.sum(footerHql, params), this.accountDetailDao.sum2(footerHql2, params));
		return dg;
	}
	
	/**
	 * 确认复核
	 */
	public void AccountDetailToreview(String id) {
		String hql0="from AccountDetail where id in ("+id+")";
		List<AccountDetail> adli=accountDetailDao.find(hql0);
		for(AccountDetail accountdetail:adli){
			String hql="update AccountDetail set status = "+2+" where settlementNo = '"+accountdetail.getSettlementNo()+"'";
			accountDetailDao.executeHql(hql);
		}
	}
	
	/**
	 * 应付驳回
	 */
	public void payableReject(String id){
		//根绝ID查出结算编号
		String hql0="from AccountDetail where id = "+id;
		AccountDetail accountdetail=accountDetailDao.get(hql0);
		//根绝结算编号（settlementNo）查询表（supplier_payment）对应的结算编号,返回主键ID
		String hql="from SupplierPayment where settlementNo = '"+accountdetail.getSettlementNo()+"'";
		SupplierPayment sp = supplierPaymentDao.get(hql);
		//修改表（SupplierPayment）的状态为以驳回
		String hql8="update SupplierPayment set status = "+2+" where settlementNo = '"+accountdetail.getSettlementNo()+"'";
		supplierPaymentDao.executeHql(hql8);
		//根绝主键ID查询表（payable_detail）对应的销账记录ID（supmid），根绝销账记录ID（supmid）取出对应的应付账款id（pablid）
		String hql1="from PayableDetail where supmid = "+sp.getId();
		List<PayableDetail> pdlist = payableDetailDao.find(hql1);
		//根绝应付账款id（pablid）查询表（payable），把已付金额减掉之前付掉的金额，余额加上付掉的金额
		for(PayableDetail pd:pdlist){
			//1先查出已付金额和余额
			String hql2="from Payable where id = "+pd.getPablid();
			Payable p = payableDao.get(hql2);
			//2已付金额减掉已收金额，余额加上已收金额
			String hql3="update Payable set payAmount = "+(p.getPayAmount().subtract(pd.getPayAmount()).doubleValue())+",balance = "+(p.getBalance().add(pd.getPayAmount()).doubleValue())+" where id = "+pd.getPablid();
			payableDao.executeHql(hql3);
		}
		//再根据表（SupplierPayment）结算编号查出实付金额和手续费
		String hql4="from SupplierPayment where settlementNo = '"+accountdetail.getSettlementNo()+"'";
		SupplierPayment supp = supplierPaymentDao.get(hql4);
		//查出账户本身有多少余额
		String hql5 = "from Account where accountCode = '"+accountdetail.getAccountCode()+"'";
		Account a = accountDao.get(hql5);
		//账户余额加上实付金额和手续费
		System.out.println(a.getAccountBalance().add(supp.getPayAmount()).add(supp.getFactorage()));
		String hql6="update Account set accountBalance = "+(a.getAccountBalance().add(supp.getPayAmount()).add(supp.getFactorage()))+" where accountCode = '"+accountdetail.getAccountCode()+"'";
		accountDao.executeHql(hql6);
		//改变驳回状态驳回
		String hql7="update AccountDetail set status = "+3+" where settlementNo = '"+accountdetail.getSettlementNo()+"'";
		accountDetailDao.executeHql(hql7);
	}
	
	/**
	 * 应收驳回
	 */
	public void receivableReject(String id){
		//根绝ID查出结算编号
		String hql0="from AccountDetail where id = "+id;
		AccountDetail accountdetail=accountDetailDao.get(hql0);
		//根绝结算编号（settlementNo）查询表（CustomerPayment）对应的结算编号,返回主键ID
		String hql="from CustomerPayment where settlementNo = '"+accountdetail.getSettlementNo()+"'";
		CustomerPayment cp = customerPaymentDao.get(hql);
		//修改表（CustomerPayment）的状态为以驳回
		String hql8="update CustomerPayment set status = "+2+" where settlementNo = '"+accountdetail.getSettlementNo()+"'";
		customerPaymentDao.executeHql(hql8);
		//根绝主键ID查询表（ReceivableDetail）对应的销账记录ID（ctpmid），根绝销账记录ID（ctpmid）取出对应的应收账款id（rcvbid）
		String hql1="from ReceivableDetail where ctpmid = "+cp.getId();
		List<ReceivableDetail> rdlist = receivableDetailDao.find(hql1);
		//根绝应付账款id（Rcvbid）查询表（Receivable），把已付金额减掉之前付掉的金额，余额加上付掉的金额
		for(ReceivableDetail pd:rdlist){
			//1先查出已付金额和余额
			String hql2="from Receivable where id = "+pd.getRcvbid();
			Receivable r = receivableDao.get(hql2);
			//2已收金额减掉已收金额，余额加上已收金额
			String hql3="update Receivable set incomeAmount = "+(r.getIncomeAmount().subtract(pd.getIncomeAmount()).doubleValue())+",balance = "+(r.getBalance().add(pd.getIncomeAmount()).doubleValue())+" where id = "+pd.getRcvbid();
			payableDao.executeHql(hql3);
		}
		//再根据表（CustomerPayment）结算编号查出实付金额和手续费
		String hql4="from CustomerPayment where settlementNo = '"+accountdetail.getSettlementNo()+"'";
		CustomerPayment ctpt = customerPaymentDao.get(hql4);
		//查出账户本身有多少余额
		String hql5 = "from Account where accountCode = '"+accountdetail.getAccountCode()+"'";
		Account a = accountDao.get(hql5);
		//账户余额加上入账金额和客户手续费，减掉手续费
		System.out.println(a.getAccountBalance().add(ctpt.getIncomeAmount()).add(ctpt.getFactorage()));
		String hql6="update Account set accountBalance = "+(a.getAccountBalance().subtract(ctpt.getNetAmount()).subtract(ctpt.getCustomerFactorage().add(ctpt.getFactorage())))+" where accountCode = '"+accountdetail.getAccountCode()+"'";
		accountDao.executeHql(hql6);
		//改变驳回状态驳回
		String hql7="update AccountDetail set status = "+3+" where settlementNo = '"+accountdetail.getSettlementNo()+"'";
		accountDetailDao.executeHql(hql7);
	}
	
	/**
	 * 佣金驳回
	 */
	public void commissionReject(String id){
		//根绝ID查出结算编号
		String hql0="from AccountDetail where id = "+id;
		AccountDetail accountdetail=accountDetailDao.get(hql0);
		//根绝结算编号（settlementNo）查询表（CustomerPayment）对应的结算编号,返回主键ID
		String hql="from CommissionPayment where settlementNo = '"+accountdetail.getSettlementNo()+"'";
		CommissionPayment cp = commissionPaymentDao.get(hql);
		//修改表（CommissionPayment）的状态为以驳回
		String hql8="update CommissionPayment set status = "+2+" where settlementNo = '"+accountdetail.getSettlementNo()+"'";
		commissionPaymentDao.executeHql(hql8);
		//根绝主键ID查询表（CommissionDetail）对应的销账记录ID（cmpmid），根绝销账记录ID（cmpmid）取出对应的应收账款id（cmsnid）
		String hql1="from CommissionDetail where cmpmid = "+cp.getId();
		List<CommissionDetail> cdlist = commissionDetailDao.find(hql1);
		//根绝佣金id（cmsnid）查询表（Commission），把已收金额减掉之前付掉的金额，余额加上付掉的金额
		for(CommissionDetail cd:cdlist){
			//1先查出已付金额
			String hql2="from Commission where id = "+cd.getCmsnid();
			Commission c = commissionDao.get(hql2);
			//2已收金额减掉已收金额，余额加上已收金额
			String hql3="update Commission set incomeAmount = "+(c.getIncomeAmount().subtract(cd.getIncomeAmount()).doubleValue())+",balance = "+(c.getBalance().add(cd.getIncomeAmount()).doubleValue())+" where id = "+cd.getCmsnid();
			commissionDao.executeHql(hql3);
		}
		//再根据表（CommissionPayment）结算编号查出实付金额和手续费
		String hql4="from CommissionPayment where settlementNo = '"+accountdetail.getSettlementNo()+"'";
		CommissionPayment cmpt = commissionPaymentDao.get(hql4);
		//查出账户本身有多少余额
		String hql5 = "from Account where accountCode = '"+accountdetail.getAccountCode()+"'";
		Account a = accountDao.get(hql5);
		//账户余额减掉实付金额和加上手续费
		System.out.println(a.getAccountBalance().subtract(cmpt.getIncomeAmount()).add(cmpt.getFactorage()));
		String hql6="update Account set accountBalance = "+(a.getAccountBalance().subtract(cmpt.getIncomeAmount()).add(cmpt.getFactorage()))+" where accountCode = '"+accountdetail.getAccountCode()+"'";
		accountDao.executeHql(hql6);
		//改变驳回状态驳回
		String hql7="update AccountDetail set status = "+3+" where settlementNo = '"+accountdetail.getSettlementNo()+"'";
		accountDetailDao.executeHql(hql7);
	}
	
	public void zhuanZhangReject(String id){
		//根绝ID查出结算编号
		String hql0="from AccountDetail where id = "+id;
		AccountDetail accountdetail=accountDetailDao.get(hql0);
		String hql1 = "from AccountDetail where settlementNo = '"+accountdetail.getSettlementNo()+"'";
		List<AccountDetail> adli = accountDetailDao.find(hql1);
		BigDecimal zhichu = new BigDecimal(0);
		BigDecimal shouru = new BigDecimal(0);
		for(AccountDetail ad:adli){
			zhichu = zhichu.add(ad.getExpenditure());
			shouru = shouru.add(ad.getIncome());
		}
		//账户金额还原
		String hql2="from Account where accountCode = '"+accountdetail.getAccountCode()+"'";
		Account account=accountDao.get(hql2);
		account.setAccountBalance(account.getAccountBalance().add(zhichu));
		accountDao.update(account);
		//账户金额还原
		String hql3="from Account where accountCode = '"+accountdetail.getTradeObject()+"'";
		Account account1=accountDao.get(hql3);
		account1.setAccountBalance(account1.getAccountBalance().subtract(shouru));
		accountDao.update(account1);
		//改变驳回状态
		String hql7="update AccountDetail set status = "+3+" where settlementNo = '"+accountdetail.getSettlementNo()+"'";
		accountDetailDao.executeHql(hql7);
	}
	
	/**
	 * 应付账目明细查询
	 */
	public Datagrid payableMingxi(String id) {
		Datagrid dg=new Datagrid();
		//根绝ID查出结算编号
		String hql0="from AccountDetail where id = "+id;
		AccountDetail accountdetail=accountDetailDao.get(hql0);
//		//根绝结算编号（settlementNo）查询表（supplier_payment）对应的结算编号,返回主键ID
//		String hql="from SupplierPayment where settlementNo = '"+accountdetail.getSettlementNo()+"'";
//		SupplierPayment sp = supplierPaymentDao.get(hql);
//		//根绝主键ID查询表（payable_detail）对应的销账记录ID（supmid），根绝销账记录ID（supmid）取出对应的应付账款id（pablid）
//		String hql1="from PayableDetail where supmid = "+sp.getId();
//		List<PayableDetail> pdlist = payableDetailDao.find(hql1);
//		String pablid="";
//		for(PayableDetail pd:pdlist){
//			//取出应付账款ID
//			if(!pablid.equals("")){
//				pablid+=",";
//			}
//			pablid+=pd.getPablid();
//		}
		//查询应付账款信息
		String hql2="from PayableView where voucherNo in ("+accountdetail.getVoucherNo()+")", countHql = "select count(*) ";
		List<PayableView> plist = payableViewDao.find(hql2);
		List<DetailedPRMT> dli=new ArrayList<DetailedPRMT>();
		for(PayableView r:plist){
			DetailedPRMT d=new DetailedPRMT();
			d.setAccountNo(r.getSupplierNo());
			d.setExchangeNo(r.getExchangeNo());
			d.setPayAmount(r.getPayAmount());
			d.setCurrencyType(r.getCurrencyType());
			d.setTheOperator(r.getHandPerson());
			dli.add(d);
		}
		countHql+=hql2;
		dg.setRows(dli);
		dg.setTotal(this.payableDao.count(countHql));
		
		//小计、总计
				List list=new ArrayList();
				Object[] ob=new Object[2];
				Object[] ob2=new Object[2];
				BigDecimal payAmount=new BigDecimal(0);
				BigDecimal payAmountC=new BigDecimal(0);
				for(DetailedPRMT r:dli){
					if(r.getCurrencyType().equals("CNY")){
						payAmountC=payAmountC.add(r.getPayAmount());
					}else{
						payAmount=payAmount.add(r.getPayAmount());
					}
					
				}
				if(!payAmount.equals(BigDecimal.ZERO)){
					ob[0]=payAmount;
					ob[1]="USD";
					list.add(ob);
				}
				if(!payAmountC.equals(BigDecimal.ZERO)){
					ob2[0]=payAmountC;
					ob2[1]="CNY";
					list.add(ob2);
				}
				setFoolter_Mx(dg, list,list);
		
		return dg;
	}
	
	/**
	 * 统计数据处理
	 * 
	 * @param dg
	 * @param zj
	 * @param xj
	 */
	private void setFoolter_Mx(Datagrid dg, List<Object[]> zj, List<Object[]> xj) {
		for (Object[] sum : zj) {
			Map foolter = new HashMap();
			foolter.put("payAmount", (BigDecimal) sum[0]);
			List l = new ArrayList();
			l.add(foolter);
			dg.getSumfooter().put(sum[1], l);
		}
		for (Object[] sum : xj) {
			Map foolter = new HashMap();
			foolter.put("payAmount", (BigDecimal) sum[0]);
			((List) dg.getSumfooter().get(sum[1])).add(foolter);
		}
	}
	
	/**
	 * 应收明细
	 */
	public Datagrid receivableMingxi(String id) {
		Datagrid dg=new Datagrid();
		//根绝ID查出结算编号
		String hql0="from AccountDetail where id = "+id;
		AccountDetail accountdetail=accountDetailDao.get(hql0);
//		//查询应收账款信息
		String hql2="from ReceivableView where voucherNo in ("+accountdetail.getVoucherNo()+")", countHql = "select count(*) ";
		List<ReceivableView> rlist = receivableViewDao.find(hql2);
		List<DetailedPRMT> dli=new ArrayList<DetailedPRMT>();
		for(ReceivableView r:rlist){
			String hql="from Receivable where id = "+r.getRcvbid();
			Receivable rb= receivableDao.get(hql);
			DetailedPRMT d=new DetailedPRMT();
			d.setAccountNo(r.getCustomerNo());
			d.setExchangeNo(r.getNoticeNo());
			d.setPayAmount(r.getIncomeAmount());
			d.setCurrencyType("CNY");
			d.setPayBayAmount(rb.getIncomeBeAmount());
			d.setTheOperator(r.getHandPerson());
			if(r.getPaymentMethod()==1){//1现金 /2信用卡/ 3支票 /4转账汇款/5内转
				d.setPaymentMethod("现金");//支付方式
			}else if(r.getPaymentMethod()==2){
				d.setPaymentMethod("信用卡");//支付方式
			}else if(r.getPaymentMethod()==3){
				d.setPaymentMethod("支票");//支付方式
			}else if(r.getPaymentMethod()==4){
				d.setPaymentMethod("转账汇款");//支付方式
			}else if(r.getPaymentMethod()==5){
				d.setPaymentMethod("内转");//支付方式
			}
			
			dli.add(d);
		}
		countHql+=hql2;
		dg.setRows(dli);
		dg.setTotal(this.receivableViewDao.count(countHql));
		
		//小计、总计
		List list=new ArrayList();
		Object[] ob=new Object[2];
		Object[] ob2=new Object[2];
		BigDecimal payAmount=new BigDecimal(0);
		BigDecimal payAmountC=new BigDecimal(0);
		for(DetailedPRMT r:dli){
			if(r.getCurrencyType().equals("CNY")){
				payAmountC=payAmountC.add(r.getPayAmount());
			}else{
				payAmount=payAmount.add(r.getPayAmount());
			}
			
		}
		if(!payAmount.equals(BigDecimal.ZERO)){
			ob[0]=payAmount;
			ob[1]="USD";
			list.add(ob);
		}
		if(!payAmountC.equals(BigDecimal.ZERO)){
			ob2[0]=payAmountC;
			ob2[1]="CNY";
			list.add(ob2);
		}
		setFoolter_Mx(dg, list,list);
		
		return dg;
	}
	
	/**
	 * 佣金明细
	 */
	public Datagrid commissionMingxi(String id) {
		Datagrid dg=new Datagrid();
		//根绝ID查出结算编号
		String hql0="from AccountDetail where id = "+id;
		AccountDetail accountdetail=accountDetailDao.get(hql0);
		//根绝结算编号（settlementNo）查询表（CommissionPayment）对应的结算编号,返回主键ID
		String hql="from CommissionPayment where settlementNo = '"+accountdetail.getSettlementNo()+"'";
		CommissionPayment cp = commissionPaymentDao.get(hql);
		//根绝主键ID查询表（CommissionDetail）对应的销账记录ID（cmpmid），根绝销账记录ID（cmpmid）取出对应的应付账款id（cmsnid）
		String hql1="from CommissionDetail where cmpmid = "+cp.getId();
		List<CommissionDetail> cdlist = commissionDetailDao.find(hql1);
		String cmsnid="";
		for(CommissionDetail cd:cdlist){
			//取出应付账款ID
			if(!cmsnid.equals("")){
				cmsnid+=",";
			}
			cmsnid+=cd.getCmsnid();
		}
		//查询应付账款信息
		String hql2="from Commission where id in ("+cmsnid+")", countHql = "select count(*) ";
		List<Commission> clist = commissionDao.find(hql2);
		List<DetailedPRMT> dli=new ArrayList<DetailedPRMT>();
		for(Commission c:clist){
			DetailedPRMT d=new DetailedPRMT();
			d.setAccountNo(c.getSupplierNo());
			d.setExchangeNo(c.getProductOrderNo());
			d.setPayAmount(c.getIncomeAmount());
			d.setCurrencyType(c.getCurrencyType());
			d.setTheOperator(c.getCreater());
			dli.add(d);
		}
		countHql+=hql2;
		dg.setRows(dli);
		dg.setTotal(this.accountDetailDao.count(countHql));
		
		//小计、总计
		List list=new ArrayList();
		Object[] ob=new Object[2];
		Object[] ob2=new Object[2];
		BigDecimal payAmount=new BigDecimal(0);
		BigDecimal payAmountC=new BigDecimal(0);
		for(DetailedPRMT r:dli){
			if(r.getCurrencyType().equals("CNY")){
				payAmountC=payAmountC.add(r.getPayAmount());
			}else{
				payAmount=payAmount.add(r.getPayAmount());
			}
			
		}
		if(!payAmount.equals(BigDecimal.ZERO)){
			ob[0]=payAmount;
			ob[1]="USD";
			list.add(ob);
		}
		if(!payAmountC.equals(BigDecimal.ZERO)){
			ob2[0]=payAmountC;
			ob2[1]="CNY";
			list.add(ob2);
		}
		setFoolter_Mx(dg, list,list);
		return dg;
	}
	
	/**
	 * 转账汇款
	 */
	public void zhuanZhang(AccountDetailPRMT p,User user){
		//转出账户减去转出金额
		String hql="update Account set accountBalance = "+p.getMoneyYue().subtract(p.getZhuchuMoney()).subtract(p.getFactorage())+" where accountCode = '"+p.getZhaunchuZhanghu()+"'";
		accountDao.executeHql(hql);
		//转入账户加上收入金额
		String hql1="from Account where accountCode = '"+p.getZhuanruAccount()+"'";
		Account account=accountDao.get(hql1);
		account.setAccountBalance(account.getAccountBalance().add(p.getZhuchuMoney()));
		
		//添加转出金额账户到到账目复核
		String jiesuanbinahao=this.jiesuanCode();//结算编号
		
		AccountDetail ad = new AccountDetail();
		ad.setAccountCode(p.getZhaunchuZhanghu());
		ad.setAccountBank(p.getZhuanchuKaihuhang());
		ad.setTradeDate(p.getTradeDate());
		ad.setTradeObject(p.getZhuanruAccount());
		ad.setIncome(BigDecimal.ZERO);
		ad.setExpenditure(p.getZhuchuMoney());
		ad.setBalance(p.getMoneyYue().subtract(p.getZhuchuMoney()));
		ad.setVoucherNo(p.getVoucherNo());
		ad.setRemark(p.getRemark());
		ad.setStatus(1);
		ad.setType(4);
		ad.setSettlementNo(jiesuanbinahao);
		ad.setSubjects("转账支出");
		ad.setCurrencyType(p.getCurrencyType());
		ad.setCid(user.getCid());
		// 记录手续费
		if (p.getFactorage().compareTo(BigDecimal.ZERO) != 0) {
			// 计算账户余额
//			throw new MessageException("账户余额不足！");
			AccountDetail adfee = new AccountDetail();
			BeanUtils.copyProperties(ad, adfee);
			adfee.setIncome(BigDecimal.ZERO);
			adfee.setExpenditure(p.getFactorage());
			adfee.setBalance(ad.getBalance().subtract(p.getFactorage()));
			adfee.setSubjects("手续费");
			ad.setCid(user.getCid());
			this.accountDetailDao.save(adfee);
		}
		// 保存业务明细
		this.accountDetailDao.save(ad);
		
		AccountDetail adshouru = new AccountDetail();
		adshouru.setAccountCode(p.getZhuanruAccount());
		adshouru.setAccountBank(p.getZhuanruKaihuhang());
		adshouru.setTradeDate(p.getTradeDate());
		adshouru.setTradeObject(p.getZhaunchuZhanghu());
		adshouru.setIncome(p.getZhuchuMoney());
		adshouru.setExpenditure(BigDecimal.ZERO);
		adshouru.setBalance(account.getAccountBalance());
		adshouru.setVoucherNo(p.getVoucherNo());
		adshouru.setRemark(p.getRemark());
		adshouru.setStatus(1);
		adshouru.setType(4);
		adshouru.setSettlementNo(jiesuanbinahao);
		adshouru.setSubjects("转账收入");
		adshouru.setCurrencyType(p.getCurrencyType());
		adshouru.setCid(user.getCid());
		this.accountDetailDao.save(adshouru);
		
		//更新账户
		accountDao.update(account);
	}

	public String jiesuanCode(){
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		int month = cal.get(Calendar.MONTH) + 1;
		String year = cal.get(Calendar.YEAR)+"";
		String y = year+month+day;
		String xuhao="";
		String hql="select count(*) from AccountDetail where settlementNo like  '%"+y+"%'";
		Long count = accountDetailDao.count(hql);
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
		return y+xuhao;
	}
	
}
