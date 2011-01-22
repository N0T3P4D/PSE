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

package org.ojim.client.gui.RightBar;

import java.awt.GridLayout;

import javax.swing.JPanel;

import org.ojim.language.Localizer;
import org.ojim.logic.state.Player;

public class PlayerInfoWindow extends JPanel {

	private static final int MAX_PLAYERS = 8;
	// hält PlayerInfoField playerInfoField;
	private PlayerInfoField[] playerInfoFields;
	private Localizer language;

	public PlayerInfoWindow(Localizer language) {
		
		this.language = language;
		
		
		playerInfoFields = new PlayerInfoField[MAX_PLAYERS];
		
		for(int i = 0; i < MAX_PLAYERS; i++){
			playerInfoFields[i] = new PlayerInfoField(Player.NullPlayer, 0, language);
		}
		
		Player player = new Player("Test 1", 5, 5, 1, 0);
		Player playerTwo = new Player("Test 2", 5, 5, 2, 1);
		Player playerThree = new Player("Test 3", 5, 5, 3, 2);
		Player playerFour = new Player("Test 4", 5, 5, 4, 3);

		addPlayer(player, 500);
		addPlayer(playerTwo, 501);
		addPlayer(playerThree, 399);
		addPlayer(playerFour, 222);

		//System.out.println("Alles müsste da sein.");
		
	}
	
	public void setLanguage(Localizer language) {
		this.language = language;
		
		for (int i = 0; i < MAX_PLAYERS; i++) {
			if (!playerInfoFields[i].isNull()) {
				playerInfoFields[i].setLanguage(language);
			}
		}
		
		draw();
	}

	public void addPlayer(Player player, int cash) {
		//System.out.println("addPlayer");
		if (findPlayer(player) == -1) {
			//System.out.println(player.getId()+" nicht gefunden");
			for (int i = 0; i < MAX_PLAYERS; i++) {
				if (playerInfoFields[i].isNull()) {
					//System.out.println(player.getId()+" mit id "+i+" hinzugefügt");
					playerInfoFields[i] = new PlayerInfoField(player, cash, language);
					break;
				}
			}
		}
		draw();
	}

	private void draw() {
		this.removeAll();
		
		this.setLayout(new GridLayout(MAX_PLAYERS,0));
		
		for (int i = 0; i < MAX_PLAYERS; i++) {
			if(!playerInfoFields[i].isNull()){
				this.add(playerInfoFields[i]);
			}
			//System.out.println("Player "+i+" wurde nun angeblich hinzugefügt!");
		}
	}

	public void removePlayer(Player player) {
		int i = findPlayer(player);
		for (int j = i; j <= MAX_PLAYERS; j++) {
			playerInfoFields[j] = playerInfoFields[j + 1];
		}
		playerInfoFields[MAX_PLAYERS] = null;
		draw();
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
		draw();
	}

	public void changeCash(Player player, int newCashValue) {
		int i = findPlayer(player);
		playerInfoFields[i].changeCash(newCashValue);
		draw();
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