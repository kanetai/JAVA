import java.util.*;
public class aoj0056 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N_MAX = 50001;
	static final ArrayList<Integer> prime_array = new ArrayList<Integer>();
	static final boolean[] prime_flag = sieve(N_MAX);
	public static final boolean[] sieve(int n){
		boolean[] ret = new boolean[n]; Arrays.fill(ret, true);
		ret[0] = ret[1] = false;
		for(int i = 2; i*i < n; ++i)
			if(ret[i]) for(int j = i*i; j < n; j+=i) ret[j] = false;
		return ret;
	}
	public static void main(String[] args) {
		for(int i = 0; i < N_MAX; ++i) if(prime_flag[i]) prime_array.add(i);		
		while(true){
			int n = stdin.nextInt();
			if(n == 0) break;
			System.out.println(solve(n));
		}
	}
	static int solve(int n){
		int res = 0;
		for(int a: prime_array){
			int b = n - a;
			if(a <= b && prime_flag[b]) res++;
		}
		return res;
	}
}
