package org.ojim.client.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {

	enum MenuBarState {
		mainMenu, waitRoom, game
	};
	
	private MenuBarState menuBarState;
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenuItem createGame;
	private JMenuItem joinGame;
	private JMenuItem leaveGame;
	private JMenu settings;
	private JMenuItem directConnection;
	private JMenuItem serverList;
	private JMenuItem exit;
	private JMenuItem about;
	private JMenuItem help;
	
	public MenuBar() {
		
		menuBarState = MenuBarState.mainMenu;
		GUILocalizer language = new GUILocalizer();
		//language.setLanguage("DE-de");
		language.setLanguage("EN-uk");
		language.setLanguage("FR-fr");
		
		// TODO: Lokalisierung
        fileMenu = new JMenu(language.getText("Datei"));
        editMenu = new JMenu(language.getText("?"));

        createGame = new JMenuItem(language.getText("Spiel erstellen"));
        joinGame = new JMenuItem(language.getText("Spiel beitreten"));
        leaveGame = new JMenuItem(language.getText("Spiel verlassen"));
        settings = new JMenu(language.getText("Einstellungen"));

        directConnection = new JMenuItem(language.getText("Direkte Verbindung"));
        serverList = new JMenuItem(language.getText("Serverliste"));

        exit = new JMenuItem(language.getText("Beenden"));

        about = new JMenuItem(language.getText("Über Ojim"));
        help = new JMenuItem(language.getText("Hilfe"));
        
        draw();
        
        
        
        
	}
	
	public void switchMenuBarState(MenuBarState state){
		menuBarState = state;
	}
	
	public void draw(){

        add(fileMenu);
        add(editMenu);
        
        fileMenu.add(createGame);
        fileMenu.add(joinGame);
        
        if(menuBarState.equals(MenuBarState.game)){
        	fileMenu.add(leaveGame);
        }
        
        fileMenu.add(settings);
        fileMenu.add(exit);

        settings.add(directConnection);
        settings.add(serverList);
        
        editMenu.add(about);
        editMenu.add(help);
        
	}

}
