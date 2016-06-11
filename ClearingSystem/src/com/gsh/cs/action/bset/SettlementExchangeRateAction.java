package com.gsh.cs.action.bset;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.Interface.BaseJsonResponse;
import com.gsh.cs.model.base.SettlementExchangeRate;
import com.gsh.cs.model.parameter.SettlementExchangeRatePRMT;
import com.gsh.cs.service.bset.SettlementExchangeRateServiceI;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/bset")
@Action(value = "sertAction")
public class SettlementExchangeRateAction extends BaseAction implements ModelDriven<SettlementExchangeRatePRMT> {

	private SettlementExchangeRatePRMT param = new SettlementExchangeRatePRMT();

	public SettlementExchangeRatePRMT getModel() {
		return param;
	}
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	private SettlementExchangeRateServiceI settlementExchangeRateService;

	public SettlementExchangeRateServiceI getSettlementExchangeRateService() {
		return settlementExchangeRateService;
	}

	@Autowired
	public void setSettlementExchangeRateService(SettlementExchangeRateServiceI settlementExchangeRateService) {
		this.settlementExchangeRateService = settlementExchangeRateService;
	}

	public void datagrid() {
		User user=(User)request.getSession().getAttribute("userSession");
		super.writeJson(this.settlementExchangeRateService.find(param,user));
	}
	
	public void findAllSearch(){
		try {
			User user=(User)request.getSession().getAttribute("userSession");
			if(settlementExchangeRateService.find(param,user).getRows().size()>0){
				super.result.setSuccess(true);
				super.result.setObj(settlementExchangeRateService.find(param,user));
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
	 * 添加
	 */
	public void add() {
		try {
			User user=(User)request.getSession().getAttribute("userSession");
			param.setUserId(user.getCid());
			SettlementExchangeRate t = this.settlementExchangeRateService.save(param);
			super.result.setSuccess(true);
			super.result.setMsg("添加成功！");
			super.result.setObj(t);
		} catch (Exception e) {
			super.result.setMsg(e.getMessage());
		}
		super.writeJson();
	}
	
	/**
	 * 修改
	 */
	public void edit() {
		try {
			User user=(User)request.getSession().getAttribute("userSession");
			param.setUserId(user.getCid());
			SettlementExchangeRate t = this.settlementExchangeRateService.edit(param);
			super.result.setSuccess(true);
			super.result.setMsg("修改成功！");
			super.result.setObj(t);
		} catch (Exception e) {
			super.result.setMsg(e.getMessage());
		}
		super.writeJson();
	}
	
	/**
	 * 修改解锁
	 */
	public void updateByLock(){
		try {
			String id=request.getParameter("id");
			String settlementexchangerateLock=request.getParameter("settlementexchangerateLock");
			settlementExchangeRateService.updateByLock(id,settlementexchangerateLock);
			super.result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	/**
	 * 删除
	 */
	public void del(){
		try {
			String id = request.getParameter("id");
			settlementExchangeRateService.del(Integer.parseInt(id));
			super.result.setSuccess(true);
			super.result.setMsg("删除成功！");
		} catch (Exception e) {
			super.result.setSuccess(false);
			super.result.setMsg("删除失败！");
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	public void findBianli(){
		try {
			String code=request.getParameter("code");
			User user=(User)request.getSession().getAttribute("userSession");
			SettlementExchangeRate s = this.settlementExchangeRateService.findBianli(code,user);
			if(s!=null){
				super.result.setSuccess(true);
			}else{
				super.result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	public void findList(){
		try {
			User user=(User)request.getSession().getAttribute("userSession");
			writeJson(settlementExchangeRateService.findList(user.getCid().toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 货币换算
	 */
	public void findCurrency(){
		try {
			String currency=request.getParameter("currency");
			String money = request.getParameter("money");
			BigDecimal bd=new BigDecimal(money); 
			super.result.setSuccess(true);
			super.result.setObj(settlementExchangeRateService.findCurrency(bd, currency));
		} catch (Exception e) {
			super.result.setSuccess(false);
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	public void findSettlementExchangeRate(){
		BaseJsonResponse bjr = new BaseJsonResponse();// 反馈信息对象
		String cid=request.getParameter("cid");
		try {
			bjr.setCode("cs");
			bjr.setFlag(1);
			bjr.setInfo(JSON.toJSONString(settlementExchangeRateService.findList(cid)));
			this.writeJson(bjr);
		} catch (Exception e) {
			bjr.setFlag(0);//失败
			bjr.setCode("");
			bjr.setInfo("");
			this.writeJson(bjr);
			e.printStackTrace();
		}
	}
	
	//给开户公司添加一套完整的货币
	public void saveSoa(){
		try {
			String cid=request.getParameter("cid");
			settlementExchangeRateService.saveSoa(cid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
