package com.gsh.cs.model.base;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * BspTicket entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bsp_ticket", catalog = "")
public class BspTicket implements java.io.Serializable {

	// Fields

	private Long id;//主键ID
	private Long bspStartBank;//起始票号
	private Long bspTerminationBank;//终止票号
	private Long bspNumber;//数量
	private Integer bspType;//类型:2、国内1、国际
	private Date bspCreationDate;//创建日期
	private Double bspAllowance;//余量
	private Long bspAllowanceRemind;//余量提醒
	private Long userId;//用户ID
	private String deptId;//所属部门ID
	private Long bspStartTicket;//可卖的票号

	// Constructors

	/** default constructor */
	public BspTicket() {
	}

	/** full constructor */
	public BspTicket(Long bspStartBank, Long bspTerminationBank,
			Long bspNumber, Integer bspType, Date bspCreationDate,
			Double bspAllowance, Long bspAllowanceRemind, Long userId, String deptId) {
		this.bspStartBank = bspStartBank;
		this.bspTerminationBank = bspTerminationBank;
		this.bspNumber = bspNumber;
		this.bspType = bspType;
		this.bspCreationDate = bspCreationDate;
		this.bspAllowance = bspAllowance;
		this.bspAllowanceRemind = bspAllowanceRemind;
		this.userId = userId;
		this.deptId = deptId;
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

	@Column(name = "bspStartBank", nullable = false)
	public Long getBspStartBank() {
		return this.bspStartBank;
	}

	public void setBspStartBank(Long bspStartBank) {
		this.bspStartBank = bspStartBank;
	}

	@Column(name = "bspTerminationBank", nullable = false)
	public Long getBspTerminationBank() {
		return this.bspTerminationBank;
	}

	public void setBspTerminationBank(Long bspTerminationBank) {
		this.bspTerminationBank = bspTerminationBank;
	}

	@Column(name = "bspNumber", nullable = false)
	public Long getBspNumber() {
		return this.bspNumber;
	}

	public void setBspNumber(Long bspNumber) {
		this.bspNumber = bspNumber;
	}

	@Column(name = "bspType", nullable = false)
	public Integer getBspType() {
		return this.bspType;
	}

	public void setBspType(Integer bspType) {
		this.bspType = bspType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "bspCreationDate", nullable = false, length = 10)
	public Date getBspCreationDate() {
		return this.bspCreationDate;
	}

	public void setBspCreationDate(Date bspCreationDate) {
		this.bspCreationDate = bspCreationDate;
	}

	@Column(name = "bspAllowance", nullable = false)
	public Double getBspAllowance() {
		return this.bspAllowance;
	}

	public void setBspAllowance(Double bspAllowance) {
		this.bspAllowance = bspAllowance;
	}

	@Column(name = "bspAllowanceRemind", nullable = false)
	public Long getBspAllowanceRemind() {
		return this.bspAllowanceRemind;
	}

	public void setBspAllowanceRemind(Long bspAllowanceRemind) {
		this.bspAllowanceRemind = bspAllowanceRemind;
	}

	@Column(name = "userId", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "deptId", nullable = false)
	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name = "bspStartTicket", nullable = false)
	public Long getBspStartTicket() {
		return bspStartTicket;
	}

	public void setBspStartTicket(Long bspStartTicket) {
		this.bspStartTicket = bspStartTicket;
	}
	
}