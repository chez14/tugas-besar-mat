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
	 * Constructor for this class.
	 * 
	 * @param values
	 *            Matrix object that holds the equation's value.
	 * @param constants
	 *            Constants that remined behind the equal symbol.
	 */
	public CramerMatrix(Matrix values, double[] constants) {
		super(values.toDoubleArray());
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
	 * @throws InfiniteSolutionException
	 */
	public double[] getSolution()
			throws InvalidMoveException, UnsolveableEquationsException, InfiniteSolutionException {
		double[] hasil = new double[matrix[0].length];
		double det = getDeterminant();
		boolean indefiniteFlag = false;
		for (int i = 0; i < matrix[0].length; i++) {
			hasil[i] = generateIthMatrix(i).getDeterminant();
			if (det == 0)
				if (hasil[i] != 0)
					throw new UnsolveableEquationsException("This matrix has no solution.");
				else
					indefiniteFlag = true;
			hasil[i] /= det;
		}
		if (indefiniteFlag)
			throw new InfiniteSolutionException("The eqaution has Indefenitely many solution.");

		return hasil;
	}

	/**
	 * Cast this object to Matrix, yup, it's parent.
	 * 
	 * @return new Matrix
	 */
	public Matrix toMatrix() {
		return new Matrix(matrix);
	}
}
