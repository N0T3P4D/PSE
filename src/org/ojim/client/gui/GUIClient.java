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
import org.ojim.client.gui.RightBar.ChatWindow;
import org.ojim.client.gui.RightBar.PlayerInfoWindow;
import org.ojim.language.Localizer;
import org.ojim.language.LanguageDefinition;

public class GUIClient extends ClientBase {

	GUISettings settings;
	GameField gameField;
	ChatWindow chatWindow;
	PlayerInfoWindow playerInfoWindow;
	CardWindow cardWindow;

	JFrame GUIFrame;

	JPanel pane = new JPanel(new OJIMLayout());

	private MenuState menuState;

	public GUIClient() {

		setMenuState(MenuState.game);

//		LanguageDefinition languageDefinition = new LanguageDefinition(
//				"Maximilian", "English", "English", "eng", new File(
//						"org/ojim/language/langs/eng.lang"));
		// LanguageDefinition languageDefinition = new LanguageDefinition(
		// "Maximilian", "Deutsch", "German", "deu", new File(
		// "org/ojim/language/langs/deu.lang"));

		Localizer language = new Localizer();
		LanguageDefinition[] langs = language.getLanguages();
		if (langs.length == 0) {
			System.out.println("No languagefile found.");
		}
		for (LanguageDefinition lang : langs) {
			//System.out.println("Found language: " + lang.name + " (" + lang.englishName + " code: " + lang.code + ")");
		}

		if (langs.length > 0)
			language.setLanguage(langs[0]);

		GUIFrame = new JFrame(language.getText("ojim"));

		MenuBar menubar = new MenuBar(language);

		GUIFrame.setJMenuBar(menubar);

		// LookAndFeel lookAndFeel = UIManager.getLookAndFeel();

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

		GUIFrame.setMinimumSize(new Dimension(500, 450));

		GUIFrame.setVisible(true);

	}

	public static void main(String[] args) {
		new GUIClient();
	}

	public void setMenuState(MenuState menuState) {
		this.menuState = menuState;
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

}
