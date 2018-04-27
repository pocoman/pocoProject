package MyF;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Text {

	public static List<String> TxtLoad(String TxtName) throws IOException {
		// txt ������ ������ �о List<String>�������� ��ȯ�ϴ� �Լ�
		
		String buffer = "";
		List<String> result = new ArrayList<String>();

		BufferedReader br = null;
		InputStreamReader isr = new InputStreamReader(new FileInputStream(TxtName));
		br = new BufferedReader(isr);

		while ((buffer = br.readLine()) != null) {
			result.add(buffer); // ������ ������ listtext �� ����Ʈ �迭�� ����ϴ�.
		}

		br.close(); // ����� ��ġ�� �ݾ��ݴϴ�.
		if (br != null)	br.close();

		return result;
	}
	
	public static boolean TxtSave(String TxtName, String str) throws IOException {
		// String ������ �����͸� Txt���Ϸ� �����Ѵ�.
		
		BufferedWriter out = new BufferedWriter(new FileWriter(TxtName));
		out.write(str); // ���Ͽ� ���
		out.close();
		
		return true;
	}
	
	public static String TxtEDLoad(String TxtName) throws IOException {
		// txt ������ ������ �о List<String>�������� ��ȯ�ϴ� �Լ�
		
		String buffer = "";
		String result = "";

		BufferedReader br = null;
		InputStreamReader isr = new InputStreamReader(new FileInputStream(TxtName));
		br = new BufferedReader(isr);

		while ((buffer = br.readLine()) != null) {
			result = EnDe.MyDecoding(buffer); // ������ ������ listtext �� ����Ʈ �迭�� ����ϴ�.
		}

		br.close(); // ����� ��ġ�� �ݾ��ݴϴ�.
		if (br != null) {
			br.close();
		}
		
		return result;
	}
	
	public static boolean TxtEDSave(String TxtName, String str) throws IOException {
		// String ������ �����͸� Txt���Ϸ� �����Ѵ�.
		
		BufferedWriter out = new BufferedWriter(new FileWriter(TxtName));
		
		out.write(EnDe.MyEncoding(str)); // ���Ͽ� ���
		out.close();
		
		return true;
	}
	
	public static StringBuffer TxtEDLoadByBase64(String TxtName) throws IOException {
		// txt ������ ������ �о List<String>�������� ��ȯ�ϴ� �Լ�
		
		String buffer = null;
		StringBuffer result = null;

		BufferedReader br = null;
		InputStreamReader isr = new InputStreamReader(new FileInputStream(TxtName));
		br = new BufferedReader(isr);

		while ((buffer = br.readLine()) != null) {
			try {
				result = EnDe.Base64Decode(new StringBuffer(buffer));  // ������ ������ listtext �� ����Ʈ �迭�� ����ϴ�.
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}

		br.close();
		if (br != null) {
			br.close();
		}

		return result;
	}
	
	public static boolean TxtEDSaveByBase64(String TxtName, StringBuffer str) throws IOException {
		// String ������ �����͸� Txt���Ϸ� �����Ѵ�.
		
		BufferedWriter out = new BufferedWriter(new FileWriter(TxtName));
		
		try {
			StringBuffer temp = EnDe.Base64Encode(str);
			char[] save = new char[temp.length()];
			temp.getChars(0, temp.length(), save, 0);
			out.write(save);
		} catch (Exception e) {
			e.printStackTrace();
		} // ���Ͽ� ���
		out.close();
		
		return true;
	}
}
