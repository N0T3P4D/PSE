package org.ojim.client.gui.GameField;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameField extends JPanel {

	public GameField() {
		super();
		// 1 Eckfeld = 2, sonst jeweils 1

		for (int i = 0; i < 13 * 13; i++) {
			if (i % 13 == 0 || i % 13 == 1 || i % 13 == 11 || i % 13 == 12
					|| i < 13 * 2 || i > 13 * 11) {
				this.add(new JLabel("X"));
			} else {
				this.add(new JLabel("."));
			}
		}

		this.setLayout(new GridLayout(13, 13));
		// System.out.println("X: " + this.getWidth() + ", Y: " +
		// this.getHeight());

	}

	// Hält GameFieldPieceCollection
	// Hält Referenz auf GameFieldPiece
	InteractionPopup interactionPopup;
}
