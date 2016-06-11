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
import javax.persistence.UniqueConstraint;

/**
 * CommissionPayment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "commission_payment", catalog = "", uniqueConstraints = @UniqueConstraint(columnNames = "settlementNo"))
public class CommissionPayment implements java.io.Serializable {

	// Fields

	private Long id;//唯一编号
	private Long handNo;//经手人编号
	private Date createDate;//创建日期
	private String settlementNo;//结算编号:系统生成(唯一)
	private String handPerson;//经手人
	private Integer paymentMethod;//收款方式:1现金 / 3支票 /4转账汇款
	private String carrier;//付款人
	private BigDecimal incomeAmount;//实收金额
	private BigDecimal factorage;//手续费
	private Date tradeDate;//收款日期
	private String currencyType;//货币类型:CNY,USD,EUR…
	private String fundAccount;//资金账户
	private BigDecimal bankRate;//银行汇率
	private BigDecimal exchangeProfitLoss;//汇兑损益
	private String voucherNo;//凭证号
	private String remark;//备注
	private Integer status;//状态:1正常 2取消

	// Constructors

	/** default constructor */
	public CommissionPayment() {
	}

	/** minimal constructor */
	public CommissionPayment(Long handNo, Date createDate,
			String settlementNo, String handPerson, Integer paymentMethod,
			String carrier, BigDecimal incomeAmount, BigDecimal factorage,
			Date tradeDate, String currencyType, String fundAccount,
			BigDecimal bankRate, BigDecimal exchangeProfitLoss,
			String voucherNo, String remark, Integer status) {
		this.handNo = handNo;
		this.createDate = createDate;
		this.settlementNo = settlementNo;
		this.handPerson = handPerson;
		this.paymentMethod = paymentMethod;
		this.carrier = carrier;
		this.incomeAmount = incomeAmount;
		this.factorage = factorage;
		this.tradeDate = tradeDate;
		this.currencyType = currencyType;
		this.fundAccount = fundAccount;
		this.bankRate = bankRate;
		this.exchangeProfitLoss = exchangeProfitLoss;
		this.voucherNo = voucherNo;
		this.remark = remark;
		this.status = status;
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

	@Column(name = "handNo", nullable = false)
	public Long getHandNo() {
		return this.handNo;
	}

	public void setHandNo(Long handNo) {
		this.handNo = handNo;
	}

	@Column(name = "createDate", nullable = false, length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "settlementNo", unique = true, nullable = false)
	public String getSettlementNo() {
		return this.settlementNo;
	}

	public void setSettlementNo(String settlementNo) {
		this.settlementNo = settlementNo;
	}

	@Column(name = "handPerson", nullable = false)
	public String getHandPerson() {
		return this.handPerson;
	}

	public void setHandPerson(String handPerson) {
		this.handPerson = handPerson;
	}

	@Column(name = "paymentMethod", nullable = false)
	public Integer getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Column(name = "carrier", nullable = false)
	public String getCarrier() {
		return this.carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
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
	@Column(name = "tradeDate", nullable = false, length = 10)
	public Date getTradeDate() {
		return this.tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	@Column(name = "currencyType", nullable = false)
	public String getCurrencyType() {
		return this.currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	@Column(name = "fundAccount", nullable = false)
	public String getFundAccount() {
		return this.fundAccount;
	}

	public void setFundAccount(String fundAccount) {
		this.fundAccount = fundAccount;
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

	@Column(name = "voucherNo", nullable = false)
	public String getVoucherNo() {
		return this.voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	@Column(name = "remark", nullable = false)
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

}