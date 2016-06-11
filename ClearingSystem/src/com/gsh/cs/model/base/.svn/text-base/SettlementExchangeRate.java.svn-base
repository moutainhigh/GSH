package com.gsh.cs.model.base;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "settlement_exchange_rate", catalog = "")
public class SettlementExchangeRate implements java.io.Serializable {

	private Integer id;
	private String code;//货币代码
	private BigDecimal buying;//买入价
	private BigDecimal selling;//卖出价
	private Date validity;//有效期
	private Date establishDate;//创建时间
	private Integer settlementexchangerateLock;//是否锁定
	private Long userId;//用户ID
	private Long adminId;//管理员ID
	private String deptId; //部门ID
	private String name;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "code", nullable = false, length = 3)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "buying", nullable = false)
	public BigDecimal getBuying() {
		return buying;
	}

	public void setBuying(BigDecimal buying) {
		this.buying = buying;
	}

	@Column(name = "selling", nullable = false)
	public BigDecimal getSelling() {
		return selling;
	}

	public void setSelling(BigDecimal selling) {
		this.selling = selling;
	}

	@Column(name = "validity", nullable = false, length = 10)
	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}
	
	@Column(name = "establishDate", nullable = false, length = 10)
	public Date getEstablishDate() {
		return establishDate;
	}

	public void setEstablishDate(Date establishDate) {
		this.establishDate = establishDate;
	}
	
	@Column(name = "settlementexchangerateLock", nullable = false)
	public Integer getSettlementexchangerateLock() {
		return settlementexchangerateLock;
	}

	public void setSettlementexchangerateLock(Integer settlementexchangerateLock) {
		this.settlementexchangerateLock = settlementexchangerateLock;
	}
	
	@Column(name = "userId", nullable = false)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "adminId", nullable = false)
	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	
	@Column(name = "deptId", nullable = false)
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
