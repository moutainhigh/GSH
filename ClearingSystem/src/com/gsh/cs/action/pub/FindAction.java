package com.gsh.cs.action.pub;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.Account;
import com.gsh.cs.service.pub.FindServiceI;

@Namespace("/pub")
@Action(value = "find")
public class FindAction extends BaseAction {

	@Resource
	FindServiceI findService;

	public void findAccount() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User user=(User)request.getSession().getAttribute("userSession");
		String paymentMethod=request.getParameter("paymentMethod");
		List<Account> ali = this.findService.findAll(paymentMethod,user);
		if(ali==null||ali.size()>=0){
			if(paymentMethod!=null&&(paymentMethod.equals("1")||paymentMethod.equals("6"))){
				ali = this.findService.findAllType(paymentMethod,user);
			}
		}
		super.writeJson(ali);
	}

	public void findRate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		User user=(User)request.getSession().getAttribute("userSession");
		super.writeJsonString(this.findService.findRate(request.getParameter("code"),user).toString());
	}

}
