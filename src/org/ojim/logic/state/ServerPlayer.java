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

package org.ojim.logic.state;

import java.util.ArrayList;
import java.util.List;

import org.ojim.iface.IClient;

public class ServerPlayer extends Player {
	
	private final IClient client;
	private String gameStatusMessage;
	
	private List<Card> cards;
	
	public ServerPlayer(String name, int position, int balance, int id, int color, IClient client) {
		super(name, position, balance, id, color);
		this.cards = new ArrayList<Card>(2);
		this.client = client;
		this.gameStatusMessage = "";
	}
	
	public void sendMessage(String text, int sender, boolean privateMessage) {
		this.gameStatusMessage = text;
		this.client.informMessage(text, sender, privateMessage);
	}
	
	public IClient getClient() {
		return this.client;
	}

	public String getGameStatusMessage() {
		return this.gameStatusMessage;
	}
	
	public List<Card> getCards() {
		return this.cards;
	}

	/* CARD STACK */

	/**
	 * Inserts a card in the players card stack.
	 * 
	 * @param card
	 *            New card in the stack.
	 */
	public void addCard(Card card) {
		this.cards.add(card);
	}

	/**
	 * Removes the card from the players card stack.
	 * 
	 * @param card
	 *            The card which will be removed.
	 */
	public void removeCard(Card card) {
		this.cards.remove(card);
	}

}
