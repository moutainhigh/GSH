package com.gsh.cs.model.parameter;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PaymentBudgetPRMT {
	private Long id;
	private Timestamp createDate;//创建时间
	private String productOrderNo;//产品单号
	private String supplierNo;//供应商编号
	private String supplierName;//供应商名称
	private BigDecimal payBeAmount;//预计应付金额
	private String affiliationPerson;//归属人
	private String affiliationNo;//归属人编号
	private String creater;//创建人
	private String createrNo;//创建人编号
	private Integer status;//状态0/1   0、未生成过通知单，显示在列表1、已生成过通知单，不做显示
	private String currencyType;
	
	private int page;
	private int rows;
	private String sort;
	private String order;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getProductOrderNo() {
		return productOrderNo;
	}
	public void setProductOrderNo(String productOrderNo) {
		this.productOrderNo = productOrderNo;
	}
	public String getSupplierNo() {
		return supplierNo;
	}
	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public BigDecimal getPayBeAmount() {
		return payBeAmount;
	}
	public void setPayBeAmount(BigDecimal payBeAmount) {
		this.payBeAmount = payBeAmount;
	}
	public String getAffiliationPerson() {
		return affiliationPerson;
	}
	public void setAffiliationPerson(String affiliationPerson) {
		this.affiliationPerson = affiliationPerson;
	}
	public String getAffiliationNo() {
		return affiliationNo;
	}
	public void setAffiliationNo(String affiliationNo) {
		this.affiliationNo = affiliationNo;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getCreaterNo() {
		return createrNo;
	}
	public void setCreaterNo(String createrNo) {
		this.createrNo = createrNo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
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
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
	

}
