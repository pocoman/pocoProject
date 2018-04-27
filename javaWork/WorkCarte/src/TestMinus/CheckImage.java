package TestMinus;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import MyF.ImageF;

public class CheckImage extends JPanel implements ActionListener, KeyListener {
	
	final static int COLOR_COLLECT = 0x00FFFFFF;
	final static int COLOR_WRONG = 0x55FF0000;
	
	final static int PAGE_ORI = 0;
	final static int PAGE_TGT = 1;
	final static int PAGE_CHK = 2;
	
	public static BufferedImage imgOri = null, imgTgt = null, imgCheck = null;
	public static int iNowPage = PAGE_ORI;
	public static int iViewX = 0;
	public static int iViewY = 0;
	
	static {
		imgOri = ImageF.getImageByPath("./01_Original.png");
		imgTgt = ImageF.getImageByPath("./02_Edit.png");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame();
		f.setLayout(new GridLayout(1, 1));
		f.add(new CheckImage());
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
	}
	
	public CheckImage() {
		setPreferredSize(new Dimension(1000, 800));
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
		Timer t = new Timer(1000 / 24, this);
		t.start();
		imgMinus();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		switch(iNowPage) {
		case PAGE_ORI:
			g.drawImage(imgOri, iViewX, iViewY, this);
			break;
		case PAGE_TGT:
			g.drawImage(imgTgt, iViewX, iViewY, this);
			break;
		case PAGE_CHK:
			g.drawImage(imgTgt, iViewX, iViewY, this);
			g.drawImage(imgCheck, iViewX, iViewY, this);
			break;
		}
	}

	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		if(null != imgCheck) {
			if(KeyEvent.VK_LEFT == e.getKeyCode()) {
				iViewX = iViewX - 50;
			}
			if(KeyEvent.VK_RIGHT == e.getKeyCode()) {
				iViewX = iViewX + 50;
			}
			if(KeyEvent.VK_UP == e.getKeyCode()) {
				iViewY = iViewY - 50;
			}
			if(KeyEvent.VK_DOWN == e.getKeyCode()) {
				iViewY = iViewY + 50;
			}
		}
		if(KeyEvent.VK_O == e.getKeyCode()) {
			if(iNowPage > PAGE_ORI) iNowPage--;
		} else if(KeyEvent.VK_P == e.getKeyCode()) {
			if(iNowPage < PAGE_CHK) iNowPage++;
		}
	}
	public void keyReleased(KeyEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		if(null == imgCheck) {
			imgMinus();
		}
	}
	private void imgMinus() {
		if(null != imgOri && null != imgTgt) {
			int iWidth = imgOri.getWidth();
			int iHeight = imgOri.getHeight();
			if(iWidth >= imgTgt.getWidth()) {
				iWidth = imgTgt.getWidth();
			}
			if(iHeight >= imgTgt.getHeight()) {
				iHeight = imgTgt.getHeight();
			}
			int[] imgOriArr = ImageF.getImageData(imgOri);
			int[] imgTgtArr = ImageF.getImageData(imgTgt);
			int[] imgMinus = new int[iWidth * iHeight];
			for(int i = 0; i < iHeight; i++) {
				for(int j = 0; j < iWidth; j++) {
					int i1 = imgOriArr[i * iWidth + j];
					int i2 = imgTgtArr[i * iWidth + j];
					
					if(0 < Math.abs(i1 - i2)) {
						imgMinus[i * iWidth + j] = COLOR_WRONG;
					} else {
						imgMinus[i * iWidth + j] = COLOR_COLLECT;
					}
				}
			}
			imgCheck = new BufferedImage(iWidth, iHeight, BufferedImage.TYPE_4BYTE_ABGR);
			imgCheck.setRGB(0, 0, iWidth, iHeight, imgMinus, 0, iWidth);
		}
	}
	
}
