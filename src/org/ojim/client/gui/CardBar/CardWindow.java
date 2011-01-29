package org.ojim.client.gui.CardBar;

import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import org.ojim.language.Localizer;
import org.ojim.logic.state.fields.Field;

public class CardWindow extends JPanel {

	// Hält CardStacks
	private CardStack[] cardStacks;
	private static final int MAX_CARD_STACKS = 4;
	private int row = 0;

	public CardWindow() {
		super();

		cardStacks = new CardStack[MAX_CARD_STACKS * 3];
		
		for (int i = 0; i < MAX_CARD_STACKS * 3; i++) {
			cardStacks[i] = new CardStack();
		}
		
		
		draw();
	}

	public void setLanguage(Localizer language) {
		for (int i = 0; i < MAX_CARD_STACKS; i++) {
			cardStacks[i].setLanguage(language);
		}
		draw();
	}

	public void addCard(org.ojim.logic.state.fields.BuyableField card) {
		boolean found = false;

		for (int i = 0; i < MAX_CARD_STACKS * (row + 1)
				&& !(cardStacks[i].getFieldGroup()==null); i++) {
			if (cardStacks[i].getFieldGroup().equals(card.getFieldGroup())) {
				cardStacks[i].addCard(card);
				found = true;
				break;
			}
		}
		if (!found) {

			for (int i = 0; i < MAX_CARD_STACKS * 2; i++) {

				if (cardStacks[i].getFieldGroup() == null) {
					cardStacks[i].addCard(card);
					if (i >= MAX_CARD_STACKS*(row+1)) {
						row++;
					}
					break;
				}

			}
		}
		System.out.println("Karte gekauft");
		draw();
	}

	public void removeCard(org.ojim.logic.state.fields.BuyableField card) {
		int empty = -1;
		for (int i = 0; i < MAX_CARD_STACKS * (row + 1)
				&& !cardStacks[i].getFieldGroup().equals(null); i++) {
			if (cardStacks[i].getFieldGroup().equals(card.getFieldGroup())) {
				cardStacks[i].removeCard(card);
				if (cardStacks[i].isEmpty()) {
					empty = i;
				}
				break;
			}
		}
		if (empty != -1) {
			for (int i = empty; i < MAX_CARD_STACKS * (row + 1) -1; i++) {
				cardStacks[i] = cardStacks[i+1];
			}
			cardStacks[MAX_CARD_STACKS*(row+1)-1] = new CardStack();
			if (empty < MAX_CARD_STACKS*(row+1)) {
				row--;
			}
		}
		draw();
	}

	public void draw() {
		this.setLayout(new GridLayout(3,MAX_CARD_STACKS));

		for (int i = 0; i < MAX_CARD_STACKS * (row + 1); i++) {
			this.remove(cardStacks[i]);
			cardStacks[i].draw();
			this.add(cardStacks[i]);
		}
	}
	
	public void switchCardStatus(Field field){
		
	}

}
