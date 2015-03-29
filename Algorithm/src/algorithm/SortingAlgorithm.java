package algorithm;
import static algorithm.Utility.swap;
import java.util.*;
public class SortingAlgorithm {
	private SortingAlgorithm(){}

	/**
	 * Sort specified range [fromIndex, toIndex) of array into accending order via bubble sort. <br>
	 * (O(n^2), n:= toIndex-fromIndex)
	 * @param a the array to be sorted
	 * @param fromIndex the index of the first element, inclusive, to be sorted
	 * @param toIndex the index of the last element, exclusive, to be sorted
	 * @return swap count
	 */
	public static final int bubbleSort(int a[], int fromIndex, int toIndex){
		int swapCount = 0;
		int n = toIndex - fromIndex;
		for (int i = 0; i < n; ++i)
			for (int j = fromIndex+1; j < toIndex-i; ++j)
				if (a[j-1] > a[j]) { swap(a, j-1, j); ++swapCount; }
		return swapCount;
	}
	/** AOJ No. 0167(partial modification)*/
	public static final int bubbleSort(int a[]){ return bubbleSort(a, 0, a.length); }

	/**
	 * Sorts the specified range of the array into ascending order.
     * And counts the number of swaps in bubble sort. (O(n log n), n:= toIndex-fromIndex)
     * The range to be sorted extends from the index {@code fromIndex}, inclusive, to
     * the index {@code toIndex}, exclusive. If {@code fromIndex == toIndex},
     * the range to be sorted is empty.
     * @param a the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex the index of the last element, exclusive, to be sorted
     * @return number of swaps
     * @throws IllegalArgumentException if {@code fromIndex > toIndex}
     * @throws ArrayIndexOutOfBoundsException
     *     if {@code fromIndex < 0} or {@code toIndex > a.length}
     */
	public static int mergeCount(int[] a, int fromIndex, int toIndex){
		int count = 0;
		int n = toIndex - fromIndex;
		if(n > 1){
			int mid = fromIndex + n/2;
			count += mergeCount(a, fromIndex, mid); count += mergeCount(a, mid, toIndex);
			int[] temp = new int[n]; System.arraycopy(a, fromIndex, temp, 0, n);
			//merge index i-> x, j-> left subsequence of x, k-> right subsequence of x
			for(int i = fromIndex, j = 0, k = n/2; i < toIndex; ++i){
				if(j == n/2)				a[i] = temp[k++];
				else if(k == n)				a[i] = temp[j++];
				else if(temp[j] <= temp[k])	a[i] = temp[j++];
				else						{a[i] = temp[k++]; count += n/2 - j; }
			}
		}
		return count;
	}
	/**
     * Sorts the specified array into ascending order.
     * Counts the number of swaps in bubble sort. (O(n log n), n:= a.length)<br>
     * AOJ No. 0167
     * @param a the array to be sorted
     * @return number of swaps
     */
	public static final int mergeCount(int[] a){ return mergeCount(a, 0, a.length); }

	/**
	 * Sort specified range [fromIndex, toIndex) of array into accending order via quick sort. <br>
	 * (O(n log n), n:= toIndex-fromIndex)
	 * @param a the array to be sorted
	 * @param fromIndex the index of the first element, inclusive, to be sorted
	 * @param toIndex the index of the last element, exclusive, to be sorted
	 */
	public static final void quickSort(int a[], int fromIndex, int toIndex) {
		if (fromIndex < toIndex) {
			int p = a[(fromIndex + toIndex) / 2];
			int l = fromIndex - 1, r = toIndex;
			while (true) {
				while (a[++l] < p);
				while (a[--r] > p);
				if (l >= r) break;
				swap(a, l, r);
			}
			quickSort(a, fromIndex, l);
			quickSort(a, r + 1, toIndex);
		}
	}
	/**
	 * Sort specified array into accending order via quick sort. <br>
	 * (O(n log n), n:= a.length) <br>
	 * AOJ No. 0025, 0182
	 * @param a the array to be sorted
	 */
	public static final void quickSort(int a[]) { quickSort(a, 0, a.length); }

	/**
	 * Sort specified range [fromIndex, toIndex) of array into accending order via odd-even sort. <br>
	 * (O(n^2), n:= toIndex-fromIndex)
	 * @param a the array to be sorted
	 * @param fromIndex the index of the first element, inclusive, to be sorted
	 * @param toIndex the index of the last element, exclusive, to be sorted
	 */
	public static final void oddEvenSort(int a[], int fromIndex, int toIndex) {
		boolean swapped = true;
		while (swapped) {
			swapped = false;
			for (int i = fromIndex; i < toIndex-1; i+=2) //odd-even
				if (a[i] > a[i+1]) { swap(a, i, i+1); swapped = true; }
			for (int i = fromIndex+1; i < toIndex-1; i+=2) //even-odd
				if (a[i] > a[i+1]) { swap(a, i, i+1); swapped = true; }
		}
	}
	/**
	 * Sort specified array into accending order via odd-even sort. <br>
	 * (O(n^2), n:= a.length) <br>
	 * @param a the array to be sorted
	 */
	public static final void oddEvenSort(int a[]) { oddEvenSort(a, 0, a.length); }

	/**
	 * Sort specified range [fromIndex, toIndex) of array into accending order via comb sort. <br>
	 * (O(n log n), n:= toIndex-fromIndex)
	 * @param a the array to be sorted
	 * @param fromIndex the index of the first element, inclusive, to be sorted
	 * @param toIndex the index of the last element, exclusive, to be sorted
	 */
	public static final void combSort(int a[], int fromIndex, int toIndex) {
		boolean swapped = true;
		int h = fromIndex - toIndex;
		while (h > 1 || swapped) {
			swapped = false;
			h = Math.max(h*10/13, 1);
			for (int i = fromIndex;i+h < toIndex;++i)
				if (a[i] > a[i+h]) { swap(a, i, i+h); swapped = true; }
		}
	}
	/**
	 * Sort specified array into accending order via comb sort. <br>
	 * (O(n log n), n:= a.length) <br>
	 * @param a the array to be sorted
	 */
	public static final void combSort(int a[]) { combSort(a, 0, a.length); }

	/**
	 * Sort specified range [fromIndex, toIndex) of array into accending order via selection sort. <br>
	 * (O(n^2), n:= toIndex-fromIndex)
	 * @param a the array to be sorted
	 * @param fromIndex the index of the first element, inclusive, to be sorted
	 * @param toIndex the index of the last element, exclusive, to be sorted
	 */
	public static final void selectionSort(int a[], int fromIndex, int toIndex) {
		for (int i = fromIndex; i < toIndex; ++i) {
			int minIndex = i;
			for (int j = i+1; j < toIndex; ++j)
				if (a[minIndex] > a[j]) minIndex = j;
			swap(a, i, minIndex);
		}
	}
	/**
	 * Sort specified array into accending order via selection sort. <br>
	 * (O(n^2), n:= a.length) <br>
	 * @param a the array to be sorted
	 */
	public static final void selectionSort(int a[]) { selectionSort(a, 0, a.length); }

	/**
	 * Sort specified range [fromIndex, toIndex) of array into accending order via counting sort. <br>
	 * (O(n+k), n:= toIndex-fromIndex, k = Character.MAX_VALUE)
	 * @param a the array to be sorted(element must be [0, k])
	 * @param fromIndex the index of the first element, inclusive, to be sorted
	 * @param toIndex the index of the last element, exclusive, to be sorted
	 */
	public static final void countingSort(char a[], int fromIndex, int toIndex) {
		int[] freq = new int[Character.MAX_VALUE+1];
		int n = toIndex - fromIndex;
		char tmp[] = new char[n]; System.arraycopy(a, fromIndex, tmp, 0, n);
		for (int i = 0; i < n; ++i) freq[tmp[i]]++;
		for (int k = 1; k < freq.length; ++k) freq[k] += freq[k-1];
		for (int i = n-1; i >= 0; --i) a[fromIndex + (--freq[tmp[i]])] = tmp[i];
	}
	/**
	 * Sort specified array into accending order via counting sort. <br>
	 * (O(n+k), n:= a.length, k = Character.MAX_VALUE) <br>
	 * @param a the array to be sorted(element must be [0, k])
	 */
	public static final void countingSort(char a[]) { countingSort(a, 0, a.length); }

	private static final int parentIndexOf(int childIndex, int rootIndex) { return rootIndex + (childIndex-rootIndex-1)/2; }
	private static final int leftChildIndexOf(int parentIndex, int rootIndex) { return rootIndex + 2*(parentIndex-rootIndex)+1; }
	private static final int rightChildIndexOf(int parentIndex, int rootIndex) { return rootIndex + 2*(parentIndex-rootIndex)+2; }
	/**
	 * Heapify sub tree of a[fromIndex]. In sub tree, a[fromIndex] will be max value.<br>
	 * O(log n), n:=toIndex-fromIndex
	 * @param a the array to be heapified. left and right sub tree of a[fromIndex] needs to be heapified.
	 * @param fromIndex the index of root element of sub tree.
	 * @param toIndex the index of the last element, exclusive.
	 * @param rootIndex the index of root, inclusive.
	 */
	private static final void heapify(int a[], int fromIndex, int toIndex, int rootIndex) {
		int left = leftChildIndexOf(fromIndex, rootIndex), right = rightChildIndexOf(fromIndex, rootIndex);
		int largest = (left < toIndex && a[left] > a[fromIndex]) ? left : fromIndex;
		if (right < toIndex && a[right] > a[largest]) largest = right;
		if (largest != fromIndex) {
			swap(a, fromIndex, largest);
			heapify(a, largest, toIndex, rootIndex);
		}
	}
	/**
	 * Sort specified range [fromIndex, toIndex) of array into accending order via heap sort. <br>
	 * (O(n log n), n:= toIndex-fromIndex)
	 * @param a the array to be sorted
	 * @param fromIndex the index of the first element, inclusive, to be sorted
	 * @param toIndex the index of the last element, exclusive, to be sorted
	 */
	public static final void heapSort(int a[], int fromIndex, int toIndex) {
		for (int i = parentIndexOf(toIndex-1, fromIndex); i >= fromIndex; --i) heapify(a, i, toIndex, fromIndex); //build heap
		for (int i = toIndex-1; i >= fromIndex+1; --i) {
			swap(a, fromIndex, i);
			heapify(a, fromIndex, i, fromIndex);
		}
	}
	/**
	 * Sort specified array into accending order via heap sort. <br>
	 * (O(n log n), n:= a.length) <br>
	 * @param a the array to be sorted
	 */
	public static final void heapSort(int a[]) { heapSort(a, 0, a.length); }

	/**
	 * a[fromIndex, toIndex)をsleep sort(笑)でソート
	 * @param a
	 * @param fromIndex
	 * @param toIndex
	 */
	public static final void sleepSort(char a[], int fromIndex, int toIndex) {
		final int n = toIndex - fromIndex;
		final List<Character> sorted = Collections.synchronizedList(new ArrayList<Character>(n*2));
		Thread t[] = new Thread[n];
		for (int i = 0; i < n; ++i) {
			final Character item = a[fromIndex+i];
			t[i] = new Thread() {
				final Character element = item;
				final long time = item*10; //差が小さすぎると順序が逆転してしまうので
				public void run() {
					try {
						Thread.sleep(time);
						sorted.add(element);
					} catch (InterruptedException e) {}
				}
			};
		}
		//start timer
		for (int i = 0; i < t.length; ++i) t[i].start();
		//wait
		try {
			for (int i = 0; i < t.length; ++i) t[i].join();
		} catch (InterruptedException e) {}
		//copy
		for (int i = 0; i < t.length; ++i) a[fromIndex+i] = sorted.get(i);
	}
	public static final void sleepSort(char a[]) { sleepSort(a, 0, a.length); }

	private static final boolean isSorted(int a[], int fromIndex, int toIndex) {
		for (int i = fromIndex+1; i < toIndex; ++i) if (a[i-1] > a[i]) return false;
		return true;
	}

	/**
	 * sort a[fromIndex, toIndex) via bogoSort
	 * @param a
	 * @param fromIndex
	 * @param toIndex
	 */
	public static final void bogoSort(int a[], int fromIndex, int toIndex) {
		int n = toIndex - fromIndex;
		Random r = new Random();
		while (isSorted(a, fromIndex, toIndex) == false)
			swap(a, fromIndex+r.nextInt(n), fromIndex+r.nextInt(n));
	}
	/**
	 * sort a via bogoSort
	 * @param a
	 */
	public static final void bogoSort(int a[]) { bogoSort(a, 0, a.length); }

	public static void main(String[] args) {
		int[] before = {5,3,5,2,1,7,4,9,-1,4,5,7,432,-32,43/*,5,5,-1*/};
		int[] after = new int[before.length];
		System.arraycopy(before, 0, after, 0, before.length);
		bogoSort(after, 0, after.length);
		for (int x: after) System.out.print(x+" ");
		System.out.println("\n" + (int)(Character.MIN_VALUE));

/*
		String source = "accbbhfy6rtFSDF#4234FSliyfjfvfhFDSAb";
		char[] B = source.toCharArray();
		sleepSort(B, 6, 14);
		System.out.println(new String(B));
	*/
		/*int before[] = {945, 765, -897, 187, -200, 338, -832, -471, 481, -121};
		int after[] = {945, 765, -897, 187, -832, -471, -200, 338, 481, -121};
		combSort(before, 4, 9);
		if (Arrays.equals(before, after) == false) {
			System.out.print("{");
			for (int x : before) System.out.print(x + ", ");
			System.out.println("}");
		}*/
	}

}
