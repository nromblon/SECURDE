package com.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.Date;

public class Log {
	private final static String LOG_FILE = "log.txt";
	private static FileWriter writer = null;
	private static PrintWriter print = null;
	
	public static void info(String servletPath, String logKey, Object... args) throws IOException{
		if(writer == null) {
			writer = new FileWriter(LOG_FILE, true);
			print = new PrintWriter(writer);
		}
		
		Date date = new Date();
		Timestamp dateToday = new Timestamp(date.getTime());
		String toLog = "";
		
		for(int i = 0; i < args.length; i++) {
			toLog = toLog.concat(args.toString());
			if(i + 1 < args.length)
				toLog = toLog.concat(">>");
		}
		
		print.printf("%s>>%s|%s|%s|" + "%n", dateToday.toString(), servletPath, InetAddress.getLocalHost().getHostAddress(), toLog);
	}
	
	public static void info(String servletPath, int userId, String logKey, Object... args) throws IOException{
		if(writer == null) {
			writer = new FileWriter(LOG_FILE, true);
			print = new PrintWriter(writer);
		}
		
		Date date = new Date();
		Timestamp dateToday = new Timestamp(date.getTime());
		String toLog = "";
		
		for(int i = 0; i < args.length; i++) {
			toLog = toLog.concat(args.toString());
			if(i + 1 < args.length)
				toLog = toLog.concat(">>");
		}
		
		print.printf("%s>>%s|%s|%s|%s|" + "%n", dateToday.toString(), servletPath, InetAddress.getLocalHost().getHostAddress(), "USERID: "+userId, toLog);
	}
	
}
