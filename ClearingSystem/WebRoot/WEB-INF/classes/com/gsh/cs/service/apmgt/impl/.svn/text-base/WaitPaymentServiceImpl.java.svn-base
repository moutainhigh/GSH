package com.gsh.cs.service.apmgt.impl;

import java.math.BigDecimal;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import com.gsh.cs.base.MessageException;
import com.gsh.cs.dao.BaseDaoI;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.Interface.AccountsPayable;
import com.gsh.cs.model.Interface.SalesmanArrearsReport;
import com.gsh.cs.model.base.Account;
import com.gsh.cs.model.base.AccountDetail;
import com.gsh.cs.model.base.CommissionPayment;
import com.gsh.cs.model.base.CustomerPayment;
import com.gsh.cs.model.base.Payable;
import com.gsh.cs.model.base.PayableDetail;
import com.gsh.cs.model.base.Receivable;
import com.gsh.cs.model.base.SupplierPayment;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.PayablePRMT;
import com.gsh.cs.service.apmgt.WaitPaymentServiceI;
import com.gsh.cs.service.bset.SettlementExchangeRateServiceI;
import com.gsh.cs.service.bsp.DataPermissionServiceI;
import com.gsh.cs.tools.StringTool;

@Service("waitPaymentService")
public class WaitPaymentServiceImpl implements WaitPaymentServiceI {

	private BaseDaoI<Payable> payableDao;
	private BaseDaoI<PayableDetail> payableDetailDao;
	private BaseDaoI<Account> accountDao;
	private BaseDaoI<AccountDetail> accountDetailDao;
	private BaseDaoI<SupplierPayment> supplierPaymentDao;
	private BaseDaoI<CustomerPayment> customerPaymentDao;
	private BaseDaoI<CommissionPayment> commissionPaymentDao;
	private SettlementExchangeRateServiceI settlementExchangeRateService;
	@Resource DataPermissionServiceI dataPermissionService;
	@Autowired
	public void setCommissionPaymentDao(
			BaseDaoI<CommissionPayment> commissionPaymentDao) {
		this.commissionPaymentDao = commissionPaymentDao;
	}

	@Autowired
	public void setCustomerPaymentDao(BaseDaoI<CustomerPayment> customerPaymentDao) {
		this.customerPaymentDao = customerPaymentDao;
	}

	@Autowired
	public void setPayableDetailDao(BaseDaoI<PayableDetail> payableDetailDao) {
		this.payableDetailDao = payableDetailDao;
	}

	@Autowired
	public void setPayableDao(BaseDaoI<Payable> payableDao) {
		this.payableDao = payableDao;
	}

	@Autowired
	public void setSupplierPaymentDao(BaseDaoI<SupplierPayment> supplierPaymentDao) {
		this.supplierPaymentDao = supplierPaymentDao;
	}

	@Autowired
	public void setAccountDetailDao(BaseDaoI<AccountDetail> accountDetailDao) {
		this.accountDetailDao = accountDetailDao;
	}

	@Autowired
	public void setAccountDao(BaseDaoI<Account> accountDao) {
		this.accountDao = accountDao;
	}
	@Autowired
	public void setSettlementExchangeRateService(
			SettlementExchangeRateServiceI settlementExchangeRateService) {
		this.settlementExchangeRateService = settlementExchangeRateService;
	}

	public Datagrid findPayable(PayablePRMT p,User user) {
		try {
		Datagrid dg = new Datagrid();
		String hql = "where t.balance != 0 ", countHql = "select count(*) ";
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
		if (p.getSupplierNo() != null && !p.getSupplierNo().equals("")) {
			hql += "and t.supplierNo = :supplierNo ";
			args.put("supplierNo", p.getSupplierNo());
		}
		if (p.getExchangeNo() != null && !p.getExchangeNo().equals("")) {
			hql += "and t.exchangeNo = :exchangeNo ";
			args.put("exchangeNo", p.getExchangeNo());
		}
		if (p.getProductNo() != null && !p.getProductNo().equals("")) {
			hql += "and t.productNo = :productNo ";
			args.put("productNo", p.getProductNo());
		}
		if (p.getOrderNo() != null && !p.getOrderNo().equals("")) {
			hql += "and t.orderNo = :orderNo ";
			args.put("orderNo", p.getOrderNo());
		}
		if(p.getGroupNumber()!=null&&!p.getGroupNumber().equals("")){
			hql += "and t.groupNumber = :groupNumber ";
			args.put("groupNumber", p.getGroupNumber());
		}
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
		
		hql="from Payable t "+hql;
		countHql += hql;
		hql = setOrder(p, hql);
		List<Payable> li = this.payableDao.find(hql, args, p.getPage(), p.getRows());
		List<Payable> li1 = this.payableDao.find(hql, args);
		dg.setRows(this.payableDao.find(hql, args, p.getPage(), p.getRows()));
		dg.setTotal(this.payableDao.count(countHql, args));
		System.out.println("aaaaaaaaaaaaaaaaaaa");
		System.out.println(sumCurrencySum(li));
		System.out.println(sumCurrencySum(li1));
		System.out.println("bbbbbbbbbbbbbbbbbbb");
		dg.getFooter().add(formatterSum("小计：", sumCurrencySum(li)));
		dg.getFooter().add(formatterSum("总计：", sumCurrencySum(li1)));
		return dg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object[] sumCurrencySum(List<Payable> li){
		Object[] paySum=new Object[6];//小计
		BigDecimal pay1=new BigDecimal(0);
		BigDecimal pay2=new BigDecimal(0);
		BigDecimal pay3=new BigDecimal(0);
		BigDecimal pay4=new BigDecimal(0);
		BigDecimal pay5=new BigDecimal(0);
		BigDecimal pay6=new BigDecimal(0);
		for (Payable pab : li) {
			if(pab.getType()!=2){//押金
				if(!pab.getCurrencyType().equals("CNY")){
					pay1 = pay1.add(new BigDecimal(settlementExchangeRateService.findCurrency(pab.getPayBeAmount(), pab.getCurrencyType())));
					pay2=pay2.add(new BigDecimal(settlementExchangeRateService.findCurrency(pab.getPayAmount(), pab.getCurrencyType())));
					pay3=pay3.add(new BigDecimal(settlementExchangeRateService.findCurrency(pab.getBalance(), pab.getCurrencyType())));
				}else{
					pay1 = pay1.add(pab.getPayBeAmount());
					pay2=pay2.add(pab.getPayAmount());
					pay3=pay3.add(pab.getBalance());
				}
			}else{
				if(!pab.getCurrencyType().equals("CNY")){
					pay4= pay4.add(new BigDecimal(settlementExchangeRateService.findCurrency(pab.getPayBeAmount(), pab.getCurrencyType())));
					pay5=pay5.add(new BigDecimal(settlementExchangeRateService.findCurrency(pab.getPayAmount(), pab.getCurrencyType())));
					pay6=pay6.add(new BigDecimal(settlementExchangeRateService.findCurrency(pab.getBalance(), pab.getCurrencyType())));
				}else{
					pay4 = pay4.add(pab.getPayBeAmount());
					pay5=pay5.add(pab.getPayAmount());
					pay6=pay6.add(pab.getBalance());
				}
			}
			
		}
		paySum[0]=pay1;
		paySum[1]=pay2;
		paySum[2]=pay3;
		paySum[3]=pay4;
		paySum[4]=pay5;
		paySum[5]=pay6;
		return paySum;
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
		}
		for (Object[] sum : xj) {
			Map foolter = new HashMap();
			foolter.put("payBeAmount", (BigDecimal) sum[0]);
			foolter.put("payAmount", (BigDecimal) sum[1]);
			foolter.put("balance", (BigDecimal) sum[2]);
			((List) dg.getSumfooter().get(sum[3])).add(foolter);
			
		}
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
		foolter.put("supplierNo", (BigDecimal) sum[0]);
		foolter.put("tradeDate", (BigDecimal) sum[1]);
		foolter.put("productOrderNo", (BigDecimal) sum[2]);
		
		foolter.put("payBeAmount", (BigDecimal) sum[3]);
		foolter.put("payAmount", (BigDecimal) sum[4]);
		foolter.put("balance", (BigDecimal) sum[5]);
		return foolter;
	}

	/**
	 * 排序条件 处理
	 * 
	 * @param PayablePRMT
	 * @param hql
	 */
	public String setOrder(PayablePRMT p, String hql) {
		if (!StringTool.isEmptyOrNull(p.getSort()) && null != p.getOrder()) {
			hql += " order by " + p.getSort() + " " + p.getOrder();
		}else{
			hql += " order by t.yajinSort desc,t.tradeDate desc ";
		}
		return hql;
	}

	@Transactional
	public Payable revocation(Long id) throws MessageException {
		Payable payable = this.payableDao.get(Payable.class, id);
		if (payable.getRevocationHas() == 1) {
			Payable t = new Payable();
			BeanUtils.copyProperties(payable, t, new String[] { "id" });
			t.setPayBeAmount(t.getPayBeAmount().negate());
			t.setBalance(t.getPayBeAmount());
			t.setExchangeNo("*"+t.getExchangeNo());
			t.setRevocationHas(2);
			t.setReverseHas(1);
			t.setId((Long) this.payableDao.save(t));
			payable.setRevocationHas(2);
			payable.setReverseHas(1);
			this.payableDao.update(payable);
			return t;
		}
		throw new MessageException("不可撤销！");
	}

	@Transactional
	public List<PayableDetail> pay(SupplierPayment t, List<PayableDetail> pl,User user) throws MessageException {
		t.setNoticeStatus(2);
		t.setProductStatus(2);
		t.setCreateDate(new Date());
		t.setDataType(1);
		t.setSettlementNo(jiesuanCode("PMT"));
		t.setStatus(5);
		t.setTemporaryHas(1);
		t.setExchangeNos("");
		t.setId((Long) this.supplierPaymentDao.save(t));
		for (PayableDetail payableDetail : pl) {
			Payable p = this.payableDao.get(Payable.class, payableDetail.getPablid());
			p.setPayAmount(p.getPayAmount().add(payableDetail.getPayAmount()));
			p.setBalance(p.getBalance().subtract(payableDetail.getPayAmount()));
			p.setRevocationHas(2);
			if (p.getReverseHas() == 2) {
				p.setReverseHas(1);
				Payable pf = new Payable();
				BeanUtils.copyProperties(p, pf, new String[] { "id" });
				pf.setPayBeAmount(pf.getPayBeAmount().negate());
				pf.setPayAmount(BigDecimal.ZERO);
				pf.setExchangeNo("*"+p.getExchangeNo());
				pf.setBalance(pf.getPayBeAmount());
				pf.setCreateDate(new Date());
				this.payableDao.save(pf);
			}
			this.payableDao.update(p);
			if(p.getType()==3){
				payableDetail.setStatusZanzhi(1);
			}else{
				payableDetail.setStatusZanzhi(0);
			}
			payableDetail.setSupmid(t.getId());
			payableDetail.setId((Long) this.payableDetailDao.save(payableDetail));
			if (t.getNoticeStatus() == 2 && p.getNoticeStatus() == 1) {
				t.setNoticeStatus(1);
			}
			if (t.getProductStatus() == 2 && p.getProductStatus() == 1) {
				t.setProductStatus(1);
			}
			if (p.getType() == 3) {
				t.setTemporaryHas(2);
			}
			t.setExchangeNos(t.getExchangeNos() + "," + p.getExchangeNo());
		}
		t.setExchangeNos(t.getExchangeNos().substring(1));
		this.supplierPaymentDao.update(t);
		// 获取账户信息-计算余额
		String hql = "from Account t where t.accountCode = :accountCode";
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("accountCode", t.getFundAccount());
		Account at = this.accountDao.find(hql, args).get(0);
		at.setAccountBalance(at.getAccountBalance().subtract(t.getPayAmount().multiply(t.getBankRate())));
		if (at.getAccountBalance().compareTo(BigDecimal.ZERO) < 0) {
			throw new MessageException("账户余额不足！");
		}
		// 记录账户明细
		AccountDetail ad = new AccountDetail();
		ad.setAccountCode(at.getAccountCode());
		ad.setAccountBank(at.getAccountBank());
		ad.setTradeDate(t.getTradeDate());
		ad.setTradeNo(t.getTradeNo());
		ad.setTradeObject(t.getSupplierNo());
		ad.setIncome(BigDecimal.ZERO);
		ad.setExpenditure(t.getPayAmount().multiply(t.getBankRate()));
		ad.setBalance(at.getAccountBalance());
		ad.setVoucherNo(t.getVoucherNo());
		ad.setRemark(t.getRemark());
		ad.setStatus(1);
		ad.setType(2);
		ad.setSettlementNo(t.getSettlementNo());
		ad.setSubjects("业务支出");
		ad.setCurrencyType(t.getCurrencyType());
		ad.setCid(user.getCid());
		// 记录手续费
		if (t.getFactorage().compareTo(BigDecimal.ZERO) != 0) {
			// 计算账户余额
			at.setAccountBalance(at.getAccountBalance().subtract(t.getFactorage()));
			if (at.getAccountBalance().compareTo(BigDecimal.ZERO) < 0) {
				throw new MessageException("账户余额不足！");
			}
			AccountDetail adfee = new AccountDetail();
			BeanUtils.copyProperties(ad, adfee);
			adfee.setIncome(BigDecimal.ZERO);
			adfee.setExpenditure(t.getFactorage());
			adfee.setBalance(at.getAccountBalance());
			adfee.setSubjects("手续费");
			adfee.setCid(user.getCid());
			this.accountDetailDao.save(adfee);
		}
		// 更新账户
		this.accountDao.update(at);
		// 保存业务明细
		this.accountDetailDao.save(ad);
		return pl;
	}

	@Transactional
	public List<PayableDetail> income(SupplierPayment t, List<PayableDetail> pl,User user) throws MessageException {
		t.setNoticeStatus(2);
		t.setProductStatus(2);
		t.setCreateDate(new Date());
		t.setDataType(2);
		t.setSettlementNo(jiesuanCode("RCV"));
		t.setStatus(5);
		t.setTemporaryHas(1);
		t.setExchangeNos("");
		t.setId((Long) this.supplierPaymentDao.save(t));
		for (PayableDetail payableDetail : pl) {
			Payable p = this.payableDao.get(Payable.class, payableDetail.getPablid());
			p.setPayAmount(p.getPayAmount().add(payableDetail.getPayAmount()));
			p.setBalance(p.getBalance().subtract(payableDetail.getPayAmount()));
			p.setRevocationHas(2);
			if (p.getReverseHas() == 2) {
				p.setReverseHas(1);
				Payable pf = new Payable();
				BeanUtils.copyProperties(p, pf, new String[] { "id" });
				pf.setPayBeAmount(pf.getPayBeAmount().negate());
				pf.setExchangeNo("*"+p.getExchangeNo());
				pf.setPayAmount(BigDecimal.ZERO);
				pf.setBalance(pf.getPayBeAmount());
				pf.setCreateDate(new Date());
				this.payableDao.save(pf);
			}
			this.payableDao.update(p);
			if(p.getType()==3){
				payableDetail.setStatusZanzhi(1);
			}else{
				payableDetail.setStatusZanzhi(0);
			}
			payableDetail.setSupmid(t.getId());
			payableDetail.setId((Long) this.payableDetailDao.save(payableDetail));
			if (t.getNoticeStatus() == 2 && p.getNoticeStatus() == 1) {
				t.setNoticeStatus(1);
			}
			if (t.getProductStatus() == 2 && p.getProductStatus() == 1) {
				t.setProductStatus(1);
			}
			if (p.getType() == 3) {
				t.setTemporaryHas(2);
			}
			t.setExchangeNos(t.getExchangeNos() + "," + p.getExchangeNo());
		}
		t.setExchangeNos(t.getExchangeNos().substring(1));

		this.supplierPaymentDao.update(t);

		// 获取账户信息-计算余额
		String hql = "from Account t where t.accountCode = :accountCode";
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("accountCode", t.getFundAccount());
		Account at = this.accountDao.find(hql, args).get(0);
		at.setAccountBalance(at.getAccountBalance().subtract(t.getPayAmount().multiply(t.getBankRate())));
		// 记录账户明细
		AccountDetail ad = new AccountDetail();
		ad.setAccountCode(at.getAccountCode());
		ad.setAccountBank(at.getAccountBank());
		ad.setTradeDate(t.getTradeDate());
		ad.setTradeNo(t.getTradeNo());
		ad.setTradeObject(t.getSupplierNo());
		ad.setIncome(t.getPayAmount().multiply(t.getBankRate()).abs());
		ad.setExpenditure(BigDecimal.ZERO);
		ad.setBalance(at.getAccountBalance());
		ad.setVoucherNo(t.getVoucherNo());
		ad.setRemark(t.getRemark());
		ad.setStatus(1);
		ad.setType(2);
		ad.setSettlementNo(t.getSettlementNo());
		ad.setSubjects("业务支出");
		ad.setCurrencyType(t.getCurrencyType());
		ad.setCid(user.getCid());
		// 记录手续费
		if (t.getFactorage().compareTo(BigDecimal.ZERO) != 0) {
			// 计算账户余额
			at.setAccountBalance(at.getAccountBalance().add(t.getFactorage()));
			AccountDetail adfee = new AccountDetail();
			BeanUtils.copyProperties(ad, adfee);
			adfee.setIncome(t.getFactorage());
			adfee.setExpenditure(BigDecimal.ZERO);
			adfee.setBalance(at.getAccountBalance());
			adfee.setSubjects("手续费");
			adfee.setCid(user.getCid());
			this.accountDetailDao.save(adfee);
		}
		// 更新账户
		this.accountDao.update(at);
		// 保存业务明细
		this.accountDetailDao.save(ad);
		return pl;
	}

	@Transactional
	public List<PayableDetail> apply(SupplierPayment t, List<PayableDetail> pl) throws MessageException {
		t.setNoticeStatus(2);
		t.setProductStatus(2);
		t.setCreateDate(new Date());
		t.setDataType(1);
		t.setSettlementNo(jiesuanCode("RCV"));
		t.setStatus(1);
		t.setTemporaryHas(1);
		t.setExchangeNos("");
		t.setId((Long) this.supplierPaymentDao.save(t));
		for (PayableDetail payableDetail : pl) {
			Payable p = this.payableDao.get(Payable.class, payableDetail.getPablid());
			p.setPayAmount(p.getPayAmount().add(payableDetail.getPayAmount()));
			p.setBalance(p.getBalance().subtract(payableDetail.getPayAmount()));
			p.setRevocationHas(2);
			this.payableDao.update(p);
			if(p.getType()==3){
				payableDetail.setStatusZanzhi(1);
			}else{
				payableDetail.setStatusZanzhi(0);
			}
			payableDetail.setSupmid(t.getId());
			payableDetail.setId((Long) this.payableDetailDao.save(payableDetail));
			if (t.getNoticeStatus() == 2 && p.getNoticeStatus() == 1) {
				t.setNoticeStatus(1);
			}
			if (t.getProductStatus() == 2 && p.getProductStatus() == 1) {
				t.setProductStatus(1);
			}
			if (p.getType() == 3) {
				t.setTemporaryHas(2);
			}
			t.setExchangeNos(t.getExchangeNos() + "," + p.getExchangeNo());
		}
		t.setExchangeNos(t.getExchangeNos().substring(1));
		this.supplierPaymentDao.update(t);
		return pl;
	}
	/**
	 * 查询归属人
	 */
	public List<Payable> findAllList(User user) {
		String hql = "from Payable p1 where not exists (select 1 from Payable p2 where p2.affiliationNo=p1.affiliationNo and p2.id<p1.id) ";
		String str = dataPermissionService.findAllList(user);
		if(str!=null&&!str.equals("")){
			hql += "and p1.affiliationNo in ("+str+")";
		}else{
			hql +="and p1.affiliationNo = 111111111111111111";
		}
		return payableDao.find(hql);
	}
	/**
	 * 查询供应商编号
	 */
	public List<Payable> findAllSupplierNo(String code) {
		String hql = "from Payable p1 where not exists (select 1 from Payable p2 where p2.supplierNo=p1.supplierNo and p2.id<p1.id)";
		Map<String, Object> params = new HashMap<String, Object>(0);
		if(code!=null&&!code.equals("")) {
			hql+=" and p1.supplierNo like :code";
			params.put("code", "%"+code+"%");
		}
		return payableDao.find(hql,params);
	}
	
	/**
	 * 调用接口，添加单据
	 */
	public void save(Payable p){
		String hql="from Payable where exchangeNo = '"+p.getExchangeNo()+"'";
		List<Payable> li = payableDao.find(hql);
		if(li.size()==0){
			if(p.getType()==2){
				p.setYajinSort(10);
			}else{
				p.setYajinSort(1);
			}
			payableDao.save(p);
		}
	}
	
	/**
	 * 订单中心作废单据，需要调用接口，改变申请作废状态
	 */
	public void UpdateCancelStatus(String exchangeNo){
		String hql="update Payable set cancelStatus = 2 where exchangeNo = '" + exchangeNo + "'";
		payableDao.executeHql(hql);
	}

	/**
	 * 同意作废接口
	 */
	public Payable tongyi(String id) {
		Payable p=payableDao.get(Payable.class, Long.parseLong(id));
		p.setCancelStatus(1);
		String hql="update Payable set cancelStatus = 1 where id = "+id;
		payableDao.executeHql(hql);
		Payable pf = new Payable();
		BeanUtils.copyProperties(p, pf, new String[] { "id" });
		pf.setExchangeNo("*"+p.getExchangeNo());
		pf.setPayBeAmount(pf.getPayBeAmount().negate());
		pf.setPayAmount(BigDecimal.ZERO);
		pf.setBalance(pf.getPayBeAmount());
		pf.setCreateDate(new Date());
		pf.setId((Long)this.payableDao.save(pf));
		return pf;
	}
	
	/**
	 * 不同意作废接口
	 */
	public void butongyi(String id){
		String hql="update Payable set cancelStatus = 1 where id = "+id;
		payableDao.executeHql(hql);
	}
	
	/**
	 * 新建
	 */
	public Payable add(Payable p) {
		p.setId((Long) this.payableDao.save(p));
		return p;
	}
	
	/**
	 * 编码规则
	 * 13位数：AP+8位年月日+“-”+2位顺序号
	 * Eg：AP20140721-01
	 */
	public String addCode(){
		String ar="AR";
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		int month = cal.get(Calendar.MONTH) + 1;
		String year = cal.get(Calendar.YEAR)+"";
		String y = year+month+day;
		String xuhao="";
		String hql="select count(*) from Payable where exchangeNo like  '%"+y+"%'";
		Long count = payableDao.count(hql);
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
		return ar+y+"-"+xuhao;
	};
	
	
	/**
	 * 修改通知单状态接口
	 * @param noticeNo
	 */
	public void UpdateNoticeStatus(String exchangeNo){
		String hql="update Payable set noticeStatus = 2 where exchangeNo = '"+exchangeNo+"'";
		payableDao.executeHql(hql);
	}
	
	/**
	 * 修改产品单状态接口
	 * @param noticeNo
	 */
	public void UpdateProductStatus(String exchangeNo){
		String hql="update Payable set productStatus = 2 where exchangeNo = '"+exchangeNo+"'";
		payableDao.executeHql(hql);
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
	
	public Payable findPayable(String pablid){
		String hql="from Payable where id ="+pablid;
		return payableDao.get(hql);
	}
	
	/**
	 * 修改交换单的异常状态的异常状态
	 */
	public void UpdateAbnormal(String exchangeNo,String abnormalStatus){
		String hql="update Payable set abnormalStatus = "+abnormalStatus+" where exchangeNo = '"+exchangeNo+"'";
		payableDao.executeHql(hql);
	}
	
	/**
	 * 订单中心新增作废
	 */
	public Payable updateWaitPaymentCS(String exchangeNo) {
		String hql1="from Payable where exchangeNo = '"+exchangeNo+"'";
		Payable p=payableDao.get(hql1);
		p.setCancelStatus(1);
//		String hql="update Payable set cancelStatus = 1 where id = "+id;
//		payableDao.executeHql(hql);
		Payable pf = new Payable();
		BeanUtils.copyProperties(p, pf, new String[] { "id" });
		pf.setExchangeNo("*"+p.getExchangeNo());
		pf.setPayBeAmount(pf.getPayBeAmount().negate());
		pf.setPayAmount(BigDecimal.ZERO);
		pf.setBalance(pf.getPayBeAmount());
		pf.setCreateDate(new Date());
		pf.setId((Long)this.payableDao.save(pf));
		return pf;
	}
	
	public List<AccountsPayable> accountsPayable(PayablePRMT p,String xianshi,String xianshi1,String xianshi2){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<AccountsPayable> aplist=new ArrayList<AccountsPayable>();
		List<AccountsPayable> aplist1=new ArrayList<AccountsPayable>();
		String hql="from Payable t where t.balance!=0 ";
		Map<String, Object> args = new HashMap<String, Object>();
		if (p.getSupplierNo() != null && !p.getSupplierNo().equals("")) {
			hql += "and t.supplierNo in ("+p.getSupplierNo()+")";
		}
		if (p.getTradeDateQi() != null) {
			hql += "and t.tradeDate >= :tradeDateQi ";
			args.put("tradeDateQi", p.getTradeDateQi());
		}
		if (p.getTradeDateShi() != null) {
			hql += "and t.tradeDate <= :tradeDateShi ";
			args.put("tradeDateShi", p.getTradeDateShi());
		}
		if(p.getGroupNumber()!=null&&!p.getGroupNumber().equals("")){
			hql += "and t.groupNumber = :groupNumber ";
			args.put("groupNumber", p.getGroupNumber());
		}
		if(p.getCreaterNo()!=null&&!p.getCreaterNo().equals("")){
			hql += "and t.createrNo = :createrNo ";
			args.put("createrNo", p.getCreaterNo());
		}
		if(xianshi!=null&&xianshi.equals("0")){//不包含0的数据
			hql += "and t.balance!=0 ";
		}
		if(xianshi1!=null&&xianshi1.equals("0")){//不包含手动填加的数据
			hql += "and t.affiliationNo<>0 ";
		}
		if(xianshi2!=null&&xianshi2.equals("0")){//不包含押金
			hql += "and t.type not in (2,6)";
		}
		hql+=" order by supplierNo";
		List<Payable> pli =  payableDao.find(hql, args);
		List<Payable> pli1 =  payableDao.find(hql, args);
		for(Payable pb:pli){
			if(pb.getExchangeNo().indexOf("*")!=-1){
				pli1.add(pb);
				continue;
			}
			
			AccountsPayable ap=new AccountsPayable();
			ap.setSupplierNoAndName(pb.getSupplierNo()+"-"+pb.getSupplierName());
			if(!pb.getCurrencyType().equals("CNY")){
				ap.setBalance(Double.parseDouble(settlementExchangeRateService.findCurrency(pb.getBalance(), pb.getCurrencyType()).toString()));
				ap.setPayAmount(settlementExchangeRateService.findCurrency(pb.getPayAmount(),pb.getCurrencyType()).toString());
				ap.setPayBeAmount(settlementExchangeRateService.findCurrency(pb.getPayBeAmount(),pb.getCurrencyType()).toString());
			}else{
				ap.setBalance(Double.parseDouble(pb.getBalance().toString()));
				ap.setPayAmount(pb.getPayAmount().toString());
				ap.setPayBeAmount(pb.getPayBeAmount().toString());
			}
			ap.setConfirmationNumber(pb.getProductNo());
			if(pb.getDeadline()!=null){
				ap.setDeadline(sdf.format(pb.getDeadline()));
			}
			ap.setExchangeNo(pb.getExchangeNo());
			ap.setIssuer(pb.getAffiliationPerson());
			ap.setOrderNo(pb.getOrderNo());
			ap.setTradeDate(sdf.format(pb.getTradeDate()));
			aplist.add(ap);
		}
		
		for(AccountsPayable ap:aplist){
			boolean fale=true;
			for(Payable r1:pli1){
				if(ap.getExchangeNo().equals(r1.getExchangeNo().substring(1))){
					fale=false;
				}
			}
			if(fale){
				aplist1.add(ap);
			}
		}
		
		return aplist1;
	}
	
	//订单中心修改确认号
	public void updateQrh(String exchangeNo,String productNo){
		String hql1="from Payable where exchangeNo = '"+ exchangeNo+"'";
		Payable p=payableDao.get(hql1);
		if(p.getProductNo()==null||p.getProductNo().equals("")){
			String hql="update Payable set productNo = '"+productNo+"' where exchangeNo = '"+ exchangeNo+"'";
			payableDao.executeHql(hql);
			UpdateProductStatus(exchangeNo);
		}
	}
	
	public Payable findAllexNo(String exchangeNo){
		String hql="from Payable t where t.exchangeNo ='"+exchangeNo+"'";
		return payableDao.get(hql);
	}
}
