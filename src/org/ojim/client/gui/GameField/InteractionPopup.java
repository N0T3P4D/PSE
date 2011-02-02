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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.ojim.client.gui.GUIClient;
import org.ojim.client.gui.PlayerColor;
import org.ojim.language.Localizer;
import org.ojim.logic.state.Card;
import org.ojim.logic.state.GameState;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.fields.BuyableField;

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
	
	private String upgradeFieldname;
	private int position;
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
	private JButton okButton;
	private JLabel okButtonLabel;
	
	private int[] tradePositions;
	private JPanel[] tradeCardPanel;

	private ActionListener upgradeListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.upgradeField(position,Integer.parseInt(upgradeTextField.getText()));
			deleteUpgrade();
		}
	};;;
	
	private ActionListener downgradeListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.downgradeField(position,Integer.parseInt(upgradeTextField.getText()));
			deleteUpgrade();
		}
	};;;
	

	private ActionListener tradeOkListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.trade(Integer.parseInt(myMoneyField.getText())-Integer.parseInt(hisMoneyField.getText()), null, 0);
		}
	};;;
	
	//private 

	/** 
	 * Diese Methode initialisiert alles.
	 * @param guiClient 
	 */
	public InteractionPopup(GUIClient guiClient) {
		
		this.gui = guiClient;
		this.setBackground(Color.BLACK);
		
		tradePositions = new int[GameState.FIELDS_AMOUNT];
		tradeCardPanel = new JPanel[GameState.FIELDS_AMOUNT];
		
		
		
		this.diceValues = new JLabel[2];
		dicePanel.add(diceTextLabel);
		for(int i = 0; i < diceValues.length; i++){
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
		//upgradePanel.add(upgradeTextField);
		upgradePanel.add(upgradeButton);
		upgradePanel.add(downgradeButton);
		upgradePanel.setBackground(Color.WHITE);
		
		upgradeButton.addActionListener(upgradeListener );
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
		myCards = new JPanel();
		hisCards = new JPanel();
		okButton = new JButton();

		myTradePanel.add(myName);
		myTradePanel.add(myMoney);
		myTradePanel.add(myMoneyField);
		myTradePanel.add(myCards);

		hisTradePanel.add(hisName);
		hisTradePanel.add(hisMoney);
		hisTradePanel.add(hisMoneyField);
		hisTradePanel.add(hisCards);
		
		okButton.add(okButtonLabel = new JLabel());
		
		okButton.addActionListener(tradeOkListener);

		myTradePanel.setLayout(new GridLayout(0,1));
		tradePanel.add(myTradePanel);
		hisTradePanel.setLayout(new GridLayout(0,1));
		tradePanel.add(hisTradePanel);
		tradePanel.setLayout(new GridLayout(0,2));
		tradePanel.add(okButton);
		
		
	}

	public void clear() {

	}

	public void showMessage(String message, boolean accepting, boolean declining) {

	}

	public void showInformation(String message) {

	}

	public void draw() {
		
	}
	
	
	public void showAuction(){
		
	} 
	
	public void showTrade(Player me, Player partnerPlayer,
			int requiredCash, BuyableField[] requiredBuyableFields,
			int requiredOutOfJailCards, int offeredCash,
			BuyableField[] offeredBuyableFields, int offeredOutOfJailCards) {
		
		myName.setText(me.getName());
		hisName.setText(partnerPlayer.getName());
		myMoney.setText(language.getText("give money"));
		hisMoney.setText(language.getText("claim money"));
		myMoneyField.setColumns(5);
		hisMoneyField.setColumns(5);
		okButtonLabel.setText(language.getText("ok"));
		//myCards = new JPanel();
		//hisCards = new JPanel();
		
		this.add(tradePanel);

		this.repaint();
		this.revalidate();
		System.out.println("May the Trade begin!");
		
	}
	
	public void startAuction() {
		
	}
	
	public void close () {
		
	}

	public void showDices(int[] diceValues) {
		if(this.diceValues == null){
			this.diceValues = new JLabel[diceValues.length];
		}
		for(int i = 0; i < diceValues.length; i++){
			this.diceValues[i].setText(" "+diceValues[i]+" ");
		}
	}
	
	public void showFreeParkingCash(int cash){
		this.cash = cash;
	}
	
	public void setLanguage(Localizer language){
		this.language = language;
		diceTextLabel.setText(language.getText("dice values")+": ");
		freeParkingCashLabel.setText(language.getText("free parking cash")+": "+cash+" "+language.getText("currency"));
		
		upgradeTextLabel.setText(upgradeFieldname+": ");
		upgradeButtonLabel.setText(language.getText("upgrade"));
		downgradeButtonLabel.setText(language.getText("downgrade"));
		
		repaint();
	}

	public void showUpgrade(int parseInt, String fieldName) {
		
		this.position = parseInt;
		this.remove(upgradePanel);
		
		upgradeFieldname = fieldName;
		
		upgradeTextLabel.setText(language.getText("new upgrade level")+" "+upgradeFieldname+": ");

		System.out.println("Upgrade");
		upgradePanel.setLayout(new FlowLayout());
		this.add(upgradePanel);
		this.repaint();
		this.revalidate();
		
	}
	
	public void showTrade(int parseInt){
		
	}

	public void deleteUpgrade() {		
		this.remove(upgradePanel);
		this.repaint();
		this.revalidate();
		
	}

	public void endTrade() {
		// TODO Auto-generated method stub
		
	}
}
