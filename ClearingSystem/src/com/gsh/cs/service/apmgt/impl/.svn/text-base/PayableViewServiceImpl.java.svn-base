package com.gsh.cs.service.apmgt.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsh.cs.dao.BaseDaoI;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.Payable;
import com.gsh.cs.model.base.PayableDetail;
import com.gsh.cs.model.base.PayableView;
import com.gsh.cs.model.base.SupplierPayment;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.PayableViewPRMT;
import com.gsh.cs.service.apmgt.PayableViewServiceI;
import com.gsh.cs.service.bset.SettlementExchangeRateServiceI;
import com.gsh.cs.service.bsp.DataPermissionServiceI;

@Transactional
@Service("payableViewService")
public class PayableViewServiceImpl implements PayableViewServiceI {
	private BaseDaoI<PayableView> payableViewDao;
	private BaseDaoI<PayableDetail> payableDetailDao;
	private BaseDaoI<Payable> payableDao;
	@Resource SettlementExchangeRateServiceI settlementExchangeRateService;
	@Resource DataPermissionServiceI dataPermissionService;
	public BaseDaoI<PayableView> getPayableViewDao() {
		return payableViewDao;
	}

	@Autowired
	public void setPayableViewDao(BaseDaoI<PayableView> payableViewDao) {
		this.payableViewDao = payableViewDao;
	}

	public BaseDaoI<PayableDetail> getPayableDetailDao() {
		return payableDetailDao;
	}
	@Autowired
	public void setPayableDetailDao(BaseDaoI<PayableDetail> payableDetailDao) {
		this.payableDetailDao = payableDetailDao;
	}

	public BaseDaoI<Payable> getPayableDao() {
		return payableDao;
	}
	@Autowired
	public void setPayableDao(BaseDaoI<Payable> payableDao) {
		this.payableDao = payableDao;
	}

	public Datagrid findAll(PayableViewPRMT p,User user) {
		Datagrid dg = new Datagrid();
		String footerHql = "select sum(p.payAmount) ,p.currencyType from PayableView p ";
		String footerHql2_center="select * from payable_view p ";
		String hql = " where p.status in (5,6) ", countHql = "select count(*) ";
//		String hql = "from PayableView p where p.status in (5,6) ", countHql = "select count(*) ";
		Map<String, Object> args = new HashMap<String, Object>();
		if (p.getTradeDateQi() != null) {
			hql += "and p.tradeDate >= :tradeDateQi ";
			args.put("tradeDateQi", p.getTradeDateQi());
		}
		if (p.getTradeDateShi() != null) {
			hql += "and p.tradeDate <= :tradeDateShi ";
			args.put("tradeDateShi", p.getTradeDateShi());
		}
		if (p.getDeadlineQi() != null) {
			hql += "and p.deadline >= :deadlineQi ";
			args.put("deadlineQi", p.getDeadlineQi());
		}
		if (p.getDeadlineShi() != null) {
			hql += "and p.deadline <= :deadlineShi ";
			args.put("deadlineShi", p.getDeadlineShi());
		}
		if (p.getSupplierNo() != null && !p.getSupplierNo().equals("")) {
			hql += "and p.supplierNo = :supplierNo ";
			args.put("supplierNo", p.getSupplierNo());
		}
		if (p.getExchangeNo() != null && !p.getExchangeNo().equals("")) {
			hql += "and p.exchangeNo = :exchangeNo ";
			args.put("exchangeNo", p.getExchangeNo());
		}
		if (p.getOrderNo() != null && !p.getOrderNo().equals("")) {
			hql += "and p.orderNo = :orderNo ";
			args.put("orderNo", p.getOrderNo());
		}
		if (p.getAffiliationNo() != null && !p.getAffiliationNo().equals("")) {
			hql += "and p.affiliationNo = :affiliationNo ";
			args.put("affiliationNo", p.getAffiliationNo());
		}
		if (p.getVoucherNo() != null && !p.getVoucherNo().equals("")) {
			hql += "and p.voucherNo = :voucherNo ";
			args.put("voucherNo", p.getVoucherNo());
		}
		if (p.getProductNo() != null && !p.getProductNo().equals("")) {
			hql += "and p.productNo = :productNo ";
			args.put("productNo", p.getProductNo());
		}
		if (p.getCounterFee() != null && p.getCounterFee() != 0) {
			if (p.getCounterFee() == 1) {
				// 查询包含有手续费的条目
				hql += " and p.settlementNo in (select settlementNo from AccountDetail where subjects = '手续费')";
			} else if (p.getCounterFee() == 2) {
				// 查询不包含手续的条目
				hql += " and p.settlementNo in (select settlementNo from AccountDetail where subjects != '手续费')";
			}
		}
		String str = dataPermissionService.findAllList(user);
		if(str!=null&&!str.equals("")){
			if (p.getAffiliationNo() != null && !p.getAffiliationNo().equals("")) {
				hql += "and p.affiliationNo = :affiliationNo ";
				args.put("affiliationNo", p.getAffiliationNo());
			}else{
				hql += "and p.affiliationNo in ("+str+")";
			}
		}else{
			hql +="and p.createrNo = "+user.getCid();
		}
		footerHql2_center += hql;
		//小计
		String footerHql2="select sum(p.payAmount),p.currencyType from ("+footerHql2_center+" limit "+((p.getPage()-1)*p.getRows())+","+p.getRows()+") p ";
		footerHql += hql+" group by p.currencyType";
		footerHql2 += hql+" group by p.currencyType";
		hql="from PayableView p "+hql;
		countHql += hql;
		List<PayableView> li = this.payableViewDao.find(hql, args, p.getPage(), p.getRows());
		List<PayableView> li1 = this.payableViewDao.find(hql, args);
		dg.setRows(this.payableViewDao.find(hql, args, p.getPage(), p.getRows()));
		dg.setTotal(this.payableViewDao.count(countHql, args));
		dg.getFooter().add(formatterSum("小计：", sumCurrencySum(li)));
		dg.getFooter().add(formatterSum("总计：", sumCurrencySum(li1)));
//		setFoolter(dg, this.payableViewDao.sum(footerHql, args), this.payableViewDao.sum2(footerHql2, args));
		return dg;
	}
	
	public Object[] sumCurrencySum(List<PayableView> li){
		Object[] paySum=new Object[6];//小计
		BigDecimal pay1=new BigDecimal(0);
		for (PayableView pab : li) {
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
		foolter.put("exchangeNo", title);
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
	private void setFoolter(Datagrid dg, List<Object[]> zj, List<Object[]> xj) {
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

	public List<PayableView> list(PayableViewPRMT p) {
		String hql = "";
		Map<String, Object> args = new HashMap<String, Object>();
		if (p.getSupmid() != null) {
			hql += "and p.supmid = :supmid ";
			args.put("supmid", p.getSupmid());
		}
		if (p.getTradeDateQi() != null) {
			hql += "and p.tradeDate >= :tradeDateQi ";
			args.put("tradeDateQi", p.getTradeDateQi());
		}
		if (p.getTradeDateShi() != null) {
			hql += "and p.tradeDate <= :tradeDateShi ";
			args.put("tradeDateShi", p.getTradeDateShi());
		}
		if (p.getDeadlineQi() != null) {
			hql += "and p.deadline >= :deadlineQi ";
			args.put("deadlineQi", p.getDeadlineQi());
		}
		if (p.getDeadlineShi() != null) {
			hql += "and p.deadline <= :deadlineShi ";
			args.put("deadlineShi", p.getDeadlineShi());
		}
		if (p.getSupplierNo() != null && !p.getSupplierNo().equals("")) {
			hql += "and p.supplierNo = :supplierNo ";
			args.put("supplierNo", p.getSupplierNo());
		}
		if (p.getExchangeNo() != null && !p.getExchangeNo().equals("")) {
			hql += "and p.exchangeNo = :exchangeNo ";
			args.put("exchangeNo", p.getExchangeNo());
		}
		if (p.getOrderNo() != null && !p.getOrderNo().equals("")) {
			hql += "and p.orderNo = :orderNo ";
			args.put("orderNo", p.getOrderNo());
		}
		if (p.getAffiliationNo() != null && !p.getAffiliationNo().equals("")) {
			hql += "and p.affiliationNo = :affiliationNo ";
			args.put("affiliationNo", p.getAffiliationNo());
		}
		if (p.getVoucherNo() != null && !p.getVoucherNo().equals("")) {
			hql += "and p.voucherNo = :voucherNo ";
			args.put("voucherNo", p.getVoucherNo());
		}
		if (p.getProductNo() != null && !p.getProductNo().equals("")) {
			hql += "and p.productNo = :productNo ";
			args.put("productNo", p.getProductNo());
		}
		if (!hql.equals(""))
			hql = " where " + hql.substring(3);
		hql = "from PayableView p " + hql;
		List<PayableView> li=this.payableViewDao.find(hql, args);
		return li;
	}
	
	/**
	 * 查询暂支款
	 */
	public Datagrid findZhanzhi(PayableViewPRMT p,User user,String status) {
		Datagrid dg = new Datagrid();
		String hql="";
		String countHql="";
		if(status.equals("1")){
			hql = " where p.status = 5 and p.temporaryHas = 1 ";
			countHql = "select count(*) ";
		}else if(status.equals("2")){
			hql = " where p.type = 3 and p.status = 5 and p.temporaryHas = 2 and p.statusZanzhi = 1 ";
			countHql = "select count(*) ";
		}
		Map<String, Object> args = new HashMap<String, Object>();
		if (p.getTradeDateQi() != null) {
			hql += "and p.tradeDate >= :tradeDateQi ";
			args.put("tradeDateQi", p.getTradeDateQi());
		}
		if (p.getTradeDateShi() != null) {
			hql += "and p.tradeDate <= :tradeDateShi ";
			args.put("tradeDateShi", p.getTradeDateShi());
		}
		if (p.getSupplierNo() != null && !p.getSupplierNo().equals("")) {
			hql += "and p.supplierNo = :supplierNo ";
			args.put("supplierNo", p.getSupplierNo());
		}
		if (p.getExchangeNo() != null && !p.getExchangeNo().equals("")) {
			hql += "and p.exchangeNo = :exchangeNo ";
			args.put("exchangeNo", p.getExchangeNo());
		}
		if (p.getProductNo() != null && !p.getProductNo().equals("")) {
			hql += "and p.productNo = :productNo ";
			args.put("productNo", p.getProductNo());
		}
		String str = dataPermissionService.findAllList(user);
		if(str!=null&&!str.equals("")){
			if (p.getAffiliationNo() != null && !p.getAffiliationNo().equals("")) {
				hql += "and p.affiliationNo = :affiliationNo ";
				args.put("affiliationNo", p.getAffiliationNo());
			}else{
				hql += "and p.affiliationNo in ("+str+")";
			}
		}else{
			hql +="and p.affiliationNo = 111111111111111111";
		}
		hql="from PayableView p "+hql;
		countHql += hql;
		dg.setRows(this.payableViewDao.find(hql, args, p.getPage(), p.getRows()));
		dg.setTotal(this.payableViewDao.count(countHql, args));
		List<PayableView> li = this.payableViewDao.find(hql, args, p.getPage(), p.getRows());
		List<PayableView> li1 = this.payableViewDao.find(hql, args);
		dg.getFooter().add(formatterSum("小计：", sumCurrencySum(li)));
		dg.getFooter().add(formatterSum("总计：", sumCurrencySum(li1)));
		return dg;
	}

	public com.gsh.cs.model.Interface.PayableView findId(Long id) {
		String hql="from PayableView p where p.id = "+id;
		PayableView pv = payableViewDao.get(hql);
		com.gsh.cs.model.Interface.PayableView pvin=new com.gsh.cs.model.Interface.PayableView();
		BeanUtils.copyProperties(pv, pvin);
		return pvin;
	}
	
	public List<Payable> findSupmid(String supmid){
		List<Payable> pli=new ArrayList<Payable>();
		String hql="from PayableDetail where supmid = "+supmid;
		List<PayableDetail> pdli = payableDetailDao.find(hql);
		for(PayableDetail pd:pdli){
			String hql1="from Payable where id="+pd.getPablid();
			Payable p = payableDao.get(hql1);
			pli.add(p);
		}
		return pli;
	}

}
