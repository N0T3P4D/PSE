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

import java.util.logging.ConsoleHandler;
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

	private OJIMLogger() {
	}

	private static void setupLogger(Logger logger) {
		assert (logger != null);
		logger.setLevel(Level.CONFIG);
		logger.setUseParentHandlers(false);
		if (consoleHandler == null) {
			consoleHandler = new ConsoleHandler();
		}
		if (formatter == null) {
			formatter = new OJIMFormatter();
		}
		consoleHandler.setFormatter(formatter);
		consoleHandler.setLevel(Level.CONFIG);
		if (logger.getHandlers().length == 0) {
			logger.addHandler(consoleHandler);
		}
	}

	/**
	 * 
	 * To be called from outside - creates a new Logger, set up for OJIM
	 * purposes
	 * 
	 * @param name
	 *            The logger's name
	 * @return A new logger for OJIM
	 */
	public static Logger getLogger(String name) {
		if (name == null) {
			throw new IllegalArgumentException("name == null");
		}
		Logger result = Logger.getLogger(name);
		assert (result != null);
		setupLogger(result);
		return result;
	}

}
