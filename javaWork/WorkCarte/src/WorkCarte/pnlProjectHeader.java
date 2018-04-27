package WorkCarte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;

import MyF.Function;

public class pnlProjectHeader extends JPanel implements infWork, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1315057694880469215L;
	final static int URL_PROJECT = 0;
	final static int URL_TICKET_LIST = 1;
	final static int URL_NEW_TICKET = 2;
	final static int URL_NOW_TICKET = 3;

	JButton btPro = new JButton("Project");
	JButton btTicList = new JButton("Ticket List");
	JButton btTicNew = new JButton("New Ticket");
	JButton btTicOpen = new JButton("Now Ticket");
	
	static String[] strArrUrl = new String[4];
	
	public pnlProjectHeader() {
		setUrl();
		
		btPro.addActionListener(this);			add(btPro);
		btTicList.addActionListener(this);		add(btTicList);
		btTicNew.addActionListener(this);		add(btTicNew);
		btTicOpen.addActionListener(this);		add(btTicOpen);
	}
	
	void setUrl() {
		int iProject = frmMain.iProject;
		
		ResultSet rs_pro = db_function.loadProjectData(iProject);
		
		try {
			rs_pro.next();
			strArrUrl[URL_PROJECT] = REDMINE_BASIC_URL + REDMINE_PROJECT  + "/" +  rs_pro.getString("pr_key");
			strArrUrl[URL_TICKET_LIST] = strArrUrl[URL_PROJECT] + "/" + REDMINE_TICKET;
			strArrUrl[URL_NEW_TICKET] = strArrUrl[URL_PROJECT] + "/" + REDMINE_NEW_TICKET;
			rs_pro.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource() == btPro) {
			Function.OpenURL(strArrUrl[URL_PROJECT]);
		} else if(ae.getSource() == btTicList) {
			Function.OpenURL(strArrUrl[URL_TICKET_LIST]);
		} else if(ae.getSource() == btTicNew) {
			Function.OpenURL(strArrUrl[URL_NEW_TICKET]);
		} else if(ae.getSource() == btTicOpen) {
			if(strArrUrl[URL_NOW_TICKET] != null) {
				Function.OpenURL(strArrUrl[URL_NOW_TICKET]);
			}
		}	
	}
	
	public static void setTicketUrl(String ticket) {
		if(ticket == null) {
			strArrUrl[URL_NOW_TICKET] = null;
		} else {
			strArrUrl[URL_NOW_TICKET] = REDMINE_BASIC_URL + REDMINE_TICKET + "/" + ticket;		
		}
	}

}
