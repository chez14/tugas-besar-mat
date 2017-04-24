package MatrixUtil;
/**
 * This class are actually intended to prettify the matrix printing on the console.
 * 
 * @version v0.1.1-alpha1
 * @author Chris(2016730011)
 * @author Irvan(201673077)
 */
final class Prettify {
	/**
	 * A method to count the maximum required size of single item for the matrix.
	 * so the printing will be smoothly formatted.
	 * @param korban Matrix that will be survey'd.
	 * @return single digit maximum.
	 */
	public static int countSingleDigitSpace(Matrix korban){
		double[][] matrixnya = korban.toIntArray();
		
		int maxSingleSize = 1;
		double maxSize=matrixnya[0][0];
		
		// we will loop entire matrix's item to get it's maximum number and
		// calculate the digit space required for it. Then store it into
		// a temporary variable, which is will be returned to the caller.
		for(double[] row : matrixnya){
			for(double col : row){
				if(Math.abs(col) > maxSize){
					maxSize = Math.abs(col);
					maxSingleSize = getDigit(maxSize);
				}
			}
		}
		System.out.println("MSS: " + maxSingleSize);
		// just in case if there's -24 and 30.
		return maxSingleSize + 1;
	}
	
	
	/**
	 * This will count the digit of the integer.
	 * @param i digit that will be counted
	 * @return digits
	 */
	public static int getDigit(double i){
		int temp=0;
		
		while((i=Math.floor(i/10)) > 0)
			temp++;
		
		System.out.println("Length: " + temp);
		return temp;
	}
	
	
}
