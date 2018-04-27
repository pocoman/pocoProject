package WorkCarte;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import MyF.Function;

public class pnlWorkView extends JPanel implements infWork, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3055660536964620167L;
	static txfWork txfTicket = new txfWork();
	static JTextPane txpTitle = new JTextPane();
	static txfWork txfStartDate = new txfWork();
	static txfWork txfEndDate = new txfWork();
	static txfWork txfHour = new txfWork();
	static JTextPane txpCarte = new JTextPane();
	static JTextPane txpMemo = new JTextPane();
	static int iCr_id = -1;
	
	JButton btSave = new JButton("Save");
	
	public pnlWorkView() {
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
		
		JPanel pnHour = new JPanel();
		pnHour.setLayout(new BorderLayout());
		pnHour.add(txfHour, BorderLayout.CENTER);
		pnHour.add(new lblWork("H", lblWork.HOR_CENTER, lblWork.VER_CENTER), BorderLayout.EAST);
		
		pl1_1_1.add(new lblWork("Ticket Number"));		pl1_1_2.add(txfTicket);
		pl1_1_1.add(new lblWork("Start Date"));			pl1_1_2.add(txfStartDate);
		pl1_1_1.add(new lblWork("End Date"));			pl1_1_2.add(txfEndDate);
		pl1_1_1.add(new lblWork("Work Hour"));			pl1_1_2.add(pnHour);
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
	
	static void setTicket(ResultSet rs) {
		if(rs == null) {
			txfTicket.setText("");
			txpTitle.setText("");
			txfStartDate.setText("");
			txfEndDate.setText("");
			txfHour.setText("");
			
			txpMemo.setText("");
			txpCarte.setText("");
		} else {
			try {
				rs.next();
				iCr_id = rs.getInt("cr_id");
				txfTicket.setText(rs.getString("cr_ticket"));
				txpTitle.setText(rs.getString("cr_title"));

				Date cr_st_date = rs.getDate("cr_st_date");
				Date cr_ed_date = rs.getDate("cr_ed_date");
				if(cr_st_date == null) {
					txfStartDate.setText("");
				} else {
					txfStartDate.setText(new SimpleDateFormat("yyyy/MM/dd").format(cr_st_date));
				}
				if(cr_ed_date == null) {
					txfEndDate.setText("");
				} else {
					txfEndDate.setText(new SimpleDateFormat("yyyy/MM/dd").format(cr_ed_date));
				}
				txfHour.setText(rs.getString("cr_hour"));
				
				txpMemo.setText(rs.getString("cr_memo"));
				txpCarte.setText(rs.getString("cr_carte"));
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btSave) {
			if(iCr_id >= 0) {
				String[] data = new String[8];
				data[DATA_TICKET] = txfTicket.getText();
				data[DATA_TITLE] = txpTitle.getText();
				data[DATA_START] = txfStartDate.getText();
				data[DATA_END] = txfEndDate.getText();
				data[DATA_HOUR] = txfHour.getText();
				data[DATA_MEMO] = txpMemo.getText();
				data[DATA_CARTE] = txpCarte.getText();
				
				String SQL = "";
				SQL = " UPDATE carte_data ";
				SQL += " SET ";
				SQL += "		cr_ticket = ? ";
				SQL += "		, cr_title = ? ";
				SQL += "		, cr_st_date = ? ";
				SQL += "		, cr_ed_date = ? ";
				SQL += "		, cr_hour = ? ";
				SQL += "		, cr_memo = ? ";
				SQL += "		, cr_carte = ? ";
				SQL += " WHERE cr_id = ?;";
				
				try {
					if(db_function.saveTicketData(SQL, data, iCr_id)) {
						JOptionPane.showMessageDialog(this, "Success!!");
						pnlList.setTicketList();
					} else {
						JOptionPane.showMessageDialog(this, "Failed...");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
