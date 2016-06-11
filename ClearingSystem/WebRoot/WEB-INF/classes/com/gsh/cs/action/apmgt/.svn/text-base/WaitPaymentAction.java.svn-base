package com.gsh.cs.action.apmgt;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.base.MessageException;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.log.appender.LogActionAppender;
import com.gsh.cs.model.Interface.BaseJsonResponse;
import com.gsh.cs.model.Interface.GshSupplierList;
import com.gsh.cs.model.Interface.PayableView;
import com.gsh.cs.model.Interface.XiaoZhang;
import com.gsh.cs.model.base.Payable;
import com.gsh.cs.model.base.PayableDetail;
import com.gsh.cs.model.base.Receivable;
import com.gsh.cs.model.base.SettlementExchangeRate;
import com.gsh.cs.model.base.SupplierPayment;
import com.gsh.cs.model.parameter.PayablePRMT;
import com.gsh.cs.service.apmgt.PayableViewServiceI;
import com.gsh.cs.service.apmgt.WaitPaymentServiceI;
import com.gsh.cs.tools.SoaPropertiesUtil;
import com.gsh.cs.tools.httpClient;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.tools.internal.ws.processor.model.Request;

@Namespace("/apmgt")
@Action(value = "/wpAction")
public class WaitPaymentAction extends BaseAction implements ModelDriven<PayablePRMT> {
	private PayablePRMT param = new PayablePRMT();

	public PayablePRMT getModel() {
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
	@Resource PayableViewServiceI payableViewService;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session=request.getSession();
	/**
	 * 新建
	 */
	public void add(){
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("userSession");
		try{
			Payable p=new Payable();
			BeanUtils.copyProperties(param, p);
			p.setExchangeNo(waitPaymentService.addCode());
			p.setTradeDate(new Date());
			p.setBalance(p.getPayBeAmount());
			p.setCreateDate(new Date());
			p.setPayAmount(BigDecimal.ZERO);
			p.setNoticeStatus(1);
			p.setProductStatus(1);
			p.setCurrencyType("CNY");
			p.setRevocationHas(1);
			p.setCancelStatus(1);
			if(p.getType()==5||p.getType()==6){
				p.setReverseHas(2);
			}else{
				p.setReverseHas(1);
			}
			if(p.getType()==6){
				p.setYajinSort(10);
			}else{
				p.setYajinSort(1);
			}
			p.setCreaterNo(user.getCid());
			p.setCreater(user.getName());
			p = this.waitPaymentService.add(p);
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/apmgt/wpAction!add.action", 1, "新建应付账款",
					"单据号码：" + p.getExchangeNo(), 1, p.getId());// 记录日志
			super.result.setSuccess(true);
			super.result.setMsg("添加成功！");
			super.result.setObj(p);
		} catch (Exception e) {
			super.result.setMsg(e.getMessage());
		}
		super.writeJson();
	}
	
	/**
	 * 查询
	 */
	public void datagrid() {
		User user=(User)session.getAttribute("userSession");
		String supplierNo=request.getParameter("supplierNo");
		String exchangeNo=request.getParameter("exchangeNo");
		String tradeDateQi=request.getParameter("tradeDateQi");
		String tradeDateShi=request.getParameter("tradeDateShi");
		String orderNo=request.getParameter("orderNo");
		String affiliationNo=request.getParameter("affiliationNo");
		String deadlineShi=request.getParameter("deadlineShi");
		String productNo=request.getParameter("productNo");
		String groupNumber=request.getParameter("groupNumber");
		
		param.setSupplierNo(supplierNo);
		param.setExchangeNo(exchangeNo);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		try {
			if(tradeDateQi!=null&&!tradeDateQi.equals("")){
				param.setTradeDateQi(df.parse(tradeDateQi));
			}
			if(tradeDateShi!=null&&!tradeDateShi.equals("")){
				param.setTradeDateShi(df.parse(tradeDateShi));
			}
			if(deadlineShi!=null&&!deadlineShi.equals("")){
				param.setDeadlineShi(df.parse(deadlineShi));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		param.setOrderNo(orderNo);
		if(affiliationNo!=null&&!affiliationNo.equals("")){
			param.setAffiliationNo(Long.parseLong(affiliationNo));
		}
		if(groupNumber!=null&&!groupNumber.equals("")){
			param.setGroupNumber(groupNumber);
		}
		param.setProductNo(productNo);
		
		if(param.getPage()==0){
			param.setPage(1);
			param.setRows(10);
		}
		super.writeJson(this.waitPaymentService.findPayable(param,user));
	}
	
	/**
	 * 条件查询
	 */
	public void findAllSearch(){
		try {
			User user=(User)session.getAttribute("userSession");
			if(this.waitPaymentService.findPayable(param,user).getRows().size()>0){
				if(param.getPage()==0){
					param.setPage(1);
					param.setRows(10);
				}
				this.result.setSuccess(true);
				this.result.setObj(this.waitPaymentService.findPayable(param,user));
			}else{
				this.result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}

	public void findAllexNo(){
		try {
			String exchangeNo=request.getParameter("exchangeNo");
			super.writeJson(waitPaymentService.findAllexNo(exchangeNo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 撤销
	 */
	public void revocation() {
		try {
			User user=(User)session.getAttribute("userSession");
			Payable t = this.waitPaymentService.revocation(param.getId());
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/apmgt/wpAction!revocation.action", 1, "撤销应付账款",
					"单据号码：" + t.getExchangeNo(), 1, t.getId());// 记录日志
			super.result.setSuccess(true);
			super.result.setMsg("撤销成功！");
			super.result.setObj(t);
		} catch (MessageException e) {
			super.result.setMsg(e.getMessage());
		} catch (Exception e) {
			super.result.setMsg(e.getMessage());
		}
		super.writeJson();
	}

	/**
	 * 付款销账
	 */
	public void pay() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("userSession");
		try {
			SupplierPayment sp = JSON.parseObject(request.getParameter("form"), SupplierPayment.class);
			sp.setHandNo(user.getUid());
			sp.setHandPerson(user.getName());
			List<PayableDetail> pdl = JSON.parseArray(request.getParameter("detail"), PayableDetail.class);
			List<PayableDetail> pl = this.waitPaymentService.pay(sp, pdl,user);
			payableViewOrder(pl);//推送销账明细到结算
			super.result.setSuccess(true);
			super.result.setMsg("付款成功！");
			
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
					"/apmgt/wpAction!pay.action", 1, "应付账款销账付款",
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

	/**
	 * 收入销账
	 */
	public void income() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("userSession");
		try {
			SupplierPayment sp = JSON.parseObject(request.getParameter("form"), SupplierPayment.class);
			sp.setHandNo(user.getUid());
			sp.setHandPerson(user.getName());
			List<PayableDetail> pdl = JSON.parseArray(request.getParameter("detail"), PayableDetail.class);
			List<PayableDetail> pl= this.waitPaymentService.income(sp, pdl,user);
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
					"/apmgt/wpAction!income.action", 1, "应付账款销账收入",
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

	/**
	 * 付款申请
	 */
	public void apply() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("userSession");
		try {
			SupplierPayment sp = JSON.parseObject(request.getParameter("form"), SupplierPayment.class);
			sp.setBankRate(new BigDecimal("1"));
			sp.setExchangeProfitLoss(BigDecimal.ZERO);
			sp.setHandNo(user.getUid());
			sp.setHandPerson(user.getName());
			List<PayableDetail> pdl = JSON.parseArray(request.getParameter("detail"), PayableDetail.class);
			this.waitPaymentService.apply(sp, pdl);
			String rcvbid="";
			List<Payable> li=new ArrayList<Payable>();
			for (PayableDetail pd : pdl) {
				if(!rcvbid.equals("")){
					rcvbid+=",";
				}
				rcvbid+=pd.getPablid();
			}
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/apmgt/wpAction!income.action", 1, "应付账款付款申请",
					"应付账款主键ID：" + rcvbid, 1, 0L);// 记录日志
			super.result.setSuccess(true);
			super.result.setMsg("申请成功！");
		} catch (MessageException e) {
			super.result.setMsg(e.getMessage());
		} catch (Exception e) {
			super.result.setMsg(e.getMessage());
		}
		super.writeJson();
	}
	/**
	 * 查询归属人
	 */
	public void findAllList(){
		try {
			User user=(User)session.getAttribute("userSession");
			writeJson(waitPaymentService.findAllList(user));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询供应商编号,检索
	 */
	public void findAllSupplierNo(){
		try {
			String code="";
			writeJson(waitPaymentService.findAllSupplierNo(code));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询供应商编号,检索
	 */
	public void findAllSupplierNoJianSuo(){
		try {
			String code=request.getParameter("code");
			super.result.setObj(waitPaymentService.findAllSupplierNo(code));
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	/**
	 * 结算推送单据
	 */
	public void AddPayable(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			User user=(User)session.getAttribute("userSession");
			String json="["+request.getParameter("payable")+"]";
			ArrayList<Payable> pblist = (ArrayList<Payable>) JSON
					.parseArray(json, Payable.class);
//			rblist.get(0).setCreater(super.getUser().getName());
//			rblist.get(0).setCreaterNo(super.getUser().getCid());
//			pblist.get(0).setCreater("唐彩红");
//			pblist.get(0).setCreaterNo((long)10001);
			waitPaymentService.save(pblist.get(0));
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/apmgt/wpAction!income.action", 1, "应付账款结算推送单据",
					"应付账款单据号码：" + pblist.get(0).getExchangeNo(), 1, 0L);// 记录日志
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
	
	/**
	 * 订单调用接口，改变申请取消状态
	 */
	public void UpdateCancelStatus(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			String exchangeNo=request.getParameter("exchangeNo");
			waitPaymentService.UpdateCancelStatus(exchangeNo);
			response.setCharacterEncoding("UTF-8");
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
	
	/**
	 * 同意和不同意的情况下返回信息给订单中心
	 */
	public void tngyiButongyi(){
		try {
			String status=request.getParameter("status");
			String id=request.getParameter("id");
			String exchangeNo=request.getParameter("exchangeNo");
			if(status.equals("1")){//同意
				Payable pf = waitPaymentService.tongyi(id);
				super.result.setSuccess(true);
				super.result.setObj(pf);
				
				//调订单中心接口，传递同意作废信息
				Map<String, String> params = new HashMap<String, String>(0);
				params.put("exchangeNo", exchangeNo);
				params.put("status", status);
				String url=SoaPropertiesUtil.soaUrl.getProperty("UpdateOrderTongyiNo");
				String json = httpClient.getDoPostResponseDataByURL(url, params,
						"utf-8", true);
			}else{//不同意
				waitPaymentService.butongyi(id);
				super.result.setSuccess(true);
				
				//调订单中心接口，传递不同意作废信息
				Map<String, String> params = new HashMap<String, String>(0);
				params.put("exchangeNo", exchangeNo);
				params.put("status", status);
				String url=SoaPropertiesUtil.soaUrl.getProperty("UpdateOrderTongyiNo");
				String json = httpClient.getDoPostResponseDataByURL(url, params,
						"utf-8", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		super.writeJson();
	}
	
	/**
	 * 订单中心有了通知单，调用该接口修改结算通知单状态
	 */
	public void UpdateNoticeStatus(){
		String exchangeNo=request.getParameter("exchangeNo");
		waitPaymentService.UpdateNoticeStatus(exchangeNo);
	}
	
	/**
	 * 订单中心有了产品单，调用该接口修改结算产品单状态
	 */
	public void UpdateProductStatus(){
		String exchangeNo=request.getParameter("exchangeNo");
		waitPaymentService.UpdateProductStatus(exchangeNo);
	}
	
	
	/**
	 * 订单中心调用接口，修改付款交换单的异常状态
	 */
	public void UpdateAbnormal(){
		try {
			String exchangeNo=request.getParameter("exchangeNo");
			String abnormalStatus=request.getParameter("abnormalStatus");
			waitPaymentService.UpdateAbnormal(exchangeNo,abnormalStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 通过产品单号去结算查询归属人信息和客户信息,再通过归属人姓名去云平台查询详细信息
	 */
	public void OrderSearch(){
		BaseJsonResponse bjr=new BaseJsonResponse();
		try {
			String productOrderNo=request.getParameter("productOrderNo");
			Map<String, String> params = new HashMap<String, String>(0);
			params.put("productOrderNo", productOrderNo);
			String url=SoaPropertiesUtil.soaUrl.getProperty("findProducdOrder");
			String json = httpClient.getDoPostResponseDataByURL(url, params,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			if(bjr.getFlag()==1){
				String s1="["+bjr.getInfo()+"]";
				String s2 = "{\"affiliationNo\":0,\"affiliationPerson\":\"公司\",\"customerNo\":\"\",\"customerName\":\"\"},";
				int i = 1;// 插入到第三位
				String newString = s1.substring(0, i) + s2 + s1.substring(i, s1.length());

//				String newString1 = newString. replaceFirst("customerNo","accountCode");
//				String newString2 = newString1. replaceFirst("customerName","accountName");
				System.out.println(newString);
				super.result.setObj(newString);
				super.result.setSuccess(true);
			}else{
				//订单号错误
				super.result.setSuccess(false);
			}
		} catch (Exception e) {
			super.result.setSuccess(false);
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	//订单中心新增作废，结算对应的应付生成一条反向数据
	public void updateWaitPaymentCS(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			String exchangeNo=request.getParameter("exchangeNo");
			waitPaymentService.updateWaitPaymentCS(exchangeNo);
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
	
	//订单中心生成通知单，推送到结算对应的交换单的确认号需要填充
	public void updateQrh(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			String exchangeNo = request.getParameter("exchangeNo");
			String productNo = request.getParameter("productNo");
			waitPaymentService.updateQrh(exchangeNo, productNo);
			bjr.setFlag(1);//失败
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
	
	public void findSupmid(){
		try {
			String supmid=request.getParameter("supmid");
			writeJson(payableViewService.findSupmid(supmid));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
