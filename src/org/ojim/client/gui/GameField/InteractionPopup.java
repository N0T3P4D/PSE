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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.ojim.client.gui.PlayerColor;
import org.ojim.language.Localizer;
import org.ojim.logic.state.Card;

public class InteractionPopup extends JPanel {

	/**
	 * Status Diese Enum erklärt welchen Zustand das Mittelfeld im Moment hat
	 * trade - Ein Handel zwischen zwei Spielern, dazu klickt man rechts auf
	 * einen Spieler auction - eine Auktion an der alle Teilhaben auctionStart -
	 * das Vorbereiten einer Auktion game - Das Spielfeld im normalen Zustand
	 * mit Ereigniskarten, Würfelergebnissen und dem Geld in der Mitte
	 * 
	 * 
	 */
	private enum status {
		trade, auction, auctionStart, game
	};

	private String message;
	private boolean cancelEnabled;
	private boolean acceptEnabled;
	private boolean isActive;
	private JLabel textLabel = new JLabel();
	private JPanel dicePanel = new JPanel();
	private JLabel diceTextLabel = new JLabel();
	private JLabel[] diceValues;
	private JPanel freeParkingCashPanel = new JPanel();
	private JLabel freeParkingCashLabel = new JLabel();
	private Localizer language;
	private int cash;
	//private 

	/** 
	 * Diese Methode initialisiert alles.
	 */
	public InteractionPopup() {
		
		
		this.setBackground(Color.BLACK);
		
		
		this.diceValues = new JLabel[2];
		dicePanel.add(diceTextLabel);
		for(int i = 0; i < diceValues.length; i++){
			diceValues[i] = new JLabel();
			dicePanel.add(diceValues[i]);
			diceValues[i].setBorder(new LineBorder(Color.BLACK, 1));
		}
		this.add(dicePanel);
		
		dicePanel.setBackground(Color.WHITE);
		
		freeParkingCashPanel.setBackground(Color.WHITE);

		freeParkingCashPanel.add(freeParkingCashLabel);
		this.add(freeParkingCashPanel);
		
		
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

	public void showDices(int[] diceValues) {
		if(this.diceValues == null){
			this.diceValues = new JLabel[diceValues.length];
		}
		for(int i = 0; i < diceValues.length; i++){
			this.diceValues[i].setText(" "+diceValues[i]+" ");
		}
	}
	
	public void showFreeParkingCash(int cash){
		this.cash = cash;
	}
	
	public void setLanguage(Localizer language){
		this.language = language;
		diceTextLabel.setText(language.getText("dice values")+": ");
		freeParkingCashLabel.setText(language.getText("free parking cash")+": "+cash+" "+language.getText("currency"));
		repaint();
	}
}
