package com.gsh.cs.service.armgt.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.gsh.cs.model.base.CreditCost;
import com.gsh.cs.model.base.CustomerPayment;
import com.gsh.cs.model.base.DeadlineChg;
import com.gsh.cs.model.base.Receivable;
import com.gsh.cs.model.base.ReceivableDetail;
import com.gsh.cs.model.base.SupplierPayment;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.CustomerPaymentPRMT;
import com.gsh.cs.service.armgt.CustomerPaymentServiceI;
import com.gsh.cs.service.bsp.DataPermissionServiceI;
import com.gsh.cs.tools.StringTool;

@Service("customerPaymentService")
public class CustomerPaymentServiceImpl implements CustomerPaymentServiceI {
	private BaseDaoI<Receivable> receivableDao;
	private BaseDaoI<ReceivableDetail> receivableDetailDao;
	private BaseDaoI<DeadlineChg> deadlineChgDao;
	private BaseDaoI<CustomerPayment> customerPaymentDao;
	private BaseDaoI<Account> accountDao;
	private BaseDaoI<AccountDetail> accountDetailDao;
	private BaseDaoI<CreditCost> creditCostDao;
	private BaseDaoI<SupplierPayment> SupplierPaymentDao;
	
	@Resource DataPermissionServiceI dataPermissionService;

	@Autowired
	public void setSupplierPaymentDao(BaseDaoI<SupplierPayment> supplierPaymentDao) {
		SupplierPaymentDao = supplierPaymentDao;
	}

	@Autowired
	public void setCreditCostDao(BaseDaoI<CreditCost> creditCostDao) {
		this.creditCostDao = creditCostDao;
	}

	@Autowired
	public void setReceivableDao(BaseDaoI<Receivable> receivableDao) {
		this.receivableDao = receivableDao;
	}

	@Autowired
	public void setReceivableDetailDao(BaseDaoI<ReceivableDetail> receivableDetailDao) {
		this.receivableDetailDao = receivableDetailDao;
	}

	@Autowired
	public void setDeadlineChgDao(BaseDaoI<DeadlineChg> deadlineChgDao) {
		this.deadlineChgDao = deadlineChgDao;
	}

	@Autowired
	public void setCustomerPaymentDao(BaseDaoI<CustomerPayment> customerPaymentDao) {
		this.customerPaymentDao = customerPaymentDao;
	}

	@Autowired
	public void setAccountDao(BaseDaoI<Account> accountDao) {
		this.accountDao = accountDao;
	}

	@Autowired
	public void setAccountDetailDao(BaseDaoI<AccountDetail> accountDetailDao) {
		this.accountDetailDao = accountDetailDao;
	}

	public Datagrid find(CustomerPaymentPRMT p,User user) {
		Datagrid dg = new Datagrid();
		String hql = "from CustomerPayment t where ", countHql = "select count(*) ", footerHql = "select sum(t.incomeAmount) , sum(t.customerFactorage) ";
		Map<String, Object> args = new HashMap<String, Object>();
		if (p.getStatus() != null) {
			hql += "t.status " + p.getStatusCompare() + " :status ";
			args.put("status", p.getStatus());
		}
		if(p.getIncomeDateQi()!=null){
			hql += "and t.incomeDate  >= :incomeDateQi ";
			args.put("incomeDateQi", p.getIncomeDateQi());
		}
		if(p.getIncomeDateShi()!=null){
			hql += "and t.incomeDate  <= :incomeDateShi ";
			args.put("incomeDateShi", p.getIncomeDateShi());
		}
		if(p.getTradeNo()!=null&&!p.getTradeNo().equals("")){
			hql += "and t.tradeNo =:tradeNo ";
			args.put("tradeNo", p.getTradeNo());
		}
		if(p.getSettlementNo()!=null&&!p.getSettlementNo().equals("")){
			hql += "and t.settlementNo like :settlementNo ";
			args.put("settlementNo", "%"+p.getSettlementNo()+"%");
		}
		if(p.getCustomerNo()!=null&&!p.getCustomerNo().equals("")){
			hql += "and t.customerNo like :customerNo ";
			args.put("customerNo", "%"+p.getCustomerNo()+"%");
		}
		hql += "and t.handNo = "+user.getUid();
		countHql += hql;
		hql = setOrder(p, hql);
		footerHql += hql;
		dg.setRows(this.customerPaymentDao.find(hql, args, p.getPage(), p.getRows()));
		dg.setTotal(this.customerPaymentDao.count(countHql, args));
//		Object o = this.customerPaymentDao.sum(footerHql, args, p.getPage(), p.getRows()).get(0);
//		dg.getFooter().add(formatterSum("小计：", (Object[]) this.customerPaymentDao.sum(footerHql, args, p.getPage(), p.getRows()).get(0)));
		List<CustomerPayment> rli=this.customerPaymentDao.find(hql, args, p.getPage(), p.getRows());
		List li=new ArrayList();
		BigDecimal incomeBeAmount=new BigDecimal(0);
		BigDecimal incomeAmount=new BigDecimal(0);
		BigDecimal balance=new BigDecimal(0);
		for(CustomerPayment r:rli){
			incomeAmount=incomeAmount.add(r.getIncomeAmount());
		}
		li.add(incomeAmount);
		dg.getFooter().add(formatterSum("小计：", (Object[])li.toArray()));
		dg.getFooter().add(formatterSum("总计：", (Object[]) this.customerPaymentDao.sum(footerHql, args).get(0)));
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
		foolter.put("options", title);
		foolter.put("incomeAmount", (BigDecimal) sum[0]);
		return foolter;
	}

	/**
	 * 排序条件 处理
	 * 
	 * @param CustomerPaymentPRMT
	 * @param hql
	 */
	public String setOrder(CustomerPaymentPRMT p, String hql) {
		if (!StringTool.isEmptyOrNull(p.getSort()) && null != p.getOrder()) {
			hql += " order by " + p.getSort() + " " + p.getOrder();
		}
		return hql;
	}

	@Transactional
	public void submit(Long id,String status) throws MessageException {
		CustomerPayment t = this.customerPaymentDao.get(CustomerPayment.class, id);
		if (true) {// 提交申请 对接审批接口
			if(status.equals("1")){
				t.setStatus(3);
			}else if(status.equals("2")){
				t.setStatus(4);
			}
			String hql="update CustomerPayment set status = "+t.getStatus()+" where id = "+id;
			this.customerPaymentDao.executeHql(hql);
		} else {
			throw new MessageException("审批中心错误提示信息");
		}
	}
	
	@Transactional
	public void huidiaoSubmit(Long id,String name,String date) throws MessageException {
		CustomerPayment t = this.customerPaymentDao.get(CustomerPayment.class, id);
		if (true) {// 提交申请 对接审批接口
			t.setStatus(4);
			String hql="update CustomerPayment set status = 4,sprName = '"+name+"',spDate = '"+date+"' where id = "+id;
			this.customerPaymentDao.executeHql(hql);
		} else {
			throw new MessageException("审批中心错误提示信息");
		}
	}
	
	@Transactional
	public void boHuiSubmit(Long id,String name,String date) throws MessageException {
		CustomerPayment t = this.customerPaymentDao.get(CustomerPayment.class, id);
		if (true) {// 提交申请 对接审批接口
			t.setStatus(2);
			String hql="update CustomerPayment set status = 2,sprName = '"+name+"',spDate = '"+date+"' where id = "+id;
			this.customerPaymentDao.executeHql(hql);
		} else {
			throw new MessageException("审批中心错误提示信息");
		}
	}

	@Transactional
	public void revoke(Long id) throws MessageException {
		CustomerPayment t = this.customerPaymentDao.get(CustomerPayment.class, id);
		t.setStatus(9);
		this.customerPaymentDao.update(t);
		String hql = "from ReceivableDetail t where t.ctpmid = " + id;
		List<ReceivableDetail> rd_l = this.receivableDetailDao.find(hql);
		for (ReceivableDetail rd : rd_l) {
			Receivable r = this.receivableDao.get(Receivable.class, rd.getRcvbid());
			r.setIncomeAmount(r.getIncomeAmount().subtract(rd.getIncomeAmount()));
			r.setBalance(r.getBalance().add(rd.getIncomeAmount()));
			this.receivableDao.update(r);
		}
	}

	@Transactional
	public List<ReceivableDetail> payment(CustomerPayment tp, List<ReceivableDetail> rdl,User user) throws MessageException {
		CustomerPayment t = this.customerPaymentDao.get(CustomerPayment.class, tp.getId());
		t.setPaymentMethod(tp.getPaymentMethod());
		t.setIncomeDate(tp.getIncomeDate());
		t.setFundAccount(tp.getFundAccount());
		t.setBankRate(tp.getBankRate());
		t.setExchangeProfitLoss(tp.getExchangeProfitLoss());
		t.setFactorage(tp.getFactorage());
		t.setNetAmount(tp.getNetAmount());
		t.setVoucherNo(tp.getVoucherNo());
		t.setHandNo(tp.getHandNo());
		t.setHandPerson(tp.getHandPerson());
		t.setStatus(6);
		for (ReceivableDetail rd : rdl) {
			Receivable r = this.receivableDao.get(Receivable.class, rd.getRcvbid());
			if (r.getReverseHas() == 2) {
				r.setReverseHas(1);
				Receivable rf = new Receivable();
				BeanUtils.copyProperties(r, rf, new String[] { "id" });
				rf.setIncomeBeAmount(rf.getIncomeBeAmount().negate());
				rf.setIncomeAmount(BigDecimal.ZERO);
				rf.setBalance(rf.getIncomeBeAmount());
				rf.setNoticeNo("*"+r.getNoticeNo());
				rf.setCreateDate(new Date());
				rf.setOriginalPaymentMethod(t.getPaymentMethod());
				rf.setDeadlineHas(0);
				this.receivableDao.save(rf);
			}
			this.receivableDao.update(r);
			rd.setCtpmid(t.getId());
			rd.setId((Long) this.receivableDetailDao.save(rd));
		}
		// 获取账户信息-计算余额
		String hql = "from Account t where t.accountCode = :accountCode";
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("accountCode", t.getFundAccount());
		Account at = this.accountDao.find(hql, args).get(0);
		at.setAccountBalance(at.getAccountBalance().add(t.getNetAmount()).add(t.getCustomerFactorage()));
		// 记录账户明细
		AccountDetail ad = new AccountDetail();
		ad.setAccountCode(at.getAccountCode());
		ad.setAccountBank(at.getAccountBank());
		ad.setTradeDate(t.getIncomeDate());
		ad.setTradeNo(t.getTradeNo());
		ad.setTradeObject(t.getCustomerNo());
		ad.setIncome(BigDecimal.ZERO);
		ad.setExpenditure(t.getNetAmount().add(t.getCustomerFactorage()).abs());
		ad.setBalance(at.getAccountBalance());
		ad.setVoucherNo(t.getVoucherNo());
		ad.setRemark(t.getRemark());
		ad.setStatus(1);
		ad.setType(1);
		ad.setSettlementNo(t.getSettlementNo());
		ad.setSubjects("业务收入-退款");
		ad.setCurrencyType(at.getAccountCurrency());
		ad.setCid(user.getCid());
		// 保存业务明细
		this.accountDetailDao.save(ad);
		// 记录手续费
		if (t.getFactorage().compareTo(BigDecimal.ZERO) != 0) {
			// 计算账户余额
			at.setAccountBalance(at.getAccountBalance().subtract(t.getFactorage()));
			AccountDetail adfee = new AccountDetail();
			BeanUtils.copyProperties(ad, adfee);
			adfee.setIncome(BigDecimal.ZERO);
			adfee.setExpenditure(t.getFactorage().abs());
			adfee.setBalance(at.getAccountBalance());
			adfee.setSubjects("手续费");
			adfee.setCid(user.getCid());
			this.accountDetailDao.save(adfee);
		}
		this.accountDao.update(at);
		this.customerPaymentDao.update(t);
		return rdl;
	}

	@Transactional
	public void sure(CustomerPaymentPRMT t,User user) throws MessageException {
		CustomerPayment to = this.customerPaymentDao.get(CustomerPayment.class, t.getId());
		to.setNetAmount(t.getNetAmount());
		to.setStatus(6);
		to.setArriveDate(t.getArriveDate());
		to.setVoucherNo(t.getVoucherNo());
		to.setFundAccount(t.getFundAccount());
		this.customerPaymentDao.update(to);
		// 获取账户信息-计算余额
		String hql = "from Account t where t.accountCode = :accountCode";
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("accountCode", to.getFundAccount());
		Account at = this.accountDao.find(hql, args).get(0);
		at.setAccountBalance(at.getAccountBalance().add(to.getNetAmount()));
		// 记录账户明细
		AccountDetail ad = new AccountDetail();
		ad.setAccountCode(at.getAccountCode());
		ad.setAccountBank(at.getAccountBank());
		ad.setTradeDate(to.getIncomeDate());
		ad.setTradeNo(to.getTradeNo());
		ad.setTradeObject(to.getCustomerNo());
		ad.setIncome(to.getNetAmount());
		ad.setExpenditure(BigDecimal.ZERO);
		ad.setBalance(at.getAccountBalance());
		ad.setVoucherNo(to.getVoucherNo());
		ad.setRemark(to.getRemark());
		ad.setStatus(1);
		ad.setType(1);
		ad.setSettlementNo(to.getSettlementNo());
		ad.setSubjects("业务收入");
		ad.setCurrencyType(at.getAccountCurrency());
		ad.setCid(user.getCid());
		//添加信用卡成本
		if(to.getPaymentMethod()==2){
			CreditCost cc=new CreditCost();
			cc.setCreditDate(new Date());
			cc.setTradeNo(t.getTradeNo()); 
			cc.setSaleMoney(t.getCustomerFactorage());
			cc.setCostAmount(t.getIncomeAmount().add(t.getCustomerFactorage()).subtract(t.getNetAmount()));
			cc.setGrossProfit(t.getCustomerFactorage().subtract(t.getIncomeAmount().add(t.getCustomerFactorage()).subtract(t.getNetAmount())));
			cc.setUserId(user.getCid());
			cc.setDeptId(user.getDepts());
			creditCostDao.save(cc);
		}
		
		// 保存业务明细.add(to.getCustomerFactorage())
		this.accountDetailDao.save(ad);
		if (to.getCustomerFactorage().compareTo(BigDecimal.ZERO) != 0) {
			// 计算账户余额
			at.setAccountBalance(at.getAccountBalance().add(to.getCustomerFactorage()));
			AccountDetail adfee = new AccountDetail();
			BeanUtils.copyProperties(ad, adfee);
			adfee.setIncome(to.getCustomerFactorage().abs());
			adfee.setExpenditure(BigDecimal.ZERO);
			adfee.setBalance(at.getAccountBalance());
			adfee.setSubjects("手续费");
			adfee.setCid(user.getCid());
			this.accountDetailDao.save(adfee);
		}
		this.accountDao.update(at);
	}
	
	/**
	 * 应收账款销账明细报表
	 */
	public List<CustomerPayment> findYsZhangkuan(){
		String hql="from CustomerPayment where voucherNo != null";
		List<CustomerPayment> cplist = customerPaymentDao.find(hql);
		return cplist;
	}
	public List<SupplierPayment> PassengerReport(){
		String hql="from SupplierPayment where voucherNo != null";
		List<SupplierPayment> cplist = SupplierPaymentDao.find(hql);
		return cplist;
	}
	
	
	
}
