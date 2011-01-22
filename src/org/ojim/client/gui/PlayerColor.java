package org.ojim.client.gui;

import java.awt.Color;

public class PlayerColor {

	private static final Color[][] colors = {
			// Spieler 1
			{ Color.blue, Color.white },
			// Spieler 2
			{ Color.yellow, Color.black },
			// Spieler 3
			{ Color.red, Color.white },
			// Spieler 4
			{ Color.green, Color.white },
			// Spieler 5
			{ Color.orange, Color.white },
			// Spieler 6
			{ Color.cyan, Color.white },
			// Spieler 7
			{ Color.pink, Color.white },
			// Spieler 8
			{ Color.black, Color.white } };

	public static Color getBackGroundColor(int color) {
		return colors[color][0];
	}

	public static Color getFontColor(int color) {
		return colors[color][1];
	}

}
