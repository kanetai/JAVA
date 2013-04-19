import java.util.*;
public class aoj0044 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N_MAX = 500001;
	static final boolean[] table = sieve(N_MAX);
	public static void main(String[] args) {
		List<Integer> l = new ArrayList<Integer>(N_MAX);
		for(int i = 0; i < N_MAX; ++i) if(table[i]) l.add(i);
		Integer[] primeArray = (Integer[]) l.toArray(new Integer[l.size()]);
		while(stdin.hasNext()){
			int n = stdin.nextInt();
			int a = lowerBound(primeArray, n-1), b = lowerBound(primeArray, n+1);
			System.out.println(
					(primeArray[a] >= n ? primeArray[a-1] : primeArray[a]) + " "
							+ (primeArray[b] <= n ? primeArray[b+1] : primeArray[b])
					);

		}
	}
	public static final boolean[] sieve(int n){
		boolean[] ret = new boolean[n]; Arrays.fill(ret, true);
		ret[0] = ret[1] = false;
		for(int i = 2; i*i < n; ++i)
			if(ret[i]) for(int j = i*i; j < n; j+=i) ret[j] = false;
		return ret;
	}
	public static <T> int lowerBound(T[] a, int fromIndex, int toIndex, T key, Comparator<? super T> c){
		int lb = fromIndex - 1, ub = toIndex;
		while(ub - lb > 1){ 			//rage of solution > 1
			int mid = (lb + ub) / 2, cmp = c.compare(a[mid], key);
			if( cmp >= 0 )	ub = mid;	//(lb,mid]
			else			lb = mid;	//(mid,ub]
		}
		return ub; 						//(lb, ub], ub = lb + 1 → [ub, ub+1)
	}
	public static <T extends Comparable<? super T>> int lowerBound(T[] a, T key){
		return lowerBound(a, 0, a.length, key, new Comparator<T>(){
			@Override public int compare(T o1, T o2) { return o1.compareTo(o2); }
		});
	}
}
