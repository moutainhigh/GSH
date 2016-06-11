package com.gsh.cs.action.base;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gsh.cs.base.User;
import com.gsh.cs.model.easyui.Json;

@ParentPackage("basePackage")
@Namespace("/")
public class BaseAction {

	protected Json result = new Json();

	public Json getResult() {
		return result;
	}

	public void setResult(Json result) {
		this.result = result;
	}

	public User getUser() {
		return (User) ServletActionContext.getRequest().getSession().getAttribute("userSession");
	}

	/**
	 * 将对象转为json 后输出
	 * 
	 * @param object
	 */
	public void writeJson(Object object) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd");
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 输出Json 对象
	 */
	public void writeJson() {
		try {
			String json = JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd");
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 直接输出字符串
	 * 
	 * @param json
	 */
	public void writeJsonString(String json) {
		try {
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将对象转为json 并输出 （禁用循环引用检测）
	 * 
	 * @param object
	 */
	public void writeDisableJson(Object object) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd", SerializerFeature.DisableCircularReferenceDetect);
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
