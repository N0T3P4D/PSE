package org.ojim.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.ojim.client.ClientBase;
import org.ojim.iface.IClient;
import org.ojim.iface.Rules;
import org.ojim.network.ClientDetails;
import org.ojim.rmi.server.NetOjim;

import edu.kit.iti.pse.iface.IServer;

/**
 * Klasse verwaltet den Verbindungsaufbau vom Client zum Server.
 * 
 * @author Usman Ghani Ahmed
 *
 */
public class ImplNetClient extends UnicastRemoteObject implements NetClient, IServer {
	
	ClientBase clientBase;
	
	ClientDetails details;
	
	NetOjim server;
	
	public ImplNetClient(ClientBase clientBase, ClientDetails details) throws RemoteException {
		super();
		this.clientBase = clientBase;
		this.details = details;
	}
	
	/*
	public ImplNetClient(ClientBase clientBase) {
		
			this(clientBase, details);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	*/
	
	public void createClientRMIConnection(int portNum,String ip){
		 
		String registryURL = "rmi://"+ip+":" + portNum + "/myServer"; 
		 try {
			this.server =(NetOjim)Naming.lookup(registryURL);
			NetClient clientInter = this;
			this.server.registerClient(clientInter);
			System.out.println("Client wurde beim Server angemeldet");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
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
		int temp=0;
		try {
			temp = this.server.addPlayer(client);
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
	public String getEstateName(int position) {
		String temp="";
		try {
			temp = this.server.getEstateName(position);
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
	public String getGameStatusMessage(int playerID) {
		String temp="";
		try {
			temp = this.server.getGameStatusMessage(playerID);
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
	public void informTrade(int actingPlayer, int partnerPlayer)
			throws RemoteException {
		this.clientBase.informTrade(actingPlayer, partnerPlayer);
		
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

}
