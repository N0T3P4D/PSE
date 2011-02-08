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

package org.ojim.client.ai.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.ojim.log.OJIMLogger;
import org.ojim.logic.Logic;
import org.ojim.logic.state.Player;

import edu.kit.iti.pse.iface.IServer;

/**
 * 
 * Gets you out of prison
 * 
 * @author Jeremias Mechler
 * 
 */
public class OutOfPrisonCommand extends Command {
	
	private Player player;

	/**
	 * 
	 * Constructor
	 * 
	 * @param server
	 *            Reference to the server
	 * @param logic
	 *            Reference to the game logic
	 * @param playerId
	 *            The client's ID
	 * 
	 */
	public OutOfPrisonCommand(Logic logic, IServer server, int playerId) {
		super(logic, playerId, server);
		this.player = logic.getGameState().getPlayerByID(playerId);
	}

	@Override
	public void execute() {
		Logger logger = OJIMLogger.getLogger(getClass().toString());
		OJIMLogger.changeLogLevel(logger, Level.FINE);
		if (this.player.getNumberOfGetOutOfJailCards() > 0) {
			logger.log(Level.FINE, "Using OutOfJailCard!");
			useGetOutOfJailCard();
		} else {
			logger.log(Level.FINE, "Player " + this.player.getId() + " Paying fine!");
			payFine();
		}
	}

}
