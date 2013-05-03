import java.util.*;
public class aoj0135 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int n = stdin .nextInt();
		while(n-- > 0){
			String[] str = stdin.next().split(":");
			int h = Integer.parseInt(str[0]), m = Integer.parseInt(str[1]);
			double a = (30.0 * h) + m / 2.0, b = m * 6.0;
			double x = Math.min(Math.abs(a-b), Math.abs(b-a));
			if(x >= 180) x = 360 - x;
			System.out.println((x < 30) ? "alert" : (x < 90) ? "warning" : "safe");
		}
	}
}
