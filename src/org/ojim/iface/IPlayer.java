/*  Copyright (C) 2010  Fabian Neundorf
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

package org.ojim.iface;

import edu.kit.iti.pse.iface.IServer;

/**
 * Minimale Schnittstelle um als Gegenpart für das Interface IServer zu dienen.
 * 
 * @see {@link IServer}
 * @author Fabian Neundorf
 */
public interface IPlayer {
	
	/**
	 * Initialisiert den Spieler mit den gegeben Server.
	 * @param server der Server auf den der Spieler gerade spielt.
	 */
	void initializePlayer(IServer server);

	/**
	 * Informiert den Spieler, dass er an der Reihe ist und nun Würfeln darf.
	 */
	void informTurn();

	/**
	 * Informiert den Spieler, dass der Spieler mit der ID das folgende
	 * Würfelergebnis hatte.
	 * 
	 * @param diceValues
	 *            Augenwerte der einzelnen Würfel.
	 * @param playerId
	 *            ID des Spielers der gewürfelt hat.
	 */
	void informDiceValues(int[] diceValues, int playerId);

	/**
	 * Informiert den Spieler, dass die Straße gekauft wurde, auf den der
	 * Spieler mit der angegeben ID steht.
	 * 
	 * @param playerId
	 *            der neue Besitzer der Straße.
	 */
	void informStreetBuy(int playerId);
	
	/**
	 * Informiert den Spieler, dass ein Spieler <i>ein</i> Gebäude gebaut hat.
	 * @param street Die Straße auf der das Gebäude gebaut wurde.
	 * @param playerId Der Spieler der das Gebäude gebaut hat.
	 */
	//FIXME: playerID ist obsolet, da: Straße.Besitzer == playerId? (evtl. auch beim IServer ändern?)
	void informConstruct(int street, int playerId);
	
	/**
	 * Informiert den Spieler, dass ein Spieler <i>ein</i> Gebäude aufgelöst hat.
	 * @param street Die Straße auf der das Gebäude gestanden hat.
	 * @param playerId Der Spieler der das Gebäude aufgelöst hat.
	 */
	//FIXME: playerID ist obsolet, da: Straße.Besitzer == playerId? (evtl. auch beim IServer ändern?)
	void informDestruct(int street, int playerId);
	
	/**
	 * Informiert den Spieler, dass ein Spieler eine Hypothek auf die Straße aufgenommen/zurückgezahlt hat.
	 * @param street Die Straße auf der die Hypothek aufgenommen wurde bzw. dessen Hypothek zurückgezahlt wurde.
	 * @param playerId Der handelnde Spieler.
	 */
	//FIXME: playerID ist obsolet, da: Straße.Besitzer == playerId? (evtl. auch beim IServer ändern?)
	void informMortageToogle(int street, int playerId);
	
}
