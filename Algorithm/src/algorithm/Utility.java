package algorithm;
public class Utility {
	public static final double EPS = 1e-10;
	public static boolean equal(double a, double b){ return Math.abs(a-b) < EPS; }	// a == b
	public static boolean less(double a, double b){ return a - b < -EPS; }			// a < b
	public static boolean leq(double a, double b){ return a - b < EPS; }			// a <= b
	public static boolean greater(double a, double b){ return less(b,a); }			// a > b
	public static boolean geq(double a, double b){ return leq(b,a); }				// a >= b
	/**
	 * Swaps x[i] for x[j].<br>
	 * AOJ No. 0011, 0040
	 * @param x array |modify|
	 * @param i
	 * @param j
	 * @return swapped array
	 */
	public static final int[] swap(int[] x, int i, int j){
		int tmp = x[i]; x[i] = x[j]; x[j] = tmp; return x;
	}
}
