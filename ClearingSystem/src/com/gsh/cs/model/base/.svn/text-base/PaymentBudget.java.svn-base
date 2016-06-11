package com.gsh.cs.model.base;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PaymentBudget entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "payment_budget", catalog = "clearing")
public class PaymentBudget implements java.io.Serializable {

	// Fields

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
	private String currencyType;//货币

	// Constructors

	/** default constructor */
	public PaymentBudget() {
	}

	/** full constructor */
	public PaymentBudget(Timestamp createDate, String productOrderNo,
			String supplierNo, String supplierName, BigDecimal payBeAmount,
			String affiliationPerson, String affiliationNo, String creater,
			String createrNo, Integer status,String currencyType) {
		this.createDate = createDate;
		this.productOrderNo = productOrderNo;
		this.supplierNo = supplierNo;
		this.supplierName = supplierName;
		this.payBeAmount = payBeAmount;
		this.affiliationPerson = affiliationPerson;
		this.affiliationNo = affiliationNo;
		this.creater = creater;
		this.createrNo = createrNo;
		this.status = status;
		this.currencyType=currencyType;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "createDate", nullable = false, length = 19)
	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Column(name = "productOrderNo", nullable = false)
	public String getProductOrderNo() {
		return this.productOrderNo;
	}

	public void setProductOrderNo(String productOrderNo) {
		this.productOrderNo = productOrderNo;
	}

	@Column(name = "supplierNo", nullable = false)
	public String getSupplierNo() {
		return this.supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	@Column(name = "supplierName", nullable = false)
	public String getSupplierName() {
		return this.supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@Column(name = "payBeAmount", nullable = false, precision = 10, scale = 0)
	public BigDecimal getPayBeAmount() {
		return this.payBeAmount;
	}

	public void setPayBeAmount(BigDecimal payBeAmount) {
		this.payBeAmount = payBeAmount;
	}

	@Column(name = "affiliationPerson", nullable = false)
	public String getAffiliationPerson() {
		return this.affiliationPerson;
	}

	public void setAffiliationPerson(String affiliationPerson) {
		this.affiliationPerson = affiliationPerson;
	}

	@Column(name = "affiliationNo", nullable = false)
	public String getAffiliationNo() {
		return this.affiliationNo;
	}

	public void setAffiliationNo(String affiliationNo) {
		this.affiliationNo = affiliationNo;
	}

	@Column(name = "creater", nullable = false)
	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	@Column(name = "createrNo", nullable = false)
	public String getCreaterNo() {
		return this.createrNo;
	}

	public void setCreaterNo(String createrNo) {
		this.createrNo = createrNo;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "currencyType", nullable = false)
	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

}