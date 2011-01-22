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

public class PlayerInfoWindow extends JPanel {

	// hält PlayerInfoField playerInfoField;
	private PlayerInfoField[] playerInfoFields;
	private static final int MAX_PLAYERS = 8;

	public PlayerInfoWindow() {
		super();
	}

	public void addPlayer(Player player,int cash) {
		if (findPlayer(player) != -1) {
			for (int i = 0; i < MAX_PLAYERS; i++) {
				if (playerInfoFields[i] == null) {
					playerInfoFields[i] = new PlayerInfoField(player, cash);
					break;
				}
			}
		}
	}

	public void removePlayer(Player player) {
		int i = findPlayer(player);
		for (int j = i; j <= MAX_PLAYERS; j++) {
			playerInfoFields[j] = playerInfoFields[j + 1];
		}
		playerInfoFields[MAX_PLAYERS] = null;
	}

	public void turnOn(Player player) {
		for (int i = 0; i < MAX_PLAYERS; i++) {
			if (playerInfoFields[i].isOn()) {
				playerInfoFields[i].turnOff();
				break;
			}
		}
		int i = findPlayer(player);
		playerInfoFields[i].turnOn();
	}

	public void changeCash(Player player, int newCashValue) {
		int i = findPlayer(player);
		playerInfoFields[i].changeCash(newCashValue);
	}

	private int findPlayer(Player player) {
		for (int i = 0; i < MAX_PLAYERS; i++) {
			if (playerInfoFields[i].isPlayer(player)) {
				return i;
			}
		}
		return -1;
	}
}
