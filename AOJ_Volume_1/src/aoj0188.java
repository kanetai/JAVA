import java.util.*;
public class aoj0188 {
	static final Scanner stdin = new Scanner(System.in);
	static int ans;
	public static void main(String[] args) {
		int n;
		while((n=stdin.nextInt()) != 0){
			Integer[] x = new Integer[n];
			for(int i = 0; i < n; ++i) x[i] = stdin.nextInt();
			Arrays.sort(x);
			ans = 0;
			_binarySearch(x, stdin.nextInt());
			System.out.println(ans);
		}
	}
	public static <T> int _binarySearch(T[] a, int fromIndex, int toIndex, T key, Comparator<? super T> c){
		int lb = fromIndex, ub = toIndex-1;
		while( lb <= ub ){
			ans++;
			int mid = (lb + ub) / 2, cmp = c.compare(a[mid], key);
			if( cmp == 0 ) return mid;		// exit success
			if( cmp < 0 )	lb = mid + 1;
			else 			ub = mid-1; 	// x[mid] > key
		}
		return -1; 							//exit failure
	}
	public static <T extends Comparable<? super T>> int _binarySearch(T[] a, T key){
		return _binarySearch(a, 0, a.length, key, new Comparator<T>(){
			@Override public int compare(T o1, T o2) { return o1.compareTo(o2); }
		});
	}
}
