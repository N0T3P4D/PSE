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

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import org.ojim.client.gui.GUIClient;
import org.ojim.language.Localizer;
import org.ojim.logic.state.fields.Field;

/**
 * CardStack ist ein Kartenstapel der die Karten der Spielfelder des GUI
 * Spielers hält
 */
public class CardStack extends JPanel {

	// Hält Cards
	private Card[] cards;
	private static final int MAX_CARDS = 4;
	private org.ojim.logic.state.fields.FieldGroup fieldGroup;
	private Localizer language;

	/**
	 * Initialisiert den Kartenstapel
	 */
	public CardStack() {
		super();

		cards = new Card[MAX_CARDS];

		for (int i = 0; i < MAX_CARDS; i++) {
			cards[i] = null;
		}

		draw();
	}

	/**
	 * fügt eine Karte zum Kartenstapel des Spielers hinzu
	 * 
	 * @param card
	 *            das Spielfeld welches als Karte hinzugefügt werden soll
	 */
	public void addCard(org.ojim.logic.state.fields.BuyableField card, GUIClient gui) {
		for (int i = 0; i < MAX_CARDS; i++) {
			// System.out.println("Karte gesetztXXX");
			if (cards[i] == null) {
				// System.out.println("Karte gesetzt");
				cards[i] = new Card(gui);
				System.out.println(i+" ist eine neue Karte "+card.getName());
				cards[i].setCard(card);
				this.fieldGroup = card.getFieldGroup();
				break;
			}
		}
		// System.out.println("Karte gekauft;");
		draw();
	}

	/**
	 * Entfernt eine Karte aus dem Kartenstapel sofern sie enthalten ist
	 * 
	 * @param card
	 *            die zu entferndende Karte
	 */
	public void removeCard(org.ojim.logic.state.fields.BuyableField card, GUIClient gui) {
		int empty = -1;
		for (int i = 0; i < MAX_CARDS; i++) {
			if (cards[i].getCard().equals(card)) {
				cards[i].removeCard();
				empty = i;
				break;
			}
		}
		if (empty != -1) {
			for (int i = empty; i < MAX_CARDS - 1; i++) {
				cards[i] = cards[i + 1];
			}
			cards[MAX_CARDS - 1] = new Card(gui);
		}
		if (cards[0].getCard() != null) {
			setFieldGroupNull();
		}
		draw();
	}

	/**
	 * Setzt die Sprache
	 * 
	 * @param language
	 *            die neue Sprache
	 */
	public void setLanguage(Localizer language) {
		this.language = language;

	}

	/**
	 * Zeichnet den Kartenstapel
	 */
	public void draw() {

		// this.setLayout(new CardStackLayout());
		this.setLayout(new GridBagLayout());

		for (int i = 0; i < MAX_CARDS; i++) {
			try {
				this.remove(cards[i]);
				cards[i].draw();
				this.add(cards[i]);
			} catch (NullPointerException e) {
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			}
		}
	}

	/**
	 * Gibt die FieldGroup des Kartenstapels zurück
	 * 
	 * @return die FieldGroup
	 */
	public org.ojim.logic.state.fields.FieldGroup getFieldGroup() {
		return fieldGroup;
	}

	/**
	 * Setzt die FieldGroup des Kartenstapels auf Null
	 */
	public void setFieldGroupNull() {
		fieldGroup = null;
	}

	/**
	 * Gibt zurück ob der Kartenstapel leer ist
	 * 
	 * @return wenn er leer ist wahr
	 */
	public boolean isEmpty() {
		return (fieldGroup != null);
	}

	public void switchMortage(Field field) {

		for (int i = 0; i < cards.length; i++) {
			if (cards[i] != null) {
				cards[i].mortage(field);
			}
		}

	}

}
