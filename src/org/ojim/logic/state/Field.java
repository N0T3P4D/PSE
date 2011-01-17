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

package org.ojim.logic.state;

import org.ojim.logic.actions.Action;

public class Field {
	
	private final String name;
	private final int position;
	
	private Action[] executeActions;
	private Action[] passThroughActions;
	
	public Field(String name, int position) {
		this(name, position, new Action[0], new Action[0]);
	}
	
	public Field(String name, int position, Action[] executeActions, Action[] passThroughActions) {
		this.name = name;
		this.position = position;
		this.setActions(executeActions, passThroughActions);
	}
	
	protected final void setActions(Action[] executeActions, Action[] passThroughActions) {
		this.executeActions = executeActions;
		this.passThroughActions = passThroughActions;
	}
	
	protected final void setExecuteActions(Action... executeActions) {
		this.executeActions = executeActions;
	}
	
	protected final void setPassThroughActions(Action... passThroughActions) {
		this.passThroughActions = passThroughActions;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public void execute() {
		for (int i = 0; i < this.executeActions.length; i++) {
			this.executeActions[i].execute();
		}
	}
	
	public void passThrough() {
		for (int i = 0; i < this.passThroughActions.length; i++) {
			this.passThroughActions[i].execute();
		}		
	}
}
