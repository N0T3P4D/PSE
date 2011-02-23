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

	double valuation = 0;

	protected Command(Logic logic, int playerId, IServer server) {
		super(logic, playerId, server);

	}

	/**
	 * Execute method, has to be implemented by all commands!
	 */
	public abstract void execute();

	@Override
	public int compareTo(Command c) {
		return (int) ((-1)*(this.valuation - c.getValuation()));
	}

	public double getValuation() {
		return valuation;
	}
	
	public void setValuation(double valuation) {
		this.valuation = valuation;
	}
	
	@Override
	public String toString() {
		return this.getClass().toString();
	}
}
