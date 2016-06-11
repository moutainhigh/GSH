package com.gsh.cs.model.base;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PayableDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "payable_detail", catalog = "")
public class PayableDetail implements java.io.Serializable {

	// Fields

	private Long id;//唯一编号
	private Long supmid;//销账记录id
	private Long pablid;//应付账款id
	private BigDecimal payAmount;//实付金额
	private Integer statusZanzhi;//录入状态

	// Constructors

	/** default constructor */
	public PayableDetail() {
	}

	/** full constructor */
	public PayableDetail(Long supmid, Long pablid, BigDecimal payAmount,Integer statusZanzhi) {
		this.supmid = supmid;
		this.pablid = pablid;
		this.payAmount = payAmount;
		this.statusZanzhi=statusZanzhi;
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

	@Column(name = "supmid", nullable = false)
	public Long getSupmid() {
		return this.supmid;
	}

	public void setSupmid(Long supmid) {
		this.supmid = supmid;
	}

	@Column(name = "pablid", nullable = false)
	public Long getPablid() {
		return this.pablid;
	}

	public void setPablid(Long pablid) {
		this.pablid = pablid;
	}

	@Column(name = "payAmount", nullable = false, precision = 10)
	public BigDecimal getPayAmount() {
		return this.payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	@Column(name = "statusZanzhi", nullable = false, precision = 10)
	public Integer getStatusZanzhi() {
		return statusZanzhi;
	}

	public void setStatusZanzhi(Integer statusZanzhi) {
		this.statusZanzhi = statusZanzhi;
	}
	
	
}