package com.gsh.cs.action.bset;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.model.Interface.BaseJsonResponse;
import com.gsh.cs.model.Interface.CustomerProfilesInfo;
import com.gsh.cs.model.Interface.GshSupplierAccount;
import com.gsh.cs.model.Interface.GshSupplierList;
import com.gsh.cs.model.base.CollectpayAccount;
import com.gsh.cs.model.base.Payable;
import com.gsh.cs.model.base.Receivable;
import com.gsh.cs.model.parameter.CollectpayAccountPRMT;
import com.gsh.cs.service.apmgt.WaitPaymentServiceI;
import com.gsh.cs.service.armgt.DeadlineChgServiceI;
import com.gsh.cs.service.armgt.ReceivableServiceI;
import com.gsh.cs.service.bset.CollectpayaccountServiceI;
import com.gsh.cs.tools.LongTool;
import com.gsh.cs.tools.SoaPropertiesUtil;
import com.gsh.cs.tools.httpClient;
import com.opensymphony.xwork2.ModelDriven;
import com.gsh.cs.filter.loginUser.User;
@Namespace("/bset")
@Action(value = "CollectpayaccountAction")
public class CollectpayaccountAction extends BaseAction implements ModelDriven<CollectpayAccountPRMT> {
	private CollectpayAccountPRMT cp=new CollectpayAccountPRMT();
	public CollectpayAccountPRMT getModel() {
		// TODO Auto-generated method stub
		return cp;
	}
	@Resource CollectpayaccountServiceI collectpayaccountService;
	private ReceivableServiceI receivableService;
	@Autowired
	public void setReceivableService(ReceivableServiceI receivableService) {
		this.receivableService = receivableService;
	}
	private WaitPaymentServiceI waitPaymentService;

	public WaitPaymentServiceI getWaitPaymentService() {
		return waitPaymentService;
	}

	@Autowired
	public void setWaitPaymentService(WaitPaymentServiceI waitPaymentService) {
		this.waitPaymentService = waitPaymentService;
	}
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	/**
	 * 条件查询
	 */
	public void findAllSearch(){
		User user=(User)request.getSession().getAttribute("userSession");
		try {
			String source=request.getParameter("accountSource");
			cp.setAccountSource(Integer.parseInt(source));
			if(collectpayaccountService.findAll(cp,user).getRows().size()>0){
				super.result.setSuccess(true);
				super.result.setObj(collectpayaccountService.findAll(cp,user));
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
	 * 查询
	 */
	public void findAll(){
		User user=(User)request.getSession().getAttribute("userSession");
		try {
			String source=request.getParameter("accountSource");
			cp.setAccountSource(Integer.parseInt(source));
			writeJson(this.collectpayaccountService.findAll(cp,user));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void findList(){
		User user=(User)request.getSession().getAttribute("userSession");
		try {
			String source=request.getParameter("accountSource");
			String code=request.getParameter("supplierNo");
			super.writeJson(this.collectpayaccountService.findAllList(source,code,user));
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	public void findListJiansuo(){
		User user=(User)request.getSession().getAttribute("userSession");
		try {
			String source=request.getParameter("accountSource");
			String code=request.getParameter("code");
			super.writeJson(this.collectpayaccountService.findAllList(source,code,user));
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	
	/**
	 * 接口，调用接口需要提供三个属性，accountCode、accountType、accountName、accountSource
	 */
	public void save(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		User user=(User)request.getSession().getAttribute("userSession");
		PrintWriter out=null;
		try {
			String activation=request.getParameter("activation");
			String cid=request.getParameter("cid");
			out=response.getWriter();
			CollectpayAccount c=new CollectpayAccount();
			c.setCid(Long.parseLong(cid));
			c.setAccountCode(request.getParameter("accountCode"));
			c.setAccountType(request.getParameter("accountType"));
			c.setAccountName(request.getParameter("accountName"));
			c.setAccountSource(Integer.parseInt(request.getParameter("accountSource")));
			c.setAccountBalance(BigDecimal.valueOf(0));
//			if(activation!=null&&activation.equals("1")){
//				c.setAccountStatus(1);
//			}else{
			c.setAccountStatus(0);
//			}
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			c.setAccountDate((LongTool.stringTosqlDate(df.format(new Date()))));
			collectpayaccountService.save(c);
			out.print(1);
			this.writeJson(bjr);
		} catch (Exception e) {
			out.print(0);
			e.printStackTrace();
		}
	}
	
	/**
	 * 调用接口。激活账户
	 */
	public void update(){
		BaseJsonResponse bjr=new BaseJsonResponse();
		try {
			Map<String, String> params = new HashMap<String, String>(0);
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User user=(User) session.getAttribute("userSession");
			String url="";
			if(cp.getAccountSource()==1){
				params.put("cpCode", cp.getAccountCode());
				params.put("cid", user.getCid().toString());
				url=SoaPropertiesUtil.soaUrl.getProperty("UpdateCrmJihuo");
			}else if(cp.getAccountSource()==2){
				params.put("accountCode", cp.getAccountCode());
				params.put("cid",user.getCid().toString());
				url=SoaPropertiesUtil.soaUrl.getProperty("UpdateGysJihuo");
			}
			String json = httpClient.getDoPostResponseDataByURL(url, params,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			if(bjr.getFlag()==1){
				cp.setAccountStatus(1);
				cp.setCid(user.getCid());
				CollectpayAccount c = collectpayaccountService.update(cp);
				//推送数据到应收账款
				if(cp.getAccountSource()==1&&cp.getAccountBalance().compareTo(new BigDecimal(0))!=0){
					Receivable r=new Receivable();
					String code=receivableService.addCode();
					r.setNoticeNo(code);
					r.setCustomerNo(c.getAccountCode());
					r.setCustomerName(c.getAccountName());
					r.setCreateDate(new Date());
					r.setType(6);
					r.setAffiliationPerson(user.getName());
					r.setTradeDate(new Date());
					r.setAffiliationNo(user.getUid());
					r.setDeadlineHas(0);
					r.setCreater(user.getName());
					r.setCreaterNo(user.getCid());
					r.setRevocationHas(2);
					r.setReverseHas(1);
					r.setIncomeBeAmount(c.getAccountBalance());
					r.setIncomeAmount(new BigDecimal(0));
					r.setBalance(c.getAccountBalance());
					r.setYajinSort(1);
					r.setOriginalPaymentMethod(0);
					receivableService.add(r);
				}else if(cp.getAccountSource()==2&&cp.getAccountBalance().compareTo(new BigDecimal(0))!=0){
					Payable p=new Payable();
					p.setExchangeNo(waitPaymentService.addCode());
					p.setTradeDate(new Date());
					p.setSupplierNo(c.getAccountCode());
					p.setSupplierName(c.getAccountName());
					p.setTradeDate(new Date());
					p.setPayBeAmount(c.getAccountBalance());
					p.setPayAmount(new BigDecimal(0));
					p.setBalance(c.getAccountBalance());
					p.setType(7);
					p.setCreateDate(new Date());
					p.setAffiliationPerson(user.getName());
					p.setAffiliationNo(user.getUid());
					p.setCreater(user.getName());
					p.setCreaterNo(user.getCid());
					p.setNoticeStatus(2);
					p.setProductStatus(2);
					p.setCancelStatus(1);
					p.setCurrencyType("CNY");
					p.setRevocationHas(2);
					p.setReverseHas(1);
					p.setYajinSort(1);
					waitPaymentService.add(p);
				}
				
				super.result.setSuccess(true);
				super.result.setMsg("激活成功!");
				super.result.setObj(c);
			}else{
				super.result.setSuccess(false);
				super.result.setMsg("激活失败!");
			}
			
		} catch (Exception e) {
			super.result.setSuccess(false);
			super.result.setMsg("激活失败!");
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	/**
	 * 调用接口查看客户基础信息和财务信息
	 */
	public void findSee(){
		BaseJsonResponse bjr=new BaseJsonResponse();
		CustomerProfilesInfo cpi=new CustomerProfilesInfo();
		String cpCode=request.getParameter("cpCode");
		try {
			Map<String, String> params = new HashMap<String, String>(0);
			params.put("cpCode", cpCode);
			String url=SoaPropertiesUtil.soaUrl.getProperty("findCrm");
			String json = httpClient.getDoPostResponseDataByURL(url, params,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			super.result.setObj(JSON.parseObject(bjr.getInfo(), CustomerProfilesInfo.class));
			super.result.setSuccess(true);
		} catch (Exception e) {
			super.result.setSuccess(false);
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	/**
	 * 调用接口查看供应商基础信息和财务信息
	 */
	public void findSupplier(){
		BaseJsonResponse bjr=new BaseJsonResponse();
		GshSupplierList cpi=new GshSupplierList();
		String accountCode=request.getParameter("accountCode");
		try {
			Map<String, String> params = new HashMap<String, String>(0);
			params.put("accountCode", accountCode);
			String url=SoaPropertiesUtil.soaUrl.getProperty("findGysCaiwu");
			String json = httpClient.getDoPostResponseDataByURL(url, params,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			super.result.setObj(JSON.parseObject(bjr.getInfo(), GshSupplierList.class));
			super.result.setSuccess(true);
		} catch (Exception e) {
			super.result.setSuccess(false);
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	/**
	 * 调用供应商接口，获取账号
	 */
	public void findBankAccountNo(){
		BaseJsonResponse bjr=new BaseJsonResponse();
		GshSupplierAccount gsl=new GshSupplierAccount();
		String cpCode=request.getParameter("accountCode");
		try {
			Map<String, String> params = new HashMap<String, String>(0);
			params.put("accountCode", cpCode);
			String url=SoaPropertiesUtil.soaUrl.getProperty("findGysZhanghu");
			String json = httpClient.getDoPostResponseDataByURL(url, params,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			super.result.setObj(bjr.getInfo());
			super.result.setSuccess(true);
		} catch (Exception e) {
			super.result.setSuccess(false);
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	public void findAllList(){
		User user=(User)request.getSession().getAttribute("userSession");
		try {
			String accountSource=request.getParameter("accountSource");
			String code="";
			writeJson(collectpayaccountService.findAllList(accountSource,code,user));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void findAllListJiansuo(){
		User user=(User)request.getSession().getAttribute("userSession");
		try {
			String accountSource=request.getParameter("accountSource");
			String code=request.getParameter("code");
			super.result.setObj(collectpayaccountService.findAllList(accountSource,code,user));
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	/**
	 * 应收账户类型
	 */
	public void findAllTypeShou(){
		User user=(User)request.getSession().getAttribute("userSession");
		String cid="";
		if(user==null){
			cid=request.getParameter("cid");
		}else{
			cid=user.getCid().toString();
		}
		try {
			writeJson(collectpayaccountService.findAllType("1",cid));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 应付账户类型
	 */
	public void findAllTypeFu(){
		User user=(User)request.getSession().getAttribute("userSession");
		String cid="";
		if(user==null){
			cid=request.getParameter("cid");
		}else{
			cid=user.getCid().toString();
		}
		try {
			writeJson(collectpayaccountService.findAllType("2",cid));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void UpdateZH(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		PrintWriter out=null;
		try {
			String idCode=request.getParameter("idCode");//修改前账户代码
			out=response.getWriter();
			CollectpayAccountPRMT c=new CollectpayAccountPRMT();
			c.setAccountCode(request.getParameter("accountCode"));
			c.setAccountType(request.getParameter("accountType"));
			c.setAccountName(request.getParameter("accountName"));
			c.setIdCode(idCode);
			collectpayaccountService.updateZH(c);
			out.print(1);
			this.writeJson(bjr);
		} catch (Exception e) {
			out.print(0);
			e.printStackTrace();
		}
	}
}
