/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OBEIntelegent;

/**
 *
 * @author hayashi
 */
public class OBERow {

    protected double[] cols;

    public OBERow(double[] cols) {
        this.cols = cols;
    }

    /**
     * Get row's value at particular cols.
     *
     * @param index column
     * @return value of given current cols
     * @throws IndexOutOfBoundsException
     */
    public double getValue(int index) throws IndexOutOfBoundsException {
        return cols[index];
    }

    /**
     * Convert current object to double array
     *
     * @return Matri in array of double presentation
     */
    public double[] toDoubleArray() {
        return cols;
    }

    public void substractPower(OBERow subtractor, double power) {

    }
}
