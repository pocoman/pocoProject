package MyCap;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class CapFrame extends JFrame {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2139076663995961737L;
	
	static GraphicsEnvironment ge = GraphicsEnvironment
			.getLocalGraphicsEnvironment();
	static GraphicsDevice gd = ge.getDefaultScreenDevice();
	static final int ConsoleWidth = gd.getDisplayMode().getWidth();
	static final int ConsoleHeight = gd.getDisplayMode().getHeight();
	
	public CapFrame() {
		setLayout(new GridLayout(1, 1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new CapPanel());
		pack();
		
		setLocation(ConsoleWidth / 2 - getWidth() / 2, ConsoleHeight / 2 - getHeight() / 2);
		setVisible(true);
	}

}
