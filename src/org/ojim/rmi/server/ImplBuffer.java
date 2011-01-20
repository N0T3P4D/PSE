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

package org.ojim.rmi.server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.ojim.iface.IClient;
import org.ojim.iface.Rules;

/**
 * Klasse verwaltet alle Methoden die ueber das Netzwerk aufgerufen werden koennen
 * @author Usman Ghani Ahmed
 *
 */
public class ImplBuffer extends UnicastRemoteObject implements NetOjim {

	
	
	protected ImplBuffer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPlayerPiecePosition(int playerID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEstateColorGroup(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEstateHouses(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEstatePrice(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEstateRent(int position, int houses) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getGameStatusMessage(int playerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMortgaged(int position) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getOwner(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDiceValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] getDiceValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPlayerCash(int playerID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPlayerOnTurn() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfGetOutOfJailCards(int playerID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfHousesLeft() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfHotelsLeft() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean rollDice(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean accept(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean decline(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean endTurn(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean declareBankruptcy(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean construct(int playerID, int position) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deconstruct(int playerID, int position) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean toggleMortgage(int playerID, int position) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int addPlayer(IClient client) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getEstateName(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPlayerColor(int player) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getPlayerName(int player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rules getRules() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPlayerReady(int player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getEstateHousePrice(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTurnsInPrison(int playerID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean payFine(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void sendMessage(String text, int sender) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendPrivateMessage(String text, int sender, int reciever) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean useGetOutOfJailCard(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}


}
