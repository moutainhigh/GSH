package com.gsh.cs.model.base;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ReceivableDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "receivable_detail", catalog = "")
public class ReceivableDetail implements java.io.Serializable {

	// Fields

	private Long id;//唯一编号
	private Long ctpmid;//销账记录ID
	private Long rcvbid;//应收账款id
	private BigDecimal incomeAmount;//实收金额

	// Constructors

	/** default constructor */
	public ReceivableDetail() {
	}

	/** full constructor */
	public ReceivableDetail(Long ctpmid, Long rcvbid, BigDecimal incomeAmount) {
		this.ctpmid = ctpmid;
		this.rcvbid = rcvbid;
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

	@Column(name = "ctpmid", nullable = false)
	public Long getCtpmid() {
		return this.ctpmid;
	}

	public void setCtpmid(Long ctpmid) {
		this.ctpmid = ctpmid;
	}

	@Column(name = "rcvbid", nullable = false)
	public Long getRcvbid() {
		return this.rcvbid;
	}

	public void setRcvbid(Long rcvbid) {
		this.rcvbid = rcvbid;
	}

	@Column(name = "incomeAmount", nullable = false, precision = 10)
	public BigDecimal getIncomeAmount() {
		return this.incomeAmount;
	}

	public void setIncomeAmount(BigDecimal incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

}