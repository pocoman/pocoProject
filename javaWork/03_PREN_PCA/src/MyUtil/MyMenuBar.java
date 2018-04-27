package MyUtil;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5724886223349157672L;

	public JMenu addMenu(String str) {
		JMenu result = new JMenu(str);
		add(result);
		return result;
	}
	
	public JMenuItem addMenuitem(JMenu m, String str) {
		JMenuItem result = new JMenuItem(str);
		m.add(result);
		
		return result;
	}
	
	public JMenuItem addMenuitem(JMenu m, String str, ActionListener ali) {
		JMenuItem result = new JMenuItem(str);
		m.add(result);
		result.addActionListener(ali);
		
		return result;
	}
	
}
