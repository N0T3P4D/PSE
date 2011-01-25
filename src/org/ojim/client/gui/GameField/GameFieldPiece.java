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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.ojim.client.gui.PlayerColor;
import org.ojim.client.gui.StreetColor;
import org.ojim.client.gui.GameField.fielddrawer.FieldDrawer;
import org.ojim.client.gui.OLabel.FontLayout;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.fields.BuyableField;
import org.ojim.logic.state.fields.Field;
import org.ojim.logic.state.fields.Street;

public class GameFieldPiece extends JPanel {

	private FieldDrawer drawer;
	private Field field;
	private JPanel colorTop;
	private JLabel group;
	private JLabel name;
	private JLabel price;
	private JPanel textPanel;

	public GameFieldPiece(Field field, String name, int position, Image image) {
	}

	public GameFieldPiece(Field field) {
		if (this.getComponentCount() > 0) {
			remove(textPanel);
			remove(colorTop);
		}

		textPanel = new JPanel();
		// this.drawer = FieldDrawer.getDrawer(field);
		this.field = field;
		colorTop = new JPanel();
		if (!(this.field.getColorGroup() < 0)) {
			colorTop.setBackground(StreetColor.getBackGroundColor(this.field
					.getColorGroup()));
			this.add(colorTop);
		}

		try {
			group = new JLabel("<html>"
					+ ((Street) field).getFieldGroup().getName());

			group.setLayout(new FontLayout());
			group.setHorizontalTextPosition(JLabel.CENTER);
			textPanel.add(group);
		} catch (ClassCastException e) {

		}

		name = new JLabel("<html>" + field.getName());
		name.setHorizontalTextPosition(JLabel.CENTER);
		name.setLayout(new FontLayout());
		textPanel.add(name);
		try {
			if(((BuyableField) field).getOwner()!=null){
				
				price = new JLabel("<html>" + ((BuyableField) field).getOwner().getName());
				//System.out.println("SOLD: "+field.getName());
				//System.out.println(((BuyableField) field).getOwner().getId()+" - ID - "+field.getName());
				textPanel.setBackground(PlayerColor
						.getBackGroundColor(((BuyableField) field).getOwner()
								.getColor()));
				group.setBackground(PlayerColor
						.getBackGroundColor(((BuyableField) field).getOwner()
								.getColor()));
				price.setBackground(PlayerColor
						.getBackGroundColor(((BuyableField) field).getOwner()
								.getColor()));
				name.setBackground(PlayerColor
						.getBackGroundColor(((BuyableField) field).getOwner()
								.getColor()));

				group.setForeground(PlayerColor
						.getFontColor(((BuyableField) field).getOwner()
								.getColor()));
				price.setForeground(PlayerColor
						.getFontColor(((BuyableField) field).getOwner()
								.getColor()));
				name.setForeground(PlayerColor
						.getFontColor(((BuyableField) field).getOwner()
								.getColor()));
				
			} else {
				//System.out.println("NOT SOLD: "+field.getName());
				price = new JLabel("<html>" + ((BuyableField) field).getPrice());
			}
			price.setHorizontalTextPosition(JLabel.CENTER);
			price.setVerticalTextPosition(JLabel.BOTTOM);
			price.setLayout(new FontLayout());
			textPanel.add(price);
		} catch (ClassCastException e) {

		}
		// text = new JLabel("<html>" + "test");
		textPanel.setLayout(new GridLayout(0, 1));

		this.add(textPanel);
		this.setLayout(new GameFieldPieceLayout());
		//this.setBackground(Color.white);
	}

	public void setField(Field field) {
		this.field = field;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// Draw Field piece here
	}
}
