package ImageProcessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageProcessing02_1 {

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
		int[] iColor = new int[iWidth * iHeight];
		original.getRGB(0, 0, iWidth, iHeight, iColor, 0, iWidth);
		BufferedImage img = null;
		
		/**Image Rotate(Rotate 90)**/
		int iRotate = 90;
		int iRotateS = iRotate / 90;
		int iChangeWidth = 0, iChangeHeight = 0;
		switch(iRotateS % 2) {
		case 0:
			iChangeWidth = iWidth;
			iChangeHeight = iHeight;
			break;
		case 1:
			iChangeWidth = iHeight;
			iChangeHeight = iWidth;
			break;
		}
		img = new BufferedImage(iChangeWidth, iChangeHeight, BufferedImage.TYPE_4BYTE_ABGR);
		int[] iChangeColor = new int[iChangeWidth * iChangeHeight];
		int iTargetRow = 0, iTargetCol = 0;
		for(int i = 0; i < iChangeHeight; i++) {
			for(int j = 0; j < iChangeWidth; j++) {
				switch(iRotateS % 4) {
				case 0:
					iTargetRow = i;
					iTargetCol = j;
					break;
				case 1:
					iTargetRow = iChangeWidth - (j + 1);
					iTargetCol = i;
					break;
				case 2:
					iTargetRow = iChangeHeight - (i + 1);
					iTargetCol = iChangeWidth - (j + 1);
					break;
				case 3:
					iTargetRow = j;
					iTargetCol = iChangeHeight - (i + 1);
					break;
				}
				
				int iTargetIndex = (iTargetRow * iWidth) + iTargetCol;
				int iChangeIndex = (i * iChangeWidth) + j;
				iChangeColor[iChangeIndex] = iColor[iTargetIndex]; 
			}
		}
		
		img.setRGB(0, 0, iChangeWidth, iChangeHeight, iChangeColor, 0, iChangeWidth);
		
		try {
			ImageIO.write(img, "png", new File("ImageRotate.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
