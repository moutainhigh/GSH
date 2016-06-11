package com.gsh.cs.model.base;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Collectpayaccount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "collectpay_account", catalog = "")
public class CollectpayAccount implements java.io.Serializable {

	// Fields

	private Long id;
	private String accountCode;//账号编码(唯一)
	private String accountType;//账户类型
	private String accountName;//账户名称
	private BigDecimal accountBalance;//期初余额
	private Integer accountStatus;//账户状态:0、未激活1、已激活
	private Integer accountSource;//账户来源:1、crm2、供应商
	private Date accountDate;//创建时间
	private Long cid;

	// Constructors

	/** default constructor */
	public CollectpayAccount() {
	}

	/** full constructor */
	public CollectpayAccount(String accountCode, String accountType,
			String accountName, BigDecimal accountBalance, Integer accountStatus,
			Integer accountSource,Date accountDate,Long cid) {
		this.accountCode = accountCode;
		this.accountType = accountType;
		this.accountName = accountName;
		this.accountBalance = accountBalance;
		this.accountStatus = accountStatus;
		this.accountSource = accountSource;
		this.accountDate=accountDate;
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

	@Column(name = "accountCode", nullable = false, length = 20)
	public String getAccountCode() {
		return this.accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	@Column(name = "accountType", nullable = false, length = 50)
	public String getAccountType() {
		return this.accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Column(name = "accountName", nullable = false, length = 50)
	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Column(name = "accountBalance", nullable = false, precision = 10, scale = 4)
	public BigDecimal getAccountBalance() {
		return this.accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Column(name = "accountStatus", nullable = false)
	public Integer getAccountStatus() {
		return this.accountStatus;
	}

	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Column(name = "accountSource", nullable = false)
	public Integer getAccountSource() {
		return this.accountSource;
	}

	public void setAccountSource(Integer accountSource) {
		this.accountSource = accountSource;
	}
	@Column(name = "accountDate", nullable = false, length = 19)
	public Date getAccountDate() {
		return accountDate;
	}

	public void setAccountDate(Date accountDate) {
		this.accountDate = accountDate;
	}
	
	@Column(name = "cid", unique = true, nullable = false)
	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}
}