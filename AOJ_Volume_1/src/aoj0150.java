import java.util.*;
public class aoj0150 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 10001;
	public static final boolean[] sieve(int n){
		boolean[] ret = new boolean[n]; Arrays.fill(ret, true);
		ret[0] = ret[1] = false;
		for(int i = 2; i*i < n; ++i)
			if(ret[i]) for(int j = i*i; j < n; j+=i) ret[j] = false;
		return ret;
	}
	public static void main(String[] args) {
		boolean[] prime = sieve(N);
		int n;
		while((n = stdin.nextInt()) != 0){
			int pre = -1;
			for(int i = n; i >= 2; --i){
				if(prime[i]){
					if(pre-i == 2){
						System.out.println(i + " " + pre);
						break;
					}
					pre = i;
				}
			}
		}
	}
}
