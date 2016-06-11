package com.gsh.cs.action.bsp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.alibaba.fastjson.JSON;
import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.Interface.BaseJsonResponse;
import com.gsh.cs.model.base.BaspTicketUse;
import com.gsh.cs.model.base.Payable;
import com.gsh.cs.model.parameter.BaspTicketUsePRMT;
import com.gsh.cs.service.bsp.BaspTicketUseServiceI;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.tools.internal.ws.processor.model.Request;

@Namespace("/bsp")
@Action(value = "baspTicketUseAction")
public class BaspTicketUseAction extends BaseAction implements ModelDriven<BaspTicketUsePRMT>  {
	private BaspTicketUsePRMT param = new BaspTicketUsePRMT();
	public BaspTicketUsePRMT getModel() {
		return param;
	}
	@Resource BaspTicketUseServiceI baspTicketUseService;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	/**
	 * 查询国内BSP票号使用情况
	 */
	public void findGuonei(){
		try {
			User user=(User)request.getSession().getAttribute("userSession");
			String type=request.getParameter("type");
			String date=request.getParameter("date");
			if(type!=null&&!type.equals("")){
				param.setType(type);
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			try {
				if(date!=null&&!date.equals("")){
					param.setDrawerDay(df.parse(date));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			super.writeJson(this.baspTicketUseService.findGuonei(param,user));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询国际BSP票号使用情况
	 */
	public void findGuoji(){
		try {
			User user=(User)request.getSession().getAttribute("userSession");
			super.writeJson(this.baspTicketUseService.findGuoji(param,user));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 订单中心调用接口，存储使用票号的数据
	 */
	public void saveBaspTicketUse(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			String baspTicketUse ="["+ request.getParameter("baspTicketUse")+"]";
			ArrayList<BaspTicketUse> btuli = (ArrayList<BaspTicketUse>) JSON
					.parseArray(baspTicketUse, BaspTicketUse.class);
			String cid=request.getParameter("cid");
			for(BaspTicketUse btu:btuli){
				btu.setCreater("唐彩红");
				btu.setCreaterNo(Long.parseLong(cid));
				baspTicketUseService.save(btu);
			}
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
	
	//查询票号是否使用过
	public void findBspTicketUse(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		int falg=1;
		try {
			//先查询结算所有使用的票号
			String baspTicketUse =request.getParameter("btulist");
			ArrayList<BaspTicketUse> btuli = (ArrayList<BaspTicketUse>) JSON
					.parseArray(baspTicketUse, BaspTicketUse.class);
			for(BaspTicketUse btu:btuli){
				if(falg==0){
					break;
				}
				List<BaspTicketUse> btulist = baspTicketUseService.findAll(btu.getType().subSequence(0, 1).toString(),btu.getCreaterNo().toString());
				if(btulist.size()>0){
					for(BaspTicketUse btuse:btulist){
						if(btu.getAirlineCompany().equals(btuse.getAirlineCompany())&&btu.getTicketNo().equals(btuse.getTicketNo())){
							falg=0;
							break;
						}
					}
				}
				
			}
			if(falg==0){
				bjr.setFlag(0);//失败
				bjr.setCode("");
				bjr.setInfo("");
				this.writeJson(bjr);
			}else{
				bjr.setFlag(1);//成功
				bjr.setCode("");
				bjr.setInfo("");
				this.writeJson(bjr);
			}
		} catch (Exception e) {
			bjr.setFlag(0);//失败
			bjr.setCode("");
			bjr.setInfo("");
			this.writeJson(bjr);
			e.printStackTrace();
		}
		
	}
	
	//修改票号使用记录的状态
	public void updateBank(){
		BaseJsonResponse bjr = new BaseJsonResponse();//反馈信息对象
		try {
			String airlineCompany=request.getParameter("airlineCompany");
			String ticketNo=request.getParameter("ticketNo");
			String type=request.getParameter("type");
			String typeTgq=request.getParameter("typeTgq");
			BaspTicketUse btu = baspTicketUseService.updateBank(airlineCompany, ticketNo, type, typeTgq);
			if(btu==null){
				bjr.setFlag(0);//失败
				bjr.setCode("");
				bjr.setInfo("");
				this.writeJson(bjr);
			}else{
				bjr.setFlag(1);//成功
				bjr.setCode("");
				bjr.setInfo("");
				this.writeJson(bjr);
			}
		} catch (Exception e) {
			bjr.setFlag(0);//失败
			bjr.setCode("");
			bjr.setInfo("");
			this.writeJson(bjr);
			e.printStackTrace();
		}
	}
}
