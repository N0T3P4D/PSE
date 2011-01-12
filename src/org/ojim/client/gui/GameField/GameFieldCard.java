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

package org.ojim.client.gui.GameField;

import java.awt.Image;

@SuppressWarnings("serial")
public class GameFieldCard extends GameFieldPiece {

	private boolean turnedAround = false;

	// FIXME: cardID statt ID Objekt!
	public GameFieldCard(int cardId, String name, int position, Image image,
			int price) {
		super(price, name, price, image);
		// TODO Auto-generated constructor stub
	}

	public void turnAround() {
		this.turnedAround = !this.turnedAround;
	}

	// FIXME: PLAYER ALS OBJEKT!
	public void switchOwner(int player) {

	}

}
