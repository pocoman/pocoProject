package MyUtil;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6335953302125128862L;
	int FrameWidth, FrameHeight;
	
	public MyFrame(int width, int height) {
		FrameWidth = width;
		FrameHeight = height;
		setTitle("My Frame Ver 0.1");
		BasicSetting(this);
	}
	
	public MyFrame(int width, int height, String title) {
		FrameWidth = width;
		FrameHeight = height;
		setTitle(title);
		BasicSetting(this);
	}
	
	private static void BasicSetting(JFrame j) {
		j.setDefaultCloseOperation(EXIT_ON_CLOSE);
		j.setLayout(new GridLayout(1, 1));
		j.setVisible(true);
		j.setResizable(false);
		
	}
	
}
