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

package org.ojim.logic.actions;

import java.util.List;

import org.ojim.logic.accounting.Bank;
import org.ojim.logic.accounting.IMoneyPartner;
import org.ojim.logic.state.BuyableField;
import org.ojim.logic.state.GameState;
import org.ojim.logic.state.Street;

public class ActionPayForBuildings implements Action {

	private final int costForEachHouse;
	private final int costForEachHotel;
	private final GameState state;
	private final IMoneyPartner payee;

	public ActionPayForBuildings(GameState state, int costForEachHouse,
			int costForEachHotel, IMoneyPartner payee) {
		this.costForEachHouse = costForEachHouse;
		this.costForEachHotel = costForEachHotel;
		this.state = state;
		this.payee = payee;
	}

	@Override
	public void execute() {
		ActionPayForBuildings.execute(this.state, this.costForEachHouse,
				this.costForEachHotel, this.payee);
	}

	public static void execute(GameState state, int costForEachHouse,
			int costForEachHotel, IMoneyPartner payee) {
		int costs = 0;

		// Gehe jede Straße des Spielers durch
		List<BuyableField> field = state.getActivePlayer().getFields();

		// In jeder Straße gucke wie viele Häuse/Hotels es gibt
		for (BuyableField buyableField : field) {
			if (buyableField instanceof Street) {
				Street street = (Street) buyableField;
				costs += street.getNumberOfHouse() * costForEachHouse
						+ street.getNumberOfHotels() * costForEachHotel;
			}
		}

		// Danach dann abrechnen:
		Bank.exchangeMoney(state.getActivePlayer(), payee, costs);
	}

}
