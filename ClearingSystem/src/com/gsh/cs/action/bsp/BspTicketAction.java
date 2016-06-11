package com.gsh.cs.action.bsp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.Interface.BaseJsonResponse;
import com.gsh.cs.model.base.BspTicket;
import com.gsh.cs.model.parameter.BspTicketPRMT;
import com.gsh.cs.service.bsp.BspTicketServiceI;
import com.gsh.cs.tools.Utils;
import com.opensymphony.xwork2.ModelDriven;
@Namespace("/bsp")
@Action(value = "bspTicketAction")
public class BspTicketAction extends BaseAction implements ModelDriven<BspTicketPRMT> {
	private BspTicketPRMT bp=new BspTicketPRMT();
	public BspTicketPRMT getModel() {
		// TODO Auto-generated method stub
		return bp;
	}
	@Resource BspTicketServiceI bspTicketService;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	/**
	 * 查询
	 */
	public void findAll(){
		try {
			User user=(User)request.getSession().getAttribute("userSession");
			writeJson(this.bspTicketService.findAll(bp,user));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void findAllSearch(){
		try {
			User user=(User)request.getSession().getAttribute("userSession");
			if(this.bspTicketService.findAll(bp,user).getRows().size()>0){
				super.result.setSuccess(true);
				super.result.setObj(this.bspTicketService.findAll(bp,user));
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
	 * 新增
	 */
	public void save(){
		try {
			User user=(User)request.getSession().getAttribute("userSession");
			bp.setUserId(user.getCid());
			BspTicket b = this.bspTicketService.save(bp);
			this.result.setSuccess(true);
			this.result.setMsg("添加成功");
			this.result.setObj(b);
		} catch (Exception e) {
			this.result.setSuccess(false);
			this.result.setMsg("添加失败");
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	public void quPiao(){
		BaseJsonResponse bjr=new BaseJsonResponse();
		try {
			String cid=request.getParameter("cid");
			String type=request.getParameter("type");//类型，国际、国内机票
			String piaohao=request.getParameter("piaohao");
			String bank = this.bspTicketService.quPiao(type,piaohao,cid);
			if(Utils.isNullOrEmpty(bank)){
				bjr.setInfo("无匹配票号");
				bjr.setFlag(0);
				this.writeJson(bjr);
			}else{
				bjr.setInfo(bank);
				bjr.setFlag(1);
				this.writeJson(bjr);
			}
		} catch (Exception e) {
			bjr.setInfo("报错");
			bjr.setFlag(0);
			this.writeJson(bjr);
			e.printStackTrace();
		}
	}
	
	//检索票号的唯一性
	public void tickedWeiyi(){
		try {
			User user=(User)request.getSession().getAttribute("userSession");
			String piaohao=request.getParameter("piaohao");
			boolean b=bspTicketService.tickedWeiyi(piaohao,user);
			if(b){
				super.result.setObj(1);
			}else{
				super.result.setObj(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}
}
