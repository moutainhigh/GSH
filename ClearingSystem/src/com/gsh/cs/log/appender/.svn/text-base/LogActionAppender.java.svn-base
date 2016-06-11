package com.gsh.cs.log.appender;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.log4j.jdbc.JDBCAppender;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.spi.LoggingEvent;

import com.alibaba.fastjson.JSON;
import com.codestudio.sql.PoolMan;
import com.gsh.cs.log.test.Log;
import com.gsh.cs.model.base.LogAppender;
import com.gsh.cs.tools.DateTool;

public class LogActionAppender extends JDBCAppender {
	/** 通过 PoolMan 获取数据库连接对象的 jndiName 属性 */
	protected String jndiName;
	/** 数据库连接对象 */
	protected Connection connection;

	public void flushBuffer() {
		// Do the actual logging
		removes.ensureCapacity(buffer.size());
		for (Iterator i = buffer.iterator(); i.hasNext();) {
			try {
				LoggingEvent logEvent = (LoggingEvent) i.next();
				Object obj = logEvent.getMessage();
				String msg = obj.toString();
				LogAppender lg = JSON.parseObject(msg, LogAppender.class);
				if (lg != null) {
					String sql = "insert into log_appender_ (uid_log_,cid_log_,time_log_,actionName_log_,eventType_log_,eventDetail_log_,relatedIdTypeDesc,relatedIdType,relatedId) values ('"
							+ lg.getUidLog()
							+ "','"
							+ lg.getCidLog()
							+ "','"
							+ lg.getTimeLog()
							+ "','"
							+ lg.getActionNameLog()
							+ "','"
							+ lg.getEventTypeLog()
							+ "','"
							+ lg.getEventDetailLog()
							+ "','"
							+ lg.getRelatedIdTypeDesc()
							+ "','"
							+ lg.getRelatedIdType()
							+ "','"
							+ lg.getRelatedId()
							+ "')";
					System.out.println(sql);
					execute(sql);
				}
				removes.add(logEvent);
			} catch (SQLException e) {
				errorHandler.error("Failed to excute sql", e,
						ErrorCode.FLUSH_FAILURE);
			}
		}
		// remove from the buffer any events that were reported
		buffer.removeAll(removes);
		// clear the buffer of reported events
		removes.clear();
	}

	@Override
	protected void closeConnection(Connection con) {
		try {
			if (connection != null && !connection.isClosed())
				connection.close();
		} catch (SQLException e) {
			errorHandler.error("Error closing connection", e,
					ErrorCode.GENERIC_FAILURE);
		}
	}

	@Override
	protected Connection getConnection() throws SQLException {
		try {
			System.out.println("log4j:" + jndiName);
			// 通过 PoolMan
			// 获取数据库连接对象(http://nchc.dl.sourceforge.net/project/poolman/PoolMan/poolman-2.1-b1/poolman-2.1-b1.zip)
			Class.forName("com.codestudio.sql.PoolMan");
			connection = PoolMan.connect("jdbc:poolman://" + getJndiName());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}

	/**
	 * @return the jndiName
	 */
	public String getJndiName() {
		return jndiName;
	}

	/**
	 * @param jndiName
	 *            the jndiName to set
	 */
	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}

	/**
	 * 添加log日志方法
	 * 
	 * @param uid
	 * @param cid
	 * @param actionName
	 * @param eventType
	 * @param eventDetail
	 * @param relatedIdTypeDesc
	 * @param relatedIdType
	 * @param relatedId
	 */
	public static void logRecord(Long uid, Long cid, String actionName,
			Integer eventType, String eventDetail, String relatedIdTypeDesc,
			Integer relatedIdType, Long relatedId) {
		Logger logger = Logger.getLogger(Log.class);
		LogAppender lg = new LogAppender();
		lg.setUidLog(uid);
		lg.setCidLog(cid);
		lg.setTimeLog(DateTool.getSystemTimestamp());
		lg.setActionNameLog(actionName);
		lg.setEventTypeLog(eventType);
		lg.setEventDetailLog(eventDetail);
		lg.setRelatedIdTypeDesc(relatedIdTypeDesc);
		lg.setRelatedIdType(relatedIdType);
		lg.setRelatedId(relatedId);
		String jsonString = JSON.toJSONStringWithDateFormat(lg,
				"yyyy-MM-dd HH:mm:ss.SSS");
		logger.info(jsonString);
	}
}
