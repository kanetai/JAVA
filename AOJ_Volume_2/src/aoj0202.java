import java.util.*;
public class aoj0202 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 1000000;
	static final boolean[] table = sieve(N);
	public static void main(String[] args) {
		while(true){
			int n = stdin.nextInt(), x = stdin.nextInt();
			if((n|x) == 0) break;
			boolean[] flag = new boolean[x+1]; Arrays.fill(flag, false);
			int[] menu = new int[n];
			for(int i = 0; i < n; ++i){
				menu[i] = stdin.nextInt();
				if(menu[i] <= x) flag[menu[i]] = true;
			}
			for(int i = 0; i <= x; ++i){
				if(flag[i]) continue;
				for(int p: menu) if(i-p >= 0 && flag[i-p]) flag[i] = true;
			}
			int ans = -1;
			for(int i = x; i >= 0; --i) if(flag[i] && table[i]){ ans = i; break; }
			System.out.println(ans == -1 ? "NA" : ans);
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
