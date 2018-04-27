package WorkCarte;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class pnlMain extends JPanel implements infWork {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9083627464474148664L;
	static pnlTabbed WorkPanel = new pnlTabbed();
	pnlList ListPanel = new pnlList();
	pnlProjectHeader HeaderPanel = new pnlProjectHeader();
	public pnlMain() {
		JPanel pn1 = new JPanel();
		setPreferredSize(FRAME_SIZE[FRM_MAIN]);
		setLayout(new BorderLayout());
		
		pn1.setLayout(new BorderLayout());
		pn1.add(WorkPanel, BorderLayout.CENTER);
		pn1.add(ListPanel, BorderLayout.WEST);
		
		add(HeaderPanel, BorderLayout.NORTH);
		add(pn1, BorderLayout.CENTER);
		
	}
}
