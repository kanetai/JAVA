import java.util.*;
public class aoj0166 {
	static final Scanner stdin = new Scanner(System.in);
	public static final double EPS = 1e-10;
	public static final boolean equal(double a, double b){ return Math.abs(a-b) < EPS; }// a == b
	public static void main(String[] args) {
		int m;
		while((m = stdin.nextInt()) != 0){
			double a = 0, b = 0, c = 360;
			while(--m > 0){
				int x = stdin.nextInt();
				a += Math.sin(x*Math.PI/180);
				c -= x;
			}
			a += Math.sin(c*Math.PI/180);
			int n = stdin.nextInt(); c = 360;
			while(--n > 0){
				int x = stdin.nextInt();
				b += Math.sin(x*Math.PI/180);
				c -= x;
			}
			b += Math.sin(c*Math.PI/180);
			System.out.println(equal(a, b) ? 0 : a > b ? 1 : 2);
		}
	}
}
