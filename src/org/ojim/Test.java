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

package org.ojim;

import org.ojim.client.gui.GUIClient;
import org.ojim.server.OjimServer;

/**
 * NUR EINE BEISPIELKLASSE!
 * @author xZise
 */
public class Test {
	
	// Possible parameters:
	// -g --gui == GUI Client
	// -s --server == ojim server
	// -a --ai == AI Client
	public static void main(String args[]) {
		
		for (String string : args) {
			if (string.equals("-g") || string.equals("--gui")) {
				System.out.println("Starting guiclient!");
				new GUIClient();
				return;
			} else if (string.equals("-s") || string.equals("--server")) {
				System.out.println("Starting server!");
				new OjimServer("test");
				return;
			} else if (string.equals("-a") || string.equals("--ai")) {
				System.out.println("Starting aiclient!");
//				AIClient a = new AIClient();
				return;
			}
		}
		
		// No usable parameter found
		System.out.println("No parameter for type defined. Starting default.");
		System.out.println("Starting guiclient!");
		new GUIClient();
		return;
	}
}
