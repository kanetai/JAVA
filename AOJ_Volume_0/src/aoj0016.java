import java.util.*;
public class aoj0016 {
	static final Scanner stdin = new Scanner(System.in);
	static double x,y;
	static int theta; //degree
	public static void main(String[] args) {
		x = y = 0.0;
		theta = 90; //north
		while(true){
			String[] s = stdin.next().split(",");
			int r = Integer.parseInt( s[0] );
			int t = Integer.parseInt( s[1] );
			if( r==0 && t==0 ) break;
			solve(r,t);
		}
		System.out.printf("%d\n%d\n", (int)x, (int)y);
	}
	static void solve(int r, int t){ //t clockwise -> positive
		x += r*Math.cos(Math.PI/180*theta);
		y += r*Math.sin(Math.PI/180*theta);
		theta -= t; //theta clockwise -> negative
		theta %= 360;
	}
}
