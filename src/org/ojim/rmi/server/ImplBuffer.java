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

import org.ojim.iface.IClient;
import org.ojim.iface.Rules;
import org.ojim.network.ServerDetails;
import org.ojim.rmi.client.NetClient;

/**
 * Klasse verwaltet alle Methoden die ueber das Netzwerk aufgerufen werden koennen
 * 
 * @author Usman Ghani Ahmed
 *
 */
public class ImplBuffer  extends UnicastRemoteObject implements NetOjim {
	
	private Registry reg;
	
	private ServerDetails serverDetails;
	
	//Speichert alle Clients
	private Vector clientList;

	public ImplBuffer(Registry reg, ServerDetails serverDetails) throws RemoteException {
		super();
		this.reg = reg;
		this.serverDetails = serverDetails;
		clientList = new Vector();

		
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


	@Override
	public synchronized void registerClient(NetClient netClient) throws RemoteException {
		 if (!(clientList.contains(netClient))) {
	         clientList.addElement(netClient);
	         }
	      System.out.println("Client wurde dem Server hinzugefügt");

		
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
	public int getRoundsToWait(int position) {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public int getMoneyToPay(int position) {
		// TODO Auto-generated method stub
		return 0;
	}


}
