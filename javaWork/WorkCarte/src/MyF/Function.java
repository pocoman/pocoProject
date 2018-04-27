package MyF;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class Function {
	
	public static int MODE_OK = 1;
	public static int MODE_OKCANCEL = 2;
	public static int MODE_YESNO = 3;
	
	public static int STR_SHADOW = 1;
	public static int STR_LINE = 2;

	public static boolean msg_result;
	
	public static java.sql.Date ConvertUtilSQL(java.util.Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		java.sql.Date d2 = new java.sql.Date(cal.getTimeInMillis());
		
		return d2;
	}
	
	public static void OpenURL(String url) {
		Desktop desktop = Desktop.getDesktop();
		try{
			URI uri = new URI(url);
			desktop.browse(uri);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static JScrollPane setScroll(Component obj) {
		JScrollPane sp = new JScrollPane();
	    sp.getViewport().setView(obj);
	    return sp;
	}
	
	public static ArrayList<Integer> getFactor(int num) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for(int i = 1; i <= num; i++) {
			if(num % i == 0) {
				result.add(i);
			}
		}
		
		return result;
	}
	
	public static int RandomInt(int max, int start) {
		return (int)(Math.random() * max + start);
	}
	
	public static boolean BesideCheck(int value, int i, int iMax) {
		boolean result = false;

		if (value >= i && value <= iMax) {
			result = true;
		}

		return result;
	}
	
	public void WaitTime(int time) {
		long nowtime = System.currentTimeMillis();
		
		while(System.currentTimeMillis() - nowtime < time) {
			
		}
	}
	

	public static String BinaryChange (int Number, String rs) {
		String result = "";
		int temp = 0;
		if(!rs.trim().equals("")) {
			result = rs;
		}
		
		System.out.println(Number);
		if(Number / 2 > 1) {
			temp = Number % 2;
			Number = Number / 2;
			result = String.valueOf(temp) + result;
			result = BinaryChange(Number, result);
		} else {
			if(Number > 1) {
				temp = Number % 2;
				Number = Number / 2;
				result = String.valueOf(Number) + String.valueOf(temp) + result;
			} else {
				result = String.valueOf(Number) + result;
			}			
		}		
		return result;
	}
	
	public static void MyDrawString(Graphics g, String msg, int x, int y, int length, Color color, int mode) {
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
	
	static Dimension SizeCalc (String str) {
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
	
	public static boolean MsgBox(JFrame fa, String str, int mode) {
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
			bt1.setText("Ȯ��");
			bt2.setText("���");
		}
		if(mode == MODE_YESNO){
			bt1.setText("��");
			bt2.setText("�ƴϿ�");
			
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
	
	public static String[][] DoubleSplit(String str, String sp1, String sp2) {
		// ���ڿ��� �� ���� �����ڷ� ������ �������迭 ����
		
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
	
	public static int PreNum(int a, int b) {
		// ���ǰ� A�� B�� ���ؼ� ū ���� ��ȯ�ϴ� �Լ�
		
		int result;
		
		if(a >= b) {
			result = a;
		} else {
			result = b;
		}
		
		
		return result;		
	}
	
	public static int RoundNum(int a, int check, boolean updw) {
		//check�� = �ڸ���, �ݿø��Ѵ�. updw ���� true = �ø�, false = ����
		
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

	public static double RoundNum(double a, int check) {
		//check�� = �ڸ���, �ݿø��Ѵ�.
		
		double result;
		
		result = a;
		if(check < 0) {
			check = -check;
			for(int i=1;i<=check;i++){
				result = result * 10;
			}	
			
			for(int i=1;i<=check;i++){
				result = Math.round(result);	
			}
			
			for(int i=1;i<=check;i++){
				result = result / 10;
			}
		} else {
			for(int i=1;i<=check;i++){
				result = result / 10;
			}	
			
			for(int i=1;i<=check;i++){
				result = Math.round(result);	
			}
			
			for(int i=1;i<=check;i++){
				result = result * 10;
			}	
		}
		
		return result;
	}
	

	public static int RoundNum(int a, int check) {
		//check�� = �ڸ���, �ݿø��Ѵ�.
		
		double result;
		
		result = a;
		if(check < 0) {
			for(int i=1;i<=check;i++){
				result = result * 10;
			}	
			
			for(int i=1;i<=check;i++){
				result = Math.round(result);	
			}
			
			for(int i=1;i<=check;i++){
				result = result / 10;
			}
		} else {
			for(int i=1;i<=check;i++){
				result = result / 10;
			}	
			
			for(int i=1;i<=check;i++){
				result = Math.round(result);	
			}
			
			for(int i=1;i<=check;i++){
				result = result * 10;
			}	
		}
		
		return (int) result;
	}
	
	static int PrimeNumCheck(double prime1, double prime2) {
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

	public static double PrimeAdd(double prime1, double prime2) {
		int primeadd = 1;
		double result;

		primeadd = PrimeNumCheck(prime1, prime2);
		result = ((prime1 * primeadd) + (prime2 * primeadd)) / primeadd;
		return result;
	}

	public static double PrimeMinus(double prime1, double prime2) {
		int primeadd;
		double result;

		primeadd = PrimeNumCheck(prime1, prime2);
		result = ((prime1 * primeadd) - (prime2 * primeadd)) / primeadd;

		return result;
	}

	public static double PrimeMulti(double prime1, double prime2) {
		int primeadd;
		double result;

		primeadd = PrimeNumCheck(prime1, prime2);
		result = ((prime1 * primeadd) * (prime2 * primeadd)) / primeadd / primeadd;

		return result;
	}

	public static double PrimeDivide(double prime1, double prime2) {
		int primeadd;
		double result;

		primeadd = PrimeNumCheck(prime1, prime2);
		result = ((prime1 * primeadd) / (prime2 * primeadd)) / primeadd / primeadd;

		return result;
	}
	
	public static boolean ProgramOpen(String File, String FilePath) {
		boolean result = false;
		
		String exeFile = File;
		System.out.println("exeFile: " + File);
		ProcessBuilder process = new ProcessBuilder();
        Map<String, String> environment = process.environment();
        process.redirectErrorStream(true);
        process.directory(new File(FilePath));
        environment.put("name", "var");
        process.command(exeFile);
      
        try {
        	// ������ ��ü�� ����(start()) �����ָ� �ȴ�.
            Process p = process.start();
            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = output.readLine()) != null)
              System.out.println(line);
            // The process should be done now, but wait to be sure.
            try {
                p.waitFor();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            result = true;
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
		
		return result;
	}
	

	public static int getTriDeg(int CenterX, int CenterY, int MoveX, int MoveY) {
		int result = -1;
		double dSizeX = MoveX - CenterX;
		double dSizeY = MoveY - CenterY;
		boolean isSizeXMinus = false;
		boolean isSizeYMinus = false;
		double angleInRadians, degreeA = 0;
		if(dSizeX < 0) isSizeXMinus = true;
		if(dSizeY < 0) isSizeYMinus = true;

		if(dSizeX != 0.0 && dSizeY != 0.0) {
			angleInRadians = Math.atan(dSizeY / dSizeX);
			degreeA = Math.toDegrees(angleInRadians);
			degreeA = degreeA + 90;
			//degreeB = 90 - degreeA;
			
		} else {
			//100.0,0.0/false,false
			//0.0,100.0/false,false
			//-100.0,0.0/true,false
			//0.0,-100.0/false,true
			if(isSizeYMinus) {
				if(!isSizeXMinus) result = 270;
			} else {
				if(isSizeXMinus)  result = 180;
				else {
					if(dSizeX > 0) result = 0;
					else if(dSizeY > 0) result = 90;
				}
			}
		}
		
		// false false, true false, true true, false true
		if(result == -1) {
			if(isSizeYMinus) {
				if(isSizeXMinus)  result = (int) (degreeA + 90);
				else result = (int) (degreeA + 270);
			} else {
				if(isSizeXMinus)  result = (int) (degreeA + 90);
				else result = (int) (degreeA - 90); 
			}
		}
		
		return result;
	}

	public static double getTriDownSize(int a, int b) {
		double result = -1;
		result = Math.sqrt((a * a) + (b * b)); 
		return result;
	}
	

	public static double getTriDownSize(double a, double b) {
		double result = -1;
		result = Math.sqrt((a * a) + (b * b)); 
		return result;
	}
}
