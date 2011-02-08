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

package org.ojim.client.gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.ojim.language.Localizer;
import org.ojim.language.Localizer.TextKey;
import org.ojim.logic.state.Player;

/**
 * Das PlayerInfoField zeigt ob der Spieler aktiv ist, wie er heißt und wie viel
 * Geld er besitzt
 * 
 * 
 */
public class PlayerInfoField extends JPanel {

	private boolean isTurnedOn;
	private Player player;
	private int cash;
	private JLabel nameLabel;
	private JLabel activeLabel;
	private JLabel cashLabel;
	private Localizer language;

	/**
	 * Der Konstruktor initialisiert das Feld
	 * @param player Der Spieler der angezeigt wird
	 * @param cash Sein anfänglicher geldbetrag
	 * @param language Die Sprache in der das Feld angezeigt werden soll
	 */
	public PlayerInfoField(Player player, int cash, Localizer language) {

		this.language = language;

		this.player = player;
		isTurnedOn = false;
		this.cash = cash;
		draw();
	}

	/**
	 * Die Sprache wechseln
	 * @param language die neue Sprache
	 */
	public void setLanguage(Localizer language) {
		this.language = language;
		draw();
	}

	/**
	 * Den Spielerstatus wechseln
	 */
	public void switchStatus() {
		isTurnedOn = !isTurnedOn;
		draw();
	}

	/**
	 * Das Feld zeichnen
	 */
	private void draw() {
		this.setBackground(PlayerColor.getBackGroundColor(player.getColor()));

		this.setBorder(getBorder());

		activeLabel = new JLabel(language.getText(TextKey.OJIM));
		nameLabel = new JLabel(this.player.getName());
		cashLabel = new JLabel(this.cash + " " + language.getText(TextKey.CURRENCY));

		// TODO Schriftfarbe, Schriftgröße

		// Eigener Layouter?
		this.setLayout(new GridLayout(0, 3));

		this.add(activeLabel);
		this.add(nameLabel);
		this.add(cashLabel);
		// System.out.println("Player " + player.getId() + " gezeichnet.");
	}
	
	/**
	 * Ist das Feld mit diesem Spieler besetzt?
	 * @param player das Vergleichsobjekt
	 * @return Wahr wenn die Spieler gleich sind
	 */
	public boolean isPlayer(Player player) {
		// TODO: Player Objekt eine richtige Equalsmethode übergeben?
		if (this.player.getId() == player.getId()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Den Spieler anstellen
	 */
	public void turnOn() {
		isTurnedOn = true;
		draw();

	}

	/**
	 * Den Spieler ausstellen
	 */
	public void turnOff() {
		isTurnedOn = false;
		draw();

	}

	/**
	 * Ist der Spieler an?
	 * @return wahr, wenn ja
	 */
	public boolean isOn() {
		return isTurnedOn;
	}

	/**
	 * Setzt einen neuen Geldbetrag
	 * @param newCashValue der neue Geldbetrag
	 */
	public void changeCash(int newCashValue) {
		cash = newCashValue;
		draw();

	}

	/**
	 * Ist dieses Feld gesetzt mit einem Spieler?
	 * @return wahr wenn es so ist
	 */
	public boolean isNull() {
		if (player == Player.NullPlayer) {
			return true;
		}
		return false;
	}

}
