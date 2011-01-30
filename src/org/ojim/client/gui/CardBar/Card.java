/*  Copyright (C) 2010  Fabian Neundorf, Philip Caroli, Maximilian Madlung, 
 * 						Usman Ghani Ahmed, Jeremias Mechler
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

package org.ojim.client.gui.CardBar;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.ojim.client.gui.StreetColor;
import org.ojim.client.gui.OLabel.FontLayout;
import org.ojim.logic.state.fields.BuyableField;

/**
 * Eine Karte im Kartenstapel des Spielers
 *
 */
public class Card extends JPanel {

	private boolean isTurnedAround = false;
	private org.ojim.logic.state.fields.BuyableField card;
	private JPanel colorPanel = new JPanel();
	private JPanel textPanel = new JPanel();
	private JLabel textLabel = new JLabel();

	/**
	 * Initialisiert eine Karte
	 */
	public Card() {
		super();
		draw();
	}

	/**
	 * Dreht die Karte herum (Hypothek)
	 */
	public void turnAround() {
		isTurnedAround = !isTurnedAround;
	}

	/**
	 * Weist einer Karte ein Feld zu
	 * @param card Das Spielfeld
	 */
	public void setCard(org.ojim.logic.state.fields.BuyableField card) {
		//System.out.println("Karte zu "+card.getName()+" gesetzt");
		this.card = card;
	}

	/**
	 * gibt das Spielfeld der Karte zurück
	 * @return Das Spielfeld
	 */
	public org.ojim.logic.state.fields.BuyableField getCard() {
		return card;

	}

	/**
	 * löst das Spielfeld von der Karte
	 */
	public void removeCard() {
		this.card = null;

	}

	/**
	 * Zeichnet die Karte
	 */
	public void draw() {
		if (card != null) {

			this.remove(colorPanel);
			this.remove(textLabel);
			
			colorPanel.setBackground(StreetColor.getBackGroundColor(card
					.getFieldGroup().getColor()));
			textPanel.setBackground(Color.white);
			
			//System.out.println("Draww");
			
			//textLabel.setLayout(new FontLayout());
			textLabel.setText("<html>"+card.getName());

			this.setLayout(new GridLayout(0,1));
			this.add(colorPanel);
			textPanel.add(textLabel);
			this.add(textPanel);
		}

	}
}
