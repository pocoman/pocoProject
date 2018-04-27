package ImageProcessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageProcessing02_2 {

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
		int[] iChangeColor = new int[iWidth * iHeight];
		original.getRGB(0, 0, iWidth, iHeight, iColor, 0, iWidth);
		BufferedImage img = new BufferedImage(iWidth, iHeight, BufferedImage.TYPE_4BYTE_ABGR);

		boolean isVerticalChange = true; // 上下反転
		boolean isHorizonChange = true; // 左右反転
		for(int i = 0; i < iHeight; i++) {
			for(int j = 0; j < iWidth; j++) {
				int iChangeRow = (isVerticalChange) ? iHeight - ( i + 1 ) : i; // 上下反転なら一番下から上に
				int iChangeCol = (isHorizonChange) ? iWidth - ( j + 1 ) : j;  // 左右反転なら一番右から左に
				int iChangeIndex = (iChangeRow * iWidth) + iChangeCol;
				
				int iTargetIndex = (i * iWidth) + j;
				
				iChangeColor[iChangeIndex] = iColor[iTargetIndex];
			}
		}
		
		
		img.setRGB(0, 0, iWidth, iHeight, iChangeColor, 0, iWidth);
		
		try {
			ImageIO.write(img, "png", new File("ImageReverse.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
