package algorithm;
import java.util.*;
import java.util.stream.*;
public class PrimalityTest {
	PrimalityTest(){}
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
	 * Creates primality test table via Eratosthenes' sieve. <br>
	 * O( nlog(log n) )<br>
	 * AOJ No. 0009, 0044, 0053, 0056, 0150, 0165, 0222
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
	 * Creates prime numbers in the range of [0, n). <br>
	 * O( nlog(log n) )
	 * @param n upper bound (exclusive)
	 * @return Prime numbers in the range of [0, n) and they are ascending order.
	 */
	public static final ArrayList<Integer> primeNumbers(int n) {
		if (n <= 0) throw new IllegalArgumentException();
		boolean[] table = sieve(n);
		ArrayList<Integer> ret = new ArrayList<>(n);
		for (int i = 2; i < n; ++i) if (table[i]) ret.add(i);
		return ret;
	}
	/**
	 * Creates primality test table via segment sieve in the range of [L, U).
	 * @param L Lower bound
	 * @param U Upper bound
	 * @param removeNonPrime
	 * If removeNonPrime is true, return Prime number in the range of [L, U) and they are ascending order.
	 * If removeNonPrime is false, return primality test table( table[i] > 0 ? i+L is prime number(i+L == table[i]) : i is not prime number.
	 * @param prime Prime numbers in the range of [0, \sqrt(U)). Prime numbers must be ascending order.
	 * @return prime numbers in the range of [L, U) or primality test table.
	 */
	public static final ArrayList<Integer> segmentedSieve(int L, int U, ArrayList<Integer> prime, boolean removeNonPrime) {
		if (!(0 <= L && L < U)) throw new IllegalArgumentException();
		ArrayList<Integer> ret = new ArrayList<>((U-L)*2);
		IntStream.range(L, U).forEach(i -> ret.add(i - L, i));
		for(int p : prime) {
			if (p*p >= U) break;
			int j = 
					(p >= L ? p*p :
						L % p == 0 ? L :
							L - (L % p) + p);
            for (; j < U; j += p) ret.set(j - L, 0);
        }
        return removeNonPrime ? 
				ret.stream().filter(e -> e > 0).collect(Collectors.toCollection(ArrayList::new)) : ret;
	}
	/**
	 * Creates prime numbers in the range of [0, n) via segmentSieve, iteratively.<br>
	 * AOJ 0185
	 * @param n upper bound (exclusive)
	 * @return prime numbers in the range of [0, n) element is ordered by 
	 */
	public static final ArrayList<Integer> iterativeSieve(int n) {
		if (n <= 0) throw new IllegalArgumentException();
		final int BLOCK = (int)Math.ceil(Math.sqrt(n));
		ArrayList<Integer> ret = primeNumbers(BLOCK);
		for (int b = BLOCK; b < n; b += BLOCK) {
			ArrayList<Integer> tmp = segmentedSieve(b , b+BLOCK, ret, false);
			ret.addAll(tmp.stream().filter(e -> 0 < e && e < n)
					.collect(Collectors.toList()));
		}
		return ret;
	}
}
