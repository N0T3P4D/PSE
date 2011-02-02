package org.ojim.client.gui.OLabel;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BiggerFontLayout implements LayoutManager {

	private int minWidth = 0, minHeight = 0;
	private int preferredWidth = 50, preferredHeight = 50;
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

		int totalWidth = parent.getWidth();
		int totalHeight = parent.getHeight();

		int maxSize = totalWidth < totalHeight ? totalWidth
				: (int) (totalWidth - (totalWidth - totalHeight) / 2);

		try {
			JLabel label = (JLabel) parent;
			float textLength = label.getText().length();
			int fontSize = (int) ((maxSize) / (textLength * 0.25f + 0.75f));
			label.setFont(new Font(null, nComps, fontSize));
		} catch (ClassCastException e) {
			try {
			JButton label = (JButton) parent;
			int fontSize = (int) (maxSize / 6.0f);
			label.setFont(new Font(null, nComps, fontSize));
			} catch (ClassCastException e2){
				JTextField label = (JTextField) parent;
				int fontSize = (int) (maxSize / 0.75f);
				label.setFont(new Font(null, nComps, fontSize));				
			}
		}

		/*
		 * for (int i = 0; i < nComps; i++) { Component c =
		 * parent.getComponent(i);
		 * 
		 * int totalWidth = parent.getWidth(); int totalHeight =
		 * parent.getHeight();
		 * 
		 * 
		 * 
		 * }
		 */
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