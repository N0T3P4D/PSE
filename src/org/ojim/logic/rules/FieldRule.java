/*  Copyright (C) 2010  Fabian Neundorf, Philip Caroli, Maximilian Madlung, 
 * 						Usman Ghani Ahmed, Jeremias Mechler
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

package org.ojim.logic.rules;

public class FieldRule {
	
	private final String name;
	private final int position;
	
	private final Action[] executeActions;
	private final Action[] passThroughActions;
	
	/**
	 * Creates a new field rule.
	 * @param name
	 * @param position
	 * @param executeActions
	 * @param passThroughActions
	 * @see {@link #newExecuteRule(String, int, Action...)}
	 * @see {@link #newPassThroughRule(String, int, Action...)}
	 */
	public FieldRule(String name, int position, Action[] executeActions, Action[] passThroughActions) {
		this.executeActions = executeActions;
		this.passThroughActions = passThroughActions;
		this.name = name;
		this.position = position;
	}
	
	/**
	 * Creates a new field rule only with execute Actions.
	 * @param name
	 * @param position
	 * @param executeActions
	 * @return The new field rule.
	 * @see {@link #FieldRule(String, int, Action[], Action[])}
	 * @see {@link #newPassThroughRule(String, int, Action...)}
	 */
	public static FieldRule newExecuteRule(String name, int position, Action... executeActions) {
		return new FieldRule(name, position, executeActions, new Action[0]);
	}
	
	/**
	 * 
	 * @param name
	 * @param position
	 * @param passThroughActions
	 * @return
	 * @see {@link #FieldRule(String, int, Action[], Action[])}
	 * @see {@link #newExecuteRule(String, int, Action...)}
	 */
	public static FieldRule newPassThroughRule(String name, int position, Action... passThroughActions) {
		return new FieldRule(name, position, new Action[0], passThroughActions);
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
