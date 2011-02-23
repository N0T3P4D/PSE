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

import java.util.LinkedList;
import java.util.Queue;

import org.ojim.logic.state.fields.Jail;

/**
 * Extends the GameState for the purposes of the server.
 * 
 * @author Fabian Neundorf
 */
@SuppressWarnings("serial")
public class ServerGameState extends GameState {
	
	private CardStack eventCards;
	private CardStack communityCards;
	private Jail defaultJail;
	private Queue<Card> waitingCards;
	
	public ServerGameState() {
		super();
		this.eventCards = new CardStack();
		this.communityCards = new CardStack();

		waitingCards = new LinkedList<Card>();
	}
	
	public Jail getDefaultJail() {
		if(defaultJail == null) {
			for(int i = 0; i < this.getNumberOfFields(); i++) {
				if(this.getFieldAt(i) instanceof Jail) {
					this.defaultJail = (Jail) this.getFieldAt(i);
					break;
				}
			}
		}
		return this.defaultJail;
	}
	
	public void setDefaultJail(Jail jail) {
		this.defaultJail = jail;
	}
	
	public void addWaitingCard(Card card) {
		this.waitingCards.add(card);
	}
	
	public Card getFirstWaitingCard() {
		return this.waitingCards.peek();
	}
	
	public void RemoveWaitingCard(Card card) {
		this.waitingCards.remove(card);
	}
	
	public Queue<Card> getWaitingCards() {
		return this.waitingCards;
	}
	
	/*
	 * CARD STACK
	 */
	
	public CardStack getEventCards() {
		return eventCards;
	}



	public CardStack getCommunityCards() {
		return communityCards;
	}
	
	public ServerPlayer getPlayerById(int playerId) {
		return (ServerPlayer) super.getPlayerById(playerId);
	}

//	public void 
//
//	public Card getFirstEventCard() {
//		return this.eventCards.getPointedCard();
//	}
//	
//	public Card getFirstCommunityCard() {
//		return this.communityCards.getPointedCard();
//	}
}
