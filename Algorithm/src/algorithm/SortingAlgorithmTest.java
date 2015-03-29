package algorithm;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

public class SortingAlgorithmTest {
	static class sortIntTestCase {
		static Random random = new Random();
		int[] before, after;
		int fromIndex, toIndex;
		public static final int N_MAX = 1000;
		public static final int V_MAX = Integer.MAX_VALUE-1;
		void assignCase(int n, int fromIndex, int toIndex, int v) {
			this.fromIndex = fromIndex; this.toIndex = toIndex;
			this.before = new int[n]; this.after = new int[n];
			for (int i = 0; i < n; ++i) {
				int value = random.nextInt(v+1);
				this.after[i] = this.before[i] = random.nextBoolean() ? value : -value;
			}
			Arrays.sort(this.after, fromIndex, toIndex);
		}
		public sortIntTestCase(int n, int v) {
			int toIndex = 1+random.nextInt(n), fromIndex = random.nextInt(toIndex);
			assignCase(n, fromIndex, toIndex, v);
		}
		public sortIntTestCase(int n) {
			int toIndex = 1+random.nextInt(n), fromIndex = random.nextInt(toIndex);
			assignCase(n, fromIndex, toIndex, V_MAX);
		}
		public sortIntTestCase() {
			int n = 1 + random.nextInt(N_MAX);
			int toIndex = 1+random.nextInt(n), fromIndex = random.nextInt(toIndex);
			assignCase(n, fromIndex, toIndex, V_MAX);
		}
		public int[] copyBefore() {
			int[] ret = new int[this.before.length];
			System.arraycopy(this.before, 0, ret, 0, ret.length);
			return ret;
		}
	}
	
	static sortIntTestCase[] intCase, smallIntCase;
	static sortCharTestCase[] charCase, smallCharCase;
	static final int testCaseN = 1000;

	static public String printArray(int a[]) {
		StringBuilder sb = new StringBuilder("{");
		for (int x : a) sb.append(String.format("%d, ", x));
		sb.append("}");
		return sb.toString();
	}
	
	static class sortCharTestCase {
		static Random random = new Random();
		char[] before, after;
		int fromIndex, toIndex;
		public static final int N_MAX = 10000;
		public static final int V_MAX = Character.MAX_VALUE;
		void assignCase(int n, int fromIndex, int toIndex, int v) {
			this.fromIndex = fromIndex; this.toIndex = toIndex;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < n; ++i) sb.append((char)random.nextInt(v+1));
			before = sb.toString().toCharArray();
			after = sb.toString().toCharArray();
			Arrays.sort(this.after, fromIndex, toIndex);
		}
		public sortCharTestCase(int n, int v) {
			int toIndex = 1+random.nextInt(n), fromIndex = random.nextInt(toIndex);
			assignCase(n, fromIndex, toIndex, v);
		}
		public sortCharTestCase(int n) {
			int toIndex = 1+random.nextInt(n), fromIndex = random.nextInt(toIndex);
			assignCase(n, fromIndex, toIndex, V_MAX);
		}
		public sortCharTestCase() {
			int n = 1 + random.nextInt(N_MAX);
			int toIndex = 1+random.nextInt(n), fromIndex = random.nextInt(toIndex);
			assignCase(n, fromIndex, toIndex, V_MAX);
		}
		public char[] copyBefore() {
			char[] ret = new char[this.before.length];
			System.arraycopy(this.before, 0, ret, 0, ret.length);
			return ret;
		}
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		intCase = new sortIntTestCase[testCaseN];
		intCase[0] = new sortIntTestCase(1);
		intCase[1] = new sortIntTestCase(sortIntTestCase.N_MAX);
		for (int i = 2; i < intCase.length; ++i) intCase[i] = new sortIntTestCase();
		
		charCase = new sortCharTestCase[testCaseN];
		charCase[0] = new sortCharTestCase(1);
		charCase[1] = new sortCharTestCase(sortCharTestCase.N_MAX);
		for (int i = 2; i < charCase.length; ++i) charCase[i] = new sortCharTestCase();
		
		Random r = new Random();
		smallCharCase = new sortCharTestCase[100];
		smallCharCase[0] = new sortCharTestCase(1, 1+r.nextInt(20));
		for (int i = 1; i < smallCharCase.length; ++i)
			smallCharCase[i] = new sortCharTestCase(1+r.nextInt(10), r.nextInt(20));
		
		smallIntCase = new sortIntTestCase[testCaseN];
		smallIntCase[0] = new sortIntTestCase(1);
		for (int i = 1; i < smallIntCase.length; ++i)
			smallIntCase[i] = new sortIntTestCase(1+r.nextInt(10));
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
	public void testBubbleSort() {
		for (sortIntTestCase c : intCase) {
			int[] target = c.copyBefore();
			assertTrue(Arrays.equals(c.before, target));
			SortingAlgorithm.bubbleSort(target, c.fromIndex, c.toIndex);
			assertTrue(String.format("fromIndex = %d, toIndex = %d\n before = %s\n after = %s\n target = %s\n", 
					c.fromIndex, c.toIndex, printArray(c.before), printArray(c.after), printArray(target)), 
					Arrays.equals(target, c.after));
		}
	}
	@Test
	public void testMergeCount() {
		for (sortIntTestCase c : intCase) {
			int[] target = c.copyBefore();
			assertTrue(Arrays.equals(c.before, target));
			int swapCount = SortingAlgorithm.mergeCount(target, c.fromIndex, c.toIndex);
			assertTrue(String.format("fromIndex = %d, toIndex = %d\n before = %s\n after = %s\n target = %s\n", 
					c.fromIndex, c.toIndex, printArray(c.before), printArray(c.after), printArray(target)), 
					Arrays.equals(target, c.after));
			target = c.copyBefore();
			assertEquals(swapCount, SortingAlgorithm.mergeCount(target, c.fromIndex, c.toIndex));
		}
	}
	@Test
	public void testQuickSort() {
		for (sortIntTestCase c : intCase) {
			int[] target = c.copyBefore();
			assertTrue(Arrays.equals(c.before, target));
			SortingAlgorithm.quickSort(target, c.fromIndex, c.toIndex);
			assertTrue(String.format("fromIndex = %d, toIndex = %d\n before = %s\n after = %s\n target = %s\n", 
					c.fromIndex, c.toIndex, printArray(c.before), printArray(c.after), printArray(target)), 
					Arrays.equals(target, c.after));
		}
	}
	@Test
	public void testOddEvenSort() {
		for (sortIntTestCase c : intCase) {
			int[] target = c.copyBefore();
			assertTrue(Arrays.equals(c.before, target));
			SortingAlgorithm.oddEvenSort(target, c.fromIndex, c.toIndex);
			assertTrue(String.format("fromIndex = %d, toIndex = %d\n before = %s\n after = %s\n target = %s\n", 
					c.fromIndex, c.toIndex, printArray(c.before), printArray(c.after), printArray(target)), 
					Arrays.equals(target, c.after));
		}
	}
	@Test
	public void testCombSort() {
		for (sortIntTestCase c : intCase) {
			int[] target = c.copyBefore();
			assertTrue(Arrays.equals(c.before, target));
			SortingAlgorithm.combSort(target, c.fromIndex, c.toIndex);
			assertTrue(String.format("fromIndex = %d, toIndex = %d\n before = %s\n after = %s\n target = %s\n", 
					c.fromIndex, c.toIndex, printArray(c.before), printArray(c.after), printArray(target)), 
					Arrays.equals(target, c.after));
		}
	}
	@Test
	public void testSelectionSortIntt() {
		for (sortIntTestCase c : intCase) {
			int[] target = c.copyBefore();
			assertTrue(Arrays.equals(c.before, target));
			SortingAlgorithm.selectionSort(target, c.fromIndex, c.toIndex);
			assertTrue(String.format("fromIndex = %d, toIndex = %d\n before = %s\n after = %s\n target = %s\n", 
					c.fromIndex, c.toIndex, printArray(c.before), printArray(c.after), printArray(target)), 
					Arrays.equals(target, c.after));
		}
	}
	@Test
	public void testSleepSort() {
		for (sortCharTestCase c : smallCharCase) {
			char[] target = c.copyBefore();
			assertTrue(Arrays.equals(c.before, target));
			SortingAlgorithm.sleepSort(target, c.fromIndex, c.toIndex);
			assertTrue(String.format("fromIndex = %d, toIndex = %d\n before = %s\n after = %s\n target = %s\n", 
					c.fromIndex, c.toIndex, new String(c.before), new String(c.after), new String(target)), 
					Arrays.equals(target, c.after));
		}
	}
	@Test
	public void testCountingSortChar() {
		for (sortCharTestCase c : charCase) {
			char[] target = c.copyBefore();
			assertTrue(Arrays.equals(c.before, target));
			SortingAlgorithm.countingSort(target, c.fromIndex, c.toIndex);
			assertTrue(String.format("fromIndex = %d, toIndex = %d\n before = %s\n after = %s\n target = %s\n", 
					c.fromIndex, c.toIndex, new String(c.before), new String(c.after), new String(target)), 
					Arrays.equals(target, c.after));
		}
	}
	@Test
	public void testHeapSort() {
		for (sortIntTestCase c : intCase) {
			int[] target = c.copyBefore();
			assertTrue(Arrays.equals(c.before, target));
			SortingAlgorithm.heapSort(target, c.fromIndex, c.toIndex);
			assertTrue(String.format("fromIndex = %d, toIndex = %d\n before = %s\n after = %s\n target = %s\n", 
					c.fromIndex, c.toIndex, printArray(c.before), printArray(c.after), printArray(target)), 
					Arrays.equals(target, c.after));
		}
	}
	@Test
	public void testBogoSort() {
		for (sortIntTestCase c : smallIntCase) {
			int[] target = c.copyBefore();
			assertTrue(Arrays.equals(c.before, target));
			SortingAlgorithm.bogoSort(target, c.fromIndex, c.toIndex);
			assertTrue(String.format("fromIndex = %d, toIndex = %d\n before = %s\n after = %s\n target = %s\n", 
					c.fromIndex, c.toIndex, printArray(c.before), printArray(c.after), printArray(target)), 
					Arrays.equals(target, c.after));
		}
	}
}
