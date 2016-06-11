package com.gsh.cs.action.armgt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.base.MessageException;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.log.appender.LogActionAppender;
import com.gsh.cs.model.Interface.BaseJsonResponse;
import com.gsh.cs.model.Interface.ReceivableView;
import com.gsh.cs.model.Interface.XiaoZhang;
import com.gsh.cs.model.base.CustomerPayment;
import com.gsh.cs.model.base.Receivable;
import com.gsh.cs.model.base.ReceivableDetail;
import com.gsh.cs.model.parameter.CustomerPaymentPRMT;
import com.gsh.cs.service.armgt.CustomerPaymentServiceI;
import com.gsh.cs.service.armgt.ReceivableServiceI;
import com.gsh.cs.service.armgt.ReceivableViewServiceI;
import com.gsh.cs.tools.SoaPropertiesUtil;
import com.gsh.cs.tools.httpClient;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.tools.internal.ws.processor.model.Request;

@Namespace("/armgt")
@Action(value = "ctpm")
public class CustomerPaymentAction extends BaseAction implements ModelDriven<CustomerPaymentPRMT> {

	private CustomerPaymentPRMT param = new CustomerPaymentPRMT();
	
	public CustomerPaymentPRMT getModel() {
		// TODO Auto-generated method stub
		return param;
	}

	private CustomerPaymentServiceI customerPaymentService;
	private ReceivableServiceI receivableService;
	@Autowired
	public void setReceivableService(ReceivableServiceI receivableService) {
		this.receivableService = receivableService;
	}
	@Autowired
	public void setCustomerPaymentService(CustomerPaymentServiceI customerPaymentService) {
		this.customerPaymentService = customerPaymentService;
	}
	@Resource
	ReceivableViewServiceI receivableViewService;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session=request.getSession();
	
	public void datagrid() {
		User user=(User)session.getAttribute("userSession");
		param.setStatusCompare("<");
		param.setStatus(5);
		super.writeJson(this.customerPaymentService.find(param,user));
	}

	/**
	 * 条件查询
	 */
	public void findAllSearch(){
		try {
			User user=(User)session.getAttribute("userSession");
			param.setStatusCompare("<");
			param.setStatus(5);
			if(customerPaymentService.find(param,user).getRows().size()>0){
				super.result.setSuccess(true);
				super.result.setObj(customerPaymentService.find(param,user));
			}else{
				super.result.setSuccess(false);
				super.result.setMsg("未查询到任何记录");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
		
	}
	
	/**
	 * 提交申请
	 */
	public void submit() {
		BaseJsonResponse bjr=new BaseJsonResponse();
		try {
			User user=(User)session.getAttribute("userSession");
			//这里要提交给审批系统东西（小许）,小徐那边没有任何异常，这里才能申请成功
			Map<String, String> params = new HashMap<String, String>(0);
			params.put("cid", user.getCid().toString());
			params.put("uid", user.getUid().toString());
			params.put("poid", param.getId().toString());
			params.put("poCode", param.getSettlementNo());
			params.put("eventCode", "tksq");
			params.put("profitJe", param.getIncomeAmount().toString());
			params.put("ticket", user.getTicket());
			String url=SoaPropertiesUtil.soaUrl.getProperty("startProcess");
			String json = httpClient.getDoPostResponseDataByURL(url, params,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			this.customerPaymentService.submit(param.getId(),bjr.getInfo());
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/armgt/ctpm!submit.action", 1, "应收账款付款申请提交",
					"结算编号：" + param.getSettlementNo(), 1, param.getId());// 记录日志
			super.result.setSuccess(true);
			super.result.setMsg("申请成功！");
			super.result.setObj(bjr.getInfo());
		} catch (MessageException e) {
			super.result.setMsg(e.getMessage());
		} catch (Exception e) {
			super.result.setMsg(e.getMessage());
		}
		super.writeJson();
	}
	
	/**
	 * 审核通过回调接口
	 */
	public void huidiaoSubmit(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			//审批通过，状态改变为代销账状态，这里应该是通过结算编号改变状态，到时候再跟小许商量，小许调用我的接口
			String poid=request.getParameter("poid");
			param.setId(Long.parseLong(poid));
			String name=request.getParameter("name");
			String date=request.getParameter("date");
			this.customerPaymentService.huidiaoSubmit(param.getId(),name,date);
			bjr.setFlag(1);//成功
			bjr.setCode("");
			bjr.setInfo("");
			this.writeJson(bjr);
		} catch (Exception e) {
			bjr.setFlag(0);//失败
			bjr.setCode("");
			bjr.setInfo("");
			this.writeJson(bjr);
			super.result.setMsg(e.getMessage());
		}
		super.writeJson();
	}
	
	/**
	 * 审核驳回回调接口
	 */
	public void boHuiSubmit(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			//审批驳回，状态改变为驳回，这里应该是通过结算编号改变状态，到时候再跟小许商量，小许调用我的接口
			String poid=request.getParameter("poid");
			param.setId(Long.parseLong(poid));
			String name=request.getParameter("name");
			String date=request.getParameter("date");
			this.customerPaymentService.boHuiSubmit(param.getId(),name,date);
			bjr.setFlag(1);//成功
			bjr.setCode("");
			bjr.setInfo("");
			this.writeJson(bjr);
		} catch (Exception e) {
			bjr.setFlag(0);//失败
			bjr.setCode("");
			bjr.setInfo("");
			this.writeJson(bjr);
			super.result.setMsg(e.getMessage());
		}
		super.writeJson();
	}

	/**
	 * 撤销
	 */
	public void revoke() {
		try {
			User user=(User)session.getAttribute("userSession");
			this.customerPaymentService.revoke(param.getId());
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/armgt/ctpm!submit.action", 1, "应收账款付款申请撤销",
					"结算编号：" + param.getSettlementNo(), 1, param.getId());// 记录日志
			super.result.setSuccess(true);
			super.result.setMsg("撤销成功！");
		} catch (MessageException e) {
			super.result.setMsg(e.getMessage());
		} catch (Exception e) {
			super.result.setMsg(e.getMessage());
		}
		super.writeJson();
	}

	/**
	 * 销账
	 */
	public void payment() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("userSession");
		try {
			CustomerPayment cp = JSON.parseObject(request.getParameter("form"), CustomerPayment.class);
			cp.setHandNo(user.getUid());
			cp.setHandPerson(user.getName());
			List<ReceivableDetail> rdl = JSON.parseArray(request.getParameter("detail"), ReceivableDetail.class);
			List<ReceivableDetail> rl = this.customerPaymentService.payment(cp, rdl,user);
			receivableViewOrder(rl);//销账推送数据到订单中心
			super.result.setSuccess(true);
			super.result.setMsg("销账成功！");
			
			//只要销账、订单中心就不可作废
			String rcvbid="";
			List<Receivable> li=new ArrayList<Receivable>();
			for (ReceivableDetail rd : rdl) {
				Receivable r = receivableService.findReceivable(rd.getRcvbid().toString());
				li.add(r);
				if(!rcvbid.equals("")){
					rcvbid+=",";
				}
				rcvbid+=rd.getRcvbid();
			}
			
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/armgt/receivable!income.action", 1, "应收账款销账收入",
					"应收账款主键ID：" + rcvbid, 1, 0L);// 记录日志
			Map<String, String> params = new HashMap<String, String>(0);
			for(Receivable rb:li){
				if(rb.getType()==1||rb.getType()==2||rb.getType()==3){
					XiaoZhang x=new XiaoZhang();
					x.setNoticeExchangeNo(rb.getNoticeNo());
					x.setSource("1");
					if(rb.getBalance().equals(0)){
						x.setStatus("1");
					}else{
						x.setStatus("0");
					}
					x.setType(rb.getType().toString());
					params.put("thewriteoff", JSON.toJSONString(x));
					String url=SoaPropertiesUtil.soaUrl.getProperty("UpdateOrderStatusXiaozhang");
					String json = httpClient.getDoPostResponseDataByURL(url, params,
							"utf-8", true);
				}
			}
			
		} catch (MessageException e) {
			super.result.setMsg(e.getMessage());
		} catch (Exception e) {
			super.result.setMsg(e.getMessage());
		}
		super.writeJson();
	}
	
	//销账时调用该方法推送数据到结算
		public void receivableViewOrder(List<ReceivableDetail> rl){
			try {
				List<ReceivableView> rvli=new ArrayList<ReceivableView>();
				for(ReceivableDetail rd:rl){
					ReceivableView rv=receivableViewService.findId(rd.getId());
					rvli.add(rv);
				}
				//调订单中心接口，推送销账信息到订单中心
				Map<String, String> params = new HashMap<String, String>(0);
				params.put("rvli", JSON.toJSONString(rvli));
				String url=SoaPropertiesUtil.soaUrl.getProperty("receivableViewSave");
				String json = httpClient.getDoPostResponseDataByURL(url, params,
						"utf-8", true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

}
