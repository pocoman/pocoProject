import MyF.ArrayF;

public class Main3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] dA = {3, 2, 1, 4};
		double[] dB = {3, 0, 8, -1};
		double[] dC = {1, 2, 4, 3};

		double[] EvA = ArrayF.ArrEigenValue(dA, 2);
		double[] EvB = ArrayF.ArrEigenValue(dB, 2);
		double[] EvC = ArrayF.ArrEigenValue(dC, 2);

		System.out.println("Eigenvalue A");
		ArrayF.ArrPrint(EvA, 2);
		System.out.println();
		System.out.println("Eigenvalue B");
		ArrayF.ArrPrint(EvB, 2);
		System.out.println();
		System.out.println("Eigenvalue C");
		ArrayF.ArrPrint(EvC, 2);
		System.out.println();

		double[] EvAv = ArrayF.ArrEigenVector(dA, EvA, 2);
		double[] EvBv = ArrayF.ArrEigenVector(dB, EvB, 2);
		double[] EvCv = ArrayF.ArrEigenVector(dC, EvC, 2);

		System.out.println("Eigenvector A");
		ArrayF.ArrPrint(EvAv, 2);
		System.out.println();
		System.out.println("Eigenvector B");
		ArrayF.ArrPrint(EvBv, 2);
		System.out.println();
		System.out.println("Eigenvector C");
		ArrayF.ArrPrint(EvCv, 2);
		System.out.println();
	}
	


}
