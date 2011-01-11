package org.ojim.client.gui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import org.ojim.client.ClientBase;
import org.ojim.client.gui.GameField.GameField;

public class GUIClient extends ClientBase {

	GUISettings settings;
	GameField gameField;
	ChatWindow chatWindow;
	PlayerInfoWindow playerInfoWindow;

	JFrame GUIFrame;

	public GUIClient() {

		GUIFrame = new JFrame("OJim");

		MenuBar menubar = new MenuBar();

		GUIFrame.setJMenuBar(menubar);

		LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
/*
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			UIManager
			.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			System.out.print("lol");
			//e.printStackTrace();
		}*/
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Windows".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		        else if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		        else if ("Metal".equals(info.getName())) {
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

		GridLayout experimentLayout = new GridLayout(0, 2);

		JPanel pane = new JPanel(experimentLayout);

		GUIFrame.add(pane);
		
		GUIFrame.setSize(640, 360);
		
		GUIFrame.show();

	}
	
	public static void main (String[] args){
		new GUIClient();
	}

}
