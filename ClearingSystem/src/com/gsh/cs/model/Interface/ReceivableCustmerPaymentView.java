package com.gsh.cs.model.Interface;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class ReceivableCustmerPaymentView implements java.io.Serializable {

	// Fields

	private String exchangeNo;// 交换单号 
	private String supplierNo;// 供应商编号
	private String supplierName;// 供应商名称
	private Date tradeDate;// 交易日期 
	private String productOrderNo;// 产品单号 
	private Date deadline;// 结算期限 
	private String productNo;// 产品号 
	private Integer type;// 类型:1业务应付 2业务押金 3业务暂支 4业务应退 5预付6押金7其它8ADM9ACM10MCO
	private Date createDate;// 创建日期
	private String affiliationPerson;// 归属人
	private Long affiliationNo;// 归属人编号
	private String creater;// 创建人
	private Long createrNo;// 创建人编号
	private Integer noticeStatus;// 通知单状态:1未完成 2已完成
	private Integer productStatus;// 产品状态:1未完成 2已完成
	private Integer cancelStatus;// 申请取消状态:1正常 2申请取消
	private String currencyType;// 货币类型:CNY,USD,EUR…
	private String orderNo;// 订单号
	private Integer revocationHas;// 可否撤销 1可以 2不可以
	private String depts;
	private Integer reverseHas;// 是否反向生成 1不生成 2生成
	
	private Long id;
	private Long supmid;//销账记录id
	private Long pablid;//应付账款id
	private BigDecimal payAmount;//实付金额
	private Integer statusZanzhi;
	
	private String settlementNo;//结算单号
	private Integer paymentMethod;//付款方式
	private String voucherNo;//凭证号
	private String handPerson;//经手人
	private String remark;// 备注 
	private Integer status;//状态
	private Integer temporaryHas;// 是否包含暂支款:1不包含 2包含
	
	private Long handNo;// 经手人编号
	private Long applyNo;// 申请人编号
	private BigDecimal publication;// 客户信用卡手续费扣率%
	private BigDecimal settlement;// 结算信用卡手续费扣率%
	private Integer dataType;// 数据类型:1收入2退款
	private String applyPerson;// 申请人
	private String customerNo;// 客户编号
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
	private Date arriveDate;// 到账日期
	private String sprName;
	private Timestamp spDate;
	public String getExchangeNo() {
		return exchangeNo;
	}
	public void setExchangeNo(String exchangeNo) {
		this.exchangeNo = exchangeNo;
	}
	public String getSupplierNo() {
		return supplierNo;
	}
	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getProductOrderNo() {
		return productOrderNo;
	}
	public void setProductOrderNo(String productOrderNo) {
		this.productOrderNo = productOrderNo;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getAffiliationPerson() {
		return affiliationPerson;
	}
	public void setAffiliationPerson(String affiliationPerson) {
		this.affiliationPerson = affiliationPerson;
	}
	public Long getAffiliationNo() {
		return affiliationNo;
	}
	public void setAffiliationNo(Long affiliationNo) {
		this.affiliationNo = affiliationNo;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Long getCreaterNo() {
		return createrNo;
	}
	public void setCreaterNo(Long createrNo) {
		this.createrNo = createrNo;
	}
	public Integer getNoticeStatus() {
		return noticeStatus;
	}
	public void setNoticeStatus(Integer noticeStatus) {
		this.noticeStatus = noticeStatus;
	}
	public Integer getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}
	public Integer getCancelStatus() {
		return cancelStatus;
	}
	public void setCancelStatus(Integer cancelStatus) {
		this.cancelStatus = cancelStatus;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getRevocationHas() {
		return revocationHas;
	}
	public void setRevocationHas(Integer revocationHas) {
		this.revocationHas = revocationHas;
	}
	public String getDepts() {
		return depts;
	}
	public void setDepts(String depts) {
		this.depts = depts;
	}
	public Integer getReverseHas() {
		return reverseHas;
	}
	public void setReverseHas(Integer reverseHas) {
		this.reverseHas = reverseHas;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSupmid() {
		return supmid;
	}
	public void setSupmid(Long supmid) {
		this.supmid = supmid;
	}
	public Long getPablid() {
		return pablid;
	}
	public void setPablid(Long pablid) {
		this.pablid = pablid;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	public Integer getStatusZanzhi() {
		return statusZanzhi;
	}
	public void setStatusZanzhi(Integer statusZanzhi) {
		this.statusZanzhi = statusZanzhi;
	}
	public String getSettlementNo() {
		return settlementNo;
	}
	public void setSettlementNo(String settlementNo) {
		this.settlementNo = settlementNo;
	}
	public Integer getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}
	public String getHandPerson() {
		return handPerson;
	}
	public void setHandPerson(String handPerson) {
		this.handPerson = handPerson;
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
	public Integer getTemporaryHas() {
		return temporaryHas;
	}
	public void setTemporaryHas(Integer temporaryHas) {
		this.temporaryHas = temporaryHas;
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
	public Integer getDataType() {
		return dataType;
	}
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
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
	public Date getArriveDate() {
		return arriveDate;
	}
	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
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