import java.util.*;
public class aoj0241 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while (true) {
			int n = stdin.nextInt();
			if (n == 0) break;
			while (n-- > 0) {
				int x1 = stdin.nextInt(), y1 = stdin.nextInt(), z1 = stdin.nextInt(), w1 = stdin.nextInt(), x2 = stdin.nextInt(), y2 = stdin.nextInt(), z2 = stdin.nextInt(), w2 = stdin.nextInt();
				System.out.println(String.format("%d %d %d %d", x1*x2 - y1*y2 - z1*z2 - w1*w2, x1*y2 + y1*x2 + z1*w2 - w1*z2, x1*z2 - y1*w2 + z1*x2 + w1*y2, x1*w2 + y1*z2 - z1*y2 + w1*x2));
			}
		}
	}
}
