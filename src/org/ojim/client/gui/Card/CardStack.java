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

package org.ojim.client.gui.Card;

import javax.swing.JLabel;
import javax.swing.JPanel;

// xZise: Als Info: Es gibt bereits eine Klasse CardStack? Könnte die für dich interessant sein?
// Card Stack ist in diesem Fall ein Kartenstapel unten im Card Window

public class CardStack extends JPanel {

	// Hält Cards

	public CardStack() {
		super();
		this.add(new JPanel().add(new JLabel("CStack")));
		// TODO Auto-generated constructor stub
	}

	// FIXME: INT
	// TODO: (xZise) Klassen statt untypisierte Zahlen!
	public void addCard(int cardId) {

	}

	// TODO: (xZise) Klassen statt untypisierte Zahlen!
	public void removeCard(int cardId) {

	}

}
