package OBEUtil;

import MatrixUtil.Matrix;

public class OBEMatrix {
	protected double[] constants;
	protected OBERow[] rows;

	// this variable are purposed to be an helper for printing any movements.
	protected double[][] values;

	private Matrix thisIsJustAnInternalMatrix;

	/**
	 * Constructor for this class.
	 * 
	 * @param values
	 *            matrix values that representate the coefficient for the
	 *            respected variables.
	 * @param constants
	 */
	public OBEMatrix(double[][] values, double[] constants) {
		this.constants = constants;
		this.values = values;
		rows = new OBERow[values.length];
		for (int i = 0; i < values.length; i++)
			rows[i] = new OBERow(values[i], constants[i]);
		thisIsJustAnInternalMatrix = new Matrix(values);
	}

	/**
	 * Constructor for this class.
	 * 
	 * @param values
	 *            Matrix object that holds the equation's value.
	 * @param constants
	 *            Constants that remined behind the equal symbol.
	 */
	public OBEMatrix(Matrix values, double[] constants) {
		this(values.toDoubleArray(), constants);
	}

	/**
	 * Substract the targeted OBE matrix with it's substractor
	 * 
	 * @param target
	 *            Target's index location
	 * @param substractor
	 *            substractor index location
	 * @param scalar
	 *            Scalar value that will be multiplied to the subtractor before
	 *            the subtraction
	 * @throws ArrayIndexOutOfBoundsException
	 *             When the index value are not in bounds, this exception will
	 *             be thrown
	 */
	public void add(int target, int substractor, double scalar) throws ArrayIndexOutOfBoundsException {
		rows[target].add(rows[substractor], scalar);
	}

	/**
	 * Substract the targeted OBE matrix with it's substractor Please note that
	 * the index starts from 0.
	 * 
	 * @param target
	 *            Target's index location
	 * @param substractor
	 *            substractor index location
	 * @throws ArrayIndexOutOfBoundsException
	 *             When the index value are not in bounds, this exception will
	 *             be thrown
	 */
	public void add(int target, int substractor) throws ArrayIndexOutOfBoundsException {
		add(target, substractor, 1);
	}

	/**
	 * Swap the row. Please note that the index starts from 0.
	 * 
	 * @param a
	 *            index of row A
	 * @param b
	 *            index of row B
	 * @throws ArrayIndexOutOfBoundsException
	 *             When the index value are not in bounds, this exception will
	 *             be thrown
	 */
	public void swap(int a, int b) throws ArrayIndexOutOfBoundsException {
		OBERow temp = rows[a];
		rows[a] = rows[b];
		rows[b] = temp;
	}

	/**
	 * Multiplies the selected index with a scalar.
	 * 
	 * @param index
	 *            Targeted index
	 * @param scalar
	 *            Scalar values that will be multiplied
	 * @throws ArrayIndexOutOfBoundsException
	 *             When the index value are not in bounds, this exception will
	 *             be thrown
	 */
	public void multiply(int index, double scalar) throws ArrayIndexOutOfBoundsException {
		rows[index].multiply(scalar);
	}

	/**
	 * MEBT Detector
	 * 
	 * @return will return true if it's MEBT
	 */
	public boolean isMEBT() {
		boolean result = true;
		resync();
		for (int i = 0; i < values.length && result; i++)
			for (int j = 0; j < values[0].length && result; j++)
				result &= ((i == j) && (values[i][j] == 1)) || (values[i][j] == 0);
		return result;
	}
	
	/**
	 * Return the solved qeuation's solution.
	 * 
	 * @return Solution of the respected variables.
	 * @throws UnableToSolveException then the equation cannot be solved, then this error will be returned.
	 */
	public double[] getSolution() throws UnableToSolveException {
		if(!isMEBT())
			throw new UnableToSolveException("MEBT is required to solve the equation");
		return constants;
	}

	/**
	 * Check on current move, is it might have a chance to get the solution.
	 * 
	 * @return -1 on it has infinitely many solution, 0 on no solution and 1 on
	 *         has only 1 solution.
	 */
	public int detectSolution() {
		if (!detectHasSolution())
			return 0;
		return (detectSingleSolution() ? 1 : -1);
	}

	/**
	 * Detect if it has solution or not. This method are responsible for
	 * returning `0` on the detectSolution method.
	 * 
	 * @return
	 */
	private boolean detectHasSolution() {
		double hasil = 0;
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values.length; j++)
				hasil += values[i][j];
			if (hasil == 0 && constants[i] != 0)
				return false;
		}
		return true;
	}

	/**
	 * We'll detect the matrix and check if it has single solution. If it's
	 * possible to find the single solution, we'll report it to you.
	 * 
	 * @return true if it has single solution.
	 */
	private boolean detectSingleSolution() {
		resync();
		for (int j = 0; j < values.length - 2; j++)
			for (int i = j; i < values.length - 1; i++)
				if (!detectRowRatioConsistant(i, i + 1))
					return false;
		return true;
	}

	/**
	 * Detect the ratio consistency of the those column.
	 * 
	 * @param rowA
	 *            row A
	 * @param rowB
	 *            row B
	 * @return true if it's consistent
	 */
	private boolean detectRowRatioConsistant(int rowA, int rowB) {
		resync();
		double ratio = values[rowA][0] / values[rowB][0];
		for (int j = 1; j < values[0].length; j++)
			if ((values[rowA][j] / values[rowB][j]) != ratio)
				return false;
		return true;
	}

	/**
	 * Resync are used to resync the values with the OBERow class.
	 */
	private void resync() {
		for (int i = 0; i < rows.length; i++) {
			values[i] = rows[i].toDoubleArray(true);
			constants[i] = rows[i].getConstant();
		}
		thisIsJustAnInternalMatrix.setValues(values);
	}

	/**
	 * Represent current object to a beautifully formatted string. By resync all
	 * those values and it's constants, we could get the best way to ge those
	 * juicy string.
	 * 
	 * @return Beautifully formatted string.
	 */
	@Override
	public String toString() {
		resync();
		return thisIsJustAnInternalMatrix.toString();
	}
}
