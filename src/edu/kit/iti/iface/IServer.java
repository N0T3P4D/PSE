package edu.kit.iti.pse.iface;

/**
 * <p>Schnittstellendefinition für einen minimalen Mxnxpxly-Server.
 * Diese setzt voraus, dass sich Server und Clients über den Spielaufbau
 * (insbesondere Struktur des Brettes) einig sind.
 * Außerdem ist (mit Absicht) keinerlei Sicherheitsvorkehrungen vorgegeben,
 * d.h. ein Spieler könnte für einen anderen handeln.</p>
 * <p>Spieler und Grundstücke werden nur durch numerische Identifikatoren beschrieben.
 * Spielernummern liegen im Bereich 0 bis n-1 wo n die Anzahl der Teilnehmenden ist.
 * Wenn nötig, wird die Bank durch den Wert -1 repräsentiert.</p>
 * <p>Grundstücke sind aufsteigend in der Reihenfolgen auf dem Brett angeordnet,
 * beginnen mit "Los" repräsentiert als 0.</p>
 * <p> Alle "Getter"-Methoden dürfen den internen Zustand des Servers <i>nicht</i> verändern.</p>
 * <p> Alle statusändernden Methoden haben den Rückgabetyp <code>boolean</code>, 
 * der angibt, ob die Operation erfolgreich war.</p>
 * @author bruns
 *
 */
public interface IServer {
    
    // ----
    // Abfragen (nicht-statusändernd)
    // ----
    
    /**
     * Gibt die aktuelle Position des Spielsteins zurück.
     * @param playerID Nummer des Spielers [0,max-1]
     */
    public int getPlayerPiecePosition (int playerID);
    
    
    /**
     * Gibt an, zu welcher Farbgruppe das Grundstück gehört.
     * Nicht-negative Werte stehen dabei für die eigentlichen Farbgruppen
     * in der Reihenfolge auf dem Brett.
     * Negative Werte stehen für Sonderfelder:
     * <ul>
     * <li>-1 für das Startfeld
     * <li>-2 für das Gefängnis
     * <li>-3 für Frei Parken
     * <li>-4 für "gehe in das Gefängnis"
     * <li>-5 für Ereignisfelder
     * <li>-6 für Gemeinschaftsfelder
     * <li>-7 für Bahnhöfe
     * <li>-8 für Infrastrukturgebäude
     * <li>-9 für Sondersteuerfelder
     * <li> weitere können benutzerdefiniert werden
     * </ul>
     */
    public int getEstateColorGroup (int position);
    
    /**
     * Liefert die Zahl der gebauten Häuser an der gegebenen Position.
     * Ein Hotel wird dabei als die Zahl 5 repräsentiert.
     */
    public int getEstateHouses (int position);
    
    /**
     * Liefert den Kaufpreis des Grundstücks an der gegebeben Position.
     */
    public int getEstatePrice (int position);
    
    /**
     * Liefert die Höhe der Miete für ein Grundstück in der angegebenen Bebauung.
     * @param position Die Position des Grundstücks auf dem Brett
     * @param houses Die Anzahl der Häuser, für die die Miete nachgeschlagen werden soll. Ein Hotel wird durch die Zahl 5 repräsentiert.
     */
    public int getEstateRent (int position, int houses);
    
    /**
     * <p>Liefert die aktuelle Spielmeldung für einen Spieler.
     * Dies können entweder reine Statusmeldungen oder für den Spielfluss entscheidende Fragen an den Spieler sein,
     * z.B. ob man die Geldstrafe im Gefängnis zahlt oder etwa ob man ein Grundstück erwerben möchte.
     * In den letzteren Fällen muss dem Server zunächst mit <code>accept()</code> oder <code>decline()</code> geantwortet werden
     * bevor andere Aktionen (z.B. Häuser bauen, Zug beenden) getätigt werden.</p>
     * <p>Da eine solche Textmeldung nur für Menschen verständlich ist,
     * müssen für einen KI-Client andere Methoden implementiert werden,
     * die die wesentliche Information dieser Nachricht übermitteln.</p>
     * @param playerID Spieler, für den die Nachricht vorgesehen ist
     */
    public String getGameStatusMessage (int playerID);
    
    /**
     * Gibt an, ob das gegebene Grundstück mit einer Hypothek belastet ist.
     */
    public boolean isMortgaged (int position);
    
    /**
     * Gibt den Eigentümer des Grundstücks an der gegebenen Position an.
     * Falls das Grundstück der Bank gehört, ist das Ergebnis -1.
     */
    public int getOwner (int position);
    
    /**
     * Liefert den aktuellen Wert der Würfel.
     */
    public int getDiceValue ();
    
    /**
     * Liefert das aktuelle Barvermögen eines Spielers.
     * Ein negativer Wert bedeutet dabei, dass der Spieler bankrott ist und aus dem Spiel ausgeschieden ist.
     */
    public int getPlayerCash (int playerID);
    
    /**
     * Liefert die ID des Spielers, der momentan am Zug ist.
     * Falls das Spiel momentan nicht (mehr) läuft, ist das Ergebnis -1.
     */
    public int getPlayerOnTurn ();
    
    /**
     * Liefert die Zahl der "Du kommst aus dem Gefängnis frei"-Karten eines Spielers.
     */
    public int getNumberOfGetOutOfJailCards (int playerID);
    
    /**
     * Liefert die Zahl der noch in der Bank vorhandenen Häuser.
     */
    public int getNumberOfHousesLeft();
    
    /**
     * Liefert die Zahl der noch in der Bank verbliebenen Hotels.
     */
    public int getNumberOfHotelsLeft();
    
    // ----
    // Spielaktionen (statusändernd)
    // ----
    
    /**
     * Rollt die Würfel und bewegt die Figur (falls sie nicht im Gefängnis ist).
     * Das Ergebnis kann mit den Methoden <code>getDiceValue()</code>, <code>getPlayerPiecePosition()</code> und <code>getGameStatusMessage()</code> erfragt werden.
     * Falls dem Spieler eine für den Spielfluss entscheidende Frage gestellt wird,
     * so muss diese danach mit <code>accept()</code> oder <code>decline()</code> beantwortet werden.
     * Es kann nur einmal am Anfang des Zuges gewürfelt werden (bzw. dreimal im Gefängnis).
     * @param playerID Der handelnde Spieler.
     */
    public boolean rollDice (int playerID);
    
    /**
     * Gibt eine positive Antwort auf eine spielentscheidende Frage des Servers.
     * Kann nur getätigt werden, wenn die aktuelle Statusmeldung eine solche Frage ist.
     * @param playerID Der handelnde Spieler.
     */
    public boolean accept (int playerID);

    /**
     * Gibt eine negative Antwort auf eine spielentscheidende Frage des Servers.
     * Kann nur getätigt werden, wenn die aktuelle Statusmeldung eine solche Frage ist.
     * @param playerID Der handelnde Spieler.
     */
    public boolean decline (int playerID);
    
    /**
     * Beendet den aktuellen Zug.
     * Kann nur getätigt werden, wenn gewürfelt und alle spielentscheidenen Fragen beantwortet wurden.
     * @param playerID Der handelnde Spieler.
     */
    public boolean endTurn(int playerID);
    
    /**
     * Erklärt Bankrott.
     * Setzt das Barvermögen des jeweiligen Spielers auf einen negativen Wert.
     * @param playerID Der handelnde Spieler.
     */
    public boolean declareBankruptcy(int playerID);
    
    /**
     * Baut <i>ein</i> Haus auf das angegebene Grundstück.
     * Kann nur getätigt werden, wenn die Bedingungen dafür erfüllt sind.
     * @param playerID Der handelnde Spieler.
     */
    public boolean construct (int playerID, int position);
    
    /**
     * Löst <i>ein</i> Haus auf dem angegebenen Grundstück auf.
     * Kann nur getätigt werden, wenn die Bedingungen dafür erfüllt sind.
     * @param playerID Der handelnde Spieler.
     */
    public boolean deconstruct (int playerID, int position);
    
    /**
     * Nimmt eine Hypothek auf das angegebene Grundstück auf oder zahlt diese zurück.
     * @param playerID Der handelnde Spieler.
     */
    public boolean toggleMortgage (int playerID, int position);

}