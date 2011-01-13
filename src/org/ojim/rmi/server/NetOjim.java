package org.ojim.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import edu.kit.iti.pse.iface.IServer;

public interface NetOjim extends Remote , IServer {
	
	public void setName(String name) throws RemoteException;
	public String getName()throws RemoteException;
		
		
	

}
