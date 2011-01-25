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
import org.ojim.rmi.server.ImplNetOjim;
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
	public String getName() {
		return this.clientBase.getName();
	}

	@Override
	public String getLanguage() {
		return this.clientBase.getLanguage();
	}

	@Override
	public void informStartGame(int[] ids) {
		this.clientBase.informStartGame(ids);
		
	}

	@Override
	public void informTurn(int player) {
		this.clientBase.informTurn(player);
		
	}

	@Override
	public void informDiceValues(int[] diceValues) {
		this.clientBase.informDiceValues(diceValues);
		
	}

	@Override
	public void informCashChange(int player, int cashChange) {
		this.informCashChange(player, cashChange);		
	}

	@Override
	public void informBuy(int player, int position) {
		this.clientBase.informBuy(player, position);
		
	}

	@Override
	public void informConstruct(int street) {
		this.clientBase.informConstruct(street);
		
	}

	@Override
	public void informDestruct(int street) {
		this.clientBase.informDestruct(street);
		
	}

	@Override
	public void informMortgageToogle(int street) {
		this.clientBase.informMortgageToogle(street);
		
	}

	@Override
	public void informCardPull(String text, boolean communityCard) {
		this.clientBase.informCardPull(text, communityCard);
		
	}

	@Override
	public void informBankruptcy() {
		this.clientBase.informBankruptcy();
		
	}

	@Override
	public void informMessage(String text, int sender, boolean privateMessage) {
		this.clientBase.informMessage(text, sender, privateMessage);
		
	}

	@Override
	public void informTrade(int actingPlayer, int partnerPlayer) {
		this.clientBase.informTrade(actingPlayer, partnerPlayer);
		
	}

	@Override
	public void informAuction(int auctionState) {
		this.informAuction(auctionState);
		
	}

	@Override
	public void informMove(int playerId, int position) {
		this.clientBase.informMove(playerId, position);
		
	}

	@Override
	public void informNewPlayer(int playerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informPlayerLeft(int playerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPlayerPiecePosition(int playerID) {
		try {
			return this.server.getPlayerPiecePosition(playerID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int addPlayer(IClient client) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPlayerReady(int player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPlayerName(int player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPlayerColor(int player) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTurnsInPrison(int playerID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Rules getRules() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEstateName(int position) {
		// TODO Auto-generated method stub
		return null;
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
	public int getEstateHousePrice(int position) {
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
	public boolean useGetOutOfJailCard(int playerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean payFine(int playerID) {
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
	public void sendMessage(String text, int sender) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendPrivateMessage(String text, int sender, int reciever) {
		// TODO Auto-generated method stub
		
	}

}
