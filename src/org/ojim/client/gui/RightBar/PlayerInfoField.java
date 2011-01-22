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

package org.ojim.client.gui.RightBar;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.ojim.client.gui.PlayerColor;
import org.ojim.language.Localizer;
import org.ojim.logic.state.Player;

public class PlayerInfoField extends JPanel {

	private boolean isTurnedOn;
	private Player player;
	private int cash;
	private JLabel nameLabel;
	private JLabel activeLabel;
	private JLabel cashLabel;
	private Localizer language;

	public PlayerInfoField(Player player, int cash, Localizer language) {

		this.language = language;
		
		this.player = player;
		isTurnedOn = false;
		this.cash = cash;
		

		activeLabel = new JLabel();
		nameLabel = new JLabel(this.player.getName());
		cashLabel = new JLabel(this.cash + " " + 
				language.getText("currency"));
		
		draw();
	}

	public void setLanguage(Localizer language) {
		this.language = language;
		draw();
	}

	public void switchStatus() {
		isTurnedOn = !isTurnedOn;
		draw();
	}

	private void draw() {
		this.setBackground(PlayerColor.getBackGroundColor(player.getColor()));
		
		this.setBorder(getBorder());

		// TODO Schriftfarbe, Schriftgröße
		
		if(isTurnedOn){
			activeLabel = new JLabel("On");
		} else {
			activeLabel = new JLabel("Off");
		}
		
		this.activeLabel.setForeground(PlayerColor.getFontColor(this.player.getColor()));
		this.nameLabel.setForeground(PlayerColor.getFontColor(this.player.getColor()));
		this.cashLabel.setForeground(PlayerColor.getFontColor(this.player.getColor()));
		
		// Eigener Layouter?
		this.setLayout(new GridLayout(0,3));
		
		this.add(activeLabel);
		this.add(nameLabel);
		this.add(cashLabel);
		//System.out.println("Player " + player.getId() + " gezeichnet.");
	}

	public boolean isPlayer(Player player) {
		// TODO: Player Objekt eine richtige Equalsmethode übergeben?
		if (this.player.getId() == player.getId()) {
			return true;
		} else {
			return false;
		}
	}

	public void turnOn() {
		isTurnedOn = true;
		draw();

	}

	public void turnOff() {
		isTurnedOn = false;
		draw();

	}

	public boolean isOn() {
		return isTurnedOn;
	}

	public void changeCash(int newCashValue) {
		cash = newCashValue;
		draw();

	}

	public boolean isNull() {
		if (player == Player.NullPlayer) {
			return true;
		}
		return false;
	}

}