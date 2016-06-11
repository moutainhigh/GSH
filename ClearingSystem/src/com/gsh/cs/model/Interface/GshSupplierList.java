package com.gsh.cs.model.Interface;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * GshSupplierList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gsh_supplier_list", catalog = "")
public class GshSupplierList implements java.io.Serializable {

	// Fields

	private Long id;//主键
	private Long uid;//登录人id
	private Long cid;//登录人公司id
	/**
	 * 供应商状态  
	 * 1.未激活(添加供应商默认状态)
	 * 2.激活(经过审批后)
	 * 3.已挂起(订单中心不能选此供应商)
	 */
	private Integer status;
	/**
	 * 供应商来源
	 * 1.来自CRM(CRM提供数据无权编辑,只能补全未填信息)
	 * 2.默认数据(两条测试数据)
	 * 3.正常数据
	 */
	private Integer supplierSource;
	private Integer approveStatus;//审批状态 1.未审批  2.申请中  3.已通过  4.未通过
	private String companyNo;//供应商编号
	private String shortCode;//供应商简称
	private String cname;//供应商全称
	private String tel;//电话
	private String fax;//传真
	private String zipCode;//邮编
	private Integer supplierType;//供应商类型(类型表外键)
	private Integer supplierLevel;//供应商级别
	private String province;//省
	private String city;//城市
	private String address;//具体地址
	private String lr;//法人
	private String lrAddress;//法人地址
	private String contacter1;//联系人1
	private String tel1;//联系人电话1
	private String contacter2;//联系人2
	private String tel2;//联系人电话2
	private Integer purchaseType;//采购类型 1.虚拟采购  2.内部采购   3.外部采购
	private String unavailableOp;//不适用OP(ids)
	private String availableClient;//适用客户
	private String linkMan;//结算信息 联系人
	private Integer jsType;//结算方式 1.自然月   2.固定天
	private Integer monthDay;//月数OR天数
	private Double quota;//限额
	private Integer keepNum;//保留小数 1.元  2.角  3.分
	private Integer carryType;//进位方式 1.四舍五入  2.逢一进十  3.舍去余数
	private Integer payType;//付款方式 1.转账 2.汇款  3.现金  4.信用卡  5.支票
	private String currency;//币种
	private String financeRemark;//其他财务信息
	// Constructors

	/** default constructor */
	public GshSupplierList() {
	}

	/** minimal constructor */
	public GshSupplierList(Long uid, Long cid, Integer status,
			Integer supplierSource, Integer approveStatus, String companyNo,
			String shortCode, String cname, String tel, Integer supplierType,
			Integer purchaseType) {
		this.uid = uid;
		this.cid = cid;
		this.status = status;
		this.supplierSource = supplierSource;
		this.approveStatus = approveStatus;
		this.companyNo = companyNo;
		this.shortCode = shortCode;
		this.cname = cname;
		this.tel = tel;
		this.supplierType = supplierType;
		this.purchaseType = purchaseType;
	}

	/** full constructor */
	public GshSupplierList(Long uid, Long cid, Integer status,
			Integer supplierSource, Integer approveStatus, String companyNo,
			String shortCode, String cname, String tel, String fax,
			String zipCode, Integer supplierType, Integer supplierLevel,
			String province, String city, String address, String lr,
			String lrAddress, String contacter1, String tel1,
			String contacter2, String tel2, Integer purchaseType,
			String unavailableOp, String availableClient, String linkMan,
			Integer jsType, Integer monthDay, Double quota, Integer keepNum,
			Integer carryType, Integer payType, String currency,
			String financeRemark) {
		this.uid = uid;
		this.cid = cid;
		this.status = status;
		this.supplierSource = supplierSource;
		this.approveStatus = approveStatus;
		this.companyNo = companyNo;
		this.shortCode = shortCode;
		this.cname = cname;
		this.tel = tel;
		this.fax = fax;
		this.zipCode = zipCode;
		this.supplierType = supplierType;
		this.supplierLevel = supplierLevel;
		this.province = province;
		this.city = city;
		this.address = address;
		this.lr = lr;
		this.lrAddress = lrAddress;
		this.contacter1 = contacter1;
		this.tel1 = tel1;
		this.contacter2 = contacter2;
		this.tel2 = tel2;
		this.purchaseType = purchaseType;
		this.unavailableOp = unavailableOp;
		this.availableClient = availableClient;
		this.linkMan = linkMan;
		this.jsType = jsType;
		this.monthDay = monthDay;
		this.quota = quota;
		this.keepNum = keepNum;
		this.carryType = carryType;
		this.payType = payType;
		this.currency = currency;
		this.financeRemark = financeRemark;
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

	@Column(name = "uid", nullable = false)
	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	@Column(name = "cid", nullable = false)
	public Long getCid() {
		return this.cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "supplierSource", nullable = false)
	public Integer getSupplierSource() {
		return this.supplierSource;
	}

	public void setSupplierSource(Integer supplierSource) {
		this.supplierSource = supplierSource;
	}

	@Column(name = "approveStatus", nullable = false)
	public Integer getApproveStatus() {
		return this.approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	@Column(name = "companyNo", nullable = false)
	public String getCompanyNo() {
		return this.companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	@Column(name = "shortCode", nullable = false)
	public String getShortCode() {
		return this.shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	@Column(name = "cName", nullable = false)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "tel", nullable = false, length = 20)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "fax", length = 20)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "zipCode", length = 20)
	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "supplierType", nullable = false)
	public Integer getSupplierType() {
		return this.supplierType;
	}

	public void setSupplierType(Integer supplierType) {
		this.supplierType = supplierType;
	}

	@Column(name = "supplierLevel")
	public Integer getSupplierLevel() {
		return this.supplierLevel;
	}

	public void setSupplierLevel(Integer supplierLevel) {
		this.supplierLevel = supplierLevel;
	}

	@Column(name = "province", length = 100)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city", length = 100)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "lr", length = 100)
	public String getLr() {
		return this.lr;
	}

	public void setLr(String lr) {
		this.lr = lr;
	}

	@Column(name = "lr_address")
	public String getLrAddress() {
		return this.lrAddress;
	}

	public void setLrAddress(String lrAddress) {
		this.lrAddress = lrAddress;
	}

	@Column(name = "contacter1", length = 30)
	public String getContacter1() {
		return this.contacter1;
	}

	public void setContacter1(String contacter1) {
		this.contacter1 = contacter1;
	}

	@Column(name = "tel1", length = 30)
	public String getTel1() {
		return this.tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	@Column(name = "contacter2", length = 30)
	public String getContacter2() {
		return this.contacter2;
	}

	public void setContacter2(String contacter2) {
		this.contacter2 = contacter2;
	}

	@Column(name = "tel2", length = 30)
	public String getTel2() {
		return this.tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	@Column(name = "purchaseType", nullable = false)
	public Integer getPurchaseType() {
		return this.purchaseType;
	}

	public void setPurchaseType(Integer purchaseType) {
		this.purchaseType = purchaseType;
	}

	@Column(name = "unavailableOP", length = 50)
	public String getUnavailableOp() {
		return this.unavailableOp;
	}

	public void setUnavailableOp(String unavailableOp) {
		this.unavailableOp = unavailableOp;
	}

	@Column(name = "availableClient")
	public String getAvailableClient() {
		return this.availableClient;
	}

	public void setAvailableClient(String availableClient) {
		this.availableClient = availableClient;
	}

	@Column(name = "linkMan", length = 50)
	public String getLinkMan() {
		return this.linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	@Column(name = "jsType")
	public Integer getJsType() {
		return this.jsType;
	}

	public void setJsType(Integer jsType) {
		this.jsType = jsType;
	}

	@Column(name = "monthDay")
	public Integer getMonthDay() {
		return this.monthDay;
	}

	public void setMonthDay(Integer monthDay) {
		this.monthDay = monthDay;
	}

	@Column(name = "quota", precision = 22, scale = 0)
	public Double getQuota() {
		return this.quota;
	}

	public void setQuota(Double quota) {
		this.quota = quota;
	}

	@Column(name = "keepNum")
	public Integer getKeepNum() {
		return this.keepNum;
	}

	public void setKeepNum(Integer keepNum) {
		this.keepNum = keepNum;
	}

	@Column(name = "carryType")
	public Integer getCarryType() {
		return this.carryType;
	}

	public void setCarryType(Integer carryType) {
		this.carryType = carryType;
	}

	@Column(name = "payType")
	public Integer getPayType() {
		return this.payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	@Column(name = "currency", length = 50)
	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "financeRemark")
	public String getFinanceRemark() {
		return this.financeRemark;
	}

	public void setFinanceRemark(String financeRemark) {
		this.financeRemark = financeRemark;
	}

}