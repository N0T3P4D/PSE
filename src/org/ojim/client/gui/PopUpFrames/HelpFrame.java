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

package org.ojim.client.gui.PopUpFrames;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.ojim.language.Localizer;
import org.ojim.language.Localizer.TextKey;

public class HelpFrame extends JFrame {
	
	private Localizer language;
	private JLabel helpText;
	
	public HelpFrame(Localizer language) {
		ImageIcon icon = new ImageIcon("g4468.png");
		this.setIconImage(icon.getImage());
		this.language = language;
		
		setMinimumSize(new Dimension(500, 500));
		helpText = new JLabel("<html>" + this.language.getText(TextKey.HELP_TEXT));
		
		this.add(helpText);
		
		this.pack();
	}

	public void setLanguage(Localizer language) {
		this.language = language;
		if(isVisible()){
			draw();
		}
		
	}

	public void draw() {
		remove(helpText);
		helpText = new JLabel("<html>" + this.language.getText(TextKey.HELP_TEXT));
		
		this.add(helpText);
		
		this.repaint();
		setVisible(true);
		this.pack();
		
	}
	
}
