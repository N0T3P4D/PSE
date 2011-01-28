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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.ojim.client.ClientBase;
import org.ojim.client.gui.CardBar.CardWindow;
import org.ojim.client.gui.GameField.GameField;
import org.ojim.client.gui.OLabel.FontLayout;
import org.ojim.client.gui.PopUpFrames.*;
import org.ojim.client.gui.RightBar.ChatMessage;
import org.ojim.client.gui.RightBar.ChatWindow;
import org.ojim.client.gui.RightBar.PlayerInfoWindow;
import org.ojim.language.Localizer;
import org.ojim.language.LanguageDefinition;
import org.ojim.logic.state.GameState;
import org.ojim.logic.state.Player;
import org.ojim.logic.state.fields.BuyableField;
import org.ojim.logic.state.fields.Field;
import org.ojim.logic.state.fields.Street;
import org.ojim.server.OjimServer;

public class GUIClient extends ClientBase {

	GUISettings settings;
	GameField gameField;
	ChatWindow chatWindow;
	PlayerInfoWindow playerInfoWindow;
	CardWindow cardWindow = new CardWindow();

	CreateGameFrame createGameFrame;
	JoinGameFrame joinGameFrame;
	SettingsFrame settingsFrame;
	HelpFrame helpFrame;
	AboutFrame aboutFrame;

	String name;

	MenuBar menubar;
	JPanel window;
	JPanel downRight;
	JButton buyButton;
	JButton rollButton;
	JButton endTurnButton;

	JFrame GUIFrame;

//	JPanel pane = new JPanel(new OJIMLayout());
	Localizer language;

	boolean haveIalreadyRolled = false;

	private MenuState menuState;

	public GUIClient() {

		language = new Localizer();

		LanguageDefinition[] langs = language.getLanguages();
		if (langs.length == 0) {
			System.out.println("No languagefile found.");
		}

		if (langs.length > 0)
			language.setLanguage(langs[0]);

		this.GUIFrame = new JFrame(language.getText("ojim"));
		this.menubar = new MenuBar(this.language, this);
		this.GUIFrame.setJMenuBar(this.menubar);
		this.GUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.GUIFrame.setMinimumSize(new Dimension(550, 450));
		this.GUIFrame.setVisible(true);

		// Nur zu Debugzwecken auf game
		setMenuState(MenuState.waitRoom);
		// setMenuState(MenuState.game);

		createGameFrame = new CreateGameFrame(language, this);
		joinGameFrame = new JoinGameFrame(language);
		settingsFrame = new SettingsFrame(language);
		helpFrame = new HelpFrame(language);
		aboutFrame = new AboutFrame(language);

		createGameFrame.setTitle(language.getText("create game"));
		joinGameFrame.setTitle(language.getText("join game"));
		settingsFrame.setTitle(language.getText("settings"));
		helpFrame.setTitle(language.getText("help"));
		aboutFrame.setTitle(language.getText("about"));

		chatWindow = new ChatWindow(language,this);
		
//		//xZise: WTF was macht dieser Kram?
//		try {
//			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//				if ("Windows".equals(info.getName())) {
//					UIManager.setLookAndFeel(info.getClassName());
//					break;
//				} else if ("Nimbus".equals(info.getName())) {
//					UIManager.setLookAndFeel(info.getClassName());
//					break;
//				} else if ("Metal".equals(info.getName())) {
//					UIManager.setLookAndFeel(info.getClassName());
//					break;
//				}
//			}
//			// Keines der Standarddesigns vorhanden. Nimm das was du hast.
//		} catch (Exception e) {
//			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//				try {
//					UIManager.setLookAndFeel(info.getClassName());
//				} catch (Exception e1) {
//					// Kein Look and Feel
//					e1.printStackTrace();
//				}
//			}
//		}
		
		// LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
		
		this.initialize();
	}

	public static void main(String[] args) {
		new GUIClient();
	}
	
	public void initialize() {
		this.GUIFrame.removeAll();
		this.GUIFrame.setLayout(new OJIMLayout());
		this.GUIFrame.setJMenuBar(this.menubar);
		switch (this.menuState) {
		case game:
			this.initializeGame();
			break;
		case waitRoom:
			this.initializeWaitingRoom();
			break;
		case mainMenu:
			this.initializeMenu();
			break;
		}
	}
	
	public void initializeMenu() {
		
	}
	
	public void initializeWaitingRoom() {
		setName("Max");

		JPanel leftWindow = new JPanel();
		this.chatWindow = new ChatWindow(language, this);
		leftWindow.add(this.chatWindow);
		
		JPanel rightWindow = new JPanel();

		rightWindow.setLayout(new GridLayout(0, 1));

		this.playerInfoWindow = new PlayerInfoWindow(language);
//		for (Player player : this.getGameState().getPlayers()) {
//			this.playerInfoWindow.addPlayer(player);
//		}
		rightWindow.add(this.playerInfoWindow);

		JButton readyButton = new JButton(language.getText("ready"));
		readyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIClient.this.ready();
			}
		});
		rightWindow.add(readyButton);
		
		this.GUIFrame.setLayout(new GridLayout(1, 0));
		
		this.GUIFrame.add(leftWindow);
		this.GUIFrame.add(rightWindow);
		
		// PRIVATE SERVER CONFIGURATION
		OjimServer server = new OjimServer("Philip");
		server.initGame(8, 7);
		connect(server);
		// REMOVE THESE STATEMENTS IF IT'S WORKING!
	}
	
	public void initializeGame() {
		// Gamefield
		this.gameField = new GameField(this.getGameState());		
		this.GUIFrame.add(gameField);

		// Right bar (players, chat, buttons)		
		JPanel rightTop = new JPanel(new GridLayout(0, 1));
		
		// Button bar
		JPanel buttonBar = new JPanel(new GridLayout(1, 0));
		
		this.rollButton = new JButton(language.getText("roll"));
		this.rollButton.setLayout(new FontLayout());
		this.rollButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!GUIClient.this.haveIalreadyRolled) {
					GUIClient.this.haveIalreadyRolled = true;
					GUIClient.this.rollButton.setEnabled(false);
					GUIClient.this.rollDice();
				}
			}
		});

		this.buyButton = new JButton(language.getText("buy"));
		this.buyButton.setLayout(new FontLayout());
		this.buyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIClient.this.accept();
			}
		});
		
		this.endTurnButton = new JButton(language.getText("endturn"));
		this.endTurnButton.setLayout(new FontLayout());
		this.endTurnButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIClient.this.endTurn();
			}
		});
		
		buttonBar.add(this.rollButton);
		buttonBar.add(this.buyButton);
		buttonBar.add(this.endTurnButton);
		
		rightTop.add(this.playerInfoWindow);
		rightTop.add(this.chatWindow);
		rightTop.add(buttonBar);
		
		this.GUIFrame.add(rightTop);

		// Cardstacks
		
		JPanel downWindow = new JPanel(new GridLayout(1, 0));

		cardWindow = new CardWindow();
		downWindow.add(cardWindow);

		this.GUIFrame.add(downWindow);
		
		GUIFrame.repaint();
		GUIFrame.setVisible(true);
		//System.out.println("Spielfeld gemalt.");
	}
	
	public void update() {
		switch (this.menuState) {
		case game :
			this.gameField.draw();
			if (this.haveIalreadyRolled && this.isMyTurn()) {
				boolean buyable = false;
				Field yourPos = this.getGameState().getFieldAt(this.getMe().getPosition());
				if (yourPos instanceof BuyableField) {
					this.buyButton.setEnabled((!((BuyableField) yourPos).getOwner().equals(this.getMe())) && ((BuyableField) yourPos).getPrice() <= this.getMe().getBalance());
				}
				this.endTurnButton.setEnabled(true);
			} else {
				this.rollButton.setEnabled(this.isMyTurn());
				this.endTurnButton.setEnabled(false);
			}
			this.buyButton.setEnabled(false);
			break;
		case mainMenu :
			break;
		case waitRoom :
			break;
		}
	}

	public void setMenuState(MenuState menuState) {
		this.menuState = menuState;
		this.initialize();
	}

	@Override
	public void onBuy(Player player, BuyableField field) {
		gameField.playerBuysField(player, field);
		this.update();


		//System.out.println("Meista, da hat wer was gekauft!");
		//draw();

		// TODO if player = gui player => feld and cardBar schicken zum
		// aufnehmen
		// Wo finde ich heraus ob ich der GUI Player bin?

	}

	@Override
	public void onCashChange(Player player, int cashChange) {
		playerInfoWindow.changeCash(player, cashChange);
		this.update();
	}

	@Override
	public void onConstruct(Street street) {
		gameField.buildOnStreet(street);
		this.update();
	}

	@Override
	public void onDestruct(Street street) {
		gameField.destroyOnStreet(street);
		this.update();
	}

	@Override
	public void onMessage(String text, Player sender, boolean privateMessage) {
		chatWindow.write(new ChatMessage(sender, privateMessage, text));
		this.update(); // hmmmm
	}

	@Override
	public void onMortgageToogle(BuyableField street) {
		cardWindow.switchCardStatus(street);
		gameField.switchFieldStatus(street);
		this.update();
	}

	@Override
	public void onMove(Player player, int position) {
		// TODO: (v. xZise) position kann negativ sein (z.B. Gefängnis)
		// this.menuState = MenuState.game;
		
		this.update();
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
		this.setMenuState(MenuState.game);
	}

	@Override
	public void onTurn(Player player) {
		playerInfoWindow.turnOn(player);
		if (this.isMyTurn()) {
			this.rollButton.setEnabled(true);
			this.buyButton.setEnabled(false);
			this.endTurnButton.setEnabled(false);
		}
		System.out.println("Player has changed to "+player.getName());
	}

	@Override
	public void setName(String name) {
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
		aboutFrame.draw();
		aboutFrame.setVisible(true);

	}

	public void openHelpWindow() {
		helpFrame.draw();
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
		createGameFrame.setLanguage(language);
		joinGameFrame.setTitle(language.getText("join game"));
		joinGameFrame.setLanguage(language);
		settingsFrame.setTitle(language.getText("settings"));
		settingsFrame.setLanguage(language);
		helpFrame.setTitle(language.getText("help"));
		helpFrame.setLanguage(language);
		aboutFrame.setTitle(language.getText("about"));
		aboutFrame.setLanguage(language);
		menubar.language(language);

	}

	@Override
	public void onAuction(int auctionState) {
		// TODO Auto-generated method stub

	}

	public void openSettingsWindow() {
		settingsFrame.draw();
		settingsFrame.setVisible(true);

	}

	public void startServer() {
		menuState = MenuState.waitRoom;
		createGameFrame.setVisible(false);
		this.update();
	}

	@Override
	public void onNewPlayer(Player player) {
		this.playerInfoWindow.addPlayer(player);
	}

	@Override
	public void onPlayerLeft(Player player) {
		this.playerInfoWindow.removePlayer(player);
	}
	
	public void sendOutMessage(String text){
		sendMessage(text);
	}

}
