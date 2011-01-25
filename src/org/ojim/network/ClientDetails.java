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

package org.ojim.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Klasse verwaltet Client spezifische Informationen
 * 
 * @author Usman Ghani Ahmed
 *
 */
public class ClientDetails {
	
	
	private String ip;
	
	private String username;
	
	public ClientDetails(String ip,String username){
		this.ip = ip;
		this.username = username;
	}
	
	/**
	 * Ermittelt die eigene öffentliche ip Adresse
	 * 
	 * @return öffentliche ip Adresse
	 */
	public String getIp(){
		
		
		try{
	    URL url = new URL("http://www.whatismyip.com/automation/n09230945.asp");
	    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	    connection.setReadTimeout(2000);
	    InputStream InStream = connection.getInputStream();
	    InputStreamReader Isr = new InputStreamReader(InStream);
	    BufferedReader Br = new BufferedReader(Isr);
	    this.ip = Br.readLine();
		
		} catch(Exception e){
			e.printStackTrace();
		}
	
		 return this.ip;
		
	}
	
	/**
	 * Gibt den Spielernamen zurück
	 * @return Spielername
	 */
	public String getUsername(){
		return this.username;
	}
}
