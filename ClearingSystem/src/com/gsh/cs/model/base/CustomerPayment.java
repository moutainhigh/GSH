package com.gsh.cs.model.base;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * CustomerPayment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customer_payment", catalog = "", uniqueConstraints = @UniqueConstraint(columnNames = "settlementNo"))
public class CustomerPayment implements java.io.Serializable {

	// Fields

	private Long id;// 唯一编号
	private Long handNo;// 经手人编号
	private Long applyNo;// 申请人编号
	private BigDecimal publication;// 客户信用卡手续费扣率%
	private BigDecimal settlement;// 结算信用卡手续费扣率%
	private Date createDate;// 创建日期
	private Integer dataType;// 数据类型:1收入2退款
	private String settlementNo;// 结算编号:系统生成(唯一)
	private String handPerson;// 经手人
	private String applyPerson;// 申请人
	private String customerNo;// 客户编号
	private Integer paymentMethod;// 收款方式/付款方式：1现金 /2信用卡/ 3支票 /4转账汇款/5内转
	private String customerName;// 付款人/收款人
	private String bankAccountNo;// 账号
	private String cardCode;// 信用卡代码
	private String cardExpirationDate;// 信用卡有效期：格式 13/08 日/月
	private BigDecimal incomeAmount;// 实收金额
	private BigDecimal factorage;// 手续费
	private BigDecimal customerFactorage;// 客户手续费
	private Date incomeDate;// 收款/付款日期
	private String fundAccount;// 资金账户
	private BigDecimal netAmount;// 入账金额
	private BigDecimal bankRate;// 银行汇率 结算汇率
	private BigDecimal exchangeProfitLoss;// 汇兑损益
	private String tradeNo;// 交易号
	private String voucherNo;// 凭证号
	private String remark;// 备注
	private Integer status;// 状态：1未提交 2已驳回 3审批中 4待销账 5未到账 6已到账 9已取消
	private Date arriveDate;// 到账日期
	private String sprName;
	private Timestamp spDate;

	// Constructors

	/** default constructor */
	public CustomerPayment() {
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

	@Column(name = "handNo")
	public Long getHandNo() {
		return this.handNo;
	}

	public void setHandNo(Long handNo) {
		this.handNo = handNo;
	}

	@Column(name = "applyNo")
	public Long getApplyNo() {
		return this.applyNo;
	}

	public void setApplyNo(Long applyNo) {
		this.applyNo = applyNo;
	}

	@Column(name = "publication", nullable = false, precision = 10)
	public BigDecimal getPublication() {
		return this.publication;
	}

	public void setPublication(BigDecimal publication) {
		this.publication = publication;
	}

	@Column(name = "settlement", nullable = false, precision = 10)
	public BigDecimal getSettlement() {
		return this.settlement;
	}

	public void setSettlement(BigDecimal settlement) {
		this.settlement = settlement;
	}

	@Column(name = "createDate", nullable = false, length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "dataType", nullable = false)
	public Integer getDataType() {
		return this.dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	@Column(name = "settlementNo", unique = true, nullable = false)
	public String getSettlementNo() {
		return this.settlementNo;
	}

	public void setSettlementNo(String settlementNo) {
		this.settlementNo = settlementNo;
	}

	@Column(name = "handPerson")
	public String getHandPerson() {
		return this.handPerson;
	}

	public void setHandPerson(String handPerson) {
		this.handPerson = handPerson;
	}

	@Column(name = "applyPerson")
	public String getApplyPerson() {
		return this.applyPerson;
	}

	public void setApplyPerson(String applyPerson) {
		this.applyPerson = applyPerson;
	}

	@Column(name = "customerNo", nullable = false)
	public String getCustomerNo() {
		return this.customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	@Column(name = "paymentMethod")
	public Integer getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Column(name = "customerName", nullable = false)
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name = "bankAccountNo")
	public String getBankAccountNo() {
		return this.bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	@Column(name = "cardCode")
	public String getCardCode() {
		return this.cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	@Column(name = "cardExpirationDate")
	public String getCardExpirationDate() {
		return this.cardExpirationDate;
	}

	public void setCardExpirationDate(String cardExpirationDate) {
		this.cardExpirationDate = cardExpirationDate;
	}

	@Column(name = "incomeAmount", nullable = false, precision = 10)
	public BigDecimal getIncomeAmount() {
		return this.incomeAmount;
	}

	public void setIncomeAmount(BigDecimal incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

	@Column(name = "factorage", nullable = false, precision = 10)
	public BigDecimal getFactorage() {
		return this.factorage;
	}

	public void setFactorage(BigDecimal factorage) {
		this.factorage = factorage;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "incomeDate", length = 10)
	public Date getIncomeDate() {
		return this.incomeDate;
	}

	public void setIncomeDate(Date incomeDate) {
		this.incomeDate = incomeDate;
	}

	@Column(name = "fundAccount")
	public String getFundAccount() {
		return this.fundAccount;
	}

	public void setFundAccount(String fundAccount) {
		this.fundAccount = fundAccount;
	}

	@Column(name = "netAmount", nullable = false, precision = 10)
	public BigDecimal getNetAmount() {
		return this.netAmount;
	}

	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	@Column(name = "bankRate", nullable = false, precision = 10)
	public BigDecimal getBankRate() {
		return this.bankRate;
	}

	public void setBankRate(BigDecimal bankRate) {
		this.bankRate = bankRate;
	}

	@Column(name = "exchangeProfitLoss", nullable = false, precision = 10)
	public BigDecimal getExchangeProfitLoss() {
		return this.exchangeProfitLoss;
	}

	public void setExchangeProfitLoss(BigDecimal exchangeProfitLoss) {
		this.exchangeProfitLoss = exchangeProfitLoss;
	}

	@Column(name = "tradeNo")
	public String getTradeNo() {
		return this.tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	@Column(name = "voucherNo")
	public String getVoucherNo() {
		return this.voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "customerFactorage", nullable = false, precision = 10)
	public BigDecimal getCustomerFactorage() {
		return customerFactorage;
	}

	public void setCustomerFactorage(BigDecimal customerFactorage) {
		this.customerFactorage = customerFactorage;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "arriveDate", length = 10)
	public Date getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}
	
	@Column(name = "sprName")
	public String getSprName() {
		return sprName;
	}
	
	public void setSprName(String sprName) {
		this.sprName = sprName;
	}

	@Column(name = "spDate")
	public Timestamp getSpDate() {
		return spDate;
	}
	
	public void setSpDate(Timestamp spDate) {
		this.spDate = spDate;
	}
}