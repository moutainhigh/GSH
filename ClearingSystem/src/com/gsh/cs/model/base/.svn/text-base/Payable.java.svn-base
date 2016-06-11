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
 * Payable entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "payable", catalog = "")
public class Payable implements java.io.Serializable {

	// Fields

	private Long id;// 唯一编号
	private String exchangeNo;// 交换单号 
	private String supplierNo;// 供应商编号
	private String supplierName;// 供应商名称
	private Date tradeDate;// 交易日期 
	private String productOrderNo;// 产品单号 
	private Date deadline;// 结算期限 
	private BigDecimal payBeAmount;// 应付金额 
	private BigDecimal payAmount;// 已付金额 
	private BigDecimal balance;// 余额 
	private String productNo;// 产品号 
	private Integer type;// 类型:1业务应付 2业务押金 3业务暂支 4业务应退 5预付6押金7其它8ADM9ACM10MCO
	private String remark;// 备注 
	private Date createDate;// 创建日期
	private String affiliationPerson;// 归属人
	private Long affiliationNo;// 归属人编号
	private String creater;// 创建人
	private Long createrNo;// 创建人编号
	private Integer noticeStatus;// 通知单状态:1未完成 2已完成
	private Integer productStatus;// 产品状态:1未完成 2已完成
	private Integer cancelStatus;// 申请取消状态:1正常 2申请取消
	private String currencyType;// 货币类型:CNY,USD,EUR…
	private String orderNo;// 订单号
	private Integer revocationHas;// 可否撤销 1可以 2不可以
	private String depts;
	private Integer reverseHas;// 是否反向生成 1不生成 2生成
	private Integer abnormalStatus;//异常状态1、正常2、异常
	private String groupNumber;//团号
	private Integer yajinSort;//排序

	// Constructors

	/** default constructor */
	public Payable() {
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

	@Column(name = "exchangeNo", nullable = false)
	public String getExchangeNo() {
		return this.exchangeNo;
	}

	public void setExchangeNo(String exchangeNo) {
		this.exchangeNo = exchangeNo;
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
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tradeDate", nullable = false, length = 10)
	public Date getTradeDate() {
		return this.tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	@Column(name = "productOrderNo")
	public String getProductOrderNo() {
		return this.productOrderNo;
	}

	public void setProductOrderNo(String productOrderNo) {
		this.productOrderNo = productOrderNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "deadline", length = 10)
	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Column(name = "payBeAmount", nullable = false, precision = 10)
	public BigDecimal getPayBeAmount() {
		return this.payBeAmount;
	}

	public void setPayBeAmount(BigDecimal payBeAmount) {
		this.payBeAmount = payBeAmount;
	}

	@Column(name = "payAmount", nullable = false, precision = 10)
	public BigDecimal getPayAmount() {
		return this.payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	@Column(name = "balance", nullable = false, precision = 10)
	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Column(name = "productNo")
	public String getProductNo() {
		return this.productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	@Column(name = "type", nullable = false)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	@Column(name = "noticeStatus", nullable = false)
	public Integer getNoticeStatus() {
		return this.noticeStatus;
	}

	public void setNoticeStatus(Integer noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	@Column(name = "productStatus", nullable = false)
	public Integer getProductStatus() {
		return this.productStatus;
	}

	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}

	@Column(name = "cancelStatus", nullable = false)
	public Integer getCancelStatus() {
		return this.cancelStatus;
	}

	public void setCancelStatus(Integer cancelStatus) {
		this.cancelStatus = cancelStatus;
	}

	@Column(name = "currencyType", nullable = false)
	public String getCurrencyType() {
		return this.currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	@Column(name = "orderNo")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "revocationHas", nullable = false)
	public Integer getRevocationHas() {
		return revocationHas;
	}

	public void setRevocationHas(Integer revocationHas) {
		this.revocationHas = revocationHas;
	}

	@Column(name = "depts")
	public String getDepts() {
		return depts;
	}

	public void setDepts(String depts) {
		this.depts = depts;
	}

	@Column(name = "reverseHas", nullable = false)
	public Integer getReverseHas() {
		return reverseHas;
	}

	public void setReverseHas(Integer reverseHas) {
		this.reverseHas = reverseHas;
	}
	
	@Column(name = "abnormalStatus")
	public Integer getAbnormalStatus() {
		return abnormalStatus;
	}

	public void setAbnormalStatus(Integer abnormalStatus) {
		this.abnormalStatus = abnormalStatus;
	}

	@Column(name = "groupNumber")
	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}
	
	@Column(name = "yajinSort")
	public Integer getYajinSort() {
		return yajinSort;
	}

	public void setYajinSort(Integer yajinSort) {
		this.yajinSort = yajinSort;
	}
	
}