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

package org.ojim.network;





import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.ojim.rmi.server.ImplBuffer;



/**
 * Klasse verwaltet den Verbindungsaufbau vom Client zum Server.
 * 
 * @author Usman Ghani Ahmed
 * 
 */
public class ClientConnection {

	//private Socket clientSoket;
	
	//Speichert die Verbindungsart
	//0 für eine Verbindung im Local Area Network
	//1 für eine Verbindung über das internet
	private int connectionType;

	public ClientConnection(int connectionType) {
		this.connectionType=connectionType;

	}

	public boolean isConnected() {

		return false;

	}
	
	/**
	 * Meldet einen Client beim Server an
	 * 
	 * @param ip ip Adresse des Servers
	 * @param port port der Registry , die auf dem Server l�uft
	 * @return Remote Objekt 
	 */
	public ImplBuffer connect(String ip, int port) {
		
		ImplBuffer iServer = null;
		
		if(this.connectionType == 0){
			ip="localhost";
		} 
		
		try {
		    Registry registry = LocateRegistry.getRegistry(ip,port);
		    iServer = (ImplBuffer) registry.lookup("myServer");
		    System.out.print("Client wurde erfolgreich beim Server angemeldet!");
		} catch (Exception e) {
		    System.err.println("Client exception: " + e.toString());
		    e.printStackTrace();
		}
		
		return iServer;
		
		
	}
}
