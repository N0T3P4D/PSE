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

import java.util.HashMap;
import java.util.Map;

import org.ojim.iface.IClient;
import org.ojim.logic.Logic;
import org.ojim.logic.state.BuyableField;
import org.ojim.logic.state.CardField;
import org.ojim.logic.state.Field;
import org.ojim.logic.state.FieldGroup;
import org.ojim.logic.state.FreeParking;
import org.ojim.logic.state.GameState;
import org.ojim.logic.state.GoField;
import org.ojim.logic.state.GoToJail;
import org.ojim.logic.state.InfrastructureField;
import org.ojim.logic.state.Jail;
import org.ojim.logic.state.Street;
import org.ojim.logic.state.TaxField;
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

	private void loadGameBoard() {

		GameState state = this.getGameState();

		FieldGroup stations = new FieldGroup(FieldGroup.STATIONS);
		FieldGroup infrastructures = new FieldGroup(FieldGroup.INFRASTRUCTURE);
		Map<Integer, FieldGroup> colorGroups = new HashMap<Integer, FieldGroup>(8);
		
		for (int position = 0; position < GameState.FIELDS_AMOUNT; position++) {
			Field field = null;
			String name = this.getEstateName(position);
			int price = this.getEstatePrice(position);
			int group = this.getEstateColorGroup(position);
			if (group >= 0) {
				FieldGroup theChoosenGroup = colorGroups.get(group);
				if (theChoosenGroup == null) {
					theChoosenGroup = new FieldGroup(group);
				}
				
				int[] rentByLevel = new int[this.getMaximumBuiltLevel()];
				for (int builtLevel = 0; builtLevel < rentByLevel.length; builtLevel++) {
					rentByLevel[builtLevel] = this.getEstateRent(position, builtLevel);
				}
				
				field = theChoosenGroup.addField(new Street(name, position, rentByLevel, price));
			} else {
				switch (this.getEstateColorGroup(position)) {
				case FieldGroup.GO:
					field = new GoField(position); // Los feld
					break;
				case FieldGroup.JAIL: // Gefängnis
					// FIXME: (xZise) Get real values!
					field = new Jail(position, 1000, 3);
					break;
				case FieldGroup.FREE_PARKING:
					field = new FreeParking(position);
					break;
				case FieldGroup.GO_TO_JAIL:
					field = new GoToJail(position);
					break;
				case FieldGroup.EVENT:
					field = new CardField(position);
					break;
				case FieldGroup.COMMUNITY:
					field = new CardField(position);
					break;
				case FieldGroup.STATIONS:
					field = stations
							.addField(new Station(name, position, price));
					break;
				case FieldGroup.INFRASTRUCTURE:
					field = infrastructures.addField(new InfrastructureField(
							name, position, price));
					break;
				case FieldGroup.TAX:
					field = new TaxField(name, position, this.getEstateRent(
							position, 0), this.getGameState().getBank());
					break;
				}
			}

			if (field == null) {
				// TODO: show error
			} else {
				state.setFieldAt(field, position);
			}
		}
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
		this.setParameters(new Logic(server.getRules()),
				server.addPlayer(this), server);
		this.loadGameBoard();
		return true;
	}

	protected final void connect(IServer server) {
		this.setParameters(new Logic(server.getRules()),
				server.addPlayer(this), server);
		this.loadGameBoard();
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

	@Override
	public void informMove(int position, int playerId) {
		// TODO Auto-generated method stub

	}

}
