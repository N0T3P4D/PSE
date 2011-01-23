package org.ojim.client.gui;

import java.awt.Color;

public class StreetColor {
	private static final Color[][] colors = {
	// Straße 1
			{ Color.blue, Color.white },
			// Straße 2
			{ Color.yellow, Color.black },
			// Straße 3
			{ Color.red, Color.white },
			// Straße 4
			{ Color.green, Color.white },
			// Straße 5
			{ Color.orange, Color.white },
			// Straße 6
			{ Color.cyan, Color.white },
			// Straße 7
			{ Color.pink, Color.white },
			// Straße 8
			{ Color.black, Color.white } };

	public static Color getBackGroundColor(int color) {
		return colors[color][0];
	}

	public static Color getFontColor(int color) {
		return colors[color][1];
	}
}
