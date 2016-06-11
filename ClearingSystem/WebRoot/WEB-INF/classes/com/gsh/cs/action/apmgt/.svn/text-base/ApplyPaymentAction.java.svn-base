package com.gsh.cs.action.apmgt;

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
import com.gsh.cs.model.Interface.PayableView;
import com.gsh.cs.model.Interface.XiaoZhang;
import com.gsh.cs.model.base.Payable;
import com.gsh.cs.model.base.PayableDetail;
import com.gsh.cs.model.base.SupplierPayment;
import com.gsh.cs.model.parameter.SupplierPaymentPRMT;
import com.gsh.cs.service.apmgt.ApplyPaymentServiceI;
import com.gsh.cs.service.apmgt.PayableViewServiceI;
import com.gsh.cs.service.apmgt.WaitPaymentServiceI;
import com.gsh.cs.tools.SoaPropertiesUtil;
import com.gsh.cs.tools.httpClient;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/apmgt")
@Action(value = "apply")
public class ApplyPaymentAction extends BaseAction implements ModelDriven<SupplierPaymentPRMT> {

	private SupplierPaymentPRMT param = new SupplierPaymentPRMT();

	public SupplierPaymentPRMT getModel() {

		return param;
	}
	private WaitPaymentServiceI waitPaymentService;

	public WaitPaymentServiceI getWaitPaymentService() {
		return waitPaymentService;
	}

	@Autowired
	public void setWaitPaymentService(WaitPaymentServiceI waitPaymentService) {
		this.waitPaymentService = waitPaymentService;
	}

	private ApplyPaymentServiceI applyPaymentService;

	@Autowired
	public void setApplyPaymentService(ApplyPaymentServiceI applyPaymentService) {
		this.applyPaymentService = applyPaymentService;
	}
	@Resource PayableViewServiceI payableViewService;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session=request.getSession();
	/**
	 * 查询
	 */
	public void datagrid() {
		User user=(User)session.getAttribute("userSession");
		super.writeJson(this.applyPaymentService.find(param,user));
	}
	
	public void findAllSearch(){
		try {
			User user=(User)session.getAttribute("userSession");
			if(applyPaymentService.find(param,user).getRows().size()>0){
				super.result.setSuccess(true);
				super.result.setObj(applyPaymentService.find(param,user));
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
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("userSession");
		try {
			//这里要提交给审批系统东西（小许）,小徐那边没有任何异常，这里才能申请成功
			Map<String, String> params = new HashMap<String, String>(0);
			params.put("cid", user.getCid().toString());
			params.put("uid", user.getUid().toString());
			params.put("poid", param.getId().toString());
			params.put("poCode", param.getSettlementNo());
			params.put("eventCode", "fksq");
			params.put("profitJe", param.getPayAmount().toString());
			params.put("ticket", user.getTicket());
			String url=SoaPropertiesUtil.soaUrl.getProperty("startProcess");
			String json = httpClient.getDoPostResponseDataByURL(url, params,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			this.applyPaymentService.submit(param.getId(),bjr.getInfo());
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/apmgt/apply!submit.action", 1, "应付账款付款申请提交",
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
			//审批通过，状态改变为代销账状态，这里应该是通过结算编号改变状态，到时候再跟小徐商量，小许调用我的接口
			String poid=request.getParameter("poid");
			String name=request.getParameter("name");
			String date=request.getParameter("date");
			param.setId(Long.parseLong(poid));
			this.applyPaymentService.huidiaoSubmit(param.getId(),name,date);
			bjr.setFlag(1);//成功
			bjr.setCode("");
			bjr.setInfo("");
		} catch (Exception e) {
			bjr.setFlag(0);//失败
			bjr.setCode("");
			bjr.setInfo("");
			super.result.setMsg(e.getMessage());
		}
		super.writeJson();
	}
	
	/**
	 * 审核驳回回调接口
	 */
	public void bohuiSubmit(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			//审批通过，状态改变为代销账状态，这里应该是通过结算编号改变状态，到时候再跟小徐商量，小许调用我的接口
			String poid=request.getParameter("poid");
			param.setId(Long.parseLong(poid));
			String name=request.getParameter("name");
			String date=request.getParameter("date");
			this.applyPaymentService.bohuiSubmit(param.getId(),name,date);
			bjr.setFlag(1);//成功
			bjr.setCode("");
			bjr.setInfo("");
		} catch (Exception e) {
			bjr.setFlag(0);//失败
			bjr.setCode("");
			bjr.setInfo("");
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
			this.applyPaymentService.revoke(param.getId());
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/apmgt/apply!revoke.action", 1, "应付账款付款申请撤销",
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
			SupplierPayment sp = JSON.parseObject(request.getParameter("form"), SupplierPayment.class);
			sp.setHandNo(user.getUid());
			sp.setHandPerson(user.getName());
			List<PayableDetail> pdl = JSON.parseArray(request.getParameter("detail"), PayableDetail.class);
			List<PayableDetail> pl = this.applyPaymentService.payment(sp, pdl,user);
			payableViewOrder(pl);//推送销账明细到结算
			super.result.setSuccess(true);
			super.result.setMsg("销账成功！");
			
			//只要销账、订单中心就不可作废
			String rcvbid="";
			List<Payable> li=new ArrayList<Payable>();
			for (PayableDetail pd : pdl) {
				Payable p = waitPaymentService.findPayable(pd.getPablid().toString());
				li.add(p);
				if(!rcvbid.equals("")){
					rcvbid+=",";
				}
				rcvbid+=pd.getPablid();
			}
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/apmgt/wpAction!income.action", 1, "应付账款付款申请",
					"应付账款主键ID：" + rcvbid, 1, 0L);// 记录日志
			
			Map<String, String> params = new HashMap<String, String>(0);
			for(Payable pb:li){
				if(pb.getType()==1||pb.getType()==2||pb.getType()==3||pb.getType()==4){
					XiaoZhang x=new XiaoZhang();
					x.setNoticeExchangeNo(pb.getExchangeNo());
					x.setSource("2");
					if(pb.getBalance().equals(0)){
						x.setStatus("1");
					}else{
						x.setStatus("0");
					}
					x.setType(pb.getType().toString());
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
		public void payableViewOrder(List<PayableDetail> pl){
			try {
				List<PayableView> pvli=new ArrayList<PayableView>();
				for(PayableDetail pd:pl){
					PayableView pv=payableViewService.findId(pd.getId());
					pvli.add(pv);
				}
				//调订单中心接口，传递不同意作废信息
				Map<String, String> params = new HashMap<String, String>(0);
				params.put("pvli", JSON.toJSONString(pvli));
				String url=SoaPropertiesUtil.soaUrl.getProperty("payableViewSave");
				String json = httpClient.getDoPostResponseDataByURL(url, params,
						"utf-8", true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	
	
}
