package PCA_Frame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class pnlImage extends JPanel {
	
	BufferedImage img = null;
	int iWidth = 600, iWidthMin = 600;
	int iHeight = 600, iHeightMin = 600;

	public pnlImage() {
		setPreferredSize(new Dimension(600, 600));
	}
	
	public void setImage(BufferedImage img) {
		this.img = img;
		if(img.getWidth() > 600) {
			iWidth = img.getWidth();
		} else {
			iWidth = iWidthMin;
		}
		
		if(img.getHeight() > 600) {
			iHeight = img.getHeight();
		} else {
			iHeight = iHeightMin;
		}
		setPreferredSize(new Dimension(iWidth, iHeight));
		repaint();
	}
	
	protected void paintComponent(Graphics g2) {
		// TODO Auto-generated method stub
		super.paintComponent(g2);
		BufferedImage buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = buffer.getGraphics();
		if(img != null) {
			g.drawImage(img, 0, 0, null);
		}
		
		g2.drawImage(buffer, 0, 0, null);
	}
	
}
