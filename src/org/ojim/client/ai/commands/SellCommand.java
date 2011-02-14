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

package org.ojim.client.ai.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.ojim.client.SimpleClient;
import org.ojim.log.OJIMLogger;
import org.ojim.logic.Logic;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.fields.BuyableField;

import edu.kit.iti.pse.iface.IServer;

/**
 * 
 * @author Jeremias Mechler
 * 
 */
public class SellCommand extends Command {

	private static final long serialVersionUID = 5107314051420108256L;

	private int position;
	private int maxCash;
	private int minCash;

	/**
	 * 
	 * Constructor
	 * 
	 * @param server
	 *            Reference to the server
	 * @param logic
	 *            Reference to the game logic
	 * @param playerId
	 *            The client's ID
	 * @param position
	 *            position of the property which is to be sold
	 * @param maxCash
	 *            desired price
	 * @param minCash
	 *            minimum price
	 * 
	 */
	public SellCommand(Logic logic, IServer server, int playerId, int position, int maxCash, int minCash) {
		super(logic, playerId, server);
		this.position = position;
		this.maxCash = maxCash;
		this.minCash = minCash;
		assert (maxCash > minCash);
	}

	@Override
	public void execute() {
		Logger logger = OJIMLogger.getLogger(this.getClass().getName());
		int amount = maxCash;
		TradeState state = TradeState.WAITING_PROPOSAL;
		boolean end = false;
		boolean sold = false;
		BuyableField offeredEstate = (BuyableField) this.getGameState().getFieldAt(this.position);
		while (!sold || !end) {
			for (Player player : getGameState().getPlayers()) {
				end = false;
				if (!sold) {
					if (player != this.getMe()) {
						this.initTrade(player);
						logger.log(Level.FINE, "RemoteID = " + player.getId() + " offered estate = " + position);
						assert (offeredEstate.getOwner() == this.getMe());
						this.offerEstate(offeredEstate);
						requireCash(amount);
						proposeTrade();
						
						state = this.getTradeState();
						assert (state != TradeState.NOT_RUNNING && !end);
						while (state != TradeState.NOT_RUNNING && !end) {
							state = this.getTradeState();
							switch (state) {
							case WAITING_PROPOSAL :
								assert (false);
								break;
							case WAITING_PROPOSED :
								break;
							case DECLINED :
								sold = true;
							case ACCEPTED :
								end = true;
								break;
							default:
								assert (false);
								break;
							}
						}
					}
				}
			}
			// try again with less cash
			amount = minCash;
		}
		if (sold) {
			assert (true);
			// TODO remove
		}
		// An die Bank weitergeben
		if (!sold) {
			// TODO remove
			assert (false);
			this.initTrade(null);
			offerEstate(offeredEstate);
			this.requireCash(0);
			proposeTrade();
			assert (this.getTradeState() == TradeState.DECLINED);
		}
	}
}
