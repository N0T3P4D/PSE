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

public class StartNetOjim {
	
	
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
	public void startServer(int portReg,String ip,ImplNetOjim ojimServer){
		
		System.out.println("Server wird eingerichtet...");
		
		try {
		    
			Registry registry = LocateRegistry.createRegistry(portReg);
			registry.list();  
			String registryURL = "rmi://"+ip+":" + portReg + "/myServer";
		    Naming.rebind(registryURL, ojimServer);
		    
		    System.err.println("Server ist bereit");
		} catch (Exception e) {
		    System.err.println("Server exception: " + e.toString());
		    e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * Beendet die gestartete Registry
	 */
    
	public void endRegistry(ImplNetOjim ojimServer){
		
    	
    	try {
			Registry registry = LocateRegistry.getRegistry();
			registry.unbind("myServer");
			UnicastRemoteObject.unexportObject(ojimServer, true);
			UnicastRemoteObject.unexportObject(registry, true);
			registry = null;
			
			
			} catch (RemoteException e) {
			
			System.out.println("Es wurde keine Registry gestartet!");
		} catch (NotBoundException e) {
			System.out.println("Es ist kein Objekt in der Registry registriert,"+"\n"+
					"somit kann auch kein Remote Objekt von der Registry abgemeldet werden!");
			
		}
    	
    	
    }
	

}
