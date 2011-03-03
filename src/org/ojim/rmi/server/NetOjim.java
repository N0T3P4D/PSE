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
	
	public int getAuctionState () throws RemoteException;

    /**
     * Liefert das zu versteigernde Grundstück falls eine Auktion läuft.
     * @return laufende Nummer des Grundstücks
     */
    public int getAuctionedEstate () throws RemoteException;
    
    /**
     * Liefert die höchste gebotene Summe.
     * Hat noch niemand geboten, so beträgt sie den in den Spielregeln vorgesehenen Standardwert.
     */
    public int getHighestBid () throws RemoteException;
    
    /**
     * Liefert den Bieter der höchsten gebotenen Summe.
     * @return ID des Spielers; -1 falls noch niemand geboten hat
     */
    public int getBidder() throws RemoteException;
    
    /**
     * Gibt ein Gebot in einer Versteigerung ab.
     * @param playerID der bietende Spieler
     * @param amount der gebotene Geldbetrag
     */
    public boolean placeBid (int playerID, int amount) throws RemoteException;
    
    
    /**
     * Eröffnet eine Handelssitzung mit einem weiteren Spieler.
     * Angebotene Handelsware kann mit <code>offerX()</code> hinzugefügt werden.
     * Erwartete Handelsware kann mit <code>requireX()</code> hinzugefügt werden.
     * Der Handelsvorschlag wird mit <code>proposeTrade()</code> oder <code>cancelTrade()</code> abgeschlossen.
     * Weitere Aktionen können während dieser Zeit nicht durchgeführt werden.
     * @param actingPlayer Der handelnde Spieler.
     * @param partnerPlayer Der Handelspartner (-1 für die Bank).
     */
    public boolean initTrade (int actingPlayer, int partnerPlayer) throws RemoteException;
    
    /**
     * Gibt den Zustand einer Handelssitzung an.
     * @return -1 falls keine Handelssitzung läuft<br>
     *          0 falls eine Handelssitzung läuft und der handelnde Spieler noch kein Angebot abgegeben hat<br>
     *          1 falls eine Handelssitzung läuft, der handelnde Spieler ein Angebot abgegeben hat,
     *          der Handelspartner aber noch keine Entscheidung getroffen hat<br>
     *          2 falls gerade eine Handelssitzung mit negativem Ergebnis beendet wurde <i>(optional)</i>,
     *          in diesem Zustand müssen noch alle Daten des Angebots abrufbar sein<br>
     *          3 falls gerade eine Handelssitzung mit positivem Ergebnis beendet wurde <i>(optional)</i>,
     *          in diesem Zustand müssen noch alle Daten des Angebots abrufbar sein
     */
    public int getTradeState() throws RemoteException;
    
    /**
     * Liefert den Handelspartner.
     */
    public int getPartner () throws RemoteException;
    
    /**
     * Bietet Geld im Handel.
     * Die Bank nimmt kein Geld an.
     * @param playerID Der handelnde Spieler
     * @param amount Höhe des Geldbetrags
     */
    public boolean offerCash (int playerID, int amount) throws RemoteException;
    
    /**
     * Bietet eine "Du kommst aus dem Gefängnis frei"-Karte im Handel an.
     * Die Bank nimmt keine Karten an.
     * @param playerID Der handelnde Spieler
     */
    public boolean offerGetOutOfJailCard (int playerID) throws RemoteException;
    
    /**
     * Bietet ein Grundstück im Handel an.
     * Falls der Handel mit der Bank stattfindet, wird automatisch der entsprechende Geldwert auf die Soll-Seite gesetzt.
     * @param playerID Der handelnde Spieler
     * @param position Position des Grundstücks
     */
    public boolean offerEstate (int playerID, int position) throws RemoteException;
    
    /**
     * Verlangt Geld im Handel.
     * Von der Bank wird automatisch Geld verlangt.
     * @param playerID Der handelnde Spieler
     * @param amount Höhe des Geldbetrags
     */
    public boolean requireCash (int playerID, int amount) throws RemoteException;
    
    /**
     * Verlangt eine "Du kommst aus dem Gefängnis frei"-Karte im Handel.
     * Muss sich im Besitz des Handelspartners befinden.
     */
    public boolean requireGetOutOfJailCard (int playerID) throws RemoteException;
    
    /**
     * Verlangt ein Grundstück im Handel.
     * Muss sich im Besitz des Handelspartners befinden.
     * @param playerID Der handelnde Spieler
     * @param position Position des Grundstücks
     */
    public boolean requireEstate (int playerID, int position) throws RemoteException;
    
    /**
     * Liefert eine Liste der vom handelnden Spieler angebotenen Grundstücke.
     */
    public int[] getOfferedEstates() throws RemoteException;
    
    /**
     * Liefert das vom handelnden Spieler angebotene Geld.
     */
    public int getOfferedCash () throws RemoteException;
    
    /**
     * Liefert die Zahl der vom handelnden Spieler angebotenen "Gefängnis frei"-Karten.
     */
    public int getNumberOfOfferedGetOutOfJailCards () throws RemoteException;
    
    /**
     * Liefert eine Liste der vom handelnden Spieler verlangten Grundstücke.
     */
    public int[] getRequiredEstates () throws RemoteException;

    /**
     * Liefert das vom handelnden Spieler verlangte Geld.
     */
    public int getRequiredCash() throws RemoteException;

    /**
     * Liefert die Zahl der vom handelnden Spieler verlangten "Gefängnis frei"-Karten.
     */
    public int getNumberOfRequiredGetOutOfJailCards() throws RemoteException;
    
    /**
     * Bricht die aktuelle Handelssitzung ohne Ergebnis ab.
     */
    public boolean cancelTrade (int playerID) throws RemoteException;
    
    /**
     * Unterbreitet dem Handelspartner einen Handelsvorschlag.
     * Handelswaren wurden vorher mit <code>offerX()</code> bzw. <code>requireX()</code> hinzugefügt.
     * Falls der Handelspartner nicht die Bank ist,
     * wird er daraufhin gefragt, ob er dem Handel zustimmt.
     * Das Ergebnis des Handels kann dann wieder mit <code>getGameStatusMessage()</code> erfragt werden.
     */
    public boolean proposeTrade (int playerID) throws RemoteException;
	
	 

	
	
	
	
}
