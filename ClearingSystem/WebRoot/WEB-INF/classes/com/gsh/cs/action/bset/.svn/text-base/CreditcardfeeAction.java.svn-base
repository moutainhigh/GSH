package com.gsh.cs.action.bset;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.CreditcardFee;
import com.gsh.cs.model.parameter.CreditcardFeePRMT;
import com.gsh.cs.service.bset.CreditcardfeeServiceI;
import com.opensymphony.xwork2.ModelDriven;
@Namespace("/bset")
@Action(value = "creditcardfeeServiceAction")
public class CreditcardfeeAction extends BaseAction implements ModelDriven<CreditcardFeePRMT> {
	private CreditcardFeePRMT cp=new CreditcardFeePRMT();
	public CreditcardFeePRMT getModel() {
		// TODO Auto-generated method stub
		return cp;
	}
	@Resource CreditcardfeeServiceI creditcardfeeService;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	
	/**
	 * 查询
	 */
	public void findAll(){
		User user=(User)request.getSession().getAttribute("userSession");
		try {
			writeJson(this.creditcardfeeService.findAll(cp,user));
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	/**
	 * 条件查询
	 */
	public void findAllSearch(){
		User user=(User)request.getSession().getAttribute("userSession");
		try {
			if(this.creditcardfeeService.findAll(cp,user).getRows().size()>0){
				this.result.setSuccess(true);
				this.result.setObj(this.creditcardfeeService.findAll(cp,user));
			}else{
				this.result.setSuccess(false);
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
	public void save(){
		User user=(User)request.getSession().getAttribute("userSession");
		try {
			cp.setUserId(user.getCid());
			CreditcardFee c = creditcardfeeService.save(cp);
			super.result.setSuccess(true);
			super.result.setMsg("新增成功!");
			super.result.setObj(c);
		} catch (Exception e) {
			super.result.setSuccess(false);
			super.result.setMsg("新增失败!");
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	/**
	 * 修改
	 */
	public void update(){
		try {
			CreditcardFee c = creditcardfeeService.update(cp);
			super.result.setSuccess(true);
			super.result.setMsg("修改成功!");
			super.result.setObj(c);
		} catch (Exception e) {
			super.result.setSuccess(false);
			super.result.setMsg("修改失败!");
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	/**
	 * 删除
	 */
	public void delete(){
		try {
			cp.setId(Long.parseLong(request.getParameter("creditCardFeeId")));
			creditcardfeeService.delete(cp);
			super.result.setSuccess(true);
			super.result.setMsg("删除成功!");
		} catch (Exception e) {
			super.result.setSuccess(false);
			super.result.setMsg("删除失败!");
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	/**
	 * 修改解锁
	 */
	public void updateByCreditcardfeeLock(){
		try {
			String id=request.getParameter("creditcardfeeId");
			String accountLock=request.getParameter("creditcardfeeLock");
			creditcardfeeService.updateByCreditcardfeeLock(id,accountLock);
			super.result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	public void findBianli(){
		try {
			String creditCode=request.getParameter("creditCode");
			User user=(User)request.getSession().getAttribute("userSession");
			CreditcardFee c = creditcardfeeService.findBianli(creditCode,user);
			if(c!=null){
				super.result.setSuccess(true);
			}else{
				super.result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}
	
}
