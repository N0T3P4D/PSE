package org.ojim.client.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

public class OJIMLayout implements LayoutManager {

    private int minWidth = 400, minHeight = 400;
    private int preferredWidth = 1600, preferredHeight = 900;
    private boolean sizeUnknown = true;
	
	@Override
	public void addLayoutComponent(String arg0, Component arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
    private void setSizes(Container parent) {
        int nComps = parent.getComponentCount();
        Dimension d = null;

        //Reset preferred/minimum width and height.
        preferredWidth = 0;
        preferredHeight = 0;
        minWidth = 0;
        minHeight = 0;

        for (int i = 0; i < nComps; i++) {
            Component c = parent.getComponent(i);
            if (c.isVisible()) {
                d = c.getPreferredSize();

                if (i > 0) {
                    preferredWidth += d.width/2;
                } else {
                    preferredWidth = d.width;
                }
                preferredHeight += d.height;

                minWidth = Math.max(c.getMinimumSize().width,
                                    minWidth);
                minHeight = preferredHeight;
            }
        }
    }

	@Override
    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();
        int maxWidth = parent.getWidth()
                       - (insets.left + insets.right);
        int maxHeight = parent.getHeight()
                        - (insets.top + insets.bottom);
        int nComps = parent.getComponentCount();
        int xFudge = 0, yFudge = 0;
        boolean oneColumn = false;

        // Go through the components' sizes, if neither
        // preferredLayoutSize nor minimumLayoutSize has
        // been called.
        if (sizeUnknown) {
            setSizes(parent);
        	//System.out.println("Handlungsbedarf");
        }

        if (maxWidth <= minWidth) {
            oneColumn = true;
        }

        if (maxWidth != preferredWidth) {
            xFudge = (maxWidth - preferredWidth)/(nComps - 1);
        }

        if (maxHeight > preferredHeight) {
            yFudge = (maxHeight - preferredHeight)/(nComps - 1);
        }

        for (int i = 0 ; i < nComps ; i++) {
            Component c = parent.getComponent(i);
            

            int squareHeight = (int) (parent.getHeight()*0.8);
            int restWidth = (int) (parent.getWidth()-squareHeight);
            int restHeight = (int) (parent.getHeight()-squareHeight);
            
            if(restWidth < parent.getHeight()*0.2){
            	parent.setSize(parent.getHeight(), parent.getHeight());
            }
            if (c.isVisible()) {
                Dimension d = c.getPreferredSize();

                
                
                switch(i){
                case 0:

                    c.setBounds(0, 0, squareHeight, squareHeight);
                    break;
                case 1:

                    c.setBounds(squareHeight, 0, restWidth, squareHeight);
                    break;
                case 2:

                    c.setBounds(0, squareHeight, squareHeight, restHeight);
                    break;
                case 3:

                    c.setBounds(squareHeight, squareHeight, restWidth, restHeight);
                    break;
                }
            }
        }
    }

	@Override
	public Dimension minimumLayoutSize(Container arg0) {
		return new Dimension(minWidth,minHeight);
	}

	@Override
	public Dimension preferredLayoutSize(Container arg0) {
		return new Dimension(preferredWidth,preferredHeight);
	}

	@Override
	public void removeLayoutComponent(Component arg0) {
		// TODO Auto-generated method stub
		
	}

}
