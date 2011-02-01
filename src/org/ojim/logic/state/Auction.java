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

package org.ojim.logic.state;

import java.util.Timer;
import java.util.TimerTask;

import org.ojim.logic.Logic;
import org.ojim.logic.rules.GameRules;
import org.ojim.logic.state.fields.BuyableField;
import org.ojim.logic.state.fields.Field;

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
		timer.schedule(new AuctionTask(this), 1000 * this.rules.getActionStartTime(), 1000 * this.rules.getActionTickTime());
		
		//Tell All Players that a new Auction has started
		for(Player player : state.getPlayers()) {
			if(player instanceof ServerPlayer) {
				((ServerPlayer)player).getClient().informAuction(this.auctionState);
			}
		}
	}
	
	public Player getHighestBidder() {
		return this.highestBidder;
	}
	
	public int getHighestBid() {
		return this.currentBid;
	}
		
	public int getAuctionState() {
		return this.auctionState;
	}
	
	public boolean placeBid(Player bidder, int bid) {
		if(bid > currentBid) {
			//Set the Bidder as the Highest Bidder
			this.highestBidder = bidder;
			this.currentBid = bid;
			
			//Restart the Timer
			this.auctionState = 0;
			//timer.cancel();
			timer.schedule(new AuctionTask(this), 1000 * this.rules.getActionTickTime(), 1000 * this.rules.getActionTickTime());
			
			//Tell All Players that a new Highest Bid is there
			for(Player player : state.getPlayers()) {
				if(player instanceof ServerPlayer) {
					((ServerPlayer)player).getClient().informAuction(this.auctionState);
				}
			}	
			return true;
		}
		return false;
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
					//TODO remove test
					((ServerPlayer)player).getClient().informMessage("Auction:" + this.auctionState, -1, false);
				}
			}
		}
	}

	public Field getObjective() {
		return this.objective;
	}
	
}

class AuctionTask extends TimerTask {

	private Auction auction;
	
	protected AuctionTask(Auction auction) {
		this.auction = auction;
		System.out.println("Tick!");
	}
	
	@Override
	public void run() {
		auction.tick();		
		System.out.println("Tock!");
	}
	
}
