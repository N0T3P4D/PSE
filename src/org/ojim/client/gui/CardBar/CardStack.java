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

package org.ojim.client.gui.CardBar;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import org.ojim.language.Localizer;

// Card Stack ist in diesem Fall ein Kartenstapel unten im Card Window

public class CardStack extends JPanel {

	// Hält Cards
	private Card[] cards;
	private static final int MAX_CARDS = 4;
	private org.ojim.logic.state.fields.FieldGroup fieldGroup;
	private Localizer language;

	public CardStack() {
		super();

		cards = new Card[MAX_CARDS];

		for (int i = 0; i < MAX_CARDS; i++) {
			cards[i] = null;
		}

		draw();
	}

	// Um verwechslungen mit der GUI Card zu vermeiden, die ausführliche
	// Beschreibung
	public void addCard(org.ojim.logic.state.fields.BuyableField card) {
		for (int i = 0; i < MAX_CARDS; i++) {
			//System.out.println("Karte gesetztXXX");
			if (cards[i] == null) {
				//System.out.println("Karte gesetzt");
				cards[i] = new Card();
				cards[i].setCard(card);
				this.fieldGroup = card.getFieldGroup();
				break;
			}
		}
		//System.out.println("Karte gekauft;");
		draw();
	}

	public void removeCard(org.ojim.logic.state.fields.BuyableField card) {
		int empty = -1;
		for (int i = 0; i < MAX_CARDS; i++) {
			if (cards[i].getCard().equals(card)) {
				cards[i].removeCard(card);
				empty = i;
				break;
			}
		}
		if (empty != -1) {
			for (int i = empty; i < MAX_CARDS - 1; i++) {
				cards[i] = cards[i + 1];
			}
			cards[MAX_CARDS - 1] = new Card();
		}
		if (cards[0].getCard() != null) {
			setFieldGroupNull();
		}
		draw();
	}

	public void setLanguage(Localizer language) {
		this.language = language;

	}

	public void draw() {

		//this.setLayout(new CardStackLayout());
		this.setLayout(new GridBagLayout());

		for (int i = 0; i < MAX_CARDS; i++) {
			try {
				this.remove(cards[i]);
				cards[i].draw();
				this.add(cards[i]);
			} catch (NullPointerException e) {

			}
		}
	}

	public org.ojim.logic.state.fields.FieldGroup getFieldGroup() {
		return fieldGroup;
	}

	public void setFieldGroupNull() {
		fieldGroup = null;
	}

	public boolean isEmpty() {
		return (fieldGroup != null);
	}

}
