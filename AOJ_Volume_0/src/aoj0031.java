import java.util.*;
public class aoj0031 {
	static final Scanner stdin = new Scanner(System.in);
	final static int[] b = {1,2,4,8,16,32,64,128,256,512};
	public static void main(String[] args) {
		while(stdin.hasNext() ){
			ArrayList<Integer> a = new ArrayList<Integer>();
			solve( a, stdin.nextInt() );
			for(int i=0; i<a.size(); ++i) System.out.print( a.get(i)+(a.size()-1 == i ? "\n" : " ") );
		}
	}
	static void solve(ArrayList<Integer> a, int n){
		for(int i=0; i<10; ++i){
			if( (n & 1) == 1) a.add(b[i]);
			n >>= 1;
		}
	}
}
