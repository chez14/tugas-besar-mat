package CramerAlgo;

import MatrixUtil.InvalidMoveException;
import MatrixUtil.Matrix;

public class CramerMatrix extends MatrixUtil.Matrix {
	protected double[] constants;

	/**
	 * Constructor for this class.
	 * 
	 * @param values
	 *            matrix values that representate the coefficient for the
	 *            respected variables.
	 * @param constants
	 */
	public CramerMatrix(double[][] values, double[] constants) {
		super(values);
		this.constants = constants;
	}

	/**
	 * Generate Cramer matrix for respected values.
	 * 
	 * @param colNum
	 *            column number, or we can say the variable's index.
	 * @return matrix for the respected variables.
	 */
	protected Matrix generateIthMatrix(int colNum) {
		Matrix a = new Matrix(this.matrix.length, this.matrix.length);
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[0].length; j++)
				if (j == colNum)
					a.setValue(i, j, constants[i]);
				else
					a.setValue(i, j, matrix[i][j]);
		return a;
	}

	/**
	 * Calculate the solution using Cramer's rule
	 * 
	 * @return solution in array of double
	 * @throws InvalidMoveException
	 *             When the matrix are not square or it's determinant are 0,
	 *             This exception will be thrown.
	 * @throws UnsolveableEquationsException 
	 */
	public double[] getSolution() throws InvalidMoveException, UnsolveableEquationsException {
		double[] hasil = new double[matrix[0].length];
		double det = getDeterminant();
		if (det == 0)
			throw new UnsolveableEquationsException("The matrix's determinant are 0. So it's solution are infinite.");
		for (int i = 0; i < matrix[0].length; i++)
			hasil[i] = generateIthMatrix(i).getDeterminant() / det;

		return hasil;
	}
	
	/**
	 * Cast this object to Matrix, yup, it's parent.
	 * @return new Matrix
	 */
	public Matrix toMatrix() {
		return new Matrix(matrix);
	}
}
