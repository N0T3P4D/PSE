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

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.ojim.client.gui.GUIClient;
import org.ojim.language.Localizer;
import org.ojim.logic.state.Auction;
import org.ojim.logic.state.GameState;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.fields.BuyableField;
import org.ojim.logic.state.fields.Field;
import org.ojim.logic.state.fields.Street;
import org.ojim.logic.state.fields.StreetFieldGroup;

/**
 * Das Spielfeld
 * 
 */
public class GameField extends JPanel {

	private static final long serialVersionUID = 5398588367941146349L;
	
	private GameFieldPiece[] fields;
	private boolean isInitialized = false;
	private static Player me;

	// Das Feld auf das zuletzt mit der Maus geklickt wurde

	// Hält GameFieldPieceCollection
	// Hält Referenz auf GameFieldPiece
	private InteractionPopup interactionPopup;

	private MouseListener mouseListener = new MouseListener() {

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
			Field selected = null;
			if (e.getComponent() instanceof GameFieldPiece) {
				selected = ((GameFieldPiece) e.getComponent()).getField();
			}
			
			interactionPopup.fieldClicked(e.getComponent().getName(), me);
			
			if (selected instanceof Street && GameField.this.allOfGroupOwned((Street) selected)) {
				interactionPopup.showUpgrade((Street) selected);
			} else {
				interactionPopup.deleteUpgrade();
			}
		}
	};

	public GameField(GUIClient guiClient) {
		interactionPopup = new InteractionPopup(guiClient, me);

	}

	public void buildOnStreet(Field field) {
		this.fields[field.getPosition()].redrawStreet();
//		for (int i = 0; i < GameState.FIELDS_AMOUNT; i++) {
//			if (this.fields[i].isField(field)) {
//				this.fields[i].redrawStreet();
//			}
//		}
		redraw();

	}

	public void playerBuysField(Player player, Field field) {
		this.fields[field.getPosition()].draw();
		redraw();

	}

	public void destroyOnStreet(Field field) {
		this.fields[field.getPosition()].redrawStreet();
//		for (int i = 0; i < GameState.FIELDS_AMOUNT; i++) {
//			if (this.fields[i].isField(field)) {
//				this.fields[i].redrawStreet();
//			}
//		}
		redraw();

	}

	public void switchFieldStatus(Field field) {
		this.fields[field.getPosition()].draw();
		System.out.println(field.getName()+" wird mortaged");
		redraw();

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

		for (GameFieldPiece piece : this.fields) {
			piece.removePlayer(player);
		}
		this.fields[field.getPosition()].addPlayer(player);

		redraw();
	}

	public void init(GameState gameState, GUIClient gui) {

		// Mittelfeld
		// interactionPopup.setBackground(Color.black);
		interactionPopup.setName(-1 + "");

		this.add(interactionPopup);

		// System.out.println("GAMEFIELD UPDATE");

		this.setLayout(new GameFieldLayout());

		fields = new GameFieldPiece[gameState.getNumberOfFields()];
		for (int i = 0; i < gameState.getNumberOfFields(); i++) {
			fields[i] = new GameFieldPiece(gameState.getFieldAt(i), gui);
			try {
				fields[i].setField(gameState.getFieldAt(i));
				fields[i].init(gameState);
			} catch (NullPointerException e) {
				// System.out.println("IDx "+i);
			}

		}
		isInitialized = true;

		for (int i = 0; i < gameState.getNumberOfFields(); i++) {
			// ((GameFieldPiece) actualLabel).draw();
			fields[i].setName(i + "");
			fields[i].setBorder(new LineBorder(Color.black, 1));
			fields[i].addMouseListener(mouseListener);
			this.add(fields[i]);

		}

		redraw();
	}

	/*
	 * public void draw() { this.setLayout(new GameFieldLayout());
	 * 
	 * JPanel actualLabel = new JPanel();
	 * 
	 * actualLabel.setBackground(Color.black);
	 * 
	 * actualLabel.setName(-1 + ""); this.add(actualLabel);
	 * 
	 * for (int i = 0; i < GameState.MAXIMUM_PLAYER_COUNT; i++) { //
	 * this.remove(playerLabel[i]); // System.out.println("Er malt."); }
	 * 
	 * for (int i = 0; i < GameState.FIELDS_AMOUNT; i++) { try {
	 * ((GameFieldPiece) actualLabel).removePlayer(); } catch
	 * (ClassCastException e) {
	 * 
	 * } }
	 * 
	 * for (int j = 0; j < GameState.MAXIMUM_PLAYER_COUNT; j++) { for (int i =
	 * 0; i < GameState.FIELDS_AMOUNT; i++) { actualLabel = fields[i]; try { if
	 * (i == field[j].getPosition()) { ((GameFieldPiece)
	 * actualLabel).addPlayer(player[j]); ((GameFieldPiece) actualLabel).draw();
	 * } actualLabel.setName(i + ""); actualLabel.setBorder(new
	 * LineBorder(Color.black, 1)); actualLabel.addMouseListener(mouseListener);
	 * this.add(actualLabel);
	 * 
	 * } catch (NullPointerException e) {
	 * 
	 * } } }
	 * 
	 * }
	 */

	public void redraw() {
		/*
		 * for (int i = 0; i < fieldsAmount; i++) { try {
		 * ((GameFieldPiece)actualLabel).removePlayer(); } catch
		 * (ClassCastException e) {
		 * 
		 * } }
		 */

	}

	public boolean isInitialized() {
		return isInitialized;
	}

	public void dices(int[] diceValues) {
		interactionPopup.showDices(diceValues);

	}

	public void setFreeParkingMoney(int moneyInPot) {
		interactionPopup.showFreeParkingCash(moneyInPot);

	}

	public void setLanguage(Localizer language) {
		if (interactionPopup != null) {
			interactionPopup.setLanguage(language);
		}

	}

	public void playerIsBancrupt(Player bancruptPlayer) {
		for (int i = 0; i < this.fields.length; i++) {
			this.fields[i].draw();
			this.fields[i].removePlayer(bancruptPlayer);
		}

	}

	public static void addMe(Player me2) {
		me = me2;

	}

	/**
	 * Funktion von Jeremias
	 * 
	 * @param street
	 *            eingegebene Straße
	 * @return gehören mir alle Teile der Straße?
	 */
	private boolean allOfGroupOwned(Street street) {
		StreetFieldGroup group = street.getFieldGroup();
		// System.out.println("3!");
		if (group.getFields().length > 1) {
			// System.out.println("4!");
			int count = 0;

			for (Field field : group.getFields()) {
				// System.out.println("5!");
				if (((BuyableField) field).getOwner() == me) {
					count++;
				}
			}
			return (count == group.getFields().length);
		} else {
			System.out.println(street.getFieldGroup().getName());
		}
		return false;
	}

	public void showTrade(Player me, Player partnerPlayer, int requiredCash,
			BuyableField[] requiredBuyableFields, int requiredOutOfJailCards,
			int offeredCash, BuyableField[] offeredBuyableFields,
			int offeredOutOfJailCards) {

		interactionPopup.showTrade(me, partnerPlayer, offeredOutOfJailCards,
				offeredBuyableFields, offeredOutOfJailCards,
				offeredOutOfJailCards, offeredBuyableFields,
				offeredOutOfJailCards);

	}

	public void endTrade() {
		interactionPopup.endTrade();

	}

	public void showAuction(Auction auction) {
		interactionPopup.showAuction(auction.getState(), auction.objective, auction.getHighestBidder(),
				auction.getHighestBid());
		
	}

	public void removeAuction() {
		interactionPopup.removeAuction();
		
	}

}
