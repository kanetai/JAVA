package algorithm;

import java.util.Arrays;

public class IntegerAlgorithm extends Utility{
	/**
	 * Via Eratosthenes' sieve, get primality test table<br>
	 * O( nlog(log n) )<br>
	 * AOJ No. 0009
	 * @param n table_size
	 * @return primality test table {isPrime(0), isPrime(1),..., isPrime(n-1)}
	 */
	public static final boolean[] sieve(int n){
		boolean[] ret = new boolean[n]; Arrays.fill(ret, true);
		ret[0] = ret[1] = false;
		for(int i = 2; i*i < n; ++i)
			if(ret[i]) for(int j = i*i; j < n; j+=i) ret[j] = false;
		return ret;
	}
	/**
	 * Via Extended Euclid's algorithm, get the Greatest Common Divisor GCD(a,b).<br>
	 * O(log max{a,b})<br>
	 * AOJ No.0005
	 * @param a
	 * @param b
	 * @return GCD(a,b)
	 */
	public static final int GCD(int a, int b){ return b == 0 ? a : GCD(b, a%b); }
	/**
	 * Get the Least Common Multiple LCM(a,b).<br>
	 * O(log max{a,b})<br>
	 * AOJ No.0005
	 * @param a
	 * @param b
	 * @return GCD(a,b)
	 */
	public static final int LCM(int a, int b){ return a / GCD(a, b) * b; }
	/**
	 * Via Extended Euclid's algorithm, get the Greatest Common Divisor GCD(x[0], x[1], ..., x[n-1])<br>
	 * n = x.length, O(n log max{a,b})
	 * @param x
	 * @return GCD(x[0], x[1], ..., x[x.length-1])
	 */
	public static final int GCD(int[] x){
		int ret = x[0];
		for(int i = 1; i < x.length; ++i) ret = GCD(ret, x[i]);
		return ret;
	}
	/**
	 * Get the Least Common Multiple LCM(x[0], x[1], ..., x[n-1])<br>
	 * n = x.length, O(n log max{a,b})
	 * @param x
	 * @return
	 */
	public static final int LCM(int[] x){
		int ret = x[0];
		for(int i = 1; i < x.length; ++i) ret = LCM(ret, x[i]);
		return ret;
	}
	/**
	 * Via Extended Euclid's algorithm, get GCD(a,b) and a solution (x1,x2) for ax1 + bx2 = GCD(a,b). <br>
	 * O(log max(a,b))
	 * @param a
	 * @param b
	 * @return array {x1, x2, GCD(a,b)}
	 */
	public static int[] extGCD(int a, int b){
		int[] ret = new int[3];
		ret[2] = extGCD(a, b, ret);
		return ret;
	}
	/**
	 * Via Extended Euclid's algorithm, get GCD(a,b) and a solution (x1,x2) for ax1 + bx2 = GCD(a,b). O(log max(a,b))
	 * @param a
	 * @param b
	 * @param x	array(size>=2)←{x1, x2} |modify|
	 * @return	GCD(a,b)
	 */
	public static int extGCD(int a, int b, int x[]){
		int g = a;
		x[0] = 1;
		x[1] = 0;
		if (b != 0){
			g = extGCD(b, a % b, x);
			swap(x, 0, 1);
			x[1] -= (a / b) * x[0];
		}
		return g;
	}
}
