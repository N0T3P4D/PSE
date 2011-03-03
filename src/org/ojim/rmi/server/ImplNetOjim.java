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
		super();
		this.server = server;
		
	}
	
	
	public int getPlayerPiecePosition(int playerID) throws RemoteException {
		return this.server.getPlayerPiecePosition(playerID);
	}

	
	public int getEstateColorGroup(int position) throws RemoteException {
		return this.server.getEstateColorGroup(position);
	}

	
	public int getEstateHouses(int position) throws RemoteException {
		return this.server.getEstateHouses(position);
	}

	
	public int getEstatePrice(int position) throws RemoteException {
		return this.server.getEstatePrice(position);
	}

	
	public int getEstateRent(int position, int houses) throws RemoteException {
		return this.server.getEstateRent(position, houses);
	}
	
	public boolean isMortgaged(int position) throws RemoteException {
		return this.server.isMortgaged(position);
	}

	
	public int getOwner(int position) throws RemoteException {
		return this.server.getOwner(position);
	}

	
	public int getDiceValue() throws RemoteException {
		return this.server.getDiceValue();
	}

	
	public int[] getDiceValues() throws RemoteException {
		return this.server.getDiceValues();
	}

	
	public int getPlayerCash(int playerID) throws RemoteException {
		return this.server.getPlayerCash(playerID);
	}

	
	public int getPlayerOnTurn() throws RemoteException {
		return this.server.getPlayerOnTurn();
	}

	
	public int getNumberOfGetOutOfJailCards(int playerID) throws RemoteException {
		return this.server.getNumberOfGetOutOfJailCards(playerID);
	}

	
	public int getNumberOfHousesLeft() throws RemoteException {
		return this.server.getNumberOfHousesLeft();
	}

	
	public int getNumberOfHotelsLeft() throws RemoteException {
		return this.server.getNumberOfHotelsLeft();
	}

	
	public boolean rollDice(int playerID) throws RemoteException {
		return this.server.rollDice(playerID);
	}

	
	public boolean accept(int playerID) throws RemoteException {
		return this.server.accept(playerID);
	}

	
	public boolean decline(int playerID) throws RemoteException {
		return this.server.decline(playerID);
	}

	
	public boolean endTurn(int playerID) throws RemoteException {
		return this.server.endTurn(playerID);
	}

	
	public boolean declareBankruptcy(int playerID) throws RemoteException {
		return this.server.declareBankruptcy(playerID);
	}

	
	public boolean construct(int playerID, int position) throws RemoteException {
		return this.server.construct(playerID, position);
	}

	
	public boolean deconstruct(int playerID, int position) throws RemoteException {
		return this.server.deconstruct(playerID, position);
	}

	
	public boolean toggleMortgage(int playerID, int position) throws RemoteException {
		return this.server.toggleMortgage(playerID, position);
	}

	
	public int addPlayer(IClient client) throws RemoteException {
		return this.server.addPlayer(client);
	}
	
	public int getPlayerColor(int player) throws RemoteException {
		return this.server.getPlayerColor(player);
	}

	@Override
	public String getPlayerName(int player) throws RemoteException {
		return this.server.getPlayerName(player);
		
		
	}

	
	public Rules getRules() throws RemoteException {
		return this.server.getRules();
	}

	
	public void setPlayerReady(int player) throws RemoteException {
		this.server.setPlayerReady(player);
		
	}

	
	public int getEstateHousePrice(int position) throws RemoteException {
		return this.server.getEstateHousePrice(position);
	}

	
	public int getTurnsInPrison(int playerID) throws RemoteException {
		return this.server.getTurnsInPrison(playerID);
	}

	
	public boolean payFine(int playerID) throws RemoteException {
		return this.server.payFine(playerID);
	}

	
	public void sendMessage(String text, int sender) throws RemoteException {
		this.server.sendMessage(text, sender);
		
	}

	
	public void sendPrivateMessage(String text, int sender, int reciever) throws RemoteException {
		this.server.sendPrivateMessage(text, sender, reciever);
		
	}

	
	public boolean useGetOutOfJailCard(int playerID) throws RemoteException {
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

	@Override
	public int getAuctionState() throws RemoteException {
		return this.server.getAuctionState();
	}

	@Override
	public int getAuctionedEstate() throws RemoteException {
		return this.server.getAuctionedEstate();
	}


	@Override
	public int getHighestBid() throws RemoteException {
		return this.server.getHighestBid();
	}


	@Override
	public int getBidder() throws RemoteException {
		return this.server.getBidder();
	}


	@Override
	public boolean placeBid(int playerID, int amount) throws RemoteException {
		return this.server.placeBid(playerID, amount);
	}


	@Override
	public boolean initTrade(int actingPlayer, int partnerPlayer) throws RemoteException {
		return this.server.initTrade(actingPlayer, partnerPlayer);
	}


	@Override
	public int getTradeState() throws RemoteException {
		return this.server.getTradeState();
	}


	@Override
	public int getPartner() throws RemoteException {
		return this.server.getPartner();
	}


	@Override
	public boolean offerCash(int playerID, int amount) throws RemoteException {
		return this.server.offerCash(playerID, amount);
	}


	@Override
	public boolean offerGetOutOfJailCard(int playerID) throws RemoteException {
		return this.server.offerGetOutOfJailCard(playerID);
	}


	@Override
	public boolean offerEstate(int playerID, int position) throws RemoteException {
		return this.server.offerEstate(playerID, position);
	}


	@Override
	public boolean requireCash(int playerID, int amount) throws RemoteException {
		return this.server.requireCash(playerID, amount);
	}


	@Override
	public boolean requireGetOutOfJailCard(int playerID) throws RemoteException {
		return this.server.requireGetOutOfJailCard(playerID);
	}


	@Override
	public boolean requireEstate(int playerID, int position)throws RemoteException {
		
		return this.server.requireEstate(playerID, position);
	}


	@Override
	public int[] getOfferedEstates() throws RemoteException {
		return this.getOfferedEstates();
	}


	@Override
	public int getOfferedCash() throws RemoteException {
		return this.getOfferedCash();
	}


	@Override
	public int getNumberOfOfferedGetOutOfJailCards() throws RemoteException {
		return this.getNumberOfOfferedGetOutOfJailCards();
	}


	@Override
	public int[] getRequiredEstates() throws RemoteException {
		return this.server.getRequiredEstates();
	}


	@Override
	public int getRequiredCash() throws RemoteException {
		return this.server.getRequiredCash();
	}


	@Override
	public int getNumberOfRequiredGetOutOfJailCards() throws RemoteException {
		return this.server.getNumberOfRequiredGetOutOfJailCards();
	}


	@Override
	public boolean cancelTrade(int playerID) throws RemoteException {
		return this.server.cancelTrade(playerID);
	}


	@Override
	public boolean proposeTrade(int playerID) throws RemoteException {
		return this.server.proposeTrade(playerID);
	}

	public int getFreeParkingPot(int position) throws RemoteException {
		return this.server.getFreeParkingPot(position);
	}

	@Override
	public String getEstateName(int position, int player) throws RemoteException {
		return this.server.getEstateName(position, player);
	}


	@Override
	public int getActing() throws RemoteException {
		return this.server.getActing();
	}
}
