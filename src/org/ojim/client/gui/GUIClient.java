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

package org.ojim.client.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.ojim.client.ClientBase;
import org.ojim.client.gui.CardBar.CardWindow;
import org.ojim.client.gui.GameField.GameField;
import org.ojim.client.gui.PopUpFrames.*;
import org.ojim.client.gui.RightBar.ChatMessage;
import org.ojim.client.gui.RightBar.ChatWindow;
import org.ojim.client.gui.RightBar.PlayerInfoWindow;
import org.ojim.language.Localizer;
import org.ojim.language.LanguageDefinition;
import org.ojim.logic.state.GameState;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.fields.BuyableField;
import org.ojim.logic.state.fields.Street;

public class GUIClient extends ClientBase {

	GUISettings settings;
	GameField gameField;
	ChatWindow chatWindow;
	PlayerInfoWindow playerInfoWindow;
	CardWindow cardWindow;

	CreateGameFrame createGameFrame;
	JoinGameFrame joinGameFrame;
	SettingsFrame settingsFrame;
	HelpFrame helpFrame;
	AboutFrame aboutFrame;

	MenuBar menubar;

	JFrame GUIFrame;

	JPanel pane = new JPanel(new OJIMLayout());
	Localizer language;

	private MenuState menuState;

	public GUIClient() {

		// Nur zu Debugzwecken auf game
		setMenuState(MenuState.mainMenu);

		createGameFrame = new CreateGameFrame();
		joinGameFrame = new JoinGameFrame();
		settingsFrame = new SettingsFrame();
		helpFrame = new HelpFrame();
		aboutFrame = new AboutFrame();

		language = new Localizer();

		LanguageDefinition[] langs = language.getLanguages();
		if (langs.length == 0) {
			System.out.println("No languagefile found.");
		}

		if (langs.length > 0)
			language.setLanguage(langs[0]);

		createGameFrame.setTitle(language.getText("create game"));
		joinGameFrame.setTitle(language.getText("join game"));
		settingsFrame.setTitle(language.getText("settings"));
		helpFrame.setTitle(language.getText("help"));
		aboutFrame.setTitle(language.getText("about"));

		GUIFrame = new JFrame(language.getText("ojim"));

		// LookAndFeel lookAndFeel = UIManager.getLookAndFeel();

		draw();

	}

	private void draw() {

		GUIFrame.removeAll();
		GUIFrame = new JFrame(language.getText("ojim"));

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				} else if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				} else if ("Metal".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
			// Keines der Standarddesigns vorhanden. Nimm das was du hast.
		} catch (Exception e) {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (Exception e1) {
					// Kein Look and Feel
					e1.printStackTrace();
				}
			}
		}

		menubar = new MenuBar(language, this);
		GUIFrame.setJMenuBar(menubar);

		switch (menuState) {

		case mainMenu:

			// TODO Ein schönes Bild, oder ein Vorschauspiel vielleicht?

			break;

		case waitRoom:

			JPanel window = new JPanel();
			JPanel leftWindow = new JPanel();
			JPanel rightWindow = new JPanel();

			window.setLayout(new GridLayout(1, 0));
			rightWindow.setLayout(new GridLayout(0, 1));

			playerInfoWindow = new PlayerInfoWindow(language);
			chatWindow = new ChatWindow(language);

			rightWindow.add(playerInfoWindow);
			leftWindow.add(chatWindow);

			JButton button;
			button = new JButton(language.getText("ready"));
			rightWindow.add(button);

			window.add(leftWindow);
			window.add(rightWindow);

			GUIFrame.add(window);
			break;

		case game:

			gameField = new GameField();

			gameField.init(GameState.FIELDS_AMOUNT, this.getGameState());

			gameField.draw();

			pane.add(gameField);

			JPanel rightWindow1 = new JPanel();

			rightWindow1.setLayout(new GridLayout(0, 1));

			playerInfoWindow = new PlayerInfoWindow(language);
			chatWindow = new ChatWindow(language);

			rightWindow1.add(playerInfoWindow);
			rightWindow1.add(chatWindow);

			pane.add(rightWindow1);

			JPanel downWindow = new JPanel();

			downWindow.setLayout(new GridLayout(1, 0));

			cardWindow = new CardWindow();

			downWindow.add(cardWindow);

			pane.add(downWindow);

			JPanel downRight = new JPanel();
			downRight.setLayout(new GridLayout(1, 0));

			JButton button1;

			button1 = new JButton(language.getText("buy"));
			downRight.add(button1);
			button1 = new JButton(language.getText("roll"));
			downRight.add(button1);

			pane.add(downRight);

			GUIFrame.add(pane);
			break;

		}

		GUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GUIFrame.setMinimumSize(new Dimension(550, 450));

		GUIFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new GUIClient();
	}

	public void setMenuState(MenuState menuState) {
		this.menuState = menuState;
	}

	@Override
	public void onBuy(Player player, BuyableField field) {
		gameField.playerBuysField(player, field);
		
		
		
		//TODO if player = gui player => feld and cardBar schicken zum aufnehmen
		// Wo finde ich heraus ob ich der GUI Player bin?

	}

	@Override
	public void onCashChange(Player player, int cashChange) {
		playerInfoWindow.changeCash(player, cashChange);
	}

	@Override
	public void onConstruct(Street street) {
		gameField.buildOnStreet(street);
	}

	@Override
	public void onDestruct(Street street) {
		gameField.destroyOnStreet(street);
	}

	@Override
	public void onMessage(String text, Player sender, boolean privateMessage) {
		chatWindow.write(new ChatMessage(sender, privateMessage, text));
	}

	@Override
	public void onMortgageToogle(BuyableField street) {
		cardWindow.switchCardStatus(street);
		gameField.switchFieldStatus(street);
	}

	@Override
	public void onMove(Player player, int position) {
		//TODO: (v. xZise) position kann negativ sein (z.B. Gefängnis)
		gameField.playerMoves(this.getGameState().getFieldAt(position), player);
	}

	@Override
	public void onTrade(Player actingPlayer, Player partnerPlayer) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onBankruptcy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCardPull(String text, boolean communityCard) {
		// TODO Auto-generated method stub
		// Mittelfeld
	}

	@Override
	public void onDiceValues(int[] diceValues) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartGame(Player[] players) {
		this.menuState = MenuState.game;

		// TODO IDS! -> Sind das die Spieler?
		//xZise: Korrekt		
	}

	@Override
	public void onTurn(Player player) {
		playerInfoWindow.turnOn(player);
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName(name);
	}

	public MenuState getMenuState() {
		return menuState;
	}

	public GameField getGameField() {
		return gameField;
	}

	public ChatWindow getChatWindow() {
		return chatWindow;
	}

	public PlayerInfoWindow getPlayerInfoWindow() {
		return playerInfoWindow;
	}

	public CardWindow getCardWindow() {
		return cardWindow;
	}

	public void openCreateGameWindow() {
		createGameFrame.setVisible(true);

	}

	public void leaveGame() {
		// TODO Game beenden

		menuState = MenuState.mainMenu;

	}

	public void openJoinGameWindow() {
		joinGameFrame.showJoin();
		joinGameFrame.setVisible(true);

	}

	public void openServerListWindow() {
		joinGameFrame.showServerList();
		joinGameFrame.setVisible(true);

	}

	public void openDirectConnectionWindow() {
		joinGameFrame.showDirectConnection();
		joinGameFrame.setVisible(true);

	}

	public void openAboutWindow() {
		aboutFrame.setVisible(true);

	}

	public void openHelpWindow() {
		helpFrame.setVisible(true);

	}

	public void changeLanguage(String languageName) {
		for (int i = 0; i < language.getLanguages().length; i++) {
			if (language.getLanguages()[i].name.equals(languageName)) {
				language.setLanguage(language.getLanguages()[i]);
				resetLanguage();
			}
		}

	}

	private void resetLanguage() {
		GUIFrame.setTitle(language.getText("ojim"));
		createGameFrame.setTitle(language.getText("create game"));
		joinGameFrame.setTitle(language.getText("join game"));
		settingsFrame.setTitle(language.getText("settings"));
		helpFrame.setTitle(language.getText("help"));
		aboutFrame.setTitle(language.getText("about"));
		menubar.language(language);

	}

	@Override
	public void onAuction(int auctionState) {
		// TODO Auto-generated method stub
		
	}

}
