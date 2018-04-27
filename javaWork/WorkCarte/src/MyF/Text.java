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
		
		String buffer = "";
		List<String> result = new ArrayList<String>();

		BufferedReader br = null;
		InputStreamReader isr = new InputStreamReader(new FileInputStream(TxtName));
		br = new BufferedReader(isr);

		while ((buffer = br.readLine()) != null) {
			result.add(buffer);
		}

		br.close(); 
		if (br != null)	br.close();

		return result;
	}
	
	public static boolean TxtSave(String TxtName, String str) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(TxtName));
		out.write(str); 
		out.close();
		
		return true;
	}
	
	public static List<String> TxtEDLoad(String TxtName) throws IOException {
		String buffer = "";
		List<String> result = new ArrayList<String>();

		BufferedReader br = null;
		InputStreamReader isr = new InputStreamReader(new FileInputStream(TxtName));
		br = new BufferedReader(isr);

		while ((buffer = br.readLine()) != null) {
			result.add(EnDe.MyDecoding(buffer));
		}

		br.close();
		if (br != null) {
			br.close();
		}
		
		return result;
	} 
	
	public static boolean TxtEDSave(String TxtName, String[] str) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(TxtName));
		
		for(int i = 0; i<str.length; i++) {
			out.write(EnDe.MyEncoding(str[i]));
			if(i < str.length-1)  out.write("\n");
		}
		out.close();
		
		return true;
	}
	
	public static List<String> TxtEDLoadByBase64(String TxtName) throws IOException {
		String buffer = null;
		List<String> result = null;

		BufferedReader br = null;
		InputStreamReader isr = new InputStreamReader(new FileInputStream(TxtName));
		br = new BufferedReader(isr);

		while ((buffer = br.readLine()) != null) {
			System.out.println(buffer);
			try {
				result.add(EnDe.Base64Decode(new StringBuffer(buffer)).toString());
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
	
	public static boolean TxtEDSaveByBase64(String TxtName, String[] str) throws IOException {
		
		BufferedWriter out = new BufferedWriter(new FileWriter(TxtName));
		
		try {
			for(int i = 0; i<str.length; i++) {
				out.write(EnDe.Base64Encode(new StringBuffer(str[i])).toString());
				if(i < str.length-1)  out.write("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.close();
		
		return true;
	}
}
