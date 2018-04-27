package MyF;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayF {
	
	public static double[][] ArrSingleToDouble(double[] arr, int width) {
		int height = arr.length / width;
		double[][] result = new double[height][width];
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				result[i][j] = arr[i * width + j];
			}
		}
		
		return result;
	}
	
	public static double[] ArrDoubleToSingle(double[][] arr) {
		int width = arr[0].length;
		int height = arr.length;
		double[] result = new double[height * width];
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				result[i * width + j] = arr[i][j];
			}
		}
		
		return result;
	}
	
	public static double[] ArrEigenVector(double[] A, double[] EvA, int width) {
		double[] result = new double[A.length];
		double[] temp = new double[EvA.length];
		double[] tempRs = new double[EvA.length];
		int TempEv;
		int iAdd = 0;
		
		if(A[0] == 0 || A[1] == 0) iAdd = 2;
			
		for(int i = 0; i < EvA.length; i++) {
			TempEv = (int) EvA[i];

			if(iAdd == 2) {
				temp[0] = A[iAdd];
				temp[1] = A[iAdd + 1] - TempEv;	
			} else {
				temp[0] = A[iAdd] - TempEv;
				temp[1] = A[iAdd + 1];
			}
			
			tempRs[0] = -(temp[1] / temp[0]);
			tempRs[1] = -(temp[0] * tempRs[0]) / temp[1];
			for(int j = 0; j < tempRs.length; j++) {
				if(Double.isNaN(tempRs[j])) {
					result[(j * width) + i] = -A[iAdd + j];
				} else {
					result[(j * width) + i] = tempRs[j];	
				}
			}
		}
		
		return result;
	}
	
	public static double[] ArrEigenValue(double[] A, int width) {
		//3, 2,
		//1, 4
		double[] result = new double[width];
		ArrayList<Double> arrst = new ArrayList<Double>(); 
		double dC = (-A[0] * -A[3]) - (-A[2] * -A[1]); // 10
		double dB = -A[0] + -A[3]; // -7
		int iTempA, iTempB, iTempRs = Math.abs((int) dB);
		boolean isCheck = false;
		boolean isMinus = false;
		
		if(dC < 0) isMinus = true;
		
		ArrayList<Integer> iCArr = Function.getFactor(Math.abs((int) dC));
		for(int i = 0; i < iCArr.size(); i++) {
			iTempA = iCArr.get(i);
			for(int j = i + 1; j < iCArr.size(); j++) {
				iTempB = iCArr.get(j);
				if(isMinus) {
					if(Math.abs(iTempA - iTempB) == iTempRs) {
						arrst.add((double) iTempA);
						arrst.add((double) iTempB);
						isCheck = true;
						break;
					}
				} else {
					if(iTempA + iTempB == iTempRs) {
						arrst.add((double) iTempA);
						arrst.add((double) iTempB);
						isCheck = true;
						break;
					}
				}
			}
			if(isCheck) break;
		}
		
		Collections.sort(arrst);
		//System.out.println(arrst.size());
		if(isMinus) {
			if(dB > 0) {
				arrst.set(1, -arrst.get(1));
			} else {
				arrst.set(0, -arrst.get(0));
			}
		}

		result[0] = arrst.get(1);
		result[1] = arrst.get(0);
		
		return result;
	}
	
	public static void ArrPrint(double[] copy, int width) {
		int height = copy.length / width;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				System.out.print(String.format("%10.4f", copy[(i * width) + j]) + "\t");
			}
			System.out.println();
		}
	}
	public static void ArrPrint(double[][] copy) {
		for(int i = 0; i < copy.length; i++) {
			for(int j = 0; j < copy[0].length; j++) {
				System.out.print(String.format("%10.4f", copy[i][j]) + "\t");
			}
			System.out.println();
		}
	}
	
	public static double[] ArrCopy(double[] copy) {
		double[] result = new double[copy.length];
		
		for(int i = 0; i < copy.length; i++) {
			result[i] = copy[i];
		}
		
		return result;
	}
	
	public static double[] ArrCovariance(double[] iA, double[] iB, double[] iC, double[] AvgVec) {
		double[] result = new double[iA.length * iA.length];

		double[] iDifA = ArrPluMin(iA, AvgVec, false);
		double[] iDifB = ArrPluMin(iB, AvgVec, false);
		double[] iDifC = ArrPluMin(iC, AvgVec, false);
		
		System.out.println("*. Avg Minus Array");
		for(int i = 0; i < iDifA.length; i++) {
			System.out.print(String.format("%10.4f", iDifA[i]) + "\t");
			System.out.print(String.format("%10.4f", iDifB[i]) + "\t");
			System.out.println(String.format("%10.4f", iDifC[i]));
		}
		System.out.println();
		
		int width = iA.length;
		for(int i = 0; i < iDifA.length; i++) {
			for(int j = 0; j < iDifB.length; j++) {
				double temp = iDifA[i] * iDifB[j];
				
				result[(i * width) + j] = -temp; 
			}
		}
		
		return result;
	}	
	public static double[] ArrCovariance(double[] iA, double[] iB, double[] AvgVec) {
		double[] result = new double[iA.length * iA.length];

		double[] iDifA = ArrPluMin(iA, AvgVec, false);
		double[] iDifB = ArrPluMin(iB, AvgVec, false);
		
		int width = iA.length;
		for(int i = 0; i < iDifA.length; i++) {
			for(int j = 0; j < iDifB.length; j++) {
				double temp = iDifA[i] * iDifB[j];
				
				result[(i * width) + j] = -temp; 
			}
		}
		
		return result;
	}
	
	public static double[] ArrAvgVector(double[] Tr, int size) {
		double[] result = new double[Tr.length / size];
		double dSum;
		
		for(int i = 0; i < Tr.length / size; i++) {
			
			dSum = 0;
			for(int j = 0; j < size; j++) {
				dSum += Tr[(i * size) + j];
			}
			dSum = dSum / (double) size;
			result[i] = dSum;
		}
		
		return result;
	}
	
	public static double[] ArrTranning(ArrayList<double[]> Arr) {
		int iMaxRow = Arr.get(0).length;
		int iMaxCol = Arr.size();
		double[] result = new double[iMaxRow * iMaxCol];
		
		for(int i = 0; i < iMaxCol; i++) {
			double[] temp = Arr.get(i);
			for(int j = 0; j < iMaxRow; j++) {
				result[(j * iMaxCol) + i] = temp[j];
			}
		}
		
		return result;
	}
	
	public static double[] ArrTranning(double[] A, double[] B) {
		double[] result = new double[A.length * 2];
		
		for(int i = 0; i < A.length; i++) {
			result[i * 2] = A[i];
		}
		for(int i = 0; i < B.length; i++) {
			result[i * 2 + 1] = B[i];
		}
		
		return result;
	}
	
	public static double ArrDet(double [] A, int width) {
		// 행렬식 구하는 코드
    	double result = 0;
    	
    	double temp1 = 1, temp2 = 1;
    	double[] temparr = new double[(width - 1) * (width - 1)];
    	int col = 0;
    	int NowTemp = 0;
    	int CheckX = 0;
    	int CheckY = 0;
    	
    	if(width == 2) {
			for(int j = 0; j < width; j++) {
				col = j * (width * width - 1);
				temp1 = temp1 * A[col];
			}
			for(int j = 0; j < width; j++) {
				col = j + 1;
				temp2 = temp2 * A[col];
			}
			result += temp1 - temp2;
		
    	} else {
	    	for(int i = 0; i < width; i++) {
	    		CheckX = (i / width);
    			CheckY = (i % width);
    			NowTemp = 0;
    			
    			for(int j = 0; j < A.length; j++) {
    				if(j < ((CheckX + 1) * width) && j >= ((CheckX) * width)) {
    					continue;
    				}
    				if((j - CheckY) % width == 0) {
    					continue;
    				}
    				//System.out.print(j + "\t");
    				temparr[NowTemp] = A[j];
    				NowTemp++;
    				if(NowTemp == temparr.length) {
    					break;
    				}
    			}
    			if(i % 2 == 0) {
    				result = result + A[i] * ArrDet(temparr, width - 1);
    			} else {
    				result = result + -A[i] * ArrDet(temparr, width - 1);
    			}
	    	}
    	}
    	
    	return result;
    }

	public static double [] ArrInv(double [] A, int width) {
        // 여기에 코드 작성(역행렬 구하는 코드)
    	double[] result = new double[width * width];
    	double[] temp = new double[(width - 1) * (width - 1)];
    	double detA = ArrDet(A, width);
    	double detT = 0;
    	
    	int col = 0;
    	int CheckX = 0;
    	int CheckY = 0;
    	int NowTemp = 0;
    	
    	
		for(int i = 0; i < A.length; i++) {
			CheckX = (i / width);
			CheckY = (i % width);
			
			NowTemp = 0;
			for(int j = 0; j < A.length; j++) {
				if(j < ((CheckX + 1) * width) && j >= ((CheckX) * width)) {
					continue;
				}
				if((j - CheckY) % width == 0) {
					continue;
				}
				//System.out.print(j + "\t");
				temp[NowTemp] = A[j];
				NowTemp++;
				if(NowTemp == temp.length) {
					break;
				}
			}
			//System.out.println();
			detT = ArrDet(temp, width - 1);
			if(i % 2 == 1) detT = -detT;
			col = (CheckY * width) + CheckX;
			result[col] = (1 / detA) * detT;
		}
    	
    	return result;
    }
    
    public static double[] ArrSwitch (double[] A, int WidthA, int WidthB) {
    	// 전치행렬
    	double[] result = new double[A.length];
    	int Height = A.length / WidthB;
    	
    	for(int i = 0; i < WidthB; i++) {
	    	for(int j = 0; j < Height; j++) {
	    		result[(j * WidthB) + i] = A[(i * WidthA) + j];
	    	}
    	}
    	
    	return result;
    }
    public static double[] ArrMul (double[] A, double[] B, int widthA, int widthB) {
    	// 행렬곱, 동항렬끼리만 가능
    	// B는 전치행렬
    	double[] result;
    	int iCol = 0;
    	int iRow = 0;
    	int Width = widthB;
    	int Height = A.length / widthA;
    	int ColCalc = B.length / widthB;
    	double dCalc = 0; 
    	
    	result = new double[Width * Height];
    	
    	for(int i = 0; i < result.length; i++) {
    		iRow = i / widthB;
    		iCol = i % widthB;
    		
    		dCalc = 0;
    		for(int j = 0; j < ColCalc; j++) {
    			double dA = A[(iRow * widthA) + j];
    			double dB = B[(j * widthB) + iCol];
    			
    			dCalc = Function.PrimeAdd(dCalc, Function.PrimeMulti(dA, dB));
    		}
    		
    		result[i] = dCalc;
    	}
    	
    	return result;
    }
    
    public static double[] ArrMul (double[] A, double[] B, int Width) {
    	// 행렬곱, 동항렬끼리만 가능
    	// B는 전치행렬
    	double[] result = new double[Width * Width];
    	int iCol = 0;
    	int iRow = 0;
    	int idxA = 0;
    	int idxB = 0;
    	
    	for(int i = 0; i < A.length; i++) {
    		iRow = i / Width;
    		iCol = i % Width;
    		
    		for(int j = 0; j < Width; j++) {
    			idxA = (iRow * Width) + j;
    			idxB = (iCol * Width) + j;
    			
    			result[i] = result[i] + (A[idxA] * B[idxB]);
    		}
    	}
    	
    	return result;
    }
    
    public static double[] ArrMul (int[] A, int[] B, int widthA, int widthB) {
    	// 행렬곱, 동항렬끼리만 가능
    	// B는 전치행렬
    	double[] result;
    	int iCol = 0;
    	int iRow = 0;
    	int Width = widthB;
    	int Height = A.length / widthA;
    	int ColCalc = B.length / widthB;
    	double dCalc = 0; 
    	
    	result = new double[Width * Height];
    	
    	
    	for(int i = 0; i < result.length; i++) {
    		iRow = i / widthB;
    		iCol = i % widthB;
    		
    		dCalc = 0;
    		for(int j = 0; j < ColCalc; j++) {
    			double dA = A[(iRow * widthA) + j];
    			double dB = B[(j * widthB) + iCol];
    			
    			dCalc = Function.PrimeAdd(dCalc, Function.PrimeMulti(dA, dB));
    		}
    		
    		result[i] = dCalc;
    	}
    	
    	return result;
    }
    
    public static double[] ArrMul (int[] A, int[] B, int Width) {
    	// 행렬곱, 동항렬끼리만 가능
    	// B는 전치행렬
    	double[] result = new double[Width * Width];
    	int iCol = 0;
    	int iRow = 0;
    	int idxA = 0;
    	int idxB = 0;
    	
    	for(int i = 0; i < A.length; i++) {
    		iRow = i / Width;
    		iCol = i % Width;
    		
    		for(int j = 0; j < Width; j++) {
    			idxA = (iRow * Width) + j;
    			idxB = (iCol * Width) + j;
    			
    			result[i] = result[i] + (A[idxA] * B[idxB]);
    		}
    	}
    	
    	return result;
    }
    
    public static double[] ArrPluMin(double[] A, double[] B, boolean isPlus) {
    	// 행렬합 / 차, 동항렬끼리만 가능
    	double[] result = new double[A.length];
    	int iMinus = -1;
    	if(isPlus) iMinus *= -1;
    	
    	for(int i = 0; i < A.length; i++) {
    		result[i] = A[i] + (B[i] * iMinus);
    	}
    	
    	return result;
    }
    
    public static double[] ArrPluMin(int[] A, int[] B, boolean isPlus) {
    	// 행렬합 / 차, 동항렬끼리만 가능
    	double[] result = new double[A.length];
    	int iMinus = -1;
    	if(isPlus) iMinus *= -1;
    	
    	for(int i = 0; i < A.length; i++) {
    		result[i] = A[i] + (B[i] * iMinus);
    	}
    	
    	return result;
    }
    
	public static double Euclid_Distance(double []A, double [] B) {
		double ed = 0;

		if(A.length != B.length) {
			return -1;
		}
		for(int k=0; k<A.length; k++) {
			ed += Math.pow(A[k]-B[k],2);
		}

		return Math.sqrt(ed);
	}
}
