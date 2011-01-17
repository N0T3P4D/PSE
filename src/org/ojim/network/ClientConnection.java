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

import java.net.Socket;
import java.nio.Buffer;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;

import edu.kit.iti.pse.iface.IServer;

/**
 * Der Client baut eine aktive Verbindung zum Server aufgebaut
 * 
 * @author Usman Ghani Ahmed
 * 
 */
public class ClientConnection {

	private Socket clientSoket;

	public ClientConnection() {

	}

	public boolean isConnected() {

		return false;

	}

	public IServer connect(String ip, String name) {

		Buffer b1 = null;
		System.setSecurityManager(new RMISecurityManager());
		String url = "rmi://" + ip + "/";
		try {
			b1 = (Buffer) Naming.lookup(url + name);

		} catch (Exception e) {
			System.out.println("Fehler: " + e);
		}

		return (IServer) b1;
	}
}
