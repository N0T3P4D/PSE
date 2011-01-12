package org.ojim.server;

import java.awt.Dimension;
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
	
	public TestClient(final IServer server) {
		this.server = server;
	
		JPanel pane = new JPanel();		
		JFrame frame = new JFrame("TestClient");
		JTextField var1 = new JTextField();
		var1.setMinimumSize(new Dimension(200, 100));
		//pane.add(var1);
		JTextField var2 = new JTextField();
		var2.setMinimumSize(new Dimension(200, 100));
		//pane.add(var2);
		final JLabel res = new JLabel("Result     ");
		pane.add(res);
		JButton bt1 = new JButton("getNumberOfHousesLeft()");
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setText("" + server.getNumberOfHousesLeft());
			}
		});
		pane.add(bt1);
		
		JButton bt2 = new JButton("getPlayerOnTurn()");
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setText("" + server.getPlayerOnTurn());
			}
		});
		pane.add(bt2);
		
		JButton bt3 = new JButton("getNumberOfHotelsLeft()");
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setText("" + server.getNumberOfHotelsLeft());
			}
		});
		pane.add(bt3);
		
		frame.add(pane);		
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
