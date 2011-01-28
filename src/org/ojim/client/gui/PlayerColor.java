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

import java.awt.Color;

public class PlayerColor {

	private static final Color[][] colors = {
			// Spieler 1
			{ Color.blue, Color.white },
			// Spieler 2
			{ Color.yellow, Color.black },
			// Spieler 3
			{ Color.red, Color.white },
			// Spieler 4
			{ Color.green, Color.black },
			// Spieler 5
			{ Color.orange, Color.white },
			// Spieler 6
			{ Color.cyan, Color.white },
			// Spieler 7
			{ Color.pink, Color.white },
			// Spieler 8
			{ Color.black, Color.white } };

	public static Color getBackGroundColor(int color) {
		return color == -1 ? Color.WHITE : colors[color][0];
	}

	public static Color getFontColor(int color) {
		return color == -1 ? Color.BLACK : colors[color][1];
	}

}
