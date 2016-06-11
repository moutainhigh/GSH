package com.gsh.cs.service.apmgt.impl;

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
import com.gsh.cs.model.base.Payable;
import com.gsh.cs.model.base.PayableDetail;
import com.gsh.cs.model.base.PayableView;
import com.gsh.cs.model.base.SupplierPayment;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.PayablePRMT;
import com.gsh.cs.model.parameter.PayableViewPRMT;
import com.gsh.cs.model.parameter.SupplierPaymentPRMT;
import com.gsh.cs.service.apmgt.ApplyPaymentServiceI;
import com.gsh.cs.service.bset.SettlementExchangeRateServiceI;
import com.gsh.cs.service.bsp.DataPermissionServiceI;
import com.gsh.cs.tools.StringTool;

@Service("applyPaymentService")
public class ApplyPaymentServiceImpl implements ApplyPaymentServiceI {

	private BaseDaoI<Payable> payableDao;

	private BaseDaoI<SupplierPayment> supplierPaymentDao;

	private BaseDaoI<PayableDetail> payableDetailDao;

	private BaseDaoI<Account> accountDao;

	private BaseDaoI<AccountDetail> accountDetailDao;
	@Resource DataPermissionServiceI dataPermissionService;
	@Resource SettlementExchangeRateServiceI settlementExchangeRateService;
	@Autowired
	public void setSupplierPaymentDao(BaseDaoI<SupplierPayment> supplierPaymentDao) {
		this.supplierPaymentDao = supplierPaymentDao;
	}

	@Autowired
	public void setPayableDao(BaseDaoI<Payable> payableDao) {
		this.payableDao = payableDao;
	}

	@Autowired
	public void setPayableDetailDao(BaseDaoI<PayableDetail> payableDetailDao) {
		this.payableDetailDao = payableDetailDao;
	}

	@Autowired
	public void setAccountDao(BaseDaoI<Account> accountDao) {
		this.accountDao = accountDao;
	}

	@Autowired
	public void setAccountDetailDao(BaseDaoI<AccountDetail> accountDetailDao) {
		this.accountDetailDao = accountDetailDao;
	}

	public Datagrid find(SupplierPaymentPRMT p,User user) {
		Datagrid dg = new Datagrid();
		String footerHql = "select sum(t.payAmount) ,t.currencyType from SupplierPayment t ";
		String footerHql2_center="select * from supplier_payment t ";
		String hql = " where t.status < 5 ", countHql = "select count(*) ";
//		String hql = "from SupplierPayment t where t.status < 5 ", countHql = "select count(*) ", footerHql = "select sum(t.payAmount) ,t.currencyType ";
		Map<String, Object> args = new HashMap<String, Object>();
		if(p.getSettlementNo()!=null&&!p.getSettlementNo().equals("")){
			hql += "and t.settlementNo =:settlementNo ";
			args.put("settlementNo", p.getSettlementNo());
		}
		if(p.getSupplierNo()!=null&&!p.getSupplierNo().equals("")){
			hql += "and t.supplierNo =:supplierNo ";
			args.put("supplierNo", p.getSupplierNo());
		}
		hql += "and t.handNo ="+user.getUid();
		footerHql2_center += hql;
		//小计
		String footerHql2="select sum(t.payAmount) ,t.currencyType from ("+footerHql2_center+" limit "+((p.getPage()-1)*p.getRows())+","+p.getRows()+") t ";
		footerHql += hql+" group by t.currencyType";
		footerHql2 += hql+" group by t.currencyType";
		hql="from SupplierPayment t "+hql;
		countHql += hql;
		hql = setOrder(p, hql);
		List<SupplierPayment> li = this.supplierPaymentDao.find(hql, args, p.getPage(), p.getRows());
		List<SupplierPayment> li1 = this.supplierPaymentDao.find(hql, args);
		dg.setRows(this.supplierPaymentDao.find(hql, args, p.getPage(), p.getRows()));
		dg.setTotal(this.supplierPaymentDao.count(countHql, args));
//		setFoolter(dg, this.payableDao.sum(footerHql, args), this.payableDao.sum2(footerHql2, args));
		dg.getFooter().add(formatterSum("小计：", sumCurrencySum(li)));
		dg.getFooter().add(formatterSum("总计：", sumCurrencySum(li1)));
		return dg;
	}
	
	public Object[] sumCurrencySum(List<SupplierPayment> li){
		Object[] paySum=new Object[6];//小计
		BigDecimal pay1=new BigDecimal(0);
		for (SupplierPayment pab : li) {
				if(!pab.getCurrencyType().equals("CNY")){
					pay1 = pay1.add(new BigDecimal(settlementExchangeRateService.findCurrency(pab.getPayAmount(), pab.getCurrencyType())));
				}else{
					pay1 = pay1.add(pab.getPayAmount());
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
		foolter.put("settlementNo", title);
		foolter.put("payAmount", (BigDecimal) sum[0]);
		return foolter;
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
//			foolter.put("payAmount", (BigDecimal) sum[0]);
//			List l = new ArrayList();
//			l.add(foolter);
//			dg.getSumfooter().put(sum[1], l);
//		}
//		for (Object[] sum : xj) {
//			Map foolter = new HashMap();
//			foolter.put("payAmount", (BigDecimal) sum[0]);
//			((List) dg.getSumfooter().get(sum[1])).add(foolter);
//		}
//	}

	/**
	 * 排序条件 处理
	 * 
	 * @param SupplierPaymentPRMT
	 * @param hql
	 */
	public String setOrder(SupplierPaymentPRMT p, String hql) {
		if (!StringTool.isEmptyOrNull(p.getSort()) && null != p.getOrder()) {
			hql += " order by " + p.getSort() + " " + p.getOrder();
		}
		return hql;
	}

	@Transactional
	public void submit(Long id,String status) throws MessageException {
		SupplierPayment t = this.supplierPaymentDao.get(SupplierPayment.class, id);
		if (true) {// 提交申请 对接审批接口
			if(status.equals("1")){
				t.setStatus(3);
			}else if(status.equals("2")){
				t.setStatus(4);
			}
			String hql="update SupplierPayment set status = "+t.getStatus()+" where id="+id;
			supplierPaymentDao.executeHql(hql);
		} else {
			throw new MessageException("审批中心错误提示信息");
		}
	}
	
	@Transactional
	public void huidiaoSubmit(Long id,String name,String date) throws MessageException {
		SupplierPayment t = this.supplierPaymentDao.get(SupplierPayment.class, id);
		if (true) {// 提交申请 对接审批接口
			t.setStatus(4);
			String hql="update SupplierPayment set status = 4,sprName = '"+name+"',spDate = '"+date+"' where id="+id;
			supplierPaymentDao.executeHql(hql);
		} else {
			throw new MessageException("审批中心错误提示信息");
		}
	}
	
	public void bohuiSubmit(Long id,String name,String date) throws MessageException {
		SupplierPayment t = this.supplierPaymentDao.get(SupplierPayment.class, id);
		if (true) {// 提交申请 对接审批接口
			t.setStatus(2);
			String hql="update SupplierPayment set status = 2,sprName = '"+name+"',spDate = '"+date+"' where id="+id;
			supplierPaymentDao.executeHql(hql);
		} else {
			throw new MessageException("审批中心错误提示信息");
		}
	}

	@Transactional
	public void revoke(Long id) throws MessageException {
		SupplierPayment t = this.supplierPaymentDao.get(SupplierPayment.class, id);
		t.setStatus(9);
		this.supplierPaymentDao.update(t);
		String hql = "from PayableDetail t where t.supmid = " + id;
		List<PayableDetail> pd_l = this.payableDetailDao.find(hql);
		for (PayableDetail pd : pd_l) {
			Payable pb = this.payableDao.get(Payable.class, pd.getPablid());
			pb.setPayAmount(pb.getPayAmount().subtract(pd.getPayAmount()));
			pb.setBalance(pb.getBalance().add(pd.getPayAmount()));
			this.payableDao.update(pb);
		}
	}

	@Transactional
	public List<PayableDetail> payment(SupplierPayment sp, List<PayableDetail> pl,User user) throws MessageException {
		// TODO Auto-generated method stub
		SupplierPayment t = this.supplierPaymentDao.get(SupplierPayment.class, sp.getId());
		t.setPaymentMethod(sp.getPaymentMethod());
		t.setTradeDate(sp.getTradeDate());
		t.setFundAccount(sp.getFundAccount());
		t.setBankRate(sp.getBankRate());
		t.setExchangeProfitLoss(sp.getExchangeProfitLoss());
		t.setVoucherNo(sp.getVoucherNo());
		t.setHandNo(sp.getHandNo());
		t.setHandPerson(sp.getHandPerson());
		t.setStatus(5);
		this.supplierPaymentDao.update(t);
		// 反向生成
		for (PayableDetail payableDetail : pl) {
			Payable p = this.payableDao.get(Payable.class, payableDetail.getPablid());
			if (p.getReverseHas() == 2) {
				p.setReverseHas(1);
				Payable pf = new Payable();
				BeanUtils.copyProperties(p, pf, new String[] { "id" });
				pf.setPayBeAmount(pf.getPayBeAmount().negate());
				pf.setPayAmount(BigDecimal.ZERO);
				pf.setBalance(pf.getPayBeAmount());
				pf.setExchangeNo("*"+p.getExchangeNo());
				pf.setCreateDate(new Date());
				this.payableDao.save(pf);
				this.payableDao.update(p);
			}
		}
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

	public void updateStatus(String status, String settlementNo) {
		String hql = "update SupplierPayment set status = " + status + " where settlementNo = '" + settlementNo + "'";
		supplierPaymentDao.executeHql(hql);
	}

	public void updatePayableDdetail(Integer pablid, Integer supmid) {
		String hql = "update PayableDetail set statusZanzhi = 2 where pablid = " + pablid + " and supmid = " + supmid;
		payableDetailDao.executeHql(hql);
	}

	public Integer findstatusZanzhi(Integer supmid) {
		String hql="from PayableDetail where supmid = "+supmid;
		List<PayableDetail> li=payableDetailDao.find(hql);
		for(PayableDetail p:li){
			if(p.getStatusZanzhi()==1){
				return 1;
			}
		}
		return 2;
	}
	
	
}
