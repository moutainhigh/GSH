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
 * Creditcardfee entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "creditcard_fee", catalog = "")
public class CreditcardFee implements java.io.Serializable {

	// Fields

	private Long id;
	private String creditCode;//信用卡代码
	private String creditDesc;//描述
	private BigDecimal publication;//公布手续费（%）
	private BigDecimal settlement;//结算手续费（%）
	private Date validtime;//生效日期
	private Date expirationdate;//失效日期
	private Integer creditcardfeeLock;//是否锁定:0、以锁1、未锁
	private Long userId;//用户ID
	private Long adminId;//管理员ID
	private String deptId;//所属部门ID
	private Date establishDate;//创建时间

	// Constructors

	/** default constructor */
	public CreditcardFee() {
	}

	/** minimal constructor */
	public CreditcardFee(String creditCode, BigDecimal publication,
			BigDecimal settlement, Date validtime, Date expirationdate,
			Integer creditcardfeeLock, Long userId, Long adminId, String deptId,
			Date establishDate) {
		this.creditCode = creditCode;
		this.publication = publication;
		this.settlement = settlement;
		this.validtime = validtime;
		this.expirationdate = expirationdate;
		this.creditcardfeeLock = creditcardfeeLock;
		this.userId = userId;
		this.adminId = adminId;
		this.deptId = deptId;
		this.establishDate=establishDate;
	}

	/** full constructor */
	public CreditcardFee(String creditCode, String creditDesc,
			BigDecimal publication, BigDecimal settlement, Date validtime,
			Date expirationdate, Integer creditcardfeeLock, Long userId,
			Long adminId, String deptId,Date establishDate) {
		this.creditCode = creditCode;
		this.creditDesc = creditDesc;
		this.publication = publication;
		this.settlement = settlement;
		this.validtime = validtime;
		this.expirationdate = expirationdate;
		this.creditcardfeeLock = creditcardfeeLock;
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

	@Column(name = "creditCode", nullable = false, length = 50)
	public String getCreditCode() {
		return this.creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	@Column(name = "creditDesc")
	public String getCreditDesc() {
		return this.creditDesc;
	}

	public void setCreditDesc(String creditDesc) {
		this.creditDesc = creditDesc;
	}

	@Column(name = "publication", nullable = false, precision = 10, scale = 4)
	public BigDecimal getPublication() {
		return this.publication;
	}

	public void setPublication(BigDecimal publication) {
		this.publication = publication;
	}

	@Column(name = "settlement", nullable = false, precision = 10, scale = 4)
	public BigDecimal getSettlement() {
		return this.settlement;
	}

	public void setSettlement(BigDecimal settlement) {
		this.settlement = settlement;
	}

	@Column(name = "validtime", nullable = false, length = 19)
	public Date getValidtime() {
		return this.validtime;
	}

	public void setValidtime(Date validtime) {
		this.validtime = validtime;
	}

	@Column(name = "expirationdate", nullable = false, length = 19)
	public Date getExpirationdate() {
		return this.expirationdate;
	}

	public void setExpirationdate(Date expirationdate) {
		this.expirationdate = expirationdate;
	}
	
	@Column(name = "creditcardfeeLock", nullable = false)
	public Integer getCreditcardfeeLock() {
		return this.creditcardfeeLock;
	}

	public void setCreditcardfeeLock(Integer creditcardfeeLock) {
		this.creditcardfeeLock = creditcardfeeLock;
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