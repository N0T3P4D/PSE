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

package org.ojim.logic.actions;

import org.ojim.logic.ServerLogic;

public class ActionMoveForward implements Action {

	private final int steps;
	private final ServerLogic logic;
	private final boolean executePasses;

	public ActionMoveForward(ServerLogic logic, boolean executePasses, int steps) {
		this.steps = steps;
		this.logic = logic;
		this.executePasses = executePasses;
	}

	@Override
	public void execute() {
		ActionMoveForward.execute(this.logic, this.executePasses, this.steps);
	}

	public static void execute(ServerLogic logic, boolean executePasses,
			int steps) {
		ActionMoveToField.execute(
				logic,
				executePasses,
				logic.getGameState().getFieldAt(
						logic.getGameState().getActivePlayer().getPosition()
								+ steps));
	}

}
