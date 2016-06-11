package com.gsh.cs.action.apmgt;

import java.math.BigDecimal;
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

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.Interface.BaseJsonResponse;
import com.gsh.cs.model.Interface.BudgetOrderP;
import com.gsh.cs.model.Interface.SupplierReport;
import com.gsh.cs.model.base.Receivable;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.PaymentBudgetPRMT;
import com.gsh.cs.service.apmgt.PaymentBudgetServiceI;
import com.gsh.cs.service.bsp.DataPermissionServiceI;
import com.gsh.cs.tools.SoaPropertiesUtil;
import com.gsh.cs.tools.httpClient;
import com.opensymphony.xwork2.ModelDriven;
@Namespace("/apmgt")
@Action(value = "paymentBudgetAction")
public class PaymentBudgetAction extends BaseAction implements ModelDriven<PaymentBudgetPRMT> {
	private PaymentBudgetPRMT p=new PaymentBudgetPRMT();
	public PaymentBudgetPRMT getModel() {
		// TODO Auto-generated method stub
		return p;
	}
	@Resource DataPermissionServiceI dataPermissionService;
	@Resource PaymentBudgetServiceI paymentBudgetService;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session=request.getSession();
	public void findAll(){
		try {
			BaseJsonResponse bjr=new BaseJsonResponse();
			String tradeDate = request.getParameter("tradeDate");
			String supplier = request.getParameter("supplier");
			String supplierName = request.getParameter("supplierName");
			Map<String,String> parameters =new HashMap<String,String>();
			parameters.put("tradeDate", tradeDate);
			parameters.put("supplier", supplier);
			parameters.put("supplierName", supplierName);
			String url=SoaPropertiesUtil.soaUrl.getProperty("csYs");
			String json = httpClient.getDoPostResponseDataByURL(url, parameters,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			ArrayList<BudgetOrderP> list = (ArrayList<BudgetOrderP>) JSON
					.parseArray(bjr.getInfo(), BudgetOrderP.class);
			super.writeJson(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 条件查询
	 */
	public void findAllSearch(){
		try {
			Datagrid dg = new Datagrid();
			BaseJsonResponse bjr=new BaseJsonResponse();
			String tradeDate = request.getParameter("tradeDate");
			String supplier = request.getParameter("supplier");
			String supplierName = request.getParameter("supplierName");
			User user=(User)session.getAttribute("userSession");
			Map<String,String> parameters =new HashMap<String,String>();
			String str = dataPermissionService.findAllList(user);
			if(str!=null&&!str.equals("")){
				parameters.put("issuerId", str);
			}else{
				parameters.put("issuerId", "111111111111111111");
			}
			parameters.put("tradeDate", tradeDate);
			parameters.put("supplier", supplier);
			parameters.put("supplierName", supplierName);
			String url=SoaPropertiesUtil.soaUrl.getProperty("csYs");
			String json = httpClient.getDoPostResponseDataByURL(url, parameters,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			ArrayList<BudgetOrderP> list = (ArrayList<BudgetOrderP>) JSON
					.parseArray(bjr.getInfo(), BudgetOrderP.class);
			BigDecimal cost=new BigDecimal(0);
			BigDecimal sale=new BigDecimal(0);
			List li=new ArrayList();
			for(BudgetOrderP r:list){
				cost=cost.add(new BigDecimal(r.getCost()));
				sale=sale.add(new BigDecimal(r.getSale()));
			}
			li.add(cost);
			li.add(sale);
			dg.setRows(list);
			dg.getFooter().add(formatterSum("总计：", (Object[])li.toArray()));
			
			this.result.setSuccess(true);
			this.result.setObj(dg);
		} catch (Exception e) {
			this.result.setSuccess(false);
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	private Map formatterSum(String title, Object[] sum) {
		Map foolter = new HashMap();
		foolter.put("orderNo", title);
		foolter.put("cost", (BigDecimal) sum[0]);
		foolter.put("sale", (BigDecimal) sum[1]);
		return foolter;
	}
	
	//查询供应商产品
	public void findByPurType(){
		BaseJsonResponse bjr=new BaseJsonResponse();
		try {
			Map<String,String> parameters =new HashMap<String,String>();
			String url=SoaPropertiesUtil.soaUrl.getProperty("findByPurType");
			String json = httpClient.getDoPostResponseDataByURL(url, parameters,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			ArrayList<SupplierReport> list = (ArrayList<SupplierReport>) JSON
					.parseArray(bjr.getInfo(), SupplierReport.class);
			super.writeJson(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
