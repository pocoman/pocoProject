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
		// txt 파일의 내용을 읽어서 List<String>형식으로 반환하는 함수
		
		String buffer = "";
		List<String> result = new ArrayList<String>();

		BufferedReader br = null;
		InputStreamReader isr = new InputStreamReader(new FileInputStream(TxtName));
		br = new BufferedReader(isr);

		while ((buffer = br.readLine()) != null) {
			result.add(buffer); // 위에서 정의한 listtext 의 리스트 배열에 담습니다.
		}

		br.close(); // 사용을 맞치면 닫아줍니다.
		if (br != null)	br.close();

		return result;
	}
	
	public static boolean TxtSave(String TxtName, String str) throws IOException {
		// String 형식의 데이터를 Txt파일로 저장한다.
		
		BufferedWriter out = new BufferedWriter(new FileWriter(TxtName));
		out.write(str); // 파일에 기록
		out.close();
		
		return true;
	}
	
	public static String TxtEDLoad(String TxtName) throws IOException {
		// txt 파일의 내용을 읽어서 List<String>형식으로 반환하는 함수
		
		String buffer = "";
		String result = "";

		BufferedReader br = null;
		InputStreamReader isr = new InputStreamReader(new FileInputStream(TxtName));
		br = new BufferedReader(isr);

		while ((buffer = br.readLine()) != null) {
			result = EnDe.MyDecoding(buffer); // 위에서 정의한 listtext 의 리스트 배열에 담습니다.
		}

		br.close(); // 사용을 맞치면 닫아줍니다.
		if (br != null) {
			br.close();
		}
		
		return result;
	}
	
	public static boolean TxtEDSave(String TxtName, String str) throws IOException {
		// String 형식의 데이터를 Txt파일로 저장한다.
		
		BufferedWriter out = new BufferedWriter(new FileWriter(TxtName));
		
		out.write(EnDe.MyEncoding(str)); // 파일에 기록
		out.close();
		
		return true;
	}
	
	public static StringBuffer TxtEDLoadByBase64(String TxtName) throws IOException {
		// txt 파일의 내용을 읽어서 List<String>형식으로 반환하는 함수
		
		String buffer = null;
		StringBuffer result = null;

		BufferedReader br = null;
		InputStreamReader isr = new InputStreamReader(new FileInputStream(TxtName));
		br = new BufferedReader(isr);

		while ((buffer = br.readLine()) != null) {
			try {
				result = EnDe.Base64Decode(new StringBuffer(buffer));  // 위에서 정의한 listtext 의 리스트 배열에 담습니다.
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
		// String 형식의 데이터를 Txt파일로 저장한다.
		
		BufferedWriter out = new BufferedWriter(new FileWriter(TxtName));
		
		try {
			StringBuffer temp = EnDe.Base64Encode(str);
			char[] save = new char[temp.length()];
			temp.getChars(0, temp.length(), save, 0);
			out.write(save);
		} catch (Exception e) {
			e.printStackTrace();
		} // 파일에 기록
		out.close();
		
		return true;
	}
}
