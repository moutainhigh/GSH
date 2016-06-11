package com.gsh.cs.model.base;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * SupplierPayment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "supplier_payment", catalog = "", uniqueConstraints = @UniqueConstraint(columnNames = "settlementNo"))
public class SupplierPayment implements java.io.Serializable {

	// Fields

	private Long id;// 唯一编号
	private Long handNo;// 经手人编号
	private Long applyNo;// 申请人编号
	private Date createDate;// 创建日期
	private Integer dataType;// 数据类型:1支出2退款
	private String settlementNo;// 结算编号:系统生成(唯一)
	private String exchangeNos;// 交换单号
	private String handPerson;// 经手人
	private String applyPerson;// 申请人
	private String supplierNo;// 供应商编号
	private Integer paymentMethod;// 收款方式/付款方式:1现金 /2信用卡/ 3支票 /4转账汇款/5内转
	private String supplierName;// 付款人/收款人
	private String bankAccountNo;// 账号
	private BigDecimal payAmount;// 实付金额
	private BigDecimal factorage;// 手续费
	private Date tradeDate;// 付款日期/收款日期
	private String currencyType;// 货币类型:CNY,USD,EUR…
	private String fundAccount;// 资金账户
	// private BigDecimal netAmount;//入账金额
	// private BigDecimal settlementRate;//结算汇率
	private BigDecimal bankRate;// 银行汇率
	private BigDecimal exchangeProfitLoss;// 汇兑损益
	private String tradeNo;// 交易号
	private String voucherNo;// 凭证号
	private String remark;// 备注
	private Integer noticeStatus;// 通知单状态:1未完成 2已完成
	private Integer productStatus;// 产品状态:1未完成 2已完成
	private String credentials;// 供应商凭据
	private Integer temporaryHas;// 是否包含暂支款:1不包含 2包含
	private Integer status;// 状态:1未提交 2已驳回 3审批中 4待销账 5待确认 6已确认 9已取消
	private Date deadline;// 结算期限
	private String sprName;
	private Timestamp spDate;

	// Constructors

	/** default constructor */
	public SupplierPayment() {
	}

	/** minimal constructor */
	public SupplierPayment(Date createDate, Integer dataType, String settlementNo, String supplierNo, Integer paymentMethod, String supplierName, String bankAccountNo, BigDecimal payAmount, BigDecimal factorage, Date tradeDate, String currencyType, String fundAccount, BigDecimal netAmount, BigDecimal settlementRate, BigDecimal bankRate, BigDecimal exchangeProfitLoss, String tradeNo, String voucherNo, String remark, Integer noticeStatus, Integer productStatus, String credentials, Integer temporaryHas, Integer status, Date deadline) {
		this.createDate = createDate;
		this.dataType = dataType;
		this.settlementNo = settlementNo;
		this.supplierNo = supplierNo;
		this.paymentMethod = paymentMethod;
		this.supplierName = supplierName;
		this.bankAccountNo = bankAccountNo;
		this.payAmount = payAmount;
		this.factorage = factorage;
		this.tradeDate = tradeDate;
		this.currencyType = currencyType;
		this.fundAccount = fundAccount;
		this.bankRate = bankRate;
		this.exchangeProfitLoss = exchangeProfitLoss;
		this.tradeNo = tradeNo;
		this.voucherNo = voucherNo;
		this.remark = remark;
		this.noticeStatus = noticeStatus;
		this.productStatus = productStatus;
		this.credentials = credentials;
		this.temporaryHas = temporaryHas;
		this.status = status;
		this.deadline = deadline;
	}

	/** full constructor */
	public SupplierPayment(Long handNo, Long applyNo, Date createDate, Integer dataType, String settlementNo, String handPerson, String applyPerson, String supplierNo, Integer paymentMethod, String supplierName, String bankAccountNo, BigDecimal payAmount, BigDecimal factorage, Date tradeDate, String currencyType, String fundAccount, BigDecimal netAmount, BigDecimal settlementRate, BigDecimal bankRate, BigDecimal exchangeProfitLoss, String tradeNo, String voucherNo, String remark, Integer noticeStatus, Integer productStatus, String credentials, Integer temporaryHas, Integer status, Date deadline,String sprName,Timestamp spDate) {
		this.handNo = handNo;
		this.applyNo = applyNo;
		this.createDate = createDate;
		this.dataType = dataType;
		this.settlementNo = settlementNo;
		this.handPerson = handPerson;
		this.applyPerson = applyPerson;
		this.supplierNo = supplierNo;
		this.paymentMethod = paymentMethod;
		this.supplierName = supplierName;
		this.bankAccountNo = bankAccountNo;
		this.payAmount = payAmount;
		this.factorage = factorage;
		this.tradeDate = tradeDate;
		this.currencyType = currencyType;
		this.fundAccount = fundAccount;
		this.bankRate = bankRate;
		this.exchangeProfitLoss = exchangeProfitLoss;
		this.tradeNo = tradeNo;
		this.voucherNo = voucherNo;
		this.remark = remark;
		this.noticeStatus = noticeStatus;
		this.productStatus = productStatus;
		this.credentials = credentials;
		this.temporaryHas = temporaryHas;
		this.status = status;
		this.deadline = deadline;
		this.sprName=sprName;
		this.spDate=spDate;
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

	@Column(name = "handNo")
	public Long getHandNo() {
		return this.handNo;
	}

	public void setHandNo(Long handNo) {
		this.handNo = handNo;
	}

	@Column(name = "applyNo")
	public Long getApplyNo() {
		return this.applyNo;
	}

	public void setApplyNo(Long applyNo) {
		this.applyNo = applyNo;
	}

	@Column(name = "createDate", nullable = false, length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "dataType", nullable = false)
	public Integer getDataType() {
		return this.dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	@Column(name = "settlementNo", unique = true, nullable = false)
	public String getSettlementNo() {
		return this.settlementNo;
	}

	public void setSettlementNo(String settlementNo) {
		this.settlementNo = settlementNo;
	}

	@Column(name = "handPerson")
	public String getHandPerson() {
		return this.handPerson;
	}

	public void setHandPerson(String handPerson) {
		this.handPerson = handPerson;
	}

	@Column(name = "applyPerson")
	public String getApplyPerson() {
		return this.applyPerson;
	}

	public void setApplyPerson(String applyPerson) {
		this.applyPerson = applyPerson;
	}

	@Column(name = "supplierNo", nullable = false)
	public String getSupplierNo() {
		return this.supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	@Column(name = "paymentMethod")
	public Integer getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Column(name = "supplierName", nullable = false)
	public String getSupplierName() {
		return this.supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@Column(name = "bankAccountNo", nullable = false)
	public String getBankAccountNo() {
		return this.bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	@Column(name = "payAmount", nullable = false, precision = 10)
	public BigDecimal getPayAmount() {
		return this.payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	@Column(name = "factorage", nullable = false, precision = 10)
	public BigDecimal getFactorage() {
		return this.factorage;
	}

	public void setFactorage(BigDecimal factorage) {
		this.factorage = factorage;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tradeDate", length = 10)
	public Date getTradeDate() {
		return this.tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	@Column(name = "currencyType", nullable = false)
	public String getCurrencyType() {
		return this.currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	@Column(name = "fundAccount")
	public String getFundAccount() {
		return this.fundAccount;
	}

	public void setFundAccount(String fundAccount) {
		this.fundAccount = fundAccount;
	}

	// @Column(name = "netAmount", nullable = false, precision = 10)
	// public BigDecimal getNetAmount() {
	// return this.netAmount;
	// }
	//
	// public void setNetAmount(BigDecimal netAmount) {
	// this.netAmount = netAmount;
	// }
	//
	// @Column(name = "settlementRate", nullable = false, precision = 10)
	// public BigDecimal getSettlementRate() {
	// return this.settlementRate;
	// }
	//
	// public void setSettlementRate(BigDecimal settlementRate) {
	// this.settlementRate = settlementRate;
	// }

	@Column(name = "bankRate", nullable = false, precision = 10)
	public BigDecimal getBankRate() {
		return this.bankRate;
	}

	public void setBankRate(BigDecimal bankRate) {
		this.bankRate = bankRate;
	}

	@Column(name = "exchangeProfitLoss", nullable = false, precision = 10)
	public BigDecimal getExchangeProfitLoss() {
		return this.exchangeProfitLoss;
	}

	public void setExchangeProfitLoss(BigDecimal exchangeProfitLoss) {
		this.exchangeProfitLoss = exchangeProfitLoss;
	}

	@Column(name = "tradeNo")
	public String getTradeNo() {
		return this.tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	@Column(name = "voucherNo")
	public String getVoucherNo() {
		return this.voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "noticeStatus", nullable = false)
	public Integer getNoticeStatus() {
		return this.noticeStatus;
	}

	public void setNoticeStatus(Integer noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	@Column(name = "productStatus", nullable = false)
	public Integer getProductStatus() {
		return this.productStatus;
	}

	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}

	@Column(name = "credentials")
	public String getCredentials() {
		return this.credentials;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	@Column(name = "temporaryHas", nullable = false)
	public Integer getTemporaryHas() {
		return this.temporaryHas;
	}

	public void setTemporaryHas(Integer temporaryHas) {
		this.temporaryHas = temporaryHas;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "deadline", length = 10)
	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Column(name = "exchangeNos", nullable = false)
	public String getExchangeNos() {
		return exchangeNos;
	}

	public void setExchangeNos(String exchangeNos) {
		this.exchangeNos = exchangeNos;
	}

	@Column(name = "sprName")
	public String getSprName() {
		return sprName;
	}
	
	public void setSprName(String sprName) {
		this.sprName = sprName;
	}

	@Column(name = "spDate")
	public Timestamp getSpDate() {
		return spDate;
	}
	
	public void setSpDate(Timestamp spDate) {
		this.spDate = spDate;
	}
	
	
}