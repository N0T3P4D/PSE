package org.ojim.client.gui.Card;

import java.awt.GridLayout;

import javax.swing.JPanel;


public class CardWindow extends JPanel{

	// Hält CardStacks
	private CardStack[] cardStacks;
	private static final int MAX_CARD_STACKS = 4;
	
	public CardWindow() {
		super();
		
		cardStacks = new CardStack[4];
		
		this.setLayout(new GridLayout(0,MAX_CARD_STACKS));
		
		for(int i = 0; i < MAX_CARD_STACKS; i++){
			cardStacks[i] = new CardStack();
			this.add(cardStacks[i]);
		}
	}

	// FIXME: INT
	public void addCard(int cardStack, int cardId){
		
	}
	
	public void removeCard(int cardId){
		
	}
	
}
