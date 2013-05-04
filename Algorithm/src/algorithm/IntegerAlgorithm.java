package algorithm;

import java.util.Arrays;
import static algorithm.Utility.swap;
public final class IntegerAlgorithm {
	IntegerAlgorithm(){}
	/**
	 * Creates primality test table via Eratosthenes' sieve. <br>
	 * O( nlog(log n) )<br>
	 * AOJ No. 0009, 0044, 0053, 0056, 0150
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
	 * Calculates the Greatest Common Divisor GCD(a,b) via Extended Euclid's algorithm. <br>
	 * O(log max{a,b})<br>
	 * AOJ No.0005, 0040
	 * @param a
	 * @param b
	 * @return GCD(a,b)
	 */
	public static final int GCD(int a, int b){ return b == 0 ? a : GCD(b, a%b); }
	/**
	 * Calculates the Least Common Multiple LCM(a,b).<br>
	 * O(log max{a,b})<br>
	 * AOJ No.0005
	 * @param a
	 * @param b
	 * @return GCD(a,b)
	 */
	public static final int LCM(int a, int b){ return a / GCD(a, b) * b; }
	/**
	 * Calculates the Greatest Common Divisor GCD(x[0], x[1], ..., x[n-1]) via Extended Euclid's algorithm. <br>
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
	 * Calculates the Least Common Multiple LCM(x[0], x[1], ..., x[n-1])<br>
	 * AOJ No. 0114(partial modification)
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
	 * Calculates GCD(a,b) and a solution (x1,x2) for ax1 + bx2 = GCD(a,b) via Extended Euclid's algorithm. <br>
	 * O(log max(a,b))
	 * @param a
	 * @param b
	 * @return array {x1, x2, GCD(a,b)}
	 */
	public static final int[] extGCD(int a, int b){
		int[] ret = new int[3];
		ret[2] = extGCD(a, b, ret);
		return ret;
	}
	/**
	 * Calculates GCD(a,b) and a solution (x1,x2) for ax1 + bx2 = GCD(a,b) via Extended Euclid's algorithm. <br>
	 * <a href=http://www2.cc.niigata-u.ac.jp/~takeuchi/tbasic/BackGround/EuclidRec.html>consultation</a><br>
	 * O(log max(a,b))<br>
	 * AOJ No. 0040
	 * @param a
	 * @param b
	 * @param x	array(size>=2)←{x1, x2} |modify|
	 * @return	GCD(a,b)
	 */
	public static final int extGCD(int a, int b, int x[]){
		int g = a;
		x[0] = 1; x[1] = 0;
		if (b != 0){
			g = extGCD(b, a % b, x);
			swap(x, 0, 1);
			x[1] -= (a / b) * x[0];
		}
		return g;
	}
	/**
	 * Tests whether a positive integer is prime number or not.
	 * @param n positive integer
	 * @return true -> n is prime number. false -> n is prime number.
	 */
	public static final boolean isPrime(int n){
		if( n <= 1 ) return false; 
		for(int i=2; i*i <= n;i++ ) if( n % i == 0 ) return false;
		return true;
	}
	/**
	 * Tests whether two positive integers are coprime or not.<br>
	 * O( log max(a,b) )<br>
	 * AOJ No. 0040
	 * @param a integer
	 * @param b integer
	 * @return true -> a is coprime to b. false -> a is not coprime to b.
	 */
	public static final boolean isCoprime(int a, int b){ return GCD(a,b) == 1; }

	/**
	 * Calculates modular multiplicative inverse(a^-1 mod m).
	 * If a^-1 mod m doesn't exist(i.e. a is coprime to m), returns 0.<br>
	 * O( log max(a,m) )<br>
	 * AOJ No. 0040
	 * @param a positive integer
	 * @param m modulus
	 * @return	a^-1 mod m.  0 -> a^-1 mod m doesn't exist.
	 */
	public static final int modInverse(int a, int m){
		int[] x = new int[2];
		return (extGCD(a, m, x) == 1) ? (x[0] + m) % m : 0;
	}
}
