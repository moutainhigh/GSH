package com.gsh.cs.action.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.base.Initialization;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.Interface.BaseJsonResponse;
import com.gsh.cs.tools.SoaPropertiesUtil;
import com.gsh.cs.tools.httpClient;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "login")
@Results({ @Result(name = "success", location = "/index.jsp") })
public class LoginAction extends BaseAction implements ModelDriven<User> {

	private User user = new User();

	public User getModel() {
		return user;
	}

//	public String login() {
//		HttpServletRequest request = ServletActionContext.getRequest();
//		 try {
//			user.setCid((long)10001);
//			user.setName("唐彩虹");
//			user.setDept(18l);
//			user.setDepts("1,2,3,4,5,18");
//			user.setEmail("caihong.tang@gshts.com");
//			user.setTicket(UUID.randomUUID().toString());
//			request.getSession().setAttribute("USER", user);
//			request.getSession().setAttribute("APPLY_POWWER", Initialization.APPLY_POWWER);
//			request.getSession().setAttribute("CURRENCY_UNIT", Initialization.CURRENCY_UNIT);
//			request.getSession().setAttribute("SETTLEMENT_DAY", Initialization.SETTLEMENT_DAY);
//			return "success";
//			 super.result.setSuccess(true);
//			 super.result.setMsg("登陆成功！");
//		 } catch (Exception e) {
//			 super.result.setSuccess(false);
//			 super.result.setMsg(e.getMessage());
		 	
//		 }
//		 super.writeJson();
//	}
	
	/**
	 * 登录
	 * 
	 * @throws RuntimeException
	 */
	public String login() throws RuntimeException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		BaseJsonResponse bjr = new BaseJsonResponse(); 
		try {
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			Map<String, String> params = new HashMap<String, String>(0);
			params.put("userName", userName);
			params.put("password", password);
			String iurl = SoaPropertiesUtil.soaUrl.getProperty("login");
			String json = httpClient.getDoPostResponseDataByURL(iurl, params,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			if (bjr.getFlag() == 1) {// 返回成功时
				String object = bjr.getInfo();
				System.out.println(object);// 打印获取的json串对象(对象或list集合,此对象自行创建)
				User user = JSON.parseObject(object, User.class);
				session.setAttribute("userSession", user);
				List list = getPang.getPermission(user.getUid()
						.toString());
				session.setAttribute("limitsList", list);
				List<User> users=new ArrayList<User>(0);
				User u=getPang.getManageInfo(user.getUid()
						.toString());
				users.add(u);
				session.setAttribute("USER", users);
				request.getSession().setAttribute("APPLY_POWWER", Initialization.APPLY_POWWER);
				request.getSession().setAttribute("CURRENCY_UNIT", Initialization.CURRENCY_UNIT);
				request.getSession().setAttribute("SETTLEMENT_DAY", Initialization.SETTLEMENT_DAY);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
}
