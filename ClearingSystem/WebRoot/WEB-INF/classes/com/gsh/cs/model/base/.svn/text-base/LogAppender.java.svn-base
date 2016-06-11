package com.gsh.cs.model.base;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LogAppender entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "log_appender_", catalog = "")
public class LogAppender implements java.io.Serializable {

	// Fields

	private Integer idLog;
	private Long uidLog;// 操作人id
	private Long cidLog;// 操作人公司id
	private Timestamp timeLog;// 操作时间（具体到秒）
	private String actionNameLog;// 操作方法(action中具体方法)
	private Integer eventTypeLog;// 操作事件类型(需要归类操作类型) 1、增 2、删 3、改  4、查
	private String eventDetailLog;// 操作事件补充
	private String relatedIdTypeDesc;// 可追溯关键数据的类型的文字描述（比如订单类型，单据）
	private Integer relatedIdType;// 可追溯关键数据类型  1、订单 2、产品单  3、单据
	private Long relatedId;// 具体数据的id（比如订单的id）

	// Constructors

	/** default constructor */
	public LogAppender() {
	}

	/** minimal constructor */
	public LogAppender(Long uidLog, Long cidLog, Timestamp timeLog) {
		this.uidLog = uidLog;
		this.cidLog = cidLog;
		this.timeLog = timeLog;
	}

	/** full constructor */
	public LogAppender(Long uidLog, Long cidLog, Timestamp timeLog,
			String actionNameLog, Integer eventTypeLog, String eventDetailLog,
			String relatedIdTypeDesc, Integer relatedIdType, Long relatedId) {
		this.uidLog = uidLog;
		this.cidLog = cidLog;
		this.timeLog = timeLog;
		this.actionNameLog = actionNameLog;
		this.eventTypeLog = eventTypeLog;
		this.eventDetailLog = eventDetailLog;
		this.relatedIdTypeDesc = relatedIdTypeDesc;
		this.relatedIdType = relatedIdType;
		this.relatedId = relatedId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_log_", unique = true, nullable = false)
	public Integer getIdLog() {
		return this.idLog;
	}

	public void setIdLog(Integer idLog) {
		this.idLog = idLog;
	}

	@Column(name = "uid_log_", nullable = false)
	public Long getUidLog() {
		return this.uidLog;
	}

	public void setUidLog(Long uidLog) {
		this.uidLog = uidLog;
	}

	@Column(name = "cid_log_", nullable = false)
	public Long getCidLog() {
		return this.cidLog;
	}

	public void setCidLog(Long cidLog) {
		this.cidLog = cidLog;
	}

	@Column(name = "time_log_", nullable = false, length = 19)
	public Timestamp getTimeLog() {
		return this.timeLog;
	}

	public void setTimeLog(Timestamp timeLog) {
		this.timeLog = timeLog;
	}

	@Column(name = "actionName_log_")
	public String getActionNameLog() {
		return this.actionNameLog;
	}

	public void setActionNameLog(String actionNameLog) {
		this.actionNameLog = actionNameLog;
	}

	@Column(name = "eventType_log_")
	public Integer getEventTypeLog() {
		return this.eventTypeLog;
	}

	public void setEventTypeLog(Integer eventTypeLog) {
		this.eventTypeLog = eventTypeLog;
	}

	@Column(name = "eventDetail_log_")
	public String getEventDetailLog() {
		return this.eventDetailLog;
	}

	public void setEventDetailLog(String eventDetailLog) {
		this.eventDetailLog = eventDetailLog;
	}

	@Column(name = "relatedIdTypeDesc")
	public String getRelatedIdTypeDesc() {
		return this.relatedIdTypeDesc;
	}

	public void setRelatedIdTypeDesc(String relatedIdTypeDesc) {
		this.relatedIdTypeDesc = relatedIdTypeDesc;
	}

	@Column(name = "relatedIdType")
	public Integer getRelatedIdType() {
		return this.relatedIdType;
	}

	public void setRelatedIdType(Integer relatedIdType) {
		this.relatedIdType = relatedIdType;
	}

	@Column(name = "relatedId")
	public Long getRelatedId() {
		return this.relatedId;
	}

	public void setRelatedId(Long relatedId) {
		this.relatedId = relatedId;
	}

}