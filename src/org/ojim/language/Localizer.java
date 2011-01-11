package org.ojim.language;

public class Localizer {

	int language;

	private static final String[] LANGUAGE = { 
		// Deutschland Deutsch
		"DE-de", 
		// Vereinigtes Königreich Englisch
		"EN-uk", 
		// Frankreich Französisch
		"FR-fr" };

	private static final String[][] LANGUAGE_FILES = 
	{
			{ "Fehlender Text", "Missing Text", " Erreur" },
			{ "Datei", "File", " Fichier" } ,
			{ "?", "?", " ?" } ,
			{ "Spiel erstellen", "Create Game"} ,
			{ "Spiel beitreten", "Join Game" } ,
			{ "Spiel verlassen", "Leave Game" } ,
			{ "Einstellungen", "Settings" } ,
			{ "Direkte Verbindung", "Direct Connection" } ,
			{ "Serverliste", "Serverlist" } ,
			{ "Beenden", "Exit"} ,
			{ "Über Ojim", "About Ojim" } ,
			{ "Hilfe", "Help"}
	};

	public Localizer() {
		language = 0;
		// TODO: Aus Textdatei LANGUAGE_FILES auslesen
	}

	public void setLanguage(String language) {

		for (int i = 0; i < LANGUAGE.length; i++) {
			if (language.equals(LANGUAGE[i])) {
				this.language = i;
			}
		}

	}

	public String getText(String textRequest) {
		for (int i = 0; i < LANGUAGE_FILES.length; i++) {
			if (LANGUAGE_FILES[i][0].equals(textRequest)) {
				try {
					return LANGUAGE_FILES[i][this.language];
				} catch (Exception e) {
					return LANGUAGE_FILES[0][this.language];
				}
			}
		}

		return LANGUAGE_FILES[0][this.language];

	}

}
