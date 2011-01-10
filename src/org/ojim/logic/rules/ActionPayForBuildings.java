package org.ojim.logic.rules;

import java.util.List;

import org.ojim.logic.accounting.Bank;
import org.ojim.logic.state.BuyableField;
import org.ojim.logic.state.Field;
import org.ojim.logic.state.GameState;
import org.ojim.logic.state.Street;

public class ActionPayForBuildings implements Action {

	private int costForEachHouse;
	private int costForEachHotel;
	private final GameState state;
	
	public ActionPayForBuildings(GameState state, int costForEachHouse, int costForEachHotel) {
		this.costForEachHouse = costForEachHouse;
		this.costForEachHotel = costForEachHotel;
		this.state = state;
	}
	
	@Override
	public void execute() {
		ActionPayForBuildings.execute(this.state, this.costForEachHouse, this.costForEachHotel);
	}
	
	public static void execute(GameState state, int costForEachHouse, int costForEachHotel) {
		int costs = 0;
		
		// Gehe jede Straße des Spielers durch
		List<BuyableField> field = state.getActivePlayer().getFields();
		
		// In jeder Straße gucke wie viele Häuse/Hotels es gibt
		for (BuyableField buyableField : field) {
			//TODO: Ährrrr unschöner Code irgendwie?! Geht das schöner?
			if (buyableField instanceof Street) {
				Street street = (Street) buyableField;
				//TODO: Magic numbers :P jay
				costs += (street.getBuiltLevel() % 5) * costForEachHouse + (street.getBuiltLevel() / 5) * costForEachHotel;
			}
		}
		
		// Danach dann abrechnen:
		Bank.exchangeMoney(state.getActivePlayer(), state.getBank(), costs);
	}

}
