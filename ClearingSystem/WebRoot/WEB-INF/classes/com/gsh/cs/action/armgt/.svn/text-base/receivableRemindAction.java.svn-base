package com.gsh.cs.action.armgt;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;


import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.parameter.ReceivablePRMT;
import com.gsh.cs.service.armgt.receivableRemindServiceI;
import com.opensymphony.xwork2.ModelDriven;
@Namespace("/armgt")
@Action(value = "receivableRemind")
public class receivableRemindAction extends BaseAction implements ModelDriven<ReceivablePRMT> {
	private ReceivablePRMT param = new ReceivablePRMT();

	public ReceivablePRMT getModel() {
		return param;
	}
	
	@Resource receivableRemindServiceI receivableRemindService;

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session=request.getSession();
	public void findAll(){
		try {
			User user=(User)session.getAttribute("userSession");
			super.writeJson(receivableRemindService.findAll(param,user));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void findAllSearch(){
		try {
			User user=(User)session.getAttribute("userSession");
			if(this.receivableRemindService.findAll(param,user).getRows().size()>0){
				this.result.setSuccess(true);
				this.result.setObj(this.receivableRemindService.findAll(param,user));
			}else{
				this.result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}
}
