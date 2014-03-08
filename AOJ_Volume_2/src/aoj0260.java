import java.util.*;
public class aoj0260 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while (true) {
			int n = stdin.nextInt();
			if (n == 0) break;
			int[] j = new int[n-1];
			long sum = 0;
			for (int i = 0; i < n; ++i) sum += stdin.nextInt();
			for (int i = 0; i < n-1; ++i) { j[i] = stdin.nextInt(); sum += j[i]; }
			long ans = sum;
			Arrays.sort(j);
			for (int i = 0; i < n-1; ++i) { sum -= j[i]; ans = Math.max(ans, sum * (i+2)); }
			System.out.println(ans);
		}
	}
}
