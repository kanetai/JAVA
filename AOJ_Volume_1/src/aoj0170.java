import java.util.*;
public class aoj0170 {
	static final double INF = 1e10;
	static final Scanner stdin = new Scanner(System.in);
	static int[] w, s;
	static Solver solver = Solver.DFS;
	public static final double centerOfGravity(Integer[] idx){
		int n = idx.length, numerator = n*w[idx[n-1]];
		int denominator = w[idx[n-1]];
		for(int i = n-2; i >= 0; --i){
			if(s[idx[i]] < denominator) return INF;
			denominator += w[idx[i]];
			numerator += (i+1)*w[idx[i]];	
		}
		return ((double)numerator)/denominator;
	}
	public static void main(String[] args) {
		int n;
		while((n = stdin.nextInt()) != 0){
			String[] name = new String[n];
			w = new int[n]; s = new int[n];
			Integer[] idx = new Integer[n];
			for(int i = 0; i < n; ++i){
				name[i] = stdin.next(); w[i] = stdin.nextInt(); s[i] = stdin.nextInt();
				idx[i] = i;
			}
			for(int i: solver.solve(idx)) System.out.println(name[i]);
		}
	}
	static enum Solver {
		NextPermutation { @Override Integer[] solve(Integer[] idx){
			int n = idx.length;
			Integer[] ans = new Integer[n];
			double g = INF;
			do{
				double tempg = centerOfGravity(idx);
				if(g > tempg){ System.arraycopy(idx, 0, ans, 0, n); g = tempg; }
			}while(nextPermutation(idx));
			return ans;
		}}, DFS { @Override Integer[] solve(Integer[] idx){
			int n = idx.length;
			answer = new Integer[n]; tempa = new Integer[n]; used = new boolean[n]; g = INF; Arrays.fill(used, false);
			DFS(0, 0, 0);
			return answer;
		}};
		Integer[] solve(Integer[] idx){ return new Integer[]{}; }
	}
	static Integer[] answer, tempa; static boolean[] used; static double g;
	public static void DFS(int d, int numerator, int denominator){
		int n = answer.length;
		if(d == n){
			double tempg = (double)numerator/denominator;
			if(g > tempg){ g = tempg; System.arraycopy(tempa, 0, answer, 0, n); }
			return;
		}
		for(int i = 0; i < n; ++i){
			if(used[i] || s[i] < denominator) continue;
			used[i] = true; tempa[n-d-1] = i;
			DFS(d+1, numerator+(n-d-1)*w[i], denominator+w[i]);
			used[i] = false;
		}
	}
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
	public static <T extends Comparable <? super T>> boolean nextPermutation(T[] a){
		return nextPermutation(a, 0, a.length, new Comparator<T>(){
			@Override public int compare(T o1, T o2) { return o1.compareTo(o2); }
		});
	}
	public static Object[] swap(Object[] a, int i, int j){	
		Object tmp = a[i]; a[i] = a[j]; a[j] = tmp; return a;
	}
	public static void reverse(Object[] a, int fromIndex, int toIndex) {
		for(int n = toIndex - fromIndex, mid = n>>1, i = 0, j = n-1; i < mid; ++i, --j)
			swap(a, fromIndex+i, fromIndex+j); 
	}
}
