package edu.kit.iti.pse.iface;

/**
 * Erweiterte Server-Schnittstelle, die auch Auktionen beinhaltet.
 * Auktionen können nicht von den Spielern selbst gestartet werden,
 * sondern finden automatisch statt wenn ein Grundstück nicht verkauft wurde.
 * @author bruns
 *
 */
public interface IServerAuction extends IServer {
    
    /**
     * Gibt den Stand der Auktion an.
     * @return -1 falls keine Auktion läuft<br>
     *          0 falls eine Auktion läuft und noch nicht ausgezählt wird<br>
     *          1 falls eine Auktion läuft und "zum Ersten" angekündigt wurde<br>
     *          2 falls eine Auktion läuft und "zum Zweiten" angekündigt wurde<br>
     *          3 falls eine Auktion gerade abgeschlossen wurde <i>(optional)</i>,
     *          in diesem Zustand müssen noch alle Daten abrufbar sein
     */
    public int getAuctionState ();

    /**
     * Liefert das zu versteigernde Grundstück falls eine Auktion läuft.
     * @return laufende Nummer des Grundstücks
     */
    public int getAuctionedEstate ();
    
    /**
     * Liefert die höchste gebotene Summe.
     * Hat noch niemand geboten, so beträgt sie den in den Spielregeln vorgesehenen Standardwert.
     */
    public int getHighestBid ();
    
    /**
     * Liefert den Bieter der höchsten gebotenen Summe.
     * @return ID des Spielers; -1 falls noch niemand geboten hat
     */
    public int getBidder();
    
    /**
     * Gibt ein Gebot in einer Versteigerung ab.
     * @param playerID der bietende Spieler
     * @param amount der gebotene Geldbetrag
     */
    public boolean placeBid (int playerID, int amount);
}