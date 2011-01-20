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

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameField extends JPanel {

	public GameField() {
		super();
		// 1 Eckfeld = 2, sonst jeweils 1

		for (int i = 0; i < 13 * 13; i++) {
			if (i % 13 == 0 || i % 13 == 1 || i % 13 == 11 || i % 13 == 12
					|| i < 13 * 2 || i > 13 * 11) {
				this.add(new JLabel("X"));
			} else {
				this.add(new JLabel("."));
			}
		}

		this.setLayout(new GridLayout(13, 13));
		// System.out.println("X: " + this.getWidth() + ", Y: " +
		// this.getHeight());

	}

	// Hält GameFieldPieceCollection
	// Hält Referenz auf GameFieldPiece
	InteractionPopup interactionPopup;
}
