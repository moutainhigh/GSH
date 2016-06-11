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
@Table(name = "receivable_view", catalog = "")
public class ReceivableView {
	
	private String noticeNo;// 通知单号
	private String customerNo;// 客户编号
	private String customerName;// 客户名称
	private Date createDate;// 创建日期
	private String orderNo;// 订单号
	private Integer type;// 类型:1业务应收 2业务押金 3业务应退 4预收5押金6其它
	private Date deadline;// 结算期限 
	private String passengerName;// 旅客名字
	private String affiliationPerson;// 归属人
	private Date tradeDate;// 交易日期
	private Long affiliationNo;// 归属人编号
	private Integer deadlineHas;// 修改结算期限
	private Integer originalPaymentMethod;// 原收款方式
	private String creater;// 创建人
	private Long createrNo;// 创建人编号
	private String depts;
	private Integer revocationHas;// 可否撤销 1可以 2不可以
	private Integer reverseHas;// 是否反向生成 1不生成 2生成
	
	private Long id;//唯一编号
	private Long ctpmid;//销账记录ID
	private Long rcvbid;//应收账款id
	private BigDecimal incomeAmount;//实收金额
	
	private String settlementNo;//结算编号
	private Integer paymentMethod;//交易方式
	private String voucherNo;//凭证号
	private String handPerson;//经手人
	private String remark;//备注
	private Integer status;
	
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
	@Column(name = "noticeNo")
	public String getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}
	@Column(name = "customerNo")
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
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
	@Column(name = "incomeAmount")
	public BigDecimal getIncomeAmount() {
		return incomeAmount;
	}
	public void setIncomeAmount(BigDecimal incomeAmount) {
		this.incomeAmount = incomeAmount;
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
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "deadline")
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	@Column(name = "customerName")
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	@Column(name = "createDate")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name = "type")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name = "passengerName")
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	@Column(name = "deadlineHas")
	public Integer getDeadlineHas() {
		return deadlineHas;
	}
	public void setDeadlineHas(Integer deadlineHas) {
		this.deadlineHas = deadlineHas;
	}
	@Column(name = "originalPaymentMethod")
	public Integer getOriginalPaymentMethod() {
		return originalPaymentMethod;
	}
	public void setOriginalPaymentMethod(Integer originalPaymentMethod) {
		this.originalPaymentMethod = originalPaymentMethod;
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
	@Column(name = "depts")
	public String getDepts() {
		return depts;
	}
	public void setDepts(String depts) {
		this.depts = depts;
	}
	@Column(name = "revocationHas")
	public Integer getRevocationHas() {
		return revocationHas;
	}
	public void setRevocationHas(Integer revocationHas) {
		this.revocationHas = revocationHas;
	}
	@Column(name = "reverseHas")
	public Integer getReverseHas() {
		return reverseHas;
	}
	public void setReverseHas(Integer reverseHas) {
		this.reverseHas = reverseHas;
	}
	@Column(name = "ctpmid")
	public Long getCtpmid() {
		return ctpmid;
	}
	public void setCtpmid(Long ctpmid) {
		this.ctpmid = ctpmid;
	}
	@Column(name = "rcvbid")
	public Long getRcvbid() {
		return rcvbid;
	}
	public void setRcvbid(Long rcvbid) {
		this.rcvbid = rcvbid;
	}
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
