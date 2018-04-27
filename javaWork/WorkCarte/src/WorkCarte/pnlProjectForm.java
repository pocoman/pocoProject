package WorkCarte;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class pnlProjectForm extends JPanel implements infWork, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8326777997696781972L;
	int iProject = -1;

	JTextField txfKey = new JTextField();
	JTextField txfTitle = new JTextField();
	JButton btSave = new JButton("Save");
	JButton btReset = new JButton("Reset");
	
	ResultSet rs = null;
	
	pnlProjectForm() {
		iProject = -1;
		rs = null;
		setInit();
	}
	pnlProjectForm(int value) {
		iProject = value;
		setData();
		setInit();
	}
	
	void setData() {
		rs = db_function.loadProjectData(iProject);
		
		try {
			rs.next();
			txfKey.setText(rs.getString("pr_key"));
			txfTitle.setText(rs.getString("pr_title"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void clearText() {
		txfTitle.setText("");
		txfKey.setText("");
	}
	
	void setInit() {
		btSave.addActionListener(this);
		btReset.addActionListener(this);
		
		setPreferredSize(FRAME_SIZE[FRM_PRONEW]);
		setLayout(new BorderLayout());

		JPanel pn1 = new JPanel();
		JPanel pn1_1 = new JPanel();
		JPanel pn1_2 = new JPanel();
		JPanel pn2 = new JPanel();
		pn1.setLayout(new BorderLayout());
		pn1_1.setPreferredSize(PANEL_SIZE[PNL_PROEDIT]);
		pn1_1.setLayout(new GridLayout(2, 1));
		pn1_2.setLayout(new GridLayout(2, 1));
		
		pn2.setLayout(new FlowLayout());

		pn1.add(pn1_1, BorderLayout.WEST);
		pn1.add(pn1_2, BorderLayout.CENTER);
		pn1_1.add(new lblWork("Key"));			pn1_2.add(txfKey);
		pn1_1.add(new lblWork("Title"));		pn1_2.add(txfTitle);
		
		pn2.add(btSave);	pn2.add(btReset);
		
		add(pn1, BorderLayout.CENTER);
		add(pn2, BorderLayout.SOUTH);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btSave) {
			if(iProject == -1) {
				if(txfKey.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(this, "Please Input Project Key");
				} else if(txfTitle.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(this, "Please Input Project Title");
				} else {
					String SQL = "";
					SQL = " INSERT INTO project_data ( ";
					SQL += "		pr_title ";
					SQL += "		, pr_key ";
					SQL += "		, pr_url ";
					SQL += " ) values ( ";
					SQL += " 		?, ?, ? ";
					SQL += " ); ";

					String strTitle = txfTitle.getText().trim();
					String strKey = txfKey.getText().trim();
					String strUrl = REDMINE_BASIC_URL + REDMINE_PROJECT + "/" + strKey;
					try {
						if(db_function.saveProjectData(SQL, strTitle, strKey, strUrl)) {
							clearText();
							JOptionPane.showMessageDialog(this, "Success!!");
						} else {
							JOptionPane.showMessageDialog(this, "Failed...");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				if(txfKey.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(this, "Please Input Project Key");
				} else if(txfTitle.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(this, "Please Input Project Title");
				} else {
					String SQL = "";
					SQL = " UPDATE project_data ";
					SQL += " SET	pr_title = ?";
					SQL += "		, pr_key = ?";
					SQL += "		, pr_url = ?";
					SQL += " WHERE pr_id = ?;";

					String strTitle = txfTitle.getText().trim();
					String strKey = txfKey.getText().trim();
					String strUrl = REDMINE_BASIC_URL + REDMINE_PROJECT + strKey;
					System.out.println(strKey);
					try {
						if(db_function.saveProjectData(SQL, strTitle, strKey, strUrl, iProject)) {
							JOptionPane.showMessageDialog(this, "Success!!");
						} else {
							JOptionPane.showMessageDialog(this, "Failed...");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		} else if(ae.getSource() == btReset) {
			if(iProject == -1) {
				txfKey.setText("");
				txfTitle.setText("");
			} else {
				try {
					txfKey.setText(rs.getString("pr_key"));
					txfTitle.setText(rs.getString("pr_title"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
