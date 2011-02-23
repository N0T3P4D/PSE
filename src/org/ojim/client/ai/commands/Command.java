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
 * Interface for the commands
 * 
 * @author Jeremias Mechler
 * 
 */
public abstract class Command extends SimpleClient implements Comparable<Command> {

	private static final long serialVersionUID = 8814513647822392905L;
	double valuation = 0;

	/**
	 * Constructor
	 * 
	 * @param logic
	 *            reference to logic
	 * @param playerId
	 *            calling player's id
	 * @param server
	 *            reference to server
	 */
	protected Command(Logic logic, int playerId, IServer server) {
		super(logic, playerId, server);

	}

	/**
	 * Execute method, has to be implemented by all commands!
	 */
	public abstract void execute();

<<<<<<< HEAD
	@Override
=======
	/**
	 * For ordering commands by their valuation
	 * 
	 * @param c
	 *            other command
	 * @return result as defined in the interface
	 */
>>>>>>> 9f2de6a7826dfde1b959cbe6a128ea3382288f7c
	public int compareTo(Command c) {
		return (int) ((-1) * (this.valuation - c.getValuation()));
	}

	/**
	 * Get valuation
	 * 
	 * @return valuation
	 */
	public double getValuation() {
		return valuation;
	}

	/**
	 * Set valuation
	 * 
	 * @param valuation
	 *            valuation
	 */
	public void setValuation(double valuation) {
		this.valuation = valuation;
	}
<<<<<<< HEAD
	
	@Override
	public String toString() {
		return this.getClass().toString();
	}
=======
>>>>>>> 9f2de6a7826dfde1b959cbe6a128ea3382288f7c
}
