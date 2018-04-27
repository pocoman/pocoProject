package MyF;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;

public class Function {
	
	public int MODE_OK = 1;
	public int MODE_OKCANCEL = 2;
	public int MODE_YESNO = 3;
	
	public int STR_SHADOW = 1;
	public int STR_LINE = 2;
	
	
	public boolean msg_result;
	public static Clip clip = null;
	public String NowPlay = "";

	public void WaitTime(int time) {
		long nowtime = System.currentTimeMillis();
		
		while(System.currentTimeMillis() - nowtime < time) {
			
		}
	}
	
	public boolean BgmPlay(String filename) {
		boolean result = false;
		NowPlay = filename;
		File f = new File(filename);
		
		AudioInputStream ais = null;
		
		if(f.isFile()) {
			try {
				ais = AudioSystem.getAudioInputStream(f);
				clip = AudioSystem.getClip();
				clip.open(ais);
				clip.loop(-1);
				clip.start();
				result = true;
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			result = false;
		}		
		return result;
	}
	public void BgmStop(boolean isStop) {
		if(clip != null) {
			if(isStop) {
				clip.stop();
			} else {
				clip.start();
			}
		}
	}
	public void BgmClose() {
		if(clip != null) {
			clip.stop();
			clip.close();
		}
	}
	
	public boolean SoundPlay(String filename) {
		boolean result = false;
		File f = new File(filename);
		Clip clip = null;
		AudioInputStream ais = null;
		
		if(f.isFile()) {
			try {
				ais = AudioSystem.getAudioInputStream(f);
				clip = AudioSystem.getClip();
				clip.open(ais);
				clip.start();			
				result = true;
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			result = false;
		}		
		return result;
	}
	
	public void MyDrawString(Graphics g, String msg, int x, int y, int length, Color color, int mode) {
		switch(mode){
		case 1: //STR_SHADOW
			g.setColor(Color.BLACK);
			g.drawString(msg, x + length, y + length);
			g.setColor(color);
			g.drawString(msg, x, y);			
			break;
		case 2: //STR_LINE
			g.setColor(Color.BLACK);
			g.drawString(msg, x + length, y + length);
			g.drawString(msg, x + length, y - length);
			g.drawString(msg, x - length, y + length);
			g.drawString(msg, x - length, y - length);
			g.setColor(color);
			g.drawString(msg, x, y);
			break;
		}
	}
	
	Dimension SizeCalc (String str) {
		Dimension result = null;
		String[] temp = null;
		int check1 = 0;
		int check2 = 0;
		
		temp = str.split("\n");
		for(int i=0;i<temp.length;i++){
			if(check1 < temp[i].length()) {
				check1 = temp[i].length();
			}
			check2++;
		}
		
		result = new Dimension(check1 * 10 + 50, check2 * 20 + 100);
		
		
		return result;
	}
	
	public boolean MsgBox(JFrame fa, String str, int mode) {
		int X = 0;
		int Y = 0;
		Dialog f = new Dialog(fa, true);
		JTextPane lb = new JTextPane();
		JButton bt1 = new JButton();
		JButton bt2 = new JButton();
		
		lb.setText(str);
		lb.setEditable(false);
		
		msg_result = false;
		
		if(mode == MODE_OK || mode == MODE_OKCANCEL) {
			bt1.setText("확인");
			bt2.setText("취소");
		}
		if(mode == MODE_YESNO){
			bt1.setText("예");
			bt2.setText("아니오");
			
		}
		
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				msg_result = true;
				f.setVisible(false);
			}
			
		});
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				msg_result = false;
				f.setVisible(false);
			}
		});
		
		f.setLayout(new FlowLayout());
		f.add(lb, BorderLayout.CENTER);

		if(mode == MODE_YESNO || mode == MODE_OKCANCEL) {
			f.add(bt1, BorderLayout.SOUTH);
			f.add(bt2, BorderLayout.SOUTH);
		}
		if(mode == MODE_OK){
			f.add(bt1, BorderLayout.SOUTH);
		}
		
		f.setSize(SizeCalc(str));
		
		X = fa.getX() + fa.getWidth() / 2 - f.getWidth() / 2;
		Y = fa.getY() + fa.getHeight() / 2 - f.getHeight() / 2;
		
		f.setLocation(X, Y);		
		f.setVisible(true);
		
		return msg_result;
	}
	
	public String[][] DoubleSplit(String str, String sp1, String sp2) {
		// 문자열을 두 개의 구분자로 나눠서 이차원배열 생성
		
		String[][] result = null;
		String[] stemp1;
		String[] stemp2;
		
		stemp1 = str.split(sp1);
		stemp2 = stemp1[0].split(sp2);
		
		result = new String[stemp1.length][stemp2.length];
		for(int i=0;i<stemp1.length;i++) {
			result[i] = stemp1[i].split(sp2);
		}
		
		return result;
	}
	
	public int PreNum(int a, int b) {
		// 임의값 A와 B를 비교해서 큰 값을 반환하는 함수
		
		int result;
		
		if(a >= b) {
			result = a;
		} else {
			result = b;
		}
		
		
		return result;		
	}
	
	public int RoundNum(int a, int check, boolean updw) {
		//check값 = 자릿수, 반올림한다. updw 값에 true = 올림, false = 내림
		
		double result;
		
		result = a;
		
		for(int i=1;i<=check;i++){
			result = result / 10;
		}	
		
		if(updw == true) {			
			result = Math.ceil(result);
		} else {
			result = Math.floor(result);
		}
		
		for(int i=1;i<=check;i++){
			result = result * 10;
		}
		
		return (int)result;
	}
	
	public int RoundNum(int a, int check) {
		//check값 = 자릿수, 반올림한다.
		
		double result;
		
		result = a;
		
		for(int i=1;i<=check;i++){
			result = result / 10;
		}	
		
		for(int i=1;i<=check;i++){
			result = Math.round(result);	
		}
		
		for(int i=1;i<=check;i++){
			result = result * 10;
		}
		
		return (int)result;
	}
	
	int PrimeNumCheck(double prime1, double prime2) {
		String primestr1, primestr2;
		int primenum1, primenum2;
		int primeadd = 1;
		int i;

		primestr1 = String.valueOf(prime1);
		primestr2 = String.valueOf(prime2);

		primenum1 = primestr1.length() - primestr1.indexOf(".") - 1;
		primenum2 = primestr2.length() - primestr2.indexOf(".") - 1;

		if (primenum1 > primenum2) {
			for (i = 1; i <= primenum1; i++) {
				primeadd = primeadd * 10;
			}
		} else {
			for (i = 1; i <= primenum2; i++) {
				primeadd = primeadd * 10;
			}
		}

		return primeadd;

	}

	public double PrimeAdd(double prime1, double prime2) {
		int primeadd = 1;
		double result;

		primeadd = PrimeNumCheck(prime1, prime2);
		result = ((prime1 * primeadd) + (prime2 * primeadd)) / primeadd;
		return result;
	}

	public double PrimeMinus(double prime1, double prime2) {
		int primeadd;
		double result;

		primeadd = PrimeNumCheck(prime1, prime2);
		result = ((prime1 * primeadd) - (prime2 * primeadd)) / primeadd;

		return result;
	}

	public double PrimeMulti(double prime1, double prime2) {
		int primeadd;
		double result;

		primeadd = PrimeNumCheck(prime1, prime2);
		result = ((prime1 * primeadd) * (prime2)) / primeadd;

		return result;
	}

	public double PrimeDivide(double prime1, double prime2) {
		int primeadd;
		double result;

		primeadd = PrimeNumCheck(prime1, prime2);
		result = ((prime1 * primeadd) / (prime2)) / primeadd;

		return result;
	}
}
