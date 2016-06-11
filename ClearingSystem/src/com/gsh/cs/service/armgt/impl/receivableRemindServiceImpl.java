package com.gsh.cs.service.armgt.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsh.cs.dao.BaseDaoI;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.Receivable;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.ReceivablePRMT;
import com.gsh.cs.service.armgt.receivableRemindServiceI;
import com.gsh.cs.service.bsp.DataPermissionServiceI;

@Service("receivableRemindService")
public class receivableRemindServiceImpl implements receivableRemindServiceI {
	private BaseDaoI<Receivable> receivabledao;
	@Resource DataPermissionServiceI dataPermissionService;
	public BaseDaoI<Receivable> getReceivabledao() {
		return receivabledao;
	}
	@Autowired
	public void setReceivabledao(BaseDaoI<Receivable> receivabledao) {
		this.receivabledao = receivabledao;
	}

	public Datagrid findAll(ReceivablePRMT param,User user) {
		Datagrid dg = new Datagrid();
		Date date = new Date();
		date.setDate(date.getDate() + 3);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(date);
		List<Receivable> rbli=new ArrayList<Receivable>();
		String hql="from Receivable r1 where (r1.deadline <= '"+str+"') and r1.balance!=0 ",countHql = "select count(*) ";
		Map<String, Object> args = new HashMap<String, Object>();
		String str1 = dataPermissionService.findAllList(user);
		if(str1!=null&&!str1.equals("")){
			if (param.getAffiliationNo() != null && !param.getAffiliationNo().equals("")) {
				hql += "and r1.affiliationNo = :affiliationNo ";
				args.put("affiliationNo", param.getAffiliationNo());
			}else{
				hql += "and r1.affiliationNo in ("+str1+")";
			}
		}else{
			hql +="and r1.createrNo = "+user.getCid();
		}
		hql+=" group by r1.customerNo,r1.deadline";
//		String hql = "from Receivable r1 where not exists (select 1 from Receivable r2 where r2.customerNo=r1.customerNo and r2.deadline=r1.deadline and r2.id<r1.id) and (r1.deadline <= '"+str+"') and r1.balance!=0 ", countHql = "select count(*) ";
//		hql+="order by r1.deadline desc";
		List<Receivable> li  =receivabledao.find(hql,args, param.getPage(), param.getRows());
		for(Receivable rb:li){
			Receivable receivable=new Receivable();
			String hql1="from Receivable where customerNo = '"+rb.getCustomerNo()+"' and deadline = '"+rb.getDeadline()+"'";
			List<Receivable> li1  =receivabledao.find(hql1);
 			BigDecimal yingshou=new BigDecimal(0);
			BigDecimal yishou=new BigDecimal(0);
			BigDecimal yue=new BigDecimal(0);
			for(Receivable r:li1){
				yingshou=yingshou.add(r.getIncomeAmount());
				yishou=yishou.add(r.getIncomeBeAmount());
				yue=yue.add(r.getBalance());
			}
			receivable.setIncomeAmount(yingshou);
			receivable.setIncomeBeAmount(yishou);
			receivable.setBalance(yue);
			receivable.setCustomerNo(rb.getCustomerNo());
			receivable.setDeadline(rb.getDeadline());
			rbli.add(receivable);
		}
		countHql+=hql;
		dg.setRows(rbli);
//		dg.setTotal(this.receivabledao.count(countHql, args));
		dg.setTotal((long)rbli.size());
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
		foolter.put("customerNo", title);
		foolter.put("incomeBeAmount", (BigDecimal) sum[0]);
		foolter.put("incomeAmount", (BigDecimal) sum[1]);
		foolter.put("balance", (BigDecimal) sum[2]);
		return foolter;
	}

	@Test
	public void getDate() {
		Date date = new Date();
		date.setDate(date.getDate() + 3);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(date);
		System.out.println(str);
	}
	

}
