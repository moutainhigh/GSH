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
 * Receivable entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "receivable", catalog = "")
public class Receivable implements java.io.Serializable {

	// Fields

	private Long id;// 唯一编号
	private String noticeNo;// 通知单号
	private String customerNo;// 客户编号
	private String customerName;// 客户名称
	private Date createDate;// 创建日期
	private String orderNo;// 订单号
	private BigDecimal incomeBeAmount;// 应收金额
	private BigDecimal incomeAmount;// 已收金额
	private BigDecimal balance;// 余额
	private Integer type;// 类型:1业务应收 2业务押金 3业务应退 4预收5押金6其它
	private Date deadline;// 结算期限 
	private String remark;// 备注
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
	private String groupNumber;//团号
	private Integer cancelStatus;// 申请取消状态:1正常 2申请取消
	private Integer yajinSort;//排序

	// Constructors

	/** default constructor */
	public Receivable() {
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

	@Column(name = "noticeNo", nullable = false)
	public String getNoticeNo() {
		return this.noticeNo;
	}

	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}

	@Column(name = "customerNo", nullable = false)
	public String getCustomerNo() {
		return this.customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	@Column(name = "customerName", nullable = false)
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name = "createDate", nullable = false, length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "orderNo")
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	@Column(name = "type", nullable = false)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "deadline", length = 10)
	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "passengerName")
	public String getPassengerName() {
		return this.passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	@Column(name = "affiliationPerson", nullable = false)
	public String getAffiliationPerson() {
		return this.affiliationPerson;
	}

	public void setAffiliationPerson(String affiliationPerson) {
		this.affiliationPerson = affiliationPerson;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tradeDate", nullable = false, length = 10)
	public Date getTradeDate() {
		return this.tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	@Column(name = "affiliationNo", nullable = false)
	public Long getAffiliationNo() {
		return this.affiliationNo;
	}

	public void setAffiliationNo(Long affiliationNo) {
		this.affiliationNo = affiliationNo;
	}

	@Column(name = "deadlineHas", nullable = false)
	public Integer getDeadlineHas() {
		return this.deadlineHas;
	}

	public void setDeadlineHas(Integer deadlineHas) {
		this.deadlineHas = deadlineHas;
	}

	@Column(name = "originalPaymentMethod", nullable = false)
	public Integer getOriginalPaymentMethod() {
		return this.originalPaymentMethod;
	}

	public void setOriginalPaymentMethod(Integer originalPaymentMethod) {
		this.originalPaymentMethod = originalPaymentMethod;
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

	@Column(name = "revocationHas", nullable = false)
	public Integer getRevocationHas() {
		return revocationHas;
	}

	public void setRevocationHas(Integer revocationHas) {
		this.revocationHas = revocationHas;
	}

	@Column(name = "reverseHas", nullable = false)
	public Integer getReverseHas() {
		return reverseHas;
	}

	public void setReverseHas(Integer reverseHas) {
		this.reverseHas = reverseHas;
	}

	@Column(name = "groupNumber")
	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}
	
	@Column(name = "cancelStatus")
	public Integer getCancelStatus() {
		return cancelStatus;
	}

	public void setCancelStatus(Integer cancelStatus) {
		this.cancelStatus = cancelStatus;
	}

	@Column(name = "yajinSort")
	public Integer getYajinSort() {
		return yajinSort;
	}

	public void setYajinSort(Integer yajinSort) {
		this.yajinSort = yajinSort;
	}
	
}