import java.util.*;
public class aoj0009 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 1000001;
	public static void main(String[] args) {
		boolean[] tempTable = sieve(N);
		BitSet table = new BitSet(N), mask = new BitSet(N);
		for(int i = 0; i < N; i++) table.set(i, tempTable[i]);
		while(stdin.hasNext()){
			mask.clear();
			mask.set(0, stdin.nextInt()+1 );
			mask.and(table);
			System.out.println( mask.cardinality() );
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
