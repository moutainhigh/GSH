package com.gsh.cs.action.armgt;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
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
import com.gsh.cs.model.Interface.CustomerProfilesInfo;
import com.gsh.cs.model.Interface.GshSupplierList;
import com.gsh.cs.model.Interface.OrderGuishu;
import com.gsh.cs.model.Interface.PayableView;
import com.gsh.cs.model.Interface.PortalCompanyUsersP;
import com.gsh.cs.model.Interface.ReceivableView;
import com.gsh.cs.model.Interface.XiaoZhang;
import com.gsh.cs.model.base.CustomerPayment;
import com.gsh.cs.model.base.DeadlineChg;
import com.gsh.cs.model.base.Payable;
import com.gsh.cs.model.base.PayableDetail;
import com.gsh.cs.model.base.Receivable;
import com.gsh.cs.model.base.ReceivableDetail;
import com.gsh.cs.model.parameter.ReceivablePRMT;
import com.gsh.cs.service.armgt.DeadlineChgServiceI;
import com.gsh.cs.service.armgt.ReceivableServiceI;
import com.gsh.cs.service.armgt.ReceivableViewServiceI;
import com.gsh.cs.tools.LongTool;
import com.gsh.cs.tools.SoaPropertiesUtil;
import com.gsh.cs.tools.httpClient;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/armgt")
@Action(value = "receivable")
public class ReceivableAction extends BaseAction implements ModelDriven<ReceivablePRMT> {

	private ReceivablePRMT param = new ReceivablePRMT();

	public ReceivablePRMT getModel() {
		return param;
	}

	private ReceivableServiceI receivableService;

	private DeadlineChgServiceI deadlineChgService;

	@Autowired
	public void setReceivableService(ReceivableServiceI receivableService) {
		this.receivableService = receivableService;
	}

	@Autowired
	public void setDeadlineChgService(DeadlineChgServiceI deadlineChgService) {
		this.deadlineChgService = deadlineChgService;
	}
	@Resource
	ReceivableViewServiceI receivableViewService;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session=request.getSession();
	public void datagrid() {
		String tradeDateQi=request.getParameter("tradeDateQi");
		String tradeDateShi=request.getParameter("tradeDateShi");
		String customerNo=request.getParameter("customerNo");
		String orderNo=request.getParameter("orderNo");
		String affiliationNo=request.getParameter("affiliationNo");
		String deadlineShi=request.getParameter("deadlineShi");
		String noticeNo=request.getParameter("noticeNo");
		String groupNumber=request.getParameter("groupNumber");
		User user=(User)session.getAttribute("userSession");
		param.setCustomerNo(customerNo);
		param.setNoticeNo(noticeNo);
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
		if(param.getPage()==0){
			param.setPage(1);
			param.setRows(10);
		}
		super.writeJson(this.receivableService.find(param,user));
	}
	
	public void findAllSearch(){
		try {
			User user=(User)session.getAttribute("userSession");
			if(this.receivableService.find(param,user).getRows().size()>0){
				if(param.getPage()==0){
					param.setPage(1);
					param.setRows(10);
				}
				this.result.setSuccess(true);
				this.result.setObj(this.receivableService.find(param,user));
			}else{
				this.result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	/**
	 * 新建
	 */
	public void add() {
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("userSession");
		try {
			Receivable t = new Receivable();
			BeanUtils.copyProperties(param, t);
			String code=receivableService.addCode();
			t.setNoticeNo(code);
			t.setTradeDate(new Date());
			t.setBalance(t.getIncomeBeAmount());
			t.setCreateDate(new Date());
			t.setDeadlineHas(0);
			t.setIncomeAmount(BigDecimal.ZERO);
			t.setOriginalPaymentMethod(0);
			t.setRevocationHas(1);
			t.setCreaterNo(user.getCid());
			t.setCreater(user.getName());
			if(t.getType()==4||t.getType()==5){
				t.setReverseHas(2);
			}else{
				t.setReverseHas(1);
			}
			if(t.getAffiliationNo()==null){
				t.setAffiliationNo(param.getAffiliationNo1());
			}
			if(t.getType()==5){
				t.setYajinSort(10);
			}else{
				t.setYajinSort(1);
			}
			
			t = this.receivableService.add(t);
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/armgt/receivable!add.action", 1, "新增应收账款",
					"单据号码：" + t.getNoticeNo(), 1, t.getId());// 记录日志
			super.result.setSuccess(true);
			super.result.setMsg("添加成功！");
			super.result.setObj(t);
		} catch (Exception e) {
			super.result.setMsg(e.getMessage());
		}
		super.writeJson();
	}

	/**
	 * 撤销
	 */
	public void revocation() {
		try {
			User user=(User)session.getAttribute("userSession");
			Receivable t = this.receivableService.revocation(param.getId());
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/armgt/receivable!revocation.action", 1, "撤销应收账款",
					"单据号码：" + t.getNoticeNo(), 1, t.getId());// 记录日志
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
	 * 修改结算期限
	 */
	public void deadline() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("userSession");
		try {
			DeadlineChg d = JSON.parseObject(request.getParameter("form"), DeadlineChg.class);
			d.setModifiedDate(new Date());
			d.setModifier(user.getName());
			d.setModifierNo(user.getCid());
			Receivable t = this.receivableService.deadline(d);
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/armgt/receivable!deadline.action", 1, "修改应收账款结算期限",
					"单据号码：" + t.getNoticeNo(), 1, t.getId());// 记录日志
			super.result.setSuccess(true);
			super.result.setMsg("修改成功！");
			super.result.setObj(t);
		} catch (Exception e) {
			super.result.setMsg(e.getMessage());
		}
		super.writeJson();
	}

	/**
	 * 结算日期变更记录查询
	 */
	public void deadlineDtgd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		super.writeJson(this.deadlineChgService.find(Long.parseLong(request.getParameter("rcvbid"))));
	}

	/**
	 * 销账 -收入
	 */
	public void income() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("userSession");
		try {
			CustomerPayment cp = JSON.parseObject(request.getParameter("form"), CustomerPayment.class);
			cp.setHandNo(user.getUid());
			cp.setHandPerson(user.getName());
			List<ReceivableDetail> rdl = JSON.parseArray(request.getParameter("detail"), ReceivableDetail.class);
			List<ReceivableDetail> rl = this.receivableService.income(cp, rdl,user);
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
					int r=rb.getBalance().compareTo(BigDecimal.ZERO); //和0，Zero比较
					if(r==0){//等于
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
			
		} catch (Exception e) {
			super.result.setMsg(e.getMessage());
		}
		super.writeJson();
	}

	/**
	 * 销账 -退款
	 */
	public void refund() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("userSession");
		try {
			CustomerPayment cp = JSON.parseObject(request.getParameter("form"), CustomerPayment.class);
			cp.setHandNo(user.getUid());
			cp.setHandPerson(user.getName());
			List<ReceivableDetail> rdl = JSON.parseArray(request.getParameter("detail"), ReceivableDetail.class);
			List<ReceivableDetail> rl = this.receivableService.refund(cp, rdl,user);
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
					"/armgt/receivable!refund.action", 1, "应收账款销账退款",
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
			
			
		} catch (Exception e) {
			super.result.setMsg(e.getMessage());
		}
		super.writeJson();
	}

	/**
	 * 退款申请
	 */
	public void apply() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("userSession");
		try {
			CustomerPayment cp = JSON.parseObject(request.getParameter("form"), CustomerPayment.class);
			cp.setHandNo(user.getUid());
			cp.setHandPerson(user.getName());
			List<ReceivableDetail> rdl = JSON.parseArray(request.getParameter("detail"), ReceivableDetail.class);
			this.receivableService.apply(cp, rdl);
			String rcvbid="";
			List<Receivable> li=new ArrayList<Receivable>();
			for (ReceivableDetail rd : rdl) {
				if(!rcvbid.equals("")){
					rcvbid+=",";
				}
				rcvbid+=rd.getRcvbid();
			}
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/armgt/receivable!apply.action", 1, "应收账款退款申请",
					"应收账款主键ID：" + rcvbid, 1, 0L);// 记录日志
			super.result.setSuccess(true);
			super.result.setMsg("申请成功！");
		} catch (Exception e) {
			super.result.setMsg(e.getMessage());
		}
		super.writeJson();
	}
	
	/**
	 * 订单中心调用接口、推送应收单据记录
	 */
	public void AddReceiva(){
		User user=(User)session.getAttribute("userSession");
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			String json="["+request.getParameter("receivable")+"]";
			System.out.println(json);
			ArrayList<Receivable> rblist = (ArrayList<Receivable>) JSON
					.parseArray(json, Receivable.class);
//			rblist.get(0).setCreater(super.getUser().getName());
//			rblist.get(0).setCreaterNo(super.getUser().getCid());
//			rblist.get(0).setCreater("唐彩红");
//			rblist.get(0).setCreaterNo((long)10001);
			receivableService.save(rblist.get(0));
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/armgt/receivable!AddReceiva.action", 1, "订单中心推送数据到应收账款",
					"单据号码：" + rblist.get(0).getNoticeNo(), 1, 0L);// 记录日志
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
	 * 调用云平台接口，获取归属人信息
	 */
	public void findAffiliationNo(){
		BaseJsonResponse bjr=new BaseJsonResponse();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("userSession");
		try {
			Map<String, String> params = new HashMap<String, String>(0);
			params.put("cid", user.getCid().toString());
			String url=SoaPropertiesUtil.soaUrl.getProperty("findCpByCid");
			String json = httpClient.getDoPostResponseDataByURL(url, params,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			String s1=bjr.getInfo();
			String s2 = "{\"cid\":10001,\"uid\":0,\"name\":\"公司\",\"sex\":\"\",\"dept\":\"\",\"empId\":\"\"},";
			int i = 1;// 插入到第三位
			String newString = s1.substring(0, i) + s2 + s1.substring(i, s1.length());
			super.result.setObj(newString);
			super.result.setSuccess(true);
		} catch (Exception e) {
			super.result.setSuccess(false);
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	/**
	 * 订单中心作废应收单据---不使用，修改成同意和不同意的作废方式
	 */
	public void OrderZuofei(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			String noticeNo=request.getParameter("noticeNo");
			String type=request.getParameter("type");
			String createDate =request.getParameter("createDate");
			Receivable r = receivableService.OrderZuofei(noticeNo);
			if(r!=null){
				bjr.setFlag(1);//成功
				bjr.setCode("");
				bjr.setInfo("");
				this.writeJson(bjr);
			}else{
				bjr.setFlag(0);//失败
				bjr.setCode("");
				bjr.setInfo("");
				this.writeJson(bjr);
			}
		} catch (Exception e) {
			bjr.setFlag(0);//失败
			bjr.setCode("");
			bjr.setInfo("");
			this.writeJson(bjr);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 通过订单号去订单中心查询归属人信息和客户信息,再通过归属人姓名去云平台查询详细信息
	 */
	public void OrderSearch(){
		BaseJsonResponse bjr=new BaseJsonResponse();
		try {
			String orderNo=request.getParameter("orderNo");
			Map<String, String> params = new HashMap<String, String>(0);
			params.put("orderNo", orderNo);
			String url=SoaPropertiesUtil.soaUrl.getProperty("findOrderOdderNo");
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
	
	
	public void text(){
//		 String html = request.getParameter("html");
//         if(html != null){
//             byte[] bs = html.getBytes();
//             ByteArrayInputStream bais = new ByteArrayInputStream(bs);
//             POIFSFileSystem poifs = new POIFSFileSystem();
//             DirectoryEntry directory = poifs.getRoot();  
//             DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
//             FileOutputStream ostream = new FileOutputStream("D:\\a.doc");
//             poifs.writeFilesystem(ostream);
//             bais.close();  
//             ostream.close();               
//         }
	}

	/**
	 * 订单中心需要的接口
	 * 根据客户编号查询所有未销账的数据
	 * 根据起始日期，终止日期，客户编号查询应收数据
	 */
	public void findParpaySearch(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			String customerNo=request.getParameter("customerNo");
			String startDate=request.getParameter("startDate");
			String endDate=request.getParameter("endDate");
			double money = receivableService.findReceivableSearch(customerNo,startDate,endDate);
			bjr.setFlag(1);//成功
			bjr.setCode("");
			bjr.setInfo(String.valueOf(money));
			this.writeJson(bjr);
		} catch (Exception e) {
			bjr.setFlag(0);//成功
			bjr.setCode("");
			bjr.setInfo("");
			this.writeJson(bjr);
			e.printStackTrace();
		}
	}
	
	//根据通知单号查询未销账金额
	public void findMoney(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			String noticeNo=request.getParameter("noticeNo");
			double money = receivableService.findMoney(noticeNo);
			bjr.setFlag(1);//成功
			bjr.setCode("");
			bjr.setInfo(String.valueOf(money));
			this.writeJson(bjr);
		} catch (Exception e) {
			bjr.setFlag(0);//成功
			bjr.setCode("");
			bjr.setInfo("");
			this.writeJson(bjr);
			e.printStackTrace();
		}
	}
	
	//cm报表查询接口
	public void findReceivableReport(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			bjr.setFlag(1);//成功
			bjr.setCode("");
			bjr.setInfo(JSON.toJSONString(receivableService.findReceivableReport()));
			this.writeJson(bjr);
		} catch (Exception e) {
			bjr.setFlag(0);//成功
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
			String noticeNo=request.getParameter("noticeNo");
			if(status.equals("1")){//同意
				Receivable pf = receivableService.tongyi(id);
				super.result.setSuccess(true);
				super.result.setObj(pf);
				
				//调订单中心接口，传递同意作废信息
				Map<String, String> params = new HashMap<String, String>(0);
				params.put("noticeNo", noticeNo);
				String url=SoaPropertiesUtil.soaUrl.getProperty("UpdateOrderInvTongyi");
				String json = httpClient.getDoPostResponseDataByURL(url, params,
						"utf-8", true);
			}else{//不同意
				receivableService.butongyi(id);
				super.result.setSuccess(true);
				
				//调订单中心接口，传递不同意作废信息
				Map<String, String> params = new HashMap<String, String>(0);
				params.put("noticeNo", noticeNo);
				String url=SoaPropertiesUtil.soaUrl.getProperty("UpdateOrderInvTongyiNo");
				String json = httpClient.getDoPostResponseDataByURL(url, params,
						"utf-8", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		super.writeJson();
	}
	
	/**
	 * 订单调用接口，改变申请取消状态
	 */
	public void UpdateCancelStatus(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			String noticeNo=request.getParameter("noticeNo");
			receivableService.UpdateCancelStatus(noticeNo);
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
