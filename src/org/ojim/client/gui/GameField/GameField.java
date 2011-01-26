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

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.ojim.client.gui.PlayerColor;
import org.ojim.client.gui.StreetColor;
import org.ojim.logic.state.GameState;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.fields.Field;

public class GameField extends JPanel {

	GameFieldPiece[] fields;
	int fieldsAmount;
	JPanel[] playerLabel;
	Player[] player;
	Field[] field;

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

	public GameField() {
		playerLabel = new JPanel[GameState.MAXIMUM_PLAYER_COUNT];
		for (int i = 0; i < GameState.MAXIMUM_PLAYER_COUNT; i++) {
			playerLabel[i] = new JPanel();
		}

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
		//this.remove(playerLabel[player.getId()]);
		this.player[player.getId()] = player;
		this.field[player.getId()] = field;
		playerLabel[player.getId()].setBackground(PlayerColor
				.getBackGroundColor(player.getColor()));
		playerLabel[player.getId()].setName(field.getPosition() + "000");
		playerLabel[player.getId()].setBorder(new LineBorder(Color.black, 1));
		this.add(playerLabel[player.getId()]);
		//this.revalidate();
	}

	public void init(int fieldsAmount, GameState gameState) {
		// System.out.println("GAMEFIELD UPDATE");
		this.player = new Player[GameState.MAXIMUM_PLAYER_COUNT];
		this.field = new Field[GameState.MAXIMUM_PLAYER_COUNT];
		
		for (int i = 0; i < GameState.MAXIMUM_PLAYER_COUNT; i++) {
			this.player[i] = gameState.getPlayers()[i];
			this.field[i] = gameState.getFieldAt(gameState.getPlayers()[i].getPosition());
		}
		
		
		this.fieldsAmount = fieldsAmount;
		fields = new GameFieldPiece[fieldsAmount];
		for (int i = 0; i < fieldsAmount; i++) {
			fields[i] = new GameFieldPiece(gameState.getFieldAt(i));
			fields[i].setField(gameState.getFieldAt(i));
		}
		draw();
	}

	public void draw() {

		this.setLayout(new GameFieldLayout());

		for (int i = 0; i < GameState.MAXIMUM_PLAYER_COUNT; i++) {
				//this.remove(playerLabel[i]);
				//System.out.println("Er malt.");
				playerLabel[i].setBackground(PlayerColor
						.getBackGroundColor(player[i].getColor()));
				playerLabel[i].setName(field[i].getPosition() + "000");
				playerLabel[i].setBorder(new LineBorder(Color.black, 1));
				this.add(playerLabel[i]);
				//this.revalidate();
		}
		JPanel actualLabel = new JPanel();

		actualLabel.setBackground(Color.black);

		actualLabel.setName(-1 + "");
		this.add(actualLabel);

		for (int i = 0; i < fieldsAmount; i++) {
			actualLabel = fields[i];
			/*
			 * if(i%2 == 0){
			 * actualLabel.setBackground(StreetColor.getBackGroundColor(0)); }
			 * else {
			 * actualLabel.setBackground(StreetColor.getBackGroundColor(1)); }
			 */
			try {
				actualLabel.setName(i + "");
				actualLabel.setBorder(new LineBorder(Color.black, 1));
				actualLabel.addMouseListener(mouseListener);
				this.add(actualLabel);

			} catch (NullPointerException e) {

			}
		}


	}

}
