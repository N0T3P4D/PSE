package org.ojim.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
	private static File logFile;
	private static FileWriter logWriter;
	
	public Log(String log) {
		this.logFile = new File(log);
		try {
			this.logWriter = new FileWriter(this.logFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
