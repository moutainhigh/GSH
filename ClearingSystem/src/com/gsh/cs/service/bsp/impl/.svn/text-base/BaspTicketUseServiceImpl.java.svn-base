package com.gsh.cs.service.bsp.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import antlr.Utils;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gsh.cs.dao.BaseDaoI;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.Interface.BaseJsonResponse;
import com.gsh.cs.model.Interface.TicketsShiyong;
import com.gsh.cs.model.Interface.ticketInfoP;
import com.gsh.cs.model.base.BaspTicketUse;
import com.gsh.cs.model.base.Receivable;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.BaspTicketUsePRMT;
import com.gsh.cs.service.bsp.BaspTicketUseServiceI;
import com.gsh.cs.tools.SoaPropertiesUtil;
import com.gsh.cs.tools.httpClient;

@Service("baspTicketUseService")
public class BaspTicketUseServiceImpl implements BaspTicketUseServiceI {
	private BaseDaoI<BaspTicketUse> baspTicketUseDao;

	public BaseDaoI<BaspTicketUse> getBaspTicketUseDao() {
		return baspTicketUseDao;
	}

	@Autowired
	public void setBaspTicketUseDao(BaseDaoI<BaspTicketUse> baspTicketUseDao) {
		this.baspTicketUseDao = baspTicketUseDao;
	}

	public Datagrid findGuoji(BaspTicketUsePRMT p, User user) {
		Datagrid dg = new Datagrid();
		Map<String, Object> args = new HashMap<String, Object>();
		String hql = "from BaspTicketUse b where b.type = 1 and b.createrNo = "
				+ user.getCid(), countHql = "select count(*) ", footerHql = "select sum(b.scny),sum(b.tax),sum(b.moneyC),sum(b.money) ";
		countHql += hql;
		footerHql += hql;
		dg.setRows(baspTicketUseDao.find(hql, p.getPage(), p.getRows()));
		dg.setTotal(baspTicketUseDao.count(countHql, args));

		List<BaspTicketUse> rli = this.baspTicketUseDao.find(hql, args,
				p.getPage(), p.getRows());
		List li = new ArrayList();
		BigDecimal scny = new BigDecimal(0);
		BigDecimal tax = new BigDecimal(0);
		BigDecimal moneyC = new BigDecimal(0);
		BigDecimal money = new BigDecimal(0);
		for (BaspTicketUse r : rli) {
			scny = scny.add(r.getScny());
			tax = tax.add(r.getTax());
			moneyC = moneyC.add(r.getMoneyC());
			money = money.add(r.getMoney());
		}
		li.add(scny);
		li.add(tax);
		li.add(moneyC);
		li.add(money);
		dg.getFooter().add(formatterSum("小计：", (Object[]) li.toArray()));
		dg.getFooter().add(
				formatterSum("总计：",
						(Object[]) this.baspTicketUseDao.sum(footerHql, args)
								.get(0)));

		return dg;
	}

	public Datagrid findGuonei(BaspTicketUsePRMT p, User user) {
		Datagrid dg = new Datagrid();
		Map<String, Object> args = new HashMap<String, Object>();
		String hql = "from BaspTicketUse b where b.createrNo = "
				+ user.getCid(), countHql = "select count(*) ", footerHql = "select sum(b.scny),sum(b.tax),sum(b.moneyC),sum(b.moneyZ),sum(b.money) ";
		if (p.getDrawerDay() != null) {
			hql += "and b.drawerDay = :drawerDay ";
			args.put("drawerDay", p.getDrawerDay());
		}

		if (p.getType() != null && !p.getType().equals("")) {
			hql += "and b.type = :type ";
			args.put("type", p.getType());
		}
		hql += "order by b.ticketNo";
		countHql += hql;
		footerHql += hql;
		dg.setRows(baspTicketUseDao.find(hql, args, p.getPage(), p.getRows()));
		dg.setTotal(baspTicketUseDao.count(countHql, args));
		List<BaspTicketUse> rli = this.baspTicketUseDao.find(hql, args,
				p.getPage(), p.getRows());
		List li = new ArrayList();
		BigDecimal scny = new BigDecimal(0);
		BigDecimal tax = new BigDecimal(0);
		BigDecimal moneyC = new BigDecimal(0);
		BigDecimal money = new BigDecimal(0);
		BigDecimal moneyZ = new BigDecimal(0);
		for (BaspTicketUse r : rli) {
			scny = scny.add(r.getScny());
			tax = tax.add(r.getTax());
			moneyC = moneyC.add(r.getMoneyC());
			moneyZ = moneyZ.add(r.getMoneyZ());
			money = money.add(r.getMoney());
		}
		li.add(scny);
		li.add(tax);
		li.add(moneyC);
		li.add(moneyZ);
		li.add(money);
		dg.getFooter().add(formatterSum1("小计：", (Object[]) li.toArray()));
		dg.getFooter().add(
				formatterSum1("总计：",
						(Object[]) this.baspTicketUseDao.sum(footerHql, args)
								.get(0)));

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
		foolter.put("ticketNo", title);
		foolter.put("scny", (BigDecimal) sum[0]);
		foolter.put("tax", (BigDecimal) sum[1]);
		foolter.put("moneyC", (BigDecimal) sum[2]);
		foolter.put("money", (BigDecimal) sum[3]);
		return foolter;
	}

	/**
	 * 统计数据处理
	 * 
	 * @param dg
	 * @param zj
	 * @param xj
	 */
	private Map formatterSum1(String title, Object[] sum) {
		Map foolter = new HashMap();
		foolter.put("ticketNo", title);
		foolter.put("scny", (BigDecimal) sum[0]);
		foolter.put("tax", (BigDecimal) sum[1]);
		foolter.put("moneyC", (BigDecimal) sum[2]);
		foolter.put("moneyZ", (BigDecimal) sum[3]);
		foolter.put("money", (BigDecimal) sum[4]);
		return foolter;
	}

	public void save(BaspTicketUse btu) {
		btu.setDrawerDay(new Date());
		if (btu.getMoneyZ() == null) {
			btu.setMoneyZ(new BigDecimal(0));
		}
		String hql = "from BaspTicketUse where ticketNo = " + btu.getTicketNo()
				+ " and createrNo = " + btu.getCreaterNo();
		List<BaspTicketUse> btuli = baspTicketUseDao.find(hql);
		if (btuli.size() == 0) {
			baspTicketUseDao.save(btu);
		}
	}

	public List<BaspTicketUse> findAll(String type, String createrNo) {
		String hql = "from BaspTicketUse where type = '" + type
				+ "' and createrNo = " + createrNo;
		return baspTicketUseDao.find(hql);
	}

	public BaspTicketUse updateBank(String airlineCompany, String ticketNo,
			String type, String typeTgq) {
		String hql = "from BaspTicketUse where airlineCompany = '"
				+ airlineCompany + "' and ticketNo = '" + ticketNo
				+ "' and type = '" + type + "'";
		BaspTicketUse btu = baspTicketUseDao.get(hql);
		String hql1 = "update BaspTicketUse set scny=0,tax=0,moneyC=0,moneyZ=0,money=0 where id = "
				+ btu.getId();
		baspTicketUseDao.executeHql(hql1);
		// btu.setScny(new BigDecimal(0.0));
		// btu.setTax(new BigDecimal(0.0));
		// btu.setMoneyC(new BigDecimal(0.0));
		// btu.setMoneyZ(new BigDecimal(0.0));
		// btu.setMoney(new BigDecimal(0.0));
		// btu.setTypeTgq(typeTgq);
		// baspTicketUseDao.update(btu);
		return btu;
	}

	public List<TicketsShiyong> ticketsReport(BaspTicketUsePRMT p) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
		BaseJsonResponse bjr = new BaseJsonResponse();
		List<TicketsShiyong> tsli = new ArrayList<TicketsShiyong>();
		Map<String, Object> args = new HashMap<String, Object>();
		String hql = "from BaspTicketUse t where 1=1 ";
		if (p.getDrawerDayQi() != null) {
			hql += "and t.drawerDay >= :drawerDayQi ";
			args.put("drawerDayQi", p.getDrawerDayQi());
		}
		if (p.getDrawerDayShi() != null) {
			hql += "and t.drawerDay <= :drawerDayShi ";
			args.put("drawerDayShi", p.getDrawerDayShi());
		}
		if (p.getType() != null && !p.getType().equals("")) {
			hql += "and t.type = :type ";
			args.put("type", p.getType());
		}
		if (p.getCreaterNo() != null && !p.getCreaterNo().equals("")) {
			hql += "and t.createrNo = '" + p.getCreaterNo().toString() + "'";
		}
		List<BaspTicketUse> btuli = baspTicketUseDao.find(hql, args);
		for (BaspTicketUse btu : btuli) {
			Map<String, String> params = new HashMap<String, String>(0);
			params.put("ticketNo",
					btu.getAirlineCompany() + "-" + btu.getTicketNo());
			String url = SoaPropertiesUtil.soaUrl.getProperty("getTicketInfo");
			String json = httpClient.getDoPostResponseDataByURL(url, params,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			ArrayList<ticketInfoP> tplist = new ArrayList<ticketInfoP>(0);
			if (bjr.getFlag() == 1) {
				tplist = (ArrayList<ticketInfoP>) JSON.parseArray(
						bjr.getInfo(), ticketInfoP.class);
				for (ticketInfoP tp : tplist) {
					TicketsShiyong ts = new TicketsShiyong();
					if ((tp.getIssuer() != null && tp.getIssuer() != "" && bjr
							.getFlag() == 1)) {
						ts.setIssuer(tp.getIssuer());
						ts.setNoticeNo(tp.getNoticeNo());
						ts.setRoute(tp.getRoute());
						ts.setSellingTotal(String.valueOf(tp.getSellingTotal()));
						ts.setExchangeSingle(tp.getExchangeSingle());
					}

					ts.setAirlineCompany(btu.getAirlineCompany());
					ts.setDrawerDay(sdf.format(btu.getDrawerDay()));
					ts.setMoney(btu.getMoney().toString());
					ts.setMoneyC(btu.getMoneyC().toString());
					if (btu.getMoneyZ() != null) {
						ts.setMoneyZ(btu.getMoneyZ().toString());
					} else {
						ts.setMoneyZ("0");
					}

					ts.setScny(btu.getScny().toString());
					ts.setTax(btu.getTax().toString());
					ts.setTicketNo(btu.getTicketNo().toString());
					if (btu.getType().equals("1")) {
						ts.setType("国际");
					} else if (btu.getType().equals("2")) {
						ts.setType("国内");
					}
					if (btu.getMoneyZM() != null) {
						ts.setZbaifenbi(btu.getMoneyZM().toString());
					}
					if (btu.getMoneyCM() != null) {
						ts.setCbaifenbi(btu.getMoneyCM().toString());
					}

					tsli.add(ts);
				}
			} else {
				TicketsShiyong ts = new TicketsShiyong();
				ts.setAirlineCompany(btu.getAirlineCompany());
				ts.setDrawerDay(sdf.format(btu.getDrawerDay()));
				ts.setMoney(btu.getMoney().toString());
				ts.setMoneyC(btu.getMoneyC().toString());
				if (btu.getMoneyZ() != null) {
					ts.setMoneyZ(btu.getMoneyZ().toString());
				} else {
					ts.setMoneyZ("0");
				}
				ts.setScny(btu.getScny().toString());
				ts.setTax(btu.getTax().toString());
				ts.setTicketNo(btu.getTicketNo().toString());
				if (btu.getType().equals("1")) {
					ts.setType("国际");
				} else if (btu.getType().equals("2")) {
					ts.setType("国内");
				}
				if (btu.getMoneyZM() != null) {
					ts.setZbaifenbi(btu.getMoneyZM().toString());
				}
				if (btu.getMoneyCM() != null) {
					ts.setCbaifenbi(btu.getMoneyCM().toString());
				}
				tsli.add(ts);
			}
		}
		return tsli;
	}
}
