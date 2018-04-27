package MyF;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class FileF {

	final static String[] ImageExt = { "png", "jpg", "jpeg", "bmp", "gif" };

	public static String fileToString(File file) {
		String fileString = new String();
		FileInputStream inputStream = null;
		ByteArrayOutputStream byteOutStream = null;
		
		try {
			inputStream = new FileInputStream(file);
			byteOutStream = new ByteArrayOutputStream();

			int len = 0;
			byte[] buf = new byte[1024];
			while ((len = inputStream.read(buf)) != -1) {
				byteOutStream.write(buf, 0, len);
			}

			byte[] fileArray = byteOutStream.toByteArray();
			fileString = new String(Base64.getEncoder().encode(fileArray));
			
			inputStream.close();
			byteOutStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileString;
	}

	public static String[] dirList(String Path, boolean isPrint) {
		File dir = new File(Path);
		String cnt[] = null;
		if (dir.isDirectory()) {
			cnt = dir.list();
			if (isPrint) {
				for (int i = 0; i < cnt.length; i++) {
					System.out.println(cnt[i]);
				}
			}
		}

		return cnt;
	}

	public static boolean isDir(String Path) {
		boolean result = false;
		File dir = new File(Path);
		if (dir.isDirectory()) {
			result = true;
		}

		return result;
	}

	public static String getExt(String Name) {
		String result = "";
		int temp = 0;

		while (Name.indexOf(".", temp + 1) > 0) {
			temp = Name.indexOf(".", temp);
		}

		result = Name.substring(temp + 1, Name.length());

		return result;
	}

	public static String getName(String Name) {
		String result = "";
		int temp = 0;

		while (Name.indexOf(".", temp + 1) > 0) {
			temp = Name.indexOf(".", temp);
		}

		result = Name.substring(0, temp);

		return result;
	}

	public static boolean isPicture(String Name) {
		boolean result = false;

		String Ext = getExt(Name);
		for (int i = 0; i < ImageExt.length; i++) {
			if (ImageExt[i].matches(Ext)) {
				result = true;
				break;
			}
		}

		return result;
	}

}
