package com.gsh.cs.model.Interface;

public class AccountsReceivable {
	private String customerIdAndName;
	private String noticeNo;//通知单号
	private String tradeDate;//交易日期
	private String orderNo;//订单号
	private String deadline;//结算期限
	private String passengerName;//旅客信息
	private String confirmationNumber;//确认号
	private String incomeBeAmount;//通知单金额incomeAmount
	private String incomeAmount;//支付金额
	private Double balance;//余额
	public String getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public String getConfirmationNumber() {
		return confirmationNumber;
	}
	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}
	public String getIncomeBeAmount() {
		return incomeBeAmount;
	}
	public void setIncomeBeAmount(String incomeBeAmount) {
		this.incomeBeAmount = incomeBeAmount;
	}
	public String getIncomeAmount() {
		return incomeAmount;
	}
	public void setIncomeAmount(String incomeAmount) {
		this.incomeAmount = incomeAmount;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getCustomerIdAndName() {
		return customerIdAndName;
	}
	public void setCustomerIdAndName(String customerIdAndName) {
		this.customerIdAndName = customerIdAndName;
	}

}
