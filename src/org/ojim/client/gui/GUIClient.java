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
	
	private void draw(){

		GUIFrame.removeAll();
		
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
		
		menubar = new MenuBar(language,this);
		System.out.println("Test3");
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
	public void informAuction(int auctionState) {
		// TODO Auto-generated method stub
		super.informAuction(auctionState);
	}

	@Override
	public void informBankruptcy() {
		// TODO Auto-generated method stub
		super.informBankruptcy();
	}

	@Override
	public void informBuy(int player, int position) {
		gameField.playerBuysField(this.getGameState().getPlayerByID(player), this.getGameState().getFieldAt(position));
		
		//TODO if player = gui player => feld and cardBar schicken zum aufnehmen
		// Wo finde ich heraus ob ich der GUI Player bin?
		
	}

	@Override
	public void informCardPull(String text, boolean communityCard) {
		// TODO Auto-generated method stub
		// Mittelfeld
		super.informCardPull(text, communityCard);
	}

	@Override
	public void informCashChange(int player, int cashChange) {
		playerInfoWindow.changeCash(this.getGameState().getPlayerByID(player), cashChange);
	}

	@Override
	public void informConstruct(int street) {
		gameField.buildOnStreet(this.getGameState().getFieldByID(street));
	}

	@Override
	public void informDestruct(int street) {
		gameField.destroyOnStreet(this.getGameState().getFieldByID(street));
	}

	@Override
	public void informDiceValues(int[] diceValues) {
		// TODO Mittelfeld
		super.informDiceValues(diceValues);
	}

	@Override
	public void informMessage(String text, int sender, boolean privateMessage) {
		chatWindow.write(new ChatMessage(this.getGameState().getPlayerByID(sender), privateMessage, text));
	}

	@Override
	public void informMortgageToogle(int street) {
		cardWindow.switchCardStatus(this.getGameState().getFieldByID(street));
		gameField.switchFieldStatus(this.getGameState().getFieldByID(street));
	}

	@Override
	public void informMove(int position, int playerId) {
		gameField.playerMoves(this.getGameState().getFieldAt(position),this.getGameState().getPlayerByID(playerId));
	}

	@Override
	public void informStartGame(int[] ids) {
		this.menuState = MenuState.game;
		
		// TODO IDS! -> Sind das die Spieler?
		
	}

	@Override
	public void informTrade(int actingPlayer, int partnerPlayer) {
		// TODO Auto-generated method stub
		super.informTrade(actingPlayer, partnerPlayer);
	}

	@Override
	public void informTurn(int player) {
		playerInfoWindow.turnOn(this.getGameState().getPlayerByID(player));
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
		for(int i = 0; i < language.getLanguages().length; i++){
			if(language.getLanguages()[i].name.equals(languageName)){
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

}
