package com.gsh.cs.model.base;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CommissionDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "commission_detail", catalog = "")
public class CommissionDetail implements java.io.Serializable {

	// Fields

	private Long id;//唯一编号
	private Long cmpmid;//销账ID
	private Long cmsnid;//佣金ID
	private BigDecimal incomeAmount;//收入金额

	// Constructors

	/** default constructor */
	public CommissionDetail() {
	}

	/** full constructor */
	public CommissionDetail(Long cmpmid, Long cmsnid, BigDecimal incomeAmount) {
		this.cmpmid = cmpmid;
		this.cmsnid = cmsnid;
		this.incomeAmount = incomeAmount;
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

	@Column(name = "cmpmid", nullable = false)
	public Long getCmpmid() {
		return this.cmpmid;
	}

	public void setCmpmid(Long cmpmid) {
		this.cmpmid = cmpmid;
	}

	@Column(name = "cmsnid", nullable = false)
	public Long getCmsnid() {
		return this.cmsnid;
	}

	public void setCmsnid(Long cmsnid) {
		this.cmsnid = cmsnid;
	}

	@Column(name = "incomeAmount", nullable = false, precision = 10)
	public BigDecimal getIncomeAmount() {
		return this.incomeAmount;
	}

	public void setIncomeAmount(BigDecimal incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

}