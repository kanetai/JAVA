import java.util.*;
public class aoj0076 {
	static final Scanner stdin = new Scanner(System.in);
	static final double RIGHT = Math.PI/2.0;
	public static void main(String[] args) {
		int n;
		while((n = stdin.nextInt()) != -1){
			double x = 1.0, y = 0.0;
			for(int i = 0; i < n-1; ++i){
				double coef = 1/Math.hypot(x, y), temp = x;
				x -= coef*y;
				y += coef*temp;
			}
			System.out.printf("%f\n%f\n",x,y);
		}
	}
}
