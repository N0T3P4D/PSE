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

package org.ojim.logic.rules;

import org.ojim.logic.accounting.Bank;
import org.ojim.logic.state.BuyableField;
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
		if (!canPlayerModifyBuyableField(player, street)) {
			return false;
		}
		
		//Is the Field at position a street?
		if(!(street instanceof Street)) {
			return false;
		}
		
		return true;
	}

	private static boolean canPlayerModifyBuyableField(Player player, Field field) {
		//Is the player on turn?
		if(player != state.getActivePlayer()) {
			return false;
		}
		
		//Is the Field at position a buyable field?
		if(!(field instanceof BuyableField)) {
			return false;
		}
		
		//Is the player on turn owner of the street?
		if(state.getActivePlayer() != ((BuyableField) field).getOwner()) {
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

	public static boolean changeMortgage(Player player, Field field, boolean mortgage) {
		
		if(!canPlayerModifyBuyableField(player, field)) {
			return false;
		}
		
		BuyableField buyField = (BuyableField) field;
		
		//The Player can't spend more money than he has
		if(player.getBalance() < buyField.getMortgagePrice() && mortgage) {
			return false;
		}
		
		//When the Street already has the needed state, then we are done
		if(!(buyField.isMortgaged() ^ mortgage)) {
			return true;
		}
		
		Bank.exchangeMoney(player, state.getBank(), buyField.getMortgagePrice(), mortgage);
		buyField.setMortgaged(mortgage);
		
//		if(mortgage) {
//			//The Player gets Money and the street gets mortgaged
//			Bank.exchangeMoney(state.getBank(), player, str.getMortgagePrice());
//			str.setMortgaged(true);
//		} else {
//			//The Player needs to pay to get his Street demortgaged
//			Bank.exchangeMoney(player, state.getBank(), str.getMortgagePrice());
//			str.setMortgaged(false);
//		}
		
		return true;
	}
	
	public static boolean changeMortgage(int playerID, int position, boolean mortgage) {
		
		Street street = (Street)state.getFieldByID(position);
		Player player = state.getPlayerByID(playerID);
			
		return changeMortgage(player, street, mortgage);
	}
}
