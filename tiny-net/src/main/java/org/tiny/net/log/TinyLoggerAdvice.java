package org.tiny.net.log;

import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository("tinyLogger")
public class TinyLoggerAdvice {
	
	public TinyLoggerAdvice () {
		
	}
	
	public void logAspect(LogLevel level, String name, String... s) {
		StringBuilder sb = new StringBuilder();
		
		for (String str : s) {
			sb.append(str);
		}
		System.out.println(addLogPrefix(name, sb.toString(), level));
	}
	
	private String addLogPrefix(String name, String logMsg, LogLevel level) {
		// 以后服务器内会有自己的GameTime
		Date d = new Date(System.currentTimeMillis());
		Date trueDate = new Date();
		if(d.equals(trueDate)){
			return String.format("%1$tF %1$tT:%1$tL [%2$s] %3$s %4$s %5$s\r\n", d, level, Thread.currentThread().getName(), name, logMsg);
		}else{
			return String.format("%1$tF %1$tT:%1$tL %2$tF %2$tT:%2$tL [%3$s] %4$s %5$s %6$s\r\n", trueDate,d, level, Thread.currentThread().getName(), name, logMsg);
		}
	}
}
