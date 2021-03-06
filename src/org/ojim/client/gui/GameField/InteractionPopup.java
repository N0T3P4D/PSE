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

package org.ojim.client.gui.GameField;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.ojim.client.SimpleClient.AuctionState;
import org.ojim.client.gui.GUIClient;
import org.ojim.client.gui.StreetColor;
import org.ojim.client.gui.RightBar.ChatMessage;
import org.ojim.client.gui.RightBar.ChatWindow;
import org.ojim.language.Localizer;
import org.ojim.language.Localizer.TextKey;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.fields.BuyableField;
import org.ojim.logic.state.fields.Field;
import org.ojim.logic.state.fields.Street;

public class InteractionPopup extends JPanel {

	/**
	 * Status Diese Enum erklärt welchen Zustand das Mittelfeld im Moment hat
	 * trade - Ein Handel zwischen zwei Spielern, dazu klickt man rechts auf
	 * einen Spieler auction - eine Auktion an der alle Teilhaben auctionStart -
	 * das Vorbereiten einer Auktion game - Das Spielfeld im normalen Zustand
	 * mit Ereigniskarten, Würfelergebnissen und dem Geld in der Mitte
	 * 
	 * 
	 */
	private enum status {
		trade, auction, auctionStart, game
	};

	private String message;
	private boolean cancelEnabled;
	private boolean acceptEnabled;
	private boolean isActive;
	private JLabel textLabel = new JLabel();
	private JPanel dicePanel = new JPanel();
	private JLabel diceTextLabel = new JLabel();
	private JLabel[] diceValues;
	private JPanel freeParkingCashPanel = new JPanel();
	private JLabel freeParkingCashLabel = new JLabel();

	private Street upgradeStreet;
	private JLabel upgradeTextLabel = new JLabel();
	private JTextField upgradeTextField = new JTextField();
	private JButton upgradeButton = new JButton();
	private JButton downgradeButton = new JButton();

	private JLabel upgradeButtonLabel = new JLabel();
	private JLabel downgradeButtonLabel = new JLabel();
	private JPanel upgradePanel = new JPanel();
	private Localizer language;
	private int cash;
	private GUIClient gui;

	// Trade
	private JPanel tradePanel;
	private JPanel myTradePanel;
	private JPanel hisTradePanel;
	private JLabel myName;
	private JLabel hisName;
	private JLabel myMoney;
	private JLabel hisMoney;
	private JTextField myMoneyField;
	private JTextField hisMoneyField;
	private JPanel myCards;
	private JPanel hisCards;
	private JLabel myJailCard;
	private JLabel hisJailCard;
	private JTextField myJailCards;
	private JTextField hisJailCards;
	private JButton okButton;
	private JLabel okButtonLabel;
	private JButton noButton;
	private JLabel noButtonLabel;

	private List<BuyableField> hisFields;
	private List<BuyableField> myFields;

	private JPanel[] tradeCardPanel;
	private JLabel fieldLabel;

	private JPanel auctionPanel;
	private JLabel auctionCardLabel;
	private JPanel auctionCardPanel;
	private JLabel auctionHighestBidPlayer;
	private JLabel auctionHighestBid;
	private JButton auctionButtonOk;
	private JLabel auctionButtonOkLabel;
	private JTextField bidRate;

	private JPanel newAuctionPanel;
	private JLabel newAuctionCardLabel;
	private JPanel newAuctionCardPanel;
	private JLabel newAuctionMinimumBid;
	private JButton newAuctionButtonOk;
	private JLabel newAuctionButtonOkLabel;
	private JTextField newBidRate;

	private ActionListener upgradeListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gui.upgradeField(upgradeStreet.getPosition(),
					upgradeStreet.getBuiltLevel() + 1);
			deleteUpgrade();
		}
	};;;

	private ActionListener downgradeListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gui.upgradeField(upgradeStreet.getPosition(),
					upgradeStreet.getBuiltLevel() - 1);
			deleteUpgrade();
		}
	};;;

	private ActionListener tradeOkListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gui.trade(tradePartner, Integer.parseInt(myMoneyField.getText())
					- Integer.parseInt(hisMoneyField.getText()), myFields,
					hisFields, Integer.parseInt(myJailCards.getText())
							- Integer.parseInt(hisJailCards.getText()));
		}
	};;;

	private ActionListener tradeNoListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gui.noTrade();
			remove(tradePanel);
			revalidate();
			repaint();
		}
	};;;

	private ActionListener bidListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (!bidRate.equals("")) {
				gui.acceptBid(Integer.parseInt(bidRate.getText()));
			}

		}
	};;;

	private ActionListener newBidListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gui.startNewAuction(newAuctionCardLabel.getText(), newBidRate);
			remove(newAuctionPanel);
			repaint();
			revalidate();

		}
	};
	private Player tradeMe;
	private Player tradePartner;

	private boolean otherTrade = false;

	// private

	/**
	 * Diese Methode initialisiert alles.
	 * 
	 * @param guiClient
	 */
	public InteractionPopup(GUIClient guiClient, Player me) {

		this.gui = guiClient;
		this.setBackground(Color.BLACK);

		tradeCardPanel = new JPanel[guiClient.getGameState()
				.getNumberOfFields()];

		tradeMe = me;

		this.diceValues = new JLabel[2];
		dicePanel.add(diceTextLabel);
		for (int i = 0; i < diceValues.length; i++) {
			diceValues[i] = new JLabel();
			dicePanel.add(diceValues[i]);
			diceValues[i].setBorder(new LineBorder(Color.BLACK, 1));
		}
		this.add(dicePanel);

		dicePanel.setBackground(Color.WHITE);

		freeParkingCashPanel.setBackground(Color.WHITE);

		freeParkingCashPanel.add(freeParkingCashLabel);
		this.add(freeParkingCashPanel);

		upgradeTextField.setText("0");
		upgradeButton.add(upgradeButtonLabel);
		downgradeButton.add(downgradeButtonLabel);
		upgradePanel.add(upgradeTextLabel);
		// upgradePanel.add(upgradeTextField);
		upgradePanel.add(upgradeButton);
		upgradePanel.add(downgradeButton);
		upgradePanel.setBackground(Color.WHITE);

		upgradeButton.addActionListener(upgradeListener);
		downgradeButton.addActionListener(downgradeListener);
		upgradePanel.setLayout(new FlowLayout());

		tradePanel = new JPanel();
		myTradePanel = new JPanel();
		hisTradePanel = new JPanel();
		myName = new JLabel();
		hisName = new JLabel();
		myMoney = new JLabel();
		hisMoney = new JLabel();
		myMoneyField = new JTextField("0");
		hisMoneyField = new JTextField("0");
		myJailCard = new JLabel();
		hisJailCard = new JLabel();
		myJailCards = new JTextField("0");
		hisJailCards = new JTextField("0");
		myCards = new JPanel();
		myCards.setLayout(new GridLayout(0, 1));
		hisCards = new JPanel();
		hisCards.setLayout(new GridLayout(0, 1));
		okButton = new JButton();
		noButton = new JButton();

		myTradePanel.add(myName);
		myTradePanel.add(myMoney);
		myTradePanel.add(myMoneyField);
		myTradePanel.add(myCards);
		myTradePanel.add(myJailCard);
		myTradePanel.add(myJailCards);
		myFields = new LinkedList<BuyableField>();

		hisTradePanel.add(hisName);
		hisTradePanel.add(hisMoney);
		hisTradePanel.add(hisMoneyField);
		hisTradePanel.add(hisCards);
		hisTradePanel.add(hisJailCard);
		hisTradePanel.add(hisJailCards);
		hisFields = new LinkedList<BuyableField>();

		okButton.add(okButtonLabel = new JLabel());
		noButton.add(noButtonLabel = new JLabel());

		okButton.addActionListener(tradeOkListener);
		noButton.addActionListener(tradeNoListener);

		myTradePanel
				.setLayout(new BoxLayout(myTradePanel, BoxLayout.PAGE_AXIS));
		tradePanel.add(myTradePanel);
		hisTradePanel.setLayout(new BoxLayout(hisTradePanel,
				BoxLayout.PAGE_AXIS));
		tradePanel.add(hisTradePanel);
		tradePanel.setLayout(new GridLayout(0, 2));
		tradePanel.add(okButton);
		tradePanel.add(noButton);

		auctionPanel = new JPanel();
		auctionCardLabel = new JLabel();
		auctionCardPanel = new JPanel();
		auctionHighestBidPlayer = new JLabel();
		auctionHighestBid = new JLabel();
		auctionButtonOk = new JButton();
		auctionButtonOkLabel = new JLabel();
		bidRate = new JTextField();
		bidRate.setText("0");

		auctionCardPanel.add(auctionCardLabel);

		auctionPanel.add(auctionCardPanel);
		auctionPanel.add(auctionHighestBidPlayer);
		auctionPanel.add(auctionHighestBid);
		auctionPanel.add(bidRate);
		auctionButtonOk.add(auctionButtonOkLabel);
		auctionPanel.add(auctionButtonOk);

		auctionButtonOk.addActionListener(bidListener);

		auctionPanel.setLayout(new GridLayout(0, 1));

		newAuctionPanel = new JPanel();
		newAuctionCardLabel = new JLabel();
		newAuctionCardPanel = new JPanel();
		newAuctionMinimumBid = new JLabel();
		newAuctionButtonOk = new JButton();
		newAuctionButtonOkLabel = new JLabel();
		newBidRate = new JTextField();
		newBidRate.setText("0");

		newAuctionCardPanel.add(newAuctionCardLabel);

		newAuctionPanel.add(newAuctionCardPanel);
		newAuctionPanel.add(newAuctionMinimumBid);
		newAuctionPanel.add(newBidRate);
		auctionButtonOk.add(auctionButtonOkLabel);
		auctionPanel.add(auctionButtonOk);

		newAuctionButtonOk.addActionListener(newBidListener);

		newAuctionPanel.setLayout(new GridLayout(0, 1));

	}

	public void clear() {

	}

	public void showMessage(String message, boolean accepting, boolean declining) {

	}

	public void showInformation(String message) {

	}

	public void draw() {

	}

	public void showAuction(AuctionState auctionState,
			BuyableField buyableField, Player bidder, int highestBid) {
		try {
			auctionButtonOkLabel.setText(language.getText(TextKey.AUCTION_BID));
			auctionCardLabel.setText(buyableField.getName());
			auctionHighestBid.setText(highestBid + " "
					+ language.getText(TextKey.CURRENCY));
			auctionHighestBidPlayer.setText(bidder.getName());
			this.add(auctionPanel);
			this.repaint();
			this.revalidate();
			System.out.println("May the Auction begin!");
		} catch (NullPointerException e) {
			removeAuction();
			System.out.println("Keine zulässige Auktion.");
		}
	}

	public void showTrade(Player me, Player partnerPlayer, int requiredCash,
			BuyableField[] requiredBuyableFields, int requiredOutOfJailCards,
			int offeredCash, BuyableField[] offeredBuyableFields,
			int offeredOutOfJailCards, boolean otherTrade) {

		this.otherTrade = otherTrade;

		tradeMe = me;
		tradePartner = partnerPlayer;
		if (!(me.getId() == partnerPlayer.getId())) {

			myFields = new LinkedList<BuyableField>();
			hisFields = new LinkedList<BuyableField>();

			for (BuyableField x : requiredBuyableFields) {
				myFields.add(x);
			}
			for (BuyableField y : offeredBuyableFields) {
				hisFields.add(y);
			}

			hisCards.removeAll();
			myCards.removeAll();

			hisCards.revalidate();
			myCards.revalidate();

			myName.setText(me.getName());
			hisName.setText(partnerPlayer.getName());
			myMoney.setText(language.getText("give money"));
			hisMoney.setText(language.getText("claim money"));
			myMoneyField.setColumns(5);
			if (requiredCash == -1) {
				requiredCash = 0;
			}
			myMoneyField.setText(requiredCash + "");
			hisMoneyField.setColumns(5);
			if (offeredCash == -1) {
				offeredCash = 0;
			}
			hisMoneyField.setText(offeredCash + "");
			myJailCard.setText(language.getText("jail cards") + ": ");
			hisJailCard.setText(language.getText("jail cards") + ": ");

			for (int i = 0; i < requiredBuyableFields.length; i++) {
				fieldClicked(requiredBuyableFields[i], me);
			}
			for (int i = 0; i < offeredBuyableFields.length; i++) {
				fieldClicked(offeredBuyableFields[i], me);
			}

			if (gui.getIsBankrupt()) {
				tradePanel.remove(okButton);
			}

			okButtonLabel.setText(language.getText("ok"));
			noButtonLabel.setText(language.getText("no"));
			// myCards = new JPanel();
			// hisCards = new JPanel();

			if (otherTrade) {
				myMoneyField.setEditable(false);
				hisMoneyField.setEditable(false);
				myJailCards.setEditable(false);
				hisJailCards.setEditable(false);
			} else {
				myMoneyField.setEditable(true);
				hisMoneyField.setEditable(true);
				myJailCards.setEditable(true);
				hisJailCards.setEditable(true);
			}

			this.add(tradePanel);

			this.repaint();
			this.revalidate();
			System.out.println("May the Trade begin!");
		}
	}

	public void startAuction(BuyableField buyableField) {

		newAuctionButtonOkLabel
				.setText(language.getText(TextKey.START_AUCTION));
		newAuctionCardLabel.setText(buyableField.getName());
		newAuctionMinimumBid.setText(language.getText(TextKey.MINIMUM_BID)
				+ ": ");
		this.add(auctionPanel);
		if (!gui.getIsBankrupt()) {
			auctionPanel.remove(auctionButtonOk);
		}

		this.add(newAuctionPanel);
		this.repaint();
		this.revalidate();
		System.out.println("May the Auction begin!");
	}

	public void close() {

	}

	public void showDices(int[] diceValues) {
		if (this.diceValues == null) {
			this.diceValues = new JLabel[diceValues.length];
		}
		for (int i = 0; i < diceValues.length; i++) {
			this.diceValues[i].setText(" " + diceValues[i] + " ");
		}
	}

	public void showFreeParkingCash(int cash) {
		this.cash = cash;
	}

	public void setLanguage(Localizer language) {
		this.language = language;
		diceTextLabel.setText(language.getText(TextKey.DICE_VALUES) + ": ");
		freeParkingCashLabel.setText(language
				.getText(TextKey.FREE_PARKING_CASH)
				+ ": "
				+ cash
				+ " "
				+ language.getText(TextKey.CURRENCY));

		if (upgradeStreet != null) {
			upgradeTextLabel.setText(language.getText("new upgrade level")
					+ " " + upgradeStreet.getName() + ": ");
			upgradeButtonLabel.setText(language.getText(TextKey.UPGRADE));
			downgradeButtonLabel.setText(language.getText(TextKey.DOWNGRADE));
		}

		auctionButtonOkLabel.setText(language.getText(TextKey.AUCTION_BID));

		myMoney.setText(language.getText("give money"));
		hisMoney.setText(language.getText("claim money"));
		myJailCard.setText(language.getText("jail cards") + ": ");
		hisJailCard.setText(language.getText("jail cards") + ": ");
		okButtonLabel.setText(language.getText("ok"));
		noButtonLabel.setText(language.getText("no"));

		repaint();
	}

	public void showUpgrade(Street street) {
		this.upgradeStreet = street;
		this.remove(upgradePanel);

		upgradeTextLabel.setText(language.getText("new upgrade level") + " "
				+ upgradeStreet.getName() + ": ");
		// TODO: (xZise) Define the maximum build level somewhere!
		// upgradeButton.setEnabled(street.getBuiltLevel() < );
		downgradeButton.setEnabled(street.getBuiltLevel() > 0);

		System.out.println("Upgrade");
		upgradePanel.setLayout(new FlowLayout());
		this.add(upgradePanel);
		this.repaint();
		this.revalidate();
	}

	public void deleteUpgrade() {
		this.remove(upgradePanel);
		this.repaint();
		this.revalidate();

	}

	public void endTrade() {
		this.remove(tradePanel);

	}

	public void removeAuction() {
		this.remove(auctionPanel);
		this.repaint();
		this.revalidate();

	}

	public void fieldClicked(Field field, Player me) {
		if (!gui.getIsBankrupt()) {
			if (field instanceof BuyableField) {
				BuyableField buyField = (BuyableField) field;
				if (tradeCardPanel[field.getPosition()] == null) {
					tradeCardPanel[field.getPosition()] = new JPanel();
					tradeCardPanel[field.getPosition()]
							.add(fieldLabel = new JLabel(field.getName()));
					tradeCardPanel[field.getPosition()]
							.setBackground(StreetColor.getBackGroundColor(field
									.getFieldGroup().getColor()));
					fieldLabel.setForeground(StreetColor.getFontColor(field
							.getFieldGroup().getColor()));
				}
				if (buyField.getOwner().getId() == this.tradeMe.getId()) {
					if (myFields.contains(buyField)) {
						myFields.remove(buyField);
					} else {
						myFields.add(buyField);
					}
					if (myCards
							.isAncestorOf(tradeCardPanel[field.getPosition()])) {
						myCards.remove(tradeCardPanel[field.getPosition()]);
					} else {
						myCards.remove(tradeCardPanel[field.getPosition()]);
						myCards.add(tradeCardPanel[field.getPosition()]);
					}
					myCards.repaint();
					myCards.revalidate();
				} else if (this.tradePartner != null
						&& buyField.getOwner().getId() == this.tradePartner
								.getId()) {
					if (hisFields.contains(buyField)) {
						hisFields.remove(buyField);
					} else {
						hisFields.add(buyField);
					}
					if (hisCards.isAncestorOf(tradeCardPanel[field
							.getPosition()])) {
						hisCards.remove(tradeCardPanel[field.getPosition()]);
					} else {
						hisCards.remove(tradeCardPanel[field.getPosition()]);
						hisCards.add(tradeCardPanel[field.getPosition()]);
					}
					hisCards.repaint();
					hisCards.revalidate();
				}

				repaint();
				revalidate();
			}
		}

	}
	
	public boolean getOtherTrade(){
		return otherTrade;
	}

}
