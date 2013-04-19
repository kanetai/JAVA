package algorithm;
import java.util.Comparator;
/**
 * <a href="http://www.cplusplus.com/reference/algorithm/binary_search/">std::binary_search C++ reference</a><br>
 * <a href="http://www.cplusplus.com/reference/algorithm/lower_bound/">std::lower_bound C++ reference</a><br>
 * <a href="http://www.cplusplus.com/reference/algorithm/upper_bound/">std::upper_bound C++ reference</a><br>
 */
public class BinarySearch extends Utility{
	//TODO: test _binarySearch, lowerBound, upperBound, keyCount
	/***** _binarySearch *****/

	/** 
	 * Searches a range[fromIndex, toIndex) of the specified array for the key using the binary search algorithm.
	 * The range must be sorted into ascending order according to the specified comparator prior to making this call.
	 * If it is not sorted, the results are undefined.
	 * If the range contains multiple elements equal to the specified object, 
	 * there is no guarantee which one will be found.<br>
	 * n = toIndex - fromIndex (0 <= fromIndex < toIndex <= a.length), O(log n)<br>
	 * ※This method is sample.
	 * @param a			array
	 * @param fromIndex	(inclusive)
	 * @param toIndex	(exclusive)
	 * @param key
	 * @param c			comparator
	 * @return			index of key (-1 → not found)
	 */
	public static <T> int _binarySearch(T[] a, int fromIndex, int toIndex, T key, Comparator<? super T> c){
		int lb = fromIndex, ub = toIndex-1;
		while( lb <= ub ){
			int mid = (lb + ub) / 2, cmp = c.compare(a[mid], key);
			if( cmp == 0 ) return mid;		// exit success
			if( cmp < 0 )	lb = mid + 1;
			else 			ub = mid-1; 	// x[mid] > key
		}
		return -1; 							//exit failure
	}
	/**
	 * Searches the specified array for the key using the binary search algorithm.
	 * The array must be sorted into ascending order according to the specified comparator prior to making this call.
	 * If it is not sorted, the results are undefined.
	 * If the array contains multiple elements equal to the specified object, 
	 * there is no guarantee which one will be found.<br>
	 * n = a.length, O(log n)<br>
	 * ※This method is sample.
	 * @param a		array
	 * @param key
	 * @param c		comparator
	 * @return		index of key (-1 → not found)
	 */
	public static <T> int _binarySearch(T[] a, T key, Comparator<? super T> c){
		return _binarySearch(a, 0, a.length, key, c);
	}
	/** 
	 * Searches a range[fromIndex, toIndex) of the specified array for the key using the binary search algorithm.
	 * The range must be sorted into ascending order prior to making this call.
	 * If it is not sorted, the results are undefined.
	 * If the range contains multiple elements equal to the specified object, 
	 * there is no guarantee which one will be found.<br>
	 * n = toIndex - fromIndex (0 <= fromIndex < toIndex <= a.length), O(log n)<br>
	 * ※This method is sample.
	 * @param a			array
	 * @param fromIndex	(inclusive)
	 * @param toIndex	(exclusive)
	 * @param key
	 * @return			index of key (-1 → not found)
	 */
	public static <T extends Comparable <? super T>> int _binarySearch(T[] a, int fromIndex, int toIndex, T key){
		return _binarySearch(a, fromIndex, toIndex, key, new Comparator<T>(){
			@Override public int compare(T o1, T o2) { return o1.compareTo(o2); }
		});
	}
	/**
	 * Searches the specified array for the key using the binary search algorithm.
	 * The array must be sorted into ascending order prior to making this call.
	 * If it is not sorted, the results are undefined.
	 * If the array contains multiple elements equal to the specified object, 
	 * there is no guarantee which one will be found.<br>
	 * n = a.length, O(log n)<br>
	 * ※This method is sample.
	 * @param a		array
	 * @param key
	 * @return		index of key (-1 → not found)
	 */
	public static <T extends Comparable<? super T>> int _binarySearch(T[] a, T key){
		return _binarySearch(a, 0, a.length, key, new Comparator<T>(){
			@Override public int compare(T o1, T o2) { return o1.compareTo(o2); }
		});
	}

	/***** lowerBound *****/

	/**
	 * Calculates the std::lower_bound(a+fromImdex, a+toIndex, key, c)-a in C++.
	 * The range[fromIndex, toIndex) must be sorted into ascending order 
	 * according to the specified comparator prior to making this call.
	 * If it is not sorted, the results are undefined.<br>
	 * n = toIndex - fromIndex (0 <= fromIndex < toIndex <= a.length), O(log n)
	 * @param a			array
	 * @param fromIndex
	 * @param toIndex
	 * @param key
	 * @param c			comparator
	 * @return			std::lower_bound(a+fromImdex, a+toIndex, key, c)-a
	 */
	public static <T> int lowerBound(T[] a, int fromIndex, int toIndex, T key, Comparator<? super T> c){
		int lb = fromIndex - 1, ub = toIndex;
		while(ub - lb > 1){ 			//rage of solution > 1
			int mid = (lb + ub) / 2, cmp = c.compare(a[mid], key);
			if( cmp >= 0 )	ub = mid;	//(lb,mid]
			else			lb = mid;	//(mid,ub]
		}
		return ub; 						//(lb, ub], ub = lb + 1 → [ub, ub+1)
	}
	/**
	 * Calculates the std::lower_bound(a, a+n, key, c)-a in C++.
	 * The array must be sorted into ascending order 
	 * according to the specified comparator prior to making this call.
	 * If it is not sorted, the results are undefined.<br>
	 * n = a.length, O(log n)<br>
	 * @param a			array
	 * @param key
	 * @param c			comparator
	 * @return			std::lower_bound(a, key, c)-a
	 */
	public static <T> int lowerBound(T[] a, T key, Comparator<? super T> c){
		return lowerBound(a, 0, a.length, key, c);
	}
	/**
	 * Calculates the std::lower_bound(a+fromImdex, a+toIndex, key)-a in C++.
	 * The range[fromIndex, toIndex) must be sorted into ascending order prior to making this call.
	 * If it is not sorted, the results are undefined.<br>
	 * n = toIndex - fromIndex (0 <= fromIndex < toIndex <= a.length), O(log n)
	 * @param a			array
	 * @param fromIndex
	 * @param toIndex
	 * @param key
	 * @return			std::lower_bound(a+fromImdex, a+toIndex, key)-a
	 */
	public static <T extends Comparable <? super T>> int lowerBound(T[] a, int fromIndex, int toIndex, T key){
		return lowerBound(a, fromIndex, toIndex, key, new Comparator<T>(){
			@Override public int compare(T o1, T o2) { return o1.compareTo(o2); }
		});
	}
	/**
	 * Calculates the std::lower_bound(a, a+n, key)-a in C++.
	 * The array must be sorted into ascending order prior to making this call.
	 * If it is not sorted, the results are undefined.<br>
	 * n = a.length, O(log n)<br>
	 * AOJ No. 0034
	 * @param a			array
	 * @param key
	 * @return			std::lower_bound(a, key)-a
	 */
	public static <T extends Comparable<? super T>> int lowerBound(T[] a, T key){
		return lowerBound(a, 0, a.length, key, new Comparator<T>(){
			@Override public int compare(T o1, T o2) { return o1.compareTo(o2); }
		});
	}

	/***** upperBound *****/

	/**
	 * Calculates the std::upper_bound(a+fromImdex, a+toIndex, key, c)-a in C++.
	 * The range[fromIndex, toIndex) must be sorted into ascending order 
	 * according to the specified comparator prior to making this call.
	 * If it is not sorted, the results are undefined.<br>
	 * n = toIndex - fromIndex (0 <= fromIndex < toIndex <= a.length), O(log n)
	 * @param a			array
	 * @param fromIndex
	 * @param toIndex
	 * @param key
	 * @param c			comparator
	 * @return			std::upper_bound(a+fromImdex, a+toIndex, key, c)-a
	 */
	public static <T> int upperBound(T[] a, int fromIndex, int toIndex, T key, Comparator<? super T> c){
		int lb = fromIndex - 1, ub = toIndex;
		while(ub - lb > 1){				//range of solution > 1
			int mid =(lb + ub) / 2, cmp = c.compare(a[mid], key);
			if( cmp <= 0 )	lb = mid;	//[mid,ub)
			else			ub = mid;	//[lb,mid)
		}
		return ub; 						//ub = lb + 1, [lb, ub)
	}
	/**
	 * Calculates the std::upper_bound(a, a+n, key, c)-a in C++.
	 * The array must be sorted into ascending order 
	 * according to the specified comparator prior to making this call.
	 * If it is not sorted, the results are undefined.<br>
	 * n = a.length, O(log n)<br>
	 * @param a			array
	 * @param key
	 * @param c			comparator
	 * @return			std::upper_bound(a, key, c)-a
	 */
	public static <T> int upperBound(T[] a, T key, Comparator<? super T> c){
		return upperBound(a, 0, a.length, key, c);
	}
	/**
	 * Calculates the std::upper_bound(a+fromImdex, a+toIndex, key)-a in C++.
	 * The range[fromIndex, toIndex) must be sorted into ascending order prior to making this call.
	 * If it is not sorted, the results are undefined.<br>
	 * n = toIndex - fromIndex (0 <= fromIndex < toIndex <= a.length), O(log n)
	 * @param a			array
	 * @param fromIndex
	 * @param toIndex
	 * @param key
	 * @return			std::upper_bound(a+fromImdex, a+toIndex, key)-a
	 */
	public static <T extends Comparable <? super T>> int upperBound(T[] a, int fromIndex, int toIndex, T key){
		return upperBound(a, fromIndex, toIndex, key, new Comparator<T>(){
			@Override public int compare(T o1, T o2) { return o1.compareTo(o2); }
		});
	}
	/**
	 * Calculates the std::upper_bound(a, a+n, key)-a in C++.
	 * The array must be sorted into ascending order prior to making this call.
	 * If it is not sorted, the results are undefined.<br>
	 * n = a.length, O(log n)<br>
	 * @param a			array
	 * @param key
	 * @return			std::upper_bound(a, key)-a
	 */
	public static <T extends Comparable<? super T>> int upperBound(T[] a, T key){
		return upperBound(a, 0, a.length, key, new Comparator<T>(){
			@Override public int compare(T o1, T o2) { return o1.compareTo(o2); }
		});
	}

	/***** keyCount *****/

	/**
	 * Counts the numbers of key Objects in specified array[fromIndex, toIndex).
	 * The range[fromIndex, toIndex) must be sorted into ascending order 
	 * according to the specified comparator prior to making this call.
	 * If it is not sorted, the results are undefined.<br>
	 * n = toIndex - fromIndex (0 <= fromIndex < toIndex <= a.length), O(log n)
	 * @param a			array
	 * @param fromIndex
	 * @param toIndex
	 * @param key
	 * @param c			comparator
	 * @return			the number of key Objects
	 */
	public static <T> int keyCount(T[] a, int fromIndex, int toIndex, T key, Comparator<? super T> c){
		return upperBound(a, fromIndex, toIndex, key, c) - lowerBound(a, fromIndex, toIndex, key, c);
	}
	/**
	 * Counts the number of key Objects in specified array.
	 * The array must be sorted into ascending order 
	 * according to the specified comparator prior to making this call.
	 * If it is not sorted, the results are undefined.<br>
	 * n = a.length, O(log n)<br>
	 * @param a			array
	 * @param key
	 * @param c			comparator
	 * @return			the number of key Object
	 */
	public static <T> int keyCount(T[] a, T key, Comparator<? super T> c){
		return keyCount(a, 0, a.length, key, c);
	}
	/**
	 * Counts the number of key Objects in specified array[fromIndex, toIndex).
	 * The range[fromIndex, toIndex) must be sorted into ascending order prior to making this call.
	 * If it is not sorted, the results are undefined.<br>
	 * n = toIndex - fromIndex (0 <= fromIndex < toIndex <= a.length), O(log n)
	 * @param a			array
	 * @param fromIndex
	 * @param toIndex
	 * @param key
	 * @return			the number of key Objects
	 */
	public static <T extends Comparable <? super T>> int keyCount(T[] a, int fromIndex, int toIndex, T key){
		return keyCount(a, fromIndex, toIndex, key, new Comparator<T>(){
			@Override public int compare(T o1, T o2) { return o1.compareTo(o2); }
		});
	}
	/**
	 * Counts the number of key Objects in specified array.
	 * The array must be sorted into ascending order prior to making this call.
	 * If it is not sorted, the results are undefined.<br>
	 * n = a.length, O(log n)<br>
	 * @param a			array
	 * @param key
	 * @return			the number of key Objects
	 */
	public static <T extends Comparable<? super T>> int keyCount(T[] a, T key){
		return keyCount(a, 0, a.length, key, new Comparator<T>(){
			@Override public int compare(T o1, T o2) { return o1.compareTo(o2); }
		});
	}
}
