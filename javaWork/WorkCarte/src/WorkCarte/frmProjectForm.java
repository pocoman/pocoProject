package WorkCarte;

import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class frmProjectForm extends JFrame implements infWork, WindowListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4094008566031387427L;
	public static int iProject = -1;

	frmProjectForm(int value) {
		iProject = value;
		setInit();
	}
	frmProjectForm() {
		iProject = -1;
		setInit();
	}
	
	private void setInit() {
		setLayout(new GridLayout(1, 1));
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(FRAME_LOCATION[FRM_PRONEW]);
		addWindowListener(this);
		
		if(iProject != -1) {
			add(new pnlProjectForm(iProject));
		} else {
			add(new pnlProjectForm());
		}
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
