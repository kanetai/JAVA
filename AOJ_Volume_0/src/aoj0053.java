import java.util.*;
public class aoj0053 {
	static final Scanner stdin = new Scanner(System.in);
	static final int MAX = 1000001;
	public static final boolean[] sieve(int n){
		boolean[] ret = new boolean[n]; Arrays.fill(ret, true);
		ret[0] = ret[1] = false;
		for(int i = 2; i*i < n; ++i)
			if(ret[i]) for(int j = i*i; j < n; j+=i) ret[j] = false;
		return ret;
	}
	public static void main(String[] args) {
		boolean[] table = sieve(MAX);
		Stack<Integer> LUT = new Stack<Integer>();
		LUT.push(0);
		for(int i = 0; i < MAX; ++i) if(table[i]) LUT.push(LUT.peek()+i);
		while(true){
			int n = stdin.nextInt();
			if(n==0) break;
			System.out.println(LUT.get(n));
		}
	}
}
