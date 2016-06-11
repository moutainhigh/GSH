package com.gsh.cs.filter.loginUser;

import java.io.Serializable;

public class User implements Serializable {
	private Long uid;// 用户id
	private Long cid; // 代理商id
	private String name; // 用户姓名
	private Long dept;// 用户部门信息
	private String depts; // 部门集合 主要是面向的主管的话就把它管理的所有部门id存储到这边以“,”隔开
	private int zhuguan;// 是否主管 主要在系统做浏览权限时 浏览的范围判断 不是主管只能查看自己的 是可以按照上面的部门集合查询
	private String domain;// 网站域名路径
	private String sitename;// 网站名称s
	private int type = 0;// 用户类型 0、tc 1、admin 2、Supervisor
	private String email;// 登录账号
	private String password;// 登录密码
	private String ticket;
	private String roleName;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDept() {
		return dept;
	}

	public void setDept(Long dept) {
		this.dept = dept;
	}

	public String getDepts() {
		return depts;
	}

	public void setDepts(String depts) {
		this.depts = depts;
	}

	public int getZhuguan() {
		return zhuguan;
	}

	public void setZhuguan(int zhuguan) {
		this.zhuguan = zhuguan;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
