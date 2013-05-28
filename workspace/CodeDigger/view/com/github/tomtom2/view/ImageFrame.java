package com.github.tomtom2.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ImageFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String imagePath = "";
	private String imageName = "";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageFrame frame = new ImageFrame("/home/thomas/img.png", "img");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ImageFrame(String imagePath, String imageName) {
		setBounds(100, 100, 450, 300);
		this.imagePath = imagePath;
		this.imageName = imageName;
		
		this.setTitle(imageName);
		this.setVisible(true);
		this.setClosable(true);
		this.setResizable(true);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		ImageIcon icon = new ImageIcon(imagePath);  
	    JLabel label = new JLabel();  
	    label.setIcon(icon);  
	    panel.add(label); 
		getContentPane().add(panel, BorderLayout.CENTER);
	}

}
