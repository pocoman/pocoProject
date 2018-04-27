package WorkCarte;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;

import MyF.Function;

public class pnlProject extends JPanel implements infWork, ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -145470858673982747L;
	lstList<String, Integer> lstProject = new lstList<String, Integer>();
	JButton btOpen = new JButton("Open");
	JButton btNew = new JButton("New");
	JButton btEdit = new JButton("Edit");
	JButton btDelete = new JButton("Delete");
	
	long loClickTime = -1;
	int chkIndex = -1;
	
	public pnlProject() {
		loadProjectData();
		
		
		JPanel pn1 = new JPanel();
		pn1.setLayout(new FlowLayout());
		lstProject.addMouseListener(this);
		btOpen.addActionListener(this);		pn1.add(btOpen);
		btNew.addActionListener(this);		pn1.add(btNew);
		btEdit.addActionListener(this);		pn1.add(btEdit);
		btDelete.addActionListener(this);	pn1.add(btDelete);
	    
		setPreferredSize(FRAME_SIZE[FRM_PROJECT]);
	    setLayout(new BorderLayout());
		add(Function.setScroll(lstProject), BorderLayout.CENTER);
		add(pn1, BorderLayout.SOUTH);
	}
	
	private void openMainFrame() {
		try {
			int iProject = lstProject.getSelectedData();
			frmProject._frame.setVisible(false);
			new frmMain(iProject, lstProject.getSelectedValue());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void loadProjectData() {
		Vector<String> vtrTitle = new Vector<String>();
		ArrayList<Integer> arrData = new ArrayList<Integer>();
		Integer[] iArrData = null;
		
		ResultSet rs = db_function.loadProjectData();
		
		try {
			while(rs.next()) {
				vtrTitle.add(rs.getString("pr_title"));
				arrData.add(rs.getInt("pr_id"));
			}
			iArrData = new Integer[arrData.size()];
			for(int i = 0; i < iArrData.length; i++) {
				iArrData[i] = arrData.get(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lstProject.removeAll();
		lstProject.setListData(vtrTitle, iArrData);
			
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btOpen) {
			openMainFrame();
		} else if(ae.getSource() == btNew) {
			frmProject._frame.setVisible(false);
			new frmProjectForm();
		} else if(ae.getSource() == btEdit) {
			try {
				int iProject = lstProject.getSelectedData();
				frmProject._frame.setVisible(false);
				new frmProjectForm(iProject);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if(ae.getSource() == btDelete) {
			
		}
	}

	public void mousePressed(MouseEvent me) {
		if (me.getSource() == lstProject) {
			long loDiffTime = System.currentTimeMillis() - loClickTime;
			if(500 <= loDiffTime || chkIndex != lstProject.getSelectedIndex()) {
				loClickTime = System.currentTimeMillis();
				chkIndex = lstProject.getSelectedIndex();
			} else {
				openMainFrame();
			}
		}
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
