package org.ojim.client.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {

	public MenuBar() {
        JMenu fileMenu = new JMenu("Datei");
        JMenu editMenu = new JMenu("?");
        add(fileMenu);
        add(editMenu);
        
	}

}
