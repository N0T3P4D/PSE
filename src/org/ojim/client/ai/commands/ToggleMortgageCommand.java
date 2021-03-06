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
public class ToggleMortgageCommand extends Command {

	private static final long serialVersionUID = 6069838209559130577L;
	private BuyableField[] fields;

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
	 * @param fields
	 *            Fields to toggle
	 */

	public ToggleMortgageCommand(Logic logic, IServer server, int playerId, BuyableField... fields) {
		super(logic, playerId, server);
		this.fields = fields;
	}

	@Override
	public void execute() {
		for (BuyableField field : fields) {
			boolean state = field.isMortgaged();
			this.toggleMortgage(field);
			assert (field.isMortgaged() != state);
		}
	}

}
