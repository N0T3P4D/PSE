package org.ojim.logic.rules;

import org.ojim.logic.accounting.Bank;
import org.ojim.logic.state.Field;
import org.ojim.logic.state.GameState;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.Street;

public class GameRules {

	private static GameState state;
	
	public GameRules(GameState state) {
		this.state = state;
	}
	
	public static boolean upgradeStreet(Player player, Field street, int levelChange) {
		
		if(!canPlayerModifyStreet(player, street)) {
			return false;
		}

		Street str = (Street)street;
		
		//When Upgrading, check for Money
		if(levelChange > 0) {
			if(player.getBalance() < levelChange * str.getFieldGroup().getHousePrice()) {
				return false;
			}
		}
		
		//then try to upgrade/downgrade the Street
		if (str.upgrade(levelChange)) {
			Bank.exchangeMoney(state.getBank(), player, -(levelChange * str.getFieldGroup().getHousePrice()));
			return true;
		}
		return false;
	}
	
	public static boolean upgradeStreet(int playerID, int position, int levelChange) {
		
		Street street = (Street)state.getFieldByID(position);
		Player player = state.getPlayerByID(playerID);
		
		return upgradeStreet(player, street, levelChange);
	}
	
	private static boolean canPlayerModifyStreet(Player player, Field street) {
		
		//Is the player on turn?
		if(player != state.getActivePlayer()) {
			return false;
		}
		
		//Is the Field at position a Street?
		if(!(street instanceof Street)) {
			return false;
		}
		
		//Is the player on turn owner of the street?
		if(state.getActivePlayer() != ((Street)street).getOwner()) {
			return false;
		}
		
		return true;
	}

	public static boolean changeMortgage(int playerID, int position) {
		Street street = (Street)state.getFieldByID(position);
		Player player = state.getPlayerByID(playerID);
		
		return changeMortgage(player, street);
	}
	
	private static boolean changeMortgage(Player player, Street street) {
		if(((Street)street).isMortgaged()) {
			return changeMortgage(player, street, false);
		} else {
			return changeMortgage(player, street, true);
		}
	}

	public static boolean changeMortgage(Player player, Field street, boolean mortgage) {
		
		if(!canPlayerModifyStreet(player, street)) {
			return false;
		}
		
		Street str = (Street)street;
		
		//The Player can't spend more money than he has
		if(player.getBalance() < str.getMortgagePrice() && mortgage) {
			return false;
		}
		
		//When the Street already has the needed state, then we are done
		if(!(str.isMortgaged() ^ mortgage)) {
			return true;
		}
		
		
		if(mortgage) {
			//The Player gets Money and the street gets mortgaged
			Bank.exchangeMoney(state.getBank(), player, str.getMortgagePrice());
			str.setMortgaged(true);
		} else {
			//The Player needs to pay to get his Street demortgaged
			Bank.exchangeMoney(player, state.getBank(), str.getMortgagePrice());
			str.setMortgaged(false);
		}
		
		return true;
	}
	
	public static boolean changeMortgage(int playerID, int position, boolean mortgage) {
		
		Street street = (Street)state.getFieldByID(position);
		Player player = state.getPlayerByID(playerID);
			
		return changeMortgage(player, street, mortgage);
	}
}
