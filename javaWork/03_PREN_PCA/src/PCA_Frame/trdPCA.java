package PCA_Frame;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import MyF.ArrayF;
import MyF.CCYPCA;
import MyF.ImageF;

public class trdPCA extends Thread {

	ArrayList<BufferedImage> arrImg = new ArrayList<BufferedImage>();
	BufferedImage template = null;
	
	public void run() {
		BufferedImage temp = arrImg.get(0);
		int iPeople = arrImg.size();
		int iPixel = temp.getWidth() * temp.getHeight();
		ArrayList<double[]> arr = new ArrayList<double[]>();
		double[] rs = new double[iPeople]; 
				
		System.out.println("0. Data Init");
		for(int i = 0; i < iPeople; i++) {
			double[] dFaceData = new double[iPixel];
			int[] iData = new int[iPixel];
			temp = arrImg.get(i);
			temp.getRGB(0, 0, temp.getWidth(), temp.getHeight(), iData, 0, temp.getWidth());
			
			iData = ImageF.Normalization(ImageF.getImageAverage(iData));
			
			for(int j = 0; j < iPixel; j++) {
				dFaceData[j] = iData[j];
			}
			arr.add(dFaceData);
			System.gc();
		}
		
		System.out.println("1. Get Trainning Set");
		double[] Trset = ArrayF.ArrTranning(arr);
		//ArrayF.ArrPrint(Trset, iPeople);
		
		System.out.println("2. Get Average Vector");
		double[] AvgVec = ArrayF.ArrAvgVector(Trset, iPeople);
		
		System.out.println("3. Get Minus Matrix");
		for (int i = 0; i < arr.size(); i++) {
			arr.set(i, ArrayF.ArrPluMin(arr.get(i), AvgVec, false));
		}
		Trset = ArrayF.ArrTranning(arr);
		double[] Pt = ArrayF.ArrCopy(Trset);
		
		System.out.println("4. Switch Minus Matrix");
		double[] P = ArrayF.ArrSwitch(Pt, arr.size(), arr.get(0).length);

		System.out.println("5. Get Covariance Matrix");
		double[] CovMat = ArrayF.ArrMul(P, Pt, iPixel, iPeople);
		//ArrayF.ArrPrint(CovMat, iPeople);

		System.out.println("6. Get Eigenvector, Eigenvalue");
		double[][] EigVec = new double[iPeople][iPeople];
		double[] EigVal = new double[iPeople];

		double[][] CovMat2 = ArrayF.ArrSingleToDouble(CovMat, iPeople);
		CCYPCA.HH_Eigen(EigVec, EigVal, CovMat2);
		double[] EigVecFs = ArrayF.ArrDoubleToSingle(EigVec);
		double[] EigVecF = ArrayF.ArrMul(Pt, EigVecFs, iPeople, iPeople);
		double[][] EigVecFd = ArrayF.ArrSingleToDouble(EigVecF, iPeople);

		System.out.println("7. Get Luminance Vector");
		double[][] LuminVec = new double[iPeople][iPeople - 1];
		for (int i = 0; i < iPeople; i++) {

			int cnt = 0;
			for (int j = EigVecFd[0].length - 1; cnt < iPeople - 1; j--, cnt++) {
				double sum = 0;
				for (int k = 0; k < iPixel; k++) {
					sum += Pt[k * iPeople + i] * EigVecFd[k][j];
				}
				LuminVec[i][cnt] = sum;
			}
		}
		
		for (int i = 0; i < iPeople; i++) {
			System.out.printf("(計算量短縮) %d番目人の特徴ベクトル = ", i + 1);
			for (int k = 0; k < iPeople - 1; k++) {
				System.out.printf("%10.3f  ", LuminVec[i][k]);
			}
			System.out.println("");
		}
		System.out.println();
		
		temp = template;
		if(temp != null) {
			double[] dFaceData = new double[iPixel];
			double[] dFaceDataP = new double[iPixel];
			double[] dFaceLumin = new double[iPeople - 1];
			int[] iData = new int[iPixel];
			System.out.println(iPixel);
			temp.getRGB(0, 0, temp.getWidth(), temp.getHeight(), iData, 0, temp.getWidth());
			
			iData = ImageF.Normalization(ImageF.getImageAverage(iData));
			
			for(int j = 0; j < iPixel; j++) {
				dFaceData[j] = iData[j];
			}

			dFaceDataP = ArrayF.ArrPluMin(dFaceData, AvgVec, false);
			for (int i = 0; i < iPeople; i++) {
				int cnt = 0;
				for (int j = EigVecFd[0].length - 1; cnt < iPeople - 1; j--, cnt++) {
					double sum = 0;
					for (int k = 0; k < iPixel; k++) {
						sum += dFaceDataP[k] * EigVecFd[k][j];
					}
					dFaceLumin[cnt] = sum;
				}
			}
			
			for(int i=0; i < iPeople; i++) {
				System.out.printf("%d番目人の特徴ベクトル : ", i+1);
				for(int k=0; k<iPeople-1; k++) {
					System.out.printf("%10.3f  ", LuminVec[i][k]);
				}
				rs[i] = ArrayF.Euclid_Distance(LuminVec[i], dFaceLumin);
				System.out.printf("==>新しい顔とのユークリッド距離 = %10.3f\n", rs[i] );
			}
			System.out.println();
			
			int idx = 0;
			double dChk1 = 0;
			for(int i = 0; i < rs.length; i++) {
				
				double dChk2 = rs[i];
				if(dChk1 == 0) {
					dChk1 = dChk2;
				} else if(dChk1 > dChk2) {
					dChk1 = dChk2;
					idx = i;
					System.out.println();
				}
			}
			System.out.println((idx + 1) + "番目の人が対象と一番似ています。");
			
		}		
	}
	
	public void setImage(BufferedImage[] arr) {
		arrImg.clear();
		for(int i = 0; i < arr.length; i++) {
			//BufferedImage temp = ImageF.getSmallImage(arr[i], 0.5, 0.5);
			BufferedImage temp = arr[i];
			arrImg.add(temp);
		}
	}
	
	public void addImage(BufferedImage img) {
		BufferedImage temp = ImageF.getSmallImage(img, 0.5, 0.5);
		
		arrImg.add(temp);
	}
	
	public void setTempImage(BufferedImage img) {
		//template = ImageF.getSmallImage(img, 0.5, 0.5);
		template = img;
	}
}
