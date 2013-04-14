import java.util.*;
public class aoj0034 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(stdin.hasNext()){
			String s[] = stdin.nextLine().split(",");
			Double[] x = new Double[s.length-2];
			x[0] = (double)Integer.parseInt(s[0]);
			for(int i=1; i<x.length; ++i)
				x[i] = x[i-1] + Integer.parseInt(s[i]);
			int v1 = Integer.parseInt( s[s.length-2] );
			int v2 = Integer.parseInt( s[s.length-1] );
			System.out.println( solve(x,v1,v2) );
		}
	}
	static int solve(Double x[], int v1, int v2){
		return lowerBound(x, x[x.length-1]*v1/(v1+v2) )+1;
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
