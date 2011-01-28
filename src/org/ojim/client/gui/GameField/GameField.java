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

package org.ojim.client.gui.GameField;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import org.ojim.logic.state.GameState;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.fields.Field;

public class GameField extends JPanel {

	GameFieldPiece[] fields;
	Player[] player;
//	int fieldsAmount;
//	JPanel[] playerLabel;
//	Field[] field;

	// Das Feld auf das zuletzt mit der Maus geklickt wurde
	String selectedField;

	MouseListener mouseListener = new MouseListener() {

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
			selectedField = e.getComponent().getName();
			System.out.println(selectedField);

		}
	};

	public GameField(GameState state) {
		// Load all data
		this.fields = new GameFieldPiece[GameState.FIELDS_AMOUNT];
		for (int i = 0; i < GameState.FIELDS_AMOUNT; i++) {
			this.fields[i] = new GameFieldPiece(state.getFieldAt(i));
		}
		
		this.player = state.getPlayers();
		
		this.setLayout(new GameFieldLayout());
		
		this.draw();
	}

	// Hält GameFieldPieceCollection
	// Hält Referenz auf GameFieldPiece
	InteractionPopup interactionPopup;

	public void buildOnStreet(Field field) {
		// TODO Auto-generated method stub
		draw();

	}

	public void playerBuysField(Player player, Field field) {
		// TODO Auto-generated method stub
		draw();

	}

	public void destroyOnStreet(Field field) {
		// TODO Auto-generated method stub
		draw();

	}

	public void switchFieldStatus(Field field) {
		// TODO Auto-generated method stub
		draw();

	}

	public void playerMoves(Field field, Player player) {
		// this.remove(playerLabel[player.getId()]);
		/*
		 * this.player[player.getId()] = player; this.field[player.getId()] =
		 * field;
		 * 
		 * playerLabel[player.getId()].setBackground(PlayerColor
		 * .getBackGroundColor(player.getColor()));
		 * playerLabel[player.getId()].setName(field.getPosition() + "000");
		 * playerLabel[player.getId()].setBorder(new LineBorder(Color.black,
		 * 1)); this.add(playerLabel[player.getId()]); this.revalidate();
		 */
		draw();
	}

	public void draw() {		
		for (GameFieldPiece field : this.fields) {
			field.removePlayers();
			field.update();
		}
		
		for (Player player : this.player) {
			this.fields[player.getPosition()].addPlayer(player);
		}

	}

}
