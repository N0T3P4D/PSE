package org.ojim.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.ojim.rmi.server.NetOjim;

public class ImplNetClient extends UnicastRemoteObject implements NetClient {
	
	
	
	protected ImplNetClient() throws RemoteException {
		super();
		
	}
	
	public void createClientRMIConnection(int portNum, int ip){
		 
		String registryURL = "rmi://"+ip+":" + portNum + "/myServer"; 
		 try {
			NetOjim serverOjim =(NetOjim)Naming.lookup(registryURL);
			NetClient clientInter = this;
			serverOjim.registerClient(clientInter);
			System.out.println("Client wurde beim Server angemeldet");
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void informStartGame(int[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informTurn(int player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informDiceValues(int[] diceValues) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informCashChange(int player, int cashChange) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informBuy(int player, int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informConstruct(int street) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informDestruct(int street) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informMortgageToogle(int street) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informCardPull(String text, boolean communityCard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informBankruptcy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informMessage(String text, int sender, boolean privateMessage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informTrade(int actingPlayer, int partnerPlayer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informAuction(int auctionState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informMove(int playerId, int position) {
		// TODO Auto-generated method stub
		
	}

}
