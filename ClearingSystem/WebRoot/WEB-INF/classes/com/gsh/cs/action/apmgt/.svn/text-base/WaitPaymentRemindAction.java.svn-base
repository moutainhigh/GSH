package com.gsh.cs.action.apmgt;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.parameter.PayablePRMT;
import com.gsh.cs.service.apmgt.WaitPaymentRemindSearchI;
import com.opensymphony.xwork2.ModelDriven;
@Namespace("/apmgt")
@Action(value = "/waitPaymentRemindAction")
public class WaitPaymentRemindAction extends BaseAction implements ModelDriven<PayablePRMT> {
	private PayablePRMT p=new PayablePRMT();
	public PayablePRMT getModel() {
		return p;
	}
	@Resource WaitPaymentRemindSearchI waitPaymentRemindSearch;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session=request.getSession();
	public void findAll(){
		try {
			User user=(User)session.getAttribute("userSession");
			super.writeJson(waitPaymentRemindSearch.findAll(p,user));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void findAllSearch(){
		try {
			User user=(User)session.getAttribute("userSession");
			if(this.waitPaymentRemindSearch.findAll(p,user).getRows().size()>0){
				this.result.setSuccess(true);
				this.result.setObj(this.waitPaymentRemindSearch.findAll(p,user));
			}else{
				this.result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}
}
