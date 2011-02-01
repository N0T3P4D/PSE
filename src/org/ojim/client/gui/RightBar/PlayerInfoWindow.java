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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import org.ojim.client.gui.GUIClient;
import org.ojim.language.Localizer;
import org.ojim.logic.state.GameState;
import org.ojim.logic.state.Player;

public class PlayerInfoWindow extends JPanel {

	// hält PlayerInfoField playerInfoField;
	private PlayerInfoField[] playerInfoFields;
	private Localizer language;
	private GUIClient gui;

	public PlayerInfoWindow(GUIClient guiClient) {

		gui = guiClient;

		this.setLayout(new GridLayout(GameState.MAXIMUM_PLAYER_COUNT, 0));
		playerInfoFields = new PlayerInfoField[GameState.MAXIMUM_PLAYER_COUNT];
		/*
		 * for(int i = 0; i < MAX_PLAYERS; i++){ playerInfoFields[i] = new
		 * PlayerInfoField(null, 0, language); }
		 */

		/*
		 * 
		 * Player player = new Player("Test 1", 5, 5, 1, 0); Player playerTwo =
		 * new Player("Test 2", 5, 5, 2, 1); Player playerThree = new
		 * Player("Test 3", 5, 5, 3, 2); Player playerFour = new
		 * Player("Test 4", 5, 5, 4, 3);
		 * 
		 * addPlayer(player, 500); addPlayer(playerTwo, 501);
		 * addPlayer(playerThree, 399); addPlayer(playerFour, 222);
		 */

		// System.out.println("Alles müsste da sein.");

	}

	public void setLanguage(Localizer language) {
		this.language = language;

		for (int i = 0; i < GameState.MAXIMUM_PLAYER_COUNT; i++) {
			try {
				if (!playerInfoFields[i].isNull()) {
					playerInfoFields[i].setLanguage(language);
				}
				
			} catch (NullPointerException e){
				
			}
		}

		draw();
	}

	public void addPlayer(Player player) {
		//System.out.println("addPlayer");
		if (findPlayer(player) == -1) {
			// System.out.println(player.getId()+" nicht gefunden");
			for (int i = 0; i < GameState.MAXIMUM_PLAYER_COUNT; i++) {
				if (playerInfoFields[i] == null) {
					//System.out.println(player.getId()+" mit id "+i+" hinzugefügt");
					playerInfoFields[i] = new PlayerInfoField(player, player.getBalance(),
							language);
					this.add(playerInfoFields[i]);
					playerInfoFields[i].addMouseListener(playerMouseListener);
					playerInfoFields[i].setName(i+"");
					break;
				}
			}
		}
		draw();
	}

	private void draw() {
		/*
		this.removeAll();
		this.revalidate();
		
		this.setLayout(new GridLayout(GameState.MAXIMUM_PLAYER_COUNT, 0));

		for (int i = 0; i < GameState.MAXIMUM_PLAYER_COUNT; i++) {
			if (playerInfoFields[i] != null) {
				playerInfoFields[i].addMouseListener(playerMouseListener);
				playerInfoFields[i].setName(i+"");
				this.add(playerInfoFields[i]);
			}
			//System.out.println("Player "+i+" wurde nun angeblich hinzugefügt!");
		}*/
		repaint();
	}
	
	MouseListener playerMouseListener = new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("Clicked on Player "+e.getComponent().getName());
			gui.showTrade(Integer.parseInt(e.getComponent().getName()));
			
		}
	};
	

	public void removePlayer(Player player) {
		int i = findPlayer(player);
		for (int j = i; j <= GameState.MAXIMUM_PLAYER_COUNT; j++) {
			playerInfoFields[j] = playerInfoFields[j + 1];
		}
		playerInfoFields[GameState.MAXIMUM_PLAYER_COUNT] = null;
		draw();
	}

	public void turnOn(Player player) {
		for (int i = 0; i < GameState.MAXIMUM_PLAYER_COUNT; i++) {
			if (playerInfoFields[i] != null && playerInfoFields[i].isOn()) {
				playerInfoFields[i].turnOff();
				break;
			}
		}
		int i = findPlayer(player);
		if (i != -1) {
			playerInfoFields[i].turnOn();
		}
		draw();
	}

	public void changeCash(Player player, int newCashValue) {
		int i = findPlayer(player);
		if (i != -1) {
			playerInfoFields[i].changeCash(newCashValue);
		}
		draw();
	}

	private int findPlayer(Player player) {
		for (int i = 0; i < GameState.MAXIMUM_PLAYER_COUNT; i++) {
			if (playerInfoFields[i] != null
					&& playerInfoFields[i].isPlayer(player)) {
				return i;
			}
		}
		return -1;
	}

	public void setBancrupt(Player player) {
		playerInfoFields[findPlayer(player)].setBancrupt();
		
	}
}
