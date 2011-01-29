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

import javax.swing.JPanel;

import org.ojim.logic.state.Card;

public class InteractionPopup extends JPanel {

	/**
	 * Status Diese Enum erklärt welchen Zustand das Mittelfeld im Moment hat
	 * trade - Ein Handel zwischen zwei Spielern, dazu klickt man rechts auf
	 * einen Spieler auction - eine Auktion an der alle Teilhaben auctionStart -
	 * das Vorbereiten einer Auktion game - Das Spielfeld im normalen Zustand
	 * mit Ereigniskarten, Würfelergebnissen und dem Geld in der Mitte
	 * 
	 * @author Duke
	 * 
	 */
	private enum status {
		trade, auction, auctionStart, game
	};

	private String message;
	private boolean cancelEnabled;
	private boolean acceptEnabled;
	private boolean isActive;

	/** 
	 * Diese Methode initialisiert alles.
	 */
	public InteractionPopup() {

	}

	public void clear() {

	}

	public void showMessage(String message, boolean accepting, boolean declining) {

	}

	public void showInformation(String message) {

	}

	public void draw() {

	}
	
	
	public void showAuction(){
		
	} 
	
	public void showTrade() {
		
	}
	
	public void startAuction() {
		
	}
	
	public void close () {
		
	}
}
