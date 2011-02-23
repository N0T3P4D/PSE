package org.ojim.client.gui.GameField;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class GameFieldPieceLayout implements LayoutManager {

	private int minWidth = 50, minHeight = 100;
	private int preferredWidth = 100, preferredHeight = 400;
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

			int twentyWidth = (int) (totalWidth * 0.2);
			int twentyHeight = (int) (totalHeight * 0.2);

			int eightyWidth = totalWidth - twentyWidth;
			int eightyHeight = totalHeight - twentyHeight;

			int field = Integer.parseInt(parent.getName());
			try {
			if (totalWidth == totalHeight) {
				c.setBounds(1, 1, totalWidth-2, totalHeight-2);
			}
			else if (parent.getComponent(1) == null){
				
			}
			else if (i == 0) {
				// Felder unten
				if (field < 10) {
					c.setBounds(1, 1, totalWidth-2, twentyHeight-1);
					// Felder links
				} else if (field < 20) {
					c.setBounds(eightyWidth, 1, twentyWidth-1, totalHeight-2);
					// Felder oben
				} else if (field < 30) {
					c.setBounds(1, eightyHeight, totalWidth-2, twentyHeight-1);
					// Felder rechts
				} else {
					c.setBounds(1, 1, twentyWidth-1, totalHeight-2);
				}
			} else {
				// Felder unten
				if (field < 10) {
					c.setBounds(1, twentyHeight, totalWidth-2, eightyHeight-1);
					// Felder links
				} else if (field < 20) {
					c.setBounds(1, 1, eightyWidth-1, totalHeight-2);
					// Felder oben
				} else if (field < 30) {
					c.setBounds(1, 1, totalWidth-2, eightyHeight-1);
					// Felder rechts
				} else {
					c.setBounds(twentyWidth-1, 1, eightyWidth-1, totalHeight-2);
				}
			}
			} catch (ArrayIndexOutOfBoundsException e){
				c.setBounds(1, 1, totalWidth-2, totalHeight-2);
				
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
