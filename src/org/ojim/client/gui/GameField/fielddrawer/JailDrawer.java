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

package org.ojim.client.gui.GameField.fielddrawer;

import java.awt.Graphics;

import org.ojim.logic.state.fields.Field;

public class JailDrawer extends FieldDrawer {

	/*
	 * At the moment doesn't work:
	 * http://www.java-forum.org/allgemeine-java-themen
	 * /112314-dynamisch-konstruktor-aufrufen.html
	 */
	// public JailDrawer(Jail field) {
	public JailDrawer(Field field) {
		super(field);
	}

	@Override
	public void drawTo(Graphics g) {
		// TODO Auto-generated method stub
		System.out.println("jail");
	}
}
