package WorkCarte;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import MyF.Function;

public class pnlList extends JPanel implements infWork, ListSelectionListener, MouseListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5649151783503827304L;
	static lstList<String, Integer> TicketList = new lstList<String, Integer>();
	JTextField txfSearch = new JTextField();
	
	public pnlList() {
		TicketList.addListSelectionListener(this);
		TicketList.addMouseListener(this);
		setTicketList();
		
		txfSearch.addKeyListener(this);
		
		JPanel pn1 = new JPanel();
		lblWork lblSearch = new lblWork("Search");
		lblSearch.setPreferredSize(new Dimension(50, 20));
		
		pn1.setLayout(new BorderLayout());
		pn1.add(lblSearch, BorderLayout.WEST);
		pn1.add(txfSearch, BorderLayout.CENTER);
		
		setPreferredSize(PANEL_SIZE[PNL_WORKLIST]);
		setLayout(new BorderLayout());
		
		add(Function.setScroll(TicketList), BorderLayout.CENTER);
		add(pn1, BorderLayout.SOUTH);
		
	}
	
	static void setTicketList() {
		int iProject = frmMain.iProject;
		Vector<String> vtrTitle = new Vector<String>();
		ArrayList<Integer> arrData = new ArrayList<Integer>();
		
		ResultSet rs = db_function.loadTicketData(iProject);
		
		try {
			while(rs.next()) {
				vtrTitle.add(rs.getString("cr_title"));
				arrData.add(rs.getInt("cr_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int iData = -1;
		if(TicketList.getSelectedIndex() >= 0) {
			iData = TicketList.getSelectedData();
		}
		if(vtrTitle.size() <= 0)  {
			TicketList.clearAllItem();
			pnlProjectHeader.setTicketUrl(null);
			pnlWorkView.setTicket(null);
		} else {
			TicketList.setListData(vtrTitle, arrData);
			if(TicketList.getModel().getSize() > 0) {
				if(iData < 0) {
					TicketList.setSelectedIndex(0);
				} else {
					TicketList.setSelectedIndexByData(iData);
				}
			} else {
				pnlProjectHeader.setTicketUrl(null);
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent le) {
		if(TicketList.getSelectedIndex() >= 0) {
			int iProject = frmMain.iProject;
			int iTicket = TicketList.getSelectedData();
			ResultSet rs1 = db_function.loadTicketData(iProject, iTicket);
			ResultSet rs2 = db_function.loadTicketData(iProject, iTicket);
			
			try {
				rs1.next();
				String strTicket = rs1.getString("cr_ticket");
				pnlWorkView.setTicket(rs2);
				pnlProjectHeader.setTicketUrl(strTicket);
				rs1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		pnlMain.WorkPanel.setSelectedIndex(0);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		// TODO Auto-generated method stub
		if(ke.getKeyCode() == KeyEvent.VK_ENTER) {
			TicketList.searchData(txfSearch.getText().trim());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
