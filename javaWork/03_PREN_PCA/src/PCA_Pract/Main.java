package PCA_Pract;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MyF.ArrayF;

public class Main extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4653107220557527131L;
	JTextField[] txfArr = new JTextField[4];
	JTextField[] txfValue = new JTextField[2];
	JTextField[] txfVector = new JTextField[4];
	JButton btCalc = new JButton("Calculator");
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);
		f.add(new Main());
		f.pack();
	}
	
	public Main() {
		Font font = new Font("Arial", Font.PLAIN, 18);
		JPanel pnlArr = new JPanel();
		JPanel pnlBtn = new JPanel();
		JPanel pnlA = new JPanel();
		JPanel pnlVal = new JPanel();
		JPanel pnlVec = new JPanel();
		pnlA.setLayout(new GridLayout(2, 2));
		pnlVal.setLayout(new GridLayout(1, 2));
		pnlVec.setLayout(new GridLayout(2, 2));

		for(int i = 0; i < txfArr.length; i++) {
			txfArr[i] = new JTextField();
			txfArr[i].setPreferredSize(new Dimension(150, 50));
			txfArr[i].setFont(font);
			pnlA.add(txfArr[i]);
		}
		for(int i = 0; i < txfValue.length; i++) {
			txfValue[i] = new JTextField();
			txfValue[i].setPreferredSize(new Dimension(150, 50));
			txfValue[i].setFont(font);
			txfValue[i].setEnabled(false);
			txfValue[i].setBackground(Color.BLACK);
			txfValue[i].setForeground(Color.YELLOW);
			pnlVal.add(txfValue[i]);
		}
		for(int i = 0; i < txfVector.length; i++) {
			txfVector[i] = new JTextField();
			txfVector[i].setPreferredSize(new Dimension(150, 50));
			txfVector[i].setFont(font);
			txfVector[i].setEnabled(false);
			txfVector[i].setBackground(Color.BLACK);
			txfVector[i].setForeground(Color.YELLOW);
			pnlVec.add(txfVector[i]);
		}
		
		btCalc.addActionListener(this);

		
		pnlArr.setLayout(new FlowLayout());
		pnlArr.add(new JLabel("A = "));
		pnlArr.add(pnlA);
		pnlArr.add(new JLabel("･�1, ･�2 = "));
		pnlArr.add(pnlVal);
		pnlArr.add(new JLabel("x1, x2 = "));
		pnlArr.add(pnlVec);
		
		pnlBtn.setLayout(new FlowLayout());
		pnlBtn.add(btCalc);
		
		pnlArr.setBackground(Color.YELLOW);
		setPreferredSize(new Dimension(370, 330));
		setLayout(new BorderLayout());

		add(pnlArr, BorderLayout.CENTER);
		add(pnlBtn, BorderLayout.SOUTH);
		 
	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btCalc) {
			try {
				double[] A = new double[4];
				for(int i = 0; i < txfArr.length; i++) {
					A[i] = Double.parseDouble(txfArr[i].getText());
				}
				double[] EigVal = ArrayF.ArrEigenValue(A, 2);
				double[] EigVec = ArrayF.ArrEigenVector(A, EigVal, 2);
				
				for(int i = 0; i < txfValue.length; i++) {
					txfValue[i].setText(String.format("%4.1f", EigVal[i]));
				}
				for(int i = 0; i < txfVector.length; i++) {
					txfVector[i].setText(String.format("%4.1f", EigVec[i]));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
