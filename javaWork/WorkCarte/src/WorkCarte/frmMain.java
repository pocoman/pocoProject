package WorkCarte;

import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class frmMain extends JFrame implements infWork, WindowListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8683621193695838965L;
	public static int iProject = -1;
	
	frmMain(int value, String title) {
		iProject = value;
		setTitle(title);
		setLocation(FRAME_LOCATION[FRM_MAIN]);
		
		setLayout(new GridLayout(1, 1));
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(this);
		
		add(new pnlMain());
		pack();
	}

	public void windowClosed(WindowEvent e) {
		frmProject._frame.setVisible(true);
	}
	public void windowOpened(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
}

