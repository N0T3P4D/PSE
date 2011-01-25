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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * 
 * OJIMFormatter is a custom formatter for logging
 * 
 * @author Jeremias Mechler
 * 
 */

public class OJIMFormatter extends Formatter {

	private SimpleDateFormat dateFormatter;

	/**
	 * Default constructor
	 */
	public OJIMFormatter() {
		dateFormatter = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
	}

	@Override
	public String format(LogRecord record) {
		if (record == null) {
			throw new IllegalArgumentException("record == null");
		}
		return (formatDate(record) + ": [" + record.getLevel() + "] " + record.getSourceClassName() + "."
				+ record.getSourceMethodName() + "(): " + record.getMessage() + '\n');
//		return (formatDate(record) + ": [" + record.getLevel() + "] " + record.getLoggerName() + "."
//		+ record.getSourceMethodName() + "(): " + record.getMessage() + '\n');
		
	}

	private String formatDate(LogRecord record) {
		assert (record != null);
		return dateFormatter.format(new Date(record.getMillis()));
	}

}
