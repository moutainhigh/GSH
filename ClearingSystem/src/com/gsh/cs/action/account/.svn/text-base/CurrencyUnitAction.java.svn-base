package com.gsh.cs.action.account;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.service.account.CurrencyUnitServiceI;

@Namespace("/aa")
@Action(value = "bb")
public class CurrencyUnitAction extends BaseAction {
	@Resource CurrencyUnitServiceI currencyUnitService;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	public void findAll(){
		try {
			String cu="";
			super.writeJson(currencyUnitService.findAll(cu));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void find(){
		try {
			String cu=request.getParameter("cu");
			super.result.setObj(currencyUnitService.findAll(cu));
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}
}
