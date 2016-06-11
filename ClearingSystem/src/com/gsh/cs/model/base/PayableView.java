package com.gsh.cs.model.base;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payable_view", catalog = "")
public class PayableView {
	
	private String exchangeNo;// 交换单号 
	private String supplierNo;// 供应商编号
	private String supplierName;// 供应商名称
	private Date tradeDate;// 交易日期 
	private String productOrderNo;// 产品单号 
	private Date deadline;// 结算期限 
	private String productNo;// 产品号 
	private Integer type;// 类型:1业务应付 2业务押金 3业务暂支 4业务应退 5预付6押金7其它8ADM9ACM10MCO
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
	
	private Long id;
	private Long supmid;//销账记录id
	private Long pablid;//应付账款id
	private BigDecimal payAmount;//实付金额
	private Integer statusZanzhi;
	
	private String settlementNo;//结算单号
	private Integer paymentMethod;//付款方式
	private String voucherNo;//凭证号
	private String handPerson;//经手人
	private String remark;// 备注 
	private Integer status;//状态
	private Integer temporaryHas;// 是否包含暂支款:1不包含 2包含
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "settlementNo")
	public String getSettlementNo() {
		return settlementNo;
	}
	public void setSettlementNo(String settlementNo) {
		this.settlementNo = settlementNo;
	}
	
	@Column(name = "exchangeNo")
	public String getExchangeNo() {
		return exchangeNo;
	}
	public void setExchangeNo(String exchangeNo) {
		this.exchangeNo = exchangeNo;
	}
	
	@Column(name = "supplierNo")
	public String getSupplierNo() {
		return supplierNo;
	}
	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	
	@Column(name = "tradeDate")
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	
	@Column(name = "orderNo")
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Column(name = "paymentMethod")
	public Integer getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	@Column(name = "payAmount")
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	
	@Column(name = "productNo")
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	
	@Column(name = "voucherNo")
	public String getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}
	
	@Column(name = "affiliationPerson")
	public String getAffiliationPerson() {
		return affiliationPerson;
	}
	public void setAffiliationPerson(String affiliationPerson) {
		this.affiliationPerson = affiliationPerson;
	}
	
	@Column(name = "affiliationNo")
	public Long getAffiliationNo() {
		return affiliationNo;
	}
	public void setAffiliationNo(Long affiliationNo) {
		this.affiliationNo = affiliationNo;
	}
	
	@Column(name = "handPerson")
	public String getHandPerson() {
		return handPerson;
	}
	public void setHandPerson(String handPerson) {
		this.handPerson = handPerson;
	}
	
	@Column(name = "supplierName")
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	@Column(name = "productOrderNo")
	public String getProductOrderNo() {
		return productOrderNo;
	}
	public void setProductOrderNo(String productOrderNo) {
		this.productOrderNo = productOrderNo;
	}
	
	@Column(name = "deadline")
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	@Column(name = "type")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	@Column(name = "createDate")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "creater")
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	@Column(name = "createrNo")
	public Long getCreaterNo() {
		return createrNo;
	}
	public void setCreaterNo(Long createrNo) {
		this.createrNo = createrNo;
	}
	
	@Column(name = "noticeStatus")
	public Integer getNoticeStatus() {
		return noticeStatus;
	}
	public void setNoticeStatus(Integer noticeStatus) {
		this.noticeStatus = noticeStatus;
	}
	
	@Column(name = "productStatus")
	public Integer getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}
	
	@Column(name = "cancelStatus")
	public Integer getCancelStatus() {
		return cancelStatus;
	}
	public void setCancelStatus(Integer cancelStatus) {
		this.cancelStatus = cancelStatus;
	}
	
	@Column(name = "currencyType")
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
	@Column(name = "revocationHas")
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
	
	@Column(name = "reverseHas")
	public Integer getReverseHas() {
		return reverseHas;
	}
	public void setReverseHas(Integer reverseHas) {
		this.reverseHas = reverseHas;
	}
	
	@Column(name = "supmid")
	public Long getSupmid() {
		return supmid;
	}
	public void setSupmid(Long supmid) {
		this.supmid = supmid;
	}
	
	@Column(name = "pablid")
	public Long getPablid() {
		return pablid;
	}
	public void setPablid(Long pablid) {
		this.pablid = pablid;
	}
	
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "statusZanzhi")
	public Integer getStatusZanzhi() {
		return statusZanzhi;
	}
	public void setStatusZanzhi(Integer statusZanzhi) {
		this.statusZanzhi = statusZanzhi;
	}
	
	@Column(name = "temporaryHas")
	public Integer getTemporaryHas() {
		return temporaryHas;
	}
	public void setTemporaryHas(Integer temporaryHas) {
		this.temporaryHas = temporaryHas;
	}
	
}
