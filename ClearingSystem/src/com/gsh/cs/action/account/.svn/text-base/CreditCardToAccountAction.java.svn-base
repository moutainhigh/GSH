package com.gsh.cs.action.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.base.MessageException;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.CustomerPayment;
import com.gsh.cs.model.parameter.CustomerPaymentPRMT;
import com.gsh.cs.service.armgt.CustomerPaymentServiceI;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/account")
@Action(value = "ccta")
public class CreditCardToAccountAction extends BaseAction implements ModelDriven<CustomerPaymentPRMT> {

	private CustomerPaymentPRMT param = new CustomerPaymentPRMT();

	public CustomerPaymentPRMT getModel() {
		return param;
	}

	private CustomerPaymentServiceI customerPaymentService;

	@Autowired
	public void setCustomerPaymentService(CustomerPaymentServiceI customerPaymentService) {
		this.customerPaymentService = customerPaymentService;
	}
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session=request.getSession();
	public void datagrid() {
		User user=(User)session.getAttribute("userSession");
		param.setStatusCompare("=");
		param.setStatus(5);
		super.writeJson(this.customerPaymentService.find(param,user));
	}
	
	public void findAllSearch(){
		try {
			User user=(User)session.getAttribute("userSession");
			param.setStatusCompare("=");
			param.setStatus(5);
			if (this.customerPaymentService.find(param,user).getRows().size() > 0) {
				super.result.setSuccess(true);
				super.result.setObj(this.customerPaymentService.find(param,user));
			} else {
				super.result.setSuccess(false);
				super.result.setMsg("未查询到任何记录");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}

	/**
	 * 确认到账
	 */
	public void sure() {
		try {
			User user=(User)session.getAttribute("userSession");
			this.customerPaymentService.sure(param,user);
			super.result.setSuccess(true);
			super.result.setMsg("确认成功！");
		} catch (MessageException e) {
			super.result.setMsg(e.getMessage());
		} catch (Exception e) {
			super.result.setMsg(e.getMessage());
		}
		super.writeJson();
	}

}
