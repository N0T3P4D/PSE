package org.ojim.server;

import org.ojim.iface.IClient;

public class TestClient implements IClient {
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void informStartGame(int numberOfPlayers) {
		// TODO Auto-generated method stub

	}

	@Override
	public void informTurn(int player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void informDiceValues(int[] diceValues) {
		// TODO Auto-generated method stub

	}

	@Override
	public void informCashChange(int player, int cashChange) {
		// TODO Auto-generated method stub

	}

	@Override
	public void informStreetBuy(int player) {
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
	public void informMortgageToogle(int street) {
		// TODO Auto-generated method stub

	}

	@Override
	public void informCardPull(String text, boolean communityCard) {
		// TODO Auto-generated method stub

	}

	@Override
	public void informBankruptcy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void informMessage(String text, int sender, boolean privateMessage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void informTrade(int actingPlayer, int partnerPlayer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void informAuction() {
		// TODO Auto-generated method stub

	}

}
