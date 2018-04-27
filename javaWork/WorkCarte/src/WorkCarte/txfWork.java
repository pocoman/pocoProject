package WorkCarte;

import javax.swing.JTextField;

public class txfWork extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6919832386791341045L;

	boolean isBlank() {
		String str = super.getText();
		
		if(str.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String getText() {
		if(isBlank()) {
			return "Blank";
		} else {
			return super.getText();
		}
		
	}
}
