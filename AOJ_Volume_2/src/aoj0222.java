import java.util.*;
public class aoj0222 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 10000000;
	static boolean[] p = sieve(N + 1);
	public static void main(String[] args) {
		while (true) {
			int n = stdin.nextInt();
			if (n == 0) break;
			while (!(p[n] && p[n-2] && p[n-6] && p[n-8])) n--;
			System.out.println(n);
		}
	}
	public static final boolean[] sieve(int n){
		boolean[] ret = new boolean[n]; Arrays.fill(ret, true);
		ret[0] = ret[1] = false;
		for(int i = 2; i*i < n; ++i)
			if(ret[i]) for(int j = i*i; j < n; j+=i) ret[j] = false;
		return ret;
	}
}
