package com.github.tomtom2.view;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.JSVGScrollPane;

public class ZoomablePanel extends JPanel {

	// private BufferedImage image;
	private JSVGCanvas svgCanvas = new JSVGCanvas();
	private double scale = 1.0;

	public ZoomablePanel(String imagePath) {
		
		String url = new File(imagePath).toURI().toString();
		svgCanvas.setURI(url);
		svgCanvas.setAutoscrolls(true);
		svgCanvas.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		svgCanvas.setAnimationLimitingFPS(5);
		this.add(svgCanvas);

		svgCanvas.setEnableImageZoomInteractor(true);
		svgCanvas.setBounds(0, 0, this.getWidth(), this.getHeight());
		svgCanvas.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				int mod = arg0.getWheelRotation();
				if(mod>0 && scale>0.1){ scale = scale - 0.1; }
				if(mod<0){ scale = scale + 0.1; }
				svgCanvas.setRenderingTransform(AffineTransform
						.getScaleInstance(scale, scale));
				repaint();
			}
		});
		
		this.setBounds(svgCanvas.getX(), svgCanvas.getY(), svgCanvas.getWidth(), svgCanvas.getHeight());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setImage(String pathToImage) {
		svgCanvas.setURI(pathToImage);
	}

}
