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
 * AccountDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "account_detail", catalog = "", uniqueConstraints = @UniqueConstraint(columnNames = "settlementNo"))
public class AccountDetail implements java.io.Serializable {

	// Fields

	private Long id;//唯一编号
	private String accountCode;//账户编号
	private String accountBank;//开户行
	private Date tradeDate;//交易日期
	private String tradeNo;//交易编号
	private String tradeObject;//交易对象
	private BigDecimal income;//期间收入
	private BigDecimal expenditure;//期间支出
	private BigDecimal balance;//余额
	private String voucherNo;//凭证号
	private String remark;//备注
	private Integer status;//状态:1未复核2已复核
	private Integer type;//数据类型:1应收 2应付 3佣金 
	private String settlementNo;//结算编号
	private String subjects;//科目
	private String currencyType;// 货币类型:CNY,USD,EUR…
	private Long cid;

	// Constructors

	/** default constructor */
	public AccountDetail() {
	}

	/** full constructor */
	public AccountDetail(String accountCode, String accountBank,
			Date tradeDate, String tradeNo, String tradeObject, BigDecimal income,
			BigDecimal expenditure, BigDecimal balance, String voucherNo,
			String remark, Integer status, Integer type, String settlementNo,
			String subjects,String currencyType,Long cid) {
		this.accountCode = accountCode;
		this.accountBank = accountBank;
		this.tradeDate = tradeDate;
		this.tradeNo = tradeNo;
		this.tradeObject = tradeObject;
		this.income = income;
		this.expenditure = expenditure;
		this.balance = balance;
		this.voucherNo = voucherNo;
		this.remark = remark;
		this.status = status;
		this.type = type;
		this.settlementNo = settlementNo;
		this.subjects = subjects;
		this.currencyType=currencyType;
		this.cid=cid;
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

	@Column(name = "accountCode", nullable = false)
	public String getAccountCode() {
		return this.accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	@Column(name = "accountBank", nullable = false)
	public String getAccountBank() {
		return this.accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tradeDate", nullable = false, length = 10)
	public Date getTradeDate() {
		return this.tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	@Column(name = "tradeNo")
	public String getTradeNo() {
		return this.tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	@Column(name = "tradeObject", nullable = false)
	public String getTradeObject() {
		return this.tradeObject;
	}

	public void setTradeObject(String tradeObject) {
		this.tradeObject = tradeObject;
	}

	@Column(name = "income", nullable = false, precision = 10)
	public BigDecimal getIncome() {
		return this.income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	@Column(name = "expenditure", nullable = false, precision = 10)
	public BigDecimal getExpenditure() {
		return this.expenditure;
	}

	public void setExpenditure(BigDecimal expenditure) {
		this.expenditure = expenditure;
	}

	@Column(name = "balance", nullable = false, precision = 10)
	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Column(name = "voucherNo", nullable = false)
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

	@Column(name = "type", nullable = false)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "settlementNo", unique = true, nullable = false)
	public String getSettlementNo() {
		return this.settlementNo;
	}

	public void setSettlementNo(String settlementNo) {
		this.settlementNo = settlementNo;
	}

	@Column(name = "subjects", nullable = false)
	public String getSubjects() {
		return this.subjects;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}
	
	@Column(name = "currencyType", nullable = false)
	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
	@Column(name = "cid", nullable = false)
	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}
	
}