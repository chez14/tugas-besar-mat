import MatrixUtil.*;

public class Main {
	public static void main(String[] args) throws InvalidMoveException {
		Matrix x = new Matrix(new double[][] {
			{3,2,1,4}, 
			{0,2,2,4}, 
			{0,0,3,4},
			{0,0,3,5}
		});
		
		System.out.println("Matrix awal:");
		System.out.println(x);
		
		System.out.println("Determinan: " + x.getDeterminant());
		
		Matrix cofactor = x.getCofactor();
		System.out.println("Matrix Cofactor:");
		System.out.println(cofactor);
		
		
		Matrix inverse = x.getInverse();
		System.out.println("Matrix Inverse:");
		System.out.println(inverse);
		
		System.out.println("Determinan dari matrix inverse: " + inverse.getDeterminant());
		 
		
		System.out.println("Selesai!");
	}
}
