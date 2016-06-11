package com.gsh.cs.model.parameter;

import java.util.Date;

public class BspTicketPRMT {
	private int page;
	private int rows;
	private String sort;
	private String order;
	
	private Long id;
	private Long bspStartBank;
	private Long bspTerminationBank;
	private Long bspNumber;
	private Integer bspType;
	private Date bspCreationDate;
	private Double bspAllowance;
	private Long bspAllowanceRemind;
	private Long userId;
	private String deptId;
	private Long bspStartTicket;//可卖的票号
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBspStartBank() {
		return bspStartBank;
	}
	public void setBspStartBank(Long bspStartBank) {
		this.bspStartBank = bspStartBank;
	}
	public Long getBspTerminationBank() {
		return bspTerminationBank;
	}
	public void setBspTerminationBank(Long bspTerminationBank) {
		this.bspTerminationBank = bspTerminationBank;
	}
	public Long getBspNumber() {
		return bspNumber;
	}
	public void setBspNumber(Long bspNumber) {
		this.bspNumber = bspNumber;
	}
	public Integer getBspType() {
		return bspType;
	}
	public void setBspType(Integer bspType) {
		this.bspType = bspType;
	}
	public Date getBspCreationDate() {
		return bspCreationDate;
	}
	public void setBspCreationDate(Date bspCreationDate) {
		this.bspCreationDate = bspCreationDate;
	}
	public Double getBspAllowance() {
		return bspAllowance;
	}
	public void setBspAllowance(Double bspAllowance) {
		this.bspAllowance = bspAllowance;
	}
	public Long getBspAllowanceRemind() {
		return bspAllowanceRemind;
	}
	public void setBspAllowanceRemind(Long bspAllowanceRemind) {
		this.bspAllowanceRemind = bspAllowanceRemind;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public Long getBspStartTicket() {
		return bspStartTicket;
	}
	public void setBspStartTicket(Long bspStartTicket) {
		this.bspStartTicket = bspStartTicket;
	}
	
	
}	
