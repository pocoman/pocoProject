package MyUtil;

import java.io.File;
import java.util.ArrayList;

import javax.swing.filechooser.FileFilter;

public class JfcFileFilter extends FileFilter {
	ArrayList<String> ext = new ArrayList<String>();
	String strDesc = "Filter File";

	public JfcFileFilter(String[] ext) {
		for(int i = 0; i < ext.length; i++) {
			this.ext.add(ext[i]);
		}
	}
	
	public boolean addExt(String ext) {
		boolean result = false;
		this.ext.add(ext);
		result = true;
		return result;
	}
	
	public boolean accept(File f) {
		boolean result = false;
		boolean chk = false;
		if (f.isDirectory()) {
			return true;
		}

		String strExt = getExtension(f);
		if (strExt != null) {
			for(int i = 0; i < ext.size(); i++) {
				if(strExt.equals(ext.get(i))) {
					chk = true;
					break;
				}
			}
			
			if(chk) {
				result = true;
			}
		}

		return result;
	}
	
	public void setStrDesc(String strDesc) {
		this.strDesc = strDesc;
	}

	public String getDescription() {
		return strDesc;
	}

	private String getExtension(File f) {
		String ext = null;
		String filename = f.getName();
		int dotIndex = filename.lastIndexOf('.');

		if ((dotIndex > 0) && (dotIndex < filename.length() - 1)) {
			ext = filename.substring(dotIndex + 1).toLowerCase();
		}

		return ext;
	}
}
