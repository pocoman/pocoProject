import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class test {
	public static void main(String[] args) {
		int width = 100;
		int height = 100;
		int color = 0;
		int[] rgb = new int[width * height];
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				int index = (i * width) + j;
				switch(index / 2500) {
				case 0: 	color = 0xFFFF0000; break;
				case 1: 	color = 0xFF00FF00; break;
				case 2: 	color = 0xFF0000FF; break;
				default: 	color = 0xFF000000; break;
				}
				rgb[index] = color;
			}
		}
		
		img.setRGB(0, 0, width, height, rgb, 0, width);
		try {
			ImageIO.write(img, "png", new File("img.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
