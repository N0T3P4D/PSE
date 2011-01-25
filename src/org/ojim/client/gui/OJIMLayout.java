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

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class OJIMLayout implements LayoutManager {

	private int minWidth = 400, minHeight = 400;
	private int preferredWidth = 1600, preferredHeight = 900;
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

			int squareHeight = (int) (parent.getHeight() * 0.8);
			int restWidth = (int) (parent.getWidth() - squareHeight);
			int restHeight = (int) (parent.getHeight() - squareHeight);

			if (restWidth < parent.getHeight() * 0.2) {
				System.out.println("Seitenverhältnis korrigiert.");
				parent.getFocusCycleRootAncestor().setSize(parent.getHeight(),
						parent.getHeight());
				parent.setSize(parent.getHeight(), parent.getHeight());
			} else if (restWidth > parent.getHeight() * 1.5) {
				System.out.println("Seitenverhältnis korrigiert.");
				parent.getFocusCycleRootAncestor().setSize(
						(int) (1.5 * parent.getHeight()), parent.getHeight());
				parent.setSize(parent.getHeight(), parent.getHeight());
			} else {
				if (c.isVisible()) {
					// Dimension d = c.getPreferredSize();

					switch (i) {
					case 0:

						c.setBounds(0, 0, squareHeight, squareHeight);
						break;
					case 1:

						c.setBounds(squareHeight, 0, restWidth, squareHeight);
						break;
					case 2:

						c.setBounds(0, squareHeight, squareHeight, restHeight);
						break;
					case 3:

						c.setBounds(squareHeight, squareHeight, restWidth,
								restHeight);
						break;
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
