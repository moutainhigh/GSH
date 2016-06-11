package com.gsh.cs.model.Interface;

public class OrderGuishu {
	private String affiliationNo;
	private String affiliationPerson;
	private String customerNo;
	private String customerName;
	private int sex;
	private Long dept;
	private String empId;
	private String deptName;
	
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Long getDept() {
		return dept;
	}
	public void setDept(Long dept) {
		this.dept = dept;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getAffiliationNo() {
		return affiliationNo;
	}
	public void setAffiliationNo(String affiliationNo) {
		this.affiliationNo = affiliationNo;
	}
	public String getAffiliationPerson() {
		return affiliationPerson;
	}
	public void setAffiliationPerson(String affiliationPerson) {
		this.affiliationPerson = affiliationPerson;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
}
