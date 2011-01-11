package org.ojim.client.gui;

public class GUILocalizer {

	int language;

	private static final String[] languages = { 
		// Deutschland Deutsch
		"DE-de", 
		// Vereinigtes Königreich Englisch
		"EN-uk", 
		// Frankreich Französisch
		"FR-fr" };

	private static final String[][] languageFiles = 
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

	public GUILocalizer() {
		language = 0;
	}

	public void setLanguage(String language) {

		for (int i = 0; i < languages.length; i++) {
			if (language.equals(languages[i])) {
				this.language = i;
			}
		}

	}

	public String getText(String textRequest) {
		for (int i = 0; i < languageFiles.length; i++) {
			if (languageFiles[i][0].equals(textRequest)) {
				try {
					return languageFiles[i][this.language];
				} catch (Exception e) {
					return languageFiles[0][this.language];
				}
			}
		}

		return languageFiles[0][this.language];

	}

}
