package com.gsh.cs.model.base;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * DeadlineChg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "deadline_chg", catalog = "")
public class DeadlineChg implements java.io.Serializable {

	// Fields

	private Long id;
	private Long rcvbid;
	private Date modifiedBefore;
	private Date modifiedOn;
	private Date modifiedDate;
	private String modifier;
	private Long modifierNo;
	private String remark;

	// Constructors

	/** default constructor */
	public DeadlineChg() {
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

	@Column(name = "rcvbid", nullable = false)
	public Long getRcvbid() {
		return rcvbid;
	}

	public void setRcvbid(Long rcvbid) {
		this.rcvbid = rcvbid;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "modifiedBefore", nullable = false, length = 10)
	public Date getModifiedBefore() {
		return this.modifiedBefore;
	}

	public void setModifiedBefore(Date modifiedBefore) {
		this.modifiedBefore = modifiedBefore;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "modifiedOn", nullable = false, length = 10)
	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "modifiedDate", nullable = false, length = 19)
	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name = "modifier", nullable = false)
	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	@Column(name = "modifierNo", nullable = false)
	public Long getModifierNo() {
		return this.modifierNo;
	}

	public void setModifierNo(Long modifierNo) {
		this.modifierNo = modifierNo;
	}

	@Column(name = "remark", nullable = false)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}