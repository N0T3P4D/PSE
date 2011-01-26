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

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class GameFieldLayout implements LayoutManager {

	private int minWidth = 200, minHeight = 200;
	private int preferredWidth = 400, preferredHeight = 400;
	private boolean sizeUnknown = true;

	@Override
	public void addLayoutComponent(String arg0, Component arg1) {
		// TODO Auto-generated method stub

	}

	private void setSizes(Container parent) {
		int nComps = parent.getComponentCount();
		Dimension d = null;

		// Reset preferred/minimum width and height.
		for (int i = 0; i < nComps; i++) {
			Component c = parent.getComponent(i);
			if (c.isVisible()) {
				d = c.getPreferredSize();

				if (i > 0) {
					preferredWidth += d.width / 2;
				} else {
					preferredWidth = d.width;
				}
				preferredHeight += d.height;

				minWidth = Math.max(c.getMinimumSize().width, minWidth);
				minHeight = preferredHeight;
			}
		}
	}

	@Override
	public void layoutContainer(Container parent) {
		int nComps = parent.getComponentCount();
		// Go through the components' sizes, if neither
		// preferredLayoutSize nor minimumLayoutSize has
		// been called.
		if (sizeUnknown) {
			setSizes(parent);
			// System.out.println("Handlungsbedarf");
		}

		for (int i = 0; i < nComps; i++) {
			Component c = parent.getComponent(i);

			int totalWidth = parent.getWidth();
			int totalHeight = parent.getHeight();

			// Wir brauchen das größtmögliche Quadrat
			int maxSize = totalWidth < totalHeight ? totalWidth : totalHeight;

			// Kartengröße
			int cardWidth = (int) (maxSize * 1 / 13);
			int cardHeight = 2 * cardWidth;
			
			int halfWidth = (int) cardWidth / 2;
			int halfHeight = (int) cardHeight / 2;

			// Mittelfeldgröße
			int middleFieldSize = 9 * cardWidth;
			/*
			 * System.out.println("totalWidth: " + totalWidth);
			 * System.out.println("totalHeight: " + totalHeight);
			 * System.out.println("maxSize: " + maxSize);
			 * System.out.println("cardWidth: " + cardWidth);
			 * System.out.println("cardHeight: " + cardHeight);
			 */
			// Rundungsfehler vermeiden, alle Zahlen werden aus CardWitdh
			// aufgebaut
			maxSize = middleFieldSize + 2 * cardHeight;

			if (c.isVisible()) {
				// System.out.println("Container "+c.getName());
				// Feld Position
				int field = Integer.parseInt(c.getName());
				
				boolean player = false;
				
				int fieldNumber = field % 10;

				// Mittelfeld = -1
				if(field > 1000){
					field /= 1000;
					player = true;
				}
				if (field == -1) {
					c.setBounds(cardHeight, cardHeight, middleFieldSize,
							middleFieldSize);
					// System.out.println("Mittelfeld ist da");
					// Randfelder
				} else if (field % 10 == 0) {
					switch (field) {
					case 0:
						if(player){
							c.setBounds(middleFieldSize + cardHeight+halfHeight,
									middleFieldSize + cardHeight+halfHeight, 10,
									10);
							
						} else {
						c.setBounds(middleFieldSize + cardHeight,
								middleFieldSize + cardHeight, cardHeight,
								cardHeight);
						}
						break;
					case 10:
						if(player){
							c.setBounds(0+halfHeight, middleFieldSize + cardHeight+halfHeight,
									10, 10);
							
						} else {
						c.setBounds(0, middleFieldSize + cardHeight,
								cardHeight, cardHeight);
						}
						break;
					case 20:
						if(player){
							c.setBounds(0+halfHeight, 0+halfHeight, 10, 10);
							
						} else {
						c.setBounds(0, 0, cardHeight, cardHeight);
						}
						break;
					case 30:
						if(player){
							c.setBounds(middleFieldSize + cardHeight+halfHeight, 0+halfHeight,
									10, 10);
							
						} else {
						c.setBounds(middleFieldSize + cardHeight, 0,
								cardHeight, cardHeight);
						}
						break;
					}
					// Felder unten
				} else if (field < 10) {
					if(player){
						c.setBounds(middleFieldSize + cardHeight
								- ((fieldNumber % 10) * cardWidth)+halfWidth, middleFieldSize
								+ cardHeight+halfHeight, 10, 10);
						
					} else {
					c.setBounds(middleFieldSize + cardHeight
							- ((fieldNumber % 10) * cardWidth), middleFieldSize
							+ cardHeight, cardWidth, cardHeight);
					}
					// Felder links
				} else if (field < 20) {
					if(player){
						c.setBounds(0+halfWidth, middleFieldSize + cardHeight
								- ((fieldNumber % 10) * cardWidth)+halfHeight, 10,
								10);
						
					} else {
					c.setBounds(0, middleFieldSize + cardHeight
							- ((fieldNumber % 10) * cardWidth), cardHeight,
							cardWidth);
					}
					// Felder oben
				} else if (field < 30) {
					if(player){
						c.setBounds(((fieldNumber % 10) * cardWidth) + cardWidth+halfWidth,
								0+halfHeight, 10, 10);
						
					} else {
					c.setBounds(((fieldNumber % 10) * cardWidth) + cardWidth,
							0, cardWidth, cardHeight);
					}
					// Felder rechts
				} else {
					if(player){
						c.setBounds(middleFieldSize + cardHeight+halfWidth, cardHeight
								+ ((fieldNumber % 10) * cardWidth) - cardWidth+halfHeight,
								10, 10);
						
					} else {
					c.setBounds(middleFieldSize + cardHeight, cardHeight
							+ ((fieldNumber % 10) * cardWidth) - cardWidth,
							cardHeight, cardWidth);
					}
				}
			}
		}
	}

	@Override
	public Dimension minimumLayoutSize(Container arg0) {
		return new Dimension(minWidth, minHeight);
	}

	@Override
	public Dimension preferredLayoutSize(Container arg0) {
		return new Dimension(preferredWidth, preferredHeight);
	}

	@Override
	public void removeLayoutComponent(Component arg0) {
		// TODO Auto-generated method stub

	}

}
