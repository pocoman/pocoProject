import MyF.ArrayF;
import MyF.Function;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("0. Data Set");
		double[] iA = {6, 10, 5, 7};
		double[] iB = {11, 13, 17, 12};
		System.out.println();
		
		System.out.println("1. Get Trainning Set");
		double[] Trset = ArrayF.ArrTranning(iA, iB);
		for(int i = 0; i < iA.length; i++) {
			for(int j = 0; j < 2; j++) {
				System.out.print(Trset[(i * 2) + j] + "\t");
			}
			System.out.println();
		}
		System.out.println();

		System.out.println("2. Get Average Vector");
		double[] AvgVec = ArrayF.ArrAvgVector(Trset, 2);
		for(int i = 0; i < AvgVec.length; i++) {
			System.out.println(AvgVec[i]);
		}
		System.out.println();
		
		System.out.println("3. Get Covariance Matrix");
		double[] ConvMat = ArrayF.ArrCovariance(iA, iB, AvgVec);
		for(int i = 0; i < iA.length; i++) {
			for(int j = 0; j < iA.length; j++) {
				System.out.print(ConvMat[i * iA.length + j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
		
		System.out.println("4. Get Eigenvector, Eigenvalue");
		//double[] EigenA = {3.9, 2.3, 0.2, 0.1};
		//double[] EigenB = {2.1, 1.8, 1.3, 0.9};
		double[] Eigen = {3.9, 2.1 
						, 2.3, 1.8
						, 0.2, 1.3
						, 0.1, 0.9};
		
		System.out.println();
		
		System.out.println("5. Get Luminance Vector");
		double[] MinusA = ArrayF.ArrPluMin(iA, AvgVec, false);
		double[] MinusB = ArrayF.ArrPluMin(iB, AvgVec, false);

		double[] LuminA = ArrayF.ArrMul(MinusA, Eigen, 4, 2);
		double[] LuminB = ArrayF.ArrMul(MinusB, Eigen, 4, 2);
		for(int i = 0; i < LuminA.length; i++) {
			System.out.print(LuminA[i] + "\t");
		}
		System.out.println();
		for(int i = 0; i < LuminB.length; i++) {
			System.out.print(LuminB[i] + "\t");
		}
		System.out.println();
		System.out.println();
		
		System.out.println("6. New Pictrue Luminance");
		double[] iC = {5, 9, 4, 6};

		double[] TrsetA = ArrayF.ArrTranning(iA, iC);
		double[] TrsetB = ArrayF.ArrTranning(iB, iC);
		
		double[] AvgVecA = ArrayF.ArrAvgVector(TrsetA, 2);
		double[] AvgVecB = ArrayF.ArrAvgVector(TrsetB, 2);

		double[] MinusC_A = ArrayF.ArrPluMin(iC, AvgVecA, false);
		double[] MinusC_B = ArrayF.ArrPluMin(iC, AvgVecB, false);

		double[] LuminC = {-21.15, -24.1};

		double DestiA_C = Function.getTriDownSize(LuminA[0] - LuminC[0], LuminA[1] - LuminC[1]);
		double DestiB_C = Function.getTriDownSize(LuminB[0] - LuminC[0], LuminB[1] - LuminC[1]);
		DestiA_C = Function.RoundNum(DestiA_C, -3);
		DestiB_C = Function.RoundNum(DestiB_C, -3);
		System.out.println("Destination A & C : " + DestiA_C);
		System.out.println("Destination B & C : " + DestiB_C);
		if(DestiA_C < DestiB_C) {
			System.out.println("A ¡Ö C");
		} else {
			System.out.println("B ¡Ö C");
		}
		
	}

}
