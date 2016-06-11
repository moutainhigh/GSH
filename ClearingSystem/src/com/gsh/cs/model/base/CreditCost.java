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

/**
 * CreditCost entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "credit_cost", catalog = "")
public class CreditCost implements java.io.Serializable {

	// Fields

	private Long id;
	private Date creditDate;//日期
	private String tradeNo;//交易号
	private BigDecimal saleMoney;//销售金额
	private BigDecimal costAmount;//成本金额
	private BigDecimal grossProfit;//毛利:销售金额-成本金额
	private Long userId;//用户ID
	private String deptId;//所属部门ID

	// Constructors

	/** default constructor */
	public CreditCost() {
	}

	/** full constructor */
	public CreditCost(Date creditDate, String tradeNo, BigDecimal saleMoney,
			BigDecimal costAmount, BigDecimal grossProfit, Long userId, String deptId) {
		this.creditDate = creditDate;
		this.tradeNo = tradeNo;
		this.saleMoney = saleMoney;
		this.costAmount = costAmount;
		this.grossProfit = grossProfit;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "creditDate", nullable = false, length = 10)
	public Date getCreditDate() {
		return this.creditDate;
	}

	public void setCreditDate(Date creditDate) {
		this.creditDate = creditDate;
	}

	@Column(name = "tradeNo", nullable = false)
	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	@Column(name = "saleMoney", nullable = false, precision = 10, scale = 4)
	public BigDecimal getSaleMoney() {
		return this.saleMoney;
	}

	public void setSaleMoney(BigDecimal saleMoney) {
		this.saleMoney = saleMoney;
	}

	@Column(name = "costAmount", nullable = false, precision = 10, scale = 4)
	public BigDecimal getCostAmount() {
		return this.costAmount;
	}

	public void setCostAmount(BigDecimal costAmount) {
		this.costAmount = costAmount;
	}

	@Column(name = "grossProfit", nullable = false, precision = 10, scale = 4)
	public BigDecimal getGrossProfit() {
		return this.grossProfit;
	}

	public void setGrossProfit(BigDecimal grossProfit) {
		this.grossProfit = grossProfit;
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

}