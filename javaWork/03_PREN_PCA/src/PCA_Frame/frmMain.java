package PCA_Frame;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import MyF.FileF;
import MyUtil.JfcFileFilter;
import MyUtil.MyFrame;
import MyUtil.MyMenuBar;

public class frmMain extends MyFrame implements ActionListener {

	static GraphicsEnvironment ge = GraphicsEnvironment
			.getLocalGraphicsEnvironment();
	static GraphicsDevice gd = ge.getDefaultScreenDevice();
	static final int ConsoleWidth = gd.getDisplayMode().getWidth();
	static final int ConsoleHeight = gd.getDisplayMode().getHeight();
	
	JMenuItem mitLoad;
	JMenuItem mitCheck;
	JFileChooser jfc = new JFileChooser();
	pnlMain pnlMain;
	
	public frmMain(int width, int height) {
		super(width, height);
		BasicSetting(width, height);
		pack();
		

	}

	private void BasicSetting(int width, int height) {
		pnlMain = new pnlMain(width, height);
		
		add(pnlMain);
		setLocation(ConsoleWidth / 2 - width / 2, ConsoleHeight / 2 - height / 2);
		
		MyMenuBar mb = new MyMenuBar();
		JMenu data = mb.addMenu("Image");
		mitLoad = mb.addMenuitem(data, "Image Load & Save", this);
		mitCheck = mb.addMenuitem(data, "Image Check", this);
		
		setJMenuBar(mb);
		
		try {
			String[] ext = {"jpg", "jpeg", "png", "gif"};
			JfcFileFilter filter = new JfcFileFilter(ext);
			filter.setStrDesc("Image File");
			jfc.setFileFilter(filter);
			jfc.setCurrentDirectory(new File("./"));
		} catch (Exception e) {
			
		}
	}

	public frmMain(int width, int height, String str) {
		super(width, height, str);
		BasicSetting(width, height);
		pack();
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		File file = null;
		
		if(ae.getSource() == mitLoad) {
			BufferedImage img = null;
			switch(jfc.showOpenDialog(this)){
			case JFileChooser.APPROVE_OPTION:
				file = new File(jfc.getSelectedFile().toString());
				try {
					img = ImageIO.read(file);
					String[] str = FileF.dirList("./face/", false);
					ImageIO.write(img, "png", new File("./face/face" + String.format("%02d", str.length + 1) + ".png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case JFileChooser.CANCEL_OPTION:
			}
			pnlMain.ImageListInit();
		} else if(ae.getSource() == mitCheck) {
			BufferedImage img = null;
			switch(jfc.showOpenDialog(this)){
			case JFileChooser.APPROVE_OPTION:
				file = new File(jfc.getSelectedFile().toString());
				try {
					img = ImageIO.read(file);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case JFileChooser.CANCEL_OPTION:
			}
			trdPCA trd = new trdPCA();
			BufferedImage[] arr = new BufferedImage[pnlMain.v.size()];
			for(int i = 0; i < arr.length; i++) {
				arr[i] = pnlMain.v.get(i);
			}
			trd.setTempImage(img);
			trd.setImage(arr);
			trd.start();
		}
	}
	
	
}
