package ImageProcessing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageProcessing02_5 {

	public static void main(String[] args) {
		BufferedImage original = null;
		try {
			original = ImageIO.read(new File("image.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Graphics g = original.getGraphics();
		
		g.setColor(Color.RED);
		g.fillOval(70, 70, 100, 100);
		g.setColor(Color.GREEN);
		g.fillOval(20, 120, 100, 100);
		g.setColor(Color.BLUE);
		g.fillOval(70, 170, 100, 100);
		g.setColor(Color.BLACK);
		g.fillOval(120, 120, 100, 100);
		
		try {
			ImageIO.write(original, "png", new File("ImageEdit.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
