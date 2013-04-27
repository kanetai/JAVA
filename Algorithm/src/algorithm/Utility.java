package algorithm;

import java.util.HashMap;
import java.util.TreeMap;
/**
 * 参考ページ<br>
 * <a href=http://ja.wikipedia.org/wiki/%E9%AD%94%E6%96%B9%E9%99%A3>魔方陣 - Wikipedia</a><br>
 * <a href=http://www2u.biglobe.ne.jp/~zed/m_stitle.htm>魔方陣の解法について</a><br>
 */
public final class Utility {
	private Utility(){}
	public static final double EPS = 1e-10;
	public static boolean equal(double a, double b){ return Math.abs(a-b) < EPS; }	// a == b
	public static boolean less(double a, double b){ return a - b < -EPS; }			// a < b
	public static boolean leq(double a, double b){ return a - b < EPS; }			// a <= b
	public static boolean greater(double a, double b){ return less(b,a); }			// a > b
	public static boolean geq(double a, double b){ return leq(b,a); }				// a >= b
	public static boolean isTrue(int field, int bit){ return (field & bit) != 0; }
	public static boolean isFalse(int field, int bit){ return !isTrue(field, bit); }
	
	public static final int[] emptyIntArray = new int[0];
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

	/**
	 * Swaps a[i] for a[j]<br>
	 * AOJ No. 0041
	 * @param a array |modify|
	 * @param i 0 <= i < a.length
	 * @param j 0 <= j < a.length
	 * @return swapped array
	 */
	public static Object[] swap(Object[] a, int i, int j){	
		Object tmp = a[i]; a[i] = a[j]; a[j] = tmp; return a;
	}
	/**
	 * Swaps a M[i][j] for M[k][l]
	 * @param M matrix |modify|
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 * @return swapped matrix
	 */
	public static int[][] swap(int[][] M, int i, int j, int k, int l){
		int temp = M[i][j]; M[i][j] = M[k][l]; M[k][l] = temp;
		return M;
	}

	/**
	 * Reverses the order of the elements in the range[fromIndex, toIndex).<br>
	 * n = toIndex - fromIndex (0 <= fromIndex < toIndex <= a.length), O(n)<br>
	 * AOJ No. 0041
	 * @param a array
	 * @param fromIndex
	 * @param toIndex
	 */
	public static void reverse(Object[] a, int fromIndex, int toIndex) {
		for(int n = toIndex - fromIndex, mid = n>>1, i = 0, j = n-1; i < mid; ++i, --j)
			swap(a, fromIndex+i, fromIndex+j); 
	}
	/**
	 * Reverses the order of the elements in the specified array.
	 * @param a array
	 */
	public static void reverse(Object[] a){
		reverse(a, 0, a.length);
	}

	/**
	 * Exteded HashMap<K, Integer> for counting frequency of K.
	 * @param <K> key
	 */
	@SuppressWarnings("serial") public static class HashFreqTable<K> extends HashMap<K,Integer>{
		HashFreqTable(){ super(); }
		HashFreqTable(HashFreqTable<K> f){ super((HashMap<K, Integer>)f); }
		public Integer add(K key) { return put(key, containsKey(key) ? get(key) + 1 : 1); }
	}
	/**
	 * Extended TreeMap<K, Integer> for counting frequency of K.<br>
	 * AOJ No. 0043, 0065
	 * @param <K> key
	 */
	@SuppressWarnings("serial") public static class TreeFreqTable<K> extends TreeMap<K,Integer>{
		TreeFreqTable(){ super(); }
		TreeFreqTable(TreeFreqTable<K> f){ super((TreeMap<K, Integer>)f); }
		public Integer add(K key) { return put(key, containsKey(key) ? get(key) + 1 : 1); }
	}
}
