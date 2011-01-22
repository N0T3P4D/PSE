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

package org.ojim.client.ai;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.ojim.log.OJIMLogger;
import org.ojim.logic.Logic;

import org.ojim.client.ClientBase;
import edu.kit.iti.pse.iface.IServer;

/**
 * AI Client
 * 
 * @author Jeremias Mechler
 * 
 */
public class AIClient extends ClientBase {

	private Logger logger;

	/**
	 * 
	 * Constructor
	 * 
	 * @param server
	 * Reference to the server
	 * @param logic
	 * Reference to the game logic
	 * @param playerID
	 * This client's ID
	 */
	public AIClient(IServer server, Logic logic, int playerID) {
		super();
		logger = OJIMLogger.getLogger(this.getClass().toString());
		logger.log(Level.INFO, "Hello! AI client with ID " + playerID + " created.");
		if (server == null) {
			throw new IllegalArgumentException("Server == null");
		}
		if (logic == null) {
			throw new IllegalArgumentException("Logic == null");
		}
		super.setParameters(logic, playerID, server);
	}
<<<<<<< HEAD
	
	public void setReady() {
		ready();
	}
=======
>>>>>>> ee9c146425fec6c603100a41c7ee18f13d50a106
}
