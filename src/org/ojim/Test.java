package org.ojim;

import org.ojim.client.ai.AIClient;
import org.ojim.client.gui.GUIClient;
import org.ojim.logic.rules.ActionMoveToField;
import org.ojim.logic.rules.Card;
import org.ojim.logic.state.Field;
import org.ojim.logic.state.GameState;
import org.ojim.server.OjimServer;

/**
 * NUR EINE BEISPIELKLASSE!
 * @author xZise
 */
public class Test {
	
	private Card[] cards;
	
	private static final int foo = 8;
	
	private Test() {
		
		GUIClient gc = new GUIClient();
		
		GameState state = new GameState(foo);
		
		this.cards = new Card[10];
		for (int i = 0; i < this.cards.length; i++) {  /* Hier kann eine andere Aktion hin, oder mehrere hintereinander */
			this.cards[i] = new Card("Beispielkarte #" + i, state, false, new ActionMoveToField(state, false, new Field(state)));
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
	}
}
