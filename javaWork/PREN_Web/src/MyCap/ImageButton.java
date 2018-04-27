package MyCap;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageButton {
	final int MAX_COUNT = 10;
	
	BufferedImage img;
	BufferedImage img_on1;
	BufferedImage img_on2;
	int width = -1;
	int height = -1;
	int x = -1, y = -1;
	int count;
	
	boolean isClick = false;
	
	public ImageButton(BufferedImage bi, BufferedImage on1, BufferedImage on2) {
		img = bi;
		img_on1 = on1;
		img_on2 = on2;
		
		width = img.getWidth();
		height = img.getHeight();
	}
	
	public void setSize(int w, int h) {
		width = w;
		height = h;
	}
	
	public void setLocation(int ix, int iy) {
		x = ix;
		y = iy;
	}
	
	public boolean checkClick(int px, int py) {
		boolean result = false;
		int w1 = x, w2 = x + width;
		int h1 = y, h2 = y + height;
		
		if(px >= w1 && px <= w2 && py >= h1 && py <= h2) {
			result = true;
		}
		
		isClick = result;
		
		return result;
	}
	
	public void drawButton(Graphics g) {
		count++;
		
		if(isClick) {
			if(count / (MAX_COUNT / 2) == 0) {
				 g.drawImage(img_on1, x, y, null);
			} else {
				 g.drawImage(img_on2, x, y, null);
			}
		} else {
			g.drawImage(img, x, y, null);
		}
		
		if(count >= MAX_COUNT) count = 0; 
	}
	
}
