package MyF;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyMenuBar extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenuBar mb;
	public MyMenuBar() {
		super("Menubar");
		mb = new JMenuBar();
		setJMenuBar(mb);
		
		JMenu file = new JMenu("File");
		JMenuItem fileNew = new JMenuItem("New File");
		JMenuItem fileOpen = new JMenuItem("Open");
		JMenuItem fileExit = new JMenuItem("Exit");
		
		file.add(fileNew);
		file.add(fileOpen);
		file.addSeparator();
		file.add(fileExit);
		
		mb.add(file);
		setSize(250, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);			
	}
	
}
