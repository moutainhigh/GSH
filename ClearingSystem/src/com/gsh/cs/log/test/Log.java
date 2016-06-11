package com.gsh.cs.log.test;

import java.text.ParseException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gsh.cs.service.bset.AccountServiceI;

public class Log {
	@org.junit.Test
	public void test() throws ParseException {
//		Logger logger = Logger.getLogger(Log.class);
//		LogAppender lg = new LogAppender();
//		lg.setUidLog(34l);
//		lg.setCidLog(10001l);
//		lg.setTimeLog(DateTool.getSystemTimestamp());
//		String jsonString = JSON.toJSONStringWithDateFormat(lg,
//				"yyyy-MM-dd HH:mm:ss.SSS");
//		logger.info(jsonString);
		//获取spring上下文
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] { "classpath:spring.xml", "classpath:spring-hibernate.xml" });
		AccountServiceI accountService = (AccountServiceI) ac.getBean("accountService");

	}
}
