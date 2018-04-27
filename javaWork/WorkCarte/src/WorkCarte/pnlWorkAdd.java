package WorkCarte;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import MyF.Function;

public class pnlWorkAdd extends JPanel implements infWork, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3810220666232513017L;
	txfWork txfTicket = new txfWork();
	JTextPane txpTitle = new JTextPane();
	txfWork txfStartDate = new txfWork();
	txfWork txfEndDate = new txfWork();
	txfWork txfHour = new txfWork();
	JTextPane txpCarte = new JTextPane();
	JTextPane txpMemo = new JTextPane();
	
	JButton btSave = new JButton("Save");
	
	public pnlWorkAdd() {
		txfTicket.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txpTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txfStartDate.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txfEndDate.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txfHour.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txpCarte.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txpMemo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		btSave.addActionListener(this);
		
		JPanel pl1 = new JPanel();
		JPanel pl1_1 = new JPanel();
		JPanel pl1_1_1 = new JPanel();
		JPanel pl1_1_2 = new JPanel();
		
		JPanel pl1_2 = new JPanel();
		JPanel pl1_2_1 = new JPanel();
		JPanel pl1_2_2 = new JPanel();
		
		JPanel pl2 = new JPanel();
		
		setLayout(new BorderLayout());
		pl1.setLayout(new BorderLayout());
		pl2.setLayout(new FlowLayout());
		pl1_1.setPreferredSize(new Dimension(FRAME_WIDTH[FRM_MAIN] / 4, FRAME_HEIGHT[FRM_MAIN]));
		pl1.add(pl1_1, BorderLayout.WEST);
		pl1.add(pl1_2, BorderLayout.CENTER);
		
		pl1_1.setLayout(new BorderLayout());
		pl1_1_1.setPreferredSize(new Dimension(100, FRAME_HEIGHT[FRM_MAIN]));
		pl1_1_1.setLayout(new GridLayout(5, 1));
		pl1_1_2.setLayout(new GridLayout(5, 1));
		pl1_1.add(pl1_1_1, BorderLayout.WEST);
		pl1_1.add(pl1_1_2, BorderLayout.CENTER);
		
		pl1_2.setLayout(new BorderLayout());
		pl1_2_1.setLayout(new BorderLayout());
		pl1_2_2.setLayout(new BorderLayout());
		pl1_2.add(pl1_2_1, BorderLayout.NORTH);
		pl1_2.add(pl1_2_2, BorderLayout.CENTER);
		
		pl1_1_1.add(new lblWork("Ticket Number"));		pl1_1_2.add(txfTicket);
		pl1_1_1.add(new lblWork("Start Date"));			pl1_1_2.add(txfStartDate);
		pl1_1_1.add(new lblWork("End Date"));			pl1_1_2.add(txfEndDate);
		pl1_1_1.add(new lblWork("Work Hour"));			pl1_1_2.add(txfHour);
		pl1_1_1.add(new lblWork("Memo"));				pl1_1_2.add(Function.setScroll(txpMemo));

		pl1_2_1.add(new lblWork("Title"), BorderLayout.WEST);
		pl1_2_1.add(txpTitle, BorderLayout.CENTER);
		
		pl1_2_2.add(new lblWork("Work Carte"), BorderLayout.NORTH);
		pl1_2_2.add(Function.setScroll(txpCarte), BorderLayout.CENTER);
		
		pl2.add(btSave);
		pl2.setPreferredSize(new Dimension(this.getWidth(), 50));
		
		add(pl1, BorderLayout.CENTER);
		add(pl2, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btSave) {
			String[] data = new String[8];
			data[DATA_PR_ID] = String.valueOf(frmMain.iProject);
			data[DATA_TICKET] = txfTicket.getText();
			data[DATA_TITLE] = txpTitle.getText();
			data[DATA_START] = txfStartDate.getText();
			data[DATA_END] = txfEndDate.getText();
			data[DATA_HOUR] = txfHour.getText();
			data[DATA_MEMO] = txpMemo.getText();
			data[DATA_CARTE] = txpCarte.getText();
			
			String SQL = "";
			
			SQL = " INSERT INTO carte_data ( ";
			SQL += "		pr_id ";
			SQL += "		, cr_ticket ";
			SQL += "		, cr_title ";
			SQL += "		, cr_st_date ";
			SQL += "		, cr_ed_date ";
			SQL += "		, cr_hour ";
			SQL += "		, cr_memo ";
			SQL += "		, cr_carte ";
			SQL += " ) values ( ";
			SQL += "		?, ?, ?, ?, ?, ?, ?, ?";
			SQL += " ); ";
			
			try {
				if(db_function.saveTicketData(SQL, data)) {
					JOptionPane.showMessageDialog(this, "Success!!");
					pnlList.setTicketList();
					clearForm();
				} else {
					JOptionPane.showMessageDialog(this, "Failed...");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	void clearForm() {
		txfTicket.setText("");
		txpTitle.setText("");
		txfStartDate.setText("");
		txfEndDate.setText("");
		txfHour.setText("");
		txpMemo.setText("");
		txpCarte.setText("");
	}
	
}
