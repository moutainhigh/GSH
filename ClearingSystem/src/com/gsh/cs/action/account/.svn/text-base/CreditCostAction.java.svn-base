package com.gsh.cs.action.account;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.parameter.CreditCostPRMT;
import com.gsh.cs.service.account.CreditCostServiceI;
import com.opensymphony.xwork2.ModelDriven;
@Namespace("/account")
@Action(value = "creditCostAction")
public class CreditCostAction extends BaseAction implements ModelDriven<CreditCostPRMT> {
	private CreditCostPRMT cp=new CreditCostPRMT();
	public CreditCostPRMT getModel() {
		return cp;
	}
	@Resource CreditCostServiceI creditCostService;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	public void findAll(){
		try {
			String qiDate=request.getParameter("qiDate");
			String shiDate=request.getParameter("shiDate");
			User user=(User)request.getSession().getAttribute("userSession");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			if(qiDate!=null&&!qiDate.equals("")){
				cp.setQiDate(df.parse(qiDate));
			}
			if(shiDate!=null&&!shiDate.equals("")){
				cp.setShiDate(df.parse(shiDate));
			}
			if(cp.getPage()==0){
				cp.setPage(1);
				cp.setRows(10);
			}
			super.writeJson(creditCostService.findAll(cp,user));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
