package ImageProcessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageProcessing02_3 {

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
		
		// 切り取り始める座標
		int iChangeY = 50;
		int iChangeX = 50;
		// 切り取るサイズ
		int iChangeWidth = 100;
		int iChangeHeight = 100;
		BufferedImage img = new BufferedImage(iChangeWidth, iChangeHeight, BufferedImage.TYPE_4BYTE_ABGR);
		for(int i = 0; i < iChangeHeight; i++) {
			for(int j = 0; j < iChangeWidth; j++) {
				// 切り取り始める座標から色情報の座標を割り出す。
				int iTargetRow = iChangeY + i;
				int iTargetCol = iChangeX + j;
				int iTargetIndex = (iTargetRow * iWidth) + iTargetCol;
				
				int iChangeIndex = (i * iChangeWidth) + j;
				
				iChangeColor[iChangeIndex] = iColor[iTargetIndex];
			}
		}
		
		img.setRGB(0, 0, iChangeWidth, iChangeHeight, iChangeColor, 0, iChangeWidth);
		
		try {
			ImageIO.write(img, "png", new File("ImageSlice.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
