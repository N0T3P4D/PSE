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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.ojim.language.Localizer;

/**
 * Die Menubar ist oben am Frame verankert und bietet dem Benutzer mehr <br>
 * Optionen und Aktionen
 *
 */
public class MenuBar extends JMenuBar {

	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenu langMenu;
	private JMenuItem createGame;
	private JMenu joinGame;
	private JMenuItem leaveGame;
	private JMenuItem settings;
	private JMenuItem directConnection;
	private JMenuItem serverList;
	private JMenuItem exit;
	private JMenuItem about;
	private JMenuItem help;
	private MenuState menuBarState;
	private GUIClient gui;
	private String languageName;
	
	/**
	 * Der Konstruktor startet die Menubar
	 */
	public MenuBar() {

		menuBarState = MenuState.mainMenu;

		Localizer language = null;

		language(language);

	}
	
	/**
	 * Der erweiterte Konstruktor
	 * @param language die Sprache
	 * @param gui eine Referenz auf die GUI
	 */
	public MenuBar(Localizer language, GUIClient gui) {

		menuBarState = MenuState.mainMenu;

		this.gui = gui;
		
		language(language);
		

	}

	/**
	 * Stellt den Status der Menubar um
	 * @param menuBarState auf diesen neuen Status
	 */
	public void setMenuBarState(MenuState menuBarState) {
		this.menuBarState = menuBarState;
		draw();
	}

	/**
	 * Setzt die Sprache neu
	 * @param language auf den angegebenen Localizer
	 */
	public void language(Localizer language) {

		removeAll();
		revalidate();

		fileMenu = new JMenu(language.getText("file"));
		langMenu = new JMenu(language.getText("languages"));
		editMenu = new JMenu(language.getText("?"));

		createGame = new JMenuItem(language.getText("create game"));
		joinGame = new JMenu(language.getText("join game"));
		leaveGame = new JMenuItem(language.getText("leave game"));
		settings = new JMenuItem(language.getText("settings"));

		directConnection = new JMenuItem(language.getText("direct connection"));
		serverList = new JMenuItem(language.getText("list of servers"));

		exit = new JMenuItem(language.getText("exit"));

		about = new JMenuItem(language.getText("about"));
		help = new JMenuItem(language.getText("help"));

		for (int i = 0; i < language.getLanguages().length; i++) {
			languageName = language.getLanguages()[i].name;

			JMenuItem langItem = new JMenuItem(languageName);
			langItem.setName(languageName);

			ActionListener languageListener = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					gui.changeLanguage(((JMenuItem) (e.getSource())).getName());
				}
			};

			langItem.addActionListener(languageListener);

			langMenu.add(langItem);
		}
		draw();

	}

	/**
	 * Zeichnet die MenuBar
	 */
	public void draw() {

		add(fileMenu);
		add(langMenu);
		add(editMenu);

		ActionListener createGameListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gui.openCreateGameWindow();
			}
		};

		createGame.addActionListener(createGameListener);

		ActionListener joinGameListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gui.openJoinGameWindow();
			}
		};

		joinGame.addActionListener(joinGameListener);

		ActionListener directConnectionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gui.openDirectConnectionWindow();
			}
		};

		directConnection.addActionListener(directConnectionListener);

		ActionListener serverListListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gui.openServerListWindow();
			}
		};

		serverList.addActionListener(serverListListener);

		ActionListener leaveGameListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gui.leaveGame();
			}
		};

		leaveGame.addActionListener(leaveGameListener);
		
		ActionListener settingsListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gui.openSettingsWindow();
			}
		};

		settings.addActionListener(settingsListener);

		ActionListener exitListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		};

		exit.addActionListener(exitListener);

		ActionListener aboutListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gui.openAboutWindow();
			}

		};

		about.addActionListener(aboutListener);

		ActionListener helpListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gui.openHelpWindow();
			}

		};

		help.addActionListener(helpListener);

		fileMenu.add(createGame);

		fileMenu.add(joinGame);

		if (menuBarState.equals(MenuState.game)) {
			fileMenu.add(leaveGame);
		}

		fileMenu.add(settings);

		fileMenu.add(exit);

		joinGame.add(directConnection);
		joinGame.add(serverList);

		editMenu.add(about);
		editMenu.add(help);

	}

}
