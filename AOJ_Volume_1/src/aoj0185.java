import java.util.*;
public class aoj0185 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 1000000;
	static final boolean[] table = sieve(N);
	public static void main(String[] args) {
		List<Integer> list = new LinkedList<Integer>();
		for(int i = 0; i <= N; ++i) if(table[i]) list.add(i);
		int n;
		while((n = stdin.nextInt()) != 0){
			int ans = 0;
			for(int p: list){
				int q = n-p;
				if(p > q) continue;
				if(table[q]) ans++;
			}
			System.out.println(ans);
		}
	}
	public static boolean[] sieve(int n){
		boolean[] ret = new boolean[n+1];
		Arrays.fill(ret, true);
		ret[0] = ret[1] = false;
		for(int i = 2; i*i <= n; ++i)
			if(ret[i]) for(int j = i*i; j <= n; j+=i) ret[j] = false;
		return ret;
	}
}
