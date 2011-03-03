/*  Copyright (C) 2010  Karlsruhe Institute of Technology
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

package edu.kit.iti.pse.iface;

/**
 * Erweiterte Server-Schnittstelle, die auch Handel zwischen Spieler erlaubt.
 * @author bruns
 *
 */
public interface IServerTrade extends IServer {

    /**
     * Eröffnet eine Handelssitzung mit einem weiteren Spieler.
     * Angebotene Handelsware kann mit <code>offerX()</code> hinzugefügt werden.
     * Erwartete Handelsware kann mit <code>requireX()</code> hinzugefügt werden.
     * Der Handelsvorschlag wird mit <code>proposeTrade()</code> oder <code>cancelTrade()</code> abgeschlossen.
     * Weitere Aktionen können während dieser Zeit nicht durchgeführt werden.
     * @param actingPlayer Der handelnde Spieler.
     * @param partnerPlayer Der Handelspartner (-1 für die Bank).
     */
    public boolean initTrade (int actingPlayer, int partnerPlayer);
    
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
    public int getTradeState();
    
    /**
     * Liefert den Handelspartner.
     */
    public int getPartner();

    /**
     * Liefert den Initiator des Handels.
     * @return Die Id des Initiators.
     * @since SVN Revision 17.
     */
    public int getActing();
   
    /**
     * Bietet Geld im Handel.
     * Die Bank nimmt kein Geld an.
     * @param playerID Der handelnde Spieler
     * @param amount Höhe des Geldbetrags
     */
    public boolean offerCash (int playerID, int amount);
    
    /**
     * Bietet eine "Du kommst aus dem Gefängnis frei"-Karte im Handel an.
     * Die Bank nimmt keine Karten an.
     * @param playerID Der handelnde Spieler
     */
    public boolean offerGetOutOfJailCard (int playerID);
    
    /**
     * Bietet ein Grundstück im Handel an.
     * Falls der Handel mit der Bank stattfindet, wird automatisch der entsprechende Geldwert auf die Soll-Seite gesetzt.
     * @param playerID Der handelnde Spieler
     * @param position Position des Grundstücks
     */
    public boolean offerEstate (int playerID, int position);
    
    /**
     * Verlangt Geld im Handel.
     * Von der Bank wird automatisch Geld verlangt.
     * @param playerID Der handelnde Spieler
     * @param amount Höhe des Geldbetrags
     */
    public boolean requireCash (int playerID, int amount);
    
    /**
     * Verlangt eine "Du kommst aus dem Gefängnis frei"-Karte im Handel.
     * Muss sich im Besitz des Handelspartners befinden.
     */
    public boolean requireGetOutOfJailCard (int playerID);
    
    /**
     * Verlangt ein Grundstück im Handel.
     * Muss sich im Besitz des Handelspartners befinden.
     * @param playerID Der handelnde Spieler
     * @param position Position des Grundstücks
     */
    public boolean requireEstate (int playerID, int position);
    
    /**
     * Liefert eine Liste der vom handelnden Spieler angebotenen Grundstücke.
     */
    public int[] getOfferedEstates();
    
    /**
     * Liefert das vom handelnden Spieler angebotene Geld.
     */
    public int getOfferedCash ();
    
    /**
     * Liefert die Zahl der vom handelnden Spieler angebotenen "Gefängnis frei"-Karten.
     */
    public int getNumberOfOfferedGetOutOfJailCards ();
    
    /**
     * Liefert eine Liste der vom handelnden Spieler verlangten Grundstücke.
     */
    public int[] getRequiredEstates ();

    /**
     * Liefert das vom handelnden Spieler verlangte Geld.
     */
    public int getRequiredCash();

    /**
     * Liefert die Zahl der vom handelnden Spieler verlangten "Gefängnis frei"-Karten.
     */
    public int getNumberOfRequiredGetOutOfJailCards();
    
    /**
     * Bricht die aktuelle Handelssitzung ohne Ergebnis ab.
     */
    public boolean cancelTrade (int playerID);
    
    /**
     * Unterbreitet dem Handelspartner einen Handelsvorschlag.
     * Handelswaren wurden vorher mit <code>offerX()</code> bzw. <code>requireX()</code> hinzugefügt.
     * Falls der Handelspartner nicht die Bank ist,
     * wird er daraufhin gefragt, ob er dem Handel zustimmt.
     * Das Ergebnis des Handels kann dann wieder mit <code>getGameStatusMessage()</code> erfragt werden.
     */
    public boolean proposeTrade (int playerID);
}
