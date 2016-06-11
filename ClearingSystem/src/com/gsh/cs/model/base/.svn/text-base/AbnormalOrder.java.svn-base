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
 * AbnormalOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="abnormal_order"
    ,catalog="clearing"
)

public class AbnormalOrder  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String exchangeNo;//交换单号
     private BigDecimal balance;//交换单金额
     private Date tradeDate;//交换单创建日期
     private String orderNo;//订单号
     private String noticeNo;//通知单号
     private String affiliationNo;//订单归属人编号
     private String affiliationPerson;//订单归属人
     private String creater;//创建人
     private String createrNo;//创建人编号
     private String currencyType;//货币类型


    // Constructors

    /** default constructor */
    public AbnormalOrder() {
    }

    
    /** full constructor */
    public AbnormalOrder(String exchangeNo, BigDecimal balance, Date tradeDate, String orderNo, String affiliationNo, String affiliationPerson, 
String creater, String createrNo,String currencyType) {
        this.exchangeNo = exchangeNo;
        this.balance = balance;
        this.tradeDate = tradeDate;
        this.orderNo = orderNo;
        this.affiliationNo = affiliationNo;
        this.affiliationPerson = affiliationPerson;
        this.creater = creater;
        this.createrNo = createrNo;
        this.currencyType=currencyType;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="exchangeNo", nullable=false)

    public String getExchangeNo() {
        return this.exchangeNo;
    }
    
    public void setExchangeNo(String exchangeNo) {
        this.exchangeNo = exchangeNo;
    }
    
    @Column(name="balance", nullable=false, precision=10, scale=0)

    public BigDecimal getBalance() {
        return this.balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="tradeDate", nullable=false, length=10)

    public Date getTradeDate() {
        return this.tradeDate;
    }
    
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }
    
    @Column(name="orderNo", nullable=false)

    public String getOrderNo() {
        return this.orderNo;
    }
    
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    
    @Column(name="noticeNo")

    public String getNoticeNo() {
        return this.noticeNo;
    }
    
    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
    }
    
    @Column(name="affiliationNo", nullable=false)

    public String getAffiliationNo() {
        return this.affiliationNo;
    }
    
    public void setAffiliationNo(String affiliationNo) {
        this.affiliationNo = affiliationNo;
    }
    
    @Column(name="affiliationPerson", nullable=false)

    public String getAffiliationPerson() {
        return this.affiliationPerson;
    }
    
    public void setAffiliationPerson(String affiliationPerson) {
        this.affiliationPerson = affiliationPerson;
    }
    
    @Column(name="creater", nullable=false)

    public String getCreater() {
        return this.creater;
    }
    
    public void setCreater(String creater) {
        this.creater = creater;
    }
    
    @Column(name="createrNo", nullable=false)

    public String getCreaterNo() {
        return this.createrNo;
    }
    
    public void setCreaterNo(String createrNo) {
        this.createrNo = createrNo;
    }

    @Column(name="currencyType", nullable=false)
	public String getCurrencyType() {
		return currencyType;
	}
	

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
   
    







}