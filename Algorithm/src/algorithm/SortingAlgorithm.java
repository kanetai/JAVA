package algorithm;
import static algorithm.Utility.swap;
public class SortingAlgorithm {
	private SortingAlgorithm(){}
	public static final void bubbleSort(int a[], int fromIndex, int toIndex){
		int n = toIndex - fromIndex;
		for (int i = fromIndex+1; i < n; ++i) 
			for (int j = fromIndex; j < n-i; j++) 
				if(a[j] > a[j+1]) swap(a, j, j+1);
	}
	/** AOJ No. 0167(partial modification)*/
	public static final void bubbleSort(int a[]){ bubbleSort(a, 0, a.length); }
	
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
}
