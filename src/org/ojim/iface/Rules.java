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

/**
 * Minimales Regelset.
 * @author Fabian Neundorf
 */
public final class Rules {

	/** Das verfügbare Startgeld. */
	public final int startMoney;
	/** Wie viel Geld man beim ziehen über Los bekommt. */
	public final int goMoney;
	/** Wenn wahr, bekommt man doppelt so viel Geld, wenn man auf Los zum stehen kommt. */
	public final boolean doubleMoneyOnGo;
	/** Wenn wahr, erlaubt das Handeln mit Geld. */
	public final boolean tradesWithMoney;
	/** Wenn wahr, sind Auktionen erlaubt. */
	public final boolean auctionActive;
	/** Wenn wahr, bekommt man auf den Feld Frei-Parken das Geld aus der Mitte. */
	public final boolean moneyOnFreeParking;

	/**
	 * Erstellt ein Regelsatz nach den Parametern.
	 * @param startMoney 
	 *            Das verfügbare Startgeld.
	 * @param goMoney
	 *            Die Menge des Geldes die man über Los bekommt.
	 * @param doubleMoneyOnGo
	 *            Wenn wahr, bekommt man doppelt so viel Geld, wenn man auf Los zum stehen kommt.
	 * @param tradesWithMoney
	 *            Wenn wahr, erlaubt das Handeln mit Geld.
	 * @param auctionActive
	 *            Wenn wahr, sind Auktionen erlaubt.
	 * @param moneyOnFreeParking
	 *            Wenn wahr, bekommt man auf den Feld Frei-Parken das Geld aus der Mitte.
	 */
	public Rules(int startMoney, int goMoney, boolean doubleMoneyOnGo, boolean tradesWithMoney, boolean auctionActive, boolean moneyOnFreeParking) {
		this.startMoney = startMoney;
		this.goMoney = goMoney;
		this.doubleMoneyOnGo = doubleMoneyOnGo;
		this.tradesWithMoney = tradesWithMoney;
		this.auctionActive = auctionActive;
		this.moneyOnFreeParking = moneyOnFreeParking;
	}

	/**
	 * Standardregeln nach den Regelwerk eines offiziellen Spiels.
	 */
	public Rules() {
		this(30000, 4000, false, true, true, false);
	}
}
