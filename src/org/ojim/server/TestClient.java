/*  Copyright (C) 2010  Fabian Neundorf, Philip Caroli, Maximilian Madlung, 
 * 						Usman Ghani Ahmed, Jeremias Mechler
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

package org.ojim.server;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.ojim.iface.IClient;

import edu.kit.iti.pse.iface.IServer;

public class TestClient implements IClient {
	
	private IServer server;
	private int id = -1;
	
	public TestClient(final IServer server) {
		this.server = server;
	
		GridLayout experimentLayout = new GridLayout(0, 3);
		
		JPanel pane = new JPanel();		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5,5));
		
		pane.setLayout(experimentLayout);
		JFrame frame = new JFrame("TestClient");
		final JTextField var1 = new JTextField();
		var1.setMinimumSize(new Dimension(200, 100));
		pane.add(var1);
		JTextField var2 = new JTextField();
		var2.setMinimumSize(new Dimension(200, 100));
		var2.setMaximumSize(new Dimension(200, 100));
		pane.add(var2);
		final JLabel res = new JLabel("Result     ");
		pane.add(res);
		JButton bt1 = new JButton("getNumberOfHousesLeft()");
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setText("" + server.getNumberOfHousesLeft());
			}
		});
		panel.add(bt1);
		
		JButton bt2 = new JButton("getPlayerOnTurn()");
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setText("" + server.getPlayerOnTurn());
			}
		});
		panel.add(bt2);
		
		JButton bt3 = new JButton("getNumberOfHotelsLeft()");
		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setText("" + server.getNumberOfHotelsLeft());
			}
		});
		panel.add(bt3);
		
		JButton bt4 = new JButton("setPlayerReady()");
		bt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id != -1) {
					server.setPlayerReady(id);
					res.setText("Set ready!");
				}
			}
		});
		//panel.add(bt4);
		
		frame.setLayout(new GridLayout(2,1));
		frame.add(pane);		
		frame.add(panel);
		frame.setSize(640, 360);		
		frame.setVisible(true);
		
		var1.setText("");
		var2.setText("");
	}
	
	@Override
	public String getName() {
		return "Im a Test!";
	}

	@Override
	public String getLanguage() {
		return "error";
	}
	
	private void print(String out) {
		System.out.println("TestClient:" + out);
	}

	@Override
	public void informStartGame(int numberOfPlayers) {
		print("informed: StartGame");

	}

	@Override
	public void informTurn(int player) {
		print("informed: Turn");

	}

	@Override
	public void informDiceValues(int[] diceValues) {
		print("informed: DiceValues" + diceValues[0] + " " + diceValues[1]);
	}

	@Override
	public void informCashChange(int player, int cashChange) {
		print("informed: CashChange Player:" + player + " Change:" + cashChange);

	}

	@Override
	public void informStreetBuy(int player) {
		print("informed: StreetBuy Player:" + player);
	}

	@Override
	public void informConstruct(int street) {
		print("informed: Construct Street:" + street);
	}

	@Override
	public void informDestruct(int street) {
		print("informed: Destruct Street:" + street);
	}

	@Override
	public void informMortgageToogle(int street) {
		print("informed: MortgageToogle Street:" + street);
	}

	@Override
	public void informCardPull(String text, boolean communityCard) {
		print("informed: CardPull Text:" + text + (communityCard ? "Community": "Event"));
	}

	@Override
	public void informBankruptcy() {
		print("informed: Bankrupty");
	}

	@Override
	public void informMessage(String text, int sender, boolean privateMessage) {
		print("informed: Message Text:" + text + " Sender:" + sender + (privateMessage ? "private" : ""));
	}

	@Override
	public void informTrade(int actingPlayer, int partnerPlayer) {
		print("informed: Trade Acting:" + actingPlayer + " Partner:" + partnerPlayer);
	}

	@Override
	public void informAuction() {
		print("informed: Auction");
	}

}
