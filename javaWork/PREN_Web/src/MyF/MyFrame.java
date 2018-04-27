package MyF;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6335953302125128862L;
	JPanel BackPanel = new JPanel();
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

		BackPanel.setPreferredSize(new Dimension(FrameWidth, FrameHeight));
		add(BackPanel);
		pack();
	}
	
	public static void BasicSetting(JFrame j) {
		j.setDefaultCloseOperation(EXIT_ON_CLOSE);
		j.setVisible(true);
		j.setLayout(new GridLayout(1, 1));
		j.setResizable(false);
		
	}
	
	public JPanel getPanel() {
		return BackPanel;
	}
	
}
