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
import org.ojim.iface.IClient;
import org.ojim.iface.Rules;
import org.ojim.rmi.client.NetClient;


/**
 * Interface stellt zusätzlich zum IServer Interface Methoden bereit 
 * die über das Netzwerk mit Hilfe von RMI, lokal oder über das Internet 
 * vom Client auf einer anderen JVM, in diesem Fall auf unserem Server aufgerufen werden können.  
 * 
 * @author Usman Ghani Ahmed
 *
 */
public interface NetOjim extends Remote {
	
	/**
	 * Meldet einen Client beim Server an, damit der Server Methoden des Client über
	 * RMI aufrufen kann
	 * 
	 * @param netClient Client Remote Objekt
	 * 
	 * @throws RemoteException
	 */
	public int registerClient(NetClient netClient) throws RemoteException;
	
	
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
	
	
	public int getPlayerPiecePosition(int playerID)throws RemoteException;


	public int addPlayer(IClient client)throws RemoteException;

	
	public void setPlayerReady(int player)throws RemoteException;


	public String getPlayerName(int player)throws RemoteException;

	
	public int getPlayerColor(int player)throws RemoteException;

	
	public int getTurnsInPrison(int playerID)throws RemoteException;

	
	public Rules getRules()throws RemoteException;

	
	public String getEstateName(int position)throws RemoteException;

	
	public int getEstateColorGroup(int position)throws RemoteException;

	
	public int getEstateHouses(int position)throws RemoteException;

	
	public int getEstatePrice(int position)throws RemoteException;

	
	public int getEstateHousePrice(int position)throws RemoteException;

	
	public int getEstateRent(int position, int houses)throws RemoteException;

	
	public String getGameStatusMessage(int playerID)throws RemoteException;

	
	public boolean isMortgaged(int position)throws RemoteException;

	
	public int getOwner(int position)throws RemoteException;

	
	public int getDiceValue()throws RemoteException;

	
	public int[] getDiceValues()throws RemoteException;

	
	public int getPlayerCash(int playerID)throws RemoteException;

	
	public int getPlayerOnTurn()throws RemoteException;

	
	public int getNumberOfGetOutOfJailCards(int playerID)throws RemoteException;

	
	public int getNumberOfHousesLeft()throws RemoteException;

	
	public int getNumberOfHotelsLeft()throws RemoteException;

	
	public boolean rollDice(int playerID)throws RemoteException;


	public boolean accept(int playerID)throws RemoteException;

	
	public boolean decline(int playerID)throws RemoteException;

	
	public boolean endTurn(int playerID)throws RemoteException;

	
	public boolean useGetOutOfJailCard(int playerID)throws RemoteException;


	public boolean payFine(int playerID)throws RemoteException;

	
	public boolean declareBankruptcy(int playerID)throws RemoteException;


	public boolean construct(int playerID, int position)throws RemoteException;

	
	public boolean deconstruct(int playerID, int position)throws RemoteException;

	
	public boolean toggleMortgage(int playerID, int position)throws RemoteException;

	
	public void sendMessage(String text, int sender)throws RemoteException;

	
	public void sendPrivateMessage(String text, int sender, int reciever)throws RemoteException;
	
	 

	
	
	
	
}
