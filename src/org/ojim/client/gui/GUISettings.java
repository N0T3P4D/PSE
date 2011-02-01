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
package org.ojim.client.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class GUISettings {

	private int width;
	private int height;
	private String title;
	private String playerName;
	private String lastIP;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getLastIP() {
		return lastIP;
	}

	public void setLastIP(String lastIP) {
		this.lastIP = lastIP;
	}
	
	/**
	 * Speichert die neuen GUI Einstellungen in der settings.txt
	 */
	public void saveSettings(){
		
		 FileWriter fw = null;
			try {
				fw = new FileWriter("settings.txt");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		    BufferedWriter bw = new BufferedWriter(fw);

		    try {
				bw.write(String.valueOf(this.width)); 
				bw.newLine();
				bw.write(String.valueOf(this.height));
				bw.newLine();
				bw.write(this.title);
				bw.newLine();
				bw.write(this.playerName);
				bw.newLine();
				bw.write(this.lastIP);
				bw.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
		
	}
	
	
	/**
	 * Lädt zuvor gespeicherte GUI Einstellungen, es muss eine settings.txt Datei vorhanden sein
	 * 
	 */
	public void loadSettings(){
		
		FileReader reader = null;
		try {
		reader = new FileReader("settings.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Datei kann nicht gefunden werden");
			//e.printStackTrace();
		}
		
		BufferedReader br = new BufferedReader(reader);
		
		    String width = null;
		    String height = null;
		    String title = null;
		    String playerName = null;
		    String lastIP = null;
		    
			try {
				width = br.readLine();
				height = br.readLine();
				title = br.readLine();
				playerName = br.readLine();
				lastIP = br.readLine();
				br.close();
			} catch (IOException e) {
				System.out.print("Fehler in der settings.txt");
				//e.printStackTrace();
			}
		    
			this.width = Integer.parseInt (width);
			this.height = Integer.parseInt (height);
			this.title = title;
			this.playerName = playerName;
			this.lastIP = lastIP;
			
			
	}
	
	

}
