package MyF;

public class Conversion {

	public final static int IM_X1 = 0;
	public final static int IM_Y1 = 1;
	public final static int IM_X2 = 2;
	public final static int IM_Y2 = 3;
	
	public static boolean bStop = true;
	public static double max = -1;

	public static int[] iImageMove = {-1, -1, -1, -1};
	
	public static int[] ConvImageMove(int[] img1, int[] img2, int iWidth, boolean isWhite) {
		int[] result = new int[img1.length];
		int[] iCheck = {-1, -1, -1, -1};
		int a = 0;
		int b = 0;
		int x = 0;
		int CheckX = -1;
		int CheckY = -1;
		
		if(img1.length != img2.length) {
			System.out.println("Error Size");
		} else {
			for(int i = 0; i < img1.length; i++) {
				a = (img1[i] & 0x000000FF);
				b = (img2[i] & 0x000000FF);
				
				x = a - b;
				if(x < 0) x = -x;
				if(x <= 80) x = 0;
				if(x > 0) {
					CheckX = i % iWidth;
					CheckY = i / iWidth;
					if(iCheck[0] == -1) {
						for(int j = 0; j < iCheck.length; j++) {
							if(j % 2 == 1) {
								iCheck[j] = CheckY;
							} else {
								iCheck[j] = CheckX;
							}
						}
					} else {
						if(iCheck[IM_X1] > CheckX) iCheck[IM_X1] = CheckX;
						if(iCheck[IM_Y1] > CheckY) iCheck[IM_Y1] = CheckY;
						if(iCheck[IM_X2] < CheckX) iCheck[IM_X2] = CheckX;
						if(iCheck[IM_Y2] < CheckY) iCheck[IM_Y2] = CheckY;
					}
				}
				x = x / 4 * 16;
				if(x > 255) x = 255;
				if(isWhite) x = 255 - x;
				
				result[i] = (result[i] | 0xFF000000);
				result[i] = (result[i] | x << 16);
				result[i] = (result[i] | x << 8);
				result[i] = (result[i] | x);
			}
		}
		for(int j = 0; j < iImageMove.length; j++) {
			iImageMove[j] = iCheck[j];		
		}
		return result;
	}
	
	public static double [] ConvMSE(int [] image, int [] check, int ImageWidth, int CheckWidth) {

		int ImageHeight = image.length / ImageWidth;
		int CheckHeight = check.length / CheckWidth;
        int Width = ImageWidth - (CheckWidth - 1);
        int Height = ImageHeight - (CheckHeight - 1);
        
        double [] result = new double [Width * Height];
		
        int ImageRow = 0, ImageCol = 0;
        int CheckRow = 0, CheckCol = 0;
        int iTempImage = 0, iTempCheck = 0;
        double dTempResult = 0;
        
		for(int i = 0; i<image.length;i++) {
			ImageRow = i / ImageWidth;
			ImageCol = i % ImageWidth;
			
			if(ImageCol > Width - 1) continue;
			if(ImageRow > Height - 1) break;
			
			dTempResult = 0;
			for(int j = 0; j<check.length;j++) {
				CheckRow = j / CheckWidth;
				CheckCol = j % CheckWidth;
				
				iTempImage = image[((ImageRow + CheckRow) * ImageWidth) + (ImageCol + CheckCol)];
				iTempCheck = check[(CheckRow * CheckWidth) + CheckCol];
				dTempResult = dTempResult + Math.pow(Math.abs(iTempImage - iTempCheck), 2);
			}
			result[(ImageRow * Width) + ImageCol] = dTempResult / check.length;
		}

		return result;
	}
	
	public static int ConvNGC(int [] iImageBody, int [] iTemplate, int iImageWidth, int iTmpltWidth) {
		// Return Index
		
		int result = -1;
		double trg = 0;
		double temp = 0;
		double ab = 0;
		double normA=0, normB=0;
		int iRow, iCol;
		int tRow, tCol;
		int iCheckIdx, iCheckRow, iCheckCol;
		int iTmpltHeight = iTemplate.length / iTmpltWidth;
		int iImageHeight = iImageBody.length / iImageWidth;
        int Width = iImageWidth - (iTmpltWidth - 1);
        int Height = iImageHeight - (iTmpltHeight - 1);
		
		bStop = false;
		for(int iIdx = 0; iIdx<iImageBody.length && !bStop; iIdx++) {
			iRow = iIdx / iImageWidth;
			iCol = iIdx % iImageWidth;
			
			if(iCol > Width - 1) continue;
			if(iRow > Height - 1) break;
			
			ab = 0; normA = 0; normB = 0;
			 
			for(int tIdx = 0; tIdx < iTemplate.length && !bStop; tIdx++) {
				tRow = tIdx / iTmpltWidth;
				tCol = tIdx % iTmpltWidth;
				iCheckRow = iRow + tRow;
				iCheckCol = iCol + tCol;
				iCheckIdx = (iCheckRow * iImageWidth) + iCheckCol;

				ab += iTemplate[tIdx] * iImageBody[iCheckIdx];
				normA += iImageBody[iCheckIdx] * iImageBody[iCheckIdx];				
				normB += iTemplate[tIdx]*iTemplate[tIdx];
			}
			normA = Math.sqrt(normA);
			normB = Math.sqrt(normB);
			temp = ab / (normA*normB);

			if(temp > trg) {
				trg = temp;
				result = iIdx;
			}
			Thread.yield();
		}
		bStop = true;
		max = trg;
		return result;
	}
	
	public static double ConvNGCGetMax(int [] iImageBody, int [] iTemplate, int iImageWidth, int iTmpltWidth) {
		// Return Index
		
		double result = -1;
		double trg = 0;
		double temp = 0;
		double ab = 0;
		double normA=0, normB=0;
		int iRow, iCol;
		int tRow, tCol;
		int iCheckIdx, iCheckRow, iCheckCol;
		int iTmpltHeight = iTemplate.length / iTmpltWidth;
		int iImageHeight = iImageBody.length / iImageWidth;
        int Width = iImageWidth - (iTmpltWidth - 1);
        int Height = iImageHeight - (iTmpltHeight - 1);
		
		bStop = false;
		for(int iIdx = 0; iIdx<iImageBody.length && !bStop; iIdx++) {
			iRow = iIdx / iImageWidth;
			iCol = iIdx % iImageWidth;
			
			if(iCol > Width - 1) continue;
			if(iRow > Height - 1) break;
			
			ab = 0; normA = 0; normB = 0;
			 
			for(int tIdx = 0; tIdx < iTemplate.length && !bStop; tIdx++) {
				tRow = tIdx / iTmpltWidth;
				tCol = tIdx % iTmpltWidth;
				iCheckRow = iRow + tRow;
				iCheckCol = iCol + tCol;
				iCheckIdx = (iCheckRow * iImageWidth) + iCheckCol;

				ab += iTemplate[tIdx] * iImageBody[iCheckIdx];
				normA += iImageBody[iCheckIdx] * iImageBody[iCheckIdx];				
				normB += iTemplate[tIdx]*iTemplate[tIdx];
			}
			normA = Math.sqrt(normA);
			normB = Math.sqrt(normB);
			temp = ab / (normA*normB);

			if(temp > trg) {
				trg = temp;
				result = trg;
			}
			Thread.yield();
		}

		bStop = true;
		return result;
	}

	
}
