import java.util.*;
public class aoj0219 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 10;
	public static void main(String[] args) {
		while (true) {
			int n = stdin.nextInt();
			if (n == 0) break;
			int[] p = new int[N];
			while (n-- > 0) p[stdin.nextInt()]++;
			for (int x : p) {
				if (x == 0) System.out.print("-");
				else while (x-- > 0) System.out.print("*");
				System.out.println();
			}
		}
	}
}
