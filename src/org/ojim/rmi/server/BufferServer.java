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
