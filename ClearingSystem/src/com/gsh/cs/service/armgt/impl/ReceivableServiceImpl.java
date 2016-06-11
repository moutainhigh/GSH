package com.gsh.cs.service.armgt.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.gsh.cs.base.Initialization;
import com.gsh.cs.base.MessageException;
import com.gsh.cs.dao.BaseDaoI;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.Interface.AccountsReceivable;
import com.gsh.cs.model.Interface.BaseJsonResponse;
import com.gsh.cs.model.Interface.Confirmation;
import com.gsh.cs.model.Interface.CustomersOntheBill;
import com.gsh.cs.model.Interface.SalesmanArrearsReport;
import com.gsh.cs.model.Interface.ticketInfoP;
import com.gsh.cs.model.base.Account;
import com.gsh.cs.model.base.AccountDetail;
import com.gsh.cs.model.base.CollectpayAccount;
import com.gsh.cs.model.base.CommissionPayment;
import com.gsh.cs.model.base.CustomerPayment;
import com.gsh.cs.model.base.DeadlineChg;
import com.gsh.cs.model.base.Payable;
import com.gsh.cs.model.base.Receivable;
import com.gsh.cs.model.base.ReceivableDetail;
import com.gsh.cs.model.base.SupplierPayment;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.ReceivablePRMT;
import com.gsh.cs.service.armgt.ReceivableServiceI;
import com.gsh.cs.service.bsp.DataPermissionServiceI;
import com.gsh.cs.tools.SoaPropertiesUtil;
import com.gsh.cs.tools.StringTool;
import com.gsh.cs.tools.httpClient;

@Service("receivableService")
public class ReceivableServiceImpl implements ReceivableServiceI {

	private BaseDaoI<Receivable> receivableDao;
	private BaseDaoI<ReceivableDetail> receivableDetailDao;
	private BaseDaoI<DeadlineChg> deadlineChgDao;
	private BaseDaoI<CustomerPayment> customerPaymentDao;
	private BaseDaoI<Account> accountDao;
	private BaseDaoI<AccountDetail> accountDetailDao;
	private BaseDaoI<SupplierPayment> supplierPaymentDao;
	private BaseDaoI<CommissionPayment> commissionPaymentDao;
	private BaseDaoI<CollectpayAccount> collectpayAccountDao;
	@Resource DataPermissionServiceI dataPermissionService;
	@Autowired
	public void setCommissionPaymentDao(
			BaseDaoI<CommissionPayment> commissionPaymentDao) {
		this.commissionPaymentDao = commissionPaymentDao;
	}

	@Autowired
	public void setSupplierPaymentDao(BaseDaoI<SupplierPayment> supplierPaymentDao) {
		this.supplierPaymentDao = supplierPaymentDao;
	}

	@Autowired
	public void setReceivableDao(BaseDaoI<Receivable> receivableDao) {
		this.receivableDao = receivableDao;
	}

	@Autowired
	public void setDeadlineChgDao(BaseDaoI<DeadlineChg> deadlineChgDao) {
		this.deadlineChgDao = deadlineChgDao;
	}

	@Autowired
	public void setReceivableDetailDao(BaseDaoI<ReceivableDetail> receivableDetailDao) {
		this.receivableDetailDao = receivableDetailDao;
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
	
	@Autowired
	public void setCollectpayAccountDao(
			BaseDaoI<CollectpayAccount> collectpayAccountDao) {
		this.collectpayAccountDao = collectpayAccountDao;
	}

	public Datagrid find(ReceivablePRMT p,User user) {
		try {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			Datagrid dg = new Datagrid();
			String hql = "from Receivable t where t.balance != 0 ", countHql = "select count(*) ", footerHql = "select sum(t.incomeBeAmount),sum(t.incomeAmount),sum(t.balance) ";
			Map<String, Object> args = new HashMap<String, Object>();
			if (p.getTradeDateQi() != null) {
				hql += "and t.tradeDate >= :tradeDateQi ";
				args.put("tradeDateQi", p.getTradeDateQi());
			}
			if (p.getTradeDateShi() != null) {
				hql += "and t.tradeDate <= :tradeDateShi ";
				args.put("tradeDateShi", p.getTradeDateShi());
			}
			if (p.getDeadlineQi() != null) {
				hql += "and t.deadline >= :deadlineQi ";
				args.put("deadlineQi", p.getDeadlineQi());
			}
			if (p.getDeadlineShi() != null) {
				hql += "and t.deadline <= :deadlineShi ";
				args.put("deadlineShi", p.getDeadlineShi());
			}
			if (p.getNoticeNo() != null && !p.getNoticeNo().equals("")) {
				hql += "and t.noticeNo = :noticeNo ";
				args.put("noticeNo", p.getNoticeNo());
			}
			if (p.getCustomerNo() != null && !p.getCustomerNo().equals("")) {
				hql += "and t.customerNo = :customerNo ";
				args.put("customerNo", p.getCustomerNo());
			}
			if (p.getOrderNo() != null && !p.getOrderNo().equals("")) {
				hql += "and t.orderNo = :orderNo ";
				args.put("orderNo", p.getOrderNo());
			}
			if(p.getGroupNumber()!=null&&!p.getGroupNumber().equals("")){
				hql += "and t.groupNumber = :groupNumber ";
				args.put("groupNumber", p.getGroupNumber());
			}
//			if (p.getAffiliationNo() != null && !p.getAffiliationNo().equals("")) {
//				hql += "and t.affiliationNo = :affiliationNo ";
//				args.put("affiliationNo", p.getAffiliationNo());
//			}
			String str = dataPermissionService.findAllList(user);
			if(str!=null&&!str.equals("")){
				if (p.getAffiliationNo() != null && !p.getAffiliationNo().equals("")) {
					hql += "and t.affiliationNo = :affiliationNo ";
					args.put("affiliationNo", p.getAffiliationNo());
				}else{
					hql += "and t.affiliationNo in ("+str+")";
				}
			}else{
				hql +="and t.createrNo = "+user.getCid();
			}
			countHql += hql;
			System.out.println(hql);
			footerHql += hql;
			String footerHql1= footerHql +" and type = 2";//押金
			footerHql+=" and type!=2";
			
			hql = setOrder(p, hql);
			
			
			List<Receivable> rbli = this.receivableDao.find(hql, args, p.getPage(), p.getRows());
			dg.setRows(rbli);
			dg.setTotal(this.receivableDao.count(countHql, args));
			List<Receivable> rli=this.receivableDao.find(hql, args, p.getPage(), p.getRows());
			
			List li=new ArrayList();
			BigDecimal incomeBeAmount=new BigDecimal(0);
			BigDecimal incomeAmount=new BigDecimal(0);
			BigDecimal balance=new BigDecimal(0);
			
			BigDecimal incomeBeAmount1=new BigDecimal(0);//押金
			BigDecimal incomeAmount1=new BigDecimal(0);//押金
			BigDecimal balance1=new BigDecimal(0);//押金
			
			for(Receivable r:rli){
				if(r.getType()!=2){
					incomeBeAmount=incomeBeAmount.add(r.getIncomeBeAmount());
					incomeAmount=incomeAmount.add(r.getIncomeAmount());
					balance=balance.add(r.getBalance());
				}else{
					incomeBeAmount1=incomeBeAmount1.add(r.getIncomeBeAmount());
					incomeAmount1=incomeAmount1.add(r.getIncomeAmount());
					balance1=balance1.add(r.getBalance());
				}
				
			}
			li.add(incomeBeAmount);
			li.add(incomeAmount);
			li.add(balance);
			
			li.add(incomeBeAmount1);
			li.add(incomeAmount1);
			li.add(balance1);
			Object[] sum2=new Object[6];
			Object[] sum=(Object[]) this.receivableDao.sum(footerHql, args).get(0);
			Object[] sum1=(Object[]) this.receivableDao.sum(footerHql1, args).get(0);
			sum2[0]=sum[0];
			sum2[1]=sum[1];
			sum2[2]=sum[2];
			if(sum1[0]==null){
				sum2[3]=new BigDecimal(0);
				sum2[4]=new BigDecimal(0);
				sum2[5]=new BigDecimal(0);
			}else{
				sum2[3]=sum1[0];
				sum2[4]=sum1[1];
				sum2[5]=sum1[2];
			}
			dg.getFooter().add(formatterSum("小计：", (Object[])li.toArray()));
			dg.getFooter().add(formatterSum("总计：", sum2));
			return dg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
		foolter.put("noticeNo", title);
		foolter.put("customerNo", (BigDecimal) sum[0]);
		foolter.put("tradeDate", (BigDecimal) sum[1]);
		foolter.put("orderNo", (BigDecimal) sum[2]);
		
		foolter.put("incomeBeAmount", (BigDecimal) sum[3]);
		foolter.put("incomeAmount", (BigDecimal) sum[4]);
		foolter.put("balance", (BigDecimal) sum[5]);
		return foolter;
	}

	/**
	 * 排序条件 处理
	 * 
	 * @param ReceivablePRMT
	 * @param hql
	 */
	public String setOrder(ReceivablePRMT p, String hql) {
		if (!StringTool.isEmptyOrNull(p.getSort()) && null != p.getOrder()) {
			hql += " order by " + p.getSort() + " " + p.getOrder();
		}else{
			hql += " order by t.yajinSort desc,t.tradeDate desc ";
		}
		return hql;
	}

	@Transactional
	public Receivable add(Receivable t) {
		t.setId((Long) this.receivableDao.save(t));
		return t;
	}
	
	/**
	 * 编码规则
	 * 10位数：AR+6位年月日+2位顺序号
	 *	Eg：AR14072101
	 */
	public String addCode(){
		String ar="AR";
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		int month = cal.get(Calendar.MONTH) + 1;
		String year = cal.get(Calendar.YEAR)+"";
		String y = year.substring(year.length()-2)+month+day;
		String xuhao="";
		String hql="select count(*) from Receivable where noticeNo like  '%"+y+"%'";
		Long count = receivableDao.count(hql);
		if(count!=0){
			if(count>9){
				xuhao=count.toString();	
			}else{
				count=count+1;
				xuhao=0+count.toString();
			}
		}else{
			xuhao="01";
		}
		return ar+y+xuhao;
	};

	@Transactional
	public Receivable revocation(Long id) throws MessageException {
		try {
			Receivable o = this.receivableDao.get(Receivable.class, id);
			if (o.getRevocationHas() == 1) {
				Receivable t = new Receivable();
				BeanUtils.copyProperties(o, t, new String[] { "id" });
				t.setIncomeBeAmount(t.getIncomeBeAmount().negate());
				t.setBalance(t.getIncomeBeAmount());
				t.setNoticeNo("*"+t.getNoticeNo());
				t.setRevocationHas(2);
				t.setReverseHas(1);
				t.setId((Long) this.receivableDao.save(t));
				o.setRevocationHas(2);
				o.setReverseHas(1);
				this.receivableDao.update(o);
				return t;
			}
		} catch (BeansException e) {
			e.printStackTrace();
		}
		throw new MessageException("不可撤销！");
	}

	@Transactional
	public Receivable deadline(DeadlineChg d) {
		Receivable t = this.receivableDao.get(Receivable.class, d.getRcvbid());
		t.setDeadline(d.getModifiedOn());
		t.setDeadlineHas(1);
		this.receivableDao.update(t);
		this.deadlineChgDao.save(d);
		return t;
	}

	@Transactional
	public List<ReceivableDetail> income(CustomerPayment t, List<ReceivableDetail> rdl,User user) throws MessageException {
		t.setCreateDate(new Date());
		t.setDataType(1);
		t.setSettlementNo(jiesuanCode("RCV"));
		t.setStatus(5);
		t.setFactorage(BigDecimal.ZERO);
		t.setId((Long) this.customerPaymentDao.save(t));
		for (ReceivableDetail rd : rdl) {
			Receivable r = this.receivableDao.get(Receivable.class, rd.getRcvbid());
			r.setIncomeAmount(r.getIncomeAmount().add(rd.getIncomeAmount()));
			r.setBalance(r.getBalance().subtract(rd.getIncomeAmount()));
//			r.setRevocationHas(2);
			if (r.getReverseHas() == 2) {
				r.setReverseHas(1);
				Receivable rf = new Receivable();
				BeanUtils.copyProperties(r, rf, new String[] { "id" });
				rf.setIncomeBeAmount(rf.getIncomeBeAmount().negate());
				rf.setIncomeAmount(BigDecimal.ZERO);
				rf.setBalance(rf.getIncomeBeAmount());
				rf.setCreateDate(new Date());
				rf.setNoticeNo("*"+r.getNoticeNo());
				rf.setOriginalPaymentMethod(t.getPaymentMethod());
				rf.setDeadlineHas(0);
				this.receivableDao.save(rf);
			}
			this.receivableDao.update(r);
			rd.setCtpmid(t.getId());
			rd.setId((Long) this.receivableDetailDao.save(rd));
		}
		if (t.getPaymentMethod() != 2 && t.getPaymentMethod() != 3) {
			t.setStatus(6);
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
			ad.setIncome(t.getNetAmount().add(t.getCustomerFactorage()));
			ad.setExpenditure(BigDecimal.ZERO);
			ad.setBalance(at.getAccountBalance());
			ad.setVoucherNo(t.getVoucherNo());
			ad.setRemark(t.getRemark());
			ad.setStatus(1);
			ad.setType(1);
			ad.setSettlementNo(t.getSettlementNo());
			ad.setSubjects("业务收入");
			ad.setCurrencyType(at.getAccountCurrency());
			ad.setCid(user.getCid());
			// 保存业务明细
			this.accountDetailDao.save(ad);
			// 记录手续费
			// if (t.getCustomerFactorage().compareTo(BigDecimal.ZERO) != 0) {
			// // 计算账户余额
			// at.setAccountBalance(at.getAccountBalance().add(t.getCustomerFactorage()));
			// AccountDetail adfee = new AccountDetail();
			// BeanUtils.copyProperties(ad, adfee);
			// adfee.setIncome(t.getCustomerFactorage());
			// adfee.setExpenditure(BigDecimal.ZERO);
			// adfee.setBalance(at.getAccountBalance());
			// adfee.setSubjects("客户手续费");
			// this.accountDetailDao.save(adfee);
			// }
			this.accountDao.update(at);
			this.customerPaymentDao.update(t);
		}
		return rdl;
	}

	@Transactional
	public List<ReceivableDetail> refund(CustomerPayment t, List<ReceivableDetail> rdl,User user) throws MessageException {
		t.setCreateDate(new Date());
		t.setDataType(2);
		t.setSettlementNo(jiesuanCode("PMT"));
		t.setStatus(6);
		t.setId((Long) this.customerPaymentDao.save(t));
		for (ReceivableDetail rd : rdl) {
			Receivable r = this.receivableDao.get(Receivable.class, rd.getRcvbid());
			r.setIncomeAmount(r.getIncomeAmount().add(rd.getIncomeAmount()));
			r.setBalance(r.getBalance().subtract(rd.getIncomeAmount()));
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
	public void apply(CustomerPayment t, List<ReceivableDetail> rdl) throws MessageException {
		t.setCreateDate(new Date());
		t.setDataType(2);
		t.setSettlementNo(jiesuanCode("PMT"));
		t.setStatus(1);
		t.setNetAmount(BigDecimal.ZERO);
		t.setBankRate(new BigDecimal("1"));
		t.setFactorage(BigDecimal.ZERO);
		t.setExchangeProfitLoss(BigDecimal.ZERO);
		t.setPublication(BigDecimal.ZERO);
		t.setSettlement(BigDecimal.ZERO);
		t.setId((Long) this.customerPaymentDao.save(t));
		for (ReceivableDetail rd : rdl) {
			Receivable r = this.receivableDao.get(Receivable.class, rd.getRcvbid());
			r.setIncomeAmount(r.getIncomeAmount().add(rd.getIncomeAmount()));
			r.setBalance(r.getBalance().subtract(rd.getIncomeAmount()));
			this.receivableDao.update(r);
			rd.setCtpmid(t.getId());
			rd.setId((Long) this.receivableDetailDao.save(rd));
		}
	}
	
	public void save(Receivable t){
		String hql="from Receivable where noticeNo = '"+t.getNoticeNo()+"'";
		List<Receivable> li = receivableDao.find(hql);
		if(li.size()==0){
			if(t.getType()==2){
				t.setYajinSort(10);
			}else{
				t.setYajinSort(1);
			}
			receivableDao.save(t);
		}
	}

	public Receivable OrderZuofei(String noticeNo) {
		Receivable r=receivableDao.get("from Receivable where noticeNo = '"+ noticeNo+"'");
		Receivable rb = new Receivable();
		BeanUtils.copyProperties(r, rb, new String[] { "id" });
		rb.setIncomeBeAmount(rb.getIncomeBeAmount().negate());
		rb.setIncomeAmount(BigDecimal.ZERO);
		rb.setBalance(rb.getIncomeBeAmount());
		rb.setNoticeNo("*"+r.getNoticeNo());
		rb.setCreateDate(new Date());
		rb.setId((Long)this.receivableDao.save(rb));
		return rb;
		
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
		String hql="select count(*) from CustomerPayment where settlementNo like  '%"+y+"%'";
		Long count = customerPaymentDao.count(hql);
		
		String hql1="select count(*) from SupplierPayment where settlementNo like  '%"+y+"%'";
		Long count1 = supplierPaymentDao.count(hql1);
		
		String hql2="select count(*) from CommissionPayment where settlementNo like  '%"+y+"%'";
		Long count2 =commissionPaymentDao.count(hql2);
		count=count+count1+count2;
		if(count!=0){
			if(count >= 9){
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
	
	/**
	 * 通过应收账款ID查询应收通知单
	 */
	public Receivable findReceivable(String rcvbid){
		String hql="from Receivable where id = "+rcvbid;
		return receivableDao.get(hql);
	}
	
	/**
	 * 修改付款通知单的异常状态
	 */
	public void UpdateAbnormal(String noticeNo,String abnormalStatus){
		String hql="update Receivable set abnormalStatus = "+abnormalStatus+" where noticeNo = "+noticeNo;
		receivableDao.executeHql(hql);
	}
	
	/**
	 * 订单中心需要的接口
	 * 根据客户编号查询所有未销账的数据
	 */
	public double findReceivableSearch(String customerNo,String startDate,String endDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String hql="from Receivable where balance != 0 and customerNo = '"+customerNo+"'";
		Map<String, Object> args = new HashMap<String, Object>();
		if(startDate!=null&&!startDate.equals("")){
			try {
				Date date=sdf.parse(startDate);
				hql += "and deadline > :startDate ";
				args.put("startDate", date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(endDate!=null&&!endDate.equals("")){
			try {
				Date date1=sdf.parse(endDate);
				hql += "and deadline <= :endDate ";
				args.put("endDate", date1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		List<Receivable> li = receivableDao.find(hql,args);
		double moner=0.0;
		for(Receivable rb:li){
			moner+=rb.getBalance().doubleValue();
		}
		return moner;
	}
	
	//报表应收账款借口
	public List<AccountsReceivable> accountsReceivable(ReceivablePRMT p,String xianshi,String xianshi1,String xianshi2){
		BaseJsonResponse bjr=new BaseJsonResponse();
		List<AccountsReceivable> arli=new ArrayList<AccountsReceivable>();
		List<AccountsReceivable> arli1=new ArrayList<AccountsReceivable>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String hql="from Receivable t where 1=1 ";
		Map<String, Object> args = new HashMap<String, Object>();
		if (p.getTradeDateQi() != null) {
			hql += "and t.tradeDate >= :tradeDateQi ";
			args.put("tradeDateQi", p.getTradeDateQi());
		}
		if (p.getTradeDateShi() != null) {
			hql += "and t.tradeDate <= :tradeDateShi ";
			args.put("tradeDateShi", p.getTradeDateShi());
		}
		if (p.getCustomerNo() != null && !p.getCustomerNo().equals("")) {
			hql += "and t.customerNo in ("+p.getCustomerNo()+")";
		}
		if(p.getGroupNumber()!=null&&!p.getGroupNumber().equals("")){
			hql += "and t.groupNumber = :groupNumber ";
			args.put("groupNumber", p.getGroupNumber());
		}
		if(xianshi!=null&&xianshi.equals("0")){//不包含0的数据
			hql += "and t.balance!=0 ";
		}
		if(xianshi1!=null&&xianshi1.equals("0")){//不包含手动填加的数据
			hql += "and t.affiliationNo<>0 ";
		}
		if(xianshi2!=null&&xianshi2.equals("0")){//不包含押金
			hql += "and t.type not in (2,5)";
		}
		if(p.getCreaterNo()!=null&&!p.getCreaterNo().equals("")){
			hql += "and t.createrNo = :createrNo ";
			args.put("createrNo", p.getCreaterNo());
		}
		hql+=" order by customerNo";
		List<Receivable> rbli = receivableDao.find(hql, args);
		List<Receivable> rbli1=new ArrayList<Receivable>();
		for(Receivable r:rbli){
			if(r.getNoticeNo().indexOf("*")!=-1){
				rbli1.add(r);
				continue;
			}
			Map<String, String> params = new HashMap<String, String>(0);
			params.put("noticeNo", r.getNoticeNo());
			String url=SoaPropertiesUtil.soaUrl.getProperty("noticeNo");
			String json = httpClient.getDoPostResponseDataByURL(url, params,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			ArrayList<Confirmation> cfli = new ArrayList<Confirmation>(0);
			AccountsReceivable ar=new AccountsReceivable();
			if(bjr!=null&&bjr.getFlag()==1){
				cfli=(ArrayList<Confirmation>) JSON
				.parseArray(bjr.getInfo(), Confirmation.class);
			}
			if(cfli.size()>0){
				ar.setConfirmationNumber(cfli.get(0).getConfirmationNumber());
			}
			ar.setCustomerIdAndName(r.getCustomerNo()+"-"+r.getCustomerName());
			ar.setBalance(Double.parseDouble(r.getBalance().toString()));
			ar.setDeadline(sdf.format(r.getDeadline()));
			ar.setIncomeAmount(r.getIncomeAmount().toString());
			ar.setIncomeBeAmount(r.getIncomeBeAmount().toString());
			ar.setNoticeNo(r.getNoticeNo());
			ar.setOrderNo(r.getOrderNo());
			ar.setPassengerName(r.getPassengerName());
			ar.setTradeDate(sdf.format(r.getTradeDate()));
			arli.add(ar);
			
		}
		for(AccountsReceivable sar:arli){
			boolean fale=true;
			for(Receivable r1:rbli1){
				if(sar.getNoticeNo().equals(r1.getNoticeNo().substring(1))){
					fale=false;
				}
			}
			if(fale){
				arli1.add(sar);
			}
		}
		return arli;
	}
	//报表客户对账单接口
	public List<CustomersOntheBill> customersOntheBill(ReceivablePRMT p){
		BaseJsonResponse bjr=new BaseJsonResponse();
		List<CustomersOntheBill> coblist=new ArrayList<CustomersOntheBill>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String hql="from Receivable t where 1=1 ";
		Map<String, Object> args = new HashMap<String, Object>();
		if (p.getTradeDateQi() != null) {
			hql += "and t.tradeDate >= :tradeDateQi ";
			args.put("tradeDateQi", p.getTradeDateQi());
		}
		if (p.getTradeDateShi() != null) {
			hql += "and t.tradeDate <= :tradeDateShi ";
			args.put("tradeDateShi", p.getTradeDateShi());
		}
		if (p.getCustomerNo() != null && !p.getCustomerNo().equals("")) {
			hql += "and t.customerNo in ("+p.getCustomerNo()+")";
		}
		List<Receivable> rbli = receivableDao.find(hql, args);
		for(Receivable r:rbli){
			Map<String, String> params = new HashMap<String, String>(0);
			params.put("noticeNo", r.getNoticeNo());
			String url=SoaPropertiesUtil.soaUrl.getProperty("noticeNo");
			String json = httpClient.getDoPostResponseDataByURL(url, params,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			ArrayList<Confirmation> cfli = new ArrayList<Confirmation>(0);
			if(bjr.getFlag()==1){
				cfli=(ArrayList<Confirmation>) JSON
						.parseArray(bjr.getInfo(), Confirmation.class);
				int i=0;
				for(Confirmation cf:cfli){
					String[] str=r.getPassengerName().split(",");
					CustomersOntheBill cob=new CustomersOntheBill();
					if(cf.getConfirmationNumber()!=null&&cf.getProduct()!=null){
						cob.setConfirmationNumber(cf.getConfirmationNumber());
						cob.setFlight(cf.getFlight());
						cob.setProduct(cf.getProduct());
						cob.setReservation(cf.getReservation());
						cob.setRouting(cf.getRouting());
						cob.setSerFee(cf.getSerFee());
						cob.setSetOoutDate(cf.getSetOutDate());
						cob.setSubTotal(cf.getSubTotal());
						cob.setTax(cf.getTax());
						cob.setSelling(cf.getSelling());
					}
					cob.setNoticeNo(r.getNoticeNo());
					cob.setPassengerName(str[i]);
					cob.setTradeDate(sdf.format(r.getTradeDate()));
					coblist.add(cob);
					i++;
				}
			}
		}
		return coblist;
	}
	//业务员欠款报表
	public List<SalesmanArrearsReport> salesmanArrearsReport(ReceivablePRMT p,String accountType,String affiliationNo){
		List<SalesmanArrearsReport> sarlist=new ArrayList<SalesmanArrearsReport>();
		List<SalesmanArrearsReport> sarlist1=new ArrayList<SalesmanArrearsReport>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(accountType!=null&&!accountType.equals("")){
			String customerNo="";
			String hql1="from CollectpayAccount where accountType = '"+accountType+"'";
			List<CollectpayAccount> cali = collectpayAccountDao.find(hql1);
			for(CollectpayAccount ca:cali){
				if(!p.getCustomerNo().equals("")){
					customerNo+=",";
				}
				customerNo+=ca.getAccountCode();
			}
			p.setCustomerNo(customerNo);
		}
		
		String hql="from Receivable t where t.balance!=0";
		Map<String, Object> args = new HashMap<String, Object>();
		if (affiliationNo!= null&&!affiliationNo.equals("")) {
			hql += " and t.affiliationNo in ("+affiliationNo+")";
		}else{
			if (p.getDepts() != null&&!p.getDepts().equals("")) {
				hql += " and t.depts in ("+p.getDepts()+")";
			}
			hql += " and t.affiliationNo !=0 ";
		}
		if (p.getTradeDateQi() != null) {
			hql += " and t.tradeDate >= :tradeDateQi ";
			args.put("tradeDateQi", p.getTradeDateQi());
		}
		if (p.getTradeDateShi() != null) {
			hql += " and t.tradeDate <= :tradeDateShi ";
			args.put("tradeDateShi", p.getTradeDateShi());
		}
		if (p.getCustomerNo()!= null&&!p.getCustomerNo().equals("")) {
			hql += " and t.customerNo in ("+p.getCustomerNo()+")";
		}
		hql+=" order by affiliationNo";
		List<Receivable> rbli = receivableDao.find(hql, args);
		List<Receivable> rbli1=new ArrayList<Receivable>();
		for(Receivable rb:rbli){
			boolean fale=true;
			if(rb.getNoticeNo().indexOf("*")!=-1){
				rbli1.add(rb);
				continue;
			}
			SalesmanArrearsReport sar=new SalesmanArrearsReport();
			sar.setBalance(rb.getBalance().toString());
			String hql1="from CollectpayAccount where accountCode = '"+rb.getCustomerNo()+"'";
			CollectpayAccount ca = collectpayAccountDao.get(hql1);
//			System.out.println(i);
			if(ca!=null){
				if(ca.getAccountType().equals("企业客户")){
					sar.setCustomer("Corp. Customer/签约客户："+rb.getCustomerNo()+"-"+rb.getCustomerName());
				}else if(ca.getAccountType().equals("散客客户")){
					sar.setCustomer("Corp. Customer/散客客户："+rb.getCustomerNo()+"-"+rb.getCustomerName());
				}else if(ca.getAccountType().equals("同行客户")){
					sar.setCustomer("Corp. Customer/同行客户："+rb.getCustomerNo()+"-"+rb.getCustomerName());
				}else if(ca.getAccountType().equals("内部客户")){
					sar.setCustomer("Corp. Customer/内部客户："+rb.getCustomerNo()+"-"+rb.getCustomerName());
				}
				
				sar.setDeadline(sdf.format(rb.getDeadline()));
				sar.setIncomeAmount(rb.getIncomeAmount().toString());
				sar.setIncomeBeAmount(rb.getIncomeBeAmount().toString());
				sar.setIssuer(rb.getAffiliationPerson());
				sar.setIssuerCode(rb.getAffiliationNo().toString());
				sar.setNoticeNo(rb.getNoticeNo());
				sar.setTradeDate(sdf.format(rb.getTradeDate()));
				sarlist.add(sar);
			}
		}
		for(SalesmanArrearsReport sar:sarlist){
			boolean fale=true;
			for(Receivable r1:rbli1){
				if(sar.getNoticeNo().equals(r1.getNoticeNo().substring(1))){
					fale=false;
				}
			}
			if(fale){
				sarlist1.add(sar);
			}
		}
		
		return sarlist1;
	}
	
	//根据通知单号查询未销账金额
	public double findMoney(String noticeNo){
		String hql="from Receivable where balance != 0 and type = 5 and noticeNo = '"+noticeNo+"'";
		List<Receivable> li = receivableDao.find(hql);
		double moner=0.0;
		for(Receivable rb:li){
			moner+=rb.getBalance().doubleValue();
		}
		return moner;
	}
	
	public List<Receivable> findReceivableReport(){
		String hql="from Receivable where incomeBeAmount != 0 and incomeAmount!=0 and balance=0";
		return receivableDao.find(hql);
	}

	//同意作废通知单
	public Receivable tongyi(String id) {
		Receivable r=receivableDao.get(Receivable.class, Long.parseLong(id));
		r.setCancelStatus(1);
		String hql="update Receivable set cancelStatus = 1 where id = "+id;
		receivableDao.executeHql(hql);
		Receivable pf = new Receivable();
		BeanUtils.copyProperties(r, pf, new String[] { "id" });
		pf.setNoticeNo("*"+r.getNoticeNo());
		pf.setIncomeBeAmount(pf.getIncomeBeAmount().negate());
		pf.setIncomeAmount(BigDecimal.ZERO);
		pf.setBalance(pf.getIncomeBeAmount());
		pf.setCreateDate(new Date());
		pf.setId((Long)this.receivableDao.save(pf));
		return pf;
	}

	//不同意作废通知单
	public void butongyi(String id) {
		String hql="update Receivable set cancelStatus = 1 where id = "+id;
		receivableDao.executeHql(hql);
	}
	
	/**
	 * 订单中心作废单据，需要调用接口，改变申请作废状态
	 */
	public void UpdateCancelStatus(String noticeNo){
		String hql="update Receivable set cancelStatus = 2 where noticeNo = '" + noticeNo + "'";
		receivableDao.executeHql(hql);
	}
}
