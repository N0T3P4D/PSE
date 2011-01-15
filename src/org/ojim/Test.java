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

import org.ojim.client.ai.AIClient;
import org.ojim.client.gui.GUIClient;
import org.ojim.logic.actions.Action;
import org.ojim.logic.actions.ActionMoveToField;
import org.ojim.logic.state.Card;
import org.ojim.logic.state.Field;
import org.ojim.logic.state.GameState;
import org.ojim.server.OjimServer;

/**
 * NUR EINE BEISPIELKLASSE!
 * @author xZise
 */
public class Test {
	
	private Card[] cards;
	
	private Test() {
		
		GUIClient gc = new GUIClient();
		
		GameState state = new GameState();
		
		this.cards = new Card[10];
		for (int i = 0; i < this.cards.length; i++) {  /* Hier kann eine andere Aktion hin, oder mehrere hintereinander */
			this.cards[i] = new Card("Beispielkarte #" + i, state, false, new ActionMoveToField(state, false, new Field(state, "foo", (int) (Math.random() * 40), new Action[0], new Action[0])));
		}
	}
	
	// Possible parameters:
	// -g --gui == GUI Client
	// -s --server == ojim server
	// -a --ai == AI Client
	public static void main(String args[]) {
		
		String param = "-g";
		for (String string : args) {
			if (args.equals("-g") || args.equals("--gui")) {
				System.out.println("Starting guiclient!");
				GUIClient c = new GUIClient();
				return;
			} else if (args.equals("-s") || args.equals("--server")) {
				System.out.println("Starting server!");
				OjimServer s = new OjimServer("test");
				return;
			} else if (args.equals("-a") || args.equals("--ai")) {
				System.out.println("Starting aiclient!");
				AIClient a = new AIClient();
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
