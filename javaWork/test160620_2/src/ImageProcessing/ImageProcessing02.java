package ImageProcessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageProcessing02 {

	public static void main(String[] args) {
		BufferedImage original = null;
		try {
			original = ImageIO.read(new File("image.png")); // 編集する画像を持ってくる
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int iWidth = original.getWidth();				// 画像の横幅
		int iHeight = original.getHeight();				// 画像の縦幅
		int[] iColor = new int[iWidth * iHeight];		// 横幅と縦幅を元に色情報を保存する配列作成
		int[] iChangeColor = new int[iWidth * iHeight]; // 同じサイズで修正したデータを保存するための配列
		
		// 修正したBufferedImage変数を予め作成する。
		BufferedImage img = new BufferedImage(iWidth, iHeight, BufferedImage.TYPE_4BYTE_ABGR);
		
		// 画像の色情報をgetRGBで持ってくる
		original.getRGB(0, 0, iWidth, iHeight, iColor, 0, iWidth);
		
		/**********************
		 * ここにコードを書きます。*
		 **********************/

		// 修正した色情報を修正用のBufferedImageにセット
		img.setRGB(0, 0, iWidth, iHeight, iChangeColor, 0, iWidth);
		
		try {
			ImageIO.write(img, "png", new File("ImageRotate.png")); // 画像を保存
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
