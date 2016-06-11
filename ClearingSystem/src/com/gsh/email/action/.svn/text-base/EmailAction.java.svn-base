package com.gsh.email.action;

import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.gsh.cs.action.base.BaseAction;
import com.gsh.cs.tools.Utils;
import com.gsh.email.SimpleMailSender;
import com.gsh.email.model.OutUrl;
import com.gsh.email.properties.MailSenderInfo;
import com.gsh.email.util.RandomString;
import com.opensymphony.xwork2.ModelDriven;


@Namespace("/")
@Action(value = "emailAction")
@Results({
	@Result(name = "emailSuccess", location = "/business/email/success.jsp"),
	@Result(name = "emailFail", location = "/business/email/fail.jsp") })
public class EmailAction extends BaseAction implements ModelDriven<MailSenderInfo>{
	private MailSenderInfo mailInfo=new MailSenderInfo();
	
	public MailSenderInfo getModel() {
		return mailInfo;
	}
	
	HttpServletRequest request=ServletActionContext.getRequest();
	
	public void sendEmail(){
		String title=request.getParameter("title");//email标题
		String content=request.getParameter("content2");//emailContent
		String mailSignature=request.getParameter("mailSignature");//签名
		String content2=request.getParameter("content");//填写内容
		String toAddress = request.getParameter("toAddress");//收件人
		String ccAddress = request.getParameter("ccAddress");//抄送
		String subject = title;//主题
		
		mailSignature = "<div><br/><br/>" + mailSignature + "</div>";
		content2 = "<div><br/><br/>" + content2 + "</div>";
		String htmlCon = "<html><head>" + title + "</head><body>";
		htmlCon += content;
			
		try{	
			mailInfo.setMailServerHost("smtp.ym.163.com");
			mailInfo.setMailServerPort("25");
			mailInfo.setValidate(true);
			mailInfo.setUserName("autoservice@gshts.com"); //发送者账号
			mailInfo.setPassword("888888");
			mailInfo.setFromAddress("autoservice@gshts.com"); //发送者账号
			mailInfo.setToAddress(toAddress);
			mailInfo.setSubject(subject);
			mailInfo.setContent(htmlCon);
			if(!Utils.isNullOrEmpty(ccAddress))
				mailInfo.setCcAddress(ccAddress);
			// 这个类主要来发送邮件
			SimpleMailSender sms = new SimpleMailSender();
			//boolean mt = sms.sendTextMail(mailInfo);//发送文体格式
			@SuppressWarnings("static-access")
			boolean mt = sms.sendHtmlMail(mailInfo);// 发送html格式
			
			if (mt) {
				super.result.setSuccess(true);
			} else {
				super.result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.writeJson();
	}
}