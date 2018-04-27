import java.util.ArrayList;

import MyF.ArrayF;
import MyF.CCYPCA;

public class Main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("0. Data Set");
		ArrayList<double[]> arr = new ArrayList<double[]>();
		double[] iA = { 6, 10, 5, 7 };
		double[] iB = { 11, 13, 17, 12 };
		double[] iC = { 4, 5, 6, 9 };

		arr.add(iA);
		arr.add(iB);
		arr.add(iC);

		System.out.println("1. Get Trainning Set");
		double[] Trset = ArrayF.ArrTranning(arr);
		for (int i = 0; i < iA.length; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(Trset[(i * 3) + j] + "\t");
			}
			System.out.println();
		}
		System.out.println();

		System.out.println("2. Get Average Vector");
		double[] AvgVec = ArrayF.ArrAvgVector(Trset, 3);
		for (int i = 0; i < AvgVec.length; i++) {
			System.out.println(AvgVec[i]);
		}
		System.out.println();

		System.out.println("3 - 1. Get Minus Matrix");
		for (int i = 0; i < arr.size(); i++) {
			arr.set(i, ArrayF.ArrPluMin(arr.get(i), AvgVec, false));
		}
		Trset = ArrayF.ArrTranning(arr);

		double[] Pt = ArrayF.ArrCopy(Trset);
		ArrayF.ArrPrint(Pt, 3);
		System.out.println();

		System.out.println("3 - 2. Switch Minus Matrix");
		double[] P = ArrayF.ArrSwitch(Pt, arr.size(), arr.get(0).length);
		ArrayF.ArrPrint(P, 4);
		System.out.println();

		System.out.println("4. Get Covariance Matrix");
		double[] CovMat = ArrayF.ArrMul(P, Pt, 4, 3);
		ArrayF.ArrPrint(CovMat, 3);

		System.out.println("5 - 1. Get Eigenvector, Eigenvalue");
		double[][] EigVec = new double[3][3];
		double[] EigVal = new double[3];

		double[][] CovMat2 = ArrayF.ArrSingleToDouble(CovMat, 3);
		CCYPCA.HH_Eigen(EigVec, EigVal, CovMat2);
		System.out.println("**Eigenvalue**");
		ArrayF.ArrPrint(EigVal, 3);
		System.out.println("**Eigenvector**");
		ArrayF.ArrPrint(EigVec);

		double[] EigVecFs = ArrayF.ArrDoubleToSingle(EigVec);
		double[] EigVecF = ArrayF.ArrMul(Pt, EigVecFs, 3, 3);
		System.out.println("**EigenVector Final**");
		ArrayF.ArrPrint(EigVecF, 3);
		double[][] EigVecFd = ArrayF.ArrSingleToDouble(EigVecF, 3);

		System.out.println();
		System.out.println("5. Get Luminance Vector");
		int iPeople = 3;
		int iPixel = 4;
		double[][] 특징벡터 = new double[3][2];
		for (int 사람 = 0; 사람 < iPeople; 사람++) {

			// 편의를 위해 무조건 오른쪽 사람수-1개의 고유벡터를 사용하도록하자...
			// (고유치가 크기순으로 정렬되어있다고 가정)
			int cnt = 0;
			for (int v = EigVecFd[0].length - 1; cnt < iPeople - 1; v--, cnt++) {
				double sum = 0;
				for (int k = 0; k < iPixel; k++) {
					sum += Pt[k * iPeople + 사람] * EigVecFd[k][v];
				}
				특징벡터[사람][cnt] = sum;
			}
		}

		for (int 사람 = 0; 사람 < 3; 사람++) {
			System.out.printf("(계산량간소화) %d번째 사람의 특징벡터 = ", 사람 + 1);
			for (int k = 0; k < 3 - 1; k++) {
				System.out.printf("%10.3f  ", 특징벡터[사람][k]);
			}
			System.out.println("");
		}

		/*
		 * System.out.println("4. Get Eigenvector, Eigenvalue"); //double[]
		 * EigenA = {3.9, 2.3, 0.2, 0.1}; //double[] EigenB = {2.1, 1.8, 1.3,
		 * 0.9}; double[] Eigen = {3.9, 2.1 , 2.3, 1.8 , 0.2, 1.3 , 0.1, 0.9};
		 * 
		 * System.out.println();
		 * 
		 * System.out.println("5. Get Luminance Vector"); double[] MinusA =
		 * ArrayF.ArrPluMin(iA, AvgVec, false); double[] MinusB =
		 * ArrayF.ArrPluMin(iB, AvgVec, false);
		 * 
		 * double[] LuminA = ArrayF.ArrMul(MinusA, Eigen, 4, 2); double[] LuminB
		 * = ArrayF.ArrMul(MinusB, Eigen, 4, 2); for(int i = 0; i <
		 * LuminA.length; i++) { System.out.print(LuminA[i] + "\t"); }
		 * System.out.println(); for(int i = 0; i < LuminB.length; i++) {
		 * System.out.print(LuminB[i] + "\t"); } System.out.println();
		 * System.out.println();
		 * 
		 * System.out.println("6. New Pictrue Luminance"); double[] iC = {5, 9,
		 * 4, 6};
		 * 
		 * double[] TrsetA = ArrayF.ArrTranning(iA, iC); double[] TrsetB =
		 * ArrayF.ArrTranning(iB, iC);
		 * 
		 * double[] AvgVecA = ArrayF.ArrAvgVactor(TrsetA); double[] AvgVecB =
		 * ArrayF.ArrAvgVactor(TrsetB);
		 * 
		 * double[] MinusC_A = ArrayF.ArrPluMin(iC, AvgVecA, false); double[]
		 * MinusC_B = ArrayF.ArrPluMin(iC, AvgVecB, false);
		 * 
		 * double[] LuminC = {-21.15, -24.1};
		 * 
		 * double DestiA_C = Function.getTriDownSize(LuminA[0] - LuminC[0],
		 * LuminA[1] - LuminC[1]); double DestiB_C =
		 * Function.getTriDownSize(LuminB[0] - LuminC[0], LuminB[1] -
		 * LuminC[1]); DestiA_C = Function.RoundNum(DestiA_C, -3); DestiB_C =
		 * Function.RoundNum(DestiB_C, -3);
		 * System.out.println("Destination A & C : " + DestiA_C);
		 * System.out.println("Destination B & C : " + DestiB_C); if(DestiA_C <
		 * DestiB_C) { System.out.println("A ≒ C"); } else {
		 * System.out.println("B ≒ C"); }
		 */
	}

}
