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
			int cardHeight = (int) (maxSize * 2 / 13);

			// Mittelfeldgröße
			int middleFieldSize = maxSize - 2 * cardHeight;

			if (c.isVisible()) {

				// Feld Position
				int field = Integer.parseInt(c.getName());
				int fieldNumber = field % 10;

				// Mittelfeld = -1

				if (field == -1) {
					c.setBounds(cardHeight, cardHeight, middleFieldSize,
							middleFieldSize);
					// Randfelder
				} else if (field % 10 == 0) {
					switch (field) {
					case 0:
						c.setBounds(middleFieldSize + cardHeight,
								middleFieldSize + cardHeight, cardHeight,
								cardHeight);
						break;
					case 1:
						c.setBounds(0, middleFieldSize + cardHeight,
								cardHeight, cardHeight);
						break;
					case 2:
						c.setBounds(0, 0, cardHeight, cardHeight);
						break;
					case 3:
						c.setBounds(middleFieldSize + cardHeight, 0,
								cardHeight, cardHeight);
						break;
					}
					// Felder unten
				} else if (field < 10) {
					c.setBounds(middleFieldSize + cardHeight
							- (fieldNumber * cardWidth), middleFieldSize
							+ cardHeight, cardWidth, cardHeight);
					// Felder links
				} else if (field < 20) {
					c.setBounds(middleFieldSize + cardHeight
							- (fieldNumber * cardWidth), middleFieldSize
							+ cardHeight, cardWidth, cardHeight);
					// Felder oben
				} else if (field < 30) {
					c.setBounds(middleFieldSize + cardHeight
							- (fieldNumber * cardWidth), 0, cardWidth, cardHeight);
					// Felder rechts
				} else {
					c.setBounds(middleFieldSize + cardHeight
							- (fieldNumber * cardWidth), middleFieldSize
							+ cardHeight, cardWidth, cardHeight);
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
