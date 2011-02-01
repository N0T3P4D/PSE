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
package org.ojim.rmi.server;

import java.rmi.RemoteException;

import org.ojim.iface.IClient;
import org.ojim.rmi.client.NetClient;

/**
 * Klasse fungiert als Wrapper, Methodenaufrufe werden über die RMI weitergeleitet und auf dem Client 
 * aufgerufen
 * 
 * @author Usman Ghani Ahmed
 *
 */
public class ClientWrapper implements IClient {

	private NetClient sink;
	
	
	public ClientWrapper(NetClient sink) {
		this.sink = sink;
		
		
	}
	
	@Override
	public String getName() {
		
		String temp = "";
		try {
			return this.sink.getName();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public String getLanguage() {
		String temp = "";
		try {
			return this.sink.getLanguage();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public void informStartGame(int[] ids) {
		
		try {
			this.sink.informStartGame(ids);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	

	}

	@Override
	public void informTurn(int player) {
		
		try {
			this.sink.informTurn(player);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void informDiceValues(int[] diceValues) {
		
		try {
			this.sink.informDiceValues(diceValues);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void informCashChange(int player, int cashChange) {
		
		try {
			this.sink.informCashChange(player, cashChange);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void informBuy(int player, int position) {
		
		try {
			this.sink.informBuy(player, position);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void informConstruct(int street) {
		
		try {
			this.sink.informConstruct(street);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void informDestruct(int street) {
		
		try {
			this.sink.informDestruct(street);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void informMortgageToogle(int street) {
		
		try {
			this.sink.informMortgageToogle(street);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	

	}

	@Override
	public void informCardPull(String text, boolean communityCard) {
		
		try {
			this.sink.informCardPull(text, communityCard);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void informBankruptcy() {
		
		try {
			this.sink.informBankruptcy();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void informMessage(String text, int sender, boolean privateMessage) {
		
		try {
			this.sink.informMessage(text, sender, privateMessage);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void informTrade(int actingPlayer, int partnerPlayer) {
		
		try {
			this.sink.informTrade(actingPlayer, partnerPlayer);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void informAuction(int auctionState) {
		
		try {
			this.sink.informAuction(auctionState);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void informMove(int playerId, int position) {
		
		try {
			this.sink.informMove(playerId, position);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void informNewPlayer(int playerId) {
		
		try {
			this.sink.informNewPlayer(playerId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void informPlayerLeft(int playerId) {
		
		try {
			this.sink.informPlayerLeft(playerId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		

	}

}
