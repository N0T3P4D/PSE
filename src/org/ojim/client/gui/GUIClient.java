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
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.ojim.client.ClientBase;
import org.ojim.client.gui.GameField.GameField;
import org.ojim.language.Localizer;
import org.ojim.language.LanguageDefinition;

public class GUIClient extends ClientBase implements ComponentListener {

	GUISettings settings;
	GameField gameField;
	ChatWindow chatWindow;
	PlayerInfoWindow playerInfoWindow;

	JFrame GUIFrame;

	JPanel pane = new JPanel(new GridBagLayout());

	private MenuState menuState;
	
	public GUIClient() {
		
		setMenuState(MenuState.mainMenu);

		
		// LanguageDefinition languageDefinition = new LanguageDefinition("Maximilian", "English", "English", "eng", new File("org/ojim/language/eng.lang") );
		LanguageDefinition languageDefinition = new LanguageDefinition("Maximilian", "deutsch", "German", "deu", new File("org/ojim/language/deu.lang") );
		
		Localizer language = new Localizer();
		language.getLanguages();
		
		
		//language.setLanguage("deu");
		language.setLanguage(languageDefinition);
		//language.setLanguage("fra");
		
		GUIFrame = new JFrame("OJim");

		
		MenuBar menubar = new MenuBar(language);

		GUIFrame.setJMenuBar(menubar);

		//LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
		
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

		//GridLayout experimentLayout = new GridLayout(0, 2);
		

		//GUIFrame.addComponentListener(this);
		pane.addComponentListener(this);
		//GUIFrame.addComponentListener(this);
		

		
		GUIFrame.setSize(640, 360);
		
		GUIFrame.show();

		fill();

	}
	
	
	public static void main (String[] args){
		new GUIClient();
	}

	public void setMenuState(MenuState menuState) {
		this.menuState = menuState;
	}

	public MenuState getMenuState() {
		return menuState;
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
        //System.out.println(" --- Resized ");   
        
        //System.out.println("Main: "+arg0.getComponent().getWidth());
        //System.out.println(pane.getWidth());
        //System.out.println(pane.getHeight());
        /*
        try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
        fill();
        
		GUIFrame.add(pane);
        
		
	}
	
	void fill(){

        pane.removeAll();
        
        int width = pane.getWidth();
        int height = pane.getHeight();
        
    	pane.setLayout(new OJIMLayout());
    	GridBagConstraints c = new GridBagConstraints();

        if(width<300){
        	//GUIFrame.setPreferredSize(new Dimension(300, 350));
        	//pane.setPreferredSize(new Dimension(300, 300));
        	GUIFrame.setSize(new Dimension(400, 400));
        } else {
        	width -= 10;
        }
        if(height<300){
        	//GUIFrame.setPreferredSize(new Dimension(300, 350));
        	GUIFrame.setSize(new Dimension(400, 400));
	    } else {
	    	height -= 30;
	    }
        

        if(height>width*1.3){
        	//GUIFrame.setPreferredSize(new Dimension(300, 350));
        	GUIFrame.setSize(new Dimension(height, height));
	    }
        
        
    	//pane.setLayout(new GridBagLayout());
    	//GridBagConstraints c = new GridBagConstraints();
        
        JButton button;
        

		button = new JButton("Upleft");
		//button.setBounds(0, 0, (int) (height*0.8), (int) (height*0.8));
		//button.setSize((int) (height*0.8), (int) (height*0.8));
		//button.setPreferredSize(new Dimension((int) (height*0.8), (int) (height*0.8)));
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 0.8;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(button,c);
		
		button = new JButton("Right");
		//button.setPreferredSize(new Dimension(width - (int) (height*0.8)-1, (int) (height*0.8)));
		c.weighty = 0.8;
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(button, c);
		

		
		button = new JButton("Down");
		//button.setPreferredSize(new Dimension((int) (height*0.8)-1, height - (int) (height*0.8)));
		c.weighty = 0.2;
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(button,c);
		
		
		button = new JButton("Downright");
		//button.setPreferredSize(new Dimension(width - (int) (height*0.8)-1, height - (int) (height*0.8)));
		c.weighty = 0.2;
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 1;
		c.gridy = 1;
		pane.add(button,c);
		GUIFrame.add(pane);
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
