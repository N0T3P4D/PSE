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

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.ojim.iface.IClient;

import edu.kit.iti.pse.iface.IServer;
/**
 * 
 * Interface stellt zusätzlich zum IClient Interface Methoden bereit, 
 * die über das Netzwerk mit Hilfe von RMI, lokal oder über das Internet 
 * vom Server auf einer anderen JVM, in diesem Fall auf unserem Client aufgerufen werden können.  
 * 
 * 
 * @author Usman Ghani Ahmed
 *
 */
public interface NetClient extends Remote {
	
	
	String getName() throws RemoteException;

	
	String getLanguage() throws RemoteException;

	
	void informStartGame(int[] ids)throws RemoteException;

	
	void informTurn(int player)throws RemoteException;


	void informDiceValues(int[] diceValues)throws RemoteException;

	
	void informCashChange(int player, int cashChange)throws RemoteException;

	
	void informBuy(int player, int position)throws RemoteException;

	
	void informConstruct(int street)throws RemoteException;

	
	void informDestruct(int street)throws RemoteException;

	
	void informMortgageToogle(int street)throws RemoteException;

	
	void informCardPull(String text, boolean communityCard)throws RemoteException;

	
	void informBankruptcy()throws RemoteException;

	
	void informMessage(String text, int sender, boolean privateMessage)throws RemoteException;

	
	void informTrade(int actingPlayer, int partnerPlayer)throws RemoteException;

	
	void informAuction(int auctionState)throws RemoteException;

	
	void informMove(int playerId, int position)throws RemoteException;

	
	void informNewPlayer(int playerId)throws RemoteException;

	
	void informPlayerLeft(int playerId)throws RemoteException;
	
	public void setPlayerId(int newId) throws RemoteException;
	
	
	
	
	

}
