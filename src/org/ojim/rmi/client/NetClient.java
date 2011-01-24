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

package org.ojim.rmi.client;

import java.rmi.Remote;

import org.ojim.iface.IClient;
/**
 * 
 * Interface stellt zusätzlich zum IClient Interface Methoden bereit 
 * die über das Netzwerk mit Hilfe von RMI, lokal oder über das Internet 
 * vom Server auf einer anderen JVM, in diesem Fall auf unserem Client aufgerufen werden können.  
 * 
 * 
 * @author Usman Ghani Ahmed
 *
 */
public interface NetClient extends Remote,IClient {
	
	

}
