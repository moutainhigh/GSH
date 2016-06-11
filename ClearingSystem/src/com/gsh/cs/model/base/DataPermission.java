package com.gsh.cs.model.base;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DataPermission entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "data_permission", catalog = "clearing")
public class DataPermission implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String role;
	private String userName;
	private String permission;
	private String remark;
	private String idName;
	private String idPermission;
	private String uid;
	private Timestamp createDate;
	private String uname;
	private Long cid;

	// Constructors

	/** default constructor */
	public DataPermission() {
	}

	/** minimal constructor */
	public DataPermission(String name, String role, String userName,
			String permission, String idName, String idPermission, String uid,
			Timestamp createDate,String uname,Long cid) {
		this.name = name;
		this.role = role;
		this.userName = userName;
		this.permission = permission;
		this.idName = idName;
		this.idPermission = idPermission;
		this.uid = uid;
		this.createDate = createDate;
		this.uname = uname;
		this.cid=cid;
	}

	/** full constructor */
	public DataPermission(String name, String role, String userName,
			String permission, String remark, String idName, String idPermission,
			String uid, Timestamp createDate,String uname,Long cid) {
		this.name = name;
		this.role = role;
		this.userName = userName;
		this.permission = permission;
		this.remark = remark;
		this.idName = idName;
		this.idPermission = idPermission;
		this.uid = uid;
		this.createDate = createDate;
		this.uname = uname;
		this.cid=cid;
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

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "role", nullable = false)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "userName", nullable = false)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "permission", nullable = false)
	public String getPermission() {
		return this.permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "idName", nullable = false)
	public String getIdName() {
		return this.idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

	@Column(name = "idPermission", nullable = false)
	public String getIdPermission() {
		return this.idPermission;
	}

	public void setIdPermission(String idPermission) {
		this.idPermission = idPermission;
	}

	@Column(name = "uid", nullable = false)
	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Column(name = "createDate", nullable = false, length = 19)
	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "uname", nullable = false)
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	
	@Column(name = "cid", unique = true, nullable = false)
	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}
}