package WorkCarte;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class lblWork extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6410144358074356007L;
	final static int HOR_LEFT = LEFT;
	final static int HOR_RIGHT = RIGHT;
	final static int HOR_CENTER = CENTER;

	final static int VER_TOP = TOP;
	final static int VER_BOTTOM = BOTTOM;
	final static int VER_CENTER = CENTER;
	
	final int TEXT_WIDTH = 20;
	final int TEXT_HEIGHT = 25;

	lblWork(String value) {
		super(value);
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
	}
	
	lblWork(String value, int AlignHorizon, int AlignVertical) {
		super(value);
		setHorizontalAlignment(AlignHorizon);
		setVerticalAlignment(AlignVertical);
		setPreferredSize(calcDimension(value));
	}
	
	Dimension calcDimension(String value) {
		String[] arr = value.split("¥n");
		int row = arr.length;
		int col = 0;
		
		for(int i = 0; i < row; i++) {
			if(col < arr[i].length()) {
				col = arr[i].length();
			}
		}
		
		return new Dimension(col * TEXT_WIDTH, row * TEXT_HEIGHT);
	}
	
	public void setDefaultDesign() {
		// TODO Auto-generated method stub
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}
