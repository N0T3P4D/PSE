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
import org.ojim.logic.state.fields.Field;

public class ActionMoveForward implements Action {

	private final int steps;
	private final ServerLogic logic;
	private final boolean executePasses;
	private final boolean special;

	public ActionMoveForward(ServerLogic logic, boolean executePasses, boolean special, int steps) {
		this.steps = steps;
		this.logic = logic;
		this.executePasses = executePasses;
		this.special = special;
	}

	@Override
	public void execute() {
		Field target = this.logic.getGameState().getFieldAt(
				(this.logic.getGameState().getActivePlayer().getPosition() + this.steps) % this.logic.getGameState().getNumberOfFields());
		logic.movePlayerTo(target, this.logic.getGameState().getActivePlayer(), this.special, this.executePasses);
	}
}
