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

package org.ojim.client.triggers;

import org.ojim.client.ClientBase;
import org.ojim.logic.state.Player;

public class OnMessage implements Runnable {

	private final ClientBase base;
	private final String text;
	private final Player sender;
	private final boolean privateMessage;
	
	public OnMessage(ClientBase base, String text, Player sender, boolean privateMessage) {
		this.base = base;
		this.text = text;
		this.sender = sender;
		this.privateMessage = privateMessage;
	}
	
	@Override
	public void run() {
		this.base.onMessage(text, sender, privateMessage);
	}

}
