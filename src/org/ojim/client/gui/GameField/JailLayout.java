package org.ojim.client.gui.GameField;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class JailLayout implements LayoutManager {

	private int minWidth = 50, minHeight = 50;
	private int preferredWidth = 100, preferredHeight = 100;
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

			int eightyWidth = (int) (totalWidth * 0.7);
			int eightyHeight = (int) (totalHeight * 0.7);

			if (c.isVisible()) {

				if (i == 0) {
					c.setBounds(totalWidth-eightyWidth, 0, eightyWidth, eightyHeight);
				} else {
					c.setBounds(0, eightyHeight, totalWidth, totalHeight-eightyHeight);
					
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
