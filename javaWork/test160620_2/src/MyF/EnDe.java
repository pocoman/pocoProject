package MyF;

public class EnDe {

	public String MyEncoding(String str) {
		StringBuffer sbTemp = null;
		String sTemp = "";
		String result = "";
		int i = 0;

		sbTemp = new StringBuffer(str);

		sTemp = "";
		for (i = 0; i < str.length(); i++) {
			if (Integer.valueOf(sbTemp.charAt(i)) < 100) {
				sTemp = sTemp + "000" + Integer.valueOf(sbTemp.charAt(i));
			} else if (Integer.valueOf(sbTemp.charAt(i)) < 1000) {
				sTemp = sTemp + "00" + Integer.valueOf(sbTemp.charAt(i));
			} else if (Integer.valueOf(sbTemp.charAt(i)) < 10000) {
				sTemp = sTemp + "0" + Integer.valueOf(sbTemp.charAt(i));
			} else {
				sTemp = sTemp + Integer.valueOf(sbTemp.charAt(i));
			}
		}
		sbTemp.delete(0, sbTemp.length());
		sbTemp.insert(0, sTemp);

		sTemp = "";
		for (i = 0; i < sbTemp.length(); i++) {
			if (i % 2 == 0) {
				sTemp = sTemp + sbTemp.substring((i / 2), (i / 2) + 1);
			} else {
				sTemp = sTemp
						+ sbTemp.substring((sbTemp.length() - (i / 2) - 1),
								sbTemp.length() - (i / 2));
			}
		}
		sbTemp.delete(0, sbTemp.length());
		sbTemp.insert(0, sTemp);

		result = sbTemp.toString();
		
		return result;
	}

	public String MyDecoding(String str) {
		StringBuffer sbTemp = null;
		String sTemp = "";
		String sTemp2 = "";
		String result = "";
		int iTemp = 0;
		int i = 0;

		sbTemp = new StringBuffer(str);

		sTemp = "";
		sTemp2 = "";
		for (i = 0; i < sbTemp.length(); i++) {
			if (i % 2 == 0) {
				sTemp = sTemp + sbTemp.substring(i, i + 1);
			} else {
				sTemp2 = sbTemp.substring(i, i + 1) + sTemp2;
			}
		}
		sTemp = sTemp + sTemp2;
		sbTemp.delete(0, sbTemp.length());
		sbTemp.insert(0, sTemp);

/*		sTemp = "";
		for (i = (sbTemp.length() / 5); i > 0; i--) {
			iTemp = Integer.valueOf(sbTemp.substring((i * 5) - 5, (i * 5)));
			sTemp = sTemp + Character.toString((char) iTemp);
		}
		sbTemp.delete(0, sbTemp.length());
		sbTemp.insert(0, sTemp);*/
		sTemp = "";
		for (i = 0; i < (sbTemp.length() / 5); i++) {
			iTemp = Integer.valueOf(sbTemp.substring((i * 5), (i * 5) + 5));
			sTemp = sTemp + Character.toString((char) iTemp);
		}
		sbTemp.delete(0, sbTemp.length());
		sbTemp.insert(0, sTemp);

		result = sbTemp.toString();

		return result;

	}
	
	public String MyEncodingOld (String str) {
		StringBuffer sbTemp = null;
		String sTemp = "";
		String result = "";
		int i = 0;

		sbTemp = new StringBuffer(str);

		sTemp = "";
		for (i = 0; i < str.length(); i++) {
			if (Integer.valueOf(sbTemp.charAt(i)) < 100) {
				sTemp = sTemp + "000" + Integer.valueOf(sbTemp.charAt(i));
			} else if (Integer.valueOf(sbTemp.charAt(i)) < 1000) {
				sTemp = sTemp + "00" + Integer.valueOf(sbTemp.charAt(i));
			} else if (Integer.valueOf(sbTemp.charAt(i)) < 10000) {
				sTemp = sTemp + "0" + Integer.valueOf(sbTemp.charAt(i));
			} else {
				sTemp = sTemp + Integer.valueOf(sbTemp.charAt(i));
			}
		}
		sbTemp.delete(0, sbTemp.length());
		sbTemp.insert(0, sTemp);

		sTemp = "";
		for (i = 0; i < sbTemp.length(); i++) {
			if (i % 2 == 0) {
				sTemp = sTemp + sbTemp.substring((i / 2), (i / 2) + 1);
			} else {
				sTemp = sTemp
						+ sbTemp.substring((sbTemp.length() - (i / 2) - 1),
								sbTemp.length() - (i / 2));
			}
		}
		sbTemp.delete(0, sbTemp.length());
		sbTemp.insert(0, sTemp);

		sTemp = "";
		for (i = sbTemp.length() - 1; i >= 0; i--) {
			if (Integer.valueOf(sbTemp.charAt(i)) < 100) {
				sTemp = sTemp + "000" + Integer.valueOf(sbTemp.charAt(i));
			} else if (Integer.valueOf(sbTemp.charAt(i)) < 1000) {
				sTemp = sTemp + "00" + Integer.valueOf(sbTemp.charAt(i));
			} else if (Integer.valueOf(sbTemp.charAt(i)) < 10000) {
				sTemp = sTemp + "0" + Integer.valueOf(sbTemp.charAt(i));
			} else {
				sTemp = sTemp + Integer.valueOf(sbTemp.charAt(i));
			}
		}

		sbTemp.delete(0, sbTemp.length());
		sbTemp.insert(0, sTemp);

		sTemp = "";
		for (i = 0; i < sbTemp.length(); i++) {
			if (i % 2 == 0) {
				sTemp = sTemp + sbTemp.substring((i / 2), (i / 2) + 1);
			} else {
				sTemp = sTemp
						+ sbTemp.substring((sbTemp.length() - (i / 2) - 1),
								sbTemp.length() - (i / 2));
			}
		}
		sbTemp.delete(0, sbTemp.length());
		sbTemp.insert(0, sTemp);

		result = sbTemp.toString();

		return result;
	}
	
	public String MyDecodingOld (String str) {
		StringBuffer sbTemp = null;
		String sTemp = "";
		String sTemp2 = "";
		String result = "";
		int iTemp = 0;
		int i = 0;

		sbTemp = new StringBuffer(str);

		sTemp = "";
		sTemp2 = "";
		for (i = 0; i < sbTemp.length(); i++) {
			if (i % 2 == 0) {
				sTemp = sTemp + sbTemp.substring(i, i + 1);
			} else {
				sTemp2 = sbTemp.substring(i, i + 1) + sTemp2;
			}
		}
		sTemp = sTemp + sTemp2;
		sbTemp.delete(0, sbTemp.length());
		sbTemp.insert(0, sTemp);

		sTemp = "";
		for (i = (sbTemp.length() / 5); i > 0; i--) {
			iTemp = Integer.valueOf(sbTemp.substring((i * 5) - 5, (i * 5)));
			sTemp = sTemp + Character.toString((char) iTemp);
		}
		sbTemp.delete(0, sbTemp.length());
		sbTemp.insert(0, sTemp);

		sTemp = "";
		sTemp2 = "";
		for (i = 0; i < sbTemp.length(); i++) {
			if (i % 2 == 0) {
				sTemp = sTemp + sbTemp.substring(i, i + 1);
			} else {
				sTemp2 = sbTemp.substring(i, i + 1) + sTemp2;
			}
		}
		sTemp = sTemp + sTemp2;
		sbTemp.delete(0, sbTemp.length());
		sbTemp.insert(0, sTemp);

		sTemp = "";
		for (i = 0; i < (sbTemp.length() / 5); i++) {
			iTemp = Integer.valueOf(sbTemp.substring((i * 5), (i * 5) + 5));
			sTemp = sTemp + Character.toString((char) iTemp);
		}
		sbTemp.delete(0, sbTemp.length());
		sbTemp.insert(0, sTemp);

		result = sbTemp.toString();

		return result;

		
	}
}
