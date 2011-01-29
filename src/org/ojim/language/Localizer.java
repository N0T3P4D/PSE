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

public class Localizer {

	private static final String[] TEXT_KEYS = { "ojim", "file", "missing text", "?", "create game", "join game",
			"leave game", "settings", "direct connection", "list of servers", "exit", "about", "help", "roll", "buy",
			"ready", "send", "currency" };

	/** Saves the translation to a key. */
	private Map<String, String> strings;

	public Localizer() {
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

				if (!(englishName.isEmpty() || name.isEmpty() || code.isEmpty())) {
					definitions.add(new LanguageDefinition(author, name, englishName, code, file));
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
		try {
			BufferedReader in = new BufferedReader(new FileReader(definition.file));

			String string;

			while ((string = in.readLine()) != null) {
				string = string.trim();
				int comment = string.indexOf('#');
				if (comment == 0 || string.length() == 0) {
					continue;
				}
				if (comment > 0) {
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
					string = string.trim(); // Notwendig?
					int delim = string.indexOf('=');
					if (delim < 1) {
						// TODO: No "=" found/"=" is first char?!
					} else {
						String key = string.substring(0, delim).trim();
						String value = string.substring(delim + 1).trim();
						this.strings.put(key, value);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found: Language File: " + definition.file.getAbsolutePath());
		} catch (IOException e) {
			System.out.println("I/O: Language File");
		}
	}

	// Newer version (replace example)
	public String getText(String key) {
		String text = this.strings.get(key);
		if (text == null) {
			for (String string : Localizer.TEXT_KEYS) {
				if (string.equals(key)) {
					// Maybe get translation?
					return "<html>"+key;
				}
			}
			// Maybe throw exception
			return key;
		} else {
			return text;
		}
	}
}
