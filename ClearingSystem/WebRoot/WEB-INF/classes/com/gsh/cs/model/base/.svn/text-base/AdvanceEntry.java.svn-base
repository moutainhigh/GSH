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
 * AdvanceEntry entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "advance_entry", catalog = "")
public class AdvanceEntry implements java.io.Serializable {

	// Fields

	private Long id;
	private String settlementNo;
	private Integer transactionType;
	private Date transactionDate;
	private BigDecimal amountMoney;
	private Integer voucherType;
	private Date invoiceDate;
	private BigDecimal invoiceMoney;
	private String invoiceNo;
	private Integer status;

	// Constructors

	/** default constructor */
	public AdvanceEntry() {
	}

	/** full constructor */
	public AdvanceEntry(String settlementNo, Integer transactionType,
			Date transactionDate, BigDecimal amountMoney, Integer voucherType,
			Date invoiceDate, BigDecimal invoiceMoney, String invoiceNo,
			Integer status) {
		this.settlementNo = settlementNo;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.amountMoney = amountMoney;
		this.voucherType = voucherType;
		this.invoiceDate = invoiceDate;
		this.invoiceMoney = invoiceMoney;
		this.invoiceNo = invoiceNo;
		this.status = status;
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

	@Column(name = "settlementNo", nullable = false)
	public String getSettlementNo() {
		return this.settlementNo;
	}

	public void setSettlementNo(String settlementNo) {
		this.settlementNo = settlementNo;
	}

	@Column(name = "transactionType", nullable = false)
	public Integer getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "transactionDate", nullable = false, length = 10)
	public Date getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Column(name = "amountMoney", nullable = false, precision = 10)
	public BigDecimal getAmountMoney() {
		return this.amountMoney;
	}

	public void setAmountMoney(BigDecimal amountMoney) {
		this.amountMoney = amountMoney;
	}

	@Column(name = "voucherType", nullable = false)
	public Integer getVoucherType() {
		return this.voucherType;
	}

	public void setVoucherType(Integer voucherType) {
		this.voucherType = voucherType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "invoiceDate", nullable = false, length = 10)
	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	@Column(name = "invoiceMoney", nullable = false, precision = 10)
	public BigDecimal getInvoiceMoney() {
		return this.invoiceMoney;
	}

	public void setInvoiceMoney(BigDecimal invoiceMoney) {
		this.invoiceMoney = invoiceMoney;
	}

	@Column(name = "invoiceNo", nullable = false)
	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}