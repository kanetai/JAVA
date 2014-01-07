import java.util.*;
public class aoj0231 {
	static final Scanner stdin = new Scanner(System.in);
	static final int LIMIT = 150, MASS = 0, TIME = 1, N = 2;
	public static void main (String[] args) {
		while (true) {
			int n = stdin.nextInt();
			if (n == 0) break;
			int[][] array = new int[n+n][N];
			for (int i = 0; i < n+n; i+=2) {
				int m = stdin.nextInt(), a = stdin.nextInt(), b = stdin.nextInt();
				array[i] = new int[]{m, a};
				array[i+1] = new int[]{-m, b};
			}
			Arrays.sort(array, new Comparator<int[]>() {
				@Override public int compare(int[] a, int[] b) { return a[TIME] != b[TIME] ? a[TIME] - b[TIME] : a[MASS] - b[MASS]; }
			});
			String ans = "OK";
			int weight = 0;
			for (int[] t : array) {
				weight += t[MASS];
				if (weight > LIMIT) { ans = "NG"; break; }
			}
			System.out.println(ans);
		}
	}
}