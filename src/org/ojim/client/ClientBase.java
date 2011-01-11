package org.ojim.client;

import org.ojim.iface.IClient;
import org.ojim.iface.Rules;
import org.ojim.logic.Logic;
import org.ojim.network.ClientConnection;

import edu.kit.iti.pse.iface.IServer;

/**
 * Basis Client für den GUIClient und AIClient.
 * 
 * @author Fabian Neundorf
 */
public class ClientBase implements IClient {
	
	private IServer server;
	private Rules rules;
	private Logic logic;
	
	private ClientConnection connection;
	
	private int playerID;
	//TODO: Wie kommt man daran?
	private String name;
	
	public ClientBase() {
		this.connection = new ClientConnection();
		this.logic = new Logic();
	}
	
	public final int getPlayerID() {
		return this.playerID;
	}
	
	/*
	 * MISC
	 */
	
	private void setServer(IServer server) {
		this.server = server;
		
		if (this.server != null) {
			this.rules = this.server.getRules();
			this.playerID = this.server.addPlayer(this);
		}
	}
	
	/**
	 * Returns true if this player is on turn.
	 * @return true if this player is on turn.
	 */
	protected final boolean isMyTurn() {
		// Better comparision! Only for test!
		return this.server.getPlayerOnTurn() == this.playerID;
	}
	
	/*
	 * ACTION METHODS
	 */
	
	/**
	 * Setzt diesen Spieler auf „bereit“.
	 */
	protected final void ready() {
		this.server.setPlayerReady(this.playerID);
	}
	
	/**
	 * Akzeptiert die Nachricht.
	 * 
	 * @see {@link edu.kit.iti.pse.iface.IServer.accept}
	 */
	protected final void accept() {
		
		this.server.accept(this.playerID);
	}
	
	protected final void decline() {
		this.server.decline(this.playerID);
	}
	
	protected final void rollDice() {
		if (this.isMyTurn()) {
			this.server.rollDice(this.playerID);
		}
	}
	
	protected final void endTurn() {
		if (this.isMyTurn()) {
			this.server.endTurn(this.playerID);
		}
	}
	
	protected final void declareBankruptcy() {
		this.server.declareBankruptcy(this.playerID);
	}
	
	protected final void construct(int street) {
		if (this.isMyTurn()) {
			this.server.construct(this.playerID, street);
		}
	}
	
	protected final void destruct(int street) {
		if (this.isMyTurn()) {
			this.server.deconstruct(this.playerID, street);
		}
	}
	
	protected final void toggleMortage(int street) {
		if (this.isMyTurn()) {
			this.server.toggleMortgage(this.playerID, street);
		}
	}
	
	protected final boolean connect(String host, int port) {
		this.setServer(this.connection.connect(host, port));
		return this.server != null;
	}
	
	protected final void connect(IServer server) {
		this.setServer(server);
	}
	
	protected final void sendMessage(String text) {
		this.server.sendMessage(text);
	}
	
	protected final void sendPrivateMessage(String text, int reciever) {
		this.server.sendPrivateMessage(text, reciever);
	}
	
	/*
	 * TRIGGER-METHODS
	 */

	@Override
	public String getLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void informAuction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informBankruptcy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informCardPull(String text, boolean communityCard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informCashChange(int player, int cashChange) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informConstruct(int street) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informDestruct(int street) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informDiceValues(int[] diceValues) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informMessage(String text, int sender, boolean privateMessage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informMortgageToogle(int street) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informStartGame(int numberOfPlayers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informStreetBuy(int player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informTrade(int actingPlayer, int partnerPlayer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informTurn(int player) {
		// TODO Auto-generated method stub
		
	}

}
