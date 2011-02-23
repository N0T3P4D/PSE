package org.ojim.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
	private static File logFile;
	private static FileWriter logWriter;
	
	public Log(String log) {
		Log.logFile = new File(log);
		try {
			Log.logWriter = new FileWriter(Log.logFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
