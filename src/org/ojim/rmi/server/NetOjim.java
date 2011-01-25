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

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.ojim.client.SimpleClient;
import org.ojim.iface.IClient;
import org.ojim.iface.Rules;
import org.ojim.rmi.client.NetClient;

import edu.kit.iti.pse.iface.IServer;
/**
 * Interface stellt zusätzlich zum IServer Interface Methoden bereit 
 * die über das Netzwerk mit Hilfe von RMI, lokal oder über das Internet 
 * vom Client auf einer anderen JVM, in diesem Fall auf unserem Server aufgerufen werden können.  
 * 
 * @author Usman Ghani Ahmed
 *
 */
public interface NetOjim extends Remote ,IServer {
	
	/**
	 * Meldet einen Client beim Server an, damit der Server Methoden des Client über
	 * RMI aufrufen kann
	 * 
	 * @param netClient Client Remote Objekt
	 * 
	 * @throws RemoteException
	 */
	public void registerClient(NetClient netClient) throws RemoteException;
	
	
	/**
	 * Meldet einen Client beim Server ab,zum Beispiel bei frühzeitiger Spielbeendigung
	 * eines Client  
	 * 
	 * @param netClient Client Remote Objekt
	 * 
	 * @throws RemoteException
	 */
	public void abmeldenClient(NetClient netClient)throws RemoteException;

	
	/**
	 * Gibt Auskunft über die Anzahl der Runden die ein Spieler im Gefängnis sitzt beziehungsweise
	 * beim Würfeln aussetzt 
	 * 
	 * @param sClient Client Objekt
	 * @param position Position
	 * @return Anzahl der Runden
	 * @throws RemoteException
	 */
	public int getRoundsToWait(int position)throws RemoteException;

	
	/**
	 * Gibt den Geldbetrag zurück ,
	 * mit welchem sich der Spieler aus dem Gefängnis frei kaufen kann
	 * 
	 * @param sClient Client Objekt
	 * @param position Position
	 * @return Geldbetrag
	 * @throws RemoteException
	 */
	public int getMoneyToPay(int position) throws RemoteException;
	
}
