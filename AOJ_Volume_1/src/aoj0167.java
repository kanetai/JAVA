import java.util.*;
public class aoj0167 {
	static final Scanner stdin = new Scanner(System.in);
	static Solver solver = Solver.MergeCount;
	public static void main(String[] args) {
		int n;
		while((n = stdin.nextInt()) != 0){
			int[] a = new int[n];
			for (int i = 0; i < n; i++) a[i] = stdin.nextInt();
			System.out.println(solver.solve(a));
		}
	}
	static enum Solver {
		BIT { @Override int solve(int[] a){
			int n = a.length;
			int[] b = new int[n]; System.arraycopy(a, 0, b, 0, n); Arrays.sort(b); //aをソートしたやつ
			FenwickTree tree = new FenwickTree(n); // all 0 で 初期化
			int ans = 0;
			for(int i = 0; i < n; ++i){
				int pos = Arrays.binarySearch(b, a[i]); //ソート後の位置(a[i]の順位)
				ans += i - tree.sum(pos); //tree.sum(pos)= |{j|j < i, a[j] <= a[i]}|
				tree.add(pos, 1);
			}
			return ans;
		}}, MergeCount { @Override int solve(int[] a){ return mergeCount(a); }
		}, BubbleSort { @Override int solve(int[] a){ return bubbleSort(a); }};
		int solve(int[] a){ return 0; }
	}
	public static int solve(int[] a){
		int n = a.length;
		int[] b = new int[n]; System.arraycopy(a, 0, b, 0, n); Arrays.sort(b); //aをソートしたやつ
		FenwickTree tree = new FenwickTree(n); // all 0 で 初期化
		int ans = 0;
		for(int i = 0; i < n; ++i){
			int pos = Arrays.binarySearch(b, a[i]); //ソート後の位置(a[i]の順位)
			ans += i - tree.sum(pos); //tree.sum(pos)= |{j|j < i, a[j] <= a[i]}|
			tree.add(pos, 1);
		}
		return ans;
	}
	public static class FenwickTree{
		private int[] x;
		public FenwickTree(int n){ init(n);}
		public final void init(int n){ x = new int[n]; Arrays.fill(x, 0); }
		public final int sum(int i){
			int ret = 0;
			//If 1 start (not 0 start), use j -= (j&-j)
			for(int j = i; j >= 0; j = ((j & (j+1)) - 1)) ret += x[j];
			return ret;
		}
		public final void add(int i, int a){
			//If 1 start (not 0 start), use j += (j&-j)
			for(int j = i; j < x.length; j |= j+1) x[j] += a;
		}
	}
	public static final int[] swap(int[] x, int i, int j){
		int tmp = x[i]; x[i] = x[j]; x[j] = tmp; return x;
	}
	public static final int bubbleSort(int a[], int fromIndex, int toIndex){
		int n = toIndex - fromIndex, count = 0;
		for (int i = fromIndex+1; i < n; ++i) 
			for (int j = fromIndex; j < n-i; j++) 
				if(a[j] > a[j+1]){ swap(a, j, j+1); count++; }
		return count;
	}
	public static final int bubbleSort(int a[]){ return bubbleSort(a, 0, a.length); }
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
	public static final int mergeCount(int[] a){ return mergeCount(a, 0, a.length); }
}
