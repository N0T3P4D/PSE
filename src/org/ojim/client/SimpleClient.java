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
import org.ojim.logic.state.BuyableField;
import org.ojim.logic.state.GameState;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.Street;

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
	private Player me;

	private int playerId;

	public SimpleClient() {

	}

	public SimpleClient(Logic logic, int playerId, IServer server) {
		this.setParameters(logic, playerId, server);
		this.me = this.getGameState().getPlayerByID(playerId);
	}

	protected Logic getLogic() {
		return this.logic;
	}

	protected GameState getGameState() {
		return this.logic.getGameState();
	}

	protected GameRules getGameRules() {
		return this.logic.getGameRules();
	}

	protected void setParameters(Logic logic, int playerId, IServer server) {
		this.logic = logic;
		this.playerId = playerId;
		this.server = server;
	}

	public final int getPlayerId() {
		return this.playerId;
	}

	/*
	 * GETTER OF ISERVER
	 */

	public int getEstateColorGroup(int position) {
		return this.server.getEstateColorGroup(position);
	}

	public String getEstateName(int position) {
		return this.server.getEstateName(position);
	}

	public int getEstatePrice(int position) {
		return this.server.getEstatePrice(position);
	}

	public int getEstateRent(int position, int houses) {
		return this.server.getEstateRent(position, houses);
	}

	/*
	 * ADITIONAL GETTER
	 */

	public int getMaximumBuiltLevel() {
		return 6; // TODO: Implement higher level
	}

	/**
	 * Returns the money the player has to pay to leave the jail.
	 * 
	 * @param position
	 *            The position of the jail.
	 * @return The money the player has to pay. If there is no money it returns
	 *         -1.
	 */
	public int getMoneyToPay(int position) {
		return 1000;
	}

	/**
	 * Returns the number of rounds the player has to wait if the player is in
	 * jail.
	 * 
	 * @param position
	 *            The position of the jail.
	 * @return The number of rounds the player has to wait. If this is no jail
	 *         it returns -1.
	 */
	public int getRoundsToWait(int position) {
		return 3;
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
		if (this.getGameRules().isPlayerOnTurn(this.me)) {
			this.server.rollDice(this.playerId);
		}
	}

	protected final void endTurn() {
		if (this.getGameRules().isPlayerOnTurn(this.me)) {
			this.server.endTurn(this.playerId);
		}
	}

	protected final void declareBankruptcy() {
		this.server.declareBankruptcy(this.playerId);
	}

	protected final void construct(Street street) {
		if (this.getGameRules().isFieldUpgradable(this.me, street, +1)) {
			this.server.construct(this.playerId, street.getPosition());
		}
	}

	protected final void destruct(Street street) {
		if (this.getGameRules().isFieldUpgradable(this.me, street, -1)) {
			this.server.deconstruct(this.playerId, street.getPosition());
		}
	}

	protected final void toggleMortage(BuyableField street) {
		if (this.getGameRules().isFieldMortgageable(this.me, street)) {
			this.server.toggleMortgage(this.playerId, street.getPosition());
		}
	}

	protected final void sendMessage(String text) {
		this.server.sendMessage(text);
	}

	protected final void sendPrivateMessage(String text, int reciever) {
		this.server.sendPrivateMessage(text, reciever);
	}
}
