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

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.ojim.client.gui.StreetColor;
import org.ojim.logic.state.fields.BuyableField;

public class Card extends JPanel {

	private boolean isTurnedAround = false;
	private org.ojim.logic.state.fields.BuyableField card;

	public Card() {
		super();
		draw();
	}

	public void turnAround() {
		isTurnedAround = !isTurnedAround;
	}

	public void setCard(org.ojim.logic.state.fields.BuyableField card) {
		this.card = card;
	}

	public org.ojim.logic.state.fields.BuyableField getCard() {
		return card;

	}

	public void removeCard(BuyableField card) {
		this.card = null;

	}

	public void draw() {
		if (card != null) {
			JLabel colorLabel = new JLabel();

			colorLabel.setBackground(StreetColor.getBackGroundColor(card
					.getFieldGroup().getColor()));

			JLabel textLabel = new JLabel();

			textLabel.setText("Card");

			this.add(colorLabel);
			this.add(textLabel);
		}

	}
}
