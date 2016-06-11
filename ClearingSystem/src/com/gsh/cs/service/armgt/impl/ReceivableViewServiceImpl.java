package com.gsh.cs.service.armgt.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
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

import com.gsh.cs.dao.BaseDaoI;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.Interface.ReceivableCustmerPaymentView;
import com.gsh.cs.model.base.AccountDetail;
import com.gsh.cs.model.base.CurrencyUnit;
import com.gsh.cs.model.base.CustomerPayment;
import com.gsh.cs.model.base.Receivable;
import com.gsh.cs.model.base.ReceivableView;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.ReceivableViewPRMT;
import com.gsh.cs.service.armgt.ReceivableViewServiceI;
import com.gsh.cs.service.bsp.DataPermissionServiceI;

@Transactional
@Service("receivableViewService")
public class ReceivableViewServiceImpl implements ReceivableViewServiceI {
	private BaseDaoI<ReceivableView> receivableViewDao;
	private BaseDaoI<Receivable> receivableDao;
	private BaseDaoI<AccountDetail> accountDetailDao;
	private	BaseDaoI<CustomerPayment> customerPaymentDao;
	@Resource DataPermissionServiceI dataPermissionService;
	public BaseDaoI<ReceivableView> getReceivableViewDao() {
		return receivableViewDao;
	}

	@Autowired
	public void setReceivableViewDao(BaseDaoI<ReceivableView> receivableViewDao) {
		this.receivableViewDao = receivableViewDao;
	}

	public BaseDaoI<Receivable> getReceivableDao() {
		return receivableDao;
	}

	@Autowired
	public void setReceivableDao(BaseDaoI<Receivable> receivableDao) {
		this.receivableDao = receivableDao;
	}

	public BaseDaoI<AccountDetail> getAccountDetailDao() {
		return accountDetailDao;
	}
	
	@Autowired
	public void setAccountDetailDao(BaseDaoI<AccountDetail> accountDetailDao) {
		this.accountDetailDao = accountDetailDao;
	}
	
	@Autowired
	public void setCustomerPaymentDao(BaseDaoI<CustomerPayment> customerPaymentDao) {
		this.customerPaymentDao = customerPaymentDao;
	}

	public DataPermissionServiceI getDataPermissionService() {
		return dataPermissionService;
	}

	public void setDataPermissionService(
			DataPermissionServiceI dataPermissionService) {
		this.dataPermissionService = dataPermissionService;
	}

	public Datagrid findAll(ReceivableViewPRMT p,User user) {
		Datagrid dg = new Datagrid();
		String hql = "from ReceivableView p where p.status = 6 ", countHql = "select count(*) ", footerHql = "select sum(p.incomeAmount),sum(p.id) ";
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
		if (p.getNoticeNo() != null && !p.getNoticeNo().equals("")) {
			hql += "and p.noticeNo = :noticeNo ";
			args.put("noticeNo", p.getNoticeNo());
		}
		if (p.getCustomerNo() != null && !p.getCustomerNo().equals("")) {
			hql += "and p.customerNo = :customerNo ";
			args.put("customerNo", p.getCustomerNo());
		}
		if (p.getOrderNo() != null && !p.getOrderNo().equals("")) {
			hql += "and p.orderNo = :orderNo ";
			args.put("orderNo", p.getOrderNo());
		}
//		if (p.getAffiliationNo() != null && !p.getAffiliationNo().equals("")) {
//			hql += "and p.affiliationNo = :affiliationNo ";
//			args.put("affiliationNo", p.getAffiliationNo());
//		}
		if (p.getVoucherNo() != null && !p.getVoucherNo().equals("")) {
			hql += "and p.voucherNo = :voucherNo ";
			args.put("voucherNo", p.getVoucherNo());
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
		countHql += hql;
		footerHql += hql;
		List<ReceivableView> li1 = this.receivableViewDao.find(hql, args, p.getPage(), p.getRows());
		List<ReceivableViewPRMT> rvpli=new ArrayList<ReceivableViewPRMT>();
		for(ReceivableView rv:li1){
			ReceivableViewPRMT rvp=new ReceivableViewPRMT();
			BeanUtils.copyProperties(rv, rvp);
			String hql1="from AccountDetail where settlementNo = '"+rv.getSettlementNo()+"'";
			rvp.setStatus1(accountDetailDao.get(hql1).getStatus());
			rvpli.add(rvp);
		}
		dg.setRows(rvpli);
		dg.setTotal(this.receivableViewDao.count(countHql, args));
		List<ReceivableView> rli=this.receivableViewDao.find(hql, args, p.getPage(), p.getRows());
		List li=new ArrayList();
		BigDecimal incomeAmount=new BigDecimal(0);
		for(ReceivableView r:rli){
			incomeAmount=incomeAmount.add(r.getIncomeAmount());
		}
		li.add(incomeAmount);
		dg.getFooter().add(formatterSum("小计：", (Object[])li.toArray()));
		dg.getFooter().add(formatterSum("总计：", (Object[]) this.receivableViewDao.sum(footerHql, args).get(0)));
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
		foolter.put("orderNo", title);
		foolter.put("incomeAmount", (BigDecimal) sum[0]);
		return foolter;
	}

	public List<Receivable> findAllList(User user) {
		String hql = "from Receivable r1 where not exists (select 1 from Receivable r2 where r2.affiliationNo=r1.affiliationNo and r2.id<r1.id)";
		String str = dataPermissionService.findAllList(user);
		if(str!=null&&!str.equals("")){
			hql += "and r1.affiliationNo in ("+str+")";
		}else{
			hql +="and r1.affiliationNo = 111111111111111111";
		}
		return receivableDao.find(hql);
	}
	
	public List<Receivable> findAllCustomerNo(String code) {
		String hql = "from Receivable r1 where not exists (select 1 from Receivable r2 where r2.customerNo=r1.customerNo and r2.id<r1.id)";
		Map<String, Object> params = new HashMap<String, Object>(0);
		if(code!=null&&!code.equals("")) {
			hql+=" and r1.customerNo like :code";
			params.put("code", "%"+code+"%");
		}
		return receivableDao.find(hql,params);
	}

	public List<ReceivableView> list(ReceivableViewPRMT p) {
		String hql = "";
		Map<String, Object> args = new HashMap<String, Object>();
		if (p.getRcvbid() != null) {
			hql += "and p.rcvbid >= :rcvbid ";
			args.put("rcvbid", p.getRcvbid());
		}
		if (p.getCtpmid() != null) {
			hql += "and p.ctpmid >= :ctpmid ";
			args.put("ctpmid", p.getCtpmid());
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
		if (p.getNoticeNo() != null && !p.getNoticeNo().equals("")) {
			hql += "and p.noticeNo = :noticeNo ";
			args.put("noticeNo", p.getNoticeNo());
		}
		if (p.getCustomerNo() != null && !p.getCustomerNo().equals("")) {
			hql += "and p.customerNo = :customerNo ";
			args.put("customerNo", p.getCustomerNo());
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

		if (!hql.equals(""))
			hql = " where " + hql.substring(3);
		hql = "from ReceivableView p " + hql;
		return this.receivableViewDao.find(hql, args);
	}
	
	public com.gsh.cs.model.Interface.ReceivableView findId(Long id){
		com.gsh.cs.model.Interface.ReceivableView rvin=new com.gsh.cs.model.Interface.ReceivableView();
		String hql="from ReceivableView where id = "+id;
		ReceivableView rv=receivableViewDao.get(hql);
		BeanUtils.copyProperties(rv, rvin);
//		rvin.setNoticeNo(rv.getNoticeNo());
//		rvin.setCustomerNo(rv.getCustomerNo());
//		rvin.setCustomerName(rv.getCustomerName());
//		rvin.setCreateDate(rv.getCreateDate());
//		rvin.setOrderNo(rv.getOrderNo());
//		rvin.setType(rv.getType());
//		rvin.setDeadline(rv.getDeadline());
//		rvin.setRemark(rv.getRemark());
//		rvin.setPassengerName(rv.getPassengerName());
//		rvin.setAffiliationPerson(rv.getAffiliationPerson());
//		rvin.setTradeDate(rv.getTradeDate());
//		rvin.setAffiliationNo(rv.getAffiliationNo());
//		rvin.setDeadlineHas(rv.getDeadlineHas());
//		rvin.setOriginalPaymentMethod(rv.getOriginalPaymentMethod());
//		rvin.setCreater(rv.getCreater());
//		rvin.setCreaterNo(rv.getCreaterNo());
//		rvin.setRevocationHas(rv.getRevocationHas());
//		rvin.setDepts(rv.getDepts());
//		rvin.setReverseHas(rv.getReverseHas());
////		rvin.setGroupNumber(rv.getgroupNumber);
////		rvin.setCancelStatus(rv.getcancelStatus);
////		rvin.setYajinSort(rv.getyajinSort);
//		rvin.setCtpmid(rv.getCtpmid());
//		rvin.setRcvbid(rv.getRcvbid());
//		rvin.setIncomeAmount(rv.getIncomeAmount());
		return rvin;
	}
	
	public List<ReceivableCustmerPaymentView> findReceivableCustmerPaymentView(){
		String hql = "from ReceivableView p where p.status = 6 ";
		List<ReceivableView> rvlist = receivableViewDao.find(hql);
		List<ReceivableCustmerPaymentView> rcpvlist=new ArrayList<ReceivableCustmerPaymentView>();
		for(ReceivableView rv:rvlist){
			ReceivableCustmerPaymentView rcpv=new ReceivableCustmerPaymentView();
			BeanUtils.copyProperties(rv, rcpv);
			String hql1="from CustomerPayment where id = "+rv.getCtpmid();
			CustomerPayment cp=customerPaymentDao.get(hql1);
			rcpv.setHandNo(cp.getHandNo());// 经手人编号
			rcpv.setApplyNo(cp.getApplyNo());// 申请人编号
			rcpv.setPublication(cp.getPublication());// 客户信用卡手续费扣率%
			rcpv.setSettlement(cp.getSettlement());// 结算信用卡手续费扣率%
			rcpv.setDataType(cp.getDataType());// 数据类型:1收入2退款
			rcpv.setApplyPerson(cp.getApplyPerson());// 申请人
			rcpv.setCustomerNo(cp.getCustomerNo());// 客户编号
			rcpv.setCustomerName(cp.getCustomerName());// 付款人/收款人
			rcpv.setBankAccountNo(cp.getBankAccountNo());// 账号
			rcpv.setCardCode(cp.getCardCode());// 信用卡代码
			rcpv.setCardExpirationDate(cp.getCardExpirationDate());// 信用卡有效期：格式 13/08 日/月
			rcpv.setIncomeAmount(cp.getIncomeAmount());// 实收金额
			rcpv.setFactorage(cp.getFactorage());// 手续费
			rcpv.setCustomerFactorage(cp.getCustomerFactorage());// 客户手续费
			rcpv.setIncomeDate(cp.getIncomeDate());// 收款/付款日期
			rcpv.setFundAccount(cp.getFundAccount());// 资金账户
			rcpv.setNetAmount(cp.getNetAmount());// 入账金额
			rcpv.setBankRate(cp.getBankRate());// 银行汇率 结算汇率
			rcpv.setExchangeProfitLoss(cp.getExchangeProfitLoss());// 汇兑损益
			rcpv.setTradeNo(cp.getTradeNo());// 交易号
			rcpv.setArriveDate(cp.getArriveDate());// 到账日期
			rcpv.setSprName(cp.getSprName());
			rcpv.setSpDate(cp.getSpDate());
			rcpvlist.add(rcpv);
		}
		return rcpvlist;
	}
}
