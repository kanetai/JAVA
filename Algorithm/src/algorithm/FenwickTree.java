package algorithm;

import java.util.Arrays;
/** AOJ No. 0167, 0271*/
public final class FenwickTree{
	private int[] x;
	public FenwickTree(int n){ init(n);}
	public FenwickTree(int[] x){ init(x, 0, x.length);}
	public FenwickTree(int[] x, int begin, int end){ init(x, begin, end); }
	/** all 0, O(n) n:=x.length */
	public final void init(int n){ x = new int[n]; Arrays.fill(x, 0); }
	/** O(n log n) n:=x.length */
	public final void init(int[] x){ init(x, 0, x.length); }
	/** O(n log n) n:=x.length */
	public final void init(int[] x, int begin, int end){
		this.x = new int[end - begin];
		System.arraycopy(x, begin, this.x, 0, end - begin);
	}
	/**
	 * Gets summation from x[0] to x[i] O(log n) n:=x.length<br>
	 * @param i	(inclusive) end index
	 * @return	x[0] + x[1] + ... + x[i]
	 */
	public final int sum(int i) {
		int ret = 0;
		//If 1 start (not 0 start), use j -= (j&-j)
		for(int j = i; j >= 0; j = ((j & (j+1)) - 1)) ret += x[j];
		return ret;
	}
	/**
	 * Gets summation from x[i] to x[j] (※i <= j) O(log n) n:=x.length
	 * @param i (inclusive) start index
	 * @param j (inclusive) end index
	 * @return  x[i] + x[i+1] + ... + x[j]
	 */
	public final int sum(int i, int j){ return i == 0 ? sum(j) : sum(j) - sum(i-1); }
	/**
	 * x[i] += a O(log n) n:=x.length<br>
	 * @param i target index
	 * @param a	
	 */
	public final void add(int i, int a) {
		//If 1 start (not 0 start), use j += (j&-j)
		for(int j = i; j < x.length; j |= j+1) x[j] += a;
	}
}