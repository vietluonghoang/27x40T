package entities.customized;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JViewport;

import entities.GeneralSettings;
import utils.Utils;

public class ImageViewport extends JViewport {
	private BufferedImage bgImg;

	public ImageViewport() {
		super();
		initializeBackgroundImage();
		// TODO Auto-generated constructor stub
	}

	private void initializeBackgroundImage() {
		try {
			bgImg = new Utils().getBufferedImageFromResourceFile(GeneralSettings.defaultLogViewBackgroundImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (bgImg != null) {
			Rectangle bounds = getViewRect();
			BufferedImage resizedImage = new BufferedImage(bounds.width, bounds.width, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = resizedImage.createGraphics();
			graphics2D.drawImage(bgImg, 0, 0, bounds.width, bounds.width, null);
			graphics2D.dispose();
			g.drawImage(resizedImage, 0, 0, this);
		}
	}
}
