package com.gsh.cs.model.parameter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class CustomerPaymentPRMT {
	private int page;
	private int rows;
	private String sort;
	private String order;
	private Long id;// 唯一编号
	private Long handNo;// 经手人编号
	private Long applyNo;// 申请人编号
	private BigDecimal publication;// 客户信用卡手续费扣率%
	private BigDecimal settlement;// 结算信用卡手续费扣率%
	private Date createDate;// 创建日期
	private Integer dataType;// 数据类型:1收入2退款
	private String settlementNo;// 结算编号:系统生成(唯一)
	private String handPerson;// 经手人
	private String applyPerson;// 申请人
	private String customerNo;// 客户编号
	private Integer paymentMethod;// 收款方式/付款方式：1现金 /2信用卡/ 3支票 /4转账汇款/5内转
	private String customerName;// 付款人/收款人
	private String bankAccountNo;// 账号
	private String cardCode;// 信用卡代码
	private String cardExpirationDate;// 信用卡有效期：格式 13/08 日/月
	private BigDecimal incomeAmount;// 实收金额
	private BigDecimal factorage;// 手续费
	private BigDecimal customerFactorage;// 客户手续费
	private Date incomeDate;// 收款/付款日期
	private String fundAccount;// 资金账户
	private BigDecimal netAmount;// 入账金额
	private BigDecimal bankRate;// 银行汇率 结算汇率
	private BigDecimal exchangeProfitLoss;// 汇兑损益
	private String tradeNo;// 交易号
	private String voucherNo;// 凭证号
	private String remark;// 备注
	private Integer status;// 状态：1未提交 2已驳回 3审批中 4待销账 5未到账 6已到账 9已取消
	private Date arriveDate;// 到账日期
	private Long cid;
	private String sprName;
	private Timestamp spDate;

	private String statusCompare;// 状态比较符号
	private Date incomeDateQi;
	private Date incomeDateShi;
	

	public Date getIncomeDateQi() {
		return incomeDateQi;
	}

	public void setIncomeDateQi(Date incomeDateQi) {
		this.incomeDateQi = incomeDateQi;
	}

	public Date getIncomeDateShi() {
		return incomeDateShi;
	}

	public void setIncomeDateShi(Date incomeDateShi) {
		this.incomeDateShi = incomeDateShi;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHandNo() {
		return handNo;
	}

	public void setHandNo(Long handNo) {
		this.handNo = handNo;
	}

	public Long getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(Long applyNo) {
		this.applyNo = applyNo;
	}

	public BigDecimal getPublication() {
		return publication;
	}

	public void setPublication(BigDecimal publication) {
		this.publication = publication;
	}

	public BigDecimal getSettlement() {
		return settlement;
	}

	public void setSettlement(BigDecimal settlement) {
		this.settlement = settlement;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public String getSettlementNo() {
		return settlementNo;
	}

	public void setSettlementNo(String settlementNo) {
		this.settlementNo = settlementNo;
	}

	public String getHandPerson() {
		return handPerson;
	}

	public void setHandPerson(String handPerson) {
		this.handPerson = handPerson;
	}

	public String getApplyPerson() {
		return applyPerson;
	}

	public void setApplyPerson(String applyPerson) {
		this.applyPerson = applyPerson;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getCardExpirationDate() {
		return cardExpirationDate;
	}

	public void setCardExpirationDate(String cardExpirationDate) {
		this.cardExpirationDate = cardExpirationDate;
	}

	public BigDecimal getIncomeAmount() {
		return incomeAmount;
	}

	public void setIncomeAmount(BigDecimal incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

	public BigDecimal getFactorage() {
		return factorage;
	}

	public void setFactorage(BigDecimal factorage) {
		this.factorage = factorage;
	}

	public BigDecimal getCustomerFactorage() {
		return customerFactorage;
	}

	public void setCustomerFactorage(BigDecimal customerFactorage) {
		this.customerFactorage = customerFactorage;
	}

	public Date getIncomeDate() {
		return incomeDate;
	}

	public void setIncomeDate(Date incomeDate) {
		this.incomeDate = incomeDate;
	}

	public String getFundAccount() {
		return fundAccount;
	}

	public void setFundAccount(String fundAccount) {
		this.fundAccount = fundAccount;
	}

	public BigDecimal getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public BigDecimal getBankRate() {
		return bankRate;
	}

	public void setBankRate(BigDecimal bankRate) {
		this.bankRate = bankRate;
	}

	public BigDecimal getExchangeProfitLoss() {
		return exchangeProfitLoss;
	}

	public void setExchangeProfitLoss(BigDecimal exchangeProfitLoss) {
		this.exchangeProfitLoss = exchangeProfitLoss;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusCompare() {
		return statusCompare;
	}

	public void setStatusCompare(String statusCompare) {
		this.statusCompare = statusCompare;
	}

	public Date getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getSprName() {
		return sprName;
	}

	public void setSprName(String sprName) {
		this.sprName = sprName;
	}

	public Timestamp getSpDate() {
		return spDate;
	}

	public void setSpDate(Timestamp spDate) {
		this.spDate = spDate;
	}

}
