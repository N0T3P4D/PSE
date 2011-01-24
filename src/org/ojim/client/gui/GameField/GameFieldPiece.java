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

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.ojim.client.gui.StreetColor;
import org.ojim.client.gui.GameField.fielddrawer.FieldDrawer;
import org.ojim.logic.state.fields.Field;

public class GameFieldPiece extends JPanel {
	
	private FieldDrawer drawer;
	private Field field;
	private JPanel colorTop;

	public GameFieldPiece(Field field, String name, int position, Image image) {
		
	}
	
	public GameFieldPiece(Field field) {
		//this.drawer = FieldDrawer.getDrawer(field); 
		this.setLayout(new GameFieldPieceLayout());
		this.field = field;
		colorTop = new JPanel();
		if(!(this.field.getColorGroup()<0)){
			colorTop.setBackground(StreetColor.getBackGroundColor(this.field.getColorGroup()));
			this.add(colorTop);
		}
		this.add(new JLabel(field.getName()));
	}
	
	public void setField(Field field){
		this.field = field;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// Draw Field piece here
	}
}
