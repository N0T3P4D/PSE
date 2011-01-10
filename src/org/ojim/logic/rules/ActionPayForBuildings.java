package org.ojim.logic.rules;

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
	
	public ActionPayForBuildings(GameState state, int costForEachHouse, int costForEachHotel, IMoneyPartner payee) {
		this.costForEachHouse = costForEachHouse;
		this.costForEachHotel = costForEachHotel;
		this.state = state;
		this.payee = payee;
	}
	
	@Override
	public void execute() {
		ActionPayForBuildings.execute(this.state, this.costForEachHouse, this.costForEachHotel, this.payee);
	}
	
	public static void execute(GameState state, int costForEachHouse, int costForEachHotel, IMoneyPartner payee) {
		int costs = 0;
		
		// Gehe jede Straße des Spielers durch
		List<BuyableField> field = state.getActivePlayer().getFields();
		
		// In jeder Straße gucke wie viele Häuse/Hotels es gibt
		for (BuyableField buyableField : field) {
			//TODO: Ährrrr unschöner Code irgendwie?! Geht das schöner?
			if (buyableField instanceof Street) {
				Street street = (Street) buyableField;
				//TODO: Magic numbers :P jay
				costs += street.getNumberOfHouse() * costForEachHouse + street.getNumberOfHotels() * costForEachHotel;
			}
		}
		
		// Danach dann abrechnen:
		Bank.exchangeMoney(state.getActivePlayer(), payee, costs);
	}

}
