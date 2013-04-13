import java.util.*;
public class aoj0003 {
	static final Scanner stdin = new Scanner(System.in);
	static final String solve(int a, int b, int c){
		return ( a*a == b*b + c*c || b*b == c*c + a*a || c*c == a*a + b*b ) ? "YES" : "NO";
	}
	public static void main(String[] args) {
		int n = stdin.nextInt();
		while( n-- != 0 ){
			int a = stdin.nextInt();
			int b = stdin.nextInt();
			int c = stdin.nextInt();
			System.out.println( solve(a,b,c) );
		}
	}
}
