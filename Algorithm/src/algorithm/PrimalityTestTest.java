package algorithm;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.util.*;

public class PrimalityTestTest {
	static final int N = 10000000;
	static final Random random = new Random();
	private ArrayList<Integer> p = null;
	ArrayList<Integer> primes() throws FileNotFoundException {
		if (p != null) return p;
		ArrayList<Integer> tmp = new ArrayList<>();
		String path = getClass().getClassLoader().getResource("prime.txt").getPath();
		File file = new File(path);
		try (Scanner scan = new Scanner(file)) {
			while (scan.hasNextInt()) tmp.add(scan.nextInt());
		}
		return this.p = tmp;
	}
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		System.gc();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsPrime() throws FileNotFoundException {
		HashSet<Integer> prime = new HashSet<>(primes());
		int n = 10000;
		while (n-- > 0) {
			int i = random.nextInt(N);
			assertTrue(prime.contains(i) == PrimalityTest.isPrime(i));
		}
	}
	@Test
	public void testSieve() throws FileNotFoundException {
		HashSet<Integer> prime = new HashSet<>(primes());
		boolean[] sieve = PrimalityTest.sieve(N);
		for (int i = 0; i < N; ++i) assertTrue(sieve[i] == prime.contains(i));
	}
	@Test
	public void testPrimeNumbers() throws FileNotFoundException {
		ArrayList<Integer> prime = primes(), sieve = PrimalityTest.primeNumbers(N);
		assertTrue(prime.size() == sieve.size());
		for (int i = 0; i < prime.size(); ++i) {
			int a = prime.get(i), b = sieve.get(i);
			assertTrue(a == b);
		}
	}
	@Test
	public void testIterativeSieve() throws FileNotFoundException {
		HashSet<Integer> prime = new HashSet<>(primes());
		ArrayList<Integer> sieve = PrimalityTest.iterativeSieve(N);
		sieve.stream().forEach(p -> assertTrue((p > 0) == prime.contains(p)));
	}
	@Test
	public void testSegmentSieve() throws FileNotFoundException {
		HashSet<Integer> prime = new HashSet<>(primes());
		int n = 10;
		while (n-- > 0) {
			int a = random.nextInt(N), b = random.nextInt(N);
			int l = Math.min(a, b), u = Math.max(a, b);
			ArrayList<Integer> seg = PrimalityTest.segmentedSieve(l, u, PrimalityTest.primeNumbers((int)Math.ceil(Math.sqrt(u))), false);
			seg.stream().forEach(p -> assertTrue((p > 0) == prime.contains(p)));
		}
	}
}
