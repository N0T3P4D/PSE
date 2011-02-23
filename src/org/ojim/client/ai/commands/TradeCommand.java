package org.ojim.client.ai.commands;

import org.ojim.logic.Logic;
import org.ojim.logic.state.fields.BuyableField;

import edu.kit.iti.pse.iface.IServer;

/**
 * 
 * Trade Command:
 * Start a trade with another player
 * 
 * @author Jeremias Mechler
 *
 */
public class TradeCommand extends Command {

	private BuyableField sell;
	private BuyableField request;

	/**
	 * Constructor
	 * @param logic
	 * reference to logic
	 * @param playerId
	 * calling player's id
	 * @param server
	 * reference to server
	 * @param sell
	 * field to sell
	 * @param request
	 * requested field
	 */
	public TradeCommand(Logic logic, int playerId, IServer server, BuyableField sell, BuyableField request) {
		super(logic, playerId, server);
		this.sell = sell;
		this.request = request;
	}

	private static final long serialVersionUID = -9030880338667586521L;

	/**
	 * @{inheritDoc}
	 */
	public void execute() {
		this.initTrade(request.getOwner());
		this.offerEstate(sell);
		this.requireEstate(request);
		this.proposeTrade();
	}

}
