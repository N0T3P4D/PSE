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

public class StreetColor {
	private static final Color[][] colors = {
			// Straße 1
			{
					Color.getHSBColor(10.0f / 100.0f, 100.0f / 100.0f,
							62.0f / 100.0f), Color.white },
			// Straße 2
			{ Color.cyan, Color.black },
			// Straße 3
			{ Color.magenta, Color.white },
			// Straße 4
			{ Color.orange, Color.white },
			// Straße 5
			{ Color.red, Color.white },
			// Straße 6
			{ Color.yellow, Color.white },
			// Straße 7
			{ Color.green, Color.white },
			// Straße 8
			{ Color.blue, Color.white } ,
			// Mittelfeld
			{ Color.black, Color.white } };

	public static Color getBackGroundColor(int color) {
		if(color < 0) {
			return colors[8][0];
		}
		return colors[color][0];
	}

	public static Color getFontColor(int color) {
		return colors[color][1];
	}
}
