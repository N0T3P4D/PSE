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
import org.ojim.logic.state.fields.BuyableField;

import edu.kit.iti.pse.iface.IServer;

/**
 * 
 * Toggles a mortgage
 * 
 * @author Jeremias Mechler
 * 
 */
public class MortgageCommand extends SimpleClient implements Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8228252721669010341L;
	private int position;

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
	 * @param position
	 *            Position to toggle
	 */

	public MortgageCommand(Logic logic, IServer server, int playerId, int position) {
		super(logic, playerId, server);
		this.position = position;
	}

	@Override
	public void execute() {
		BuyableField field = (BuyableField) getLogic().getGameState().getFieldAt(position);
		if (field.isMortgaged()) {
			field.setMortgaged(true);
		} else {
			field.setMortgaged(false);
		}
	}

}
