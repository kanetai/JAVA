import java.util.*;
public class aoj0239 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while (true) {
			int n = stdin.nextInt();
			if (n == 0) break;
			List<int[]> l = new ArrayList<int[]>();
			while (n-- > 0) {
				int[] a = { stdin.nextInt(), stdin.nextInt(), stdin.nextInt(), stdin.nextInt(), 0 };
				a[4] = 4 * a[1] + 9 * a[2] + 4 * a[3];
				l.add(a);
			}
			int b[] = {0, stdin.nextInt(), stdin.nextInt(), stdin.nextInt(), stdin.nextInt()};
			boolean f = true;
			LOOP: for (int[] a : l) {
				for (int i = 1; i < a.length; ++i) if (a[i] > b[i]) continue LOOP;
				System.out.println(a[0]); f = false;
			}
			if (f) System.out.println("NA");
		}
	}
}
