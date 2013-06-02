package com.github.tomtom2.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ZoomablePanel extends JPanel implements MouseWheelListener {

	private ImageIcon icon;
	
	
	public ZoomablePanel(ImageIcon icon){
		this.icon = icon;
		this.setSize(icon.getIconWidth(), icon.getIconHeight());
		this.repaint(0, 0, getWidth(), getHeight());
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		System.out.println(arg0.getWheelRotation());
		int rot = arg0.getWheelRotation();
		
		int newWidth = (int)(getWidth() - rot*0.1*getWidth());
		int newHeight = (int)(getHeight() - rot*0.1*getHeight());
		this.setSize(newWidth, newHeight);
		this.getParent().setSize(newWidth, newHeight);
		this.repaint();
		this.getParent().repaint();
	}
	
	public void paintComponent(Graphics g){
		Dimension d = getSize();
		g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	
}
