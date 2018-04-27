package MyCap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import MyF.ImageF;
import MyF.SFTPUtil;

public class trConv extends Thread implements intCap {
	
	static int iCount = 0;
	BufferedImage imgCheck = null;
	BufferedImage imgTmplt = null;
	BufferedImage imgUp = null;
	
	double max = -1;
	int MouseX = -1;
	int MouseY = -1;
	int iConvMode = MODE_MOVE;

	int[] iHitRect = null;	
	boolean bStart = false;
	public void setImage(BufferedImage img) {
		imgCheck = img;
	}
	public void setTemplate(BufferedImage tgt) {
		imgTmplt = tgt;
	}
	void setUpImage(BufferedImage img) {
		imgUp = img;
	}
	public void setMouseXY(int x, int y) {
		MouseX = x;
		MouseY = y;
	}

	public void setMode(int mode) {
		iConvMode = mode;
	}
	public void run() {
		bStart = true;
		if(imgCheck != null && imgTmplt != null) {
			int imgWidth, imgHeight;
			int tmpWidth;
			int[] imgData = null;
			int[] tmpData = null;
			double CheckMax = 0;
			if(iConvMode == MODE_FACE) {
				imgWidth = imgCheck.getWidth();
				imgHeight = imgCheck.getHeight();
				int iTempX = (imgWidth / 2) - FIND_SIZE;
	        	int iTempY = (imgHeight / 2) - FIND_SIZE;
	        	//System.out.println(imgTmplt.getWidth() + "," + imgTmplt.getHeight());
	        	//BufferedImage temp = imgCheck;
	        	BufferedImage temp = ImageF.SliceImage(imgCheck, iTempX, iTempY, FIND_SIZE * 2, FIND_SIZE * 2);
	        	
	        	BufferedImage imgScaleO = ImageF.getSmallImage(temp, IMG_SMALL_RATE, IMG_SMALL_RATE);
	        	BufferedImage imgScaleT = ImageF.getSmallImage(imgTmplt, IMG_SMALL_RATE, IMG_SMALL_RATE);
	        	imgData = ImageF.getImageAverage(imgScaleO);
				tmpData = ImageF.getImageAverage(imgScaleT);
				imgWidth = imgScaleO.getWidth();
				tmpWidth = imgScaleT.getWidth();
	        	max = ConvNGCGetMax(imgData, tmpData, imgWidth, tmpWidth, 2);
	        	System.out.println(max);
	        	CheckMax = CHECK_MAX_F;
				if(max >= CheckMax) {
					//FileUpload();
					iCount++;
					System.out.println("Input : " + iCount);
				}
	        } else {
				imgWidth = imgCheck.getWidth();
				imgHeight = imgCheck.getHeight();
				tmpWidth = imgTmplt.getWidth();
				imgData = ImageF.getImageAverage(imgCheck);
				tmpData = ImageF.getImageAverage(imgTmplt);
	        	max = ConvNGCGetMax(imgData, tmpData, imgWidth, tmpWidth);
	        	System.out.println(max);
	        	CheckMax = CHECK_MAX;
				if(max <= CheckMax) {
					//FileUpload();
					iCount++;
					System.out.println("Input : " + iCount);
				}
	        }
			
			//System.out.println("max : " + max);
		
		}
		bStart = false;
	}

	void FileUpload() {
		Connection con = CapPanel.con;
		SFTPUtil sftp = CapPanel.sftp;
		Statement st = null;
		
		String SQL = "";
		String strDT = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String strMS = String.valueOf(System.currentTimeMillis());
		String fname = strDT + strMS.substring(strMS.length() - 4, strMS.length()) + ".png";
		File img = new File("img/" + fname);
		try {
			ImageIO.write(imgUp, "png", img);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sftp.upload("/var/www/html/img_upload", img);
		img.delete();
		
		SQL = "";
		SQL += " INSERT INTO sc_check ( sc_file ) VALUES (";
		SQL += "   '" + fname +"'   ";
		SQL += " ); ";
		
		try {
			st = con.createStatement();
			st.execute(SQL);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	double ConvNGCGetMax(int [] iImageBody, int [] iTemplate, int iImageWidth, int iTmpltWidth) {
		// Return Index
		
		double result = -1;
		double trg = 0;
		double temp = 0;
		int iRow, iCol;
		int tRow, tCol;
		int iCheckIdx, iCheckRow, iCheckCol;
		int iHitIdx = 0;
		int iTmpltHeight = iTemplate.length / iTmpltWidth;
		int iImageHeight = iImageBody.length / iImageWidth;
        int Width = iImageWidth - (iTmpltWidth - 1);
        int Height = iImageHeight - (iTmpltHeight - 1);
    	
		for(int iIdx = 0; iIdx < iImageBody.length && bStart; iIdx++) {
			long SumImage      = 0;
			long SumSqureImage = 0;
			long SumImageNTem  = 0; 
	        long SumTemplate   = 0;
			long SumSquareTemplate = 0;

			iRow = iIdx / iImageWidth;
			iCol = iIdx % iImageWidth;
			
			if(iCol > Width - 1) continue;
			if(iRow > Height - 1) break;
			
			for(int tIdx = 0; tIdx < iTemplate.length && bStart; tIdx++) {
				
				tRow = tIdx / iTmpltWidth;
				tCol = tIdx % iTmpltWidth;
				iCheckRow = iRow + tRow;
				iCheckCol = iCol + tCol;
				iCheckIdx = (iCheckRow * iImageWidth) + iCheckCol;

				SumTemplate       += iTemplate[tIdx];
				SumSquareTemplate += Math.pow(iTemplate[tIdx], 2);
				SumImage          +=  iImageBody[iCheckIdx];
				SumSqureImage     += Math.pow(iImageBody[iCheckIdx], 2);
				SumImageNTem      += (iImageBody[iCheckIdx] * iTemplate[tIdx]);
			}
			int MN = iTemplate.length;
			double u1 = MN * SumSqureImage - SumImage*SumImage; // �и�1
			double u2 = MN * SumSquareTemplate - SumTemplate*SumTemplate; // �и�2
			double w1 = MN * SumImageNTem - SumImage*SumTemplate; // ����1

			temp = (w1*w1) / (u1*u2);
			if(temp > trg) {
				trg = temp;
				result = trg;
				iHitIdx = iIdx;
			}
			Thread.yield();
		}
		
		CapPanel.HitIdx = iHitIdx;
		return result;
	}
	
	double ConvNGCGetMax(int [] iImageBody, int [] iTemplate, int iImageWidth, int iTmpltWidth, int Rate) {
		// Return Index
		
		double result = -1;
		double trg = 0;
		double temp = 0;
		int iRow, iCol;
		int tRow, tCol;
		int iCheckIdx, iCheckRow, iCheckCol;
		int iHitIdx = 0;
		int iTmpltHeight = iTemplate.length / iTmpltWidth;
		int iImageHeight = iImageBody.length / iImageWidth;
        int Width = iImageWidth - (iTmpltWidth - 1);
        int Height = iImageHeight - (iTmpltHeight - 1);

    	for(int iIdx = 0; iIdx < iImageBody.length && bStart; iIdx++) {
    		long SumImage      = 0;
			long SumSqureImage = 0;
			long SumImageNTem  = 0; 
	        long SumTemplate   = 0;
			long SumSquareTemplate = 0;

			iRow = iIdx / iImageWidth;
			iCol = iIdx % iImageWidth;
			
			if(iCol > Width - 1) continue;
			if(iRow > Height - 1) break;
			 
			for(int tIdx = 0; tIdx < iTemplate.length && bStart; tIdx++) {
				
				tRow = tIdx / iTmpltWidth;
				tCol = tIdx % iTmpltWidth;
				iCheckRow = iRow + tRow;
				iCheckCol = iCol + tCol;
				iCheckIdx = (iCheckRow * iImageWidth) + iCheckCol;

				SumTemplate       += iTemplate[tIdx];
				SumSquareTemplate += Math.pow(iTemplate[tIdx], 2);
				SumImage          +=  iImageBody[iCheckIdx];
				SumSqureImage     += Math.pow(iImageBody[iCheckIdx], 2);
				SumImageNTem      += (iImageBody[iCheckIdx] * iTemplate[tIdx]);
			}
			int MN = iTemplate.length;
			double u1 = MN * SumSqureImage - SumImage*SumImage; // �и�1
			double u2 = MN * SumSquareTemplate - SumTemplate*SumTemplate; // �и�2
			double w1 = MN * SumImageNTem - SumImage*SumTemplate; // ����1

			temp = (w1*w1) / (u1*u2);
			if(temp > trg) {
				trg = temp;
				result = trg;
				iHitIdx = iIdx;
			}
			Thread.yield();
		}
		if(result == -1) {
			iHitIdx = (iImageHeight / 2 * iImageWidth) + (iImageWidth / 2);	
		}
		CapPanel.HitIdx = iHitIdx;
		return result;
	}

}
