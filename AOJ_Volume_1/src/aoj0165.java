import java.util.*;
public class aoj0165 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 1000000;
	public static final boolean[] sieve(int n){
		boolean[] ret = new boolean[n]; Arrays.fill(ret, true);
		ret[0] = ret[1] = false;
		for(int i = 2; i*i < n; ++i)
			if(ret[i]) for(int j = i*i; j < n; j+=i) ret[j] = false;
		return ret;
	}
	public static void main(String[] args) {
		boolean[] isPrime = sieve(N+1);
		int[] LUT = new int[N+1];
		LUT[0] = 0;
		for(int i = 1; i <= N; ++i) LUT[i] = LUT[i-1] + (isPrime[i] ? 1 : 0);
		int n;
		while((n = stdin.nextInt()) != 0){
			int ans = 0;
			for (int i = 0; i < n; i++) {
				int p = stdin.nextInt(), m = stdin.nextInt();
				int x = LUT[Math.min(N, p + m)] - LUT[Math.max(1, p - m)-1];
				ans += (x == 0 ? -1 : x - 1);
			}
			System.out.println(ans);
		}
	}
}
