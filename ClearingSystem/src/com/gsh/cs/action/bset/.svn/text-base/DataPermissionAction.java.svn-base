package com.gsh.cs.action.bset;

import java.sql.Timestamp;
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
import com.gsh.cs.log.appender.LogActionAppender;
import com.gsh.cs.model.Interface.BaseJsonResponse;
import com.gsh.cs.model.Interface.CloudCompanyDeptP;
import com.gsh.cs.model.Interface.PortalCompanyUsersP;
import com.gsh.cs.model.base.DataPermission;
import com.gsh.cs.model.parameter.DataPermissionP;
import com.gsh.cs.service.bsp.DataPermissionServiceI;
import com.gsh.cs.tools.SoaPropertiesUtil;
import com.gsh.cs.tools.httpClient;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/bset")
@Action(value = "dataPermissionAction")
public class DataPermissionAction extends BaseAction implements ModelDriven<DataPermissionP> {
	private DataPermissionP dpp=new DataPermissionP();
	public DataPermissionP getModel() {
		return dpp;
	}
	@Resource DataPermissionServiceI dataPermissionService;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session =request.getSession();
	
	//查询数据范围
	public void findAll(){
		try {
			User user=(User)session.getAttribute("userSession");
			writeJson(dataPermissionService.findAll(dpp, user));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save(){
		try {
			User user=(User)session.getAttribute("userSession");
			dpp.setCreateDate( new Timestamp(System.currentTimeMillis()));
			dpp.setCid(user.getCid());
			DataPermission dp = dataPermissionService.save(dpp);
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/bset/dataPermissionAction!save.action", 1, "用户绑定数据范围",
					"用户名称：" + dp.getUname(), 1, dp.getId());// 记录日志
			this.result.setMsg("添加成功");
			this.result.setSuccess(true);
			this.result.setObj(dp);
		} catch (Exception e) {
			this.result.setMsg(e.getMessage());
			this.result.setSuccess(false);
			e.printStackTrace();
		}
		this.writeJson();
	}
	
	public void update(){
		try {
			User user=(User)session.getAttribute("userSession");
			dataPermissionService.update(dpp);
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/bset/dataPermissionAction!update.action", 1, "用户修改数据范围",
					"用户名称：" + dpp.getUname(), 1, dpp.getId());// 记录日志
			this.result.setMsg("修改成功");
			this.result.setSuccess(true);
		} catch (Exception e) {
			this.result.setMsg("修改失败");
			this.result.setSuccess(false);
			e.printStackTrace();
		}
		this.writeJson();
	}
	
	public void delete(){
		try {
			User user=(User)session.getAttribute("userSession");
			String id=request.getParameter("id");
			dataPermissionService.delete(id);
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/bset/dataPermissionAction!delete.action", 1, "用户删除数据范围",
					"用户名称：" + dpp.getUname(), 1, dpp.getId());// 记录日志
			this.result.setMsg("删除成功");
			this.result.setSuccess(true);
		} catch (Exception e) {
			this.result.setMsg("删除失败");
			this.result.setSuccess(false);
		}
		this.writeJson();
	}
	
	
	/**
	 * 根据公司查询部门信息
	 */
	public void findDeptByCid(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		BaseJsonResponse bjr=new BaseJsonResponse();
		try {
			User user=(User)session.getAttribute("userSession");
			Map<String,String> parameters =new HashMap<String,String>();
			parameters.put("cid", user.getCid().toString());
			String url=SoaPropertiesUtil.soaUrl.getProperty("findDeptByCid");
			String json = httpClient.getDoPostResponseDataByURL(url, parameters,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			ArrayList<CloudCompanyDeptP> list = (ArrayList<CloudCompanyDeptP>) JSON
					.parseArray(bjr.getInfo(), CloudCompanyDeptP.class);
			super.writeJson(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//根据部门查询业务员
		public void findUidByDept(){
			try {
				List<PortalCompanyUsersP> pculist=new ArrayList<PortalCompanyUsersP>();
				BaseJsonResponse bjr=new BaseJsonResponse();
				HttpServletRequest request = ServletActionContext.getRequest();
				String dept=request.getParameter("dept");
				if(dept.indexOf(",")!=-1){
					String[] str=dept.split(",");
					for(int i=0;i<str.length;i++){
						Map<String,String> parameters =new HashMap<String,String>();
						parameters.put("dept",str[i]);
						String url=SoaPropertiesUtil.soaUrl.getProperty("findEmpByDept");
						String json = httpClient.getDoPostResponseDataByURL(url, parameters,
								"utf-8", true);
						Gson gson = new Gson();// 串行化Java对象为JSON字符串
						bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
						}.getType());
						if(bjr.getFlag()==1){
							ArrayList<PortalCompanyUsersP> pculist1 = (ArrayList<PortalCompanyUsersP>) JSON
									.parseArray(bjr.getInfo(), PortalCompanyUsersP.class);
							for(PortalCompanyUsersP pcup:pculist1){
								pculist.add(pcup);
							}
						}
					}
					
				}else {
					Map<String,String> parameters =new HashMap<String,String>();
					parameters.put("dept",dept);
					String url=SoaPropertiesUtil.soaUrl.getProperty("findEmpByDept");
					String json = httpClient.getDoPostResponseDataByURL(url, parameters,
							"utf-8", true);
					Gson gson = new Gson();// 串行化Java对象为JSON字符串
					bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
					}.getType());
					if(bjr.getFlag()==1){
						ArrayList<PortalCompanyUsersP> pculist1 = (ArrayList<PortalCompanyUsersP>) JSON
								.parseArray(bjr.getInfo(), PortalCompanyUsersP.class);
						for(PortalCompanyUsersP pcup:pculist1){
							pculist.add(pcup);
						}
					}
					
				}
				System.out.println(pculist);
				super.writeJson(pculist);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//根据部门查询业务员
		public void findUidByDeptString(){
			try {
				List<PortalCompanyUsersP> pculist=new ArrayList<PortalCompanyUsersP>();
				BaseJsonResponse bjr=new BaseJsonResponse();
				HttpServletRequest request = ServletActionContext.getRequest();
				String dept=request.getParameter("dept");
				if(dept.indexOf(",")!=-1){
					String[] str=dept.split(",");
					for(int i=0;i<str.length;i++){
						Map<String,String> parameters =new HashMap<String,String>();
						parameters.put("dept",str[i]);
						String url=SoaPropertiesUtil.soaUrl.getProperty("findEmpByDept");
						String json = httpClient.getDoPostResponseDataByURL(url, parameters,
								"utf-8", true);
						Gson gson = new Gson();// 串行化Java对象为JSON字符串
						bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
						}.getType());
						if(bjr.getFlag()==1){
							ArrayList<PortalCompanyUsersP> pculist1 = (ArrayList<PortalCompanyUsersP>) JSON
									.parseArray(bjr.getInfo(), PortalCompanyUsersP.class);
							for(PortalCompanyUsersP pcup:pculist1){
								pculist.add(pcup);
							}
						}
					}
					
				}else {
					Map<String,String> parameters =new HashMap<String,String>();
					parameters.put("dept",dept);
					String url=SoaPropertiesUtil.soaUrl.getProperty("findEmpByDept");
					String json = httpClient.getDoPostResponseDataByURL(url, parameters,
							"utf-8", true);
					Gson gson = new Gson();// 串行化Java对象为JSON字符串
					bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
					}.getType());
					if(bjr.getFlag()==1){
						ArrayList<PortalCompanyUsersP> pculist1 = (ArrayList<PortalCompanyUsersP>) JSON
								.parseArray(bjr.getInfo(), PortalCompanyUsersP.class);
						for(PortalCompanyUsersP pcup:pculist1){
							pculist.add(pcup);
						}
					}
					
				}
				String idName="";
				String name="";
				for(PortalCompanyUsersP pcup:pculist){
					if(!idName.equals("")){
						idName+=",";
					}
					if(!name.equals("")){
						name+=",";
					}
					idName+=pcup.getUid();
					name+=pcup.getName();
				}
				super.result.setObj(idName);
				super.result.setMsg(name);
				super.result.setSuccess(true);
			} catch (Exception e) {
				super.result.setSuccess(false);
				e.printStackTrace();
			}
			super.writeJson();
		}
		
		//查询结算应收应付登录人
		public void findCsUser(){
			try {
				BaseJsonResponse bjr=new BaseJsonResponse();
				User user=(User)session.getAttribute("userSession");
				Map<String,String> parameters =new HashMap<String,String>();
				parameters.put("cid",user.getCid().toString());
				String url=SoaPropertiesUtil.soaUrl.getProperty("findCsYsYfUser");
				String json = httpClient.getDoPostResponseDataByURL(url, parameters,
						"utf-8", true);
				Gson gson = new Gson();// 串行化Java对象为JSON字符串
				bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
				}.getType());
				if(bjr.getFlag()==1){
					ArrayList<User> userlist = (ArrayList<User>) JSON
							.parseArray(bjr.getInfo(), User.class);
					super.writeJson(userlist);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	
}
