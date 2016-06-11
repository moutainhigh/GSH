package com.gsh.cs.action.bset;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.Account;
import com.gsh.cs.model.parameter.AccountPRMT;
import com.gsh.cs.service.bset.AccountServiceI;
import com.gsh.cs.log.appender.LogActionAppender;
import com.opensymphony.xwork2.ModelDriven;
@Namespace("/bset")
@Action(value = "accountAction")
public class AccountAction extends BaseAction implements ModelDriven<AccountPRMT> {
	private AccountPRMT ap=new AccountPRMT();
	public AccountPRMT getModel() {
		return ap;
	}
	@Resource AccountServiceI accountService;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session =request.getSession();
	
	/**
	 * 查询
	 */
	public void findAll(){
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("userSession");
		try {
			writeJson(this.accountService.findAll(ap,user));
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	/**
	 * 条件查询
	 */
	public void findAllSearch(){
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("userSession");
		try {
			if(this.accountService.findAll(ap,user).getRows().size()>0){
				this.result.setSuccess(true);
				this.result.setObj(this.accountService.findAll(ap,user));
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
		try {
			HttpSession session=request.getSession();
			User user=(User)session.getAttribute("userSession");
			ap.setUserId(user.getCid());
			if(ap.getAccountStatus()==1){
				ap.setAccountLock(0);
			}else{
				ap.setAccountLock(1);
			}
			ap.setDeptId(user.getDept().toString());
			ap.setAdminId((long)0);
			Account a = accountService.save(ap);
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/bset/accountAction!save.action", 1, "新增资金账户",
					"资金账户编码：" + a.getAccountCode(), 1, a.getId());// 记录日志
			super.result.setSuccess(true);
			super.result.setMsg("新增成功!");
			super.result.setObj(a);
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
			HttpSession session=request.getSession();
			User user=(User)session.getAttribute("userSession");
			Account a1 = accountService.update(ap);
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/bset/accountAction!update.action", 1, "修改资金账户",
					"资金账户编码：" + a1.getAccountCode(), 1, a1.getId());// 记录日志
			super.result.setSuccess(true);
			super.result.setMsg("修改成功!");
			super.result.setObj(a1);
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
			HttpSession session=request.getSession();
			User user=(User)session.getAttribute("userSession");
			ap.setId(Long.parseLong(request.getParameter("accountId")));
			accountService.delete(ap);
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/bset/accountAction!delete.action", 1, "删除资金账户",
					"资金账户编码：" + ap.getAccountCode(), 1, ap.getId());// 记录日志
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
	 * 修改激活、挂起、解挂、注销状态
	 */
	public void updateByStatus(){
		try {
			HttpSession session=request.getSession();
			User user=(User)session.getAttribute("userSession");
			String id=request.getParameter("accountId");
			String status=request.getParameter("accountStatus");
			accountService.updateByStatus(id, status);
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/bset/accountAction!updateByStatus.action", 1, "修改激活、挂起、解挂、注销资金账户状态",
					"资金账户编码：" + ap.getAccountCode(), 1, ap.getId());// 记录日志
			super.result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	/**
	 * 修改解锁
	 */
	public void updateByaccountLock(){
		try {
			HttpSession session=request.getSession();
			User user=(User)session.getAttribute("userSession");
			String id=request.getParameter("accountId");
			String accountLock=request.getParameter("accountLock");
			accountService.updateByaccountLock(id,accountLock);
			LogActionAppender.logRecord(user.getUid(), user.getCid(),
					"/bset/accountAction!updateByaccountLock.action", 1, "修改资金账户解锁状态",
					"资金账户编码：" + ap.getAccountCode(), 1, ap.getId());// 记录日志
			super.result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	/**
	 * 判断唯一
	 */
	public void findBianli(){
		try {
			String accountCode=request.getParameter("accountCode");
			Account a = accountService.findBianli(accountCode);
			if(a!=null){
				super.result.setSuccess(true);
			}else{
				super.result.setSuccess(false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	public void findAllList(){
		try {
			User user=(User)request.getSession().getAttribute("userSession");
			writeJson(this.accountService.findAllList(user));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询除本身账户之外的其他账户
	 */
	public void findAccount(){
		try {
			String code=request.getParameter("code");
			String accountCurrency=request.getParameter("accountCurrency");
			writeJson(this.accountService.findAccount(code,accountCurrency));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//查询人命币的默认收款账户
	public void findByDefaultIncAccount(){
		try {
			super.result.setObj(this.accountService.findByDefaultIncAccount());
			super.result.setSuccess(true);
		} catch (Exception e) {
			super.result.setSuccess(false);
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	public void findAllListReport(){
		try {
			User user=new User();
			String cid=request.getParameter("cid");
			user.setCid(Long.parseLong(cid));
			writeJson(this.accountService.findAllList(user));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
