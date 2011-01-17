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

import org.ojim.iface.IClient;
import org.ojim.logic.Logic;
import org.ojim.logic.state.BuyableField;
import org.ojim.logic.state.Field;
import org.ojim.logic.state.Street;
import org.ojim.network.ClientConnection;

import edu.kit.iti.pse.iface.IServer;

/**
 * Basis Client für den GUIClient und AIClient.
 * 
 * @author Fabian Neundorf
 */
public class ClientBase extends SimpleClient implements IClient {

	private ClientConnection connection;

	private String name;

	public ClientBase() {
		super();
		this.connection = new ClientConnection();
	}
	
	/*
	 * MISC
	 */
	
	private void loadGameState() {
		
	}
	
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * ACTION METHODS
	 */

	protected final boolean connect(String host, String name) {
		IServer server = this.connection.connect(host, name);
		if (server == null) {
			return false;
		}
		this.setParameters(new Logic(server.getRules()), server.addPlayer(this), server);
		return true;
	}

	protected final void connect(IServer server) {
		this.setParameters(new Logic(server.getRules()), server.addPlayer(this), server);
	}

	/*
	 * TRIGGER-METHODS
	 */

	@Override
	public String getLanguage() {
		return "eng";
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
		Field field = this.getLogic().getGameState().getFieldAt(street);
		if (field instanceof Street) {
			this.getLogic().upgrade((Street) field, +1);
		}
	}

	@Override
	public void informDestruct(int street) {
		Field field = this.getLogic().getGameState().getFieldAt(street);
		if (field instanceof Street) {
			this.getLogic().upgrade((Street) field, -1);
		}
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
		Field field = this.getLogic().getGameState().getFieldAt(street);
		if (field instanceof BuyableField) {
			this.getLogic().toggleMortgage((BuyableField) field);
		}
	}

	@Override
	public void informStartGame(int numberOfPlayers) {
		// TODO Auto-generated method stub

	}

	// xZise: Brauchen wir den Parameter?
	@Override
	public void informStreetBuy(int player) {
		this.getLogic().buyStreet();
	}

	@Override
	public void informTrade(int actingPlayer, int partnerPlayer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void informTurn(int player) {

		// TODO Auto-generated method stub

		// GameState bescheid sagen, wer jetzt dran ist
	}

}
