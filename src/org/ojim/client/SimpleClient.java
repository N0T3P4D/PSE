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

		// xZise: Bäääääääääh igittigittigit
		new GameRules(this.logic.getGameState());
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
