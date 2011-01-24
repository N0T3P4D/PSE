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

import org.ojim.logic.state.Player;

/**
 * 
 * Die Klasse CapitalValuator bewertet, ob sich der KI-Client leisten kann, Geld auszugeben
 * 
 * @author Jeremias Mechler
 */
public final class CapitalValuator extends ValuationFunction {

	private CapitalValuator() {
	}

	/**
	 * This is a singleton object!
	 * 
	 * @return An instance
	 */
	public static CapitalValuator getInstance() {
		return ValuationFunction.getInstance(false, CapitalValuator.class);
	}

	/**
	 * Sometimes we have to specify an amount, for example if we want to buy something
	 * 
	 * @param amount
	 *            Amount of money
	 * @return Valuation
	 */
	public double returnValuation(int amount) {
		// Da die Klasse ein Singleton ist, muss der aktive Spieler bei jedem
		// Aufruf bestimmt werden
		Player currentPlayer = this.getGameState().getActivePlayer();

		// Die Gesamtgeldsumme aller Gegenspieler
		int sum = 0;
		// Der Geldbetrag des Spielers mit dem meisten Geld
		int max = 0;
		Player[] players = this.getGameState().getPlayers();
		for (Player player : players) {
			if (player == currentPlayer) {
				continue;
			}
			sum += player.getBalance();
			if (player.getBalance() > max) {
				max = player.getBalance();
			}
		}

		// Nach dem Papier
		double required = ValuationParameters.baseCash + ValuationParameters.averageCashPercentage
				* (((double) sum / (double) (players.length - 1)) + sum) + ValuationParameters.maxCashPercentage * max;

		if (currentPlayer.getBalance() - amount >= required) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public double returnValuation() {
		return returnValuation(0);
	}
}
