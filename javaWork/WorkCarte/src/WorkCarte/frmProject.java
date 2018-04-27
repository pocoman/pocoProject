package WorkCarte;

import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;

import javax.swing.JFrame;

import MyF.DB_MySQL;

public class frmProject extends JFrame implements infWork, WindowListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3404854485008606711L;

	public static frmProject _frame = null;
	
	static Connection con;
	static {
		con = DB_MySQL.getMyConnection("localhost", "carte", "Check1234@");
		DB_MySQL.useDB(con, "workcarte");
	}
	
	pnlProject ProjectPanel = new pnlProject();
	
	frmProject() {
		addWindowListener(this);
		setLayout(new GridLayout(1, 1));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(FRAME_LOCATION[FRM_PROJECT]);
		
		add(ProjectPanel);
		pack();
		
		_frame = this;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		ProjectPanel.loadProjectData();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
