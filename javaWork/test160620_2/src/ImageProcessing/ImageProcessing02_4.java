package ImageProcessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageProcessing02_4 {

	public static void main(String[] args) {
		BufferedImage original = null;
		try {
			original = ImageIO.read(new File("image.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int iWidth = original.getWidth();
		int iHeight = original.getHeight();
		int iPieceW = 3;
		int iPieceH = 4;
		int iChangeWidth = iWidth / iPieceW;
		int iChangeHeight = iHeight / iPieceH;
		int[] iColor = new int[iWidth * iHeight];
		int[][] iChangeColor = new int[iPieceW * iPieceH][iChangeWidth * iChangeHeight];
		original.getRGB(0, 0, iWidth, iHeight, iColor, 0, iWidth);
		BufferedImage[] img = new BufferedImage[iPieceW * iPieceH];
		for(int i = 0; i < img.length; i++) {
			img[i] = new BufferedImage(iChangeWidth, iChangeHeight, BufferedImage.TYPE_4BYTE_ABGR);
		}

		for(int i = 0; i < iHeight; i++) {
			int iNowY = i / iChangeHeight;
			if(iNowY >= iPieceH) break;
			for(int j = 0; j < iWidth; j++) {
				int iNowX = j / iChangeWidth;
				if(iNowX >= iPieceW) continue;
				int iChangeRow = i % iChangeHeight;
				int iChangeCol = j % iChangeWidth;
				
				int iTargetIndex = (i * iWidth) + j;
				int iChangeIndex = (iChangeRow * iChangeWidth) + iChangeCol;
				int iChangeKey = iNowY * iPieceW + iNowX;
				
				iChangeColor[iChangeKey][iChangeIndex] = iColor[iTargetIndex];
			}
		}

		for(int i = 0; i < img.length; i++) {
			img[i].setRGB(0, 0, iChangeWidth, iChangeHeight, iChangeColor[i], 0, iChangeWidth);
		}
		
		try {
			for(int i = 0; i < img.length; i++) {
				ImageIO.write(img[i], "png", new File("ImageSlice" + String.format("%02d", i) + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
