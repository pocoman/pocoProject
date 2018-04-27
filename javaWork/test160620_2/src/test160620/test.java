package test160620;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class test  {
	private static void windowGraphicMode(){
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = ge.getDefaultScreenDevice();
		System.out.println("w:"+ device.getDisplayMode().getWidth()+",h:"+device.getDisplayMode().getHeight());
	    

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		windowGraphicMode();
	}
}
