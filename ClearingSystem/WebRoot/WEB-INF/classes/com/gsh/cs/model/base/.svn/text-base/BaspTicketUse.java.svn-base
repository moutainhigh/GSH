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

/**
 * BaspTicketUse entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "basp_ticket_use", catalog = "clearing")
public class BaspTicketUse implements java.io.Serializable {

	// Fields

	private Long id;
	private String airlineCompany;//航空公司3位结算码
	private Long ticketNo;//票本号码
	private Date drawerDay;//票号输入的日期
	private BigDecimal scny;//SCNY
	private BigDecimal tax;//税
	private BigDecimal moneyC;//C金额（c）
	private BigDecimal moneyCM;//C金额（c）
	private BigDecimal moneyZ;//z金额（z）
	private BigDecimal moneyZM;//z金额（z）
	private BigDecimal money;//金额
	private String productNumber;//产品单号
	private String type;//类型1：国际2、国内
	private String typeTgq;// TKTT正常出票VOID作废REFD 退票EX  换开
	private Timestamp createDate;//创建日期
	private String affiliationPerson;//归属人
	private String affiliationNo;//归属人编号
	private String creater;//创建人
	private Long createrNo;//创建人编号

	// Constructors

	/** default constructor */
	public BaspTicketUse() {
	}

	/** minimal constructor */
	public BaspTicketUse(String airlineCompany, Long ticketNo, Date drawerDay,
			BigDecimal scny, BigDecimal tax, BigDecimal moneyC, BigDecimal money, String productNumber,
			String type, Timestamp createDate, String affiliationPerson,
			String affiliationNo, String creater, Long createrNo) {
		this.airlineCompany = airlineCompany;
		this.ticketNo = ticketNo;
		this.drawerDay = drawerDay;
		this.scny = scny;
		this.tax = tax;
		this.moneyC = moneyC;
		this.money = money;
		this.productNumber = productNumber;
		this.type = type;
		this.createDate = createDate;
		this.affiliationPerson = affiliationPerson;
		this.affiliationNo = affiliationNo;
		this.creater = creater;
		this.createrNo = createrNo;
	}

	/** full constructor */
	public BaspTicketUse(String airlineCompany, Long ticketNo, Date drawerDay,
			BigDecimal scny, BigDecimal tax, BigDecimal moneyC, BigDecimal moneyZ, BigDecimal money,
			String productNumber, String type, Timestamp createDate,
			String affiliationPerson, String affiliationNo, String creater,
			Long createrNo) {
		this.airlineCompany = airlineCompany;
		this.ticketNo = ticketNo;
		this.drawerDay = drawerDay;
		this.scny = scny;
		this.tax = tax;
		this.moneyC = moneyC;
		this.moneyZ = moneyZ;
		this.money = money;
		this.productNumber = productNumber;
		this.type = type;
		this.createDate = createDate;
		this.affiliationPerson = affiliationPerson;
		this.affiliationNo = affiliationNo;
		this.creater = creater;
		this.createrNo = createrNo;
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

	@Column(name = "airlineCompany", nullable = false)
	public String getAirlineCompany() {
		return this.airlineCompany;
	}

	public void setAirlineCompany(String airlineCompany) {
		this.airlineCompany = airlineCompany;
	}

	@Column(name = "ticketNo", nullable = false)
	public Long getTicketNo() {
		return this.ticketNo;
	}

	public void setTicketNo(Long ticketNo) {
		this.ticketNo = ticketNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "drawerDay", nullable = false, length = 10)
	public Date getDrawerDay() {
		return this.drawerDay;
	}

	public void setDrawerDay(Date drawerDay) {
		this.drawerDay = drawerDay;
	}

	@Column(name = "scny", nullable = false, precision = 10, scale = 0)
	public BigDecimal getScny() {
		return this.scny;
	}

	public void setScny(BigDecimal scny) {
		this.scny = scny;
	}

	@Column(name = "tax", nullable = false, precision = 10, scale = 0)
	public BigDecimal getTax() {
		return this.tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	@Column(name = "moneyC", nullable = false, precision = 10, scale = 0)
	public BigDecimal getMoneyC() {
		return this.moneyC;
	}

	public void setMoneyC(BigDecimal moneyC) {
		this.moneyC = moneyC;
	}

	@Column(name = "moneyZ", precision = 10, scale = 0)
	public BigDecimal getMoneyZ() {
		return this.moneyZ;
	}

	public void setMoneyZ(BigDecimal moneyZ) {
		this.moneyZ = moneyZ;
	}

	@Column(name = "money", nullable = false, precision = 10, scale = 0)
	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	@Column(name = "productNumber", nullable = false)
	public String getProductNumber() {
		return this.productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	@Column(name = "type", nullable = false)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "createDate", nullable = false, length = 19)
	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Column(name = "affiliationPerson", nullable = false)
	public String getAffiliationPerson() {
		return this.affiliationPerson;
	}

	public void setAffiliationPerson(String affiliationPerson) {
		this.affiliationPerson = affiliationPerson;
	}

	@Column(name = "affiliationNo", nullable = false)
	public String getAffiliationNo() {
		return this.affiliationNo;
	}

	public void setAffiliationNo(String affiliationNo) {
		this.affiliationNo = affiliationNo;
	}

	@Column(name = "creater", nullable = false)
	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	@Column(name = "createrNo", nullable = false)
	public Long getCreaterNo() {
		return this.createrNo;
	}

	public void setCreaterNo(Long createrNo) {
		this.createrNo = createrNo;
	}
	
	@Column(name = "typeTgq", nullable = true)
	public String getTypeTgq() {
		return typeTgq;
	}

	public void setTypeTgq(String typeTgq) {
		this.typeTgq = typeTgq;
	}
	
	@Column(name = "moneyCM", nullable = false, precision = 10, scale = 0)
	public BigDecimal getMoneyCM() {
		return moneyCM;
	}

	public void setMoneyCM(BigDecimal moneyCM) {
		this.moneyCM = moneyCM;
	}
	
	@Column(name = "moneyZM", precision = 10, scale = 0)
	public BigDecimal getMoneyZM() {
		return moneyZM;
	}

	public void setMoneyZM(BigDecimal moneyZM) {
		this.moneyZM = moneyZM;
	}
	
}