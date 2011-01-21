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

import org.ojim.logic.actions.ActionGetOutOfJailCard;
import org.ojim.logic.actions.ActionMoveForward;
import org.ojim.logic.state.fields.Jail;

/**
 * Extends the GameState for the purposes of the server.
 * 
 * @author Fabian Neundorf
 */
public class ServerGameState extends GameState {
	
	private CardStack eventCards;
	private CardStack communityCards;
	private Jail defaultJail;
	
	public ServerGameState() {
		super();
		this.eventCards = new CardStack();
		// Fill here the cards
//		this.eventCards.add(new Card("foobar", this, false, new ActionMoveForward(this, 5)));
//		this.eventCards.add(new Card("anti jail", this, true, new ActionGetOutOfJailCard(this)));
		
		this.communityCards = new CardStack();
		// Fill here the cards
	}
	
	public Jail getDefaultJail() {
		if(defaultJail == null) {
			for(int i = 0; i < this.FIELDS_AMOUNT; i++) {
				if(this.getFieldAt(i) instanceof Jail) {
					this.defaultJail = (Jail) this.getFieldAt(i);
				}
			}
		}
		return this.defaultJail;
	}
	
	public void setDefaultJail(Jail jail) {
		this.defaultJail = jail;
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
	
	public ServerPlayer getPlayerByID(int playerId) {
		return (ServerPlayer) super.getPlayerByID(playerId);
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
