/*  Copyright (C) 2010  Fabian Neundorf, Philip Caroli, Maximilian Madlung, 
 * 						Usman Ghani Ahmed, Jeremias Mechler
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

import java.util.NoSuchElementException;


/**
 * Implements one stack of cards. Uses a double linked list.
 * 
 * @author Fabian Neundorf
 */
public class CardStack {

	/**
	 * An element that stores one card.
	 * 
	 * @author Fabian Neundorf
	 */
	private class Element {
		public Element next;
		public Element previous;

		public final Card card;

		public Element(Card card) {
			this.card = card;
		}
	}

	private Element pointer;

	public CardStack() {
		this.pointer = null;
	}

	/**
	 * Adds a card before the active card. If the stack is empty the
	 * {@link #getPointedCard()} returns the new card.
	 * 
	 * @param card
	 *            New card.
	 */
	public void add(Card card) {
		Element newElement = new Element(card);
		// There are already elements in the stack.
		if (this.pointer != null) {
			newElement.previous = this.pointer.previous;
			this.pointer.previous = newElement;
			newElement.next = newElement.previous.next;
			newElement.previous.next = newElement;
		} else {
			newElement.next = newElement;
			newElement.previous = newElement;
			this.pointer = newElement;
		}
	}

	/**
	 * Removes the active card. The method {@link #getPointedCard()} returns the
	 * next element afterwards.
	 */
	public void remove() {
		// If there is only one element left remove it
		if (this.pointer.next == this.pointer) {
			// Cleans up
			this.pointer.next = null;
			this.pointer.previous = null;
			// Forget last element
			this.pointer = null;
		} else {
			// Extract the pointer element
			this.pointer.previous.next = this.pointer.next;
			this.pointer.next.previous = this.pointer.previous;
			// Cleans up
			this.pointer.next = null;
			this.pointer.previous = null;
			// Select next element
			this.pointer = this.pointer.next;
		}
	}

	/**
	 * Select the next card of the active card.
	 * @return the new active card.
	 * @throws NoSuchElementException If there aren't any elements.
	 */
	public Card step() {
		if (this.pointer == null) {
			throw new NoSuchElementException("Empty card stack!");
		}
		this.pointer = this.pointer.next;
		return this.pointer.card;
	}

	/**
	 * Returns the active card.
	 * @return the active card.
	 * @throws NoSuchElementException If there aren't any elements.
	 */
	public Card getPointedCard() {
		if (this.pointer == null) {
			throw new NoSuchElementException("Empty card stack!");
		}
		return this.pointer.card;
	}
	
	public boolean isEmpty() {
		return this.pointer == null;
	}
}
