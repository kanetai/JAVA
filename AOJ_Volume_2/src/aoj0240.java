import java.util.*;
public class aoj0240 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while (true) {
			int n = stdin.nextInt();
			if (n == 0) break;
			int y = stdin.nextInt(), ans = -1;
			double max = -1, charge = -1;
			while (n-- > 0) {
				int b = stdin.nextInt(), r = stdin.nextInt(), t = stdin.nextInt();
				charge = t == 1 ? (1 + y * r/100.) : Math.pow(1 + r/100., y);
				if (charge > max) { max = charge; ans = b; }
			}
			System.out.println(ans);
		}
	}
}
