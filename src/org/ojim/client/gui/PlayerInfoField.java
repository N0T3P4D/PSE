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

package org.ojim.client.gui;

import javax.swing.JPanel;

import org.ojim.logic.state.Player;

public class PlayerInfoField extends JPanel {

	private boolean isTurnedOn;
	private Player player;
	private int cash;

	public PlayerInfoField(Player player, int cash) {
		this.player = player;
		isTurnedOn = false;
		this.cash = cash;
	}

	public void switchStatus() {
		isTurnedOn = !isTurnedOn;
	}

	public boolean isPlayer(Player player) {
		// TODO: Player Objekt eine richtige Equalsmethode übergeben?
		if (this.player.getId() == player.getId()) {
			return true;
		} else {
			return false;
		}
	}

	public void turnOn() {
		isTurnedOn = true;

	}

	public void turnOff() {
		isTurnedOn = false;

	}

	public boolean isOn() {
		return isTurnedOn;
	}

	public void changeCash(int newCashValue) {
		cash = newCashValue;

	}

}
