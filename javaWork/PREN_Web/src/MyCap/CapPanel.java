package MyCap;

import static com.googlecode.javacv.cpp.opencv_highgui.cvCreateCameraCapture;
import static com.googlecode.javacv.cpp.opencv_highgui.cvQueryFrame;
import static com.googlecode.javacv.cpp.opencv_highgui.cvReleaseCapture;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;

import MyF.Conversion;
import MyF.Function;
import MyF.ImageF;
import MyF.SFTPUtil;

public class CapPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener, intCap {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5508287561506053386L;

	final Font fBasic = new Font("Consolas", Font.BOLD, 16);
	
	static CvCapture cCapture = cvCreateCameraCapture(0);
	static IplImage Iplimg;
	
	static Connection con;
	static SFTPUtil sftp = new SFTPUtil();
	
	public static int[] iHitRect = {-1, -1, -1, -1};
	public static int MouseX = -1;
	public static int MouseY = -1;
	public static int HitIdx = -1;
	public static double max = -1;
	
	public static int iConvMode = MODE_MOVE;
		
	static ImageButton ibMove;
	static ImageButton ibFace;
	static BufferedImage imgBtMove, imgBtMove_on1, imgBtMove_on2;
	static BufferedImage imgBtFace, imgBtFace_on1, imgBtFace_on2;
	
	static {
		Iplimg = cvQueryFrame(cCapture);
		//con = DB_MySQL.getMyConnection("192.168.56.101", "pren_web", "102938");
		//DB_MySQL.useDB(con, "pren_web");
		
		//sftp.init("192.168.56.101", "pocoman", "102938", 22);
		
		try {
			imgBtMove = ImageIO.read(new File("img/bt_move.png"));
			imgBtMove_on1 = ImageIO.read(new File("img/bt_move_on1.png"));
			imgBtMove_on2 = ImageIO.read(new File("img/bt_move_on2.png"));

			imgBtFace = ImageIO.read(new File("img/bt_face.png"));
			imgBtFace_on1 = ImageIO.read(new File("img/bt_face_on1.png"));
			imgBtFace_on2 = ImageIO.read(new File("img/bt_face_on2.png"));

			ibMove = new ImageButton(imgBtMove, imgBtMove_on1, imgBtMove_on2);
			ibFace = new ImageButton(imgBtFace, imgBtFace_on1, imgBtFace_on2);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	BufferedImage imgCapture = ImageF.getSmallImage(Iplimg.getBufferedImage(), 0.5f, 0.5f);
	BufferedImage imgCapture2 = new BufferedImage(imgCapture.getWidth(), imgCapture.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
	BufferedImage imgTemplate = null;
	BufferedImage imgMono = null;
	BufferedImage imgFace = null;

	Timer t = new Timer(33, this);
	trConv ConvProc = new trConv();
	public CapPanel() {
		if(Iplimg == null) Iplimg = cvQueryFrame(cCapture); 
		setPreferredSize(new Dimension(imgCapture.getWidth() * 2, imgCapture.getHeight() + 50));
		addMouseListener(this);
		addMouseMotionListener(this);
		
		try {
			imgFace = ImageIO.read(new File("img/imgFace.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ibFace.setLocation(imgCapture.getWidth() - 50 - imgBtMove.getWidth(), 
				imgCapture.getHeight() + 5);
		ibMove.setLocation(imgCapture.getWidth() + 50, imgCapture.getHeight() + 5);
		if(iConvMode == MODE_MOVE) ibMove.isClick = true;
		if(iConvMode == MODE_FACE) ibFace.isClick = true;
		t.start();
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == t) {
			repaint();
			
			if(imgCapture2 != null) {
				if(ConvProc != null && Conversion.bStop) {
					if(!ConvProc.bStart) {
						ConvProc = new trConv();
						ConvProc.setImage(imgMono);
						ConvProc.setUpImage(imgCapture);
						ConvProc.setMode(iConvMode);
						if(iConvMode == MODE_MOVE) {
							ConvProc.setTemplate(imgCapture2);
						} else if(iConvMode == MODE_FACE){
							ConvProc.setTemplate(imgFace);
						}
						ConvProc.setMouseXY(MouseX, MouseY);
						ConvProc.start();
					}
				}
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		getCaptureImage();
		BufferedImage temp = new BufferedImage(imgCapture.getWidth() * 2, 
				imgCapture.getHeight() + 50,
				BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g2 = temp.getGraphics();
		
		g2.drawImage(imgCapture, 0, 0, null);
		//g2.drawImage(imgMono, 0, 0, null);
		
		if(imgCapture2 != null) {
			int [] imgArr1 = ImageF.getImageMono(imgCapture);
			int [] imgArr2 = ImageF.getImageMono(imgCapture2);
			int [] checkMove = Conversion.ConvImageMove(imgArr1, imgArr2, imgCapture.getWidth(), false);
			iHitRect = Conversion.iImageMove;
			
			BufferedImage img2 = new BufferedImage(imgCapture.getWidth(), imgCapture.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
			img2.setRGB(0, 0, imgCapture.getWidth(), imgCapture.getHeight(), checkMove, 0, imgCapture.getWidth());

			g2.drawImage(img2, imgCapture.getWidth(), 0, null);
			if(iConvMode == MODE_MOVE && iHitRect[0] > -1) {
				g2.setColor(Color.YELLOW);
				g2.drawRect(imgCapture.getWidth() + iHitRect[RECT_X1], iHitRect[RECT_Y1], 
						iHitRect[RECT_X2] - iHitRect[RECT_X1], iHitRect[RECT_Y2] - iHitRect[RECT_Y1]);
			} else if(iConvMode == MODE_FACE) {
				//g2.drawImage(imgFace, 0, 0, null);
				//System.out.println(HitIdx);
				if(HitIdx >= 0) {
					int iOriX = ((imgCapture.getWidth() / 2) - FIND_SIZE);
					int iOriY = ((imgCapture.getHeight() / 2) - FIND_SIZE);
					int iHitX = HitIdx % (ORI_WIDTH / IMG_SMALL_ORI);
					int iHitY = HitIdx / (ORI_WIDTH / IMG_SMALL_ORI);
					int iRealX = iOriX + (iHitX * IMG_SMALL_ORI);
					int iRealY = iOriY + (iHitY * IMG_SMALL_ORI);
					int ShowX = iRealX - TEMP_WIDTH / 2;
					int ShowY = iRealY - TEMP_WIDTH / 2;
					g2.setColor(Color.YELLOW);
					g2.drawRect(ShowX, ShowY, TEMP_WIDTH, TEMP_WIDTH);
					//g2.setColor(Color.RED);
					//g2.drawRect(iRealX - , y, width, height);
					
				}
			}
		}
		ibFace.drawButton(g2);
		ibMove.drawButton(g2);
		
		g.drawImage(temp, 0, 0, null);
	}
	
	void getCaptureImage() {
		Iplimg = cvQueryFrame(cCapture);
		int[] arr = new int[imgCapture.getWidth() * imgCapture.getHeight()];
		imgCapture.getRGB(0, 0, imgCapture.getWidth(), imgCapture.getHeight(), arr, 0, imgCapture.getWidth());
		imgCapture2.setRGB(0, 0, imgCapture.getWidth(), imgCapture.getHeight(), arr, 0, imgCapture.getWidth());
		imgCapture = ImageF.getSmallImage(Iplimg.getBufferedImage(), 0.5f, 0.5f);
		BufferedImage temp = new BufferedImage(imgCapture.getWidth(), imgCapture.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		temp.setRGB(0, 0, temp.getWidth(), temp.getHeight(), ImageF.getImageMono(imgCapture), 0, temp.getWidth());
		imgMono = temp;
	}


	public void mouseClicked(MouseEvent e) {
		if(ibMove.checkClick(e.getX(), e.getY())) iConvMode = MODE_MOVE;
		if(ibFace.checkClick(e.getX(), e.getY())) iConvMode = MODE_FACE;

		if(iConvMode == MODE_MOVE) ibMove.isClick = true;
		if(iConvMode == MODE_FACE) ibFace.isClick = true;
	
		if(imgCapture != null) {
			if(Function.BesideCheck(e.getX(), 0, imgCapture.getWidth()) &&
					Function.BesideCheck(e.getY(), 0, imgCapture.getHeight())) {
				int size = 100;
				int NowX = e.getX() - (size / 2);
				int NowY = e.getY() - (size / 2);
				MouseX = e.getX();
				MouseY = e.getY();
				
				imgFace = ImageF.SliceImage(imgCapture, NowX, NowY, size, size);
				System.out.println("Change");
				if(!Conversion.bStop) {
					System.out.println("Running....");
				}
			}
		}
		
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {
		
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		cvReleaseCapture(cCapture);
		cCapture = null;
		con.close();
		sftp.disconnection();
		System.out.println("Finalize");
		System.gc();
		super.finalize();
		System.exit(0);
	}
}
