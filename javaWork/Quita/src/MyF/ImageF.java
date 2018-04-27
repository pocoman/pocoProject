package MyF;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ImageF {
	
	public static int[] Normalization(int[] data) {
		int[] result = new int[data.length];
		double dAvg = 0;
		
		for(int i = 0; i < data.length; i++) {
			dAvg += data[i];
		}
		dAvg /= data.length;
		
		for(int i = 0; i < data.length; i++) {
			result[i] += data[i] - dAvg;
		}
		
		return result;
	}
	
	public static BufferedImage getImageByPath(String path) {
		BufferedImage result = null;
		
		try {
			result = ImageIO.read(new File(path));
		} catch(Exception e) {
			System.out.println("Image Error");
		}
		
		return result;
	}
	
	public static BufferedImage getSmallImage(BufferedImage img, double dRateX, double dRateY) {
		BufferedImage result = null;
		
		if(dRateX>=1 || dRateY>=1) { 
	        JOptionPane.showMessageDialog(null, "단순좌표계산(인전화소계산) : 축소전용입니다. 배율을 1보다 작게 지정하세요.");
			return null;
		}
		
		// 원본 이미지 크기
		int orWidth  = img.getWidth();
		int orHeight = img.getHeight();

        // 결과 이미지 크기 : 결과 크기는 반올림으로 하면 안됨
		// (예 : 원본크기 9를 99%로 축소할 경우 ==> 9 * 0.99 = 8.91 
		//  ==> 반올림 하면 9가 되므로, 버리는 것이 좋음 : 8이 됨)
		
		int toWidth  = (int)(orWidth * dRateX);
		int toHeight = (int)(orHeight * dRateY);

		result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_4BYTE_ABGR);
		
		int [] sourImageBodyRGB = new int [img.getWidth()*img.getHeight()];
		int [] targImageBodyRGB = new int [result.getWidth()*result.getHeight()];

		img.getRGB(
				   0, 0,
				   orWidth, orHeight,
				   sourImageBodyRGB,
				   0,  // offset   : rgbArray에 저장된 내용을 인덱스 0부터 사용하려면 0을 지정
				   orWidth); // scansize : rgbArray에 저장된 내용의 가로크기(1차원이므로 가로크기를 지정해야 2차원형태로 식별하는 기준을 줄 수 있기 때문..)

		for(int row=0; row<toHeight; row++) {
			for(int col=0; col<toWidth; col++) {
				// 역수로 곱해주면 원본 좌표를 얻을 수 있음
				int sourRow = (int)Math.round(row / dRateY);  
				int sourCol = (int)Math.round(col / dRateX);
				targImageBodyRGB[col+row*toWidth] = sourImageBodyRGB[sourCol + sourRow*orWidth];
			}
		}
		
		result.setRGB(
				   0, 0,
				   toWidth, toHeight,
				   targImageBodyRGB,
				   0,  // offset   : rgbArray에 저장된 내용을 인덱스 0부터 사용하려면 0을 지정
				   toWidth); // scansize : rgbArray에 저장된 내용의 가로크기(1차원이므로 가로크기를 지정해야 2차원형태로 식별하는 기준을 줄 수 있기 때문..)
		
		return result;
	}

	public static int [] getImageMono(BufferedImage img) {		
		int [] body = new int[img.getWidth() * img.getHeight()];
		int temp = 0;

		img.getRGB(
		   0, 0,
		   img.getWidth(), img.getHeight(),
		   body,
		   0,  // offset   : Start Index
		   img.getWidth()); // scansize : Width

		for(int ndx=0; ndx<img.getWidth()*img.getHeight(); ndx++) {
			temp = (int)(0.3333*(body[ndx] & 0x000000ff) + 
					0.3333*((body[ndx] & 0x0000ff00)>>8) + 
					0.3333*((body[ndx] & 0x00ff0000)>>16));
			body[ndx] = 0;
			body[ndx] = body[ndx] | 0xFF000000;
			body[ndx] = body[ndx] | (temp << 16);
			body[ndx] = body[ndx] | (temp << 8);
			body[ndx] = body[ndx] | (temp);
			
		}
		return body;
	}
	
	public static int [] getImageAverage(int[] body) {
		int[] result = body;
		for(int ndx=0; ndx<body.length; ndx++) {
			result[ndx] = (int)(0.3333*(body[ndx] & 0x000000ff) + 
					0.3333*((body[ndx] & 0x0000ff00)>>8) + 
					0.3333*((body[ndx] & 0x00ff0000)>>16));
			
		}
		
		return result;
	}
	public static int [] getImageAverage(BufferedImage img) {		
		int [] body = new int[img.getWidth() * img.getHeight()];

		img.getRGB(
		   0, 0,
		   img.getWidth(), img.getHeight(),
		   body,
		   0,  // offset   : Start Index
		   img.getWidth()); // scansize : Width

		for(int ndx=0; ndx<img.getWidth()*img.getHeight(); ndx++) {
			body[ndx] = (int)(0.3333*(body[ndx] & 0x000000ff) + 
					0.3333*((body[ndx] & 0x0000ff00)>>8) + 
					0.3333*((body[ndx] & 0x00ff0000)>>16));
			
		}
		return body;
	}
	public static BufferedImage SliceImage(BufferedImage img, int x, int y, int width, int height) {
		BufferedImage result = null;

		int MaxX = x + width;
		int MaxY = y + height;
		int iWidth = width;
		int iHeight = height;
		int[] iData = null;
		int iCount = 0;

		if(MaxX > img.getWidth()) {
			iWidth = iWidth - (MaxX - img.getWidth());
			MaxX = img.getWidth();
		}
		if(MaxY > img.getHeight()) {
			iHeight = iHeight - (MaxY - img.getHeight());
			MaxY = img.getHeight();
		}
		iData = new int[iWidth * iHeight];
		
		for(int i = y;i<MaxY;i++) {
			for(int j = x;j<MaxX;j++) {
				iData[iCount] = img.getRGB(j, i);
				iCount++;
			}
		}
		
		result = getImageByInt(iData, width, "png");
		
		return result;
	}	

	public static int[] getColorByString(String[][] strColor) {
		int[] result = new int[strColor.length * strColor[0].length];
		for(int i = 0; i<strColor.length; i++) {
			for(int j = 0; j<strColor[0].length; j++) {
				result[(i * strColor[0].length) + j] = Integer.valueOf(strColor[i][j]);
			}
		}
				
		return result;
	}
	
	public static BufferedImage getImageByInt(int[] image, int ImageWidth, String Ext) {
		
		BufferedImage result = null;
		int ImageHeight = image.length / ImageWidth;
		
		if(Ext.matches("png")) {
			result = new BufferedImage(ImageWidth, ImageHeight, BufferedImage.TYPE_4BYTE_ABGR);
		} else if(Ext.matches("jpg")) {
			result = new BufferedImage(ImageWidth, ImageHeight, BufferedImage.TYPE_3BYTE_BGR);
		} else if(Ext.matches("jpeg")) {
			result = new BufferedImage(ImageWidth, ImageHeight, BufferedImage.TYPE_INT_RGB);
		} else {
			result = new BufferedImage(ImageWidth, ImageHeight, BufferedImage.TYPE_3BYTE_BGR);
		}
		
		result.setRGB(0, 0, ImageWidth, ImageHeight, image, 0, ImageWidth);
		
		return result;
	}
	
	public static int[] getImageData(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		int[] result = new int[width * height];
		
		for(int i = 0; i<height; i++) {
			for(int j = 0; j<width; j++) {
				result[(i * width) + j] = image.getRGB(j, i);
			}
		}
		
		return result;
	}
	
	public static String[] getImageDataByString(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		String[] result = new String[height];
		
		for(int i = 0; i<height; i++) {
			result[i] = "";
			for(int j = 0; j<width; j++) {
				result[i] = result[i] + image.getRGB(j, i);
				if(j < width - 1) {
					result[i] = result[i] + "_";
				}
			}
		}
		
		return result;
	}
}
