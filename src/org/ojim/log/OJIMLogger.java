/*  Copyright (C) 2010 - 2011  Fabian Neundorf, Philip Caroli,
 *  Maximilian Madlung,	Usman Ghani Ahmed, Jeremias Mechler
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.ojim.log;

import java.util.LinkedList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * Sets up a logger for OJIM
 * 
 * @author Jeremias Mechler
 * 
 */
public final class OJIMLogger {

	private static ConsoleHandler consoleHandler;
	private static OJIMFormatter formatter;
	private static Level globalLevel = Level.WARNING;
	private static LinkedList<Logger> loggerList;

	private OJIMLogger() {
	}

	private static void setupLogger(Logger logger) {
		assert (logger != null);
		logger.setLevel(globalLevel);
		logger.setUseParentHandlers(false);
		if (consoleHandler == null) {
			consoleHandler = new ConsoleHandler();
		}
		if (formatter == null) {
			formatter = new OJIMFormatter();
		}
		consoleHandler.setFormatter(formatter);
		consoleHandler.setLevel(globalLevel);
		if (logger.getHandlers().length == 0) {
			logger.addHandler(consoleHandler);
		}
	}

	/**
	 * 
	 * To be called from outside - creates a new Logger, set up for OJIM purposes
	 * 
	 * @param name
	 *            The logger's name
	 * @return A new logger for OJIM
	 */
	public static Logger getLogger(String name) {
		if (name == null) {
			throw new IllegalArgumentException("name == null");
		}
		if (loggerList == null) {
			loggerList = new LinkedList<Logger>();
		}
		Logger result = Logger.getLogger(name);
		assert (result != null);
		loggerList.add(result);
		assert(loggerList.contains(result));
		setupLogger(result);
		return result;
	}

	public synchronized static void changeLogLevel(Logger logger, Level level) {
		if (logger == null || level == null) {
			throw new IllegalArgumentException();
		}
		logger.setLevel(level);
		for (Handler handler : logger.getHandlers()) {
			handler.setLevel(level);
		}
	}

	public synchronized static void changeGlobalLevel(Level level) {
		if (level == null) {
			throw new IllegalArgumentException("level == null");
		}
		globalLevel = level;
		for (Logger logger : loggerList) {
			changeLogLevel(logger, level);
		}
	}
	
	public static void printLevel() {
		System.out.println(globalLevel);
//		assert(false);
	}
	
	public static void printLoggerLevel(Logger logger) {
		assert(loggerList.contains(logger));
		System.out.println(logger.getLevel());
//		assert(false);
	}

}
