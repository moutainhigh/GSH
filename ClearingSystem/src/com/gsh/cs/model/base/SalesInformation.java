package com.gsh.cs.model.base;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SalesInformation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sales_information", catalog = "clearing")
public class SalesInformation implements java.io.Serializable {

	// Fields

	private Long id;
	private String passengerInfo;//旅客
	private String cpCodeMainOrder;//客户编号
	private String cpNameMainOrder;//客户名称
	private String tickets;//票号
	private BigDecimal salesPrice;//销售价
	private BigDecimal costPrice;//成本价
	private String suppliersCode;//供应商ID
	private String suppliersName;//供应商Name
	private Integer status;//状态  1、未验证2、已验证
	private BigDecimal costScny;//scny
	private BigDecimal costC;//C值
	private BigDecimal costTax;//成本税
	private BigDecimal costTotal;//成本总价
	private BigDecimal salesFcny;//fcny
	private BigDecimal salesNone;//让利
	private BigDecimal salesTax;//销售税
	private BigDecimal salesServicePrice;//服务费
	private BigDecimal salesTotal;//销售总价
	private String paymentMethodCode;//付款方式Id
	private String paymentMethodName;//付款方式
	private String receivablesMethodCode;//收款方式
	private String receivablesMethodName;//收款方式
	private Timestamp createDate;
	private String linkman;//联系人
	private String linkmanPhone;//电话
	private Integer proType;//产品类型
	private Integer tgType;//退改类型
	private String oldLineInfo;//航程信息
	private String currencyType;// 货币类型:CNY,USD,EUR…
	private String codeProdouctOrder;// 产品单号


	// Constructors

		/** default constructor */
		public SalesInformation() {
		}

		/** minimal constructor */
		public SalesInformation(String passengerInfo, String cpCodeMainOrder,
				String cpNameMainOrder, String tickets, Integer status,
				Timestamp createDate,String codeProdouctOrder) {
			this.passengerInfo = passengerInfo;
			this.cpCodeMainOrder = cpCodeMainOrder;
			this.cpNameMainOrder = cpNameMainOrder;
			this.tickets = tickets;
			this.status = status;
			this.createDate = createDate;
			this.codeProdouctOrder = codeProdouctOrder;
		}

		/** full constructor */
		public SalesInformation(String passengerInfo, String cpCodeMainOrder,
				String cpNameMainOrder, String tickets, BigDecimal salesPrice,
				BigDecimal costPrice, String suppliersCode, String suppliersName,
				Integer status, BigDecimal costScny, BigDecimal costC, BigDecimal costTax,
				BigDecimal costTotal, BigDecimal salesFcny, BigDecimal salesNone, BigDecimal salesTax,
				BigDecimal salesServicePrice, BigDecimal salesTotal, String paymentMethodCode,
				String paymentMethodName, String receivablesMethodCode,
				String receivablesMethodName, Timestamp createDate, String linkman,
				String linkmanPhone, Integer proType, Integer tgType,
				String oldLineInfo,String currencyType,String codeProdouctOrder) {
			this.passengerInfo = passengerInfo;
			this.cpCodeMainOrder = cpCodeMainOrder;
			this.cpNameMainOrder = cpNameMainOrder;
			this.tickets = tickets;
			this.salesPrice = salesPrice;
			this.costPrice = costPrice;
			this.suppliersCode = suppliersCode;
			this.suppliersName = suppliersName;
			this.status = status;
			this.costScny = costScny;
			this.costC = costC;
			this.costTax = costTax;
			this.costTotal = costTotal;
			this.salesFcny = salesFcny;
			this.salesNone = salesNone;
			this.salesTax = salesTax;
			this.salesServicePrice = salesServicePrice;
			this.salesTotal = salesTotal;
			this.paymentMethodCode = paymentMethodCode;
			this.paymentMethodName = paymentMethodName;
			this.receivablesMethodCode = receivablesMethodCode;
			this.receivablesMethodName = receivablesMethodName;
			this.createDate = createDate;
			this.linkman = linkman;
			this.linkmanPhone = linkmanPhone;
			this.proType = proType;
			this.tgType = tgType;
			this.oldLineInfo = oldLineInfo;
			this.currencyType=currencyType;
			this.codeProdouctOrder=codeProdouctOrder;
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

		@Column(name = "passengerInfo", nullable = false)
		public String getPassengerInfo() {
			return this.passengerInfo;
		}

		public void setPassengerInfo(String passengerInfo) {
			this.passengerInfo = passengerInfo;
		}

		@Column(name = "cpCodeMainOrder", nullable = false)
		public String getCpCodeMainOrder() {
			return this.cpCodeMainOrder;
		}

		public void setCpCodeMainOrder(String cpCodeMainOrder) {
			this.cpCodeMainOrder = cpCodeMainOrder;
		}

		@Column(name = "cpNameMainOrder", nullable = false)
		public String getCpNameMainOrder() {
			return this.cpNameMainOrder;
		}

		public void setCpNameMainOrder(String cpNameMainOrder) {
			this.cpNameMainOrder = cpNameMainOrder;
		}

		@Column(name = "tickets", nullable = false)
		public String getTickets() {
			return this.tickets;
		}

		public void setTickets(String tickets) {
			this.tickets = tickets;
		}

		@Column(name = "salesPrice", precision = 10, scale = 0)
		public BigDecimal getSalesPrice() {
			return this.salesPrice;
		}

		public void setSalesPrice(BigDecimal salesPrice) {
			this.salesPrice = salesPrice;
		}

		@Column(name = "costPrice", precision = 10, scale = 0)
		public BigDecimal getCostPrice() {
			return this.costPrice;
		}

		public void setCostPrice(BigDecimal costPrice) {
			this.costPrice = costPrice;
		}

		@Column(name = "suppliersCode", length = 255)
		public String getSuppliersCode() {
			return this.suppliersCode;
		}

		public void setSuppliersCode(String suppliersCode) {
			this.suppliersCode = suppliersCode;
		}

		@Column(name = "suppliersName")
		public String getSuppliersName() {
			return this.suppliersName;
		}

		public void setSuppliersName(String suppliersName) {
			this.suppliersName = suppliersName;
		}

		@Column(name = "status", nullable = false)
		public Integer getStatus() {
			return this.status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		@Column(name = "costScny", precision = 10, scale = 0)
		public BigDecimal getCostScny() {
			return this.costScny;
		}

		public void setCostScny(BigDecimal costScny) {
			this.costScny = costScny;
		}

		@Column(name = "costC", precision = 10, scale = 0)
		public BigDecimal getCostC() {
			return this.costC;
		}

		public void setCostC(BigDecimal costC) {
			this.costC = costC;
		}

		@Column(name = "costTax", precision = 10, scale = 0)
		public BigDecimal getCostTax() {
			return this.costTax;
		}

		public void setCostTax(BigDecimal costTax) {
			this.costTax = costTax;
		}

		@Column(name = "costTotal", precision = 10, scale = 0)
		public BigDecimal getCostTotal() {
			return this.costTotal;
		}

		public void setCostTotal(BigDecimal costTotal) {
			this.costTotal = costTotal;
		}

		@Column(name = "salesFcny", precision = 10, scale = 0)
		public BigDecimal getSalesFcny() {
			return this.salesFcny;
		}

		public void setSalesFcny(BigDecimal salesFcny) {
			this.salesFcny = salesFcny;
		}

		@Column(name = "salesNone", precision = 10, scale = 0)
		public BigDecimal getSalesNone() {
			return this.salesNone;
		}

		public void setSalesNone(BigDecimal salesNone) {
			this.salesNone = salesNone;
		}

		@Column(name = "salesTax", precision = 10, scale = 0)
		public BigDecimal getSalesTax() {
			return this.salesTax;
		}

		public void setSalesTax(BigDecimal salesTax) {
			this.salesTax = salesTax;
		}

		@Column(name = "salesServicePrice", precision = 10, scale = 0)
		public BigDecimal getSalesServicePrice() {
			return this.salesServicePrice;
		}

		public void setSalesServicePrice(BigDecimal salesServicePrice) {
			this.salesServicePrice = salesServicePrice;
		}

		@Column(name = "salesTotal", precision = 10, scale = 0)
		public BigDecimal getSalesTotal() {
			return this.salesTotal;
		}

		public void setSalesTotal(BigDecimal salesTotal) {
			this.salesTotal = salesTotal;
		}

		@Column(name = "paymentMethodCode")
		public String getPaymentMethodCode() {
			return this.paymentMethodCode;
		}

		public void setPaymentMethodCode(String paymentMethodCode) {
			this.paymentMethodCode = paymentMethodCode;
		}

		@Column(name = "paymentMethodName")
		public String getPaymentMethodName() {
			return this.paymentMethodName;
		}

		public void setPaymentMethodName(String paymentMethodName) {
			this.paymentMethodName = paymentMethodName;
		}

		@Column(name = "receivablesMethodCode")
		public String getReceivablesMethodCode() {
			return this.receivablesMethodCode;
		}

		public void setReceivablesMethodCode(String receivablesMethodCode) {
			this.receivablesMethodCode = receivablesMethodCode;
		}

		@Column(name = "receivablesMethodName")
		public String getReceivablesMethodName() {
			return this.receivablesMethodName;
		}

		public void setReceivablesMethodName(String receivablesMethodName) {
			this.receivablesMethodName = receivablesMethodName;
		}

		@Column(name = "createDate", nullable = false, length = 19)
		public Timestamp getCreateDate() {
			return this.createDate;
		}

		public void setCreateDate(Timestamp createDate) {
			this.createDate = createDate;
		}
		
		@Column(name = "linkman")
		public String getLinkman() {
			return this.linkman;
		}

		public void setLinkman(String linkman) {
			this.linkman = linkman;
		}

		@Column(name = "linkmanPhone")
		public String getLinkmanPhone() {
			return this.linkmanPhone;
		}

		public void setLinkmanPhone(String linkmanPhone) {
			this.linkmanPhone = linkmanPhone;
		}

		@Column(name = "proType")
		public Integer getProType() {
			return this.proType;
		}

		public void setProType(Integer proType) {
			this.proType = proType;
		}

		@Column(name = "tgType")
		public Integer getTgType() {
			return this.tgType;
		}

		public void setTgType(Integer tgType) {
			this.tgType = tgType;
		}

		@Column(name = "oldLineInfo")
		public String getOldLineInfo() {
			return this.oldLineInfo;
		}

		public void setOldLineInfo(String oldLineInfo) {
			this.oldLineInfo = oldLineInfo;
		}
		
		@Column(name = "currencyType")
		public String getCurrencyType() {
			return currencyType;
		}

		public void setCurrencyType(String currencyType) {
			this.currencyType = currencyType;
		}

		@Column(name = "codeProdouctOrder", nullable = false)
		public String getCodeProdouctOrder() {
			return codeProdouctOrder;
		}

		public void setCodeProdouctOrder(String codeProdouctOrder) {
			this.codeProdouctOrder = codeProdouctOrder;
		}

	}