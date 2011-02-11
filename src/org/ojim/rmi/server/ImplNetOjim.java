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


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import org.ojim.iface.IClient;
import org.ojim.iface.Rules;
import org.ojim.rmi.client.NetClient;
import org.ojim.server.OjimServer;

/**
 * Klasse verwaltet alle Methoden die über das Netzwerk aufgerufen werden können 
 * 
 * @author Usman Ghani Ahmed
 *
 */
public class ImplNetOjim  extends UnicastRemoteObject implements NetOjim {
	
	
	private NetClient netClient;
	
	private OjimServer server;
	
	public ImplNetOjim(OjimServer server) throws RemoteException {
		super(2007);
		this.server = server;
		
	}
	
	
	public int getPlayerPiecePosition(int playerID) {
		return this.server.getPlayerPiecePosition(playerID);
	}

	
	public int getEstateColorGroup(int position) {
		return this.server.getEstateColorGroup(position);
	}

	
	public int getEstateHouses(int position) {
		return this.server.getEstateHouses(position);
	}

	
	public int getEstatePrice(int position) {
		return this.server.getEstatePrice(position);
	}

	
	public int getEstateRent(int position, int houses) {
		return this.server.getEstateRent(position, houses);
	}

	
	public String getGameStatusMessage(int playerID) {
		return this.server.getGameStatusMessage(playerID);
	}

	
	public boolean isMortgaged(int position) {
		return this.server.isMortgaged(position);
	}

	
	public int getOwner(int position) {
		return this.server.getOwner(position);
	}

	
	public int getDiceValue() {
		return this.server.getDiceValue();
	}

	
	public int[] getDiceValues() {
		return this.server.getDiceValues();
	}

	
	public int getPlayerCash(int playerID) {
		return this.server.getPlayerCash(playerID);
	}

	
	public int getPlayerOnTurn() {
		return this.server.getPlayerOnTurn();
	}

	
	public int getNumberOfGetOutOfJailCards(int playerID) {
		return this.server.getNumberOfGetOutOfJailCards(playerID);
	}

	
	public int getNumberOfHousesLeft() {
		return this.server.getNumberOfHousesLeft();
	}

	
	public int getNumberOfHotelsLeft() {
		return this.server.getNumberOfHotelsLeft();
	}

	
	public boolean rollDice(int playerID) {
		return this.server.rollDice(playerID);
	}

	
	public boolean accept(int playerID) {
		return this.server.accept(playerID);
	}

	
	public boolean decline(int playerID) {
		return this.server.decline(playerID);
	}

	
	public boolean endTurn(int playerID) {
		return this.server.endTurn(playerID);
	}

	
	public boolean declareBankruptcy(int playerID) {
		return this.server.declareBankruptcy(playerID);
	}

	
	public boolean construct(int playerID, int position) {
		return this.server.construct(playerID, position);
	}

	
	public boolean deconstruct(int playerID, int position) {
		return this.server.deconstruct(playerID, position);
	}

	
	public boolean toggleMortgage(int playerID, int position) {
		return this.server.toggleMortgage(playerID, position);
	}

	
	public int addPlayer(IClient client) {
		return this.server.addPlayer(client);
	}

	
	public String getEstateName(int position) {
		return this.server.getEstateName(position);
	}

	
	public int getPlayerColor(int player) {
		return this.server.getPlayerColor(player);
	}

	@Override
	public String getPlayerName(int player)throws RemoteException {
		return this.server.getPlayerName(player);
		
		
	}

	
	public Rules getRules() {
		return this.server.getRules();
	}

	
	public void setPlayerReady(int player) {
		this.server.setPlayerReady(player);
		
	}

	
	public int getEstateHousePrice(int position) {
		return this.server.getEstateHousePrice(position);
	}

	
	public int getTurnsInPrison(int playerID) {
		return this.server.getTurnsInPrison(playerID);
	}

	
	public boolean payFine(int playerID) {
		return this.server.payFine(playerID);
	}

	
	public void sendMessage(String text, int sender) {
		this.server.sendMessage(text, sender);
		
	}

	
	public void sendPrivateMessage(String text, int sender, int reciever) {
		this.server.sendPrivateMessage(text, sender, reciever);
		
	}

	
	public boolean useGetOutOfJailCard(int playerID) {
		return this.server.useGetOutOfJailCard(playerID);
	}


	@Override
	public synchronized int registerClient(NetClient netClient) throws RemoteException {
		 
		ClientWrapper clientWrap = new ClientWrapper(netClient);
		int temp = this.server.addPlayer(clientWrap);
		this.netClient = netClient;
		return temp;
		
	}




	@Override
	public synchronized void abmeldenClient(NetClient netClient) throws RemoteException {
		 	
		this.netClient = null;
		
		
	}




	@Override
	public int getRoundsToWait(int position) throws RemoteException {
		
		return this.server.getRoundsToWait(position);
		
		
	}




	@Override
	public int getMoneyToPay(int position) throws RemoteException {
		
		return this.server.getMoneyToPay(position);
	}
	
	public NetClient getNetClient(){
		return this.netClient;
	}


}
