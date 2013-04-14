import java.util.*;
public class aoj0011 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int w = stdin.nextInt();
		int n = stdin.nextInt();
		int[] v = new int[w+1]; //(1からはじまる)
		for(int i=0; i < w+1; ++i) v[i]=i;
		for(int i=0; i < n; ++i){
			String[] str = stdin.next().split(",");
			swap(v, Integer.parseInt( str[0] ), Integer.parseInt( str[1] ));
		}
		for(int i=1; i < w+1; ++i) System.out.println( v[i] );
	}
	public static final int[] swap(int[] x, int i, int j){
		int tmp = x[i]; x[i] = x[j]; x[j] = tmp; return x;
	}
}
