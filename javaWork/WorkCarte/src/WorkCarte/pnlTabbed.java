package WorkCarte;

import javax.swing.JTabbedPane;

public class pnlTabbed extends JTabbedPane implements infWork {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1542565234723914647L;
	pnlWorkAdd AddPanel = new pnlWorkAdd();
	pnlWorkView ViewPanel = new pnlWorkView();
	
	public pnlTabbed() {
		addTab("View", ViewPanel);
		addTab("Add", AddPanel);
	}
	
}
