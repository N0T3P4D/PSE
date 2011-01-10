package org.ojim.client.gui.GameField;

import java.awt.Image;

@SuppressWarnings("serial")
public class GameFieldCard implements GameFieldPiece {

	private boolean turnedAround = false;

	// FIXME: cardID statt ID Objekt!
	public GameFieldCard(int cardId, String name, int position, Image image,
			int price) {
		// TODO Auto-generated constructor stub
	}

	public void turnAround() {
		this.turnedAround = !this.turnedAround;
	}

	// FIXME: PLAYER ALS OBJEKT!
	public void switchOwner(int player) {

	}

}
