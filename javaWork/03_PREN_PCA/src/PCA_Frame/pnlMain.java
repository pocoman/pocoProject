package PCA_Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import MyF.FileF;
import MyF.ImageF;

public class pnlMain extends JPanel implements ListSelectionListener {
	pnlImage pnlImage = new pnlImage();
	JList<BufferedImage> lstImage = new JList<BufferedImage>();
	Vector<BufferedImage> v = new Vector<BufferedImage> (); 
	
	public pnlMain(int width, int height) {
		BasicSetting();
		
		setPreferredSize(new Dimension(width, height));
		setLayout(new BorderLayout());
		
		lstImage.setPreferredSize(new Dimension(200, 600));
		lstImage.addListSelectionListener(this);
		JScrollPane pane = new JScrollPane(pnlImage);
		pane.setPreferredSize(new Dimension(600, 600));
		
		add(pane, BorderLayout.CENTER);
		add(lstImage, BorderLayout.EAST);
	}
	
	void BasicSetting() {
		ImageListInit();
	}
	
	public void ImageListInit() {
		String[] lstFile = FileF.dirList("./face/", false);
		if(lstFile.length > 0) {
			lstImage.removeAll();
			v.clear();
			for(int i = 0; i < lstFile.length; i++) {
				BufferedImage img = ImageF.getImageByPath("./face/" + lstFile[i]);
				v.add(img);
			}
			lstImage.setListData(v);
		}
	}

	public void valueChanged(ListSelectionEvent lse) {
		if(lse.getSource() == lstImage) {
			BufferedImage img = lstImage.getSelectedValue();
			pnlImage.setImage(img);
		}
	}
}
