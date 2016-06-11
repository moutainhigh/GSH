package com.gsh.cs.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gsh.cs.action.login.ResultInfo;
import com.gsh.cs.action.login.getPang;
import com.gsh.cs.base.Initialization;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.Interface.BaseJsonResponse;
import com.gsh.cs.tools.SoaPropertiesUtil;
import com.gsh.cs.tools.httpClient;

public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	/**
	 * 启用filter
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		System.out.println("########orderFilter########");
		String url = httpRequest.getRequestURI();
		BaseJsonResponse bjr = new BaseJsonResponse();
		List<User> users = new ArrayList<User>();
		// 首页/order路径过滤
		if (url.equals("/cs/") || url.equals("/cs/index.jsp")) {
			String ticket = request.getParameter("ticket");
			String uid = request.getParameter("uid");
			Map<String, String> params = new HashMap<String, String>(0);
			params.put("ticket", ticket);
			params.put("uid", uid);
			String iurl = SoaPropertiesUtil.soaUrl.getProperty("checkTicket");
			String json = httpClient.getDoPostResponseDataByURL(iurl, params,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			if (bjr.getFlag() == 1) {// 返回成功时
				String object = bjr.getInfo();
				System.out.println(object);// 打印获取的json串对象(对象或list集合,此对象自行创建)
				ResultInfo info = JSON.parseObject(object, ResultInfo.class);
				if (info != null) {
					if (info.getResult().equals("success")) {
						User user = getPang.getUserInfo(info.getUid());
						if (user != null) {
							user.setTicket(ticket);
							session.setAttribute("userSession", user);
							if (session.getAttribute("limitsList") == null) {
								List list1 = getPang.getPermission(user
										.getUid().toString());
								if (list1.size() > 0) {
									session.setAttribute("limitsList", list1);
								}
							}
							if (session.getAttribute("deptSession") == null) {
								User u = getPang.getManageInfo(user.getUid()
										.toString());
								users.add(u);
								if (users.size() > 0) {
									session.setAttribute("deptSession", users);
								}
							}
							session.setAttribute("APPLY_POWWER", Initialization.APPLY_POWWER);
							session.setAttribute("CURRENCY_UNIT", Initialization.CURRENCY_UNIT);
							session.setAttribute("SETTLEMENT_DAY", Initialization.SETTLEMENT_DAY);
							chain.doFilter(request, response);
							return;
						}
					} else {
						session.invalidate();
						System.out.println("action 失效");
						return;
					}
				}
			} else {
				session.invalidate();
				System.out.println("action 失效");
				return;
			}
		}
		if (session.getAttribute("userSession") != null) {
			System.out.println("action 存在");
			chain.doFilter(request, response);
			return;
		} else {
			System.out.println("action 失效");
			session.invalidate();
			return;
		}
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
