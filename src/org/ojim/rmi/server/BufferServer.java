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

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;

/**
 * Klasse verwaltet eine Instanz des Remote Objektes , welche unter einem 
 * festgelegten Namen ueber das Netzwerk erreichbar ist
 * 
 * @author Usman Ghani Ahmed
 *
 */
public class BufferServer {

	private String name;
	
	public BufferServer(String name){
		
		this.name= name;
		
	}
/**
 * Netzwerk Objekt wird auf dem Server registriert	
 */
public void createBufferServer(){
	
	System.setProperty("java.security.policy", "client.policy"); 
	if (System.getSecurityManager() == null) {
		System.setSecurityManager(new RMISecurityManager());
	}
	
	
	System.out.println("Server wird eingerichtet...");
	   
	    try {
	      java.rmi.registry.LocateRegistry.createRegistry(1099);
	      ImplBuffer myBuffer = new ImplBuffer();
	      String serverName = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":1099/ServerInter";
	      Naming.rebind(serverName, myBuffer);
	      System.out.println("Server eingerichtet");
	      
	      System.out.println("RMI Wurde gestartet");
	      
	     

	    } catch (Exception e) {
	      System.out.println("Fehler beim Einrichten des Servers " + 
	e.getMessage());
	    }

	
	
}
	
	
}
