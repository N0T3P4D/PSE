package network;

import java.net.Socket;
import java.nio.Buffer;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;

/**
 * Der Client baut eine aktive Verbindung zum Server aufgebaut
 * @author Usman Ghani Ahmed
 *
 */
public class ClientConnection {
	
	
	private Socket clientSoket;
	
	public ClientConnection(){
		
		
	}
	
	public boolean isConnected (){
		
		
		return false;
		
	}
	
	/* Wurde geändert
	public void connect(String ip,int port){
		
		
	}
	*/
	
	public Buffer connect(String ip,String name){
		
		Buffer b1 = null;
		System.setSecurityManager(new RMISecurityManager());
		    String url = "rmi://"+ip+"/";   
		    try {
		      b1 = (Buffer)Naming.lookup(url + name);
		      
		    } catch (Exception e) {
		      System.out.println ("Fehler: " + e);
		    }
		  
		
    
	return b1;	
	}
}
