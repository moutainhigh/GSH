package com.gsh.cs.model.parameter;

import java.math.BigDecimal;
import java.util.Date;

public class CommissionPRMT {
	private int page;
	private int rows;
	private String sort;
	private String order;
	
	private Long id;//唯一编号
	private String productOrderNo;//产品单号
	private String supplierNo;//供应商编号
	private Date tradeDate;//交易日期 
	private String productRoute;//航线产品
	private String carrier;//承运商
	private String currencyType;//货币类型
	private BigDecimal incomeBeAmount;//应收金额
	private BigDecimal incomeAmount;//已收金额 
	private BigDecimal balance;//余额
	private String productNo;//产品号
	private Date createDate;//创建日期
	private String affiliationPerson;//归属人
	private Long affiliationNo;//归属人编号
	private String creater;//创建人
	private Long createrNo;//创建人编号
	private String depts;
	private String productRouteName;//航线产品
	
	//按时间查询的范围字段
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
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getProductRoute() {
		return productRoute;
	}
	public void setProductRoute(String productRoute) {
		this.productRoute = productRoute;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public BigDecimal getIncomeBeAmount() {
		return incomeBeAmount;
	}
	public void setIncomeBeAmount(BigDecimal incomeBeAmount) {
		this.incomeBeAmount = incomeBeAmount;
	}
	public BigDecimal getIncomeAmount() {
		return incomeAmount;
	}
	public void setIncomeAmount(BigDecimal incomeAmount) {
		this.incomeAmount = incomeAmount;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getAffiliationPerson() {
		return affiliationPerson;
	}
	public void setAffiliationPerson(String affiliationPerson) {
		this.affiliationPerson = affiliationPerson;
	}
	public Long getAffiliationNo() {
		return affiliationNo;
	}
	public void setAffiliationNo(Long affiliationNo) {
		this.affiliationNo = affiliationNo;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Long getCreaterNo() {
		return createrNo;
	}
	public void setCreaterNo(Long createrNo) {
		this.createrNo = createrNo;
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
	public String getDepts() {
		return depts;
	}
	public void setDepts(String depts) {
		this.depts = depts;
	}
	public String getProductRouteName() {
		return productRouteName;
	}
	public void setProductRouteName(String productRouteName) {
		this.productRouteName = productRouteName;
	}
	
	
}
