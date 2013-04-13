package algorithm;
public class Utility {
	/**
	 * Swap x[i] for x[j].
	 * @param x array |modify|
	 * @param i
	 * @param j
	 * @return swapped array
	 */
	public static final int[] swap(int[] x, int i, int j){
		int tmp = x[i]; x[i] = x[j]; x[j] = tmp; return x;
	}
}
