package com.gsh.cs.action.apmgt;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.parameter.PayableViewPRMT;
import com.gsh.cs.service.apmgt.PayableViewServiceI;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/apmgt")
@Action(value = "payableViewAction")
public class PayableViewAction extends BaseAction implements ModelDriven<PayableViewPRMT> {
	private PayableViewPRMT pp = new PayableViewPRMT();

	public PayableViewPRMT getModel() {
		// TODO Auto-generated method stub
		return pp;
	}

	@Resource
	PayableViewServiceI payableViewService;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session=request.getSession();

	public void findAll() {
			String supplierNo=request.getParameter("supplierNo");
			String exchangeNo=request.getParameter("exchangeNo");
			String tradeDateQi=request.getParameter("tradeDateQi");
			String tradeDateShi=request.getParameter("tradeDateShi");
			String orderNo=request.getParameter("orderNo");
			String affiliationNo=request.getParameter("affiliationNo");
			String deadlineShi=request.getParameter("deadlineShi");
			String productNo=request.getParameter("productNo");
			String counterFee=request.getParameter("counterFee");
			String voucherNo=request.getParameter("voucherNo");
			User user=(User)session.getAttribute("userSession");
			
			pp.setSupplierNo(supplierNo);
			pp.setExchangeNo(exchangeNo);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			try {
				if(tradeDateQi!=null&&!tradeDateQi.equals("")){
					pp.setTradeDateQi(df.parse(tradeDateQi));
				}
				if(tradeDateShi!=null&&!tradeDateShi.equals("")){
					pp.setTradeDateShi(df.parse(tradeDateShi));
				}
				if(deadlineShi!=null&&!deadlineShi.equals("")){
					pp.setDeadlineShi(df.parse(deadlineShi));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			pp.setOrderNo(orderNo);
			if(affiliationNo!=null&&!affiliationNo.equals("")){
				pp.setAffiliationNo(Long.parseLong(affiliationNo));
			}
			pp.setProductNo(productNo);
			pp.setVoucherNo(voucherNo);
			if(counterFee!=null&&!counterFee.equals("")){
				pp.setCounterFee(Integer.parseInt(counterFee));
			}
			
			if(pp.getPage()==0){
				pp.setPage(1);
				pp.setRows(10);
			}
			super.writeJson(this.payableViewService.findAll(pp,user));
	}

	public void findSearch() {
		try {
			User user=(User)session.getAttribute("userSession");
			if (this.payableViewService.findAll(pp,user).getRows().size() > 0) {
				super.result.setSuccess(true);
				super.result.setObj(this.payableViewService.findAll(pp,user));
			} else {
				super.result.setSuccess(false);
				super.result.setMsg("未查询到任何记录");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}

	public void list() {
		super.writeJson(this.payableViewService.list(pp));
	}
	
	/**
	 * 查询暂支款
	 */
	public void findZhanzhi() {
		try {
			User user=(User)session.getAttribute("userSession");
			String status=request.getParameter("status");
			super.writeJson(this.payableViewService.findZhanzhi(pp,user,status));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 条件查询暂支款
	 */
	public void findZhanzhiSearch() {
		try {
			User user=(User)session.getAttribute("userSession");
			String status=request.getParameter("status");
			if(payableViewService.findZhanzhi(pp,user,status).getRows().size()>0){
				super.result.setSuccess(true);
				super.result.setObj(payableViewService.findZhanzhi(pp,user,status));
			}else{
				super.result.setSuccess(false);
				super.result.setMsg("未查询到任何记录");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}
}
