package MyUtil;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTfLimit extends PlainDocument {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6338271085253902539L;
	int limit;
	public JTfLimit(int lim) {
		super();
		limit = lim;
	}
	
	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		// TODO Auto-generated method stub
		if(str == null) {
			return;
		}
		if(getLength() + str.length() <= limit){
			super.insertString(offs, str, a);	
		}
	}
}
