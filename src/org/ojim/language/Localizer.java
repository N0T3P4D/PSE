package org.ojim.language;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Localizer {

	int language;

	// private static final String[] LANGUAGE = {
	// // Deutschland Deutsch
	// "DE-de",
	// // Vereinigtes Königreich Englisch
	// "EN-uk",
	// // Frankreich Französisch
	// "FR-fr" };
	// xZise: The Language is encoded as ISO 639-3
	// See: https://secure.wikimedia.org/wikipedia/en/wiki/ISO_639-3
	private static final String[] LANGUAGE = {
	// Deutschland Deutsch
			"deu",
			// Vereinigtes Königreich Englisch
			"eng",
			// Frankreich Französisch
			"fra" };

	private static final String[][] LANGUAGE_FILES = {
			{ "Fehlender Text", "Missing Text", " Erreur" },
			{ "Datei", "File", " Fichier" }, { "?", "?", " ?" },
			{ "Spiel erstellen", "Create Game" },
			{ "Spiel beitreten", "Join Game" },
			{ "Spiel verlassen", "Leave Game" },
			{ "Einstellungen", "Settings" },
			{ "Direkte Verbindung", "Direct Connection" },
			{ "Serverliste", "Serverlist" }, { "Beenden", "Exit" },
			{ "Über Ojim", "About Ojim" }, { "Hilfe", "Help" } };

	private static final String[] TEXT_KEYS = { "file", "missing text", "?",
			"create game", "join game", "leave game", "settings",
			"direct connection", "serverlist", "exit", "about", "help", };

	/** Saves the translation to a key. */
	private Map<String, String> strings;

	public Localizer() {
		language = 0;
		// TODO: Aus Textdatei LANGUAGE_FILES auslesen

		this.strings = new HashMap<String, String>();
		// Initalize the map:
		for (String string : Localizer.TEXT_KEYS) {
			this.strings.put(string, string);
		}
	}

	/**
	 * Reads a directory and returns all language files
	 * 
	 * @return
	 */
	public LanguageDefinition[] getLanguages() {
		List<LanguageDefinition> definitions = new ArrayList<LanguageDefinition>();

		// Read directory here
		File lang = new File("langs");
		if (lang.isDirectory()) {
			for (File file : lang.listFiles()) {
				// Read file here and add definition
				String author = "Unknown";
				String englishName = "";
				String name = "";
				String code = "";

				List<String> lines = new ArrayList<String>();
				for (String string : lines) {
					string = string.trim();
					int comment = string.indexOf('#');
					if (comment >= 0) {
						string = string.substring(0, comment - 1);
					}
					// File definition fields
					if (string.charAt(0) == ';') {
						// Cuts off the ";" and any leaving beginning/ending
						// spaces
						String[] values = string.substring(1).trim().split("=");
						if (values.length == 2) {
							if (values[0].equals("author")) {
								author = values[1];
							} else if (values[0].equals("english name")) {
								englishName = values[1];
							} else if (values[0].equals("name")) {
								name = values[1];
							} else if (values[0].equals("code")) {
								code = values[1];
							}
						}
					}
				}
				if (!(englishName.isEmpty() || name.isEmpty() || code.isEmpty())) {
					definitions.add(new LanguageDefinition(author, name,
							englishName, code, file));
				}
			}
		}

		return definitions.toArray(new LanguageDefinition[0]);
	}

	public void setLanguage(LanguageDefinition definition) {
		// Populate the „strings“ object.
		// Clear
		this.strings.clear();
		// Insert correct entries
		// TODO: Read file
		/* load file here and read everyline in there: */
		List<String> lines = new ArrayList<String>();
		for (String string : lines) {
			string = string.trim();
			int comment = string.indexOf('#');
			if (comment >= 0) {
				string = string.substring(0, comment - 1);
			}
			// File definition fields
			if (string.charAt(0) == ';') {
				// Cuts off the ";" and any leaving beginning/ending spaces
				string = string.substring(1).trim();
				if (string.equals("author")) {

				} else if (string.equals("english language")) {

				} else if (string.equals("language")) {

				}
			} else {
				String[] values = string.trim().split("=");
				// TODO: Test if key is in Text Keys (?)
				if (values.length == 2) {
					this.strings.put(values[0], values[1]);
				}
			}
		}
	}

	public void setLanguage(String language) {

		for (int i = 0; i < LANGUAGE.length; i++) {
			if (language.equals(LANGUAGE[i])) {
				this.language = i;
			}
		}
	}

	// Newer version (replace example)
	public String getText2(String key) {
		String text = this.strings.get(key);
		if (text == null) {
			for (String string : Localizer.TEXT_KEYS) {
				if (string.equals(key)) {
					// Maybe get translation?
					return key;
				}
			}
			// Maybe throw exception
			return null;
		} else {
			return text;
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
