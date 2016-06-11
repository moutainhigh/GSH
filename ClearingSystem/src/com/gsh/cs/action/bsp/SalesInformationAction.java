package com.gsh.cs.action.bsp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.alibaba.fastjson.JSON;
import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.model.Interface.CreateByHand;
import com.gsh.cs.model.base.SalesInformation;
import com.gsh.cs.model.parameter.SalesInformationPRMT;
import com.gsh.cs.service.bsp.SalesInformationServiceI;
import com.gsh.cs.tools.SoaPropertiesUtil;
import com.gsh.cs.tools.httpClient;
import com.opensymphony.xwork2.ModelDriven;
@Namespace("/bsp")
@Action(value = "salesInformationAction")
public class SalesInformationAction extends BaseAction implements ModelDriven<SalesInformationPRMT> {
	private SalesInformationPRMT sip=new SalesInformationPRMT();
	public SalesInformationPRMT getModel() {
		return sip;
	}
	@Resource SalesInformationServiceI salesInformationService;
	HttpServletRequest request=ServletActionContext.getRequest();
	public void findAll(){
		try {
			this.writeJson(salesInformationService.findAll(sip));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveCreateByHand(){
		try {
			String createByHand="["+request.getParameter("createByHand")+"]";
			List<CreateByHand> cblist =JSON
					.parseArray(createByHand, CreateByHand.class);
			for(CreateByHand cb:cblist){
				salesInformationService.saveCreateByHand(cb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(){
		try {
			SalesInformation si = salesInformationService.update(sip);
			//推送数据到订单中心 getXzth
			//调订单中心接口，推送销账信息到订单中心
			Map<String, String> params = new HashMap<String, String>(0);
			params.put("salesInformation", JSON.toJSONString(si));
			String url=SoaPropertiesUtil.soaUrl.getProperty("getXzth");
			String json = httpClient.getDoPostResponseDataByURL(url, params,
					"utf-8", true);
			super.result.setSuccess(true);
			super.result.setMsg("修改成功！");
			super.result.setObj(si);
		} catch (Exception e) {
			super.result.setMsg(e.getMessage());
		}
		super.writeJson();
	}
	
}
