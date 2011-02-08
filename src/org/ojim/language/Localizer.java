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

package org.ojim.language;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ojim.log.OJIMLogger;

public class Localizer {
	
	public enum TextKey {
		OJIM("ojim"),
		FILE("file"),
		MISSING_TEXT("missing text"),
		HELP_MENU("?"),
		CREATE_GAME("create game"),
		JOIN_GAME("join game"),
		LEAVE_GAME("leave game"),
		SETTINGS("settings"),
		DIRECT_CONNECTION("direct connection"),
		LIST_OF_SERVERS("list of servers"),
		EXIT("exit"),
		ABOUT("about"),
		HELP("help"),
		ROLL("roll"),
		BUY("buy"),
		READY("ready"),
		SEND_MESSAGE("send"),
		CURRENCY("currency"),
		ENDTURN("endturn"),
		BANKRUPT("bankrupt"),
		LANGUAGES("languages"),
		PLAYER_NAME("player name"),
		ABOUT_TEXT("about text"),
		SAVE("save"),
		START_SERVER("start server"),
		HELP_TEXT("help text"),
		JOIN("join"),
		IP("ip"),
		SERVER_LIST_TEXT("server list text"),
		DICE_VALUES("dice values"),
		FREE_PARKING_CASH("free parking cash"),
		ON("on"),
		OFF("off"),
		PRIVATE_MESSAGE("private"),
		WRONG_INPUT("wrong input"),
		
		// Settings
		WIDTH("width"),
		HEIGHT("height"),
		
		UPGRADE("upgrade"),
		DOWNGRADE("downgrade"),
		
		// Auctions
		START_AUCTION("start auction"),
		MINIMUM_BID("minimum bid"),
		
		AUCTION_BID("bid"),
		
		AUCTION_FIRST("auction first"),
		AUCTION_SECOND("auction second"),
		AUCTION_THIRD("auction third"),
		AUCTION_INIT("auction init"),
		AUCTION_RESET("auction reset"),
		
		// Create server
		SERVER_NAME("server name"),
		MAX_PLAYER("max player"),
		AI_PLAYER("ai player"),
		HOST("host");
		
		public final String key;
		
		TextKey(String key) {
			this.key = key;
		}
		
		public static TextKey getToKey(String key) {
			for (TextKey textKey : TextKey.values()) {
				if (textKey.key.equals(key)) {
					return textKey;
				}
			}
			return null;
		}
	}
	
	/** Saves the translation to a key. */
	private Map<TextKey, String> strings;
	
	public Localizer() {
		this.strings = new HashMap<TextKey, String>();
		LanguageDefinition[] languages = this.getLanguages();
		if (languages.length != 0) {
			this.setLanguage(languages[0]);
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
		if (!lang.exists()) {
			System.out.println("no langs");
		}

		if (lang.isDirectory()) {
			for (File file : lang.listFiles()) {
				// Read file here and add definition
				String author = "Unknown";
				String englishName = "";
				String name = "";
				String code = "";

				try {
					BufferedReader in = new BufferedReader(new FileReader(file));

					String string;
					
					while ((string = in.readLine()) != null) {
						string = string.trim();
						int comment = string.indexOf('#');
						if (comment != 0) {
							if (comment > 0) {
								string = string.substring(0, comment - 1);
							}
							// File definition fields (at least 4 chars: ;x=y)
							if (string.length() > 3 && string.charAt(0) == ';') {
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
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// A file needs at minimum the english name, name and ISO code.
				if (!(englishName.isEmpty() || name.isEmpty() || code.isEmpty())) {
					definitions.add(new LanguageDefinition(author, name, englishName, code, file));
				}
			}
		}

		return definitions.toArray(new LanguageDefinition[0]);
	}

	/**
	 * Loads a language specified by the language definition.
	 * @param definition The language definition.
	 */
	public void setLanguage(LanguageDefinition definition) {
		this.setLanguage(definition.file);
	}
	
	/**
	 * Loads a language specified by the language file.
	 * @param file The language file.
	 */
	public void setLanguage(File file) {
		// Populate the „strings“ object.
		// Clear
		this.strings.clear();
		// Insert correct entries
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));

			String string;

			while ((string = in.readLine()) != null) {
				string = string.trim();
				int comment = string.indexOf('#');
				// The comment isn't the first char
				if (comment != 0 || !string.isEmpty()) {
					// Remove commentary
					if (comment > 0) {
						string = string.substring(0, comment - 1);
					}
	
					// Not a file definition field
					if (!string.isEmpty() && string.charAt(0) != ';') {
						string = string.trim(); // Notwendig?
						int delim = string.indexOf('=');
						if (delim < 1) {
							// TODO: No "=" found/"=" is first char?!
						} else {
							String keyText = string.substring(0, delim).trim();
							TextKey key = TextKey.getToKey(keyText);
							if (key != null) {
								String value = string.substring(delim + 1).trim();
								this.strings.put(key, value);
							} else {
								OJIMLogger.getLogger(this.getClass().getName()).warning("Trying to load with an illegal key (" + keyText + ")");
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found: Language File: " + file.getAbsolutePath());
		} catch (IOException e) {
			System.out.println("I/O: Language File");
		}
	}

	/**
	 * Returns the translation to the key.
	 * @param key the key.
	 * @return the translation to the key.
	 */
	public String getText(TextKey key) {
		String text = this.strings.get(key);
		if (text == null) {
			return key.key;
		} else {
			return text;
		}
	}
	
	/**
	 * Returns the translation to the text key.
	 * @param keyText the text key.
	 * @return the translation to the text key.
	 * @deprecated Use {@link #getText(TextKey)} instead.
	 */
	public String getText(String keyText) {
		TextKey key = TextKey.getToKey(keyText);
		if (key == null) {
			OJIMLogger.getLogger(this.getClass().toString()).warning("unknown key given (" + keyText + ")");
			return keyText;
		} else {
			return this.getText(key);
		}
	}
}
