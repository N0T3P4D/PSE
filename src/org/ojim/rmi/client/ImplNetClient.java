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
package org.ojim.rmi.client;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.ojim.client.ClientBase;
import org.ojim.iface.IClient;
import org.ojim.iface.Rules;
import org.ojim.rmi.server.NetOjim;

import edu.kit.iti.pse.iface.IServer;

/**
 * Klasse verwaltet den Verbindungsaufbau vom Client zum Server.
 * 
 * @author Usman Ghani Ahmed
 *
 */
public class ImplNetClient extends UnicastRemoteObject implements NetClient, IServer {
	
	private ClientBase clientBase;
	
	private NetOjim server;
	
	public ImplNetClient(ClientBase clientBase, NetOjim server) throws RemoteException {
		super(2007);
		this.clientBase = clientBase;
		this.server = server;
		
	}
	
	
	@Override
	public int getPlayerPiecePosition(int playerID) {
		int temp=0;
		try {
			temp = this.server.getPlayerPiecePosition(playerID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public int addPlayer(IClient client) {
		NetClient netClient = this;
		int temp=0;
		try {
			temp = this.server.registerClient(netClient);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public void setPlayerReady(int player) {
		try {
			this.server.setPlayerReady(player);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String getPlayerName(int player) {
		String temp="";
		
		try {
			temp = this.server.getPlayerName(player);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
		
		
	}

	@Override
	public int getPlayerColor(int player) {
		int temp=0;
		try {
			temp = this.server.getPlayerColor(player);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public int getTurnsInPrison(int playerID) {
		int temp=0;
		try {
			temp = this.server.getTurnsInPrison(playerID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public Rules getRules() {
		Rules temp=null;
		try {
			temp = this.server.getRules();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public String getEstateName(int position, int player) {
		String temp="";
		try {
			temp = this.server.getEstateName(position, player);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public int getEstateColorGroup(int position) {
		int temp=0;
		try {
			temp = this.server.getEstateColorGroup(position);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public int getEstateHouses(int position) {
		int temp=0;
		try {
			temp = this.server.getEstateHouses(position);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public int getEstatePrice(int position) {
		int temp=0;
		try {
			temp = this.server.getEstatePrice(position);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public int getEstateHousePrice(int position) {
		int temp=0;
		try {
			temp = this.server.getEstateHousePrice(position);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public int getEstateRent(int position, int houses) {
		int temp=0;
		try {
			temp = this.server.getEstateRent(position, houses);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public boolean isMortgaged(int position) {
		boolean temp=false;
		try {
			temp = this.server.isMortgaged(position);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public int getOwner(int position) {
		int temp=0;
		try {
			temp = this.server.getOwner(position);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public int getDiceValue() {
		int temp=0;
		try {
			temp = this.server.getDiceValue();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public int[] getDiceValues() {
		int[] temp = null;
		try {
			temp = this.server.getDiceValues();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public int getPlayerCash(int playerID) {
		int temp=0;
		try {
			temp = this.server.getPlayerCash(playerID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public int getPlayerOnTurn() {
		int temp=0;
		try {
			temp = this.server.getPlayerOnTurn();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public int getNumberOfGetOutOfJailCards(int playerID) {
		int temp=0;
		try {
			temp = this.server.getNumberOfGetOutOfJailCards(playerID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public int getNumberOfHousesLeft() {
		int temp=0;
		try {
			temp = this.server.getNumberOfHousesLeft();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public int getNumberOfHotelsLeft() {
		int temp=0;
		try {
			temp = this.server.getNumberOfHotelsLeft();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public boolean rollDice(int playerID) {
		boolean temp=false;
		try {
			temp = this.server.rollDice(playerID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public boolean accept(int playerID) {
		boolean temp=false;
		try {
			temp = this.server.accept(playerID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public boolean decline(int playerID) {
		boolean temp=false;
		try {
			temp = this.server.decline(playerID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public boolean endTurn(int playerID) {
		boolean temp=false;
		try {
			temp = this.server.endTurn(playerID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public boolean useGetOutOfJailCard(int playerID) {
		boolean temp=false;
		try {
			temp = this.server.useGetOutOfJailCard(playerID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public boolean payFine(int playerID) {
		boolean temp=false;
		try {
			temp = this.server.payFine(playerID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public boolean declareBankruptcy(int playerID) {
		boolean temp=false;
		try {
			temp = this.server.declareBankruptcy(playerID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public boolean construct(int playerID, int position) {
		boolean temp=false;
		try {
			temp = this.server.construct(playerID, position);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
		
	}

	@Override
	public boolean deconstruct(int playerID, int position) {
		boolean temp=false;
		try {
			temp = this.server.deconstruct(playerID, position);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public boolean toggleMortgage(int playerID, int position) {
		boolean temp=false;
		try {
			temp = this.server.toggleMortgage(playerID, position);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public void sendMessage(String text, int sender) {
		
		try {
			this.server.sendMessage(text, sender);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void sendPrivateMessage(String text, int sender, int reciever) {
		
		try {
			this.server.sendPrivateMessage(text, sender, reciever);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	
		
	}

	@Override
	public String getName() throws RemoteException {
		
		return this.clientBase.getName();
	}

	@Override
	public String getLanguage() throws RemoteException {
		return this.clientBase.getLanguage();
	}

	@Override
	public void informStartGame(int[] ids) throws RemoteException {
		this.clientBase.informStartGame(ids);
		
	}

	@Override
	public void informTurn(int player) throws RemoteException {
		this.clientBase.informTurn(player);
		
	}

	@Override
	public void informDiceValues(int[] diceValues) throws RemoteException {
		this.clientBase.informDiceValues(diceValues);
		
	}

	@Override
	public void informCashChange(int player, int cashChange)
			throws RemoteException {
		this.clientBase.informCashChange(player, cashChange);
		
	}

	@Override
	public void informBuy(int player, int position) throws RemoteException {
		this.clientBase.informBuy(player, position);
		
	}

	@Override
	public void informConstruct(int street) throws RemoteException {
		this.clientBase.informConstruct(street);
		
	}

	@Override
	public void informDestruct(int street) throws RemoteException {
		this.clientBase.informDestruct(street);
		
	}

	@Override
	public void informMortgageToogle(int street) throws RemoteException {
		this.clientBase.informMortgageToogle(street);
		
	}

	@Override
	public void informCardPull(String text, boolean communityCard)
			throws RemoteException {
		this.clientBase.informCardPull(text, communityCard);
		
	}

	@Override
	public void informBankruptcy() throws RemoteException {
		this.clientBase.informBankruptcy();
		
	}

	@Override
	public void informMessage(String text, int sender, boolean privateMessage)
			throws RemoteException {
		this.clientBase.informMessage(text, sender, privateMessage);
		
	}

	@Override
	public void informTrade() throws RemoteException {
		this.clientBase.informTrade();
		
	}

	@Override
	public void informAuction(int auctionState) throws RemoteException {
		this.clientBase.informAuction(auctionState);
		
	}

	@Override
	public void informMove(int playerId, int position) throws RemoteException {
		this.clientBase.informMove(playerId, position);
		
	}

	@Override
	public void informNewPlayer(int playerId) throws RemoteException {
		this.clientBase.informNewPlayer(playerId);
		
	}

	@Override
	public void informPlayerLeft(int playerId) throws RemoteException {
		this.clientBase.informPlayerLeft(playerId);
		
	}

	@Override
	public void setPlayerId(int newId) throws RemoteException {
		this.clientBase.setPlayerId(newId);
		
	}
	
	@Override
	public void informFreeParkingChange(int freeParkingField, int newPot) throws RemoteException {
		this.clientBase.informFreeParkingChange(freeParkingField, newPot);
	}

	@Override
	public int getFreeParkingPot(int position) {
		int temp=0;
		try {
			temp = this.server.getFreeParkingPot(position);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}


	@Override
	public void informCanEndTurn(int player) throws RemoteException {
		this.clientBase.informCanEndTurn(player);
	}


	@Override
	public void informBuyEvent(int player, int position) throws RemoteException {
		this.clientBase.informBuyEvent(player, position);
	}

}
