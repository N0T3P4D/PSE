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
