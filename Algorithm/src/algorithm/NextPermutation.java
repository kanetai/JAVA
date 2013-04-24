package algorithm;
import java.util.Comparator;
import static algorithm.Utility.swap;
import static algorithm.Utility.reverse;
/**
 * <a href="http://www.cplusplus.com/reference/algorithm/next_permutation/">std::next_permutation C++ reference</a>
 */
public final class NextPermutation {
	private NextPermutation(){}
	/**
	 * Calculates the std::next_permutation(a+fromImdex, a+toIndex, c) in C++.<br>
	 * n = toIndex - fromIndex (0 <= fromIndex < toIndex <= a.length)
	 * @param a			array
	 * @param fromIndex
	 * @param toIndex
	 * @param c			comparator
	 * @return			std::next_permutation(a+fromImdex, a+toIndex, c)
	 */
	public static <T> boolean nextPermutation(T[] a, int fromIndex, int toIndex, final Comparator<? super T> c){
		for (int i = toIndex - 1; i > fromIndex; --i) {
			if (c.compare(a[i-1], a[i]) < 0) { //[i,toIndex) is arranged in descending order.
				int swapIndex = toIndex;
				while (c.compare(a[i-1], a[--swapIndex]) >= 0);
				//int swapIndex = lowerBound(a, i, toIndex, a[i - 1], new Comparator<T>(){ @Override public int compare(T o1, T o2) { return -(c.compare(o1, o2)); }}) - 1;
				swap(a, i-1, swapIndex);
				reverse(a, i, toIndex);
				return true;
			}
		}
		reverse(a, fromIndex, toIndex); //turn back
		return false;
	}
	/**
	 * Calculates the std::next_permutation(a, a+n, c) in C++. (n = a.length)
	 * @param a	array
	 * @param c	comparator
	 * @return	std::next_permutation(a, c)
	 */
	public static <T> boolean nextPermutation(T[] a, Comparator<? super T> c){
		return nextPermutation(a, 0, a.length, c);
	}
	/**
	 * Calculates the std::next_permutation(a+fromImdex, a+toIndex) in C++.<br>
	 * n = toIndex - fromIndex (0 <= fromIndex < toIndex <= a.length), O(log n)
	 * @param a			array
	 * @param fromIndex
	 * @param toIndex
	 * @param key
	 * @return			std::next_permutation(a+fromImdex, a+toIndex)
	 */
	public static <T extends Comparable <? super T>> boolean nextPermutation(T[] a, int fromIndex, int toIndex){
		return nextPermutation(a, fromIndex, toIndex, new Comparator<T>(){
			@Override public int compare(T o1, T o2) { return o1.compareTo(o2); }
		});
	}
	/**
	 * Calculates the std::next_permutation(a, a+n) in C++. (n = a.length)<br>
	 * AOJ No. 0041
	 * @param a		array
	 * @param key
	 * @return		std::next_permutation(a, a+n)
	 */
	public static <T extends Comparable <? super T>> boolean nextPermutation(T[] a){
		return nextPermutation(a, 0, a.length, new Comparator<T>(){
			@Override public int compare(T o1, T o2) { return o1.compareTo(o2); }
		});
	}
}
