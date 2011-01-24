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


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.ojim.iface.IClient;
import org.ojim.iface.Rules;
import org.ojim.network.ServerDetails;

/**
 * Klasse verwaltet alle Methoden die ueber das Netzwerk aufgerufen werden koennen
 * 
 * @author Usman Ghani Ahmed
 *
 */
public class ImplBuffer implements NetOjim {
	
	private Registry reg;
	
	private ServerDetails serverDetails;
	
	
	
	public ImplBuffer(Registry reg, ServerDetails serverDetails){
		this.reg = reg;
		this.serverDetails = serverDetails;
		
	}
	
	
	

	/**
	 * Netzwerk Objekt wird bei dem lokalen Namendienst registriert, portReg und
	 * portStub müssen von der lokalen Firewall und der Hardware Firewall
	 * (Router) freigegeben werden. Bitte Achten Sie auch darauf, dass
	 * Ports die schon von ihrem Betriebssystem benutzt werden, nicht für ihren Server 
	 * benutzt werden können. Eine Liste mit den Standardports die von ihrem Betriebssystem
	 * verwendet werden, entnehmen Sie der Readme Datei. 
	 *  
	 * 
	 * @param portReg Port für die lokale Registry
	 * 
	 * @param portStub Port für das exportieren des Objekts
	 */
	public void createBufferServer(int portReg, int portStub){
		
	System.out.println("Server wird eingerichtet...");
	
	try {
	    
	    NetOjim stub = (NetOjim) UnicastRemoteObject.exportObject(this,portStub );
	    reg = LocateRegistry.createRegistry(portReg);
	    // Bind the remote object's stub in the registry
	    //Registry registry = LocateRegistry.getRegistry();
	    reg.bind("myServer", stub);
	    
	  

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
					"somit kann auch kein Remote von der Registry abgemeldet werden!");
			
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


}
