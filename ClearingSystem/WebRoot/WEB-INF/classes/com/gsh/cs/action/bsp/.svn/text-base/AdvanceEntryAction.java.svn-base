package com.gsh.cs.action.bsp;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.alibaba.fastjson.JSON;
import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.model.base.Account;
import com.gsh.cs.model.base.AdvanceEntry;
import com.gsh.cs.model.parameter.AdvanceEntryPRMT;
import com.gsh.cs.service.apmgt.ApplyPaymentServiceI;
import com.gsh.cs.service.bsp.AdvanceEntryServiceI;
import com.gsh.cs.tools.LongTool;
import com.opensymphony.xwork2.ModelDriven;
@Namespace("/bsp")
@Action(value = "advanceEntryAction")
public class AdvanceEntryAction extends BaseAction implements ModelDriven<AdvanceEntryPRMT> {
	private AdvanceEntryPRMT ap=new AdvanceEntryPRMT();
	public AdvanceEntryPRMT getModel() {
		return ap;
	}
	@Resource AdvanceEntryServiceI advanceEntryService;
	@Resource ApplyPaymentServiceI applyPaymentService;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	
	public void findAll(){
		try {
			writeJson(this.advanceEntryService.findAll(ap));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save(){
		try {
			String invoiceNo=request.getParameter("invoiceNo");
			String invoiceDate=request.getParameter("invoiceDate");
			if(invoiceNo!=null&&!invoiceNo.equals("")){
				ap.setInvoiceNo(invoiceNo);
				ap.setInvoiceMoney(ap.getAmountMoney());
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
				ap.setInvoiceDate(df.parse(invoiceDate));
			}
			ap.setStatus(1);
			super.result.setObj(this.advanceEntryService.save(ap));
			
			//把payable_detail的录入状态改为已录入  2
			applyPaymentService.updatePayableDdetail(ap.getPablid(),ap.getSupmid());
			//如果Supmid对应的应付账款id的记录都变为已录入，那么把明细表的状态改为已确认状态
			Integer statusZanzhi = applyPaymentService.findstatusZanzhi(ap.getSupmid());
			if(statusZanzhi==2){
				applyPaymentService.updateStatus("6", ap.getSettlementNo());
			}
			super.result.setSuccess(true);
			super.result.setMsg("录入成功");
		} catch (Exception e) {
			super.result.setSuccess(false);
			super.result.setMsg("录入失败");
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	public void saveWeiluru(){
		try {
			String invoiceNo=request.getParameter("invoiceNo");
			String invoiceDate=request.getParameter("invoiceDate");
			if(invoiceNo!=null&&!invoiceNo.equals("")){
				ap.setInvoiceNo(invoiceNo);
				ap.setInvoiceMoney(ap.getAmountMoney());
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
				ap.setInvoiceDate(df.parse(invoiceDate));
			}
			ap.setStatus(1);
			super.result.setObj(this.advanceEntryService.save(ap));
			applyPaymentService.updateStatus("6", ap.getSettlementNo());
			super.result.setSuccess(true);
			super.result.setMsg("录入成功");
		} catch (Exception e) {
			super.result.setSuccess(false);
			super.result.setMsg("录入失败");
			e.printStackTrace();
		}
		super.writeJson();
	}
	
	public void findBianli(){
		try {
			String invoiceNo=request.getParameter("invoiceNo");
			AdvanceEntry a = advanceEntryService.findBianli(invoiceNo);
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
}
