package OBEUtil;

/**
 * The OBE Class This class are representate the row of the OBE operation. Class
 * has automation on how to resort them and such.
 * 
 * @author Gunawan Christianto (2016730011)
 * @author Irvan Hardyanto (2016730077)
 */
public class OBERow {
	protected double[] col;
	protected double constants;

	/**
	 * This variables are used to determine where's this row should be placed.
	 * This variable are should be package private.
	 */
	protected int leftZero, rightZero;
	/**
	 * requireRedetects are indicating that this row required to be redetected
	 * the zeroses for next sorting. This variables are should be package
	 * private.
	 */
	protected boolean requireRedetect = true;

	/**
	 * Constructor of this class
	 * 
	 * @param col
	 *            column for this class
	 */
	public OBERow(double[] col, double constants) {
		this.col = col;
		this.constants = constants;
	}

	/**
	 * Create new row with 0 as the initial value
	 * 
	 * @param colSize
	 *            size of desired column.
	 */
	public OBERow(int colSize) {
		this.col = new double[colSize];
	}

	/**
	 * used to detect how much zero on leftest and rightest side of the column
	 */
	protected void detectZero() {
		int currLeft = 0;
		while (col[currLeft] == 0)
			currLeft++;
		leftZero = currLeft - 1;

		int currRight = col.length - 1;
		while (col[currRight] == 0)
			currRight--;
		rightZero = currRight + 1;
		requireRedetect = false;
	}

	/**
	 * Multiply the row with a scaler value.
	 * 
	 * @param scalar
	 *            Scalar value that multiplies the matrix
	 */
	public void multiply(double scalar) {
		requireRedetect = true;
		for (int i = 0; i < col.length; i++)
			col[i] *= scalar;
		constants *= scalar;
	}

	/**
	 * subtracts this row with other row.
	 * 
	 * @param subtractor
	 *            the subtractor for this row.
	 */
	public void add(OBERow subtractor) {
		this.add(subtractor, 1);
	}

	/**
	 * Subtract this row with other row, but with some kind of scalar values.
	 * Scalar value will be multiplied to respected column before subtraction.
	 * 
	 * @param substractor
	 *            the substractor row.
	 * @param scalar
	 *            scalar value that will be multiplied to the row before the
	 *            subtraction held.
	 */
	public void add(OBERow substractor, double scalar) {
		requireRedetect = true;
		for (int i = 0; i < col.length; i++)
			col[i] += substractor.getValue(i) * scalar;
		constants += substractor.getConstant() * scalar;
	}

	/**
	 * Converts this row as double array.
	 * 
	 * @return double
	 */
	public double[] toDoubleArray() {
		return col;
	}

	/**
	 * Converts this row as double array.
	 * 
	 * @param includeConstant
	 *            will include the constant values into the array.
	 * @return double
	 */
	public double[] toDoubleArray(boolean includeConstant) {
		if (!includeConstant)
			return toDoubleArray();

		double[] temp = new double[col.length + 1];
		for (int i = 0; i < col.length; i++)
			temp[i] = col[i];
		temp[col.length] = constants;
		
		return temp;
	}

	/**
	 * Set a value of this row on (index) column.
	 * 
	 * @param index
	 *            index of the col that want to be edited.
	 * @param value
	 *            targeted col index's value.
	 * @throws ArrayIndexOutOfBoundsException
	 *             when the index reaches out the bounds, this exception will be
	 *             thrown.
	 */
	public void setValue(int index, double value) throws ArrayIndexOutOfBoundsException {
		requireRedetect = true;
		col[index] = value;
	}

	/**
	 * used to set the constants.
	 * 
	 * @param value
	 *            value of the new constant.
	 */
	public void setConstant(double value) {
		constants = value;
	}

	/**
	 * Returns the respected column value of the index.
	 * 
	 * @param index
	 *            column index that want to be getted.
	 * @return Respected value of the targetted column.
	 * @throws ArrayIndexOutOfBoundsException
	 *             This exception will be thrown when you accidently access
	 *             array index outside the bounds.
	 */
	public double getValue(int index) throws ArrayIndexOutOfBoundsException {
		return col[index];
	}

	/**
	 * Return the constant value
	 * 
	 * @return constant value
	 */
	public double getConstant() {
		return constants;
	}
}
