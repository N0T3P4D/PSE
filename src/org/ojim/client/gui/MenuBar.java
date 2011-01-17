/*  Copyright (C) 2010  Fabian Neundorf, Philip Caroli, Maximilian Madlung, 
 * 						Usman Ghani Ahmed, Jeremias Mechler
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

import java.io.File;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.ojim.language.Localizer;
import org.ojim.language.LanguageDefinition;

public class MenuBar extends JMenuBar {
	
	private JMenu fileMenu;
	private JMenu editMenu;
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
	
	public MenuBar() {
		
		menuBarState = MenuState.mainMenu;
		
		Localizer language = null;
		
		language(language);
        
        draw();
        
        
        
        
	}
	
	public MenuBar(Localizer language) {
		
		menuBarState = MenuState.mainMenu;
		
		
		language(language);
        
        draw();
        
        
	}
	
	public void setMenuBarState(MenuState menuBarState){
		this.menuBarState = menuBarState;
	}
	
	public void language(Localizer language){
		
		// TODO: Lokalisierung
        fileMenu = new JMenu(language.getText("file"));
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
	}
	
	public void switchMenuBarState(MenuState state){
		menuBarState = state;
	}
	
	public void draw(){

        add(fileMenu);
        add(editMenu);
        
        fileMenu.add(createGame);
        fileMenu.add(joinGame);
        
        if(menuBarState.equals(MenuState.game)){
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
