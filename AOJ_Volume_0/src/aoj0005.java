import java.util.*;
public class aoj0005 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while( stdin.hasNext() ){
			int a = stdin.nextInt();
			int b = stdin.nextInt();
			System.out.println( GCD(a,b) + " " + LCM(a,b) );
		}
	}
	public static final int GCD(int a, int b){ return b == 0 ? a : GCD(b, a%b); }
	public static final int LCM(int a, int b){ return a / GCD(a, b) * b; } // a * b / gcd(a,b) だとoverflowした
}
