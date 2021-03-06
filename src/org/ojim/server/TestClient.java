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

package org.ojim.server;

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
	
	@SuppressWarnings("unused")
	private IServer server;
	private int id = -1;
	private final JTextField var1, var2;
	private final JLabel res;
	private final JFrame frame;
	private TestClient client = this; 
	
	public TestClient(final IServer server) {
		this.server = server;
	
		GridLayout experimentLayout = new GridLayout(0, 3);
		
		JPanel pane = new JPanel();		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5,5));
		
		pane.setLayout(experimentLayout);
		frame = new JFrame("TestClient");
		var1 = new JTextField();
		pane.add(var1);
		var2 = new JTextField();
		pane.add(var2);
		res = new JLabel("Result     ");
		pane.add(res);
		JButton bt1 = new JButton("getNumberOfHousesLeft()");
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestClient.this.setText("" + server.getNumberOfHousesLeft());
			}
		});
		panel.add(bt1);
		
		JButton bt2 = new JButton("getPlayerOnTurn()");
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestClient.this.setText("" + server.getPlayerOnTurn());
			}
		});
		panel.add(bt2);
		
		JButton bt3 = new JButton("getNumberOfHotelsLeft()");
		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestClient.this.setText("" + server.getNumberOfHotelsLeft());
			}
		});
		panel.add(bt3);
		
		JButton bt4 = new JButton("setPlayerReady()");
		bt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id != -1) {
					TestClient.this.setText("Set ready!");
					server.setPlayerReady(id);
				}
			}
		});
		panel.add(bt4);
		
		JButton bt5 = new JButton("rollDices()");
		bt5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id != -1) {
					TestClient.this.setText("Rolled Dice!");
					server.rollDice(id);
				}
			}
		});
		panel.add(bt5);
		
		JButton bt6 = new JButton("AddPlayer(this)");
		bt6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id == -1) {
					id = server.addPlayer(client);
					TestClient.this.setText("Added Player, Id:" + id);
					TestClient.this.frame.setTitle("TestClient [id " + id + "]");
				}
			}
		});
		panel.add(bt6);
		
		JButton bt7 = new JButton("getPlayerPiecePosition");
		bt7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestClient.this.setText("Position:" + server.getPlayerPiecePosition(id));
			}
		});
		panel.add(bt7);
		
		JButton bt8 = new JButton("endTurn()");
		bt8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestClient.this.setText("Turn ended");
				print("result = " +server.endTurn(id));
			}
		});
		panel.add(bt8);
		
		JButton bt9 = new JButton("accept()");
		bt9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestClient.this.setText("Accepted:" + server.accept(id));
			}
		});
		panel.add(bt9);
		
		JButton bt10 = new JButton("decline()");
		bt10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestClient.this.setText("Declined:" + server.decline(id));
			}
		});
		panel.add(bt10);
		
		JButton bt11 = new JButton("inf. roll");
		bt11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while(true) {
					server.rollDice(id);
					server.endTurn(id);
				}
			}
		});
		panel.add(bt11);
		
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
	
	private String lbl;
	
	private void setText(String str) {
		lbl = str;
		res.setText("<html>" + lbl + "</html>");
	}
	
	private void print(String out) {
//		lbl += "<br>" + out;
//		this.setText(lbl);
//		System.out.println(out);
	}

	@Override
	public void informStartGame(int[] ids) {
		print("informed: StartGame");

	}

	@Override
	public void informTurn(int player) {
		print("informed: Turn" + (player == id ? " YOU!" : ""));

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
	public void informTrade() {
		print("informed: Trade");
	}

	@Override
	public void informMove(int playerId, int position) {
		print("informed: Move Pos:" + position + " Player:" + playerId);
		
	}

	@Override
	public void informBuy(int player, int position) {
		print("informed: StreetBuy Player:" + player + " Field:" + position);
		
	}

	@Override
	public void informAuction(int auctionState) {
		print("informed: Auction state:" + auctionState);
		
	}

	@Override
	public void informNewPlayer(int playerId) {
		print("informed: new Player:" + playerId);
		
	}

	@Override
	public void informPlayerLeft(int playerId) {
		print("informed: Player left:" + playerId);
		
	}

	@Override
	public void informFreeParkingChange(int freeParkingField, int newPot) {
		print("informed: Free parking changed:" + freeParkingField + " to: " + newPot);
	}

	@Override
	public void informCanEndTurn(int player) {
		print("informed: Can end turn:" + player);
	}

	@Override
	public void informBuyEvent(int player, int position) {
		print("informed: Buy event:" + player + " @" + position);
	}

	@Override
	public void informGameOver(int player) {
		print("informed: Game over:" + player);
	}

}
