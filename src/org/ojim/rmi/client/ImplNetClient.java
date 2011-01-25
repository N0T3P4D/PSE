package org.ojim.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.ojim.client.ClientBase;
import org.ojim.rmi.server.ImplNetOjim;
import org.ojim.rmi.server.NetOjim;

public class ImplNetClient extends UnicastRemoteObject implements NetClient {
	
	ClientBase clientBase;
	
	public ImplNetClient(ClientBase clientBase) throws RemoteException {
		super();
		this.clientBase = clientBase;
		
	}
	
	public NetOjim createClientRMIConnection(int portNum,String ip){
		 
		NetOjim serverOjim = null;
		String registryURL = "rmi://"+ip+":" + portNum + "/myServer"; 
		 try {
			serverOjim =(NetOjim)Naming.lookup(registryURL);
			NetClient clientInter = this;
			serverOjim.registerClient(clientInter);
			System.out.println("Client wurde beim Server angemeldet");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		return serverOjim;
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

}
