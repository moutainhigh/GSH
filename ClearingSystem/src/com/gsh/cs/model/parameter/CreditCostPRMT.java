package com.gsh.cs.model.parameter;

import java.math.BigDecimal;
import java.util.Date;

public class CreditCostPRMT {
	private int page;
	private int rows;
	private String sort;
	private String order;
	
	private Long id;
	private Date creditDate;
	private Long paymentnoticeId;
	private BigDecimal saleMoney;
	private BigDecimal costAmount;
	private BigDecimal grossProfit;
	private Long userId;
	private String deptId;
	private Date qiDate;
	private Date shiDate;
	
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
	public Date getCreditDate() {
		return creditDate;
	}
	public void setCreditDate(Date creditDate) {
		this.creditDate = creditDate;
	}
	public Long getPaymentnoticeId() {
		return paymentnoticeId;
	}
	public void setPaymentnoticeId(Long paymentnoticeId) {
		this.paymentnoticeId = paymentnoticeId;
	}
	public BigDecimal getSaleMoney() {
		return saleMoney;
	}
	public void setSaleMoney(BigDecimal saleMoney) {
		this.saleMoney = saleMoney;
	}
	public BigDecimal getCostAmount() {
		return costAmount;
	}
	public void setCostAmount(BigDecimal costAmount) {
		this.costAmount = costAmount;
	}
	public BigDecimal getGrossProfit() {
		return grossProfit;
	}
	public void setGrossProfit(BigDecimal grossProfit) {
		this.grossProfit = grossProfit;
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
	public Date getQiDate() {
		return qiDate;
	}
	public void setQiDate(Date qiDate) {
		this.qiDate = qiDate;
	}
	public Date getShiDate() {
		return shiDate;
	}
	public void setShiDate(Date shiDate) {
		this.shiDate = shiDate;
	}
	
	
}
