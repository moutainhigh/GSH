package com.gsh.cs.action.armgt;

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
import com.gsh.cs.model.parameter.ReceivableViewPRMT;
import com.gsh.cs.service.armgt.ReceivableServiceI;
import com.gsh.cs.service.armgt.ReceivableViewServiceI;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/armgt")
@Action(value = "receivableViewAction")
public class ReceivableViewAction extends BaseAction implements ModelDriven<ReceivableViewPRMT> {
	private ReceivableViewPRMT rp = new ReceivableViewPRMT();

	public ReceivableViewPRMT getModel() {
		return rp;
	}

	@Resource
	ReceivableViewServiceI receivableViewService;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session=request.getSession();
	/**
	 * 查询所有
	 */
	public void findALL() {
		try {
			String tradeDateQi=request.getParameter("tradeDateQi");
			String tradeDateShi=request.getParameter("tradeDateShi");
			String customerNo=request.getParameter("customerNo");
			String orderNo=request.getParameter("orderNo");
			String affiliationNo=request.getParameter("affiliationNo");
			String deadlineShi=request.getParameter("deadlineShi");
			String noticeNo=request.getParameter("noticeNo");
			String voucherNo=request.getParameter("voucherNo");
			String counterFee=request.getParameter("counterFee");
			User user=(User)session.getAttribute("userSession");
			rp.setCustomerNo(customerNo);
			rp.setNoticeNo(noticeNo);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			try {
				if(tradeDateQi!=null&&!tradeDateQi.equals("")){
					rp.setTradeDateQi(df.parse(tradeDateQi));
				}
				if(tradeDateShi!=null&&!tradeDateShi.equals("")){
					rp.setTradeDateShi(df.parse(tradeDateShi));
				}
				if(deadlineShi!=null&&!deadlineShi.equals("")){
					rp.setDeadlineShi(df.parse(deadlineShi));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			rp.setOrderNo(orderNo);
			rp.setVoucherNo(voucherNo);
			if(counterFee!=null&&!counterFee.equals("")){
				rp.setCounterFee(Integer.parseInt(counterFee));
			}
			
			if(affiliationNo!=null&&!affiliationNo.equals("")){
				rp.setAffiliationNo(Long.parseLong(affiliationNo));
			}
			
			if(rp.getPage()==0){
				rp.setPage(1);
				rp.setRows(10);
			}
			writeJson(receivableViewService.findAll(rp,user));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 条件查询
	 */
	public void findSerach() {
		try {
			User user=(User)session.getAttribute("userSession");
			if (this.receivableViewService.findAll(rp,user).getRows().size() > 0) {
				super.result.setSuccess(true);
				super.result.setObj(this.receivableViewService.findAll(rp,user));
			} else {
				super.result.setSuccess(false);
				super.result.setMsg("未查询到任何记录");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}

	/**
	 * 查询归属人
	 */
	public void findAllList() {
		try {
			User user=(User)session.getAttribute("userSession");
			writeJson(receivableViewService.findAllList(user));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询客户编号
	 */
	public void findAllCustomerNo() {
		try {
			String code="";
			writeJson(receivableViewService.findAllCustomerNo(code));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询客户编号，检索
	 */
	public void findAllCustomerNoJiansuo() {
		try {
			String code=request.getParameter("code");
			super.result.setObj(receivableViewService.findAllCustomerNo(code));
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson();
	}

	public void list() {
		super.writeJson(this.receivableViewService.list(rp));
	}
}
