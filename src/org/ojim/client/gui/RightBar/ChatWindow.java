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

package org.ojim.client.gui.RightBar;

import java.awt.BorderLayout;
import java.awt.FontMetrics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.ojim.client.gui.GUIClient;
import org.ojim.client.gui.OLabel.FontLayout;
import org.ojim.language.Localizer;
import org.ojim.language.Localizer.TextKey;

public class ChatWindow extends JPanel {

	private static final long serialVersionUID = 5179104588584714072L;
	
	private Localizer language;
	private LinkedList<ChatMessage> messages = new LinkedList<ChatMessage>();
	private JTextArea textArea;
	private JTextField textField;
	private GUIClient gui;
	private JButton sendButton = new JButton();
	private JPanel chatWindowPanel = new JPanel();
	JScrollBar scrollPane = new JScrollBar(JScrollBar.VERTICAL);

	// http://download.oracle.com/javase/tutorial/uiswing/components/scrollpane.html#scrollable
	
	public ChatWindow(Localizer language, GUIClient guiClient) {
		super();

		this.gui = guiClient;

		this.setLayout(new GridBagLayout());
		
		chatWindowPanel.setLayout(new BorderLayout());

		textArea = new JTextArea();
		

		textArea.setEditable(false);
		//textArea.add(new JScrollPane(textArea));
		// textPane.append("Zeile 1\nZeile 2\nZeile3\nZeile4");

		// Zeigt die erste Zeile an,
		// indem dort der Caret positioniert wird
		// textPane.setCaretPosition(4);
		scrollPane.setBlockIncrement(1);

		scrollPane.setValue(90);
		
		AdjustmentListener scrollListener = new AdjustmentListener() {
			
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				//textArea.setText("    New Value is " + e.getValue() + "      ");
			      repaint();
	                FontMetrics fm = textArea.getFontMetrics(textArea.getFont());
	                int rowHeight = fm.getHeight();
	                int rows = textArea.getLineCount();
	                //JScrollBar bar = scroller.getVerticalScrollBar();
	                scrollPane.setValue(rowHeight);
	                revalidate();

			      
				
			}
		};

		scrollPane.addAdjustmentListenerdrawingArea.revalidate();
(scrollListener);
		

		chatWindowPanel.add(textArea, BorderLayout.CENTER);
		chatWindowPanel.add(scrollPane, BorderLayout.EAST);
		
		this.add(chatWindowPanel, new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
		
		this.revalidate();
		
		JPanel textPanel = new JPanel();

		textPanel.setLayout(new GridLayout(1, 0));
		
        textArea.setColumns(20);
        textArea.setRows(5);

		
		
		textField = new JTextField("");
		textField.setLayout(new FontLayout());

		this.add(textPanel, new GridBagConstraints(0, 1, 2, 1, 1.0, 0.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));

		sendButton.setLayout(new FontLayout());

		ActionListener sendListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();

			}
		};

		sendButton.setText(language.getText(TextKey.SEND_MESSAGE));
		sendButton.addActionListener(sendListener);

		textPanel.add(textField);

		textPanel.add(sendButton);
	}

	private void sendMessage() {
		if (this.textField.getText().length() > 0) {
			gui.sendOutMessage(this.textField.getText());
			this.textField.setText("");
		}
	}

	public void clear() {

	}

	public void setLanguage(Localizer language) {
		this.language = language;
		sendButton.setText(language.getText(TextKey.SEND_MESSAGE));
	}

	public void write(ChatMessage chatMessage) {
		messages.add(chatMessage);
		if (chatMessage.isPrivate) {
			textArea.append(language.getText(TextKey.PRIVATE_MESSAGE) + ": ");
		}
		if (chatMessage.player == null) {
			textArea.append(" -Server- " + chatMessage.message + "\n");
		} else {
			textArea.append(" <" + chatMessage.player.getName() + "> "
					+ chatMessage.message + "\n");
		}
	}

}
