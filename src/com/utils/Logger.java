package com.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class Logger {
	public static final String filename = "log.txt";
	private static BufferedWriter writer;
	
	public static void info(String servletPath, String logKey, Object... args){
		try {
			writer = new BufferedWriter(new FileWriter(filename,true));

			

			Date date = new Date();
			Timestamp dateToday = new Timestamp(date.getTime());
			String toLog = "";
			
			for(int i = 0; i < args.length; i++) {
				toLog = toLog.concat(args[i].toString());
				if(i + 1 < args.length)
					toLog = toLog.concat("|");
			}
			
			String line = String.format("%s >> %s//%s||%s", dateToday.toString(), servletPath,logKey,toLog);
			System.out.println(line);
			writer.write(line);
			
			
			writer.newLine();
			writer.flush();
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
