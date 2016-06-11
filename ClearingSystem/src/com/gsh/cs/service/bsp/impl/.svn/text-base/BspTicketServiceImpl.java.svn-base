package com.gsh.cs.service.bsp.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsh.cs.dao.BaseDaoI;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.BaspTicketUse;
import com.gsh.cs.model.base.BspTicket;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.BspTicketPRMT;
import com.gsh.cs.service.bsp.BspTicketServiceI;
import com.gsh.cs.tools.LongTool;
import com.gsh.cs.tools.Utils;

@Transactional
@Service("bspTicketService")
public class BspTicketServiceImpl implements BspTicketServiceI {
	private BaseDaoI<BspTicket> bspTicketDao;
	private BaseDaoI<BaspTicketUse> baspTicketUseDao;
	
	public BaseDaoI<BspTicket> getBspadministrationDao() {
		return bspTicketDao;
	}

	@Autowired
	public void setBspadministrationDao(BaseDaoI<BspTicket> bspTicketDao) {
		this.bspTicketDao = bspTicketDao;
	}
	
	
	public BaseDaoI<BaspTicketUse> getBaspTicketUseDao() {
		return baspTicketUseDao;
	}
	@Autowired
	public void setBaspTicketUseDao(BaseDaoI<BaspTicketUse> baspTicketUseDao) {
		this.baspTicketUseDao = baspTicketUseDao;
	}

	/**
	 * 查询
	 * 
	 * @param P
	 * @return
	 */
	public Datagrid findAll(BspTicketPRMT P,User user) {
		Datagrid dg = new Datagrid();
		String hql = "from BspTicket b where 1=1 ", countHql = "select count(*) ";
		Map<String, Object> params = new HashMap<String, Object>(0);
		if (P.getBspType() != null) {
			hql += "and b.bspType = :bspType ";
			params.put("bspType", P.getBspType());
		}
		hql+=" and b.userId = "+user.getCid();
		countHql += hql;
		dg.setRows(this.bspTicketDao.find(hql, params, P.getPage(), P.getRows()));
		dg.setTotal(this.bspTicketDao.count(countHql, params));
		return dg;
	}

	/**
	 * 新建
	 * 
	 * @param p
	 * @return
	 */
	public BspTicket save(BspTicketPRMT p) {
		try {
			BspTicket b = new BspTicket();
			BeanUtils.copyProperties(p, b);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			b.setBspCreationDate(LongTool.stringTosqlDate(df.format(new Date())));
			b.setDeptId("1");
			b.setId((Long) this.bspTicketDao.save(b));
			return b;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String quPiao(String type,String piaohao,String cid) {
		int num=1;
		List<BspTicket> bt = new ArrayList<BspTicket>(0);
		 if(piaohao.indexOf(",")!=-1){
			  String [] str = piaohao.split(",");
			  for(String s:str){
				  String hql = "from BspTicket where bspType = "+type+" and bspStartBank <= "+s+" and bspTerminationBank >= "+s+" and bspAllowance>0 and userId = "+cid;
				  bt = bspTicketDao.find(hql);
				  if(Utils.isNullOrEmpty(bt)){
					  return "";
				  }else{//减少该票号的数量，需要提前判断该票号是否已存在，如果已存在，取票失败
					  String hql2="from BaspTicketUse where ticketNo = "+s+" and type = '"+type+"' and createrNo = "+cid;
					  BaspTicketUse btuli = baspTicketUseDao.get(hql2);
					  if(btuli!=null){
						  return "";
					  }
				  }
			  }
			  bt.get(0).setBspAllowance(bt.get(0).getBspAllowance()-str.length);
			  bspTicketDao.update(bt.get(0));
		  }else{
			  String hql = "from BspTicket where bspType = "+type+" and bspStartBank <= "+piaohao+" and bspTerminationBank >= "+piaohao+" and bspAllowance>0 and userId = "+cid;
			  bt = bspTicketDao.find(hql);
			  if(Utils.isNullOrEmpty(bt)){
				  return "";
			  }else{//减少该票号的数量
				  String hql2="from BaspTicketUse where ticketNo = "+piaohao+" and type = '"+type+"' and createrNo = "+cid;
				  BaspTicketUse btuli = baspTicketUseDao.get(hql2);
				  if(btuli!=null){
					  return "";
				  }
			  }
			  bt.get(0).setBspAllowance(bt.get(0).getBspAllowance()-1);
			  bspTicketDao.update(bt.get(0));
		  }
		 if(!Utils.isNullOrEmpty(bt)){
				return "true";
		  }else{
			  return "";
		  }
	}

	/**
	 * 检索票号的唯一性
	 */
	public boolean tickedWeiyi(String piaohao,User user){
		String hql ="from BspTicket where bspStartBank<="+piaohao+" and bspTerminationBank>="+piaohao+" and userId = "+user.getCid();
		BspTicket bt = bspTicketDao.get(hql);
		if(bt!=null){
			return false;
		}
		return true;
	}
}
