package com.github.tomtom2.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class ImageFrame extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageFrame frame = new ImageFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ImageFrame() {
		setBounds(100, 100, 450, 300);

	}

}
