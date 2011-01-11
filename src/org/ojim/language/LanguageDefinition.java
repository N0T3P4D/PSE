package org.ojim.language;

import java.io.File;

public class LanguageDefinition {
	public final String author;
	public final String name;
	public final String englishName;
	public final String code;
	public final File file;
	
	public LanguageDefinition(String author, String name, String englishName, String code, File file) {
		this.author = author;
		this.name = name;
		this.englishName = englishName;
		this.code = code;
		this.file = file;
	}
}
