package com.gsh.cs.action.Interface;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.base.Initialization;
import com.gsh.cs.model.Interface.BaseJsonResponse;
import com.gsh.cs.model.parameter.BaspTicketUsePRMT;
import com.gsh.cs.model.parameter.CustomerPaymentPRMT;
import com.gsh.cs.model.parameter.PayablePRMT;
import com.gsh.cs.model.parameter.ReceivablePRMT;
import com.gsh.cs.model.parameter.SupplierPaymentPRMT;
import com.gsh.cs.service.apmgt.WaitPaymentServiceI;
import com.gsh.cs.service.armgt.CustomerPaymentServiceI;
import com.gsh.cs.service.armgt.ReceivableServiceI;
import com.gsh.cs.service.armgt.ReceivableViewServiceI;
import com.gsh.cs.service.bsp.BaspTicketUseServiceI;

@Namespace("/receivableInterface")
@Action(value = "receivableInterfaceAction")
public class ReceivableInterfaceAction extends BaseAction {
	private ReceivableServiceI receivableService;
	private BaspTicketUseServiceI baspTicketUseService;
	private CustomerPaymentServiceI customerPaymentService;
	@Autowired
	public void setCustomerPaymentService(CustomerPaymentServiceI customerPaymentService) {
		this.customerPaymentService = customerPaymentService;
	}
	@Autowired
	public void setReceivableService(ReceivableServiceI receivableService) {
		this.receivableService = receivableService;
	}
	@Autowired
	public void setBaspTicketUseService(BaspTicketUseServiceI baspTicketUseService) {
		this.baspTicketUseService = baspTicketUseService;
	}
	@Autowired WaitPaymentServiceI waitPaymentService;
	@Resource
	ReceivableViewServiceI receivableViewService;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	//报表应收账款借口
	public void accountsReceivable(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			String cid=request.getParameter("cid");
			String customerNo=request.getParameter("customerNo");
			String tradeDateQi=request.getParameter("createDateQi");
			String tradeDateShi=request.getParameter("createDateShi");
			String tradeDateEnd=request.getParameter("createDateEnd");
			String xianshi=request.getParameter("xianshi");
			String xianshi1=request.getParameter("xianshi1");
			String xianshi2=request.getParameter("xianshi2");
			String groupNumber=request.getParameter("groupNumber");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ReceivablePRMT p=new ReceivablePRMT();
			p.setCustomerNo(customerNo);
			p.setCreaterNo(Long.parseLong(cid));
			if(tradeDateQi!=null&&!tradeDateQi.equals("")){
				Date date=sdf.parse(tradeDateQi);
				p.setTradeDateQi(date);
			}
			if(tradeDateShi!=null&&!tradeDateShi.equals("")){
				Date date1=sdf.parse(tradeDateShi);
				p.setTradeDateShi(date1);
			}
			if(tradeDateEnd!=null&&!tradeDateEnd.equals("")){
				Date date2=sdf.parse(tradeDateEnd);
				p.setTradeDateShi(date2);
			}
			p.setGroupNumber(groupNumber);
			bjr.setFlag(1);//成功
			bjr.setCode("");
			bjr.setInfo(JSON.toJSONString(receivableService.accountsReceivable(p,xianshi,xianshi1,xianshi2)));
			this.writeJson(bjr);
		} catch (Exception e) {
			bjr.setFlag(0);//失败
			bjr.setCode("");
			bjr.setInfo("");
			this.writeJson(bjr);
			e.printStackTrace();
		}
	}
	
	//报表客户对账单接口
	public void customersOntheBill(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			String customerNo=request.getParameter("customerNo");
			String tradeDateQi=request.getParameter("createDateQi");
			String tradeDateShi=request.getParameter("createDateShi");
			String tradeDateEnd=request.getParameter("createDateEnd");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ReceivablePRMT p=new ReceivablePRMT();
			p.setCustomerNo(customerNo);
			if(tradeDateQi!=null&&!tradeDateQi.equals("")){
				Date date=sdf.parse(tradeDateQi);
				p.setTradeDateQi(date);
			}
			if(tradeDateShi!=null&&!tradeDateShi.equals("")){
				Date date1=sdf.parse(tradeDateShi);
				p.setTradeDateShi(date1);
			}
			if(tradeDateEnd!=null&&!tradeDateEnd.equals("")){
				Date date2=sdf.parse(tradeDateEnd);
				p.setTradeDateShi(date2);
			}
			receivableService.customersOntheBill(p);
			bjr.setFlag(1);//成功
			bjr.setCode("");
			bjr.setInfo(JSON.toJSONString(receivableService.customersOntheBill(p)));
			this.writeJson(bjr);
		} catch (Exception e) {
			bjr.setFlag(0);//失败
			bjr.setCode("");
			bjr.setInfo("");
			this.writeJson(bjr);
			e.printStackTrace();
		}
	}
	
	//业务员欠款报表
	public void salesmanArrearsReport(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			String dept=request.getParameter("dept");
			String affiliationNo=request.getParameter("affiliationNo");
			String tradeDateQi=request.getParameter("tradeDateQi");
			String tradeDateShi=request.getParameter("tradeDateShi");
			String accountType=request.getParameter("accountType");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ReceivablePRMT p=new ReceivablePRMT();
			p.setDepts(dept);
			
			if(tradeDateQi!=null&&!tradeDateQi.equals("")){
				Date date=sdf.parse(tradeDateQi);
				p.setTradeDateQi(date);
			}
			if(tradeDateShi!=null&&!tradeDateShi.equals("")){
				Date date1=sdf.parse(tradeDateShi);
				p.setTradeDateShi(date1);
			}
			bjr.setFlag(1);//成功
			bjr.setCode("");
			bjr.setInfo(JSON.toJSONString(receivableService.salesmanArrearsReport(p, accountType,affiliationNo)));
			this.writeJson(bjr);
		} catch (Exception e) {
			bjr.setFlag(0);//失败
			bjr.setCode("");
			bjr.setInfo("");
			this.writeJson(bjr);
			e.printStackTrace();
		}
	}
	
	public void ticketsReport(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			String cid=request.getParameter("cid");
			String type=request.getParameter("type");//国内、国际
			String drawerDayQi=request.getParameter("drawerDayQi");
			String drawerDayShi=request.getParameter("drawerDayShi");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			BaspTicketUsePRMT p=new BaspTicketUsePRMT();
			p.setType(type);
			p.setCreaterNo(cid);
			if(drawerDayQi!=null&&!drawerDayQi.equals("")){
				Date date=sdf.parse(drawerDayQi);
				p.setDrawerDayQi(date);
			}
			if(drawerDayShi!=null&&!drawerDayShi.equals("")){
				Date date1=sdf.parse(drawerDayShi);
				p.setDrawerDayShi(date1);
			}
			bjr.setFlag(1);//成功
			bjr.setCode("");
			bjr.setInfo(JSON.toJSONString(baspTicketUseService.ticketsReport(p)));
			this.writeJson(bjr);
		} catch (Exception e) {
			bjr.setFlag(0);//失败
			bjr.setCode("");
			bjr.setInfo("");
			this.writeJson(bjr);
			e.printStackTrace();
		}
	}
	
	public void accountsPayable(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			String cid=request.getParameter("cid");
			String supplierNo=request.getParameter("supplierNo");
			String tradeDateQi=request.getParameter("tradeDateQi");
			String tradeDateShi=request.getParameter("tradeDateShi");
			String tradeDateEnd=request.getParameter("tradeDateEnd");
			String groupNumber=request.getParameter("groupNumber");
			String xianshi=request.getParameter("xianshi");
			String xianshi1=request.getParameter("xianshi1");
			String xianshi2=request.getParameter("xianshi2");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			PayablePRMT p=new PayablePRMT();
			p.setCreaterNo(Long.parseLong(cid));
			p.setSupplierNo(supplierNo);
			if(tradeDateQi!=null&&!tradeDateQi.equals("")){
				Date date=sdf.parse(tradeDateQi);
				p.setTradeDateQi(date);
			}
			if(tradeDateShi!=null&&!tradeDateShi.equals("")){
				Date date1=sdf.parse(tradeDateShi);
				p.setTradeDateShi(date1);
			}
			if(tradeDateEnd!=null&&!tradeDateEnd.equals("")){
				Date date2=sdf.parse(tradeDateEnd);
				p.setTradeDateShi(date2);
			}
			p.setGroupNumber(groupNumber);
			bjr.setFlag(1);//成功
			bjr.setCode("");
			bjr.setInfo(JSON.toJSONString(waitPaymentService.accountsPayable(p,xianshi,xianshi1,xianshi2)));
			this.writeJson(bjr);
		} catch (Exception e) {
			bjr.setFlag(0);//失败
			bjr.setCode("");
			bjr.setInfo("");
			this.writeJson(bjr);
			e.printStackTrace();
		}
	}
	
	public void guanZhangDate(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			bjr.setFlag(1);//成功
			bjr.setCode("");
			bjr.setInfo(Initialization.SETTLEMENT_DAY);
			this.writeJson(bjr);
		} catch (Exception e) {
			bjr.setFlag(0);//失败
			bjr.setCode("");
			bjr.setInfo("");
			this.writeJson(bjr);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 登出接口
	 */
	public void loginOut(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		BaseJsonResponse bjr=new BaseJsonResponse();
		try {
			session.invalidate();
			bjr.setFlag(1);
			bjr.setCode("");
			bjr.setInfo("");
		} catch (Exception e) {
			bjr.setFlag(0);//失败
			bjr.setCode("");
			bjr.setInfo("");
			e.printStackTrace();
		}
		
		this.writeJson(bjr);
	}
	
	/**
	 * 应收账款销账摘要报表
	 */
	public void findYsZhangkuan(){
		BaseJsonResponse bjr=new BaseJsonResponse();
		try {
			String uid=request.getParameter("uid");
			String customerId=request.getParameter("customerId");
			String dept=request.getParameter("dept");
			String createDateQi=request.getParameter("createDateQi");
			String createDateShi=request.getParameter("createDateShi");
			String fundAccount=request.getParameter("fundAccount");//资金账户  银行 
			String paymentMethod=request.getParameter("paymentMethod");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			CustomerPaymentPRMT p=new CustomerPaymentPRMT();
			p.setHandNo(Long.parseLong(uid));
			p.setCustomerNo(customerId);
//			p.set
			if(createDateQi!=null&&!createDateQi.equals("")){
				Date date=sdf.parse(createDateQi);
				p.setIncomeDateQi(date);
			}
			if(createDateShi!=null&&!createDateShi.equals("")){
				Date date1=sdf.parse(createDateShi);
				p.setIncomeDateShi(date1);
			}
			bjr.setFlag(1);//成功
			bjr.setCode("");
			bjr.setInfo(JSON.toJSONString(customerPaymentService.findYsZhangkuan()));
			this.writeJson(bjr);
		} catch (Exception e) {
			bjr.setFlag(0);//失败
			bjr.setCode("");
			bjr.setInfo("");
			this.writeJson(bjr);
			e.printStackTrace();
		}
		
	}
	public void PassengerReport(){
		BaseJsonResponse bjr=new BaseJsonResponse();
		try {
			String uid=request.getParameter("uid");
			String customerId=request.getParameter("customerId");
			String dept=request.getParameter("dept");
			String createDateQi=request.getParameter("SupplierDateQi");
			String createDateShi=request.getParameter("SupplierDateShi");
			String fundAccount=request.getParameter("fundAccount");//资金账户  银行 
			String paymentMethod=request.getParameter("paymentMethod");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SupplierPaymentPRMT p=new SupplierPaymentPRMT();
			
			p.setHandNo(Long.parseLong(uid));
			p.setSupplierNo(customerId);
//			p.set
			if(createDateQi!=null&&!createDateQi.equals("")){
				Date date=sdf.parse(createDateQi);
				p.setSupplierDateQi(date);
			}
			if(createDateShi!=null&&!createDateShi.equals("")){
				Date date1=sdf.parse(createDateShi);
				p.setSupplierDateShi(date1);
			}
			bjr.setFlag(1);//成功
			bjr.setCode("");
			bjr.setInfo(JSON.toJSONString(customerPaymentService.PassengerReport()));
			this.writeJson(bjr);
		} catch (Exception e) {
			bjr.setFlag(0);//失败
			bjr.setCode("");
			bjr.setInfo("");
			this.writeJson(bjr);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 销账明细报表
	 */
	public void findYsZhangkuanMingxi(){
		BaseJsonResponse bjr=new BaseJsonResponse();
		try {
			String cid=request.getParameter("cid");
			String customerId=request.getParameter("customerId");
			String dept=request.getParameter("dept");
			String createDateQi=request.getParameter("createDateQi");
			String createDateShi=request.getParameter("createDateShi");
			String fundAccount=request.getParameter("fundAccount");//资金账户  银行 
			String paymentMethod=request.getParameter("paymentMethod");
			bjr.setFlag(1);//成功
			bjr.setCode("");
			bjr.setInfo(JSON.toJSONString(receivableViewService.findReceivableCustmerPaymentView()));
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
