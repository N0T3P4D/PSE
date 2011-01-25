package org.ojim.rmi.client;

public class ImplIClientRemote implements IClientRemote {

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
	public void informStartGame(int[] ids) {
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
	public void informBuy(int player, int position) {
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
	public void informAuction(int auctionState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informMove(int playerId, int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informNewPlayer(int playerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informPlayerLeft(int playerId) {
		// TODO Auto-generated method stub
		
	}

}
