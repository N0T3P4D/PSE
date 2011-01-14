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

import java.net.ServerSocket;
import java.net.Socket;

import org.ojim.server.OjimServer;

public class Network {
	
	private ServerSocket server;
	
	private Socket client;
	
	private ServerDetails details;
	
	private ClientDetails [] cl;
	
	public Network (ServerSocket server,Socket client, ServerDetails details){
		
		this.server = server;
		this.client = client;
		this.details = details;
		this.cl = new ClientDetails[8];
		
	}
	
	public boolean waitingForClients(){
		
		
		return false;
		
	}
	
	public boolean cut(int playerID){
		return false;
		
	}
	
	public boolean cutMessage(int playerID, String message){
		return false;
		
	}
	
	public boolean connectionLost(int playerID){
		return false;
		
	}
	
	public boolean sendIServer(OjimServer server){
		return false;
		
	}
	
	public ServerDetails getServerDetails(){
		return details;
		
	}
	
	void addClient(ClientDetails client){
		
	}

}
