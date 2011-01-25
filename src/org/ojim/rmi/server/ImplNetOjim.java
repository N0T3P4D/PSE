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


import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import org.ojim.client.SimpleClient;
import org.ojim.iface.IClient;
import org.ojim.iface.Rules;
import org.ojim.network.ServerDetails;
import org.ojim.rmi.client.NetClient;
import org.ojim.server.OjimServer;

/**
 * Klasse verwaltet alle Methoden die ueber das Netzwerk aufgerufen werden können 
 * 
 * @author Usman Ghani Ahmed
 *
 */
public class ImplNetOjim  extends UnicastRemoteObject implements NetOjim {
	
	private Registry reg;
	
	private ServerDetails serverDetails;
	
	private OjimServer server;
	
	//Speichert alle Clients
	private Vector clientList;

	public ImplNetOjim(Registry reg, ServerDetails serverDetails, OjimServer server) throws RemoteException {
		super();
		this.reg = reg;
		this.serverDetails = serverDetails;
		clientList = new Vector();
		this.server = server;
	}
	
	
	

	/**
	 * Klasse verwaltet eine Instanz des Remote Objektes , welches unter einem 
     * festgelegten Namen über das Netzwerk erreichbar ist
	 * Netzwerk Objekt wird bei dem lokalen Namendienst registriert, portReg und
	 * portStub müssen von der lokalen Firewall und der Hardware Firewall
	 * (Router) freigegeben werden. Bitte Achten Sie auch darauf, dass
	 * Ports die schon von ihrem Betriebssystem benutzt werden, nicht für ihren Server 
	 * benutzt werden können. Eine Liste mit den Standardports die von ihrem Betriebssystem
	 * verwendet werden, finden Sie auf http://en.wikipedia.org/wiki/List_of_TCP_and_UDP_port_numbers. 
	 *  
	 * 
	 * @param portReg Port für die lokale Registry
	 * 
	 * @param portStub Port für das exportieren des Objekts
	 * 
	 * @param ip ip Adresse unter welcher der Namendienst erreichbar ist
	 */
	public void createBufferServer(int portReg, int portStub, int ip){
		
	System.out.println("Server wird eingerichtet...");
	
	try {
	    
		
	    //NetOjim stub = (NetOjim) UnicastRemoteObject.exportObject(this,portStub );
	    //*reg = LocateRegistry.createRegistry(portReg);
	    // Bind the remote object's stub in the registry
	    //Registry registry = LocateRegistry.getRegistry();
	    //reg.bind("myServer", stub);
	    //*reg.bind("myServer", this);
		 Registry registry = LocateRegistry.createRegistry(portReg);
		 registry.list( );  
		
		String registryURL = "rmi://"+ip+":" + portReg + "/myServer";
	    Naming.rebind(registryURL, this);
	    
	    System.err.println("Server ist bereit");
	} catch (Exception e) {
	    System.err.println("Server exception: " + e.toString());
	    e.printStackTrace();
	}
	
	}
	
	/**
	 * Beendet die gestartete Registry
	 */
    public void endRegistry(){
		
    	
    	try {
			//Registry registry = LocateRegistry.getRegistry();
			this.reg.unbind("myServer");
			UnicastRemoteObject.unexportObject(this, true);
			//this.shutdown(true);
			UnicastRemoteObject.unexportObject(this.reg, true);
			this.reg = null;
			
			
			
			
		} catch (RemoteException e) {
			
			System.out.println("Es wurde keine Registry gestartet!");
		} catch (NotBoundException e) {
			System.out.println("Es ist kein Objekt in der Registry registriert,"+"\n"+
					"somit kann auch kein Remote Objekt von der Registry abgemeldet werden!");
			
		}
    	
    	
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

	
	public String getPlayerName(int player) {
		return this.server.getPlayerName(player);
	}

	
	public Rules getRules() {
		return this.server.getRules();
	}

	
	public void setPlayerReady(int player) {
		this.server.setPlayerReady(player);
		
	}

	
	public int getEstateHousePrice(int position) {
		return this.getEstateHousePrice(position);
	}

	
	public int getTurnsInPrison(int playerID) {
		return this.getTurnsInPrison(playerID);
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
	public synchronized void registerClient(NetClient netClient) throws RemoteException {
		 
		if (!(clientList.contains(netClient)) && this.serverDetails.getOpen()) {
	         clientList.addElement(netClient);
	         this.serverDetails.connectPlayer();
	         
	         
	         
	         
	       System.out.println("Client wurde dem Server hinzugefügt");  
		} else {
			System.out.print("Error: Client ist schon beim Server angemeldet");
			
		}
	      

		
	}




	@Override
	public synchronized void abmeldenClient(NetClient netClient) throws RemoteException {
		 
		    if (clientList.removeElement(netClient)) {
		      System.out.println("Client wurde erfolgreich beim Server abgemeldet");
		    } else {
		       System.out.println("Client war bereits nicht beim Server angemeldet");
		    }

		
	}




	@Override
	public int getRoundsToWait(int position) throws RemoteException {
		
		return this.server.getRoundsToWait(position);
		
		
	}




	@Override
	public int getMoneyToPay(int position) throws RemoteException {
		
		return this.server.getMoneyToPay(position);
	}

	

}
