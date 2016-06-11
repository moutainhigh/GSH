package com.gsh.cs.action.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.Interface.BaseJsonResponse;
import com.gsh.cs.tools.SoaPropertiesUtil;
import com.gsh.cs.tools.httpClient;

public class getPang {
	/**
	 * 获取权限
	 * 
	 * @return
	 */
	public static List<Object> getPermission(String uid) {
		BaseJsonResponse bjr = new BaseJsonResponse();// 反馈信息对象
		List<Object> pCode = null;
		try {
			Map<String, String> params = new HashMap<String, String>(0);
			params.put("uid", uid);
			String url = SoaPropertiesUtil.soaUrl.getProperty("getPermission");
			String json = httpClient.getDoPostResponseDataByURL(url, params,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			if (bjr.getFlag() == 1) {// 返回成功时
				String object = bjr.getInfo();
				System.out.println(object);// 打印获取的json串对象(对象或list集合,此对象自行创建)
				// 执行操作。。。
				pCode = JSON.parseArray(object);
				for (int i = 0; i < pCode.size(); i++) {
					System.out.println(pCode.get(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pCode;
	}
	
	/**
	 * 获取当前登陆人的组织架构组长
	 * 
	 * @param uid
	 * @return
	 */
	public static User getManageInfo(String uid) {
		BaseJsonResponse bjr = new BaseJsonResponse();// 反馈信息对象
		User user = null;
		try {
			Map<String, String> params = new HashMap<String, String>(0);
			params.put("uid", uid);
			String url = SoaPropertiesUtil.soaUrl.getProperty("findManageInfo");
			String json = httpClient.getDoPostResponseDataByURL(url, params,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			if (bjr.getFlag() == 1) {// 返回成功时
				String object = bjr.getInfo();
				System.out.println(object);// 打印获取的json串对象(对象或list集合,此对象自行创建)
				// 执行操作。。。
				user = JSON.parseObject(object, User.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * 通过用户id获取用户信息
	 * 
	 * @param uid
	 * @return
	 */
	public static User getUserInfo(String uid) {
		BaseJsonResponse bjr = new BaseJsonResponse();// 反馈信息对象
		User user = null;
		try {
			Map<String, String> params = new HashMap<String, String>(0);
			params.put("uid", uid);
			String url = SoaPropertiesUtil.soaUrl.getProperty("userInfo");
			String json = httpClient.getDoPostResponseDataByURL(url, params,
					"utf-8", true);
			Gson gson = new Gson();// 串行化Java对象为JSON字符串
			bjr = gson.fromJson(json, new TypeToken<BaseJsonResponse>() {
			}.getType());
			if (bjr.getFlag() == 1) {// 返回成功时
				String object = bjr.getInfo();
				System.out.println(object);// 打印获取的json串对象(对象或list集合,此对象自行创建)
				// 执行操作。。。
				user = JSON.parseObject(object, User.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
