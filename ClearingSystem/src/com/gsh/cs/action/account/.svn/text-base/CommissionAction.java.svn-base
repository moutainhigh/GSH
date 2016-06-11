package com.gsh.cs.action.account;

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
import com.gsh.cs.base.MessageException;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.Interface.BaseJsonResponse;
import com.gsh.cs.model.Interface.RouteProducts;
import com.gsh.cs.model.Interface.RouteProductsP;
import com.gsh.cs.model.base.Commission;
import com.gsh.cs.model.base.CommissionDetail;
import com.gsh.cs.model.base.CommissionPayment;
import com.gsh.cs.model.base.CustomerPayment;
import com.gsh.cs.model.base.Payable;
import com.gsh.cs.model.base.ReceivableDetail;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.CommissionPRMT;
import com.gsh.cs.service.account.CommissionServiceI;
import com.gsh.cs.tools.SoaPropertiesUtil;
import com.gsh.cs.tools.httpClient;
import com.opensymphony.xwork2.ModelDriven;
@Namespace("/account")
@Action(value = "commissionAction")
public class CommissionAction extends BaseAction implements ModelDriven<CommissionPRMT> {
	private CommissionPRMT cp=new CommissionPRMT();
	public CommissionPRMT getModel() {
		// TODO Auto-generated method stub
		return cp;
	}
	@Resource CommissionServiceI commissionService;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session=request.getSession();
	
	public void findAll(){
		try {
			User user=(User)session.getAttribute("userSession");
			writeJson(commissionService.findAll(cp,user));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void findAllSearch(){
		try {
			User user=(User)session.getAttribute("userSession");
			if(commissionService.findAll(cp,user).getRows().size()>0){
				super.result.setSuccess(true);
				super.result.setObj(commissionService.findAll(cp,user));
			}else{
				super.result.setSuccess(false);
				super.result.setMsg("未查询到任何记录");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	public void payment(){
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			String str=request.getParameter("form");
			System.out.println(str);
			CommissionPayment cp = JSON.parseObject(request.getParameter("form"), CommissionPayment.class);
			cp.setHandNo(super.getUser().getCid());
			cp.setHandPerson(super.getUser().getName());
			List<CommissionDetail> rdl = JSON.parseArray(request.getParameter("detail"), CommissionDetail.class);
			this.commissionService.payment(cp, rdl);
			super.result.setSuccess(true);
			super.result.setMsg("收入成功！");
		} catch (MessageException e) {
			super.result.setMsg(e.getMessage());
		} catch (Exception e) {
			super.result.setMsg(e.getMessage());
		}
		super.writeJson();
	}
	
	/**
	 * 查询产品航线
	 */
	public void findProductRoute(){
		try {
			super.writeJson(this.commissionService.findProductRoute());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询供应商编号
	 */
	public void findsupplierNo(){
		try {
			super.writeJson(this.commissionService.findsupplierNo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询承运商
	 */
	public void findcarrier(){
		try {
			super.writeJson(this.commissionService.findcarrier());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void AddCommission(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			String json=request.getParameter("commission");
			ArrayList<Commission> pblist = (ArrayList<Commission>) JSON
					.parseArray(json, Commission.class);
			for(Commission c:pblist){
//				c.setCreater("唐彩红");
//				c.setCreaterNo((long)10001);
				commissionService.save(c);
			}
//			rblist.get(0).setCreater(super.getUser().getName());
//			rblist.get(0).setCreaterNo(super.getUser().getCid());
//			pblist.get(0).setCreater("唐彩红");
//			pblist.get(0).setCreaterNo((long)10001);
//			commissionService.save(pblist.get(0));
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
	 * 获取航线产品routeProductsById
	 */
	public void findRouteProducts(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			String id=request.getParameter("id");
			Map<String, String> params = new HashMap<String, String>(0);
			params.put("id", id);
			String url=SoaPropertiesUtil.soaUrl.getProperty("routeProductsById");
			String json = httpClient.getDoPostResponseDataByURL(url, params,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			ArrayList<RouteProducts> rplist = (ArrayList<RouteProducts>) JSON
					.parseArray("["+bjr.getInfo()+"]", RouteProducts.class);
			super.writeJson(rplist);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取云平台的航线产品架构findAllRouteProducts
	 */
	public void findAllRouteProducts(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			User user=(User)request.getSession().getAttribute("userSession");
			Map<String, String> params = new HashMap<String, String>(0);
			params.put("cid", user.getCid().toString());
			String url=SoaPropertiesUtil.soaUrl.getProperty("findAllRouteProducts");
			String json = httpClient.getDoPostResponseDataByURL(url, params,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			ArrayList<RouteProductsP> rplist = (ArrayList<RouteProductsP>) JSON
					.parseArray(bjr.getInfo(), RouteProductsP.class);
			super.writeJson(rplist);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
