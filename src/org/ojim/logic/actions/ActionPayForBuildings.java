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

package org.ojim.logic.actions;

import java.util.List;

import org.ojim.logic.ServerLogic;
import org.ojim.logic.accounting.IMoneyPartner;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.fields.BuyableField;
import org.ojim.logic.state.fields.Street;

public class ActionPayForBuildings implements Action {

	private final int costForEachHouse;
	private final int costForEachHotel;
	private final ServerLogic logic;
	private final IMoneyPartner payee;

	public ActionPayForBuildings(ServerLogic logic, int costForEachHouse,
			int costForEachHotel, IMoneyPartner payee) {
		this.costForEachHouse = costForEachHouse;
		this.costForEachHotel = costForEachHotel;
		this.logic = logic;
		this.payee = payee;
	}

	@Override
	public void execute() {
		ActionPayForBuildings.execute(this.logic, this.costForEachHouse,
				this.costForEachHotel, this.payee);
	}

	public static void execute(ServerLogic logic, int costForEachHouse,
			int costForEachHotel, IMoneyPartner payee) {
		Player active = logic.getGameState().getActivePlayer();
		int costs = 0;
		
		// Gehe jede Straße des Spielers durch
		List<BuyableField> field = active.getFields();

		// In jeder Straße gucke wie viele Häuse/Hotels es gibt
		for (BuyableField buyableField : field) {
			if (buyableField instanceof Street) {
				Street street = (Street) buyableField;
				costs += street.getNumberOfHouse() * costForEachHouse
						+ street.getNumberOfHotels() * costForEachHotel;
			}
		}

		// Danach dann abrechnen:	
		logic.exchangeMoney(active, payee, costs);
	}

}
