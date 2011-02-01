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

import org.ojim.client.SimpleClient;
import org.ojim.logic.Logic;
import edu.kit.iti.pse.iface.IServer;

/**
 * 
 * Null command - does nothing
 * 
 * @author Jeremias Mechler
 * 
 */
public class EndTurnCommand extends Command {

	private static final long serialVersionUID = 3744229015786962057L;
	
	private Logic logic;

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
	 */
	public EndTurnCommand(Logic logic, IServer server, int playerId) {
		super(logic, playerId, server);
		this.logic = logic;
	}

	@Override
	public void execute() {
		int result[] = getGameState().getDices().getResult();
		assert(result.length == 2);
		
		if (result[0] == result[1]) {
			System.out.println("rollDice!");
			if (!rollDice()) {
				System.out.println("mist durfte aber nicht :(");
			}
		}
		else {
			System.out.println("EndTurn!");
			endTurn();
		}
	}

}