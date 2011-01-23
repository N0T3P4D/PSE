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

import java.lang.reflect.InvocationTargetException;

import org.ojim.client.ai.AIClient;
import org.ojim.client.gui.GUIClient;
import org.ojim.client.gui.GameField.fielddrawer.FieldDrawer;
import org.ojim.logic.actions.Action;
import org.ojim.logic.actions.ActionMoveToField;
import org.ojim.logic.state.Card;
import org.ojim.logic.state.GameState;
import org.ojim.logic.state.fields.Field;
import org.ojim.logic.state.fields.Jail;
import org.ojim.server.OjimServer;

/**
 * NUR EINE BEISPIELKLASSE!
 * @author xZise
 */
public class Test {
	
	private Card[] cards;
	
	// Possible parameters:
	// -g --gui == GUI Client
	// -s --server == ojim server
	// -a --ai == AI Client
	public static void main(String args[]) {
		
		FieldDrawer d = FieldDrawer.getDrawer(new Jail("foo", 2, 1, 2));
		d.drawTo(null);
		
		for (String string : args) {
			if (string.equals("-g") || string.equals("--gui")) {
				System.out.println("Starting guiclient!");
				GUIClient c = new GUIClient();
				return;
			} else if (string.equals("-s") || string.equals("--server")) {
				System.out.println("Starting server!");
				OjimServer s = new OjimServer("test");
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
		GUIClient c = new GUIClient();
		return;
	}
}
