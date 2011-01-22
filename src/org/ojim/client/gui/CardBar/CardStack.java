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

import java.awt.GridLayout;

import javax.swing.JLabel;
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
			cards[i] = new Card();
		}

		draw();
		// TODO Auto-generated constructor stub
	}

	// Um verwechslungen mit der GUI Card zu vermeiden, die ausführliche
	// Beschreibung
	public void addCard(org.ojim.logic.state.fields.BuyableField card) {
		for (int i = 0; i < MAX_CARDS; i++) {
			if (cards[i] == null) {
				cards[i].setCard(card);
			}
		}
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
		// TODO EIGENES VERDECKUNGSLAYOUT
		this.setLayout(new GridLayout(0, MAX_CARDS));

		for (int i = 0; i < MAX_CARDS; i++) {
			this.add(cards[i]);
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
