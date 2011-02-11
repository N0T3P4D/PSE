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
package org.ojim.rmi.client;



import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import org.ojim.client.ClientBase;
import org.ojim.rmi.server.NetOjim;

public class StartNetClient {
	
	public NetOjim createClientRMIConnection(int portNum,String ip, ClientBase base){
		
		
		System.setSecurityManager(new java.rmi.RMISecurityManager());
		NetOjim server = null;
		String registryURL = "rmi://"+ip+":" + portNum + "/myServer"; 
		 try {
			
			server =(NetOjim)Naming.lookup(registryURL);
			NetClient client = new ImplNetClient(base,server);
			//ClientBase base = null ;
			//NetClient clientInter = new ImplNetClient(base);
			//server.registerClient(client);
			System.out.println("Client wurde beim Server angemeldet");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return server;
	}
	

}
