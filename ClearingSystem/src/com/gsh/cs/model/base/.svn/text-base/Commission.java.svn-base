package com.gsh.cs.model.base;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Commission entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "commission", catalog = "")
public class Commission implements java.io.Serializable {

	// Fields

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

	// Constructors

	/** default constructor */
	public Commission() {
	}

	/** minimal constructor */
	public Commission(String productOrderNo, String supplierNo, Date tradeDate,
			String productRoute, BigDecimal incomeBeAmount, BigDecimal incomeAmount,
			BigDecimal balance, String productNo, Date createDate,
			String affiliationPerson, Long affiliationNo, String creater,
			Long createrNo) {
		this.productOrderNo = productOrderNo;
		this.supplierNo = supplierNo;
		this.tradeDate = tradeDate;
		this.productRoute = productRoute;
		this.incomeBeAmount = incomeBeAmount;
		this.incomeAmount = incomeAmount;
		this.balance = balance;
		this.productNo = productNo;
		this.createDate = createDate;
		this.affiliationPerson = affiliationPerson;
		this.affiliationNo = affiliationNo;
		this.creater = creater;
		this.createrNo = createrNo;
	}

	/** full constructor */
	public Commission(String productOrderNo, String supplierNo, Date tradeDate,
			String productRoute, String carrier, String currencyType,
			BigDecimal incomeBeAmount, BigDecimal incomeAmount, BigDecimal balance,
			String productNo, Date createDate, String affiliationPerson,
			Long affiliationNo, String creater, Long createrNo,String depts) {
		this.productOrderNo = productOrderNo;
		this.supplierNo = supplierNo;
		this.tradeDate = tradeDate;
		this.productRoute = productRoute;
		this.carrier = carrier;
		this.currencyType = currencyType;
		this.incomeBeAmount = incomeBeAmount;
		this.incomeAmount = incomeAmount;
		this.balance = balance;
		this.productNo = productNo;
		this.createDate = createDate;
		this.affiliationPerson = affiliationPerson;
		this.affiliationNo = affiliationNo;
		this.creater = creater;
		this.createrNo = createrNo;
		this.depts = depts;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "tradeDate", nullable = false, length = 10)
	public Date getTradeDate() {
		return this.tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	@Column(name = "productRoute", nullable = false)
	public String getProductRoute() {
		return this.productRoute;
	}

	public void setProductRoute(String productRoute) {
		this.productRoute = productRoute;
	}

	@Column(name = "carrier")
	public String getCarrier() {
		return this.carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	@Column(name = "currencyType")
	public String getCurrencyType() {
		return this.currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	@Column(name = "incomeBeAmount", nullable = false, precision = 10)
	public BigDecimal getIncomeBeAmount() {
		return this.incomeBeAmount;
	}

	public void setIncomeBeAmount(BigDecimal incomeBeAmount) {
		this.incomeBeAmount = incomeBeAmount;
	}

	@Column(name = "incomeAmount", nullable = false, precision = 10)
	public BigDecimal getIncomeAmount() {
		return this.incomeAmount;
	}

	public void setIncomeAmount(BigDecimal incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

	@Column(name = "balance", nullable = false, precision = 10)
	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Column(name = "productNo", nullable = false)
	public String getProductNo() {
		return this.productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	@Column(name = "createDate", nullable = false, length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "affiliationPerson", nullable = false)
	public String getAffiliationPerson() {
		return this.affiliationPerson;
	}

	public void setAffiliationPerson(String affiliationPerson) {
		this.affiliationPerson = affiliationPerson;
	}

	@Column(name = "affiliationNo", nullable = false)
	public Long getAffiliationNo() {
		return this.affiliationNo;
	}

	public void setAffiliationNo(Long affiliationNo) {
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
	public Long getCreaterNo() {
		return this.createrNo;
	}

	public void setCreaterNo(Long createrNo) {
		this.createrNo = createrNo;
	}
	@Column(name = "depts")
	public String getDepts() {
		return depts;
	}

	public void setDepts(String depts) {
		this.depts = depts;
	}

}