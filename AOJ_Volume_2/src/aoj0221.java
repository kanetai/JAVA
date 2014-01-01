import java.util.*;
public class aoj0221 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while (true) {
			int m = stdin.nextInt(), n = stdin.nextInt();
			if ((m|n) == 0) break;
			List<Integer> p = new LinkedList<Integer>();
			for (int i = 1; i <= m; ++i) p.add(i);
			int pi = 0;
			for (int i = 1; i <= n; ++i) {
				String l = stdin.next();
				if (p.size() == 1) continue;
				String a = i % 15 == 0 ? "FizzBuzz" : i % 3 == 0 ? "Fizz" : i % 5 == 0 ? "Buzz" : i+"";
				if (a.equals(l)) { 
					pi = (pi + 1) % p.size();
				} else {
					p.remove(pi); pi = pi % p.size(); 
				}
			}
			StringBuilder sb = new StringBuilder();
			for (int x : p) sb.append(" " + x);
			System.out.println(sb.substring(1));
		}
	}
}
