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
 * Account entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "account", catalog = "")
public class Account implements java.io.Serializable {

	// Fields

	private Long id;//主键ID
	private String accountCode;//账户编码(唯一)
	private String accountType;//账户类型:C=公司，P=个人，M=现金，X=信用卡，O=第三方支付
	private String accountCurrency;//账户币种
	private String accountOwner;//开户人
	private String accountBank;//开户银行
	private String accountNumber;//账户号码
	private BigDecimal accountBalance;//账户结余
	private Integer accountStatus;//状态:0、未激活1、使用中2、已挂起3、已注销
	private BigDecimal accountReminder;//余额提醒
	private String accountBankAdress;//开户行地址
	private String accountBankNumber;//行号/SWIFT
	private Integer defaultIncAccount;//是否默认收款账户:0、否1、是
	private Integer defaultPayAccount;//是否默认付款账户:0、否1、是
	private Integer accountLock;//是否锁定:0、以锁1、未锁
	private Long userId;//用户ID
	private Long adminId;//管理员ID
	private String deptId;//所属部门ID
	private Date establishDate;//创建时间

	// Constructors

	/** default constructor */
	public Account() {
	}

	/** minimal constructor */
	public Account(String accountCode, String accountType,
			String accountCurrency, String accountOwner, String accountBank,
			String accountNumber, BigDecimal accountBalance, Integer accountStatus,
			BigDecimal accountReminder, String accountBankAdress,
			String accountBankNumber, Integer accountLock, Long userId,
			Long adminId, String deptId,Date establishDate) {
		this.accountCode = accountCode;
		this.accountType = accountType;
		this.accountCurrency = accountCurrency;
		this.accountOwner = accountOwner;
		this.accountBank = accountBank;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.accountStatus = accountStatus;
		this.accountReminder = accountReminder;
		this.accountBankAdress = accountBankAdress;
		this.accountBankNumber = accountBankNumber;
		this.accountLock = accountLock;
		this.userId = userId;
		this.adminId = adminId;
		this.deptId = deptId;
		this.establishDate=establishDate;
	}

	/** full constructor */
	public Account(String accountCode, String accountType,
			String accountCurrency, String accountOwner, String accountBank,
			String accountNumber, BigDecimal accountBalance, Integer accountStatus,
			BigDecimal accountReminder, String accountBankAdress,
			String accountBankNumber, Integer defaultIncAccount,
			Integer defaultPayAccount, Integer accountLock, Long userId,
			Long adminId, String deptId,Date establishDate) {
		this.accountCode = accountCode;
		this.accountType = accountType;
		this.accountCurrency = accountCurrency;
		this.accountOwner = accountOwner;
		this.accountBank = accountBank;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.accountStatus = accountStatus;
		this.accountReminder = accountReminder;
		this.accountBankAdress = accountBankAdress;
		this.accountBankNumber = accountBankNumber;
		this.defaultIncAccount = defaultIncAccount;
		this.defaultPayAccount = defaultPayAccount;
		this.accountLock = accountLock;
		this.userId = userId;
		this.adminId = adminId;
		this.deptId = deptId;
		this.establishDate=establishDate;
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

	@Column(name = "accountType", nullable = false, length = 1)
	public String getAccountType() {
		return this.accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Column(name = "accountCurrency", nullable = false, length = 10)
	public String getAccountCurrency() {
		return this.accountCurrency;
	}

	public void setAccountCurrency(String accountCurrency) {
		this.accountCurrency = accountCurrency;
	}

	@Column(name = "accountOwner", nullable = false)
	public String getAccountOwner() {
		return this.accountOwner;
	}

	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	@Column(name = "accountBank", nullable = false, length = 50)
	public String getAccountBank() {
		return this.accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	@Column(name = "accountNumber", nullable = false, length = 50)
	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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

	@Column(name = "accountReminder", nullable = false, precision = 10, scale = 4)
	public BigDecimal getAccountReminder() {
		return this.accountReminder;
	}

	public void setAccountReminder(BigDecimal accountReminder) {
		this.accountReminder = accountReminder;
	}

	@Column(name = "accountBankAdress", nullable = false)
	public String getAccountBankAdress() {
		return this.accountBankAdress;
	}

	public void setAccountBankAdress(String accountBankAdress) {
		this.accountBankAdress = accountBankAdress;
	}

	@Column(name = "accountBankNumber", nullable = false, length = 20)
	public String getAccountBankNumber() {
		return this.accountBankNumber;
	}

	public void setAccountBankNumber(String accountBankNumber) {
		this.accountBankNumber = accountBankNumber;
	}

	@Column(name = "defaultIncAccount")
	public Integer getDefaultIncAccount() {
		return this.defaultIncAccount;
	}

	public void setDefaultIncAccount(Integer defaultIncAccount) {
		this.defaultIncAccount = defaultIncAccount;
	}

	@Column(name = "defaultPayAccount")
	public Integer getDefaultPayAccount() {
		return this.defaultPayAccount;
	}

	public void setDefaultPayAccount(Integer defaultPayAccount) {
		this.defaultPayAccount = defaultPayAccount;
	}
	
	@Column(name = "accountLock", nullable = false)
	public Integer getAccountLock() {
		return this.accountLock;
	}

	public void setAccountLock(Integer accountLock) {
		this.accountLock = accountLock;
	}

	@Column(name = "userId", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "adminId", nullable = false)
	public Long getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	
	@Column(name = "deptId", nullable = false)
	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	@Column(name = "establishDate", nullable = false, length = 19)
	public Date getEstablishDate() {
		return establishDate;
	}

	public void setEstablishDate(Date establishDate) {
		this.establishDate = establishDate;
	}
}