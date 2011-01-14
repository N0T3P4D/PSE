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

package org.ojim.client;

import org.ojim.logic.Logic;
import org.ojim.logic.rules.GameRules;

import edu.kit.iti.pse.iface.IServer;

/**
 * Simpler client Implementation. This client only wraps the methods of IServer
 * and checks it with the rules.
 * 
 * But no “Connection” and “IClient” support.
 * 
 * @author Fabian Neundorf
 */
public class SimpleClient {

	private IServer server;
	private Logic logic;

	private int playerId;

	public SimpleClient(Logic logic) {
		this.logic = logic;
	}

	public SimpleClient(Logic logic, int playerId, IServer server) {
		this(logic);
		this.setParameters(playerId, server);
	}
	
	protected Logic getLogic() {
		return logic;
	}
	
	//xZise: Buffer method call → In future use the logic!
	private boolean isMyTurn() {
		return true;
	}

	protected void setParameters(int playerId, IServer server) {
		this.playerId = playerId;
		this.server = server;
	}
	
	public final int getPlayerId() {
		return this.playerId;
	}
	
	/*
	 * ACTION METHODS
	 */

	/**
	 * Setzt diesen Spieler auf „bereit“.
	 */
	protected final void ready() {
		this.server.setPlayerReady(this.playerId);
	}

	/**
	 * Akzeptiert die Nachricht.
	 * 
	 * @see {@link edu.kit.iti.pse.iface.IServer.accept}
	 */
	protected final void accept() {

		this.server.accept(this.playerId);
	}

	protected final void decline() {
		this.server.decline(this.playerId);
	}

	protected final void rollDice() {
		if (this.isMyTurn()) {
			this.server.rollDice(this.playerId);
		}
	}

	protected final void endTurn() {
		if (this.isMyTurn()) {
			this.server.endTurn(this.playerId);
		}
	}

	protected final void declareBankruptcy() {
		this.server.declareBankruptcy(this.playerId);
	}

	protected final void construct(int street) {
		if (this.isMyTurn()) {
			this.server.construct(this.playerId, street);
		}
	}

	protected final void destruct(int street) {
		if (this.isMyTurn()) {
			this.server.deconstruct(this.playerId, street);
		}
	}

	protected final void toggleMortage(int street) {
		if (this.isMyTurn()) {
			this.server.toggleMortgage(this.playerId, street);
		}
	}

	protected final void sendMessage(String text) {
		this.server.sendMessage(text);
	}

	protected final void sendPrivateMessage(String text, int reciever) {
		this.server.sendPrivateMessage(text, reciever);
	}
}
