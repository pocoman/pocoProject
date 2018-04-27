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

	public List<String> TxtLoad(String TxtName) throws IOException {
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
		if (br != null)
			br.close();

		return result;
	}
	
	public boolean TxtSave(String TxtName, String str) throws IOException {
		// String ������ �����͸� Txt���Ϸ� �����Ѵ�.
		
		BufferedWriter out = new BufferedWriter(new FileWriter(TxtName));
		out.write(str); // ���Ͽ� ���
		out.close();
		
		return true;
	}
	
	public String TxtEDLoad(String TxtName) throws IOException {
		// txt ������ ������ �о List<String>�������� ��ȯ�ϴ� �Լ�
		
		String buffer = "";
		String result = "";
		EnDe e = new EnDe();

		BufferedReader br = null;
		InputStreamReader isr = new InputStreamReader(new FileInputStream(TxtName));
		br = new BufferedReader(isr);

		while ((buffer = br.readLine()) != null) {
			result = e.MyDecoding(buffer); // ������ ������ listtext �� ����Ʈ �迭�� ����ϴ�.
		}

		br.close(); // ����� ��ġ�� �ݾ��ݴϴ�.
		if (br != null)
			br.close();

		return result;
	}
	
	public boolean TxtEDSave(String TxtName, String str) throws IOException {
		// String ������ �����͸� Txt���Ϸ� �����Ѵ�.
		
		BufferedWriter out = new BufferedWriter(new FileWriter(TxtName));
		EnDe e = new EnDe();
		
		out.write(e.MyEncoding(str)); // ���Ͽ� ���
		out.close();
		
		return true;
	}
}
