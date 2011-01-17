package org.ojim.logic.state;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.ojim.logic.Logic;
import org.ojim.logic.rules.GameRules;

public class Auction {
	
	private BuyableField objective;
	
	/**
     * Gibt den Stand der Auktion an.
     *  0 falls eine Auktion läuft und noch nicht ausgezählt wird<br>
     *  1 falls eine Auktion läuft und "zum Ersten" angekündigt wurde<br>
     *  2 falls eine Auktion läuft und "zum Zweiten" angekündigt wurde<br>
     *  3 falls eine Auktion gerade abgeschlossen wurde <i>(optional)</i>,
     *    in diesem Zustand müssen noch alle Daten abrufbar sein
     */
	private int auctionState;
	
	private Player highestBidder;
	
	private GameState state;

	private int currentBid;
	
	private Logic logic;

	private GameRules rules;
	
	private Timer timer;
	
	public Auction(GameState state, Logic logic, GameRules rules, BuyableField objective) {
		this.state = state;
		this.logic = logic;
		this.rules = rules;
		this.objective = objective;
		this.highestBidder = null;;
		this.auctionState = -1;
		this.currentBid = rules.getAuctionStartBid(objective);
		this.timer = new Timer();
		
		//Start ticking
		timer.schedule(new AuctionTask(this), this.rules.getActionStartTime(), this.rules.getActionTickTime());
		
		//Tell All Players that a new Auction has started
		for(Player player : state.getPlayers()) {
			if(player instanceof ServerPlayer) {
				((ServerPlayer)player).getClient().informAuction(this.auctionState);
			}
		}
	}
		
	public int getAuctionState() {
		return this.auctionState;
	}
	
	public void placeBid(Player bidder, int bid) {
		if(bid > currentBid) {
			//Set the Bidder as the Highest Bidder
			this.highestBidder = bidder;
			this.currentBid = bid;
			
			//Restart the Timer
			this.auctionState = 0;
			timer.cancel();
			timer.schedule(new AuctionTask(this), this.rules.getActionTickTime(), this.rules.getActionTickTime());
			
			//Tell All Players that a new Highest Bid is there
			for(Player player : state.getPlayers()) {
				if(player instanceof ServerPlayer) {
					((ServerPlayer)player).getClient().informAuction(this.auctionState);
				}
			}			
		}
	}
	
	protected void tick() {
		this.auctionState++;
		
		//Auction is now over
		if(this.auctionState >= 3) {

			//End the Auction, stop the Timer
			this.timer.cancel();
			
			//No one wanted the Field, see what to do now
			if(highestBidder == null) {
				this.logic.auctionWithoutResult(this.objective);
			} else {
				this.logic.auctionWithResult(this.objective, highestBidder);
			}
		} else {
			//Call all Players that the next Step has come
			for(Player player : state.getPlayers()) {
				if(player instanceof ServerPlayer) {
					((ServerPlayer)player).getClient().informAuction(this.auctionState);
				}
			}
		}
	}
	
}

class AuctionTask extends TimerTask {

	private Auction auction;
	
	protected AuctionTask(Auction auction) {
		this.auction = auction;
	}
	
	@Override
	public void run() {
		auction.tick();		
	}
	
}
