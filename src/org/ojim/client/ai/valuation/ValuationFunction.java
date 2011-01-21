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

package org.ojim.client.ai.valuation;

import org.ojim.logic.Logic;
import org.ojim.logic.rules.GameRules;
import org.ojim.logic.state.GameState;

/**
 * 
 * @author Jeremias Mechler
 * 
 */
public abstract class ValuationFunction {

	private Logic logic;
	private GameRules rules;

	// xZise: Implicit set by logic (logic.getGameState)
	// protected GameState state;

	protected ValuationFunction() {
	}

	private static ValuationFunction instance;

	// xZise: Falls du das brauchst ;) Eine Möglichkeit

	public static ValuationFunction getInstance(
			Class<? extends ValuationFunction> clazz) {
		return CapitalValuator.getInstance(false, clazz);
	}

	public static ValuationFunction getInstance(boolean forceNew,
			Class<? extends ValuationFunction> clazz) {
		if (instance == null) {
			try {
				instance = clazz.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;
	}

	public void setParameters(Logic logic, GameRules rules) {
		this.logic = logic;
		this.rules = rules;
	}

	protected final Logic getGameLogic() {
		return this.logic;
	}

	protected final GameRules getGameRules() {
		return this.rules;
	}

	protected final GameState getGameState() {
		return this.logic.getGameState();
	}

	public abstract double returnValuation();

}
