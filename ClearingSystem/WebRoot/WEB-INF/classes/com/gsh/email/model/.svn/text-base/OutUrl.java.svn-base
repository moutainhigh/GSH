package com.gsh.email.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OutUrl entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "out_url", catalog = "")
public class OutUrl implements java.io.Serializable {

	// Fields

	private Long idOutUrl;//主键
	private String outKeyOutUrl;//随机字符串
	private String targetUrloutUrl;//链接地址
	private Timestamp createTimeOutUrl;//创建时间
	private Timestamp expriedTimeOutUrl;//失效时间
	private Timestamp usedTimeOutUrl;//使用时间
	private Long uidOutUrl;//用户id
	private Long cidOutUrl;//公司id
	private Long idBill;//报价单id
	private Long idBillQuotation;//附表id
	private Long idMainOrder;//主单id
	private Long idBillConfirm;//确认单id

	// Constructors

	/** default constructor */
	public OutUrl() {
	}

	/** full constructor */
	public OutUrl(String outKeyOutUrl, String targetUrloutUrl,
			Timestamp createTimeOutUrl, Timestamp expriedTimeOutUrl,
			Timestamp usedTimeOutUrl, Long uidOutUrl, Long cidOutUrl,
			Long idBill, Long idBillQuotation, Long idMainOrder,
			Long idBillConfirm) {
		this.outKeyOutUrl = outKeyOutUrl;
		this.targetUrloutUrl = targetUrloutUrl;
		this.createTimeOutUrl = createTimeOutUrl;
		this.expriedTimeOutUrl = expriedTimeOutUrl;
		this.usedTimeOutUrl = usedTimeOutUrl;
		this.uidOutUrl = uidOutUrl;
		this.cidOutUrl = cidOutUrl;
		this.idBill = idBill;
		this.idBillQuotation = idBillQuotation;
		this.idMainOrder = idMainOrder;
		this.idBillConfirm = idBillConfirm;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idOutUrl", unique = true, nullable = false)
	public Long getIdOutUrl() {
		return this.idOutUrl;
	}

	public void setIdOutUrl(Long idOutUrl) {
		this.idOutUrl = idOutUrl;
	}

	@Column(name = "outKeyOutUrl")
	public String getOutKeyOutUrl() {
		return this.outKeyOutUrl;
	}

	public void setOutKeyOutUrl(String outKeyOutUrl) {
		this.outKeyOutUrl = outKeyOutUrl;
	}

	@Column(name = "targetURLOutUrl")
	public String getTargetUrloutUrl() {
		return this.targetUrloutUrl;
	}

	public void setTargetUrloutUrl(String targetUrloutUrl) {
		this.targetUrloutUrl = targetUrloutUrl;
	}

	@Column(name = "createTimeOutUrl", length = 19)
	public Timestamp getCreateTimeOutUrl() {
		return this.createTimeOutUrl;
	}

	public void setCreateTimeOutUrl(Timestamp createTimeOutUrl) {
		this.createTimeOutUrl = createTimeOutUrl;
	}

	@Column(name = "expriedTimeOutUrl", length = 19)
	public Timestamp getExpriedTimeOutUrl() {
		return this.expriedTimeOutUrl;
	}

	public void setExpriedTimeOutUrl(Timestamp expriedTimeOutUrl) {
		this.expriedTimeOutUrl = expriedTimeOutUrl;
	}

	@Column(name = "usedTimeOutUrl", length = 19)
	public Timestamp getUsedTimeOutUrl() {
		return this.usedTimeOutUrl;
	}

	public void setUsedTimeOutUrl(Timestamp usedTimeOutUrl) {
		this.usedTimeOutUrl = usedTimeOutUrl;
	}

	@Column(name = "uidOutUrl")
	public Long getUidOutUrl() {
		return this.uidOutUrl;
	}

	public void setUidOutUrl(Long uidOutUrl) {
		this.uidOutUrl = uidOutUrl;
	}

	@Column(name = "cidOutUrl")
	public Long getCidOutUrl() {
		return this.cidOutUrl;
	}

	public void setCidOutUrl(Long cidOutUrl) {
		this.cidOutUrl = cidOutUrl;
	}

	@Column(name = "idBill")
	public Long getIdBill() {
		return this.idBill;
	}

	public void setIdBill(Long idBill) {
		this.idBill = idBill;
	}

	@Column(name = "idBillQuotation")
	public Long getIdBillQuotation() {
		return this.idBillQuotation;
	}

	public void setIdBillQuotation(Long idBillQuotation) {
		this.idBillQuotation = idBillQuotation;
	}

	@Column(name = "idMainOrder")
	public Long getIdMainOrder() {
		return this.idMainOrder;
	}

	public void setIdMainOrder(Long idMainOrder) {
		this.idMainOrder = idMainOrder;
	}

	@Column(name = "idBillConfirm")
	public Long getIdBillConfirm() {
		return this.idBillConfirm;
	}

	public void setIdBillConfirm(Long idBillConfirm) {
		this.idBillConfirm = idBillConfirm;
	}

}