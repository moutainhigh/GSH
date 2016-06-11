package com.gsh.cs.tools;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class ValidMethodTag extends TagSupport {
	private String code;

	private Integer type;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void testGit(){
		System.out.println("this is github test!");
	}
	
	public int doStartTag() throws JspException {
		try {
			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();
			HttpSession session = request.getSession();
			JspWriter out = pageContext.getOut();
			String result = "false";
			List list = (List) session.getAttribute("limitsList");
			for (int i = 0; i < list.size(); i++) {
//				System.out.println(list.get(i));
				if (list.get(i).equals(code)) {
					System.out.println(code);
					result = "true";
					break;
				}
			}
			out.print(result);
		} catch (java.io.IOException e) {
			throw new JspTagException(e.getMessage());
		}
		return SKIP_BODY;
	}
}
