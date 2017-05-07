import java.util.Arrays;

import CramerAlgo.CramerMatrix;
import CramerAlgo.InfiniteSolutionException;
import CramerAlgo.UnsolveableEquationsException;
import MatrixUtil.*;
import OBEUtil.OBEMatrix;

public class Main {
	public static void main(String[] args) throws InvalidMoveException, UnsolveableEquationsException, InfiniteSolutionException {
		/*Matrix x = new Matrix(new double[][] {
			{3,2,1,4}, 
			{0,2,2,4}, 
			{0,0,3,4},
			{0,0,3,5}
		});*/
		Matrix x = new Matrix(new double[][] { 
			{1, 1, 2},
			{2, 4, -3},
			{3, 6, -5},
		});
		
		System.out.println("Matrix awal:");
		System.out.println(x);
		
		System.out.println("Determinan: " + x.getDeterminant());
		
		Matrix cofactor = x.getCofactor();
		System.out.println("Matrix Cofactor:");
		System.out.println(cofactor);
		
		
		//Matrix inverse = x.getInverse();
		//System.out.println("Matrix Inverse:");
		//System.out.println(inverse);
		
		//System.out.println("Determinan dari matrix inverse: " + inverse.getDeterminant());
		 
		
		System.out.println("Selesai!");
		
		
		System.out.println("Butuh");
		//CramerMatrix cm = new CramerMatrix(x, new double[] {10, 5, 0});
		//System.out.println(Arrays.toString(cm.getSolution()));
		
		OBEMatrix om = new OBEMatrix(x, new double[] {9, 1, 0});
		om.add(1, 0, -2);
		om.add(2, 0, -3);
		System.out.println(om);
	}
}
