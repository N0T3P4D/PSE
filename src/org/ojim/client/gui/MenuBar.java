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
		
		// TODO: Lokalisierung
        fileMenu = new JMenu("Datei");
        editMenu = new JMenu("?");

        createGame = new JMenuItem("Spiel erstellen");
        joinGame = new JMenuItem("Spiel beitreten");
        leaveGame = new JMenuItem("Spiel verlassen");
        settings = new JMenu("Einstellungen");

        directConnection = new JMenuItem("Direkte Verbindung");
        serverList = new JMenuItem("Serverliste");

        exit = new JMenuItem("Beenden");

        about = new JMenuItem("Über OJim");
        help = new JMenuItem("Hilfe");
        
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
