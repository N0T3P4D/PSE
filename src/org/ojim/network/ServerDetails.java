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

public class ServerDetails {
	
	private String name;
	private String ip;
	private int connectedPlayers;
	private int maxPlayers;
	private boolean open;
	private int portReg;
	private int portStub;
	
	public ServerDetails(String name,String ip,int connectedPlayers,int maxPlayers,boolean open,
			int portReg, int portStub){
		this.connectedPlayers=connectedPlayers;
		this.name=name;
		this.ip=ip;
		this.maxPlayers=maxPlayers;
		this.open=open;
		this.portReg=portReg;
		this.portStub=portStub;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getIp (){
		return this.ip;
	}
	
	public int getConnectedPlayers(){
		return this.connectedPlayers;
		
	}
	
	public int getMaxPlayers(){
		return this.maxPlayers;
	}
	
	public boolean getOpen(){
		return this.open;
	}
	
	public int getPortReg(){
		return this.portReg;
	}
	
	public int getPortStub(){
		 return this.portStub;
	}

}	
