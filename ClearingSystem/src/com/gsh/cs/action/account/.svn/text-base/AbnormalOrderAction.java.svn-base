package com.gsh.cs.action.account;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.alibaba.fastjson.JSON;
import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.Interface.BaseJsonResponse;
import com.gsh.cs.model.base.AbnormalOrder;
import com.gsh.cs.model.base.Commission;
import com.gsh.cs.model.parameter.AbnormalOrderPRMT;
import com.gsh.cs.service.account.AbnormalOrderServiceI;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.tools.internal.ws.processor.model.Request;
@Namespace("/account")
@Action(value = "abnormalOrderAction")
public class AbnormalOrderAction extends BaseAction implements ModelDriven<AbnormalOrderPRMT> {
	private AbnormalOrderPRMT p=new AbnormalOrderPRMT();
	public AbnormalOrderPRMT getModel() {
		return p;
	}
	@Resource AbnormalOrderServiceI abnormalOrderService;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	
	public void findAll(){
		User user=(User)request.getSession().getAttribute("userSession");
		super.writeJson(this.abnormalOrderService.findAll(p,user));
	}
	
	//订单中心推送数据
	public void abnormalOrderSave(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			String abnormalOrder ="["+request.getParameter("abnormalOrder")+"]";
			ArrayList<AbnormalOrder> aolist = (ArrayList<AbnormalOrder>) JSON
					.parseArray(abnormalOrder, AbnormalOrder.class);
			for(AbnormalOrder a:aolist){
				a.setCreater("唐彩红");
				abnormalOrderService.save(a);
			}
			bjr.setFlag(1);//成功
			bjr.setCode("");
			bjr.setInfo("");
			this.writeJson(bjr);
		} catch (Exception e) {
			bjr.setFlag(0);//失败
			bjr.setCode("");
			bjr.setInfo("");
			this.writeJson(bjr);
			e.printStackTrace();
		}
	}
	
	//订单中心，交换单号对应的单据是否全部生成过付款通知单，如果生成过了，那么结算删掉该条数据
	public void abnormalOrderDelete(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			String exchangeNo=request.getParameter("exchangeNo");
			abnormalOrderService.dalete(exchangeNo);
			bjr.setFlag(1);//成功
			bjr.setCode("");
			bjr.setInfo("");
			this.writeJson(bjr);
		} catch (Exception e) {
			bjr.setFlag(0);//失败
			bjr.setCode("");
			bjr.setInfo("");
			this.writeJson(bjr);
			e.printStackTrace();
		}
	}
	
}
