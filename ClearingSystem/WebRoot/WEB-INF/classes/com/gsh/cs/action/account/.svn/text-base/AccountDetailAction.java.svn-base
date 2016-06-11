package com.gsh.cs.action.account;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.parameter.AccountDetailPRMT;
import com.gsh.cs.service.account.AccountDetailServiceI;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.tools.internal.ws.processor.model.Request;
@Namespace("/account")
@Action(value = "accountDetailAction")
public class AccountDetailAction extends BaseAction implements ModelDriven<AccountDetailPRMT> {
	private AccountDetailPRMT ap=new AccountDetailPRMT();
	public AccountDetailPRMT getModel() {
		// TODO Auto-generated method stub
		return ap;
	}
	@Resource AccountDetailServiceI accountDetailService;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	/**
	 * 查询应收应付记录
	 * @return
	 */
	public void findCollectPay(){
		try {
			User user=(User)request.getSession().getAttribute("userSession");
			if(accountDetailService.findCollectPay(ap,user).getRows().size()>0){
				if(ap.getType()==null){
					writeJson(accountDetailService.findCollectPay(ap,user));
				}else{
					super.result.setSuccess(true);
					super.result.setObj(accountDetailService.findCollectPay(ap,user));
				}
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
	 * 查询账目需要复合的记录
	 * @return
	 */
	public void findToReview(){
//		try {
//			if(accountDetailService.findToReview(ap).getRows().size()>0){
//				super.result.setSuccess(true);
//				super.result.setObj();
//			}else{
//				super.result.setSuccess(false);
//				super.result.setMsg("未查询到任何记录");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		try {
			User user=(User)request.getSession().getAttribute("userSession");
			String accountCode=request.getParameter("accountCode");
			String voucherNo=request.getParameter("voucherNo");
			String qiDate=request.getParameter("qiDate");
			String shiDate=request.getParameter("shiDate");
			
			ap.setAccountCode(accountCode);
			ap.setVoucherNo(voucherNo);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			
			if(qiDate!=null&&!qiDate.equals("")){
				ap.setQiDate(df.parse(qiDate));
			}
			if(shiDate!=null&&!shiDate.equals("")){
				ap.setShiDate(df.parse(shiDate));
			}
			if(ap.getPage()==0){
				ap.setPage(1);
				ap.setRows(10);
			}
			super.writeJson(accountDetailService.findToReview(ap,user));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 复核
	 */
	public void AccountDetailToreview(){
		try {
			String id=request.getParameter("id");
			accountDetailService.AccountDetailToreview(id);
			super.result.setSuccess(true);
			super.result.setMsg("复核成功");
		} catch (Exception e) {
			super.result.setSuccess(false);
			super.result.setMsg("复核失败");
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	/**
	 * 驳回
	 */
	public void AccountDetailReject(){
		try {
			String id=request.getParameter("id");
			String type=request.getParameter("type");
			String[] id_str=id.split(",");
			String[] type_str=type.split(",");
			for(int i=0;i<type_str.length;i++){
				if(type_str[i].equals("1")){
					accountDetailService.receivableReject(id_str[i]);//应收
				}else if(type_str[i].equals("2")){
					accountDetailService.payableReject(id_str[i]);//应付
				}else if(type_str[i].equals("3")){
					accountDetailService.commissionReject(id_str[i]);//佣金
				}else if(type_str[i].equals("4")){
					accountDetailService.zhuanZhangReject(id_str[i]);//转账
				}
			}
			super.result.setSuccess(true);
			super.result.setMsg("驳回成功");
		} catch (Exception e) {
			super.result.setSuccess(false);
			super.result.setMsg("驳回失败");
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	/**
	 * 查询账目明细
	 */
	public void AccountMingxi(){
		try {
			String id=request.getParameter("id");
			String type=request.getParameter("type");
			String[] id_str=id.split(",");
			String[] type_str=type.split(",");
			for(int i=0;i<type_str.length;i++){
				if(type_str[i].equals("1")){
					//应收
					writeJson(accountDetailService.receivableMingxi(id));
				}else if(type_str[i].equals("2")){
					writeJson(accountDetailService.payableMingxi(id));//应付
				}else if(type_str[i].equals("3")){
					//佣金
					writeJson(accountDetailService.commissionMingxi(id));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 转账汇款
	 */
	public void zhuanZhang(){
		try {
			User user=(User)request.getSession().getAttribute("userSession");
			accountDetailService.zhuanZhang(ap,user);
			super.result.setSuccess(true);
			super.result.setMsg("转账成功");
		} catch (Exception e) {
			super.result.setSuccess(false);
			super.result.setMsg("转账失败");
			e.printStackTrace();
		}
		super.writeJson();
	}
}
